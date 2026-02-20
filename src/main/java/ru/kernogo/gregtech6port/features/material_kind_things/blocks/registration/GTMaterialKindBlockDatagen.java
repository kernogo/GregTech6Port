package ru.kernogo.gregtech6port.features.material_kind_things.blocks.registration;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTMaterialKindBlockDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTMaterialKindBlockDefinitionService;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;

import java.util.List;
import java.util.stream.Stream;

/** Datagen generating BlockState/model JSONs for Material-Kind Blocks */
public final class GTMaterialKindBlockDatagen extends ModelProvider {
    private final GTMaterialKindBlockDefinitionService materialKindBlockDefinitionService =
        GTMaterialKindBlockDefinitionService.getInstance();

    public GTMaterialKindBlockDatagen(PackOutput output) {
        super(output, GregTech6Port.MODID);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        List<GTMaterialKindBlockDefinition> definitions = materialKindBlockDefinitionService.getGTMaterialKindBlockDefinitions();

        for (GTMaterialKindBlockDefinition definition : definitions) {
            if (definition.kind().equals(GTMaterialThingKinds.DUST_BLOCK)) {
                String textureSetName = definition.material().textureSet().name();
                String kindName = definition.kind().name();

                ResourceLocation blockTextureLoc = modLocation(
                    "block/material_icons/%s/%s".formatted(textureSetName, kindName)
                );
                ResourceLocation blockTextureOverlayLoc = modLocation(
                    "block/material_icons/%s/%s_overlay".formatted(textureSetName, kindName)
                );

                TextureSlot particle = TextureSlot.create("particle");
                TextureSlot allSides = TextureSlot.create("all_sides");
                TextureSlot overlay = TextureSlot.create("overlay");

                ExtendedModelTemplate template = ExtendedModelTemplateBuilder.builder()
                    .parent(modLocation("block/material_kind_blocks/dust_block_parent"))
                    .requiredTextureSlot(particle)
                    .requiredTextureSlot(allSides)
                    .requiredTextureSlot(overlay)
                    .build();

                template.create(
                    definition.deferredBlock().get(),
                    new TextureMapping()
                        .put(particle, blockTextureLoc)
                        .put(allSides, blockTextureLoc)
                        .put(overlay, blockTextureOverlayLoc),
                    blockModels.modelOutput
                );

                blockModels.blockStateOutput.accept(
                    MultiVariantGenerator.multiVariant(
                        definition.deferredBlock().get(),
                        Variant.variant().with(
                            VariantProperties.MODEL,
                            ModelLocationUtils.getModelLocation(definition.deferredBlock().get())
                        )
                    )
                );
            }
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
