package ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import ru.kernogo.gregtech6port.features.items.like.spray.behaviors.ISprayBehavior;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/** Behavior for the paint removal spray (spray that changes blocks/entities into the unpainted variants) */
public final class PaintRemovalSprayBehavior implements ISprayBehavior {
    private final SimpleBlockColorer simpleBlockColorer = new SimpleBlockColorer();
    private final TintColoringBlockEntityColorer tintColoringBlockEntityColorer = new TintColoringBlockEntityColorer();

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

        boolean wasBlockUncolored = tintColoringBlockEntityColorer.tryUncoloringABlock(clickedPos, level);
        if (wasBlockUncolored) {
            return new ISprayUseResult.WasUsed(usesToDecrease);
        }

        wasBlockUncolored = simpleBlockColorer.tryUncoloringABlock(clickedPos, level);
        if (wasBlockUncolored) {
            return new ISprayUseResult.WasUsed(usesToDecrease);
        }

        return new ISprayUseResult.CouldNotBeUsedOnThisThing();
    }
}
