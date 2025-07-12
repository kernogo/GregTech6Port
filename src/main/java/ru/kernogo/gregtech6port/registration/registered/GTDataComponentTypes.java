package ru.kernogo.gregtech6port.registration.registered;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import ru.kernogo.gregtech6port.registration.GTRegistries;

/**
 * In general, the effects of adding data components to items are dependent on each item's implementation.
 * Each Item's Javadocs will document which data components are used with them. <br>
 * Some data components however work merely by adding them to an item - see Javadocs for each DataComponentType here.
 */
public final class GTDataComponentTypes {
    private GTDataComponentTypes() {}

    /** Stores the remaining uses for items. Does not work automatically. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> REMAINING_USES =
        GTRegistries.DATA_COMPONENTS_TYPES.registerComponentType(
            "gt_remaining_uses",
            builder -> builder
                .persistent(Codec.INT)
                .networkSynchronized(ByteBufCodecs.INT)
        );
    /** Stores the maximum remaining uses for items. Does not work automatically. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> MAX_REMAINING_USES =
        GTRegistries.DATA_COMPONENTS_TYPES.registerComponentType(
            "gt_max_remaining_uses",
            builder -> builder
                .persistent(Codec.INT)
                .networkSynchronized(ByteBufCodecs.INT)
        );
    /** Stores the proc chance for items. Does not work automatically. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Double>> PROC_CHANCE =
        GTRegistries.DATA_COMPONENTS_TYPES.registerComponentType(
            "gt_proc_chance",
            builder -> builder.persistent(Codec.DOUBLE).networkSynchronized(ByteBufCodecs.DOUBLE)
        );
    /** If present on an item, it is single-use. Does not work automatically. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> SINGLE_USE =
        GTRegistries.DATA_COMPONENTS_TYPES.registerComponentType(
            "gt_single_use",
            builder -> builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE))
        );
    /** What Item does this item break into (what remains after the item breaks). Does not work automatically. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Holder<Item>>> BREAKS_INTO =
        GTRegistries.DATA_COMPONENTS_TYPES.registerComponentType(
            "gt_breaks_into",
            builder -> builder.persistent(BuiltInRegistries.ITEM.holderByNameCodec()).networkSynchronized(ByteBufCodecs.holderRegistry(BuiltInRegistries.ITEM.key()))
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
