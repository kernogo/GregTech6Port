package ru.kernogo.gregtech6port.features.material_kind_things.items;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.material_kind_things.GTMaterialKindItemsAndBlocksTintingHandler;

/**
 * Interface for Material-Kind Item that is registered for tinting with
 * {@link GTMaterialKindItemsAndBlocksTintingHandler}
 */
public interface IGTTintedMaterialKindItem {
    GTMaterial.ColorData getColorDataForTinting();
}
