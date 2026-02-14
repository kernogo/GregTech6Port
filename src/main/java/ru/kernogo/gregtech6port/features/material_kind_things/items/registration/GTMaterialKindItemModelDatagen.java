package ru.kernogo.gregtech6port.features.material_kind_things.items.registration;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinitionService;

import java.util.List;

/** Datagen for all Material-Kind Item Models */
public final class GTMaterialKindItemModelDatagen extends ItemModelProvider {
    private final GTMaterialKindItemDefinitionService materialKindItemDefinitionService =
        GTMaterialKindItemDefinitionService.getInstance();

    public GTMaterialKindItemModelDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GregTech6Port.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void registerModels() {
        List<GTMaterialKindItemDefinition> definitions = materialKindItemDefinitionService.getGTMaterialKindItemDefinitions();

        for (GTMaterialKindItemDefinition definition : definitions) {
            if (definition.hasACorrespondingBlock()) {
                // We assume that if a Material-Kind Item definition has a corresponding Block,
                // then the Item in definition is a BlockItem, and its model should be created in other datagen class
                continue;
            }

            String itemName = definition.deferredItem().getKey().location().getPath();
            String textureSetName = definition.material().textureSet().name();
            String kindName = definition.kind().name();

            doModelDatagenOfASimpleItem(itemName, textureSetName, kindName);
        }
    }

    private void doModelDatagenOfASimpleItem(String itemName, String textureSetName, String kindName) {
        // TODO: Fix the z-fighting because of overlapping and transparent layers.
        //  Will likely need to create some custom rendering code.
        getBuilder(itemName)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc("item/material_icons/" + textureSetName + "/" + kindName))
            .texture("layer1", modLoc("item/material_icons/" + textureSetName + "/" + kindName + "_overlay"));
    }
}
