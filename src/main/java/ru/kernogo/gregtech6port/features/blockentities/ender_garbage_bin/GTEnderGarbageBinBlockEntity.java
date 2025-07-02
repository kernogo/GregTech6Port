package ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;

public class GTEnderGarbageBinBlockEntity extends BlockEntity {
    public GTEnderGarbageBinBlockEntity(BlockPos pos, BlockState blockState) {
        super(GTBlockEntityTypes.ENDER_GARBAGE_BIN.get(), pos, blockState);
    }
}
