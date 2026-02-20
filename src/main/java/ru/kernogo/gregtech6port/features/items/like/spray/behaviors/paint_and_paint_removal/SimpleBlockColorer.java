package ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import ru.kernogo.gregtech6port.utils.GTUtils;

import java.util.Map;

/**
 * Colors or uncolors Simple Blocks (Blocks without BlockEntities).
 * Uses the {@link ColoredSimpleBlockDeterminer} to find the colored variant of blocks.
 *
 * @see ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringForBlocks
 */
final class SimpleBlockColorer {
    SimpleBlockColorer() {}

    private final ColoredSimpleBlockDeterminer coloredSimpleBlockDeterminer = new ColoredSimpleBlockDeterminer();

    /** Tries to do the coloring of a block at the position {@code pos}. Returns true if the coloring was done and false otherwise. */
    boolean tryColoringABlock(BlockPos pos, Level level, DyeColor dyeColor) {
        // Do not allow the coloring of Blocks with BlockEntities
        if (level.getBlockEntity(pos) != null) {
            return false;
        }

        BlockState blockState = level.getBlockState(pos);

        Block simpleBlockToColorTo = coloredSimpleBlockDeterminer.getColoredBlockVariant(blockState.getBlock(), dyeColor);

        if (simpleBlockToColorTo == null || simpleBlockToColorTo == blockState.getBlock()) {
            return false;
        }

        placeASimpleBlock(simpleBlockToColorTo, blockState.getValues(), level, pos);

        return true;
    }

    /** Tries to do the uncoloring of a block at the position {@code pos}. Returns true if the uncoloring was done and false otherwise. */
    boolean tryUncoloringABlock(BlockPos pos, Level level) {
        // Do not allow the uncoloring of Blocks with BlockEntities
        if (level.getBlockEntity(pos) != null) {
            return false;
        }

        BlockState blockState = level.getBlockState(pos);

        Block simpleBlockToColorTo = coloredSimpleBlockDeterminer.getUncoloredBlockVariant(blockState.getBlock());

        if (simpleBlockToColorTo == null || simpleBlockToColorTo == blockState.getBlock()) {
            return false;
        }

        placeASimpleBlock(simpleBlockToColorTo, blockState.getValues(), level, pos);

        return true;
    }

    /**
     * Place a Block {@code blockToPlace} with copied BlockState properties {@code blockStatePropertiesToCopy}
     * into BlockPos {@code posToPlaceTheBlock}
     */
    private void placeASimpleBlock(Block blockToPlace,
                                   Map<Property<?>, Comparable<?>> blockStatePropertiesToCopy,
                                   Level level,
                                   BlockPos posToPlaceTheBlock) {
        BlockState newBlockState = GTUtils.getBlockStateWithAllPropertiesCopiedOrFail(
            blockToPlace.defaultBlockState(),
            blockStatePropertiesToCopy
        );

        level.setBlock(posToPlaceTheBlock, newBlockState, Block.UPDATE_ALL_IMMEDIATE);
    }
}
