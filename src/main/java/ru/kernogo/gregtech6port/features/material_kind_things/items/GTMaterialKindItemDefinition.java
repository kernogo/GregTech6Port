package ru.kernogo.gregtech6port.features.material_kind_things.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

/**
 * Information about a Material-Kind Item that needs to be registered (for registration purpose only).
 *
 * @param deferredItem  Already registered DeferredItem
 * @param material      Material of the Item
 * @param kind          Kind of the Item
 * @param deferredBlock An already registered Material-Kind Block that corresponds to the {@link #deferredItem}
 * @see GTMaterialKindItemDefinitionService
 */
public record GTMaterialKindItemDefinition(
    DeferredItem<Item> deferredItem,
    GTMaterial material,
    GTMaterialThingKind kind,
    @Nullable DeferredBlock<Block> deferredBlock
) {}
