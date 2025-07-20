package ru.kernogo.gregtech6port.gametests.feature.items.like.lighter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Set of tests to check that blocks and entities light up after clicking on them with the lighter-like items. <br>
 * There are two sets of tests - for multi-use and single-use lighters. <br>
 * Proc chance is set to 1.0 for convenience.
 */
@GameTestHolder(GregTech6Port.MODID)
public class LighterLikeItemClickOnThingsTests {
    @GameTestGenerator
    public static Collection<TestFunction> tests_ClickOnThings_MultiUse() {
        ArrayList<TestFunction> tests = new ArrayList<>();
        for (DeferredItem<Item> deferredItem : List.of(
            GTItems.MATCH_BOX,
            GTItems.LIGHTER,
            GTItems.SHINY_LIGHTER
        )) {
            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath(),
                "gametest_bedrock_10x5x10",
                gameTestHelper -> doTest_ClickOnThings_MultiUse(gameTestHelper, deferredItem)
            ));
        }
        return tests;
    }

    @GameTestGenerator
    public static Collection<TestFunction> tests_ClickOnThings_SingleUse() {
        ArrayList<TestFunction> tests = new ArrayList<>();
        for (DeferredItem<Item> deferredItem : List.of(
            GTItems.MATCH,
            GTItems.FIRE_STARTER_DRY_GRASS,
            GTItems.FIRE_STARTER_DRY_TREE_BARK
        )) {
            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath(),
                "gametest_bedrock_10x5x10",
                gameTestHelper -> doTest_ClickOnThings_SingleUse(gameTestHelper, deferredItem)
            ));
        }
        return tests;
    }

    private static void doTest_ClickOnThings_MultiUse(GameTestHelper gameTestHelper,
                                                      DeferredItem<Item> deferredLighterItem) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        BlockPos startPos = new BlockPos(1, 2, 1);
        placeThings(gameTestHelper, startPos);

        ItemStack initialLighterStack = giveANewLighter(deferredLighterItem, player);
        int initialMaxRemainingUses = Objects.requireNonNull(initialLighterStack.get(GTDataComponentTypes.MAX_REMAINING_USES));
        int initialRemainingUses = Objects.requireNonNull(initialLighterStack.get(GTDataComponentTypes.REMAINING_USES));

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos); // 1

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(2)); // 2
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(2));
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(2));

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(4)); // 3
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(4));
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(4));

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(6)); // 4
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(6));
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(6));

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.below().east(8)); // 5
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.below().east(8));
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.below().east(8));

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.south(2)); // 6
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.south(2));
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.south(2));

        Creeper creeper = gameTestHelper.findOneEntity(EntityType.CREEPER);

        player.interactOn(creeper, InteractionHand.MAIN_HAND); // 7
        player.interactOn(creeper, InteractionHand.MAIN_HAND); // 7 + 1
        player.interactOn(creeper, InteractionHand.MAIN_HAND); // 7 + 2

        assertThingsWereLitOnFire(gameTestHelper, startPos, creeper);

        ItemStack currentItemStack = player.getMainHandItem();
        int currentMaxRemainingUses = Objects.requireNonNull(currentItemStack.get(GTDataComponentTypes.MAX_REMAINING_USES));
        int currentRemainingUses = Objects.requireNonNull(currentItemStack.get(GTDataComponentTypes.REMAINING_USES));

        gameTestHelper.assertTrue(currentItemStack.is(deferredLighterItem), "Item in hand changed");
        GTGameTestUtils.assertEquals(gameTestHelper, currentMaxRemainingUses, initialMaxRemainingUses,
            "Max remaining uses changed");

        // 7 things have been lit on fire in total, uses on already lit targets don't reduce the remainingUses
        // (plus 2 additional wasted uses on a creeper that reduces the durability of a vanilla flint and steel too)
        gameTestHelper.assertValueEqual(currentRemainingUses, initialRemainingUses - 7 - 2,
            "lighter's remaining uses");

        gameTestHelper.succeed();
    }

    private static void doTest_ClickOnThings_SingleUse(GameTestHelper gameTestHelper,
                                                       DeferredItem<Item> deferredLighterItem) {
        Holder<Item> breaksInto = deferredLighterItem.toStack().get(GTDataComponentTypes.BREAKS_INTO);
        gameTestHelper.assertTrue(breaksInto != null && breaksInto.value() == Items.AIR,
            "This test was made for the lighters that break into the AIR item only");

        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        BlockPos startPos = new BlockPos(1, 2, 1);
        placeThings(gameTestHelper, startPos);

        giveANewLighter(deferredLighterItem, player);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos); // 1
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(2)); // 2
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(2));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(2));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(4)); // 3
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(4));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(4));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(6)); // 4
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(6));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.east(6));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.below().east(8)); // 5
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.below().east(8));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.below().east(8));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.south(2)); // 6
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.south(2));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, startPos.south(2));
        assertLighterExists(gameTestHelper, deferredLighterItem, player);

        Creeper creeper = gameTestHelper.findOneEntity(EntityType.CREEPER);

        player.interactOn(creeper, InteractionHand.MAIN_HAND); // 7
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);
        player.interactOn(creeper, InteractionHand.MAIN_HAND); // 7 + 1
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);
        player.interactOn(creeper, InteractionHand.MAIN_HAND); // 7 + 2
        assertLighterDoesNotExistAndGiveANewLighter(gameTestHelper, deferredLighterItem, player);

        assertThingsWereLitOnFire(gameTestHelper, startPos, creeper);

        gameTestHelper.succeed();
    }

    private static void placeThings(GameTestHelper gameTestHelper, BlockPos startPos) {
        gameTestHelper.setBlock(startPos, Blocks.TNT);
        gameTestHelper.setBlock(startPos.east(2),
            Blocks.PURPLE_CANDLE_CAKE.defaultBlockState().setValue(CandleCakeBlock.LIT, false));
        gameTestHelper.setBlock(startPos.east(4),
            Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, false));
        gameTestHelper.setBlock(startPos.east(6),
            Blocks.SOUL_CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, false));
        gameTestHelper.setBlock(startPos.south(2),
            Blocks.PURPLE_CANDLE.defaultBlockState().setValue(CandleBlock.LIT, false));
        gameTestHelper.spawn(EntityType.CREEPER, startPos.south(4));
    }

    private static void assertThingsWereLitOnFire(GameTestHelper gameTestHelper, BlockPos startPos, Creeper creeper) {
        GTGameTestUtils.assertEntityPresent(gameTestHelper, EntityType.TNT,
            "Expected TNT entity be present after lighting up the TNT");
        gameTestHelper.assertBlockState(startPos.east(2),
            Predicate.isEqual(Blocks.PURPLE_CANDLE_CAKE.defaultBlockState().setValue(CandleCakeBlock.LIT, true)),
            () -> "Expected the candle cake to be lit");
        gameTestHelper.assertBlockState(startPos.east(4),
            Predicate.isEqual(Blocks.CAMPFIRE.defaultBlockState().setValue(CandleCakeBlock.LIT, true)),
            () -> "Expected the campfire to be lit");
        gameTestHelper.assertBlockState(startPos.east(6),
            Predicate.isEqual(Blocks.SOUL_CAMPFIRE.defaultBlockState().setValue(CandleCakeBlock.LIT, true)),
            () -> "Expected the soul campfire to be lit");
        gameTestHelper.assertBlock(startPos.east(8),
            Predicate.isEqual(Blocks.FIRE),
            () -> "Expected the fire block to appear");
        gameTestHelper.assertBlockState(startPos.south(2),
            Predicate.isEqual(Blocks.PURPLE_CANDLE.defaultBlockState().setValue(CandleCakeBlock.LIT, true)),
            () -> "Expected the candle to be lit");
        gameTestHelper.assertTrue(creeper.isIgnited(), "Expected creeper to be ignited");
    }

    private static ItemStack giveANewLighter(DeferredItem<Item> deferredLighterItem, Player player) {
        ItemStack stack = deferredLighterItem.toStack();
        stack.set(GTDataComponentTypes.PROC_CHANCE, 1.0); // Set to 1.0 for convenience
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        return stack;
    }

    private static void assertLighterDoesNotExist(GameTestHelper gameTestHelper, Player player) {
        gameTestHelper.assertTrue(player.getMainHandItem().is(Items.AIR),
            "Expected the lighter item to not be present");
    }

    private static void assertLighterExists(GameTestHelper gameTestHelper, DeferredItem<Item> deferredLighterItem, Player player) {
        gameTestHelper.assertTrue(player.getMainHandItem().is(deferredLighterItem),
            "Expected the lighter item to be present");
    }

    private static void assertLighterDoesNotExistAndGiveANewLighter(GameTestHelper gameTestHelper,
                                                                    DeferredItem<Item> deferredLighterItem,
                                                                    Player player) {
        assertLighterDoesNotExist(gameTestHelper, player);
        giveANewLighter(deferredLighterItem, player);
    }
}
