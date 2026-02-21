package ru.kernogo.gregtech6port.features.items.like.spray;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.features.items.like.spray.behaviors.ISprayBehavior;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.function.Consumer;

/**
 * Item class for all spray-like items. <br>
 * Uses the {@link GTDataComponentTypes#ITEM_WITH_USES} data component - data for items with uses,
 * look there for the details about it. <br>
 * Also, an instance of {@link ISprayBehavior} is used as a delegate to implement the spray behavior on click.
 */
@Slf4j
public class GTSprayLikeItem extends Item {
    private final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();
    private final ISprayBehavior sprayBehavior;

    public GTSprayLikeItem(ISprayBehavior sprayBehavior, Properties properties) {
        super(properties);
        this.sprayBehavior = sprayBehavior;
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

    /** This is called from the event handler for this item: {@link GTSprayLikeItemEntityInteractHandler} */
    public InteractionResult onEntityInteract(ItemStack stack,
                                              Player player,
                                              Entity interactionTarget,
                                              InteractionHand usedHand) {
        try {
            return doOnEntityInteract(stack, player, interactionTarget, usedHand);
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Unexpected validation fail occurred", e);
            return InteractionResult.FAIL;
        }
    }

    @SuppressWarnings("deprecation") // appendHoverText is deprecated
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        try {
            for (Component tooltipLine : itemWithUsesBehavior.makeTooltip(stack)) {
                tooltipAdder.accept(tooltipLine);
            }
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Unexpected validation fail occurred", e);
        }
    }

    private InteractionResult doUseOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player == null) {
            throw new GTUnexpectedValidationFailException("Player is null, which is unexpected");
        }

        if (itemWithUsesBehavior.getIsStackedAndMultiUseAndDisplayErrorToPlayer(context.getItemInHand(), player, level.isClientSide())) {
            return InteractionResult.FAIL;
        }

        ISprayBehavior.ISprayUseResult sprayUseResult = sprayBehavior.useOnBlock(itemWithUsesBehavior.getRemainingUses(stack), context);

        return processSprayUseResult(sprayUseResult, stack, player, level, context.getHand());
    }

    private InteractionResult doOnEntityInteract(ItemStack stack,
                                                 Player player,
                                                 Entity interactionTarget,
                                                 InteractionHand usedHand) {
        Level level = player.level();

        if (itemWithUsesBehavior.getIsStackedAndMultiUseAndDisplayErrorToPlayer(stack, player, level.isClientSide())) {
            return InteractionResult.FAIL;
        }

        ISprayBehavior.ISprayUseResult sprayUseResult = sprayBehavior.useOnEntity(
            itemWithUsesBehavior.getRemainingUses(stack), stack, player, interactionTarget, usedHand
        );

        return processSprayUseResult(sprayUseResult, stack, player, level, usedHand);
    }

    private InteractionResult processSprayUseResult(ISprayBehavior.ISprayUseResult sprayUseResult,
                                                    ItemStack stack,
                                                    Player player,
                                                    Level level,
                                                    InteractionHand usedHand) {
        return switch (sprayUseResult) {
            case ISprayBehavior.ISprayUseResult.NotSupported ignored -> InteractionResult.PASS;
            case ISprayBehavior.ISprayUseResult.WasNotUsedDueToInsufficientUses result -> {
                if (level.isClientSide()) {
                    player.sendOverlayMessage(
                        Component.translatable("gregtech6port.client_message.insufficient_uses", result.minNumberOfUsesRequired())
                    );
                }
                yield InteractionResult.FAIL;
            }
            case ISprayBehavior.ISprayUseResult.CouldNotBeUsedOnThisThing ignored -> InteractionResult.FAIL;
            case ISprayBehavior.ISprayUseResult.WasUsed result -> {
                int numUsesToDecrease = result.numUsesToDecrease();
                if (numUsesToDecrease < 0 || numUsesToDecrease > itemWithUsesBehavior.getRemainingUses(stack)) {
                    throw new GTUnexpectedValidationFailException(
                        "Unexpected value of numUsesToDecrease=%s (can't be above the remaining uses or below zero) for spray paint ItemStack=%s"
                            .formatted(numUsesToDecrease, stack)
                    );
                }
                itemWithUsesBehavior.decreaseUsesOrBreak(numUsesToDecrease, stack, player, usedHand);
                yield InteractionResult.SUCCESS;
            }
        };
    }
}
