package ru.kernogo.gregtech6port.features.material_kind_things.blocks;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

/**
 * Information about a Material-Kind Block that needs to be registered (for registration purpose only).
 *
 * @param deferredBlock Already registered DeferredBlock
 * @param material      Material of the Block
 * @param kind          Kind of the Block
 * @see GTMaterialKindBlockDefinitionService
 */
public record GTMaterialKindBlockDefinition(
    DeferredBlock<Block> deferredBlock,
    GTMaterial material,
    GTMaterialThingKind kind
) {}
