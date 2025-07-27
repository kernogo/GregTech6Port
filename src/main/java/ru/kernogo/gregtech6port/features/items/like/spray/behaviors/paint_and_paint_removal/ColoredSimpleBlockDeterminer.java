package ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal;

import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;

/**
 * Class used in {@link PaintSprayBehavior} and {@link PaintRemovalSprayBehavior}
 * to determine what Block is the colored/uncolored version of a concrete Block
 * - only for simple Blocks without BlockEntities.
 */
final class ColoredSimpleBlockDeterminer {
    ColoredSimpleBlockDeterminer() {}

    // We could try to auto-detect the colored variants of the same block via tags
    // and build the COLOR_TO_BLOCK maps automatically in runtime,
    // but that's not reliable - better not risk it.
    // That's why we're specifying the colored block variants explicitly.

    // Fields are initialized below
    private static final ColorableBlocksGroup WOOL_GROUP;
    private static final ColorableBlocksGroup CARPET_GROUP;
    private static final ColorableBlocksGroup TERRACOTTA_GROUP;
    private static final ColorableBlocksGroup CONCRETE_GROUP;
    private static final ColorableBlocksGroup CONCRETE_POWDER_GROUP;
    private static final ColorableBlocksGroup GLAZED_TERRACOTTA_GROUP;
    private static final ColorableBlocksGroup STAINED_GLASS_GROUP;
    private static final ColorableBlocksGroup STAINED_GLASS_PANE_GROUP;
    private static final ColorableBlocksGroup CANDLE_GROUP;

    static {
        WOOL_GROUP = new ColorableBlocksGroup(null,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_WOOL);
                put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
                put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
                put(DyeColor.LIME, Blocks.LIME_WOOL);
                put(DyeColor.PINK, Blocks.PINK_WOOL);
                put(DyeColor.GRAY, Blocks.GRAY_WOOL);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
                put(DyeColor.CYAN, Blocks.CYAN_WOOL);
                put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
                put(DyeColor.BLUE, Blocks.BLUE_WOOL);
                put(DyeColor.BROWN, Blocks.BROWN_WOOL);
                put(DyeColor.GREEN, Blocks.GREEN_WOOL);
                put(DyeColor.RED, Blocks.RED_WOOL);
                put(DyeColor.BLACK, Blocks.BLACK_WOOL);
            }})
        );

        CARPET_GROUP = new ColorableBlocksGroup(null,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_CARPET);
                put(DyeColor.ORANGE, Blocks.ORANGE_CARPET);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_CARPET);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_CARPET);
                put(DyeColor.YELLOW, Blocks.YELLOW_CARPET);
                put(DyeColor.LIME, Blocks.LIME_CARPET);
                put(DyeColor.PINK, Blocks.PINK_CARPET);
                put(DyeColor.GRAY, Blocks.GRAY_CARPET);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_CARPET);
                put(DyeColor.CYAN, Blocks.CYAN_CARPET);
                put(DyeColor.PURPLE, Blocks.PURPLE_CARPET);
                put(DyeColor.BLUE, Blocks.BLUE_CARPET);
                put(DyeColor.BROWN, Blocks.BROWN_CARPET);
                put(DyeColor.GREEN, Blocks.GREEN_CARPET);
                put(DyeColor.RED, Blocks.RED_CARPET);
                put(DyeColor.BLACK, Blocks.BLACK_CARPET);
            }})
        );

        TERRACOTTA_GROUP = new ColorableBlocksGroup(Blocks.TERRACOTTA,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_TERRACOTTA);
                put(DyeColor.ORANGE, Blocks.ORANGE_TERRACOTTA);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_TERRACOTTA);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_TERRACOTTA);
                put(DyeColor.YELLOW, Blocks.YELLOW_TERRACOTTA);
                put(DyeColor.LIME, Blocks.LIME_TERRACOTTA);
                put(DyeColor.PINK, Blocks.PINK_TERRACOTTA);
                put(DyeColor.GRAY, Blocks.GRAY_TERRACOTTA);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_TERRACOTTA);
                put(DyeColor.CYAN, Blocks.CYAN_TERRACOTTA);
                put(DyeColor.PURPLE, Blocks.PURPLE_TERRACOTTA);
                put(DyeColor.BLUE, Blocks.BLUE_TERRACOTTA);
                put(DyeColor.BROWN, Blocks.BROWN_TERRACOTTA);
                put(DyeColor.GREEN, Blocks.GREEN_TERRACOTTA);
                put(DyeColor.RED, Blocks.RED_TERRACOTTA);
                put(DyeColor.BLACK, Blocks.BLACK_TERRACOTTA);
            }})
        );

        CONCRETE_GROUP = new ColorableBlocksGroup(null,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_CONCRETE);
                put(DyeColor.ORANGE, Blocks.ORANGE_CONCRETE);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_CONCRETE);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_CONCRETE);
                put(DyeColor.YELLOW, Blocks.YELLOW_CONCRETE);
                put(DyeColor.LIME, Blocks.LIME_CONCRETE);
                put(DyeColor.PINK, Blocks.PINK_CONCRETE);
                put(DyeColor.GRAY, Blocks.GRAY_CONCRETE);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_CONCRETE);
                put(DyeColor.CYAN, Blocks.CYAN_CONCRETE);
                put(DyeColor.PURPLE, Blocks.PURPLE_CONCRETE);
                put(DyeColor.BLUE, Blocks.BLUE_CONCRETE);
                put(DyeColor.BROWN, Blocks.BROWN_CONCRETE);
                put(DyeColor.GREEN, Blocks.GREEN_CONCRETE);
                put(DyeColor.RED, Blocks.RED_CONCRETE);
                put(DyeColor.BLACK, Blocks.BLACK_CONCRETE);
            }})
        );

        CONCRETE_POWDER_GROUP = new ColorableBlocksGroup(null,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_CONCRETE_POWDER);
                put(DyeColor.ORANGE, Blocks.ORANGE_CONCRETE_POWDER);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_CONCRETE_POWDER);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_CONCRETE_POWDER);
                put(DyeColor.YELLOW, Blocks.YELLOW_CONCRETE_POWDER);
                put(DyeColor.LIME, Blocks.LIME_CONCRETE_POWDER);
                put(DyeColor.PINK, Blocks.PINK_CONCRETE_POWDER);
                put(DyeColor.GRAY, Blocks.GRAY_CONCRETE_POWDER);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_CONCRETE_POWDER);
                put(DyeColor.CYAN, Blocks.CYAN_CONCRETE_POWDER);
                put(DyeColor.PURPLE, Blocks.PURPLE_CONCRETE_POWDER);
                put(DyeColor.BLUE, Blocks.BLUE_CONCRETE_POWDER);
                put(DyeColor.BROWN, Blocks.BROWN_CONCRETE_POWDER);
                put(DyeColor.GREEN, Blocks.GREEN_CONCRETE_POWDER);
                put(DyeColor.RED, Blocks.RED_CONCRETE_POWDER);
                put(DyeColor.BLACK, Blocks.BLACK_CONCRETE_POWDER);
            }})
        );

        GLAZED_TERRACOTTA_GROUP = new ColorableBlocksGroup(null,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_GLAZED_TERRACOTTA);
                put(DyeColor.ORANGE, Blocks.ORANGE_GLAZED_TERRACOTTA);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_GLAZED_TERRACOTTA);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA);
                put(DyeColor.YELLOW, Blocks.YELLOW_GLAZED_TERRACOTTA);
                put(DyeColor.LIME, Blocks.LIME_GLAZED_TERRACOTTA);
                put(DyeColor.PINK, Blocks.PINK_GLAZED_TERRACOTTA);
                put(DyeColor.GRAY, Blocks.GRAY_GLAZED_TERRACOTTA);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA);
                put(DyeColor.CYAN, Blocks.CYAN_GLAZED_TERRACOTTA);
                put(DyeColor.PURPLE, Blocks.PURPLE_GLAZED_TERRACOTTA);
                put(DyeColor.BLUE, Blocks.BLUE_GLAZED_TERRACOTTA);
                put(DyeColor.BROWN, Blocks.BROWN_GLAZED_TERRACOTTA);
                put(DyeColor.GREEN, Blocks.GREEN_GLAZED_TERRACOTTA);
                put(DyeColor.RED, Blocks.RED_GLAZED_TERRACOTTA);
                put(DyeColor.BLACK, Blocks.BLACK_GLAZED_TERRACOTTA);
            }})
        );

        STAINED_GLASS_GROUP = new ColorableBlocksGroup(Blocks.GLASS,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_STAINED_GLASS);
                put(DyeColor.ORANGE, Blocks.ORANGE_STAINED_GLASS);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_STAINED_GLASS);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_STAINED_GLASS);
                put(DyeColor.YELLOW, Blocks.YELLOW_STAINED_GLASS);
                put(DyeColor.LIME, Blocks.LIME_STAINED_GLASS);
                put(DyeColor.PINK, Blocks.PINK_STAINED_GLASS);
                put(DyeColor.GRAY, Blocks.GRAY_STAINED_GLASS);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_STAINED_GLASS);
                put(DyeColor.CYAN, Blocks.CYAN_STAINED_GLASS);
                put(DyeColor.PURPLE, Blocks.PURPLE_STAINED_GLASS);
                put(DyeColor.BLUE, Blocks.BLUE_STAINED_GLASS);
                put(DyeColor.BROWN, Blocks.BROWN_STAINED_GLASS);
                put(DyeColor.GREEN, Blocks.GREEN_STAINED_GLASS);
                put(DyeColor.RED, Blocks.RED_STAINED_GLASS);
                put(DyeColor.BLACK, Blocks.BLACK_STAINED_GLASS);
            }})
        );

        STAINED_GLASS_PANE_GROUP = new ColorableBlocksGroup(Blocks.GLASS_PANE,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_STAINED_GLASS_PANE);
                put(DyeColor.ORANGE, Blocks.ORANGE_STAINED_GLASS_PANE);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_STAINED_GLASS_PANE);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE);
                put(DyeColor.YELLOW, Blocks.YELLOW_STAINED_GLASS_PANE);
                put(DyeColor.LIME, Blocks.LIME_STAINED_GLASS_PANE);
                put(DyeColor.PINK, Blocks.PINK_STAINED_GLASS_PANE);
                put(DyeColor.GRAY, Blocks.GRAY_STAINED_GLASS_PANE);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE);
                put(DyeColor.CYAN, Blocks.CYAN_STAINED_GLASS_PANE);
                put(DyeColor.PURPLE, Blocks.PURPLE_STAINED_GLASS_PANE);
                put(DyeColor.BLUE, Blocks.BLUE_STAINED_GLASS_PANE);
                put(DyeColor.BROWN, Blocks.BROWN_STAINED_GLASS_PANE);
                put(DyeColor.GREEN, Blocks.GREEN_STAINED_GLASS_PANE);
                put(DyeColor.RED, Blocks.RED_STAINED_GLASS_PANE);
                put(DyeColor.BLACK, Blocks.BLACK_STAINED_GLASS_PANE);
            }})
        );

        CANDLE_GROUP = new ColorableBlocksGroup(Blocks.CANDLE,
            ImmutableBiMap.copyOf(new EnumMap<>(DyeColor.class) {{
                put(DyeColor.WHITE, Blocks.WHITE_CANDLE);
                put(DyeColor.ORANGE, Blocks.ORANGE_CANDLE);
                put(DyeColor.MAGENTA, Blocks.MAGENTA_CANDLE);
                put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_CANDLE);
                put(DyeColor.YELLOW, Blocks.YELLOW_CANDLE);
                put(DyeColor.LIME, Blocks.LIME_CANDLE);
                put(DyeColor.PINK, Blocks.PINK_CANDLE);
                put(DyeColor.GRAY, Blocks.GRAY_CANDLE);
                put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_CANDLE);
                put(DyeColor.CYAN, Blocks.CYAN_CANDLE);
                put(DyeColor.PURPLE, Blocks.PURPLE_CANDLE);
                put(DyeColor.BLUE, Blocks.BLUE_CANDLE);
                put(DyeColor.BROWN, Blocks.BROWN_CANDLE);
                put(DyeColor.GREEN, Blocks.GREEN_CANDLE);
                put(DyeColor.RED, Blocks.RED_CANDLE);
                put(DyeColor.BLACK, Blocks.BLACK_CANDLE);
            }})
        );
    }

    // WARNING: Do not forget to add new groups here when they appear!
    private static final List<ColorableBlocksGroup> ALL_GROUPS = List.of(
        WOOL_GROUP, CARPET_GROUP, TERRACOTTA_GROUP, CONCRETE_GROUP, CONCRETE_POWDER_GROUP, GLAZED_TERRACOTTA_GROUP,
        STAINED_GLASS_GROUP, STAINED_GLASS_PANE_GROUP, CANDLE_GROUP
    );

    /** Returns the colored variant of a block, or null if it could not be found */
    @Nullable Block getColoredBlockVariant(Block block, DyeColor color) {
        for (ColorableBlocksGroup group : ALL_GROUPS) {
            if (group.paintRemovalSprayBlock() == block || group.blocks().containsValue(block)) {
                return group.blocks.get(color);
            }
        }

        return null;
    }

    /**
     * Returns the uncolored variant of a block, or null if it could not be found
     */
    @Nullable Block getUncoloredBlockVariant(Block block) {
        for (ColorableBlocksGroup group : ALL_GROUPS) {
            if (group.paintRemovalSprayBlock() == block) {
                return block;
            }
            if (group.blocks().containsValue(block)) {
                return group.paintRemovalSprayBlock();
            }
        }

        return null;
    }

    /**
     * Group of blocks that can be recolored to each other
     *
     * @param paintRemovalSprayBlock Block that will be created if paint removal spray is used;
     *                               if null, the block won't be changed
     * @param blocks                 Map of DyeColor to Block for that group of blocks
     */
    private record ColorableBlocksGroup(
        @Nullable Block paintRemovalSprayBlock,
        ImmutableBiMap<DyeColor, Block> blocks
    ) {}
}
