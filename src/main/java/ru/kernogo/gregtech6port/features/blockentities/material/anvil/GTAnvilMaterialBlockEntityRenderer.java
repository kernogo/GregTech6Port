package ru.kernogo.gregtech6port.features.blockentities.material.anvil;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.model.SimpleModelState;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class GTAnvilMaterialBlockEntityRenderer implements BlockEntityRenderer<GTAnvilMaterialBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public GTAnvilMaterialBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(GTAnvilMaterialBlockEntity blockEntity,
                       float partialTick,
                       PoseStack poseStack,
                       MultiBufferSource bufferSource,
                       int packedLight,
                       int packedOverlay) {
        BakedModel bakedAnvilModel = getCachedBakedMaterialModel(
            "block_model_templates/material_blocks/anvil/anvil.json",
            "shiny",
            "solid_block"
        );

        context.getBlockRenderDispatcher().getModelRenderer().renderModel(
            poseStack.last(),
            bufferSource.getBuffer(RenderType.CUTOUT),
            blockEntity.getBlockState(),
            bakedAnvilModel,
            1.0f,
            1.0f,
            1.0f,
            packedLight,
            packedOverlay,
            ModelData.EMPTY,
            RenderType.CUTOUT
        );

        BakedModel bakedLeftIngotOnAnvilModel = getCachedBakedMaterialModel(
            "block_model_templates/material_blocks/anvil/things_on_an_anvil/ingot_left.json",
            "shiny",
            "solid_block"
        );

        BakedModel bakedRightIngotOnAnvilModel = getCachedBakedMaterialModel(
            "block_model_templates/material_blocks/anvil/things_on_an_anvil/ingot_right.json",
            "shiny",
            "solid_block"
        );

        BakedModel bakedLeftPlateOnAnvilModel = getCachedBakedMaterialModel(
            "block_model_templates/material_blocks/anvil/things_on_an_anvil/plate_left.json",
            "shiny",
            "solid_block"
        );

        BakedModel bakedRightPlateOnAnvilModel = getCachedBakedMaterialModel(
            "block_model_templates/material_blocks/anvil/things_on_an_anvil/plate_right.json",
            "shiny",
            "solid_block"
        );

        BakedModel bakedLeftRodOnAnvilModel = getCachedBakedMaterialModel(
            "block_model_templates/material_blocks/anvil/things_on_an_anvil/rod_left.json",
            "shiny",
            "solid_block"
        );

        BakedModel bakedRightRodOnAnvilModel = getCachedBakedMaterialModel(
            "block_model_templates/material_blocks/anvil/things_on_an_anvil/rod_right.json",
            "shiny",
            "solid_block"
        );

        context.getBlockRenderDispatcher().getModelRenderer().renderModel(
            poseStack.last(),
            bufferSource.getBuffer(RenderType.CUTOUT),
            blockEntity.getBlockState(),
            bakedLeftIngotOnAnvilModel,
            1.0f,
            1.0f,
            1.0f,
            packedLight,
            packedOverlay,
            ModelData.EMPTY,
            RenderType.CUTOUT
        );

        context.getBlockRenderDispatcher().getModelRenderer().renderModel(
            poseStack.last(),
            bufferSource.getBuffer(RenderType.CUTOUT),
            blockEntity.getBlockState(),
            bakedRightIngotOnAnvilModel,
            1.0f,
            1.0f,
            1.0f,
            packedLight,
            packedOverlay,
            ModelData.EMPTY,
            RenderType.CUTOUT
        );
    }

    private static Map<Pair<String, String>, @Nullable BakedModel> cachedModels = new HashMap<>();

    private static BakedModel getCachedBakedMaterialModel(String modelTemplatePath, String textureSet, String kind) {
        return doGetBakedMaterialModel(modelTemplatePath, textureSet, kind);
//        return cachedModels.computeIfAbsent(Pair.of(modelTemplatePath, textureSet),
//            unused -> doGetBakedMaterialModel(modelTemplatePath, textureSet));
    }

    private static BakedModel doGetBakedMaterialModel(String modelTemplatePath, String textureSet, String kind) {
        // TODO: check location immediately so it crashes on startup and not during gameplay
        ResourceLocation modelResourceLocation = ResourceLocation.fromNamespaceAndPath(GregTech6Port.MODID, modelTemplatePath);

        Resource modelResource = Minecraft.getInstance().getResourceManager()
            .getResource(modelResourceLocation)
            .orElse(null);

        if (modelResource == null) {
            throw new GTUnexpectedValidationFailException(
                "Could not open json block model (resource manager returned null): " + modelResourceLocation,
                new RuntimeException("Exception thrown for stack trace purposes"));
        }

        JsonObject modelJsonObject;

        try (Reader reader = modelResource.openAsReader()) {
            modelJsonObject = GsonHelper.parse(reader);
        } catch (IOException e) {
            throw new GTUnexpectedValidationFailException("Could not open json block model: " + modelResourceLocation, e);
        }

        String materialTexturePathPattern = GsonHelper.getAsString(modelJsonObject.getAsJsonObject("textures"), "material_texture");
        modelJsonObject.getAsJsonObject("textures").add("material_texture", new JsonPrimitive(
            materialTexturePathPattern.formatted(textureSet, kind)
        ));

        String particlePathPattern = GsonHelper.getAsString(modelJsonObject.getAsJsonObject("textures"), "particle");
        modelJsonObject.getAsJsonObject("textures").add("particle", new JsonPrimitive(
            particlePathPattern.formatted(textureSet, kind)
        ));

        BlockModel unbakedModel = BlockModel.fromString(modelJsonObject.toString());

        ModelBakery modelBakery = Minecraft.getInstance().getModelManager().getModelBakery();

        // Access transformer allows access to ModelBakerImpl
        ModelBakery.ModelBakerImpl modelBakerImpl = modelBakery.new ModelBakerImpl(
            (modelLocation, material) -> material.sprite(),
            new ModelResourceLocation(
                ResourceLocation.fromNamespaceAndPath(GregTech6Port.MODID, "does/not/matter"),
                "does/not/matter"
            )
        );

        return unbakedModel.bake(modelBakerImpl, Material::sprite, new SimpleModelState(Transformation.identity()));
    }

    /** This gets subscribed with the modBus in another class */
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GTBlockEntityTypes.ANVIL.get(), GTAnvilMaterialBlockEntityRenderer::new);
    }
}
