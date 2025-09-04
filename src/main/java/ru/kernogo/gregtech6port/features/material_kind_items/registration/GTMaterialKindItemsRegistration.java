package ru.kernogo.gregtech6port.features.material_kind_items.registration;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.material_kind_items.GTMaterialKindItem;
import ru.kernogo.gregtech6port.features.material_kind_items.GTMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_items.GTMaterialKindItemDefinitionService;
import ru.kernogo.gregtech6port.registration.GTRegisters;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;

/**
 * This class adds all definitions ({@link GTMaterialKindItemDefinition}) of {@link GTMaterialKindItem}
 * into {@link GTMaterialKindItemDefinitionService} for future registration.
 */
public final class GTMaterialKindItemsRegistration {
    private GTMaterialKindItemsRegistration() {}

    private static final GTMaterialKindItemDefinitionService materialKindItemDefinitionService = GTMaterialKindItemDefinitionService.getInstance();

    /**
     * This gets called from another class. <br>
     * Requires registries {@code Registry<GTMaterial>} and {@code Registry<GTMaterialThingKind>} to be populated.
     */
    public static void registerAllMaterialKindItems() {
        // TODO: For now, we generate Material-Kind Items for all Materials and all Kinds.
        //  Later on we'll figure out the details, and will generate Material-Kind Items only for some Material-Kind combinations.

        // These registries are already registered and populated by this point
        Registry<GTMaterial> materialsRegistry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .registryOrThrow(GTCustomRegistries.MATERIALS.key());
        Registry<GTMaterialThingKind> kindsRegistry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .registryOrThrow(GTCustomRegistries.MATERIAL_THING_KINDS.key());

        for (GTMaterial material : materialsRegistry) {
            for (GTMaterialThingKind kind : kindsRegistry) {
                registerEverythingForAMaterialKindItem(material, kind);
            }
        }
    }

    private static void registerEverythingForAMaterialKindItem(
        GTMaterial material,
        GTMaterialThingKind kind
    ) {
        String itemName = "%s_%s".formatted(material.name(), kind.name());

        DeferredItem<Item> deferredItem = GTRegisters.ITEMS.register(
            itemName,
            () -> new GTMaterialKindItem(new Item.Properties(), material, kind)
        );

        GTMaterialKindItemDefinition definition = new GTMaterialKindItemDefinition(deferredItem, material, kind);

        materialKindItemDefinitionService.addDefinition(definition);
    }
}
