package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin.GTEnderGarbageBinBlockEntity;
import ru.kernogo.gregtech6port.registration.GTRegisters;

public final class GTBlockEntityTypes {
    private GTBlockEntityTypes() {}

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GTEnderGarbageBinBlockEntity>> ENDER_GARBAGE_BIN =
        register(GTEnderGarbageBinBlockEntity::new, GTBlocks.ENDER_GARBAGE_BIN);

    /** Shorthand method for registering BlockEntities corresponding to a Block */
    private static <BE_TYPE extends BlockEntity>
    DeferredHolder<BlockEntityType<?>, BlockEntityType<BE_TYPE>> register(
        BlockEntityType.BlockEntitySupplier<BE_TYPE> blockEntitySupplier,
        DeferredBlock<Block> deferredBlock
    ) {
        return GTRegisters.BLOCK_ENTITY_TYPES.register(
            deferredBlock.getKey().identifier().getPath(),
            () -> new BlockEntityType<>(blockEntitySupplier, deferredBlock.get())
        );
    }

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
