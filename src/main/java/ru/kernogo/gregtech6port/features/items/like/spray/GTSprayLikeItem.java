package ru.kernogo.gregtech6port.features.items.like.spray;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.features.items.like.spray.behavior.SprayBehaviorDelegate;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.List;

/**
 * Item class for all spray-like items. <br>
 * Uses the {@link GTDataComponentTypes#ITEM_WITH_USES} data component - data for items with uses,
 * look there for the details about it.
 */
@Slf4j
public class GTSprayLikeItem extends Item {
    private final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();
    private final SprayBehaviorDelegate sprayBehaviorDelegate;

    public GTSprayLikeItem(SprayBehaviorDelegate sprayBehaviorDelegate, Properties properties) {
        super(properties);
        this.sprayBehaviorDelegate = sprayBehaviorDelegate;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        try {
            return doUseOn(context);
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Unexpected validation fail occurred", e);
            return InteractionResult.FAIL;
        }
    }

    private InteractionResult doUseOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player == null) {
            log.error("Player is null which is unexpected");
            return InteractionResult.FAIL;
        }

        itemWithUsesBehavior.displayErrorIfStackedAndIsMultiUse(context.getItemInHand(), player, level.isClientSide());

        if (!itemWithUsesBehavior.canUse(stack)) {
            return InteractionResult.FAIL;
        }

        if (!sprayBehaviorDelegate.canUseOnBlock(context)) {
            return InteractionResult.FAIL;
        }

        itemWithUsesBehavior.decreaseUsesOrBreak(stack, player, context.getHand());

        sprayBehaviorDelegate.doUseOnBlock(context);

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        try {
            tooltipComponents.addAll(itemWithUsesBehavior.makeTooltip(stack));
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Unexpected validation fail occurred", e);
        }
    }
}
