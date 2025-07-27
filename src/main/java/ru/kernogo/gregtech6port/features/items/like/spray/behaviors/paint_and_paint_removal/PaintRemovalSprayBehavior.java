package ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import ru.kernogo.gregtech6port.features.items.like.spray.behaviors.ISprayBehavior;
import ru.kernogo.gregtech6port.utils.GTUtils;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.Map;

/** Behavior for the paint removal spray (spray that changes blocks into the unpainted variants) */
public final class PaintRemovalSprayBehavior implements ISprayBehavior {
    private final ColoredSimpleBlockDeterminer coloredSimpleBlockDeterminer = new ColoredSimpleBlockDeterminer();

    @Override
    public ISprayUseResult useOnBlock(int availableNumberOfUses, UseOnContext context) {
        if (availableNumberOfUses < 0) {
            throw new GTUnexpectedValidationFailException("Unexpected value of availableNumberOfUses (must be > 0): " + availableNumberOfUses);
        }

        final int usesToDecrease = 1; // For entities, we always consume 1 use

        if (availableNumberOfUses < usesToDecrease) {
            return new ISprayUseResult.WasNotUsedDueToInsufficientUses(usesToDecrease);
        }

        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState clickedBlockState = level.getBlockState(clickedPos);

        Block simpleBlockToColorTo = coloredSimpleBlockDeterminer.getUncoloredBlockVariant(clickedBlockState.getBlock());

        if (simpleBlockToColorTo == null || simpleBlockToColorTo == clickedBlockState.getBlock()) {
            return new ISprayUseResult.CouldNotBeUsedOnThisThing();
        }

        placeASimpleBlock(simpleBlockToColorTo, clickedBlockState.getValues(), level, clickedPos);

        return new ISprayUseResult.WasUsed(usesToDecrease);
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
