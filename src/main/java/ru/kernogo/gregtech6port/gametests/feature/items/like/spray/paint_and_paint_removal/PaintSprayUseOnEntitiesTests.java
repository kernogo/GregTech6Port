package ru.kernogo.gregtech6port.gametests.feature.items.like.spray.paint_and_paint_removal;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.tuple.Pair;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.gametests.GTItemWithUsesGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/** A set of tests to check the Paint Spray Can behavior on entities */
@GameTestHolder(GregTech6Port.MODID)
public class PaintSprayUseOnEntitiesTests {
    /** Test the painting of colorable entities */
    @GameTestGenerator
    public static Collection<TestFunction> tests_ColorableEntities() {
        ArrayList<TestFunction> tests = new ArrayList<>();
        // Paint spray can; paint spray can color
        for (Pair<DeferredItem<Item>, DyeColor> testCase : List.of(
            Pair.of(GTItems.WHITE_PAINT_SPRAY_CAN, DyeColor.WHITE),
            Pair.of(GTItems.ORANGE_PAINT_SPRAY_CAN, DyeColor.ORANGE),
            Pair.of(GTItems.MAGENTA_PAINT_SPRAY_CAN, DyeColor.MAGENTA),
            Pair.of(GTItems.LIGHT_BLUE_PAINT_SPRAY_CAN, DyeColor.LIGHT_BLUE),
            Pair.of(GTItems.YELLOW_PAINT_SPRAY_CAN, DyeColor.YELLOW),
            Pair.of(GTItems.LIME_PAINT_SPRAY_CAN, DyeColor.LIME),
            Pair.of(GTItems.PINK_PAINT_SPRAY_CAN, DyeColor.PINK),
            Pair.of(GTItems.GRAY_PAINT_SPRAY_CAN, DyeColor.GRAY),
            Pair.of(GTItems.LIGHT_GRAY_PAINT_SPRAY_CAN, DyeColor.LIGHT_GRAY),
            Pair.of(GTItems.CYAN_PAINT_SPRAY_CAN, DyeColor.CYAN),
            Pair.of(GTItems.PURPLE_PAINT_SPRAY_CAN, DyeColor.PURPLE),
            Pair.of(GTItems.BLUE_PAINT_SPRAY_CAN, DyeColor.BLUE),
            Pair.of(GTItems.BROWN_PAINT_SPRAY_CAN, DyeColor.BROWN),
            Pair.of(GTItems.GREEN_PAINT_SPRAY_CAN, DyeColor.GREEN),
            Pair.of(GTItems.RED_PAINT_SPRAY_CAN, DyeColor.RED),
            Pair.of(GTItems.BLACK_PAINT_SPRAY_CAN, DyeColor.BLACK)
        )) {
            DeferredItem<Item> deferredItem = testCase.getLeft();
            DyeColor sprayColor = testCase.getRight();

            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath() + "_colorable_sheep",
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_Colorable_Sheep(gameTestHelper, deferredItem, sprayColor)
            ));
            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath() + "_colorable_wolf",
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_Colorable_Wolf(gameTestHelper, deferredItem, sprayColor)
            ));
            tests.add(GTGameTestUtils.makeTestFunction(
                deferredItem.getKey().location().getPath() + "_colorable_cat",
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_Colorable_Cat(gameTestHelper, deferredItem, sprayColor)
            ));
        }
        return tests;
    }

    /** Test the painting of non-colorable entities */
    @GameTestGenerator
    public static Collection<TestFunction> tests_NonColorableEntities() {
        ArrayList<TestFunction> tests = new ArrayList<>();

        DeferredItem<Item> deferredItem = GTItems.PURPLE_PAINT_SPRAY_CAN;

        tests.add(GTGameTestUtils.makeTestFunction(
            deferredItem.getKey().location().getPath() + "_noncolorable_wolf",
            "gametest_bedrock_1x2x1",
            gameTestHelper -> doTest_NonColorable_Wolf(gameTestHelper, deferredItem)
        ));
        tests.add(GTGameTestUtils.makeTestFunction(
            deferredItem.getKey().location().getPath() + "_noncolorable_cat",
            "gametest_bedrock_1x2x1",
            gameTestHelper -> doTest_NonColorable_Cat(gameTestHelper, deferredItem)
        ));
        tests.add(GTGameTestUtils.makeTestFunction(
            deferredItem.getKey().location().getPath() + "_noncolorable_end_crystal",
            "gametest_bedrock_1x2x1",
            gameTestHelper -> doTest_NonColorable_EndCrystal(gameTestHelper, deferredItem)
        ));

        return tests;
    }

    private static void doTest_Colorable_Sheep(GameTestHelper gameTestHelper, DeferredItem<Item> deferredSprayCanItem, DyeColor sprayColor) {
        doTest_Colorable_Entity(gameTestHelper, deferredSprayCanItem,
            ignored -> {
                Sheep sheep = gameTestHelper.spawn(EntityType.SHEEP, new BlockPos(0, 2, 0));
                sheep.setColor(sprayColor != DyeColor.PURPLE ? DyeColor.PURPLE : DyeColor.MAGENTA);
                return sheep;
            },
            sheep -> sheep.getColor() == sprayColor);
    }

    private static void doTest_Colorable_Wolf(GameTestHelper gameTestHelper, DeferredItem<Item> deferredSprayCanItem, DyeColor sprayColor) {
        doTest_Colorable_Entity(gameTestHelper, deferredSprayCanItem,
            player -> {
                Wolf entity = gameTestHelper.spawn(EntityType.WOLF, new BlockPos(0, 2, 0));
                entity.setCollarColor(sprayColor != DyeColor.PURPLE ? DyeColor.PURPLE : DyeColor.MAGENTA);
                entity.tame(player);
                return entity;
            },
            wolf -> wolf.getCollarColor() == sprayColor);
    }

    private static void doTest_Colorable_Cat(GameTestHelper gameTestHelper, DeferredItem<Item> deferredSprayCanItem, DyeColor sprayColor) {
        doTest_Colorable_Entity(gameTestHelper, deferredSprayCanItem,
            player -> {
                Cat entity = gameTestHelper.spawn(EntityType.CAT, new BlockPos(0, 2, 0));
                entity.setCollarColor(sprayColor != DyeColor.PURPLE ? DyeColor.PURPLE : DyeColor.MAGENTA);
                entity.tame(player);
                return entity;
            },
            cat -> cat.getCollarColor() == sprayColor);
    }

    /** For entities that can be colored by a spray */
    private static <ENTITY extends Entity> void doTest_Colorable_Entity(GameTestHelper gameTestHelper,
                                                                        DeferredItem<Item> deferredSprayCanItem,
                                                                        Function<Player, ENTITY> spawnAnEntityToTest,
                                                                        Predicate<ENTITY> predicateToTest) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, deferredSprayCanItem.toStack());

        int initialRemainingUses = GTItemWithUsesGameTestUtils.getRemainingUses(player.getMainHandItem());

        ENTITY entity = spawnAnEntityToTest.apply(player);

        // Multiple uses should not do anything after the first use
        for (int i = 0; i < 5; i++) {
            player.interactOn(entity, InteractionHand.MAIN_HAND);

            gameTestHelper.assertTrue(predicateToTest.test(entity),
                "Entity doesn't pass a predicate");

            GTItemWithUsesGameTestUtils.assertRemainingUsesEquals(
                gameTestHelper, player.getMainHandItem(), initialRemainingUses - 5
            );
        }

        gameTestHelper.succeed();
    }

    /** Untamed wolf is non-colorable */
    private static void doTest_NonColorable_Wolf(GameTestHelper gameTestHelper, DeferredItem<Item> deferredSprayCanItem) {
        doTest_NonColorable_Entity(gameTestHelper, deferredSprayCanItem,
            player -> {
                Wolf entity = gameTestHelper.spawn(EntityType.WOLF, new BlockPos(0, 2, 0));
                entity.setCollarColor(DyeColor.WHITE);
                return entity;
            },
            wolf -> wolf.getCollarColor() == DyeColor.WHITE); // collar color should not change
    }

    /** Untamed cat is non-colorable */
    private static void doTest_NonColorable_Cat(GameTestHelper gameTestHelper, DeferredItem<Item> deferredSprayCanItem) {
        doTest_NonColorable_Entity(gameTestHelper, deferredSprayCanItem,
            player -> {
                Cat entity = gameTestHelper.spawn(EntityType.CAT, new BlockPos(0, 2, 0));
                entity.setCollarColor(DyeColor.WHITE);
                return entity;
            },
            cat -> cat.getCollarColor() == DyeColor.WHITE); // collar color should not change
    }

    /** End crystal is non-colorable */
    private static void doTest_NonColorable_EndCrystal(GameTestHelper gameTestHelper, DeferredItem<Item> deferredSprayCanItem) {
        doTest_NonColorable_Entity(gameTestHelper, deferredSprayCanItem,
            player -> gameTestHelper.spawn(EntityType.END_CRYSTAL, new BlockPos(0, 2, 0)),
            endCrystal -> true); // If it does not give an error, it's good enough
    }

    /** For entities that cannot be colored by a spray */
    private static <ENTITY extends Entity> void doTest_NonColorable_Entity(GameTestHelper gameTestHelper,
                                                                           DeferredItem<Item> deferredSprayCanItem,
                                                                           Function<Player, ENTITY> spawnAnEntityToTest,
                                                                           Predicate<ENTITY> predicateToTest) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, deferredSprayCanItem.toStack());

        int initialRemainingUses = GTItemWithUsesGameTestUtils.getRemainingUses(player.getMainHandItem());

        ENTITY entity = spawnAnEntityToTest.apply(player);

        // Nothing should happen on click. Multiple uses should not do anything either.
        for (int i = 0; i < 5; i++) {
            player.interactOn(entity, InteractionHand.MAIN_HAND);

            gameTestHelper.assertTrue(predicateToTest.test(entity),
                "Entity doesn't pass a predicate");

            GTItemWithUsesGameTestUtils.assertRemainingUsesEquals(
                gameTestHelper, player.getMainHandItem(), initialRemainingUses
            );
        }

        gameTestHelper.succeed();
    }
}
