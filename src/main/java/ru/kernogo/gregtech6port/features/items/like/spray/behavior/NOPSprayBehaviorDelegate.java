package ru.kernogo.gregtech6port.features.items.like.spray.behavior;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.world.item.context.UseOnContext;

/** Spray behavior that does nothing */
@Slf4j
public final class NOPSprayBehaviorDelegate implements SprayBehaviorDelegate {
    @Override
    public boolean canUseOnBlock(UseOnContext context) {
        return true;
    }

    @Override
    public void doUseOnBlock(UseOnContext context) {
        // Nothing
    }
}
