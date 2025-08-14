package ru.kernogo.gregtech6port.features.behaviors.material_composition;

import java.util.List;

/**
 * Stores the list of "materials and their amounts" in an Item of the stack. <br>
 * See the {@link ru.kernogo.gregtech6port.features.behaviors.material_composition.capabilities.IGTMaterialCompositionCapability}
 * capability that exposes this data.
 */
public record GTMaterialCompositionData(
    List<GTMaterialAndAmount> materialStacks
) {}
