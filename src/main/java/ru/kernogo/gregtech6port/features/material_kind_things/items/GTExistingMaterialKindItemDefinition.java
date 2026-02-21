package ru.kernogo.gregtech6port.features.material_kind_things.items;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

/**
 * Information about existing Items that need to be treated as Material-Kind Items too (for registration purpose only). <br>
 * Existing Items may be vanilla or modded Items.
 *
 * @see GTMaterialKindItemDefinitionService
 */
public record GTExistingMaterialKindItemDefinition(
    Identifier itemIdentifier,
    GTMaterial material,
    GTMaterialThingKind kind
) {
    /**
     * Convenience method to resolve {@link #itemIdentifier} into an Item. <br>
     * Returns null if it was not resolved <br>
     * (other mod Items will not be resolved if mods were not loaded (yet or at all)).
     */
    public @Nullable Item getItemOrElseNull() {
        return BuiltInRegistries.ITEM
            .getOptional(itemIdentifier)
            .orElse(null);
    }
}
