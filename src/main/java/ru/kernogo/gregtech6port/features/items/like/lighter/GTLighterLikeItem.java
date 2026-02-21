package ru.kernogo.gregtech6port.features.items.like.lighter;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.utils.GTUtils;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Item class for all lighter-like items. <br>
 * Uses the following required data components: <br>
 * <ul>
 *   <li>{@link GTDataComponentTypes#ITEM_WITH_USES} - data for items with uses, look there for the details about it</li>
 *   <li>{@link GTDataComponentTypes#PROC_CHANCE} - chance of a successful lighting on fire</li>
 * </ul>
 * The actual lighting of different things on fire is implemented in
 * {@link LighterBehaviorForBlocks} and {@link LighterBehaviorForEntities} delegates, which this class calls.
 */
@Slf4j
public class GTLighterLikeItem extends Item {
    private final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();

    private final LighterBehaviorForBlocks lighterBehaviorForBlocks = new LighterBehaviorForBlocks();
    private final LighterBehaviorForEntities lighterBehaviorForEntities = new LighterBehaviorForEntities();

    public GTLighterLikeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        try {
            if (context.getPlayer() == null) {
                throw new GTUnexpectedValidationFailException("Player is null, which is unexpected");
            }

            return doLighterLogic(
                context.getLevel(),
                context.getItemInHand(),
                context.getPlayer(),
                context.getHand(),
                () -> lighterBehaviorForBlocks.findWhatToDo(context)
            );
        } catch (GTUnexpectedValidationFailException e) {
            log.error("An unexpected validation fail occurred", e);
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
        } catch (GTUnexpectedValidationFailException e) {
            log.error("An unexpected validation fail occurred", e);
            return InteractionResult.FAIL;
        }
    }

    @SuppressWarnings("deprecation") // appendHoverText is deprecated
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        try {
            validateDataComponents(stack);

            for (Component tooltipLine : itemWithUsesBehavior.makeTooltip(stack)) {
                tooltipAdder.accept(tooltipLine);
            }
        } catch (GTUnexpectedValidationFailException e) {
            log.error("An unexpected validation fail occurred", e);
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_FLINT_ACTIONS.contains(itemAbility);
    }

    private InteractionResult doLighterLogic(Level level,
                                             ItemStack stack,
                                             Player player,
                                             InteractionHand interactionHand,
                                             Supplier<@Nullable IThingToDo> thingToDoSupplier) {
        validateDataComponents(stack);

        if (itemWithUsesBehavior.getIsStackedAndMultiUseAndDisplayErrorToPlayer(stack, player, level.isClientSide())) {
            return InteractionResult.FAIL;
        }

        if (itemWithUsesBehavior.getRemainingUses(stack) < 1) {
            return InteractionResult.FAIL;
        }

        IThingToDo whatToDo = thingToDoSupplier.get();

        if (whatToDo == null) { // If we must do nothing
            return InteractionResult.FAIL;
        }

        itemWithUsesBehavior.decreaseUsesOrBreak(1, stack, player, interactionHand);

        whatToDo.playLighterSound();

        if (level.isClientSide()) {
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

    /** Validate data components and throw an exception if any of the components are invalid */
    private void validateDataComponents(ItemStack itemStack) {
        itemWithUsesBehavior.validateAndGetItemWithUsesData(itemStack);

        Double procChance = itemStack.get(GTDataComponentTypes.PROC_CHANCE);

        if (procChance == null) {
            throw new GTUnexpectedValidationFailException("procChance is null");
        }
    }
}
