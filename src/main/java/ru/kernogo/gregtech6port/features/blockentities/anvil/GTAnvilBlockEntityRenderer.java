package ru.kernogo.gregtech6port.features.blockentities.anvil;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.blockentities.anvil.models.GTAnvilModelGetterService;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.registration.registered.materials.GTChemicalElementMaterials;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

@Slf4j
public final class GTAnvilBlockEntityRenderer implements BlockEntityRenderer<GTAnvilBlockEntity, GTAnvilBlockEntityRenderState> {
    private final BlockEntityRendererProvider.Context context;

    public GTAnvilBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public GTAnvilBlockEntityRenderState createRenderState() {
        return new GTAnvilBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(GTAnvilBlockEntity blockEntity,
                                   GTAnvilBlockEntityRenderState state,
                                   float partialTicks,
                                   Vec3 cameraPosition,
                                   ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        // FACING -> rotation:
        // NORTH -> 0
        // EAST -> 90
        // SOUTH -> 180
        // WEST -> 270
        state.rotation = blockEntity.getBlockState().getValue(GTAnvilBlock.FACING).getOpposite().toYRot();

        state.leftItemStack = blockEntity.getLeftStack();
        state.rightItemStack = blockEntity.getRightStack();
    }

    @Override
    public void submit(GTAnvilBlockEntityRenderState state,
                       PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector,
                       CameraRenderState camera) {
        try {
            doSubmit(state, poseStack, submitNodeCollector);
        } catch (GTUnexpectedValidationFailException e) {
            log.error("An unexpected validation fail occurred", e);
        }
    }

    private static void doSubmit(GTAnvilBlockEntityRenderState state,
                                 PoseStack poseStack,
                                 SubmitNodeCollector submitNodeCollector) {
        poseStack.pushPose();

        poseStack.translate(0.5F, 0.5F, 0.5F);
        // Anvil is rotated by "rotation" degrees clockwise around the axis that looks down
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.rotation));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        submitAnvil(state, poseStack, submitNodeCollector);

        if (!state.leftItemStack.isEmpty()) {
            submitLeftThingOnAnvil(state.leftItemStack, poseStack, submitNodeCollector, state.lightCoords);
        }

        if (!state.rightItemStack.isEmpty()) {
            submitRightThingOnAnvil(state.rightItemStack, poseStack, submitNodeCollector, state.lightCoords);
        }

        poseStack.popPose();
    }

    private static void submitAnvil(GTAnvilBlockEntityRenderState state,
                                    PoseStack poseStack,
                                    SubmitNodeCollector submitNodeCollector) {
        GTMaterial material = GTChemicalElementMaterials.SILVER; // TODO

        BlockStateModel anvilModel = GTAnvilModelGetterService.getAnvilModel(material);

        if (anvilModel == null) { // TODO replace some logs with throws, or place stacktrace into logs
            log.error("Could not find a model for the GT Anvil with material={}",
                material);
            return;
        }

        int tintColorToUse = material.colorData().toPackedArgbIntColorOrThrow(); // TODO: add spray tinting color

        submitNodeCollector.submitBlockModel(
            poseStack,
            RenderTypes.cutoutMovingBlock(),
            anvilModel,
            tintColorToUse, // All tint indices are colored into the same color here
            state.lightCoords,
            OverlayTexture.NO_OVERLAY,
            0
        );
    }

    private static void submitLeftThingOnAnvil(ItemStack leftItemStack,
                                               PoseStack poseStack,
                                               SubmitNodeCollector submitNodeCollector,
                                               int lightCoords) {
        GTMaterialThingKind kind = GTMaterialThingKind.getFromStack(leftItemStack);
        GTMaterial material = GTMaterial.getFromStack(leftItemStack);

        if (kind == null || material == null) {
            log.error("Determined null material={} or kind={} for ItemStack={}",
                material, kind, leftItemStack);
            return;
        }

        BlockStateModel leftThingOnAnvilModel =
            GTAnvilModelGetterService.getLeftThingOnAnvilModel(kind, material);

        if (leftThingOnAnvilModel == null) {
            log.error("Could not find a model for a left thing on GT Anvil with kind={}, material={}",
                kind, material);
            return;
        }

        int tintColorToUse = material.colorData().toPackedArgbIntColorOrThrow();

        submitNodeCollector.submitBlockModel(
            poseStack,
            RenderTypes.cutoutMovingBlock(),
            leftThingOnAnvilModel,
            tintColorToUse,
            lightCoords,
            OverlayTexture.NO_OVERLAY,
            0
        );
    }

    private static void submitRightThingOnAnvil(ItemStack rightItemStack,
                                                PoseStack poseStack,
                                                SubmitNodeCollector submitNodeCollector,
                                                int lightCoords) {
        GTMaterialThingKind kind = GTMaterialThingKind.getFromStack(rightItemStack);
        GTMaterial material = GTMaterial.getFromStack(rightItemStack);

        if (kind == null || material == null) {
            log.error("Determined null material={} or kind={} for ItemStack={}",
                material, kind, rightItemStack);
            return;
        }

        BlockStateModel rightThingOnAnvilModel =
            GTAnvilModelGetterService.getRightThingOnAnvilModel(kind, material);

        if (rightThingOnAnvilModel == null) {
            log.error("Could not find a model for a right thing on GT Anvil with kind={}, material={}",
                kind, material);
            return;
        }

        int tintColorToUse = material.colorData().toPackedArgbIntColorOrThrow();

        submitNodeCollector.submitBlockModel(
            poseStack,
            RenderTypes.cutoutMovingBlock(),
            rightThingOnAnvilModel,
            tintColorToUse,
            lightCoords,
            OverlayTexture.NO_OVERLAY,
            0
        );
    }

    /** This gets subscribed with the modBus in another class */
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GTBlockEntityTypes.ANVIL.get(), GTAnvilBlockEntityRenderer::new);
    }
}
