package ru.kernogo.gregtech6port.features.behaviors.material_composition.capabilities;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialCompositionData;

/**
 * Capability that returns the material composition of an Item ({@link GTMaterialCompositionData})
 * (what materials is an Item composed of, how much of each material is there in each Item). <br>
 * Amounts are for each Item of the stack, not for the whole stack. <br>
 * Items that have this capability will have a tooltip displayed automatically using
 * {@link ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialCompositionTooltipProvider}
 */
public interface IGTMaterialCompositionCapability {
    /** Returns the material composition of an Item, or null if there is no material composition for that Item. */
    @Nullable GTMaterialCompositionData getMaterialComposition(ItemStack itemStack);
}
