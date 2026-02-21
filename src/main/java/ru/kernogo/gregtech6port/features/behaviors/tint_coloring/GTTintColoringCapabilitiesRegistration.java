package ru.kernogo.gregtech6port.features.behaviors.tint_coloring;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin.GTEnderGarbageBinBlockEntity;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;

/** Registers the {@link GTCapabilities#TINT_COLORING} Capability for BlockEntities */
public final class GTTintColoringCapabilitiesRegistration {
    private GTTintColoringCapabilitiesRegistration() {}

    /** This gets subscribed with the modBus in another class */
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        register(event, GTBlockEntityTypes.ENDER_GARBAGE_BIN, GTEnderGarbageBinBlockEntity::getTintColoringCapability);
    }

    private static <BE extends BlockEntity> void register(
        RegisterCapabilitiesEvent event,
        DeferredHolder<BlockEntityType<?>, BlockEntityType<BE>> blockEntityTypeHolder,
        ICapabilityProvider<BE, @Nullable Void, GTTintColoringCapability> tintColoringCapabilityProvider
    ) {
        event.registerBlockEntity(
            GTCapabilities.TINT_COLORING,
            blockEntityTypeHolder.get(),
            tintColoringCapabilityProvider
        );
    }
}
