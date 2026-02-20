package ru.kernogo.gregtech6port.registration.registered;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesData;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringData;
import ru.kernogo.gregtech6port.registration.GTRegisters;

/**
 * In general, the effects of adding data components to items are dependent on each item's implementation.
 * Each Item's Javadocs will document which data components are used with them. <br>
 * Some data components however work merely by adding them to an item - see Javadocs for each DataComponentType here.
 */
public final class GTDataComponentTypes {
    private GTDataComponentTypes() {}

    /**
     * Data for items that have a finite number of uses. See the data class {@link GTItemWithUsesData} for the details. <br>
     * Does not work automatically.
     */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<GTItemWithUsesData>> ITEM_WITH_USES =
        GTRegisters.DATA_COMPONENTS_TYPES.registerComponentType(
            "item_with_uses",
            builder -> builder
                .persistent(GTItemWithUsesData.CODEC)
                .networkSynchronized(GTItemWithUsesData.STREAM_CODEC)
        );

    /** Stores the proc chance for items. Does not work automatically. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Double>> PROC_CHANCE =
        GTRegisters.DATA_COMPONENTS_TYPES.registerComponentType(
            "proc_chance",
            builder -> builder.persistent(Codec.DOUBLE).networkSynchronized(ByteBufCodecs.DOUBLE)
        );

    /**
     * Data component for the tint coloring of items. <br>
     * See also: {@link ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringTintSource} -
     * Tint source that uses this data component
     */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<GTTintColoringData>> TINT_COLORING =
        GTRegisters.DATA_COMPONENTS_TYPES.registerComponentType(
            "tint_coloring",
            builder -> builder
                .persistent(GTTintColoringData.CODEC)
                .networkSynchronized(GTTintColoringData.STREAM_CODEC)
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
