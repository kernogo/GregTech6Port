package ru.kernogo.gregtech6port.datagen;

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
    public GTItemDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GregTech6Port.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(GTItems.MATCH.get());
        basicItem(GTItems.FIRE_STARTER_DRY_GRASS.get());
        basicItem(GTItems.FIRE_STARTER_DRY_TREE_BARK.get());

        makeItemWithUses2StageModel(GTItems.MATCH_BOX);
        makeItemWithUses3StageModel(GTItems.LIGHTER);
        makeItemWithUses3StageModel(GTItems.SHINY_LIGHTER);
        basicItem(GTItems.PLASTIC_LIGHTER_BROKEN.get());
        makeItemWithUses3StageModel(GTItems.PLASTIC_LIGHTER);

        basicItem(GTItems.EMPTY_SPRAY_CAN.get());

        makeItemWithUses2StageModel(GTItems.WHITE_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.ORANGE_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.MAGENTA_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.LIGHT_BLUE_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.YELLOW_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.LIME_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.PINK_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.GRAY_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.LIGHT_GRAY_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.CYAN_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.PURPLE_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.BLUE_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.BROWN_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.GREEN_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.RED_PAINT_SPRAY_CAN);
        makeItemWithUses2StageModel(GTItems.BLACK_PAINT_SPRAY_CAN);

        makeItemWithUses2StageModel(GTItems.PAINT_REMOVAL_SPRAY_CAN);
    }

    /**
     * Model for items with uses items with 2 textures - full and partially used. <br>
     * Corresponding item Property Function is {@link ru.kernogo.gregtech6port.registration.registration_event_subscribers.RegisterItemProperties#makeItemWithUses2StageFunc}
     */
    private void makeItemWithUses2StageModel(DeferredItem<Item> deferredItem) {
        String key = deferredItem.getKey().location().getPath();

        basicItem(modLoc(key + "_full"));
        basicItem(modLoc(key + "_partially_used"));

        getBuilder(key)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc("item/" + key + "_full"))

            .override()
            .predicate(modLoc("use_stage"), 0.0f)
            .model(getExistingFile(modLoc("item/" + key + "_full")))
            .end()

            .override()
            .predicate(modLoc("use_stage"), 0.5f)
            .model(getExistingFile(modLoc("item/" + key + "_partially_used")))
            .end();
    }

    /**
     * Model for items with uses with 3 textures - full, partially used, and empty. <br>
     * Corresponding item Property Function is {@link ru.kernogo.gregtech6port.registration.registration_event_subscribers.RegisterItemProperties#makeItemWithUses3StageFunc}
     */
    private void makeItemWithUses3StageModel(DeferredItem<Item> deferredItem) {
        String key = deferredItem.getKey().location().getPath();

        basicItem(modLoc(key + "_full"));
        basicItem(modLoc(key + "_partially_used"));
        basicItem(modLoc(key + "_empty"));

        getBuilder(key)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc("item/" + key + "_full"))

            .override()
            .predicate(modLoc("use_stage"), 0.0f)
            .model(getExistingFile(modLoc("item/" + key + "_full")))
            .end()

            .override()
            .predicate(modLoc("use_stage"), 0.5f)
            .model(getExistingFile(modLoc("item/" + key + "_partially_used")))
            .end()

            .override()
            .predicate(modLoc("use_stage"), 1.0f)
            .model(getExistingFile(modLoc("item/" + key + "_empty")))
            .end();
    }
}
