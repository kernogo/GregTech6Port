package ru.kernogo.gregtech6port.features.material_kind_things.items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

/**
 * Information about a Material-Kind Item that needs to be registered (for registration purpose only).
 *
 * @param deferredItem           Already registered DeferredItem
 * @param material               Material of the Item
 * @param kind                   Kind of the Item
 * @param hasACorrespondingBlock true if {@link #deferredItem} has a Material-Kind Block corresponding to it
 *                               (so we assume that {@link #deferredItem} is a BlockItem).
 *                               This is useful, for instance, because we datagen models for BlockItems differently from normal Items.
 * @see GTMaterialKindItemDefinitionService
 */
public record GTMaterialKindItemDefinition(
    DeferredItem<Item> deferredItem,
    GTMaterial material,
    GTMaterialThingKind kind,
    boolean hasACorrespondingBlock
) {}
