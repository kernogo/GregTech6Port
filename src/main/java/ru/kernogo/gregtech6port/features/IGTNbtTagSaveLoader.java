package ru.kernogo.gregtech6port.features;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

/**
 * Implementations of this interface are used in BlockEntities
 * to save/load tags into/from NBT for a specific Data Component of type {@code T}
 */
public interface IGTNbtTagSaveLoader<T> {
    @Nullable T getDataForLoadAdditional(ValueInput input);

    void saveAdditional(ValueOutput output, @Nullable T data);

    @Nullable T getDataForApplyImplicitComponents(DataComponentGetter componentInput);

    void collectImplicitComponents(DataComponentMap.Builder components, @Nullable T data);

    void removeComponentsFromTag(ValueOutput output);
}
