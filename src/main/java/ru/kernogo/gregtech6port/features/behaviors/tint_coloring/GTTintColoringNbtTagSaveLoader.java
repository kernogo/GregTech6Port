package ru.kernogo.gregtech6port.features.behaviors.tint_coloring;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.IGTNbtTagSaveLoader;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;

/** An implementation of {@link IGTNbtTagSaveLoader} for {@link GTTintColoringData} */
public final class GTTintColoringNbtTagSaveLoader implements IGTNbtTagSaveLoader<GTTintColoringData> {
    private final String TAG_NAME = GTDataComponentTypes.TINT_COLORING.getKey().location().toString();

    @Override
    public @Nullable GTTintColoringData getDataForLoadAdditional(CompoundTag compoundTag) {
        if (compoundTag.contains(TAG_NAME)) {
            return new GTTintColoringData(compoundTag.getIntOr(TAG_NAME, -1));
        }
        return null;
    }

    @Override
    public void saveAdditional(CompoundTag compoundTag, @Nullable GTTintColoringData data) {
        if (data != null) {
            compoundTag.putInt(TAG_NAME, data.argbColor());
        }
    }

    @Override
    public @Nullable GTTintColoringData getDataForApplyImplicitComponents(DataComponentGetter componentInput) {
        return componentInput.get(GTDataComponentTypes.TINT_COLORING);
    }

    @Override
    public void collectImplicitComponents(DataComponentMap.Builder components, @Nullable GTTintColoringData data) {
        if (data != null) {
            components.set(GTDataComponentTypes.TINT_COLORING, data);
        }
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove(TAG_NAME);
    }
}
