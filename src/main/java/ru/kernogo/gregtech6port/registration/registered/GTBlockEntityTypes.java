package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin.GTEnderGarbageBinBlockEntity;
import ru.kernogo.gregtech6port.registration.GTRegistries;

public final class GTBlockEntityTypes {
    private GTBlockEntityTypes() {}

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GTEnderGarbageBinBlockEntity>> ENDER_GARBAGE_BIN = register(GTEnderGarbageBinBlockEntity::new, GTBlocks.ENDER_GARBAGE_BIN);

    /** Shorthand method for registering BlockEntities corresponding to a Block */
    private static <BE_TYPE extends BlockEntity, BLOCK extends Block & EntityBlock>
    DeferredHolder<BlockEntityType<?>, BlockEntityType<BE_TYPE>> register(
        BlockEntityType.BlockEntitySupplier<BE_TYPE> blockEntitySupplier,
        DeferredBlock<BLOCK> deferredBlock
    ) {
        return GTRegistries.BLOCK_ENTITY_TYPES.register(
            deferredBlock.getKey().location().getPath(),
            () -> {
                BlockEntityType.Builder<BE_TYPE> builder = BlockEntityType.Builder.of(
                    blockEntitySupplier,
                    deferredBlock.get()
                );
                //noinspection DataFlowIssue
                return builder.build(null); // null is fine here for MC 1.21.1
            }
        );
    }

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
