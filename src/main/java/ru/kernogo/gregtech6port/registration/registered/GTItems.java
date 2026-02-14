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
import ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal.PaintRemovalSprayBehavior;
import ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal.PaintSprayBehavior;
import ru.kernogo.gregtech6port.registration.GTRegisters;

public final class GTItems {
    private GTItems() {}

    public static final DeferredItem<Item> MATCH =
        GTRegisters.ITEMS.registerItem("match",
            properties -> new GTLighterLikeItem(
                properties
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(1, 1, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
            ));
    public static final DeferredItem<Item> FIRE_STARTER_DRY_GRASS =
        GTRegisters.ITEMS.registerItem("fire_starter_dry_grass",
            properties -> new GTLighterLikeItem(
                properties
                    .stacksTo(1)
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(1, 1, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.5)
            ));
    public static final DeferredItem<Item> FIRE_STARTER_DRY_TREE_BARK =
        GTRegisters.ITEMS.registerItem("fire_starter_dry_tree_bark",
            properties -> new GTLighterLikeItem(
                properties
                    .stacksTo(1)
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(1, 1, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.55)
            ));
    public static final DeferredItem<Item> MATCH_BOX =
        GTRegisters.ITEMS.registerItem("match_box",
            properties -> new GTLighterLikeItem(
                properties
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(64, 64, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
            ));
    public static final DeferredItem<Item> LIGHTER =
        GTRegisters.ITEMS.registerItem("lighter",
            properties -> new GTLighterLikeItem(
                properties
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(100, 100, null))
                    .component(GTDataComponentTypes.PROC_CHANCE, 1.0)
            ));
    public static final DeferredItem<Item> SHINY_LIGHTER =
        GTRegisters.ITEMS.registerItem("shiny_lighter",
            properties -> new GTLighterLikeItem(
                properties
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(1000, 1000, null))
                    .component(GTDataComponentTypes.PROC_CHANCE, 1.0)
            ));
    public static final DeferredItem<Item> PLASTIC_LIGHTER_BROKEN =
        GTRegisters.ITEMS.registerSimpleItem("plastic_lighter_broken");
    public static final DeferredItem<Item> PLASTIC_LIGHTER =
        GTRegisters.ITEMS.registerItem("plastic_lighter",
            properties -> new GTLighterLikeItem(
                properties
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(100, 100, PLASTIC_LIGHTER_BROKEN.getDelegate()))
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
            ));

    public static final DeferredItem<Item> EMPTY_SPRAY_CAN = GTRegisters.ITEMS.registerSimpleItem("empty_spray_can");

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

    public static final DeferredItem<Item> PAINT_REMOVAL_SPRAY_CAN =
        GTRegisters.ITEMS.registerItem("paint_removal_spray_can",
            properties -> new GTSprayLikeItem(
                new PaintRemovalSprayBehavior(),
                properties
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(256, 256, EMPTY_SPRAY_CAN))
            ));

    public static final DeferredItem<BlockItem> ENDER_GARBAGE_BIN = GTRegisters.ITEMS.registerSimpleBlockItem(GTBlocks.ENDER_GARBAGE_BIN);

    private static DeferredItem<Item> registerPaintSprayCanItem(DyeColor dyeColor) {
        return GTRegisters.ITEMS.registerItem(dyeColor.getName() + "_paint_spray_can",
            properties -> new GTSprayLikeItem(
                new PaintSprayBehavior(dyeColor),
                properties
                    .component(GTDataComponentTypes.ITEM_WITH_USES,
                        new GTItemWithUsesData(512, 512, EMPTY_SPRAY_CAN))
            ));
    }

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
