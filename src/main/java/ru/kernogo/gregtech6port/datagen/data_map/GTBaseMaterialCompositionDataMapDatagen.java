package ru.kernogo.gregtech6port.datagen.data_map;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;
import ru.kernogo.gregtech6port.registration.registered.GTDataMapTypes;
import ru.kernogo.gregtech6port.registration.registered.materials.GTBasicMaterials;
import ru.kernogo.gregtech6port.registration.registered.materials.GTChemicalElementMaterials;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Datagen for a DataMap that contains base material composition
 * ({@link ru.kernogo.gregtech6port.registration.registered.GTDataMapTypes#BASE_MATERIAL_COMPOSITION})
 */
public final class GTBaseMaterialCompositionDataMapDatagen extends DataMapProvider {
    public GTBaseMaterialCompositionDataMapDatagen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        Builder<List<GTMaterialAndAmount>, Item> builder = builder(GTDataMapTypes.BASE_MATERIAL_COMPOSITION);

        addItem(builder, Items.LEATHER, 1, GTBasicMaterials.LEATHER);

        addItem(builder, Items.LEATHER_BOOTS, 4, GTBasicMaterials.LEATHER);
        addItem(builder, Items.LEATHER_LEGGINGS, 7, GTBasicMaterials.LEATHER);
        addItem(builder, Items.LEATHER_CHESTPLATE, 8, GTBasicMaterials.LEATHER);
        addItem(builder, Items.LEATHER_HELMET, 5, GTBasicMaterials.LEATHER);

        //addVanillaItem(builder, Items.LEATHER_HORSE_ARMOR, );
        addItem(builder, Items.IRON_HORSE_ARMOR,
            8, GTChemicalElementMaterials.IRON,
            6, GTBasicMaterials.LEATHER
        );
        addItem(builder, Items.GOLDEN_HORSE_ARMOR,
            8, GTChemicalElementMaterials.IRON,
            6, GTBasicMaterials.LEATHER
        );
        addItem(builder, Items.DIAMOND_HORSE_ARMOR,
            8, GTChemicalElementMaterials.IRON,
            6, GTBasicMaterials.LEATHER
        );

        // TODO: add more material composition data

        builder.build();
    }

    private static void addItem(Builder<List<GTMaterialAndAmount>, Item> builder, Item item,
                                long materialUnits1, DeferredHolder<GTMaterial, GTMaterial> material1) {
        List<GTMaterialAndAmount> data = List.of(
            new GTMaterialAndAmount(material1.get(), GTMaterialAmount.of(materialUnits1))
        );

        builder.add(getHolder(item), data, false);
    }

    private static void addItem(Builder<List<GTMaterialAndAmount>, Item> builder, Item item,
                                long materialUnits1, DeferredHolder<GTMaterial, GTMaterial> material1,
                                long materialUnits2, DeferredHolder<GTMaterial, GTMaterial> material2) {
        List<GTMaterialAndAmount> data = List.of(
            new GTMaterialAndAmount(material1.get(), GTMaterialAmount.of(materialUnits1)),
            new GTMaterialAndAmount(material2.get(), GTMaterialAmount.of(materialUnits2))
        );

        builder.add(getHolder(item), data, false);
    }

    private static Holder.Reference<Item> getHolder(Item item) {
        // This method is deprecated, but it should still be fine to use, at least in MC 1.21.1
        //noinspection deprecation
        return item.builtInRegistryHolder();
    }
}
