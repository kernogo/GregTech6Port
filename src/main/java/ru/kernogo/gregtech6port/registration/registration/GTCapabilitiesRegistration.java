
package ru.kernogo.gregtech6port.registration.registration;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.capabilities.GTMaterialCompositionSimpleCapability;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringCapability;
import ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin.GTEnderGarbageBinBlockEntity;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;

public final class GTCapabilitiesRegistration {
    private GTCapabilitiesRegistration() {}

    /** This gets subscribed with the modBus in another class */
    public static void registerAllCapabilities(RegisterCapabilitiesEvent event) {
        registerMaterialCompositionCapabilityForAllItems(event);

        registerTintColoringCapability(event, GTBlockEntityTypes.ENDER_GARBAGE_BIN, GTEnderGarbageBinBlockEntity::getTintColoringCapability);
    }

    private static void registerMaterialCompositionCapabilityForAllItems(RegisterCapabilitiesEvent event) {
        for (Item item : BuiltInRegistries.ITEM) {
            // TODO: specific capabilities for certain items

            // Simple capability is registered for nearly all items
            event.registerItem(
                GTCapabilities.MATERIAL_COMPOSITION,
                (object, context) -> new GTMaterialCompositionSimpleCapability(),
                item
            );
        }
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
