package ru.kernogo.gregtech6port.features.material_kind_items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

/**
 * Information about a {@link GTMaterialKindItem} that needs to be registered (for registration purpose only).
 *
 * @see GTMaterialKindItemDefinitionService
 */
public record GTMaterialKindItemDefinition(
    DeferredItem<Item> deferredItem,
    GTMaterial material,
    GTMaterialThingKind kind
) {}
