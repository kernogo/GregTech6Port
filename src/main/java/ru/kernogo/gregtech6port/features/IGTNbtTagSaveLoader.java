package ru.kernogo.gregtech6port.features;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

/**
 * Implementations of this interface are used in BlockEntities
 * to save/load tags into/from NBT for a specific Data Component of type {@code T}
 */
public interface IGTNbtTagSaveLoader<T> {
    @Nullable T getDataForLoadAdditional(CompoundTag compoundTag);

    void saveAdditional(CompoundTag compoundTag, @Nullable T data);

    // Access transformer allows BlockEntity.DataComponentInput to be available
    @Nullable T getDataForApplyImplicitComponents(BlockEntity.DataComponentInput componentInput);

    void collectImplicitComponents(DataComponentMap.Builder components, @Nullable T data);

    void removeComponentsFromTag(CompoundTag tag);
}
