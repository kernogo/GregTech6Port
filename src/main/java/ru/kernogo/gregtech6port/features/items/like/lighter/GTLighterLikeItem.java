package ru.kernogo.gregtech6port.features.items.like.lighter;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.utils.GTUtils;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedNullException;

import java.util.List;
import java.util.function.Supplier;

/**
 * Accepts the following non-automatic data components: <br><br>
 * For a multi-use lighter type (that is, if {@link GTDataComponentTypes#SINGLE_USE} data component is not set): <br>
 * - {@link GTDataComponentTypes#REMAINING_USES} - required, remaining number of uses <br>
 * - {@link GTDataComponentTypes#MAX_REMAINING_USES} - required, remaining number of uses <br>
 * - {@link GTDataComponentTypes#PROC_CHANCE} - required, chance of a successful lighting on fire <br>
 * - {@link GTDataComponentTypes#BREAKS_INTO} - optional, what item remains after lighter reaches zero remaining uses;
 * if null, then lighter remains with zero uses left <br><br>
 * For a single-use lighter type (that is, if {@link GTDataComponentTypes#SINGLE_USE} data component is set): <br>
 * - {@link GTDataComponentTypes#PROC_CHANCE} - required, chance of a successful lighting on fire <br>
 * - {@link GTDataComponentTypes#BREAKS_INTO} - optional, what item remains after lighter is used;
 * if null, then nothing remains after the lighter's use <br><br>
 * Notes: <br>
 * - Lighter may be stackable, but if it's multi-use, it must be unstacked before using. <br>
 * - The actual lighting of different things on fire is implemented in
 * {@link LighterBehaviorForBlocks} and {@link LighterBehaviorForEntities} delegates, which this class calls
 */
@Slf4j
public class GTLighterLikeItem extends Item {
    private final LighterBehaviorForBlocks lighterBehaviorForBlocks = new LighterBehaviorForBlocks();
    private final LighterBehaviorForEntities lighterBehaviorForEntities = new LighterBehaviorForEntities();

    /** @see GTLighterLikeItem GTLighterLikeItem for available non-automatic data components */
    public GTLighterLikeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        try {
            if (context.getPlayer() == null) {
                log.error("Player is null, which is unexpected");
                return InteractionResult.FAIL;
            }

            return doLighterLogic(
                context.getLevel(),
                context.getItemInHand(),
                context.getPlayer(),
                context.getHand(),
                () -> lighterBehaviorForBlocks.findWhatToDo(context)
            );
        } catch (GTUnexpectedNullException e) {
            log.error("An unexpected null value was encountered that should've been validated", e);
            return InteractionResult.FAIL;
        }
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        try {
            return doLighterLogic(
                player.level(),
                stack,
                player,
                usedHand,
                () -> lighterBehaviorForEntities.findWhatToDo(interactionTarget, player)
            );
        } catch (GTUnexpectedNullException e) {
            log.error("An unexpected null value was encountered that should've been validated", e);
            return InteractionResult.FAIL;
        }
    }

    private InteractionResult doLighterLogic(Level level,
                                             ItemStack stack,
                                             Player player,
                                             InteractionHand interactionHand,
                                             Supplier<@Nullable IThingToDo> thingToDoSupplier) {
        if (!validateData(stack)) {
            return InteractionResult.FAIL;
        }

        if (!checkUsePreconditions(stack, level, player)) {
            return InteractionResult.FAIL;
        }

        IThingToDo whatToDo = thingToDoSupplier.get();

        if (whatToDo == null) { // If we must do nothing
            return InteractionResult.FAIL;
        }

        // After this we know for sure that the lighter's durability must be used

        decreaseRemainingUsesOrDestroyTheLighter(interactionHand, stack, player);

        whatToDo.playLighterSound();

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        // We roll random on the server side to not cause desyncs
        double procChance = GTUtils.assureNotNull(stack.get(GTDataComponentTypes.PROC_CHANCE));
        if (!GTUtils.rollRandomChance(procChance)) {
            return InteractionResult.CONSUME;
        }

        whatToDo.lightAThingOnFire();

        return InteractionResult.CONSUME;
    }

    private void decreaseRemainingUsesOrDestroyTheLighter(InteractionHand interactionHand, ItemStack itemInHand, Player player) {
        Holder<Item> breaksInto = itemInHand.get(GTDataComponentTypes.BREAKS_INTO);

        if (itemInHand.has(GTDataComponentTypes.SINGLE_USE)) {
            if (itemInHand.getCount() > 1) {
                if (breaksInto != null) {
                    player.addItem(breaksInto.value().getDefaultInstance());
                }
                itemInHand.shrink(1);
            } else {
                if (breaksInto != null) {
                    player.setItemInHand(interactionHand, breaksInto.value().getDefaultInstance());
                } else {
                    player.setItemInHand(interactionHand, ItemStack.EMPTY);
                }
            }
        } else { // Multi-use
            int remainingUses = GTUtils.assureNotNull(itemInHand.get(GTDataComponentTypes.REMAINING_USES));

            if (breaksInto != null && remainingUses == 1) {
                if (itemInHand.getCount() > 1) {
                    player.addItem(breaksInto.value().getDefaultInstance());
                } else {
                    player.setItemInHand(interactionHand, breaksInto.value().getDefaultInstance());
                }
            } else {
                itemInHand.set(GTDataComponentTypes.REMAINING_USES, remainingUses - 1);
            }
        }
    }

    /** Check use preconditions for the use of the lighter. Returns false if check is unsuccessful */
    private boolean checkUsePreconditions(ItemStack itemInHand, Level level, Player player) {
        if (itemInHand.has(GTDataComponentTypes.SINGLE_USE)) {
            return true; // No validations for single-use lighters
        } else { // Multi-use
            int remainingUses = GTUtils.assureNotNull(itemInHand.get(GTDataComponentTypes.REMAINING_USES));
            if (remainingUses == 0) {
                return false;
            }

            if (itemInHand.getCount() != 1) {
                if (level.isClientSide()) {
                    player.displayClientMessage(
                        Component.translatable("gregtech6port.client_message.unstack_to_use", itemInHand.getDisplayName().getString()),
                        true
                    );
                }
                return false;
            }
        }

        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        try {
            if (!validateData(stack)) {
                return;
            }

            if (stack.has(GTDataComponentTypes.SINGLE_USE)) {
                tooltipComponents.add(Component.translatable("tooltip.gregtech6port.single_use"));
            } else { // Multi-use
                int remainingUses = GTUtils.assureNotNull(stack.get(GTDataComponentTypes.REMAINING_USES));
                tooltipComponents.add(Component.translatable("tooltip.gregtech6port.remaining_uses", remainingUses));
            }

            double procChance = GTUtils.assureNotNull(stack.get(GTDataComponentTypes.PROC_CHANCE));
            if (procChance != 1.0) {
                tooltipComponents.add(Component.translatable("tooltip.gregtech6port.proc_chance", String.format("%.2f", procChance * 100)));
            }
        } catch (GTUnexpectedNullException e) {
            log.error("An unexpected null value was encountered that should've been validated", e);
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_FLINT_ACTIONS.contains(itemAbility);
    }

    /** Validate ItemStack's data. Returns false if the data is invalid */
    private boolean validateData(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof GTLighterLikeItem)) {
            log.error("Item={} of the passed ItemStack={} is not an instance of GTLighterLikeItem", itemStack.getItem(), itemStack);
            return false;
        }

        Integer remainingUses = itemStack.get(GTDataComponentTypes.REMAINING_USES);
        Integer maxRemainingUses = itemStack.get(GTDataComponentTypes.MAX_REMAINING_USES);
        Double procChance = itemStack.get(GTDataComponentTypes.PROC_CHANCE);

        boolean isSingleUse = itemStack.has(GTDataComponentTypes.SINGLE_USE);

        if (isSingleUse) {
            if (procChance == null) {
                log.error("procChance is null");
                return false;
            }
        } else { // Multi-use
            if (remainingUses == null) {
                log.error("remainingUses is null when the lighter is not single use");
                return false;
            }

            if (maxRemainingUses == null) {
                log.error("maxRemainingUses is null when the lighter is not single use");
                return false;
            }

            if (procChance == null) {
                log.error("procChance is null");
                return false;
            }

            if (remainingUses < 0) {
                log.error("remainingUses={} < 0", remainingUses);
                return false;
            }

            if (remainingUses > maxRemainingUses) {
                log.error("remainingUses={} > maxRemainingUses={}", remainingUses, maxRemainingUses);
                return false;
            }
        }

        return true;
    }
}
