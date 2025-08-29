package ru.kernogo.gregtech6port.features.material_kind_items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

/**
 * A somewhat simple Item that is defined by its Material
 * ({@link ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial})
 * and Kind ({@link GTMaterialThingKind}). <br>
 * While the Material and Kind are stored on this Item, other means of accessing them should be used instead if possible. <br>
 * Material-Kind Items are registered by adding definitions to {@link GTMaterialKindItemDefinitionService}.
 */
public class GTMaterialKindItem extends Item {
    private final GTMaterial material;
    private final GTMaterialThingKind kind;

    public GTMaterialKindItem(Properties properties, GTMaterial material, GTMaterialThingKind kind) {
        super(properties);
        this.material = material;
        this.kind = kind;
    }

    public GTMaterial.ColorData getColorDataForTinting() {
        return material.colorData();
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(kind.itemTranslationKey(), Component.translatable(material.translationKey()));
    }
}
