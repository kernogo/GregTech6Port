package ru.kernogo.gregtech6port.features.behaviors.tint_coloring;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Capability to tint color the Blocks / BlockEntities
 *
 * @see GTTintColoringSystem
 */
@AllArgsConstructor
public final class GTTintColoringCapability {
    private final Consumer<@Nullable GTTintColoringData> tintColoringDataSetterUpdater;
    private final Supplier<@Nullable GTTintColoringData> tintColoringDataSupplier;

    public void setTintColoringDataAndUpdate(@Nullable GTTintColoringData tintColoringData) {
        tintColoringDataSetterUpdater.accept(tintColoringData);
    }

    public @Nullable GTTintColoringData getTintColoringData() {
        return tintColoringDataSupplier.get();
    }
}
