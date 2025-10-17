package ru.kernogo.gregtech6port.features.material_kind_things.blocks.impls;

import net.minecraft.world.level.block.Block;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.IGTTintedMaterialKindBlock;

/** A simple Material-Kind Block with no special functionality on its own */
public class GTSimpleMaterialKindBlock extends Block implements IGTTintedMaterialKindBlock {
    private final GTMaterial material;
    private final GTMaterialThingKind kind;

    public GTSimpleMaterialKindBlock(Properties properties, GTMaterial material, GTMaterialThingKind kind) {
        super(properties);
        this.material = material;
        this.kind = kind;
    }

    @Override
    public GTMaterial.ColorData getColorDataForTinting() {
        return material.colorData();
    }
}
