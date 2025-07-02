package ru.kernogo.gregtech6port.registration.registered;

import net.neoforged.neoforge.registries.DeferredBlock;
import ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin.GTEnderGarbageBinBlock;
import ru.kernogo.gregtech6port.registration.GTRegistries;

public class GTBlocks {
    public static final DeferredBlock<GTEnderGarbageBinBlock> ENDER_GARBAGE_BIN = GTRegistries.BLOCKS.registerBlock("ender_garbage_bin", GTEnderGarbageBinBlock::new);

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
