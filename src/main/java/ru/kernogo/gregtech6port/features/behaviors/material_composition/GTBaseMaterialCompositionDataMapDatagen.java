package ru.kernogo.gregtech6port.features.behaviors.material_composition;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.material_kind_things.items.registration.GTMaterialKindItemBaseMaterialCompositionDataMapDatagen;
import ru.kernogo.gregtech6port.registration.registered.GTDataMapTypes;
import ru.kernogo.gregtech6port.registration.registered.materials.GTBasicMaterials;
import ru.kernogo.gregtech6port.registration.registered.materials.GTChemicalElementMaterials;
import ru.kernogo.gregtech6port.utils.GTUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Datagen for all entries of a DataMap that contains base material composition
 * ({@link GTDataMapTypes#BASE_MATERIAL_COMPOSITION})
 */
public final class GTBaseMaterialCompositionDataMapDatagen extends DataMapProvider {
    private final GTMaterialKindItemBaseMaterialCompositionDataMapDatagen materialKindItemBaseMaterialCompositionDataMapDatagen =
        new GTMaterialKindItemBaseMaterialCompositionDataMapDatagen(this);

    public GTBaseMaterialCompositionDataMapDatagen(PackOutput packOutput,
                                                   CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void gather(HolderLookup.Provider holderLookupProvider) {
        doCommonDatagen();
        materialKindItemBaseMaterialCompositionDataMapDatagen.gather(holderLookupProvider);
    }

    private void doCommonDatagen() {
        Builder<List<GTMaterialAndAmount>, Item> dataMapBuilder = builder(GTDataMapTypes.BASE_MATERIAL_COMPOSITION);

        addItemComposition(dataMapBuilder, Items.LEATHER, 1, GTBasicMaterials.LEATHER);

        addItemComposition(dataMapBuilder, Items.LEATHER_BOOTS, 4, GTBasicMaterials.LEATHER);
        addItemComposition(dataMapBuilder, Items.LEATHER_LEGGINGS, 7, GTBasicMaterials.LEATHER);
        addItemComposition(dataMapBuilder, Items.LEATHER_CHESTPLATE, 8, GTBasicMaterials.LEATHER);
        addItemComposition(dataMapBuilder, Items.LEATHER_HELMET, 5, GTBasicMaterials.LEATHER);

        addItemComposition(dataMapBuilder, Items.LEATHER_HORSE_ARMOR,
            7, GTBasicMaterials.LEATHER);
        addItemComposition(dataMapBuilder, Items.IRON_HORSE_ARMOR,
            8, GTChemicalElementMaterials.IRON,
            6, GTBasicMaterials.LEATHER
        );
        addItemComposition(dataMapBuilder, Items.GOLDEN_HORSE_ARMOR,
            8, GTChemicalElementMaterials.IRON,
            6, GTBasicMaterials.LEATHER
        );
        addItemComposition(dataMapBuilder, Items.DIAMOND_HORSE_ARMOR,
            8, GTChemicalElementMaterials.IRON,
            6, GTBasicMaterials.LEATHER
        );

        // TODO: add more material composition data
    }

    private static void addItemComposition(Builder<List<GTMaterialAndAmount>, Item> dataMapBuilder,
                                           Item item,
                                           long materialUnits1, GTMaterial material1) {
        List<GTMaterialAndAmount> data = List.of(
            new GTMaterialAndAmount(material1, GTMaterialAmount.of(materialUnits1))
        );

        dataMapBuilder.add(GTUtils.getHolder(item), data, false);
    }

    private static void addItemComposition(Builder<List<GTMaterialAndAmount>, Item> dataMapBuilder,
                                           Item item,
                                           long materialUnits1, GTMaterial material1,
                                           long materialUnits2, GTMaterial material2) {
        List<GTMaterialAndAmount> data = List.of(
            new GTMaterialAndAmount(material1, GTMaterialAmount.of(materialUnits1)),
            new GTMaterialAndAmount(material2, GTMaterialAmount.of(materialUnits2))
        );

        dataMapBuilder.add(GTUtils.getHolder(item), data, false);
    }
}
