package ru.kernogo.gregtech6port.features.behaviors.tint_coloring;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.features.IGTNbtTagSaveLoader;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;

/** An implementation of {@link IGTNbtTagSaveLoader} for {@link GTTintColoringData} */
public final class GTTintColoringNbtTagSaveLoader implements IGTNbtTagSaveLoader<GTTintColoringData> {
    private final String TAG_NAME = GTDataComponentTypes.TINT_COLORING.getKey().identifier().toString();

    @Override
    public @Nullable GTTintColoringData getDataForLoadAdditional(ValueInput input) {
        if (input.getInt(TAG_NAME).isPresent()) {
            return new GTTintColoringData(input.getInt(TAG_NAME).get());
        }
        return null;
    }

    @Override
    public void saveAdditional(ValueOutput output, @Nullable GTTintColoringData data) {
        if (data != null) {
            output.putInt(TAG_NAME, data.argbColor());
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
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard(TAG_NAME);
    }
}
