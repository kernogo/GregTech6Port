package ru.kernogo.gregtech6port.gametests;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.ItemStack;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesData;

/**
 * Utils for the game testing of items with uses
 * (Items that have {@link ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes#ITEM_WITH_USES} data component)
 */
public final class GTItemWithUsesGameTestUtils {
    private GTItemWithUsesGameTestUtils() {}

    private static final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();

    /** Returns the remaining uses of an item with uses */
    public static int getRemainingUses(ItemStack itemStack) {
        GTItemWithUsesData itemWithUsesData = itemWithUsesBehavior.validateAndGetItemWithUsesData(itemStack);
        return itemWithUsesData.remainingUses();
    }

    /** Asserts that the remaining uses are equal to an expected value */
    public static void assertRemainingUsesEquals(GameTestHelper gameTestHelper, ItemStack itemStack, int expectedRemainingUses) {
        GTItemWithUsesData itemWithUsesData = itemWithUsesBehavior.validateAndGetItemWithUsesData(itemStack);
        int actualRemainingUses = itemWithUsesData.remainingUses();
        gameTestHelper.assertValueEqual(actualRemainingUses, expectedRemainingUses, "remaining uses");
    }
}
