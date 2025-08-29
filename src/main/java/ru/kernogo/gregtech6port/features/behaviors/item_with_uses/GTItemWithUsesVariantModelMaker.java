package ru.kernogo.gregtech6port.features.behaviors.item_with_uses;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.DeferredItem;

/**
 * Helper class that is used in {@link GTItemWithUsesModelDatagen} to actually create variant models -
 * models that can have 2 or 3 visible stages of use.
 */
final class GTItemWithUsesVariantModelMaker {
    private final GTItemWithUsesModelDatagen provider;

    /** @param provider The instance of {@link GTItemWithUsesModelDatagen} */
    GTItemWithUsesVariantModelMaker(GTItemWithUsesModelDatagen provider) {
        this.provider = provider;
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
     * Corresponding item Property Function is {@link GTItemWithUsesItemPropertiesRegistration#make2StageFunc}
     */
    void make2StageVariantModel(DeferredItem<Item> deferredItem, String texturePathPrefix) {
        String itemName = deferredItem.getKey().location().getPath();

        makeItemVariantModel(itemName, "full", texturePathPrefix);
        makeItemVariantModel(itemName, "partially_used", texturePathPrefix);

        provider.getBuilder(itemName)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", provider.modLoc("item/" + texturePathPrefix + "/" + itemName + "/" + "full"))

            .override()
            .predicate(provider.modLoc("use_stage"), 0.0f)
            .model(provider.getExistingFile(provider.modLoc("item/" + itemName + "__" + "full")))
            .end()

            .override()
            .predicate(provider.modLoc("use_stage"), 0.5f)
            .model(provider.getExistingFile(provider.modLoc("item/" + itemName + "__" + "partially_used")))
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
     * Corresponding item Property Function is {@link GTItemWithUsesItemPropertiesRegistration#make3StageFunc}
     */
    void make3StageVariantModel(DeferredItem<Item> deferredItem, String texturePathPrefix) {
        String itemName = deferredItem.getKey().location().getPath();

        makeItemVariantModel(itemName, "full", texturePathPrefix);
        makeItemVariantModel(itemName, "partially_used", texturePathPrefix);
        makeItemVariantModel(itemName, "empty", texturePathPrefix);

        provider.getBuilder(itemName)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", provider.modLoc("item/" + texturePathPrefix + "/" + itemName + "/" + "full"))

            .override()
            .predicate(provider.modLoc("use_stage"), 0.0f)
            .model(provider.getExistingFile(provider.modLoc("item/" + itemName + "__" + "full")))
            .end()

            .override()
            .predicate(provider.modLoc("use_stage"), 0.5f)
            .model(provider.getExistingFile(provider.modLoc("item/" + itemName + "__" + "partially_used")))
            .end()

            .override()
            .predicate(provider.modLoc("use_stage"), 1.0f)
            .model(provider.getExistingFile(provider.modLoc("item/" + itemName + "__" + "empty")))
            .end();
    }

    /**
     * Creates a simple item model "itemName__textureVariantName.json"
     * that points to a texture at the location "item/texturePathPrefix/itemName/textureVariantName.png"
     */
    private void makeItemVariantModel(String itemName, String textureVariantName, String texturePathPrefix) {
        provider.getBuilder(itemName + "__" + textureVariantName)
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", provider.modLoc("item/" + texturePathPrefix + "/" + itemName + "/" + textureVariantName));
    }
}
