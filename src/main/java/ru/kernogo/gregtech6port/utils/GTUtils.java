package ru.kernogo.gregtech6port.utils;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.Map;
import java.util.Random;

/** Random utils to use globally */
@Slf4j
public final class GTUtils {
    private GTUtils() {}

    private static final Random RANDOM = new Random();

    /** Returns true with a chance (from 0.0 to 1.0), otherwise returns false */
    public static boolean rollRandomChance(double probability) {
        return RANDOM.nextDouble() < probability;
    }

    public static ResourceLocation modLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(GregTech6Port.MODID, name);
    }

    /**
     * This can be used to assure the IDE that the {@code object} is not null (because it's been validated before). <br>
     * If the {@code object} is in fact null, then this logs an error and throws a {@link GTUnexpectedValidationFailException}. <br>
     * The code that calls this method should be wrapped
     * in a try-catch block to catch the {@link GTUnexpectedValidationFailException} if it is thrown. <br>
     * TODO: static analysis to ensure that the exception is caught. This could've been a checked exception, but putting "throws" everywhere is annoying
     */
    @Contract("null -> fail; _ -> param1")
    public static <T> T assureNotNull(@Nullable T object) {
        if (object == null) {
            String errorMessage = "Object is null even though it was claimed that it's not";
            log.error(errorMessage, new RuntimeException("Exception thrown for stack trace purposes"));
            throw new GTUnexpectedValidationFailException(errorMessage);
        }
        return object;
    }

    /**
     * Returns a new BlockState that's created from {@code blockState} with all properties copied to it. <br>
     * If any property can't be put into the new BlockState, a {@link GTUnexpectedValidationFailException} is thrown.
     */
    public static BlockState getBlockStateWithAllPropertiesCopiedOrFail(BlockState blockState, Map<Property<?>, Comparable<?>> valuesToCopy) {
        for (Property<?> property : valuesToCopy.keySet()) {
            Property<? extends Comparable<?>> propertyOnNewBlock =
                blockState.getBlock().getStateDefinition().getProperty(property.getName());

            if (!blockState.hasProperty(property) || propertyOnNewBlock == null) {
                throw new GTUnexpectedValidationFailException("The new BlockState does not have the property=%s".formatted(property));
            }

            if (propertyOnNewBlock.getValueClass() != property.getValueClass()) { // Just to be sure
                throw new GTUnexpectedValidationFailException(
                    ("Property on the new BlockState is not of the same type as on the old BlockState. " +
                        "Property on old block=%s. Property on new block=%s").formatted(property, propertyOnNewBlock)
                );
            }

            // Let's hope that nothing bad will happen due to these casts
            //noinspection unchecked,rawtypes
            blockState = blockState.setValue((Property) property, (Comparable) valuesToCopy.get(property));
        }

        return blockState;
    }

    /**
     * Update the BlockEntity and its Block (so that it's redrawn, neighbors are updated, etc.) <br>
     * Here we hope that both {@code oldState} and {@code newState} parameters of {@link Level#sendBlockUpdated}
     * can be the same value without any problems.
     */
    public static void updateTheBlockEntity(BlockEntity blockEntity) {
        blockEntity.setChanged();
        Level level = blockEntity.getLevel();
        if (level != null) {
            BlockPos pos = blockEntity.getBlockPos();
            BlockState blockState = level.getBlockState(pos);
            level.sendBlockUpdated(pos, blockState, blockState, Block.UPDATE_ALL_IMMEDIATE);
        } else {
            log.warn("Level was null during the update of the BlockEntity",
                new RuntimeException("Exception thrown for stack trace purposes"));
        }
    }
}
