package ru.kernogo.gregtech6port.features.blockentities.anvil;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.Map;

/** Material x - is an x that has Material variants */
@Slf4j
public class GTAnvilBlock extends Block implements EntityBlock {
    private static final Map<Direction.Axis, VoxelShape> SHAPE_BY_FACING = Shapes.rotateHorizontalAxis(
        Block.box(0, 0, 4, 16, 12, 12)
    );

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public GTAnvilBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GTAnvilBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facingDirection = context.getHorizontalDirection().getOpposite();

        return defaultBlockState().setValue(FACING, facingDirection);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_FACING.get(state.getValue(FACING).getAxis());
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack,
                                          BlockState state,
                                          Level level,
                                          BlockPos pos,
                                          Player player,
                                          InteractionHand hand,
                                          BlockHitResult hitResult) {
        try {
            return doUseItemOn(itemStack, state, level, pos, player, hand, hitResult);
        } catch (GTUnexpectedValidationFailException e) {
            log.error("An unexpected validation fail occurred", e);
            return InteractionResult.FAIL;
        }
    }

    private InteractionResult doUseItemOn(ItemStack itemStack,
                                          BlockState state,
                                          Level level,
                                          BlockPos pos,
                                          Player player,
                                          InteractionHand hand,
                                          BlockHitResult hitResult) {
        GTAnvilBlockEntity anvilBlockEntity = getAnvilBlockEntity(level, pos);
        if (anvilBlockEntity == null) {
            return InteractionResult.FAIL;
        }

        HitPlacement hitPlacement = determineHitPlacement(hitResult, anvilBlockEntity);

        if (itemStack.isEmpty()) {
            return switch (hitPlacement) {
                case TOP_LEFT -> {
                    if (anvilBlockEntity.getLeftStack().isEmpty()) {
                        if (!anvilBlockEntity.getRightStack().isEmpty()) {
                            anvilBlockEntity.spreadThingsOnAnvilLeftAndRight(player);
                        }
                    } else {
                        anvilBlockEntity.removeLeftStackIntoPlayerInventory(player);
                    }
                    yield InteractionResult.SUCCESS;
                }
                case TOP_RIGHT -> {
                    if (anvilBlockEntity.getRightStack().isEmpty()) {
                        if (!anvilBlockEntity.getLeftStack().isEmpty()) {
                            anvilBlockEntity.spreadThingsOnAnvilLeftAndRight(player);
                        }
                    } else {
                        anvilBlockEntity.removeRightStackIntoPlayerInventory(player);
                    }
                    yield InteractionResult.SUCCESS;
                }
                default -> InteractionResult.FAIL;
            };
        }

        if (GTAnvilBlockCommon.isHammerItem(itemStack)) {
            switch (hitPlacement) {
                case TOP_LEFT, TOP_RIGHT -> handleHammerHit(); // TODO recipes
                case SIDE_LEFT -> handleHammerHit();
                case SIDE_RIGHT -> handleHammerHit();
            }

            return InteractionResult.SUCCESS;
        }

        if (GTAnvilBlockCommon.isSupportedThingOnAnvilExceptHammer(itemStack)) {
            switch (hitPlacement) {
                case TOP_LEFT -> anvilBlockEntity.addLeftStack(itemStack);
                case TOP_RIGHT -> anvilBlockEntity.addRightStack(itemStack);
                default -> {} // Nothing
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    private void handleHammerHit() {

    }

    /**
     * In vanilla (at least MC 26.1) there is
     * {@link net.minecraft.world.level.block.SelectableSlotContainer#getHitSlot} for reference
     */
    private static HitPlacement determineHitPlacement(BlockHitResult hitResult, GTAnvilBlockEntity anvilBlockEntity) {
        Direction anvilFacing = anvilBlockEntity.getBlockState().getValue(FACING);

        if (hitResult.getDirection() == anvilFacing.getClockWise()) { // Clockwise around the Y-positive axis
            return HitPlacement.SIDE_LEFT;
        }

        if (hitResult.getDirection() == anvilFacing.getCounterClockWise()) {
            return HitPlacement.SIDE_RIGHT;
        }

        if (hitResult.getDirection() == Direction.UP) {
            return determineUpHitPlacement(hitResult, anvilFacing);
        }

        return HitPlacement.SOMETHING_ELSE;
    }

    private static HitPlacement determineUpHitPlacement(BlockHitResult hitResult, Direction anvilFacing) {
        Vec3 hitVecRelativeToAnvilBlock = hitResult.getLocation().subtract(
            hitResult.getBlockPos().getX(),
            hitResult.getBlockPos().getY(),
            hitResult.getBlockPos().getZ()
        );

        double horizonalValue = switch (anvilFacing) {
            case NORTH -> 1 - hitVecRelativeToAnvilBlock.x;
            case SOUTH -> hitVecRelativeToAnvilBlock.x;
            case WEST -> hitVecRelativeToAnvilBlock.z;
            case EAST -> 1 - hitVecRelativeToAnvilBlock.z;
            default -> throw new GTUnexpectedValidationFailException(
                "Unexpected anvilFacing=%s in GT Anvil hit placement detection".formatted(anvilFacing)
            );
        };

        if (horizonalValue < 0.5) {
            return HitPlacement.TOP_LEFT;
        } else {
            return HitPlacement.TOP_RIGHT;
        }
    }

    private enum HitPlacement {
        TOP_LEFT, TOP_RIGHT, SIDE_LEFT, SIDE_RIGHT, SOMETHING_ELSE // TODO add "show recipes" spot
    }

    private static @Nullable GTAnvilBlockEntity getAnvilBlockEntity(Level level, BlockPos anvilPos) {
        BlockEntity blockEntity = level.getBlockEntity(anvilPos);
        if (!(blockEntity instanceof GTAnvilBlockEntity anvilBlockEntity)) {
            log.error(
                "Expected the block entity={} at position={} to be an instance of GTAnvilBlockEntity, but it wasn't",
                blockEntity, anvilPos
            );
            return null;
        }
        return anvilBlockEntity;
    }
}
