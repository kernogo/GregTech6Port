package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesData;
import ru.kernogo.gregtech6port.features.items.like.lighter.GTLighterLikeItem;
import ru.kernogo.gregtech6port.features.items.like.spray.GTSprayLikeItem;
import ru.kernogo.gregtech6port.features.items.like.spray.behavior.PaintSprayBehaviorDelegate;
import ru.kernogo.gregtech6port.registration.GTRegistries;

public final class GTItems {
    private GTItems() {}

    public static final DeferredItem<Item> MATCH =
        GTRegistries.ITEMS.register("match",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(1, 1, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
            ));
    public static final DeferredItem<Item> FIRE_STARTER_DRY_GRASS =
        GTRegistries.ITEMS.register("fire_starter_dry_grass",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .stacksTo(1)
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(1, 1, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.5)
            ));
    public static final DeferredItem<Item> FIRE_STARTER_DRY_TREE_BARK =
        GTRegistries.ITEMS.register("fire_starter_dry_tree_bark",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .stacksTo(1)
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(1, 1, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.55)
            ));
    public static final DeferredItem<Item> MATCH_BOX =
        GTRegistries.ITEMS.register("match_box",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(64, 64, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
            ));
    public static final DeferredItem<Item> LIGHTER =
        GTRegistries.ITEMS.register("lighter",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(100, 100, null))
                    .component(GTDataComponentTypes.PROC_CHANCE, 1.0)
            ));
    public static final DeferredItem<Item> SHINY_LIGHTER =
        GTRegistries.ITEMS.register("shiny_lighter",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(1000, 1000, null))
                    .component(GTDataComponentTypes.PROC_CHANCE, 1.0)
            ));
    public static final DeferredItem<Item> PLASTIC_LIGHTER_BROKEN =
        GTRegistries.ITEMS.registerSimpleItem("plastic_lighter_broken");
    public static final DeferredItem<Item> PLASTIC_LIGHTER =
        GTRegistries.ITEMS.register("plastic_lighter",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(100, 100, PLASTIC_LIGHTER_BROKEN.getDelegate()))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
            ));

    public static final DeferredItem<Item> EMPTY_SPRAY_CAN = GTRegistries.ITEMS.registerSimpleItem("empty_spray_can");

    public static final DeferredItem<Item> WHITE_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.WHITE);
    public static final DeferredItem<Item> ORANGE_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.ORANGE);
    public static final DeferredItem<Item> MAGENTA_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.MAGENTA);
    public static final DeferredItem<Item> LIGHT_BLUE_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.LIGHT_BLUE);
    public static final DeferredItem<Item> YELLOW_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.YELLOW);
    public static final DeferredItem<Item> LIME_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.LIME);
    public static final DeferredItem<Item> PINK_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.PINK);
    public static final DeferredItem<Item> GRAY_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.GRAY);
    public static final DeferredItem<Item> LIGHT_GRAY_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.LIGHT_GRAY);
    public static final DeferredItem<Item> CYAN_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.CYAN);
    public static final DeferredItem<Item> PURPLE_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.PURPLE);
    public static final DeferredItem<Item> BLUE_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.BLUE);
    public static final DeferredItem<Item> BROWN_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.BROWN);
    public static final DeferredItem<Item> GREEN_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.GREEN);
    public static final DeferredItem<Item> RED_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.RED);
    public static final DeferredItem<Item> BLACK_PAINT_SPRAY_CAN = registerPaintSprayCanItem(DyeColor.BLACK);

    public static final DeferredItem<BlockItem> ENDER_GARBAGE_BIN = GTRegistries.ITEMS.registerSimpleBlockItem(GTBlocks.ENDER_GARBAGE_BIN);

    private static DeferredItem<Item> registerPaintSprayCanItem(DyeColor dyeColor) {
        return GTRegistries.ITEMS.register(dyeColor.getName() + "_paint_spray_can",
            () -> new GTSprayLikeItem(
                new PaintSprayBehaviorDelegate(),
                new Item.Properties()
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(512, 512, EMPTY_SPRAY_CAN))
            ));
    }

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
