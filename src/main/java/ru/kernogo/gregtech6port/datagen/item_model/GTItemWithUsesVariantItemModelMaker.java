package ru.kernogo.gregtech6port.datagen.item_model;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.DeferredItem;

/** Contains methods to datagen item models for items with uses that have variant textures for different item states */
final class GTItemWithUsesVariantItemModelMaker {
    private final GTItemDatagen gtItemDatagen;

    /** @param gtItemDatagen The instance of {@link GTItemDatagen} */
    GTItemWithUsesVariantItemModelMaker(GTItemDatagen gtItemDatagen) {
        this.gtItemDatagen = gtItemDatagen;
    }

    /**
     * Creates models for an item with uses items with 2 variant textures - full and partially used. <br>
     * Creates:
     * <ul>
     *   <li>3 variant models:
     *       <ul>
     *           <li>"itemName__full.json" pointing to a texture at the location "item/texturePathPrefix/itemName/full.png"</li>
     *           <li>"itemName__partially_used.json" pointing to a texture at the location "item/texturePathPrefix/itemName/partially_used.png"</li>
     *       </ul>
     *   </li>
     *   <li>main item model "itemName.json" pointing to the 2 variant models and using the item properties to determine which model to use</li>
     * </ul>
     * Corresponding item Property Function is {@link ru.kernogo.gregtech6port.registration.registration.GTItemPropertiesRegistration#makeItemWithUses2StageFunc}
     */
    void makeItemWithUses2StageModel(DeferredItem<Item> deferredItem, String texturePathPrefix) {
        String itemName = deferredItem.getKey().location().getPath();

        makeItemVariantModel(itemName, "full", texturePathPrefix);
        makeItemVariantModel(itemName, "partially_used", texturePathPrefix);

        gtItemDatagen.getBuilder(itemName)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", gtItemDatagen.modLoc("item/" + texturePathPrefix + "/" + itemName + "/" + "full"))

            .override()
            .predicate(gtItemDatagen.modLoc("use_stage"), 0.0f)
            .model(gtItemDatagen.getExistingFile(gtItemDatagen.modLoc("item/" + itemName + "__" + "full")))
            .end()

            .override()
            .predicate(gtItemDatagen.modLoc("use_stage"), 0.5f)
            .model(gtItemDatagen.getExistingFile(gtItemDatagen.modLoc("item/" + itemName + "__" + "partially_used")))
            .end();
    }

    /**
     * Creates models for an item with uses with 3 variant textures - full, partially used, and empty. <br>
     * Creates:
     * <ul>
     *   <li>3 variant models:
     *       <ul>
     *           <li>"itemName__full.json" pointing to a texture at the location "item/texturePathPrefix/itemName/full.png"</li>
     *           <li>"itemName__partially_used.json" pointing to a texture at the location "item/texturePathPrefix/itemName/partially_used.png"</li>
     *           <li>"itemName__empty.json" pointing to a texture at the location "item/texturePathPrefix/itemName/empty.png"</li>
     *       </ul>
     *   </li>
     *   <li>main item model "itemName.json" pointing to the 3 variant models and using the item properties to determine which model to use</li>
     * </ul>
     * Corresponding item Property Function is {@link ru.kernogo.gregtech6port.registration.registration.GTItemPropertiesRegistration#makeItemWithUses3StageFunc}
     */
    void makeItemWithUses3StageModel(DeferredItem<Item> deferredItem, String texturePathPrefix) {
        String itemName = deferredItem.getKey().location().getPath();

        makeItemVariantModel(itemName, "full", texturePathPrefix);
        makeItemVariantModel(itemName, "partially_used", texturePathPrefix);
        makeItemVariantModel(itemName, "empty", texturePathPrefix);

        gtItemDatagen.getBuilder(itemName)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", gtItemDatagen.modLoc("item/" + texturePathPrefix + "/" + itemName + "/" + "full"))

            .override()
            .predicate(gtItemDatagen.modLoc("use_stage"), 0.0f)
            .model(gtItemDatagen.getExistingFile(gtItemDatagen.modLoc("item/" + itemName + "__" + "full")))
            .end()

            .override()
            .predicate(gtItemDatagen.modLoc("use_stage"), 0.5f)
            .model(gtItemDatagen.getExistingFile(gtItemDatagen.modLoc("item/" + itemName + "__" + "partially_used")))
            .end()

            .override()
            .predicate(gtItemDatagen.modLoc("use_stage"), 1.0f)
            .model(gtItemDatagen.getExistingFile(gtItemDatagen.modLoc("item/" + itemName + "__" + "empty")))
            .end();
    }

    /**
     * Creates a simple item model "itemName__textureVariantName.json"
     * that points to a texture at the location "item/texturePathPrefix/itemName/textureVariantName.png"
     */
    private void makeItemVariantModel(String itemName, String textureVariantName, String texturePathPrefix) {
        gtItemDatagen.getBuilder(itemName + "__" + textureVariantName)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", gtItemDatagen.modLoc("item/" + texturePathPrefix + "/" + itemName + "/" + textureVariantName));
    }
}
