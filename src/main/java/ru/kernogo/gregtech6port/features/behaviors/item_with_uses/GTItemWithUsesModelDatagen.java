package ru.kernogo.gregtech6port.features.behaviors.item_with_uses;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.List;
import java.util.stream.Stream;

/** Datagen generating model JSONs for Items With Uses */
public final class GTItemWithUsesModelDatagen extends ModelProvider {
    public GTItemWithUsesModelDatagen(PackOutput output) {
        super(output, GregTech6Port.MODID);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        makeBasicLighterLikeItem(itemModels, GTItems.MATCH);
        makeBasicLighterLikeItem(itemModels, GTItems.FIRE_STARTER_DRY_GRASS);
        makeBasicLighterLikeItem(itemModels, GTItems.FIRE_STARTER_DRY_TREE_BARK);

        make2StageLighterLikeItem(itemModels, GTItems.MATCH_BOX);
        make3StageLighterLikeItem(itemModels, GTItems.LIGHTER);
        make3StageLighterLikeItem(itemModels, GTItems.SHINY_LIGHTER);
        makeBasicLighterLikeItem(itemModels, GTItems.PLASTIC_LIGHTER_BROKEN);
        make3StageLighterLikeItem(itemModels, GTItems.PLASTIC_LIGHTER);

        makeBasicSprayLikeItem(itemModels, GTItems.EMPTY_SPRAY_CAN);

        make2StageSprayLikeItem(itemModels, GTItems.WHITE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.ORANGE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.MAGENTA_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.LIGHT_BLUE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.YELLOW_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.LIME_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.PINK_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.GRAY_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.LIGHT_GRAY_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.CYAN_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.PURPLE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.BLUE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.BROWN_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.GREEN_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.RED_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(itemModels, GTItems.BLACK_PAINT_SPRAY_CAN);

        make2StageSprayLikeItem(itemModels, GTItems.PAINT_REMOVAL_SPRAY_CAN);
    }

    private void makeBasicLighterLikeItem(ItemModelGenerators itemModels, DeferredItem<Item> deferredItem) {
        ResourceLocation textureLoc = modLocation("item/like/lighter/" + deferredItem.getKey().location().getPath());
        makeBasicItem(itemModels, deferredItem, textureLoc);
    }

    private void makeBasicSprayLikeItem(ItemModelGenerators itemModels, DeferredItem<Item> deferredItem) {
        ResourceLocation textureLoc = modLocation("item/like/spray/" + deferredItem.getKey().location().getPath());
        makeBasicItem(itemModels, deferredItem, textureLoc);
    }

    private void make2StageLighterLikeItem(ItemModelGenerators itemModels, DeferredItem<Item> deferredItem) {
        ResourceLocation textureBaseLoc = modLocation("item/like/lighter/");
        make2StageVariantModel(itemModels, deferredItem, textureBaseLoc);
    }

    private void make3StageLighterLikeItem(ItemModelGenerators itemModels, DeferredItem<Item> deferredItem) {
        ResourceLocation textureBaseLoc = modLocation("item/like/lighter/");
        make3StageVariantModel(itemModels, deferredItem, textureBaseLoc);
    }

    private void make2StageSprayLikeItem(ItemModelGenerators itemModels, DeferredItem<Item> deferredItem) {
        ResourceLocation textureBaseLoc = modLocation("item/like/spray/");
        make2StageVariantModel(itemModels, deferredItem, textureBaseLoc);
    }

    private void makeBasicItem(ItemModelGenerators itemModels, DeferredItem<Item> deferredItem, ResourceLocation textureLoc) {
        ModelTemplates.FLAT_ITEM.create(
            deferredItem.get(),
            new TextureMapping()
                .put(TextureSlot.LAYER0, textureLoc),
            itemModels.modelOutput
        );

        itemModels.itemModelOutput.accept(
            deferredItem.get(),
            ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(deferredItem.get()))
        );
    }

    /** Generates an Items Model Definition with 2 visible stages of use - full, and partially used */
    private void make2StageVariantModel(ItemModelGenerators itemModels,
                                        DeferredItem<Item> deferredItem,
                                        ResourceLocation textureBaseLoc) {
        ResourceLocation fullModelLoc = ModelLocationUtils.getModelLocation(deferredItem.get())
            .withSuffix("/full");
        ResourceLocation partiallyUsedModelLoc = ModelLocationUtils.getModelLocation(deferredItem.get())
            .withSuffix("/partially_used");

        ModelTemplates.FLAT_ITEM.create(
            fullModelLoc,
            new TextureMapping()
                .put(TextureSlot.LAYER0, textureBaseLoc.withSuffix(deferredItem.getKey().location().getPath() + "/full")),
            itemModels.modelOutput
        );
        ModelTemplates.FLAT_ITEM.create(
            partiallyUsedModelLoc,
            new TextureMapping()
                .put(TextureSlot.LAYER0, textureBaseLoc.withSuffix(deferredItem.getKey().location().getPath() + "/partially_used")),
            itemModels.modelOutput
        );

        itemModels.itemModelOutput.accept(
            deferredItem.get(),
            ItemModelUtils.select(
                new GTItemWithUsesUseStageModelProperty(),
                new SelectItemModel.SwitchCase<>(
                    List.of(GTItemWithUsesUseStageModelProperty.UseStage.FULL),
                    ItemModelUtils.plainModel(fullModelLoc)
                ),
                new SelectItemModel.SwitchCase<>(
                    List.of(GTItemWithUsesUseStageModelProperty.UseStage.PARTIALLY_USED),
                    ItemModelUtils.plainModel(partiallyUsedModelLoc)
                )
            )
        );
    }

    /** Generates an Items Model Definition with 3 visible stages of use - full, partially used, and empty */
    private void make3StageVariantModel(ItemModelGenerators itemModels,
                                        DeferredItem<Item> deferredItem,
                                        ResourceLocation textureBaseLoc) {
        ResourceLocation fullModelLoc = ModelLocationUtils.getModelLocation(deferredItem.get())
            .withSuffix("/full");
        ResourceLocation partiallyUsedModelLoc = ModelLocationUtils.getModelLocation(deferredItem.get())
            .withSuffix("/partially_used");
        ResourceLocation emptyModelLoc = ModelLocationUtils.getModelLocation(deferredItem.get())
            .withSuffix("/empty");

        ModelTemplates.FLAT_ITEM.create(
            fullModelLoc,
            new TextureMapping()
                .put(TextureSlot.LAYER0, textureBaseLoc.withSuffix(deferredItem.getKey().location().getPath() + "/full")),
            itemModels.modelOutput
        );
        ModelTemplates.FLAT_ITEM.create(
            partiallyUsedModelLoc,
            new TextureMapping()
                .put(TextureSlot.LAYER0, textureBaseLoc.withSuffix(deferredItem.getKey().location().getPath() + "/partially_used")),
            itemModels.modelOutput
        );
        ModelTemplates.FLAT_ITEM.create(
            emptyModelLoc,
            new TextureMapping()
                .put(TextureSlot.LAYER0, textureBaseLoc.withSuffix(deferredItem.getKey().location().getPath() + "/empty")),
            itemModels.modelOutput
        );

        itemModels.itemModelOutput.accept(
            deferredItem.get(),
            ItemModelUtils.select(
                new GTItemWithUsesUseStageModelProperty(),
                new SelectItemModel.SwitchCase<>(
                    List.of(GTItemWithUsesUseStageModelProperty.UseStage.FULL),
                    ItemModelUtils.plainModel(fullModelLoc)
                ),
                new SelectItemModel.SwitchCase<>(
                    List.of(GTItemWithUsesUseStageModelProperty.UseStage.PARTIALLY_USED),
                    ItemModelUtils.plainModel(partiallyUsedModelLoc)
                ),
                new SelectItemModel.SwitchCase<>(
                    List.of(GTItemWithUsesUseStageModelProperty.UseStage.EMPTY),
                    ItemModelUtils.plainModel(emptyModelLoc)
                )
            )
        );
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
