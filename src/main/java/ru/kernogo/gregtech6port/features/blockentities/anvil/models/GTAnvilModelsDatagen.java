package ru.kernogo.gregtech6port.features.blockentities.anvil.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.model.Material;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialTextureSet;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.blockentities.anvil.GTAnvilBlockCommon;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;
import ru.kernogo.gregtech6port.utils.GTUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * Generates standalone JSON model for GT Anvils
 * (models of things (like plates) that will appear on the Anvil)
 */
public class GTAnvilModelsDatagen extends ModelProvider {
    public GTAnvilModelsDatagen(PackOutput output) {
        super(output, GregTech6Port.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // Registries should be populated by this point
        List<GTMaterialTextureSet> allTextureSets = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .lookupOrThrow(GTCustomRegistries.MATERIAL_TEXTURE_SETS.key()).stream().toList();

        // TODO extract the loop somewhere
        for (GTMaterialThingKind kind : GTAnvilBlockCommon.getSupportedThingOnAnvilKinds()) {
            for (GTMaterialThingKind kindUsedForTexture : List.of(
                GTMaterialThingKinds.SOLID_BLOCK,
                GTMaterialThingKinds.GEM_BLOCK,
                GTMaterialThingKinds.RAW_BLOCK
            )) {
                for (GTMaterialTextureSet textureSet : allTextureSets) {
                    GTThingOnAnvilKey keyForLeftModel = new GTThingOnAnvilKey(
                        kind, textureSet, kindUsedForTexture, GTThingOnAnvilKey.LeftOrRight.LEFT
                    );
                    GTThingOnAnvilKey keyForRightModel = new GTThingOnAnvilKey(
                        kind, textureSet, kindUsedForTexture, GTThingOnAnvilKey.LeftOrRight.RIGHT
                    );

                    datagenModelForKey(blockModels, keyForLeftModel);
                    datagenModelForKey(blockModels, keyForRightModel);
                }
            }
        }
    }

    private void datagenModelForKey(BlockModelGenerators blockModels, GTThingOnAnvilKey key) {
        TextureSlot particle = TextureSlot.create("particle");
        TextureSlot materialTexture = TextureSlot.create("material_texture");
        // We don't do use overlay textures for these models

        ExtendedModelTemplate template = ExtendedModelTemplateBuilder.builder()
            .parent(modLocation(key.parentModelPath()))
            .requiredTextureSlot(particle)
            .requiredTextureSlot(materialTexture)
            .build();

        template.create(
            GTUtils.modLoc(key.modelPath()),
            new TextureMapping()
                .put(
                    particle,
                    new Material(GTUtils.modLoc(
                        "block/material_icons/%s/%s"
                            .formatted(key.textureSetUsedForTexture().name(), key.kindUsedForTexture().name())
                    ))
                )
                .put(
                    materialTexture,
                    new Material(GTUtils.modLoc(
                        "block/material_icons/%s/%s"
                            .formatted(key.textureSetUsedForTexture().name(), key.kindUsedForTexture().name())
                    ))
                ),
            blockModels.modelOutput
        );
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
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
