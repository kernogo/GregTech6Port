package ru.kernogo.gregtech6port.features.material_kind_things.blocks;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.material_kind_things.GTMaterialKindItemsAndBlocksTintingHandler;

/**
 * Interface for Material-Kind Block that is registered for tinting with
 * {@link GTMaterialKindItemsAndBlocksTintingHandler}
 */
public interface IGTTintedMaterialKindBlock {
    GTMaterial.ColorData getColorDataForTinting();
}
