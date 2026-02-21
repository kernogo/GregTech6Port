package ru.kernogo.gregtech6port.features.items.like.lighter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;

/** Behavior for lighter's use on entities */
final class LighterBehaviorForEntities {
    LighterBehaviorForEntities() {}

    @Nullable IThingToDo findWhatToDo(LivingEntity interactionTarget, Player player) {
        if (interactionTarget instanceof Creeper creeper) {
            return new IgniteCreeperThingToDo(creeper, player);
        }

        return null;
    }

    /**
     * Ignite a creeper <br>
     * Based on {@link net.minecraft.world.entity.monster.Creeper#mobInteract}
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class IgniteCreeperThingToDo implements IThingToDo {
        private final Creeper creeper;
        private final Player player;

        @Override
        public void lightAThingOnFire() {
            creeper.ignite();
        }

        @Override
        public void playLighterSound() {
            player.level().playSound(
                player,
                creeper.getX(), creeper.getY(), creeper.getZ(),
                SoundEvents.FLINTANDSTEEL_USE,
                creeper.getSoundSource(),
                1.0F,
                creeper.getRandom().nextFloat() * 0.4F + 0.8F
            );
        }
    }
}
