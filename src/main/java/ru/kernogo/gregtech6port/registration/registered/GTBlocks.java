package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin.GTEnderGarbageBinBlock;
import ru.kernogo.gregtech6port.features.blockentities.material.anvil.GTAnvilMaterialBlock;
import ru.kernogo.gregtech6port.registration.GTRegisters;

public final class GTBlocks {
    private GTBlocks() {}

    public static final DeferredBlock<Block> ENDER_GARBAGE_BIN =
        GTRegisters.BLOCKS.registerBlock("ender_garbage_bin", GTEnderGarbageBinBlock::new,
            BlockBehaviour.Properties.of().noOcclusion());
    public static final DeferredBlock<Block> STEEL_ANVIL = // TODO: better registration
        GTRegisters.BLOCKS.registerBlock("steel_anvil", GTAnvilMaterialBlock::new,
            BlockBehaviour.Properties.of().noOcclusion());

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
