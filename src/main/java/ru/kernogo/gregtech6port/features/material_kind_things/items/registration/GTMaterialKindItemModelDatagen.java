package ru.kernogo.gregtech6port.features.material_kind_things.items.registration;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.material_kind_things.GTMaterialKindItemTintSource;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinitionService;

import java.util.List;
import java.util.stream.Stream;

/** Datagen generating model JSONs for Material-Kind Items */
public final class GTMaterialKindItemModelDatagen extends ModelProvider {
    private final GTMaterialKindItemDefinitionService materialKindItemDefinitionService =
        GTMaterialKindItemDefinitionService.getInstance();

    public GTMaterialKindItemModelDatagen(PackOutput output) {
        super(output, GregTech6Port.MODID);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        List<GTMaterialKindItemDefinition> definitions = materialKindItemDefinitionService.getGTMaterialKindItemDefinitions();

        for (GTMaterialKindItemDefinition definition : definitions) {
            if (definition.deferredBlock() != null) {
                itemModels.itemModelOutput.accept(
                    definition.deferredItem().get(),
                    ItemModelUtils.tintedModel(
                        ModelLocationUtils.getModelLocation(definition.deferredBlock().get()),
                        new GTMaterialKindItemTintSource()
                    )
                );

                continue;
            }

            // Other cases

            String textureSetName = definition.material().textureSet().name();
            String kindName = definition.kind().name();

            ModelTemplates.TWO_LAYERED_ITEM.create(
                definition.deferredItem().get(),
                new TextureMapping()
                    .put(TextureSlot.LAYER0, modLocation("item/material_icons/" + textureSetName + "/" + kindName))
                    .put(TextureSlot.LAYER1, modLocation("item/material_icons/" + textureSetName + "/" + kindName + "_overlay")),
                itemModels.modelOutput
            );

            itemModels.itemModelOutput.accept(
                definition.deferredItem().get(),
                ItemModelUtils.tintedModel(
                    ModelLocationUtils.getModelLocation(definition.deferredItem().get()),
                    new GTMaterialKindItemTintSource()
                )
            );
        }
    }

    // We don't generate JSONs for all Blocks/Items here,
    // so we disable validations like that
    // @formatter:off
    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() { return Stream.of(); }
    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() { return Stream.of(); }
    // @formatter:on
}
