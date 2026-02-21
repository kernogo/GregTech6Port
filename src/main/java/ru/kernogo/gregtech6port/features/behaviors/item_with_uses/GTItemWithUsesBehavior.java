package ru.kernogo.gregtech6port.features.behaviors.item_with_uses;

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
public class GTItemWithUsesBehavior {
    /**
     * Returns true if item with uses is stacked and multi-use, else false. <br>
     * Displays error to the Player if the item is stacked and multi-use. <br>
     * Remember to call this before checking {@link #getRemainingUses} and calling {@link #decreaseUsesOrBreak}
     */
    public boolean getIsStackedAndMultiUseAndDisplayErrorToPlayer(ItemStack itemStack, Player player, boolean isClientSide) {
        validateAndGetItemWithUsesData(itemStack);

        if (itemStack.getCount() != 1 && !isSingleUse(itemStack)) {
            if (isClientSide) {
                player.sendOverlayMessage(
                    Component.translatable("gregtech6port.client_message.unstack_to_use", itemStack.getDisplayName().getString())
                );
            }
            return true;
        }
        return false;
    }

    /** Get the number of uses remaining for an item with uses. (For single-use items it will be 1) */
    public int getRemainingUses(ItemStack itemStack) {
        GTItemWithUsesData itemWithUsesData = validateAndGetItemWithUsesData(itemStack);

        return itemWithUsesData.remainingUses();
    }

    /**
     * Decreases the remaining uses for multi-use items or destroys one item from the stack for single-use items
     * (note that {@code numberOfUsesToDecrease} must be 1 for single-use items). <br>
     * If {@code numberOfUsesToDecrease} is less than the remaining uses, an exception is thrown. <br>
     * Puts the {@code breaksInto} item into player's inventory if needed. <br>
     * Do not use without checking the number of remaining uses with {@link #getRemainingUses} first.
     */
    public void decreaseUsesOrBreak(int numberOfUsesToDecrease, ItemStack itemStack, Player player, InteractionHand hand) {
        GTItemWithUsesData itemWithUsesData = validateAndGetItemWithUsesData(itemStack);

        if (numberOfUsesToDecrease > itemWithUsesData.remainingUses()) {
            throw new GTUnexpectedValidationFailException(
                "numberOfUsesToDecrease=%s is above the remaining number of uses on an item with uses: %s"
                    .formatted(numberOfUsesToDecrease, itemStack),
                new RuntimeException("Exception thrown for stack trace purposes")
            );
        }
        if (numberOfUsesToDecrease < 1) {
            throw new GTUnexpectedValidationFailException(
                "numberOfUsesToDecrease=%s is below 1".formatted(numberOfUsesToDecrease),
                new RuntimeException("Exception thrown for stack trace purposes")
            );
        }

        int remainingUses = itemWithUsesData.remainingUses();
        int maxRemainingUses = itemWithUsesData.maxRemainingUses();
        Holder<Item> breaksInto = itemWithUsesData.breaksInto();

        // Actually decrease uses or break
        if (isSingleUse(itemStack)) {
            ItemStack breaksIntoItemStack = GTUtils.assureNotNull(breaksInto) // breaksInto != null for single-use items
                .value()
                .getDefaultInstance();

            // numberOfUsesToDecrease is 1 for single-use items
            if (itemStack.getCount() == 1) {
                player.setItemInHand(hand, breaksIntoItemStack);
            } else {
                player.addItem(breaksIntoItemStack);
                itemStack.shrink(1);
            }
        } else { // Multi-use
            if (remainingUses - numberOfUsesToDecrease == 0 && breaksInto != null) {
                player.setItemInHand(hand, breaksInto.value().getDefaultInstance());
            } else {
                itemStack.set(GTDataComponentTypes.ITEM_WITH_USES,
                    new GTItemWithUsesData(
                        remainingUses - numberOfUsesToDecrease,
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
            throw new GTUnexpectedValidationFailException("remainingUses=%s is less than 0".formatted(remainingUses));
        }

        if (remainingUses > maxRemainingUses) {
            throw new GTUnexpectedValidationFailException(
                "remainingUses=%s is greater than maxRemainingUses=%s".formatted(remainingUses, maxRemainingUses)
            );
        }

        if (itemWithUsesData.breaksInto() != null && itemWithUsesData.maxRemainingUses() == 1) { // If item is single-use
            if (remainingUses != 1) {
                throw new GTUnexpectedValidationFailException("remainingUses=%s must be equal to 1 on a singe-use item".formatted(remainingUses));
            }
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
