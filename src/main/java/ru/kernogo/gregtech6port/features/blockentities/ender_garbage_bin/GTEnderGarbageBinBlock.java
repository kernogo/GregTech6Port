package ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class GTEnderGarbageBinBlock extends Block implements EntityBlock {
    private static final VoxelShape SHAPE = Shapes.or(
        Block.box(0, 12, 0, 16, 16, 16), // top part
        Block.box(1, 0, 1, 15, 12, 15) // bottom part
    );

    public GTEnderGarbageBinBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GTEnderGarbageBinBlockEntity(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
