package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.items.GTMatchItem;
import ru.kernogo.gregtech6port.registration.GTRegistries;

public class GTItems {
    public static final DeferredItem<Item> MATCH = GTRegistries.ITEMS.registerItem("match", GTMatchItem::new);

    public static final DeferredItem<BlockItem> ENDER_GARBAGE_BIN = GTRegistries.ITEMS.registerSimpleBlockItem(GTBlocks.ENDER_GARBAGE_BIN);

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
