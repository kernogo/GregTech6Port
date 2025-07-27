package ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal;

import lombok.AllArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import ru.kernogo.gregtech6port.features.items.like.spray.behaviors.ISprayBehavior;
import ru.kernogo.gregtech6port.utils.GTUtils;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.Map;

/** Behavior for sprays that paint blocks/entities (change blocks/entities to their colored variants) */
@AllArgsConstructor
public final class PaintSprayBehavior implements ISprayBehavior {
    private final ColoredSimpleBlockDeterminer coloredSimpleBlockDeterminer = new ColoredSimpleBlockDeterminer();
    private final EntityColorers entityColorers = new EntityColorers();
    private final DyeColor sprayDyeColor;

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

        Block simpleBlockToColorTo = coloredSimpleBlockDeterminer.getColoredBlockVariant(clickedBlockState.getBlock(), sprayDyeColor);

        if (simpleBlockToColorTo == null || simpleBlockToColorTo == clickedBlockState.getBlock()) {
            return new ISprayUseResult.CouldNotBeUsedOnThisThing();
        }

        placeASimpleBlock(simpleBlockToColorTo, clickedBlockState.getValues(), level, clickedPos);

        return new ISprayUseResult.WasUsed(usesToDecrease);
    }

    @Override
    public ISprayUseResult useOnEntity(int availableNumberOfUses,
                                       ItemStack stack,
                                       Player player,
                                       Entity interactionTarget,
                                       InteractionHand usedHand) {
        if (availableNumberOfUses < 0) {
            throw new GTUnexpectedValidationFailException("Unexpected value of availableNumberOfUses (must be > 0): " + availableNumberOfUses);
        }

        final int usesToDecrease = 5; // For entities, we always consume 5 uses

        if (availableNumberOfUses < usesToDecrease) {
            return new ISprayUseResult.WasNotUsedDueToInsufficientUses(usesToDecrease);
        }

        boolean wasEntityColored = false;

        for (EntityColorers.IEntityColorer entityColorer : entityColorers.getAllEntityColorers()) {
            if (entityColorer.isApplicableEntityClass(interactionTarget)) {
                wasEntityColored = entityColorer.colorAnEntity(interactionTarget, player, sprayDyeColor);
                break;
            }
        }

        if (!wasEntityColored) {
            return new ISprayUseResult.CouldNotBeUsedOnThisThing();
        } else {
            return new ISprayUseResult.WasUsed(usesToDecrease);
        }
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
