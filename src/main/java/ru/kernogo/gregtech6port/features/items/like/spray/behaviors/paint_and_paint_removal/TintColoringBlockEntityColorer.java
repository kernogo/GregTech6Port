package ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal;

import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringCapability;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringData;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.EnumMap;

/**
 * Colors and uncolores BlockEntities that have a
 * {@link ru.kernogo.gregtech6port.registration.registered.GTCapabilities#TINT_COLORING} capability
 *
 * @see ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringForBlocks
 */
final class TintColoringBlockEntityColorer {
    TintColoringBlockEntityColorer() {}

    /**
     * Dye colors are taken from the original GT6 without changes <br>
     * This could've been a DataMap, but for now it isn't.
     */
    private final ImmutableBiMap<DyeColor, GTTintColoringData> dyeToTintColoringData =
        ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
            put(DyeColor.WHITE, GTTintColoringData.createFromArgbComponents(255, 255, 255, 255));
            put(DyeColor.ORANGE, GTTintColoringData.createFromArgbComponents(255, 255, 128, 0));
            put(DyeColor.MAGENTA, GTTintColoringData.createFromArgbComponents(255, 255, 0, 255));
            put(DyeColor.LIGHT_BLUE, GTTintColoringData.createFromArgbComponents(255, 128, 128, 255));
            put(DyeColor.YELLOW, GTTintColoringData.createFromArgbComponents(255, 255, 255, 0));
            put(DyeColor.LIME, GTTintColoringData.createFromArgbComponents(255, 128, 255, 128));
            put(DyeColor.PINK, GTTintColoringData.createFromArgbComponents(255, 255, 192, 192));
            put(DyeColor.GRAY, GTTintColoringData.createFromArgbComponents(255, 128, 128, 128));
            put(DyeColor.LIGHT_GRAY, GTTintColoringData.createFromArgbComponents(255, 192, 192, 192));
            put(DyeColor.CYAN, GTTintColoringData.createFromArgbComponents(255, 0, 255, 255));
            put(DyeColor.PURPLE, GTTintColoringData.createFromArgbComponents(255, 128, 0, 128));
            put(DyeColor.BLUE, GTTintColoringData.createFromArgbComponents(255, 0, 0, 255));
            put(DyeColor.BROWN, GTTintColoringData.createFromArgbComponents(255, 96, 64, 0));
            put(DyeColor.GREEN, GTTintColoringData.createFromArgbComponents(255, 0, 255, 0));
            put(DyeColor.RED, GTTintColoringData.createFromArgbComponents(255, 255, 0, 0));
            put(DyeColor.BLACK, GTTintColoringData.createFromArgbComponents(255, 32, 32, 32));
        }});

    /** Tries to do the coloring of a block at the position {@code pos}. Returns true if the coloring was done and false otherwise. */
    boolean tryColoringABlock(BlockPos pos, Level level, DyeColor dyeColor) {
        GTTintColoringCapability tintColoringCapability = level.getCapability(GTCapabilities.TINT_COLORING, pos);
        if (tintColoringCapability == null) {
            return false;
        }

        GTTintColoringData currentTintColoringData = tintColoringCapability.getTintColoringData();
        GTTintColoringData dyeTintColoringData = dyeToTintColoringData.get(dyeColor);
        if (dyeTintColoringData == null) {
            throw new GTUnexpectedValidationFailException(
                "Could not find a dye color=%s in a map for Tint Coloring (this should neve happen)".formatted(dyeColor),
                new RuntimeException("Exception thrown for stack trace purposes")
            );
        }

        if (currentTintColoringData == null) { // Coloring of an uncolored block simply applies the new color
            tintColoringCapability.setTintColoringDataAndUpdate(dyeTintColoringData);
            return true;
        }

        // Else, coloring of an already colored block mixes the existing color and the new color together
        GTTintColoringData newTintColoringData = GTTintColoringData.createMixedColoringData(currentTintColoringData, dyeTintColoringData);
        if (newTintColoringData.equals(currentTintColoringData)) {
            return false;
        }
        tintColoringCapability.setTintColoringDataAndUpdate(newTintColoringData);

        return true;
    }

    /** Tries to do the uncoloring of a block at the position {@code pos}. Returns true if the uncoloring was done and false otherwise. */
    boolean tryUncoloringABlock(BlockPos pos, Level level) {
        GTTintColoringCapability tintColoringCapability = level.getCapability(GTCapabilities.TINT_COLORING, pos);
        if (tintColoringCapability == null) {
            return false;
        }

        if (tintColoringCapability.getTintColoringData() == null) {
            return false; // If already uncolored, no need to uncolor
        }

        // Do uncolor
        tintColoringCapability.setTintColoringDataAndUpdate(null);

        return true;
    }
}
