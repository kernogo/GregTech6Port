package ru.kernogo.gregtech6port.features.material_kind_things.blocks;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

/**
 * Information about existing Items that need to be treated as Material-Kind Items too (for registration purpose only). <br>
 * Existing Items may be vanilla or modded Items.
 *
 * @see GTMaterialKindBlockDefinitionService
 */
public record GTExistingMaterialKindBlockDefinition(
    ResourceLocation blockResourceLocation,
    GTMaterial material,
    GTMaterialThingKind kind
) {
    /**
     * Convenience method to resolve {@link #blockResourceLocation} into an Item. <br>
     * Returns null if it was not resolved <br>
     * (other mod Items will not be resolved if mods were not loaded (yet or at all)).
     */
    public @Nullable Block getItemOrElseNull() {
        return BuiltInRegistries.BLOCK
            .getOptional(blockResourceLocation)
            .orElse(null);
    }
}
