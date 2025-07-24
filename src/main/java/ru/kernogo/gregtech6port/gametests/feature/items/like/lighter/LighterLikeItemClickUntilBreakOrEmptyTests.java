package ru.kernogo.gregtech6port.gametests.feature.items.like.lighter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Set of tests to check that clicking the lighter will break it, or it will reach zero remaining uses. <br>
 * There are two sets of tests - for multi-use and single-use lighters.
 */
@GameTestHolder(GregTech6Port.MODID)
public class LighterLikeItemClickUntilBreakOrEmptyTests {
    private static final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();

    @GameTestGenerator
    public static Collection<TestFunction> tests_ClickUntilBreakOrEmpty_MultiUse() {
        ArrayList<TestFunction> tests = new ArrayList<>();
        for (DeferredItem<Item> deferredItem : List.of(
            GTItems.MATCH_BOX,
            GTItems.LIGHTER,
            GTItems.SHINY_LIGHTER
        )) {
            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath(),
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_ClickUntilBreakOrEmpty_MultiUse(gameTestHelper, deferredItem)
            ));
        }
        return tests;
    }

    @GameTestGenerator
    public static Collection<TestFunction> tests_ClickUntilBreak_SingleUse() {
        ArrayList<TestFunction> tests = new ArrayList<>();
        for (DeferredItem<Item> deferredItem : List.of(
            GTItems.MATCH,
            GTItems.FIRE_STARTER_DRY_GRASS,
            GTItems.FIRE_STARTER_DRY_TREE_BARK
        )) {
            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath(),
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_ClickUntilBreak_SingleUse(gameTestHelper, deferredItem)
            ));
        }
        return tests;
    }

    private static void doTest_ClickUntilBreakOrEmpty_MultiUse(GameTestHelper gameTestHelper,
                                                               DeferredItem<Item> deferredLighterItem) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, deferredLighterItem.toStack());
        BlockPos posToClick = new BlockPos(0, 1, 0);
        Holder<Item> breaksInto = itemWithUsesBehavior.validateAndGetItemWithUsesData(player.getMainHandItem()).breaksInto();

        int howManyTimesToClick = itemWithUsesBehavior.validateAndGetItemWithUsesData(player.getMainHandItem()).remainingUses();
        for (int i = 0; i < howManyTimesToClick - 1; i++) { // Click until 1 remaining use is left
            GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
            gameTestHelper.destroyBlock(posToClick.above()); // Destroy the fire if it appeared

            ItemStack currentLighterStack = player.getMainHandItem();

            gameTestHelper.assertValueEqual(currentLighterStack.getItem(), deferredLighterItem.get(), "lighter item type");
            int currentRemainingUses = itemWithUsesBehavior.validateAndGetItemWithUsesData(currentLighterStack).remainingUses();
            gameTestHelper.assertValueEqual(currentRemainingUses, howManyTimesToClick - i - 1, "remaining uses");
            gameTestHelper.assertTrue(GTGameTestUtils.getInventoryItemCount(player) == 1,
                "There are extra items in player's inventory");
        }

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        ItemStack currentLighterStack = player.getMainHandItem();
        if (breaksInto != null) {
            GTGameTestUtils.assertEquals(gameTestHelper, currentLighterStack.getItem(), breaksInto.value(),
                "Expected the lighter to break into a correct item");

            if (breaksInto.value() == Items.AIR) {
                gameTestHelper.assertTrue(player.getInventory().isEmpty(),
                    "There are extra items in player's inventory");
            } else {
                gameTestHelper.assertValueEqual(GTGameTestUtils.getInventoryItemCount(player), 1,
                    "number of items in player's inventory");
            }
        } else {
            gameTestHelper.assertValueEqual(currentLighterStack.getItem(), deferredLighterItem.get(), "lighter item type");
            int currentRemainingUses = itemWithUsesBehavior.validateAndGetItemWithUsesData(currentLighterStack).remainingUses();
            gameTestHelper.assertValueEqual(currentRemainingUses, 0, "remaining uses");

            gameTestHelper.assertValueEqual(GTGameTestUtils.getInventoryItemCount(player), 1,
                "number of items in player's inventory");
        }

        gameTestHelper.succeed();
    }

    private static void doTest_ClickUntilBreak_SingleUse(GameTestHelper gameTestHelper,
                                                         DeferredItem<Item> deferredLighterItem) {
        Holder<Item> breaksInto = itemWithUsesBehavior.validateAndGetItemWithUsesData(deferredLighterItem.toStack()).breaksInto();
        gameTestHelper.assertTrue(breaksInto != null && breaksInto.value() == Items.AIR,
            "This test was made for the lighters that break into the AIR item only");

        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        int stackSize = deferredLighterItem.get().getDefaultMaxStackSize();
        player.setItemInHand(InteractionHand.MAIN_HAND, deferredLighterItem.toStack(stackSize));
        BlockPos posToClick = new BlockPos(0, 1, 0);

        for (int i = 0; i < stackSize - 1; i++) { // Click until 1 lighter-like item is left
            GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
            gameTestHelper.destroyBlock(posToClick.above()); // Destroy the fire if it appeared

            ItemStack currentStack = player.getMainHandItem();

            gameTestHelper.assertValueEqual(currentStack.getItem(), deferredLighterItem.get(), "lighter item type");
            gameTestHelper.assertValueEqual(currentStack.getCount(), stackSize - i - 1, "lighter-like item stack count");
            gameTestHelper.assertValueEqual(GTGameTestUtils.getInventoryItemCount(player), stackSize - i - 1,
                "number of items in player's inventory");
        }

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        gameTestHelper.assertTrue(player.getInventory().isEmpty(),
            "Player's inventory is not empty after using all lighters");

        gameTestHelper.succeed();
    }
}
