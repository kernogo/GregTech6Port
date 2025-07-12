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

        lighter2StageModel(GTItems.MATCH_BOX);
        lighter3StageModel(GTItems.LIGHTER);
        lighter3StageModel(GTItems.SHINY_LIGHTER);
        basicItem(GTItems.PLASTIC_LIGHTER_BROKEN.get());
        lighter3StageModel(GTItems.PLASTIC_LIGHTER);
    }

    /**
     * Model for lighter-like items with 3 textures - full, partially used, and empty. <br>
     * Corresponding item Property Function is {@link ru.kernogo.gregtech6port.registration.registration_event_subscribers.RegisterItemProperties#lighter2StagePropFunc}
     */
    private void lighter2StageModel(DeferredItem<Item> deferredItem) {
        String key = deferredItem.getKey().location().getPath();

        basicItem(modLoc(key + "_full"));
        basicItem(modLoc(key + "_partially_used"));

        getBuilder(key)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc("item/" + key + "_full"))

            .override()
            .predicate(modLoc("gt_use_stage"), 0.0f)
            .model(getExistingFile(modLoc("item/" + key + "_full")))
            .end()

            .override()
            .predicate(modLoc("gt_use_stage"), 0.5f)
            .model(getExistingFile(modLoc("item/" + key + "_partially_used")))
            .end();
    }

    /**
     * Model for lighter-like items with 3 textures - full, partially used, and empty. <br>
     * Corresponding item Property Function is {@link ru.kernogo.gregtech6port.registration.registration_event_subscribers.RegisterItemProperties#lighter3StagePropFunc}
     */
    private void lighter3StageModel(DeferredItem<Item> deferredItem) {
        String key = deferredItem.getKey().location().getPath();

        basicItem(modLoc(key + "_full"));
        basicItem(modLoc(key + "_partially_used"));
        basicItem(modLoc(key + "_empty"));

        getBuilder(key)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", modLoc("item/" + key + "_full"))

            .override()
            .predicate(modLoc("gt_use_stage"), 0.0f)
            .model(getExistingFile(modLoc("item/" + key + "_full")))
            .end()

            .override()
            .predicate(modLoc("gt_use_stage"), 0.5f)
            .model(getExistingFile(modLoc("item/" + key + "_partially_used")))
            .end()

            .override()
            .predicate(modLoc("gt_use_stage"), 1.0f)
            .model(getExistingFile(modLoc("item/" + key + "_empty")))
            .end();
    }
}
