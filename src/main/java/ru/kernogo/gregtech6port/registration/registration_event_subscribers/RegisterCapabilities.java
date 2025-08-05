
package ru.kernogo.gregtech6port.registration.registration_event_subscribers;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringCapability;
import ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin.GTEnderGarbageBinBlockEntity;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class RegisterCapabilities {
    private RegisterCapabilities() {}

    @SubscribeEvent
    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        registerTintColoringCapability(event, GTBlockEntityTypes.ENDER_GARBAGE_BIN, GTEnderGarbageBinBlockEntity::getTintColoringCapability);
    }

    private static <BE extends BlockEntity> void registerTintColoringCapability(
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
