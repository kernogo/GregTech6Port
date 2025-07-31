package ru.kernogo.gregtech6port.datagen.item_model;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

/** Datagen for item models */
public final class GTItemDatagen extends ItemModelProvider {
    private final GTItemWithUsesVariantItemModelMaker itemWithUsesVariantItemModelMaker;

    public GTItemDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GregTech6Port.MODID, existingFileHelper);
        this.itemWithUsesVariantItemModelMaker = new GTItemWithUsesVariantItemModelMaker(this);
    }

    @Override
    protected void registerModels() {
        makeBasicLighterLikeItem(GTItems.MATCH);
        makeBasicLighterLikeItem(GTItems.FIRE_STARTER_DRY_GRASS);
        makeBasicLighterLikeItem(GTItems.FIRE_STARTER_DRY_TREE_BARK);

        make2StageLighterLikeItem(GTItems.MATCH_BOX);
        make3StageLighterLikeItem(GTItems.LIGHTER);
        make3StageLighterLikeItem(GTItems.SHINY_LIGHTER);
        makeBasicLighterLikeItem(GTItems.PLASTIC_LIGHTER_BROKEN);
        make3StageLighterLikeItem(GTItems.PLASTIC_LIGHTER);

        makeBasicSprayLikeItem(GTItems.EMPTY_SPRAY_CAN);

        make2StageSprayLikeItem(GTItems.WHITE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.ORANGE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.MAGENTA_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.LIGHT_BLUE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.YELLOW_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.LIME_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.PINK_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.GRAY_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.LIGHT_GRAY_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.CYAN_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.PURPLE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.BLUE_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.BROWN_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.GREEN_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.RED_PAINT_SPRAY_CAN);
        make2StageSprayLikeItem(GTItems.BLACK_PAINT_SPRAY_CAN);

        make2StageSprayLikeItem(GTItems.PAINT_REMOVAL_SPRAY_CAN);
    }

    private void makeBasicLighterLikeItem(DeferredItem<Item> deferredItem) {
        makeBasicItem(deferredItem, "like/lighter");
    }

    private void makeBasicSprayLikeItem(DeferredItem<Item> deferredItem) {
        makeBasicItem(deferredItem, "like/spray");
    }

    private void make2StageLighterLikeItem(DeferredItem<Item> deferredItem) {
        itemWithUsesVariantItemModelMaker.makeItemWithUses2StageModel(deferredItem, "like/lighter");
    }

    private void make3StageLighterLikeItem(DeferredItem<Item> deferredItem) {
        itemWithUsesVariantItemModelMaker.makeItemWithUses3StageModel(deferredItem, "like/lighter");
    }

    private void make2StageSprayLikeItem(DeferredItem<Item> deferredItem) {
        itemWithUsesVariantItemModelMaker.makeItemWithUses2StageModel(deferredItem, "like/spray");
    }

    /**
     * Creates a simple item model "itemName.json"
     * that points to a texture at the location "item/texturePathPrefix/itemName.png"
     */
    private void makeBasicItem(DeferredItem<Item> deferredItem, String texturePathPrefix) {
        String itemName = deferredItem.getKey().location().getPath();
        getBuilder(itemName)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc("item/" + texturePathPrefix + "/" + itemName));
    }
}
