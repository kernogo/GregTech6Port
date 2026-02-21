package ru.kernogo.gregtech6port.features.behaviors.material_composition.capabilities;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialCompositionData;
import ru.kernogo.gregtech6port.registration.registered.GTDataMapTypes;

import java.util.List;

/**
 * Uses the value from the base material composition ({@link GTDataMapTypes#BASE_MATERIAL_COMPOSITION}) DataMap
 * as the material composition of an Item
 */
public final class GTMaterialCompositionSimpleCapability implements IGTMaterialCompositionCapability {
    @Override
    public @Nullable GTMaterialCompositionData getMaterialComposition(ItemStack itemStack) {
        Holder<Item> itemStackHolder = itemStack.getItemHolder();
        List<GTMaterialAndAmount> baseMaterialComposition = itemStackHolder.getData(GTDataMapTypes.BASE_MATERIAL_COMPOSITION);
        if (baseMaterialComposition == null) {
            return null;
        }
        return new GTMaterialCompositionData(baseMaterialComposition);
    }
}
