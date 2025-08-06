package ru.kernogo.gregtech6port.gametests.feature.items.like.spray.paint_and_paint_removal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.gametest.GameTestHolder;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.gametests.GTItemWithUsesGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/** A set of tests to check the Paint Removal Spray Can behavior on blocks */
@GameTestHolder(GregTech6Port.MODID)
public class PaintRemovalSprayUseOnBlocksTests {
    /** Test the uncoloring of a simple CANDLE block (it must keep its HORIZONTAL_FACING property) */
    @GameTestGenerator
    public static Collection<TestFunction> test_SimpleBlock_Candle() {
        return List.of(
            GTGameTestUtils.makeTestFunction(
                null,
                "gametest_bedrock_1x2x1",
                PaintRemovalSprayUseOnBlocksTests::doTest_SimpleBlock_Candle
            )
        );
    }

    /** Test the clicking on a non-colorable block */
    @GameTestGenerator
    public static Collection<TestFunction> test_SimpleBlock_NonColorableBlock() {
        return List.of(
            GTGameTestUtils.makeTestFunction(
                null,
                "gametest_bedrock_1x2x1",
                PaintRemovalSprayUseOnBlocksTests::doTest_SimpleBlock_NonColorableBlock
            )
        );
    }

    private static void doTest_SimpleBlock_Candle(GameTestHelper gameTestHelper) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, GTItems.PAINT_REMOVAL_SPRAY_CAN.toStack());

        int initialRemainingUses = GTItemWithUsesGameTestUtils.getRemainingUses(player.getMainHandItem());

        BlockPos posToClick = new BlockPos(0, 2, 0);
        gameTestHelper.setBlock(posToClick, Blocks.PURPLE_CANDLE.defaultBlockState()
            .setValue(BlockStateProperties.CANDLES, 3));

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);

        gameTestHelper.assertBlockState(posToClick,
            Predicate.isEqual(Blocks.CANDLE.defaultBlockState().setValue(BlockStateProperties.CANDLES, 3)),
            () -> "BlockState changed to a wrong one or didn't change");

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

    private static void doTest_SimpleBlock_NonColorableBlock(GameTestHelper gameTestHelper) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, GTItems.PAINT_REMOVAL_SPRAY_CAN.toStack());

        int initialRemainingUses = GTItemWithUsesGameTestUtils.getRemainingUses(player.getMainHandItem());

        BlockPos posToClick = new BlockPos(0, 2, 0);
        BlockState nonColorableBlockState = Blocks.SPRUCE_STAIRS.defaultBlockState()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST);
        gameTestHelper.setBlock(posToClick, nonColorableBlockState);

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);

        gameTestHelper.assertBlockState(posToClick, Predicate.isEqual(nonColorableBlockState),
            () -> "BlockState should not have changed");

        GTItemWithUsesGameTestUtils.assertRemainingUsesEquals(
            gameTestHelper, player.getMainHandItem(), initialRemainingUses
        );

        gameTestHelper.succeed();
    }
}