package ru.kernogo.gregtech6port.features.behaviors.material_composition.capabilities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;

/**
 * Registers {@link GTCapabilities#MATERIAL_COMPOSITION} Capabilities for all Items
 */
public final class GTMaterialCompositionCapabilitiesRegistration {
    private GTMaterialCompositionCapabilitiesRegistration() {}

    /** This gets subscribed with the modBus in another class */
    public static void registerCapabilitiesForAllItems(RegisterCapabilitiesEvent event) {
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
}
