package ru.kernogo.gregtech6port.features.behaviors.tint_coloring;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;

/**
 * Tint source for Items based on
 * the value of the {@link GTDataComponentTypes#TINT_COLORING} data component on the ItemStack
 */
public record GTTintColoringTintSource() implements ItemTintSource {
    public static final MapCodec<GTTintColoringTintSource> MAP_CODEC = MapCodec.unit(new GTTintColoringTintSource());

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        GTTintColoringData tintColoringData = stack.get(GTDataComponentTypes.TINT_COLORING);
        if (tintColoringData == null) {
            return -1;
        }
        return tintColoringData.argbColor();
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
