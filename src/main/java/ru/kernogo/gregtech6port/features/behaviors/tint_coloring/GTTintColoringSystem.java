package ru.kernogo.gregtech6port.features.behaviors.tint_coloring;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.stream.Stream;

/**
 * Event handlers for the tint coloring system. <br>
 * The tint coloring system uses Minecraft's built-in model tinting. <br><br>
 * To tint color a Block/Item you must:
 * <ul>
 *     <li>
 *         <ul>
 *             <li>For Blocks, apply and use the {@link GTCapabilities#TINT_COLORING} capability.</li>
 *             <li>For Items, apply and use the {@link GTDataComponentTypes#TINT_COLORING} data component.</li>
 *         </ul>
 *     </li>
 *     <li>Register here the Block/Item.</li>
 * </ul>
 * Tint color that is used is as follows:
 * <ul>
 *     <li>If the value of a capability (for BlockEntities) or a data component (for Items) is not null,
 *     then the ARGB color from the value is the tint color.</li>
 *     <li>Else, the color of the Material of Block/Item is the tint color.</li>
 * </ul>
 * Notes:
 * <ul>
 *   <li>Tint coloring is only applied to the faces of Block/Item models that have {@code "tintindex": 0}.</li>
 *   <li>Currently only Blocks that have BlockEntities are allowed.</li>
 * </ul>
 * (TODO: add coloring based on Material's color)
 */
public final class GTTintColoringSystem {
    private GTTintColoringSystem() {}

    /** This gets subscribed with the modBus in another class */
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        Block[] blocksToRegister = Stream.of( // Register Blocks corresponding to the BlockEntities here
            GTBlocks.ENDER_GARBAGE_BIN
        ).map(DeferredHolder::get).toArray(Block[]::new);

        event.register(GTTintColoringSystem::getBlockColor, blocksToRegister);
    }

    /** This gets subscribed with the modBus in another class */
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        Item[] itemsToRegister = Stream.of( // Register Items here
            GTItems.ENDER_GARBAGE_BIN
        ).map(DeferredItem::get).toArray(Item[]::new);

        event.register(GTTintColoringSystem::getItemColor, itemsToRegister);
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

    private static int getItemColor(ItemStack itemStack, int tintIndex) {
        GTTintColoringData tintColoringData = itemStack.get(GTDataComponentTypes.TINT_COLORING);
        if (tintColoringData == null) {
            return -1;
        }
        return tintColoringData.argbColor();
    }
}
