package ru.kernogo.gregtech6port.features.behaviors.item_with_uses;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.utils.GTUtils;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.ArrayList;
import java.util.List;

/** This class implements the functionality associated with the {@link GTDataComponentTypes#ITEM_WITH_USES} data component */
@Slf4j
public class GTItemWithUsesBehavior {
    /**
     * Displays error to the Player if the item is multi-use and stacked. <br>
     * Remember to call this before checking {@link #canUse} and calling {@link #decreaseUsesOrBreak}
     */
    public void displayErrorIfStackedAndIsMultiUse(ItemStack itemStack, Player player, boolean isClientSide) {
        validateAndGetItemWithUsesData(itemStack);

        if (!isSingleUse(itemStack) && itemStack.getCount() != 1) {
            if (isClientSide) {
                player.displayClientMessage(
                    Component.translatable("gregtech6port.client_message.unstack_to_use", itemStack.getDisplayName().getString()),
                    true
                );
            }
        }
    }

    /** Returns true if the item is allowed to be used, else false */
    public boolean canUse(ItemStack itemStack) {
        GTItemWithUsesData itemWithUsesData = validateAndGetItemWithUsesData(itemStack);

        if (isSingleUse(itemStack)) {
            return itemWithUsesData.remainingUses() > 0;
        } else {
            return itemStack.getCount() == 1 && itemWithUsesData.remainingUses() > 0;
        }
    }

    /**
     * Decreases remaining uses for multi-use items or destroys one item from the stack for single-use items. <br>
     * Puts the {@code breaksInto} item into player's inventory if needed. <br>
     * Do not use without checking {@link #canUse} first.
     */
    public void decreaseUsesOrBreak(ItemStack itemStack, Player player, InteractionHand hand) {
        GTItemWithUsesData itemWithUsesData = validateAndGetItemWithUsesData(itemStack);

        if (!canUse(itemStack)) {
            log.error("You should always check canUse() before calling decreaseUsesOrBreak()!");
            return;
        }

        int remainingUses = itemWithUsesData.remainingUses();
        int maxRemainingUses = itemWithUsesData.maxRemainingUses();
        Holder<Item> breaksInto = itemWithUsesData.breaksInto();

        // Actually decrease uses or break
        if (isSingleUse(itemStack)) {
            ItemStack breaksIntoItemStack = GTUtils.assureNotNull(breaksInto) // breaksInto != null for single-use items
                .value()
                .getDefaultInstance();

            if (itemStack.getCount() == 1) {
                player.setItemInHand(hand, breaksIntoItemStack);
            } else {
                player.addItem(breaksIntoItemStack);
                itemStack.shrink(1);
            }
        } else { // Multi-use
            if (remainingUses == 1 && breaksInto != null) {
                player.setItemInHand(hand, breaksInto.value().getDefaultInstance());
            } else {
                itemStack.set(GTDataComponentTypes.ITEM_WITH_USES,
                    new GTItemWithUsesData(
                        remainingUses - 1,
                        maxRemainingUses,
                        breaksInto
                    ));
            }
        }
    }

    /**
     * Returns true if the item is single-use. <br>
     * The definition of a single-use item is:
     * it breaks into something ({@code breaksInto != null}) and {@code maxRemainingUses == 1} <br>
     * If the item isn't single-use, it's multi-use.
     */
    public boolean isSingleUse(ItemStack itemStack) {
        GTItemWithUsesData itemWithUsesData = validateAndGetItemWithUsesData(itemStack);

        return itemWithUsesData.breaksInto() != null && itemWithUsesData.maxRemainingUses() == 1;
    }

    public GTItemWithUsesData validateAndGetItemWithUsesData(ItemStack itemStack) {
        //noinspection ConstantValue
        if (itemStack == null) { // Null check just in case
            throw new GTUnexpectedValidationFailException("null ItemStack passed");
        }

        GTItemWithUsesData itemWithUsesData = itemStack.get(GTDataComponentTypes.ITEM_WITH_USES);
        if (itemWithUsesData == null) {
            throw new GTUnexpectedValidationFailException("ItemStack with null ItemWithUsesData passed. ItemStack=" + itemStack);
        }

        int remainingUses = itemWithUsesData.remainingUses();
        int maxRemainingUses = itemWithUsesData.maxRemainingUses();

        if (remainingUses < 0) {
            throw new GTUnexpectedValidationFailException(String.format("remainingUses=%s is less than 0", remainingUses));
        }

        if (remainingUses > maxRemainingUses) {
            throw new GTUnexpectedValidationFailException(
                String.format("remainingUses=%s is greater than maxRemainingUses=%s", remainingUses, maxRemainingUses)
            );
        }

        return itemWithUsesData;
    }

    /**
     * Returns tooltip associated with the {@link GTDataComponentTypes#ITEM_WITH_USES} data component.
     * The tooltip needs to be added to the item manually.
     */
    public List<Component> makeTooltip(ItemStack itemStack) {
        List<Component> result = new ArrayList<>();

        GTItemWithUsesData itemWithUsesData = validateAndGetItemWithUsesData(itemStack);

        int remainingUses = itemWithUsesData.remainingUses();

        if (isSingleUse(itemStack)) {
            result.add(Component.translatable("tooltip.gregtech6port.single_use"));
        } else { // Multi-use
            result.add(Component.translatable("tooltip.gregtech6port.remaining_uses", remainingUses));
        }

        return result;
    }
}
