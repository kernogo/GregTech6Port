package ru.kernogo.gregtech6port.gametests.feature.items.like.spray.paint_and_paint_removal;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.gametest.GameTestHolder;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringCapability;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringData;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A set of tests to check the Paint Spray Can behavior on tint colorable BlockEntities <br>
 * (see {@link ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringSystem})
 */
@GameTestHolder(GregTech6Port.MODID)
public class PaintSprayTintColoringTests {
    /** Test the coloring of Ender Garbage Bin */
    @GameTestGenerator
    public static Collection<TestFunction> test_TintColoring_EnderGarbageBin() {
        return List.of(
            GTGameTestUtils.makeTestFunction(
                null,
                "gametest_bedrock_1x2x1",
                PaintSprayTintColoringTests::doTest_TintColoring_EnderGarbageBin
            )
        );
    }

    private static void doTest_TintColoring_EnderGarbageBin(GameTestHelper gameTestHelper) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        BlockPos posToClick = new BlockPos(0, 2, 0);
        BlockPos absolutePosToClick = gameTestHelper.absolutePos(posToClick);

        gameTestHelper.setBlock(posToClick, GTBlocks.ENDER_GARBAGE_BIN.get());

        GTTintColoringCapability tintColoringCapability = gameTestHelper.getLevel().getCapability(GTCapabilities.TINT_COLORING, absolutePosToClick);
        GTGameTestUtils.assertNotNull(gameTestHelper, tintColoringCapability,
            "This test was made for BlockEntities with a TINT_COLORING capability, but this block entity does not have it"
        );

        player.setItemInHand(InteractionHand.MAIN_HAND, GTItems.RED_PAINT_SPRAY_CAN.toStack());
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);

        tintColoringCapability = gameTestHelper.getLevel().getCapability(GTCapabilities.TINT_COLORING, absolutePosToClick);
        gameTestHelper.assertTrue(Objects.requireNonNull(tintColoringCapability).getTintColoringData() != null,
            "Tint coloring data is not present, but it should be");
        gameTestHelper.assertValueEqual(
            tintColoringCapability.getTintColoringData(),
            GTTintColoringData.createFromArgbComponents(255, 255, 0, 0), // Red spray color
            "argb color"
        );

        player.setItemInHand(InteractionHand.MAIN_HAND, GTItems.BLUE_PAINT_SPRAY_CAN.toStack());
        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);

        tintColoringCapability = gameTestHelper.getLevel().getCapability(GTCapabilities.TINT_COLORING, absolutePosToClick);
        gameTestHelper.assertTrue(Objects.requireNonNull(tintColoringCapability).getTintColoringData() != null,
            "Tint coloring data is not present, but it should be");
        gameTestHelper.assertValueEqual(
            tintColoringCapability.getTintColoringData(),
            GTTintColoringData.createFromArgbComponents(255, 127, 0, 127), // Mix of red and blue spray colors
            "argb color"
        );

        gameTestHelper.succeed();
    }
}
