package ru.kernogo.gregtech6port.features;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

/**
 * Implementations of this interface are used in BlockEntities
 * to save/load tags into/from NBT for a specific Data Component of type {@code T}
 */
public interface IGTNbtTagSaveLoader<T> {
    @Nullable T getDataForLoadAdditional(CompoundTag compoundTag);

    void saveAdditional(CompoundTag compoundTag, @Nullable T data);

    @Nullable T getDataForApplyImplicitComponents(DataComponentGetter componentInput);

    void collectImplicitComponents(DataComponentMap.Builder components, @Nullable T data);

    void removeComponentsFromTag(CompoundTag tag);
}
