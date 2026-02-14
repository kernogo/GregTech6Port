package ru.kernogo.gregtech6port.features.material_kind_things.items.impls;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.material_kind_things.items.IGTTintedMaterialKindItem;

/** A simple Material-Kind Item with no special functionality on its own */
public class GTSimpleMaterialKindItem extends Item implements IGTTintedMaterialKindItem {
    private final GTMaterial material;
    private final GTMaterialThingKind kind;

    public GTSimpleMaterialKindItem(Properties properties, GTMaterial material, GTMaterialThingKind kind) {
        super(properties);
        this.material = material;
        this.kind = kind;
    }

    @Override
    public GTMaterial.ColorData getColorDataForTinting() {
        return material.colorData();
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(kind.itemTranslationKey(), Component.translatable(material.translationKey()));
    }
}
