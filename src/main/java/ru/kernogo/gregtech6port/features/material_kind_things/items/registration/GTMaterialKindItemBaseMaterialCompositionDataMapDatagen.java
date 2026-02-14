package ru.kernogo.gregtech6port.features.material_kind_things.items.registration;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTBaseMaterialCompositionDataMapDatagen;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTExistingMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinitionService;
import ru.kernogo.gregtech6port.registration.registered.GTDataMapTypes;
import ru.kernogo.gregtech6port.utils.GTUtils;

import java.util.ArrayList;
import java.util.List;

/** Datagen for the Material-Kind Item entries of a DataMap that contains the base material composition */
public final class GTMaterialKindItemBaseMaterialCompositionDataMapDatagen {
    private final GTBaseMaterialCompositionDataMapDatagen provider;
    private final GTMaterialKindItemDefinitionService materialKindItemDefinitionService =
        GTMaterialKindItemDefinitionService.getInstance();

    /** @param provider The instance of {@link GTBaseMaterialCompositionDataMapDatagen} */
    public GTMaterialKindItemBaseMaterialCompositionDataMapDatagen(GTBaseMaterialCompositionDataMapDatagen provider) {
        this.provider = provider;
    }

    /** This is called from {@link GTBaseMaterialCompositionDataMapDatagen} */
    public void gather(HolderLookup.Provider holderLookupProvider) {
        DataMapProvider.Builder<List<GTMaterialAndAmount>, Item> dataMapBuilder =
            provider.builder(GTDataMapTypes.BASE_MATERIAL_COMPOSITION);

        addCompositionsForGTMaterialKindItems(dataMapBuilder);
        addCompositionsForExistingMaterialKindItems(dataMapBuilder);
    }

    private void addCompositionsForGTMaterialKindItems(DataMapProvider.Builder<List<GTMaterialAndAmount>, Item> dataMapBuilder) {
        List<GTMaterialKindItemDefinition> definitions =
            materialKindItemDefinitionService.getGTMaterialKindItemDefinitions();

        for (GTMaterialKindItemDefinition definition : definitions) {
            DeferredItem<Item> deferredItem = definition.deferredItem();
            GTMaterial material = definition.material();
            GTMaterialAmount amountForAnItem = definition.kind().amountForAnItem();
            List<GTMaterialAndAmount> additionalMaterialCompositionInAnItem = definition.kind().additionalMaterialCompositionInAnItem();

            List<GTMaterialAndAmount> finalMaterialComposition = new ArrayList<>();
            finalMaterialComposition.add(new GTMaterialAndAmount(material, amountForAnItem));
            finalMaterialComposition.addAll(additionalMaterialCompositionInAnItem);

            dataMapBuilder.add(
                deferredItem,
                finalMaterialComposition,
                false
            );
        }
    }

    private void addCompositionsForExistingMaterialKindItems(DataMapProvider.Builder<List<GTMaterialAndAmount>, Item> dataMapBuilder) {
        List<GTExistingMaterialKindItemDefinition> definitions =
            materialKindItemDefinitionService.getExistingMaterialKindItemDefinitions();

        for (GTExistingMaterialKindItemDefinition definition : definitions) {
            Item itemFromDefinition = definition.getItemOrElseNull();
            if (itemFromDefinition == null) {
                continue;
            }

            GTMaterial material = definition.material();
            GTMaterialAmount amountForAnItem = definition.kind().amountForAnItem();
            List<GTMaterialAndAmount> additionalMaterialCompositionInAnItem = definition.kind().additionalMaterialCompositionInAnItem();

            List<GTMaterialAndAmount> finalMaterialComposition = new ArrayList<>();
            finalMaterialComposition.add(new GTMaterialAndAmount(material, amountForAnItem));
            finalMaterialComposition.addAll(additionalMaterialCompositionInAnItem);

            dataMapBuilder.add(
                GTUtils.getHolder(itemFromDefinition),
                finalMaterialComposition,
                false
            );
        }
    }
}
