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
import ru.kernogo.gregtech6port.features.items.like.spray.behaviors.ISprayBehavior;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/** Behavior for sprays that paint blocks/entities (change blocks/entities to their colored variants) */
@AllArgsConstructor
public final class PaintSprayBehavior implements ISprayBehavior {
    private final EntityColorers entityColorers = new EntityColorers();
    private final TintColoringBlockEntityColorer tintColoringBlockEntityColorer = new TintColoringBlockEntityColorer();
    private final SimpleBlockColorer simpleBlockColorer = new SimpleBlockColorer();
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

        boolean wasBlockColored = tintColoringBlockEntityColorer.tryColoringABlock(clickedPos, level, sprayDyeColor);
        if (wasBlockColored) {
            return new ISprayUseResult.WasUsed(usesToDecrease);
        }

        wasBlockColored = simpleBlockColorer.tryColoringABlock(clickedPos, level, sprayDyeColor);
        if (wasBlockColored) {
            return new ISprayUseResult.WasUsed(usesToDecrease);
        }

        return new ISprayUseResult.CouldNotBeUsedOnThisThing();
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
                wasEntityColored = entityColorer.tryColoringAnEntity(interactionTarget, player, sprayDyeColor);
                break;
            }
        }

        if (!wasEntityColored) {
            return new ISprayUseResult.CouldNotBeUsedOnThisThing();
        } else {
            return new ISprayUseResult.WasUsed(usesToDecrease);
        }
    }
}
