package ru.kernogo.gregtech6port.gametests.feature.items.like.spray.paint_and_paint_removal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.tuple.Triple;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.gametests.GTItemWithUsesGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.List;
import java.util.function.Predicate;

/** A set of tests to check the Paint Spray Can behavior on blocks */
@EventBusSubscriber
public class PaintSprayUseOnBlocksTests {
    /** Test the coloring of a simple GLAZED_TERRACOTTA block (it must keep its HORIZONTAL_FACING property) */
    @SubscribeEvent
    public static void tests_SimpleBlock_GlazedTerracotta(RegisterGameTestsEvent event) {
        // Paint spray can; paint spray can color; expected result block
        for (Triple<DeferredItem<Item>, DyeColor, Block> testCase : List.of(
            Triple.of(GTItems.WHITE_PAINT_SPRAY_CAN, DyeColor.WHITE, Blocks.WHITE_GLAZED_TERRACOTTA),
            Triple.of(GTItems.ORANGE_PAINT_SPRAY_CAN, DyeColor.ORANGE, Blocks.ORANGE_GLAZED_TERRACOTTA),
            Triple.of(GTItems.MAGENTA_PAINT_SPRAY_CAN, DyeColor.MAGENTA, Blocks.MAGENTA_GLAZED_TERRACOTTA),
            Triple.of(GTItems.LIGHT_BLUE_PAINT_SPRAY_CAN, DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA),
            Triple.of(GTItems.YELLOW_PAINT_SPRAY_CAN, DyeColor.YELLOW, Blocks.YELLOW_GLAZED_TERRACOTTA),
            Triple.of(GTItems.LIME_PAINT_SPRAY_CAN, DyeColor.LIME, Blocks.LIME_GLAZED_TERRACOTTA),
            Triple.of(GTItems.PINK_PAINT_SPRAY_CAN, DyeColor.PINK, Blocks.PINK_GLAZED_TERRACOTTA),
            Triple.of(GTItems.GRAY_PAINT_SPRAY_CAN, DyeColor.GRAY, Blocks.GRAY_GLAZED_TERRACOTTA),
            Triple.of(GTItems.LIGHT_GRAY_PAINT_SPRAY_CAN, DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA),
            Triple.of(GTItems.CYAN_PAINT_SPRAY_CAN, DyeColor.CYAN, Blocks.CYAN_GLAZED_TERRACOTTA),
            Triple.of(GTItems.PURPLE_PAINT_SPRAY_CAN, DyeColor.PURPLE, Blocks.PURPLE_GLAZED_TERRACOTTA),
            Triple.of(GTItems.BLUE_PAINT_SPRAY_CAN, DyeColor.BLUE, Blocks.BLUE_GLAZED_TERRACOTTA),
            Triple.of(GTItems.BROWN_PAINT_SPRAY_CAN, DyeColor.BROWN, Blocks.BROWN_GLAZED_TERRACOTTA),
            Triple.of(GTItems.GREEN_PAINT_SPRAY_CAN, DyeColor.GREEN, Blocks.GREEN_GLAZED_TERRACOTTA),
            Triple.of(GTItems.RED_PAINT_SPRAY_CAN, DyeColor.RED, Blocks.RED_GLAZED_TERRACOTTA),
            Triple.of(GTItems.BLACK_PAINT_SPRAY_CAN, DyeColor.BLACK, Blocks.BLACK_GLAZED_TERRACOTTA)
        )) {
            DeferredItem<Item> deferredItem = testCase.getLeft();
            DyeColor sprayColor = testCase.getMiddle();
            Block block = testCase.getRight();

            GTGameTestUtils.registerTestFunction(
                event,
                testCase.getLeft().getKey().location().getPath(),
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_SimpleBlock_GlazedTerracotta(
                    gameTestHelper, deferredItem, sprayColor, block
                )
            );
        }
    }

    /** Test the clicking on a non-colorable block */
    @SubscribeEvent
    public static void tests_SimpleBlock_NonColorableBlock(RegisterGameTestsEvent event) {
        for (DeferredItem<Item> deferredItem : List.of(
            GTItems.WHITE_PAINT_SPRAY_CAN,
            GTItems.ORANGE_PAINT_SPRAY_CAN,
            GTItems.MAGENTA_PAINT_SPRAY_CAN,
            GTItems.LIGHT_BLUE_PAINT_SPRAY_CAN,
            GTItems.YELLOW_PAINT_SPRAY_CAN,
            GTItems.LIME_PAINT_SPRAY_CAN,
            GTItems.PINK_PAINT_SPRAY_CAN,
            GTItems.GRAY_PAINT_SPRAY_CAN,
            GTItems.LIGHT_GRAY_PAINT_SPRAY_CAN,
            GTItems.CYAN_PAINT_SPRAY_CAN,
            GTItems.PURPLE_PAINT_SPRAY_CAN,
            GTItems.BLUE_PAINT_SPRAY_CAN,
            GTItems.BROWN_PAINT_SPRAY_CAN,
            GTItems.GREEN_PAINT_SPRAY_CAN,
            GTItems.RED_PAINT_SPRAY_CAN,
            GTItems.BLACK_PAINT_SPRAY_CAN
        )) {
            GTGameTestUtils.registerTestFunction(
                event,
                deferredItem.getKey().location().getPath(),
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_SimpleBlock_NonColorableBlock(gameTestHelper, deferredItem)
            );
        }
    }

    private static void doTest_SimpleBlock_GlazedTerracotta(GameTestHelper gameTestHelper,
                                                            DeferredItem<Item> deferredSprayCanItem,
                                                            DyeColor dyeColor,
                                                            Block expectedResultBlock) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, deferredSprayCanItem.toStack());

        int initialRemainingUses = GTItemWithUsesGameTestUtils.getRemainingUses(player.getMainHandItem());

        BlockPos posToClick = new BlockPos(0, 1, 0);
        BlockState initialBlockState = dyeColor != DyeColor.PURPLE ?
            Blocks.PURPLE_GLAZED_TERRACOTTA.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST) :
            Blocks.MAGENTA_GLAZED_TERRACOTTA.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST);
        gameTestHelper.setBlock(posToClick, initialBlockState);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);

        BlockState expectedBlockState = expectedResultBlock.defaultBlockState()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST);
        GTGameTestUtils.assertBlockState(gameTestHelper,
            posToClick, Predicate.isEqual(expectedBlockState),
            "BlockState changed to a wrong one or didn't change");

        GTItemWithUsesGameTestUtils.assertRemainingUsesEquals(
            gameTestHelper, player.getMainHandItem(), initialRemainingUses - 1
        );

        // Additional uses should not do anything
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        GTItemWithUsesGameTestUtils.assertRemainingUsesEquals(
            gameTestHelper, player.getMainHandItem(), initialRemainingUses - 1
        );

        gameTestHelper.succeed();
    }

    private static void doTest_SimpleBlock_NonColorableBlock(GameTestHelper gameTestHelper,
                                                             DeferredItem<Item> deferredSprayCanItem) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, deferredSprayCanItem.toStack());

        int initialRemainingUses = GTItemWithUsesGameTestUtils.getRemainingUses(player.getMainHandItem());

        BlockPos posToClick = new BlockPos(0, 1, 0);
        BlockState nonColorableBlockState = Blocks.SPRUCE_STAIRS.defaultBlockState()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST);
        gameTestHelper.setBlock(posToClick, nonColorableBlockState);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);

        GTGameTestUtils.assertBlockState(gameTestHelper,
            posToClick, Predicate.isEqual(nonColorableBlockState),
            "BlockState should not have changed");

        GTItemWithUsesGameTestUtils.assertRemainingUsesEquals(
            gameTestHelper, player.getMainHandItem(), initialRemainingUses
        );

        gameTestHelper.succeed();
    }
}
