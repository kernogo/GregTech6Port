package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.items.like.lighter.GTLighterLikeItem;
import ru.kernogo.gregtech6port.registration.GTRegistries;

public final class GTItems {
    private GTItems() {}

    public static final DeferredItem<Item> MATCH =
        GTRegistries.ITEMS.register("match",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.SINGLE_USE, Unit.INSTANCE)
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
                    .component(GTDataComponentTypes.BREAKS_INTO, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR))
            ));
    public static final DeferredItem<Item> FIRE_STARTER_DRY_GRASS =
        GTRegistries.ITEMS.register("fire_starter_dry_grass",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .stacksTo(1)
                    .component(GTDataComponentTypes.SINGLE_USE, Unit.INSTANCE)
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.5)
                    .component(GTDataComponentTypes.BREAKS_INTO, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR))
            ));
    public static final DeferredItem<Item> FIRE_STARTER_DRY_TREE_BARK =
        GTRegistries.ITEMS.register("fire_starter_dry_tree_bark",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .stacksTo(1)
                    .component(GTDataComponentTypes.SINGLE_USE, Unit.INSTANCE)
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.55)
                    .component(GTDataComponentTypes.BREAKS_INTO, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR))
            ));
    public static final DeferredItem<Item> MATCH_BOX =
        GTRegistries.ITEMS.register("match_box",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.REMAINING_USES, 64)
                    .component(GTDataComponentTypes.MAX_REMAINING_USES, 64)
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
                    .component(GTDataComponentTypes.BREAKS_INTO, BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR))
            ));
    public static final DeferredItem<Item> LIGHTER =
        GTRegistries.ITEMS.register("lighter",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.REMAINING_USES, 100)
                    .component(GTDataComponentTypes.MAX_REMAINING_USES, 100)
                    .component(GTDataComponentTypes.PROC_CHANCE, 1.0)
            ));
    public static final DeferredItem<Item> SHINY_LIGHTER =
        GTRegistries.ITEMS.register("shiny_lighter",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.REMAINING_USES, 1000)
                    .component(GTDataComponentTypes.MAX_REMAINING_USES, 1000)
                    .component(GTDataComponentTypes.PROC_CHANCE, 1.0)
            ));
    public static final DeferredItem<Item> PLASTIC_LIGHTER_BROKEN =
        GTRegistries.ITEMS.registerSimpleItem("plastic_lighter_broken");
    public static final DeferredItem<Item> PLASTIC_LIGHTER =
        GTRegistries.ITEMS.register("plastic_lighter",
            () -> new GTLighterLikeItem(
                new Item.Properties()
                    .component(GTDataComponentTypes.REMAINING_USES, 100)
                    .component(GTDataComponentTypes.MAX_REMAINING_USES, 100)
                    .component(GTDataComponentTypes.PROC_CHANCE, 0.9)
                    .component(GTDataComponentTypes.BREAKS_INTO, PLASTIC_LIGHTER_BROKEN.getDelegate())
            ));

    public static final DeferredItem<BlockItem> ENDER_GARBAGE_BIN = GTRegistries.ITEMS.registerSimpleBlockItem(GTBlocks.ENDER_GARBAGE_BIN);

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
