package ru.kernogo.gregtech6port.features.items.like.spray.behavior;

import net.minecraft.world.item.context.UseOnContext;

/** Delegate defining the action on spray-like item's use */
public interface SprayBehaviorDelegate {
    /** Returns true if the spray can be used on a block */
    boolean canUseOnBlock(UseOnContext context);

    /** Uses spray on a block */
    void doUseOnBlock(UseOnContext context);
}
