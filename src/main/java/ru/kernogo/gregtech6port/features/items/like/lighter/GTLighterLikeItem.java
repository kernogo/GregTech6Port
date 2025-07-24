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
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.utils.GTUtils;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.List;
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

    private InteractionResult doLighterLogic(Level level,
                                             ItemStack stack,
                                             Player player,
                                             InteractionHand interactionHand,
                                             Supplier<@Nullable IThingToDo> thingToDoSupplier) {
        validateDataComponents(stack);

        itemWithUsesBehavior.displayErrorIfStackedAndIsMultiUse(stack, player, level.isClientSide());

        if (!itemWithUsesBehavior.canUse(stack)) {
            return InteractionResult.FAIL;
        }

        IThingToDo whatToDo = thingToDoSupplier.get();

        if (whatToDo == null) { // If we must do nothing
            return InteractionResult.FAIL;
        }

        itemWithUsesBehavior.decreaseUsesOrBreak(stack, player, interactionHand);

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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        try {
            validateDataComponents(stack);

            tooltipComponents.addAll(itemWithUsesBehavior.makeTooltip(stack));
        } catch (GTUnexpectedValidationFailException e) {
            log.error("An unexpected validation fail occurred", e);
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_FLINT_ACTIONS.contains(itemAbility);
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
