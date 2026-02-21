package ru.kernogo.gregtech6port.features.behaviors.tint_coloring;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;

import java.util.stream.Stream;

/**
 * Event handlers for the tint coloring of Blocks
 * (they must have corresponding BlockEntities). <br>
 * Tint color is determined based on the color returned from the
 * {@link GTCapabilities#TINT_COLORING} capability previously registered on a Block. <br>
 * (You must register that capability for any Block you registered here). <br>
 * Tinting is applied only to model's faces that have {@code "tintindex": 0} <br>
 * (TODO: add coloring based on Material's color)
 */
public final class GTTintColoringForBlocks {
    private GTTintColoringForBlocks() {}

    /** This gets subscribed with the modBus in another class */
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        Block[] blocksToRegister = Stream.of( // Register Blocks corresponding to the BlockEntities here
            GTBlocks.ENDER_GARBAGE_BIN
        ).map(DeferredHolder::get).toArray(Block[]::new);

        event.register(GTTintColoringForBlocks::getBlockColor, blocksToRegister);
    }

    private static int getBlockColor(BlockState blockState,
                                     @Nullable BlockAndTintGetter blockAndTintGetter,
                                     @Nullable BlockPos pos,
                                     int tintIndex) {
        if (blockAndTintGetter == null || pos == null) {
            return -1;
        }
        BlockEntity blockEntity = blockAndTintGetter.getBlockEntity(pos);
        if (blockEntity == null) {
            return -1;
        }
        Level level = blockEntity.getLevel();
        if (level == null) {
            return -1;
        }
        GTTintColoringCapability tintColoringCapability = level.getCapability(GTCapabilities.TINT_COLORING, pos, blockState, blockEntity);
        if (tintColoringCapability == null) {
            return -1;
        }
        GTTintColoringData tintColoringData = tintColoringCapability.getTintColoringData();
        if (tintColoringData == null) {
            return -1;
        }
        return tintColoringData.argbColor();
    }
}
