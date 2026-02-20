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
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringForBlocks;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A set of tests to check the Paint Removal Spray Can behavior on tint colorable BlockEntities <br>
 * (see {@link GTTintColoringForBlocks})
 */
@GameTestHolder(GregTech6Port.MODID)
public class PaintRemovalSprayTintColoringTests {
    /** Test the uncoloring of Ender Garbage Bin */
    @GameTestGenerator
    public static Collection<TestFunction> test_TintUncoloring_EnderGarbageBin() {
        return List.of(
            GTGameTestUtils.makeTestFunction(
                null,
                "gametest_bedrock_1x2x1",
                PaintRemovalSprayTintColoringTests::doTest_TintUncoloring_EnderGarbageBin
            )
        );
    }

    private static void doTest_TintUncoloring_EnderGarbageBin(GameTestHelper gameTestHelper) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, GTItems.PAINT_REMOVAL_SPRAY_CAN.toStack());
        BlockPos posToClick = new BlockPos(0, 2, 0);
        BlockPos absolutePosToClick = gameTestHelper.absolutePos(posToClick);

        gameTestHelper.setBlock(posToClick, GTBlocks.ENDER_GARBAGE_BIN.get());

        GTTintColoringCapability tintColoringCapability = gameTestHelper.getLevel().getCapability(GTCapabilities.TINT_COLORING, absolutePosToClick);
        GTGameTestUtils.assertNotNull(gameTestHelper, tintColoringCapability,
            "This test was made for BlockEntities with a TINT_COLORING capability, but this block entity does not have it"
        );
        gameTestHelper.assertTrue(Objects.requireNonNull(tintColoringCapability).getTintColoringData() == null,
            "Ender Garbage Bin's initial tint coloring was not null, which is unexpected");

        // First check the use of the paint removal spray on an uncolored block

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);

        tintColoringCapability = gameTestHelper.getLevel().getCapability(GTCapabilities.TINT_COLORING, absolutePosToClick);
        GTGameTestUtils.assertNull(gameTestHelper, Objects.requireNonNull(tintColoringCapability).getTintColoringData(),
            "Tint coloring data is not null");

        // Then color the block and check the use of the paint removal spray on it

        Objects.requireNonNull(tintColoringCapability)
            .setTintColoringDataAndUpdate(GTTintColoringData.createFromArgbComponents(255, 128, 0, 128));

        GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);

        tintColoringCapability = gameTestHelper.getLevel().getCapability(GTCapabilities.TINT_COLORING, absolutePosToClick);
        GTGameTestUtils.assertNull(gameTestHelper, Objects.requireNonNull(tintColoringCapability).getTintColoringData(),
            "Tint coloring data is not null");

        gameTestHelper.succeed();
    }
}
