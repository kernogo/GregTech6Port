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
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesData;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Set of tests to check that fire eventually appears after clicking with the lighter-like items for some times. <br>
 * There are two sets of tests - for multi-use and single-use lighters.
 */
@GameTestHolder(GregTech6Port.MODID)
public class LighterLikeItemClickUntilFireTests {
    private static final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();

    @GameTestGenerator
    public static Collection<TestFunction> tests_ClickUntilFire_MultiUse() {
        ArrayList<TestFunction> tests = new ArrayList<>();
        for (DeferredItem<Item> deferredItem : List.of(
            GTItems.MATCH_BOX,
            GTItems.LIGHTER,
            GTItems.SHINY_LIGHTER
        )) {
            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath(),
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_ClickUntilFire_MultiUse(gameTestHelper, deferredItem)
            ));
        }
        return tests;
    }

    @GameTestGenerator
    public static Collection<TestFunction> tests_ClickUntilFire_SingleUse() {
        ArrayList<TestFunction> tests = new ArrayList<>();
        for (DeferredItem<Item> deferredItem : List.of(
            GTItems.MATCH,
            GTItems.FIRE_STARTER_DRY_GRASS,
            GTItems.FIRE_STARTER_DRY_TREE_BARK
        )) {
            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath(),
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_ClickUntilFire_SingleUse(gameTestHelper, deferredItem)
            ));
        }
        return tests;
    }

    private static void doTest_ClickUntilFire_MultiUse(GameTestHelper gameTestHelper,
                                                       DeferredItem<Item> deferredLighterItem) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, deferredLighterItem.toStack());

        ItemStack initialLighterStack = player.getMainHandItem();
        GTItemWithUsesData initialItemWithUsesData = itemWithUsesBehavior.validateAndGetItemWithUsesData(initialLighterStack);
        int initialRemainingUses = initialItemWithUsesData.remainingUses();
        int initialMaxRemainingUses = initialItemWithUsesData.maxRemainingUses();

        int maxTimesToClick = 50;
        gameTestHelper.assertTrue(initialMaxRemainingUses > maxTimesToClick,
            String.format(
                "This test was made only for lighters with max remaining uses above %s (so the lighter does not break)",
                maxTimesToClick
            )
        );
        int numberOfClicks = clickUntilFireAppearsOrFail(gameTestHelper,
            player,
            deferredLighterItem,
            new BlockPos(0, 1, 0),
            maxTimesToClick,
            false
        );

        ItemStack currentLighterStack = player.getMainHandItem();
        GTItemWithUsesData currentItemWithUsesData = itemWithUsesBehavior.validateAndGetItemWithUsesData(initialLighterStack);
        int currentRemainingUses = currentItemWithUsesData.remainingUses();
        int currentMaxRemainingUses = currentItemWithUsesData.maxRemainingUses();

        gameTestHelper.assertValueEqual(currentLighterStack.getItem(), deferredLighterItem.get(), "lighter item type");

        GTGameTestUtils.assertEquals(gameTestHelper, currentMaxRemainingUses, initialMaxRemainingUses,
            "Max remaining uses changed");

        gameTestHelper.assertTrue(currentRemainingUses == initialRemainingUses - numberOfClicks,
            String.format("currentRemainingUses=%s != initialRemainingUses=%s - numberOfClicks=%s",
                initialRemainingUses, currentRemainingUses, numberOfClicks));

        gameTestHelper.succeed();
    }

    private static void doTest_ClickUntilFire_SingleUse(GameTestHelper gameTestHelper,
                                                        DeferredItem<Item> deferredLighterItem) {
        Holder<Item> breaksInto = itemWithUsesBehavior.validateAndGetItemWithUsesData(deferredLighterItem.toStack()).breaksInto();
        gameTestHelper.assertTrue(breaksInto != null && breaksInto.value() == Items.AIR,
            "This test was made for the lighters that break into the AIR item only");

        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);

        clickUntilFireAppearsOrFail(gameTestHelper,
            player,
            deferredLighterItem,
            new BlockPos(0, 1, 0),
            50,
            true
        );

        gameTestHelper.succeed();
    }

    private static int clickUntilFireAppearsOrFail(GameTestHelper gameTestHelper,
                                                   Player player,
                                                   DeferredItem<Item> deferredLighterItem,
                                                   BlockPos blockPos,
                                                   int maxTimesToClick,
                                                   boolean isSingleUse) {
        int numberOfClicks = 0;
        boolean fireAppeared = false;
        while (numberOfClicks < maxTimesToClick) {
            if (isSingleUse) {
                gameTestHelper.assertTrue(player.getMainHandItem().is(Items.AIR),
                    "Expected the lighter item to not be present");
                player.setItemInHand(InteractionHand.MAIN_HAND, deferredLighterItem.toStack());
            }
            numberOfClicks++;
            GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, blockPos);
            if (gameTestHelper.getBlockState(blockPos.above()).is(Blocks.FIRE)) {
                fireAppeared = true;
                break;
            }
        }
        gameTestHelper.assertTrue(fireAppeared, "Fire didn't appear");
        return numberOfClicks;
    }
}
