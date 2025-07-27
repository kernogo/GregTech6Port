package ru.kernogo.gregtech6port.features.items.like.spray.behaviors;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

/** Delegate defining the action on spray-like item's use */
public interface ISprayBehavior {
    /**
     * Uses spray on a block. Returns a {@link ISprayUseResult}. <br>
     * {@code availableNumberOfUses} is the max number of uses that can be used during this particular method call. <br>
     * By default, if not implemented, does nothing and returns {@link ISprayUseResult.NotSupported}
     */
    default ISprayUseResult useOnBlock(int availableNumberOfUses, UseOnContext context) {
        return new ISprayUseResult.NotSupported();
    }

    /**
     * Uses spray on an entity. Returns a {@link ISprayUseResult}. <br>
     * {@code availableNumberOfUses} is the max number of uses that can be used during this particular method call. <br>
     * By default, if not implemented, does nothing and returns {@link ISprayUseResult.NotSupported}
     */
    default ISprayUseResult useOnEntity(int availableNumberOfUses,
                                        ItemStack stack,
                                        Player player,
                                        Entity interactionTarget,
                                        InteractionHand usedHand) {
        return new ISprayUseResult.NotSupported();
    }

    /**
     * Represents a result of {@link #useOnBlock} or {@link #useOnEntity}. <br>
     * In this context, "block/entity" means blocks for the result of {@link #useOnBlock} or entities for the result of {@link #useOnEntity}.
     */
    sealed interface ISprayUseResult
        permits ISprayUseResult.NotSupported, ISprayUseResult.WasNotUsedDueToInsufficientUses,
        ISprayUseResult.CouldNotBeUsedOnThisThing, ISprayUseResult.WasUsed {
        /** If spray can't be used on blocks/entities at all (use operation for blocks/entities is not implemented) */
        record NotSupported() implements ISprayUseResult {}

        /**
         * If spray can be used on blocks/entities in general, but it can't be used on a particular block/entity
         *
         * @param minNumberOfUsesRequired Minimum number of uses that is required
         */
        record WasNotUsedDueToInsufficientUses(int minNumberOfUsesRequired) implements ISprayUseResult {}

        /** If spray can be used on blocks/entities in general, but it could not be used on a particular block/entity */
        record CouldNotBeUsedOnThisThing() implements ISprayUseResult {}

        /**
         * If spray was actually used on a block/entity (partially or not)
         *
         * @param numUsesToDecrease Number of uses that will need to be removed from remainingUses as a result
         */
        record WasUsed(int numUsesToDecrease) implements ISprayUseResult {}
    }
}
