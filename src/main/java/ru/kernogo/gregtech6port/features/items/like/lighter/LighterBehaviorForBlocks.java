package ru.kernogo.gregtech6port.features.items.like.lighter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.Nullable;

/** Behavior for lighter's use on blocks */
@Slf4j
final class LighterBehaviorForBlocks {
    LighterBehaviorForBlocks() {}

    /**
     * Returns a thing to do for the lighter, or null if the lighter should not work
     * (e.g., if the fire can't be placed at the position we clicked on)
     */
    @Nullable IThingToDo findWhatToDo(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState clickedBlockState = level.getBlockState(blockPos);

        // New BlockState that the lighter may have transformed the previous BlockState into
        BlockState modifiedBlockState = level.getBlockState(blockPos)
            .getToolModifiedState(context, ItemAbilities.FIRESTARTER_LIGHT, false);
        if (modifiedBlockState != null) {
            return new ModifyBlockThingToDo(context, modifiedBlockState);
        }

        if (context.getPlayer() == null) {
            log.error("Player is null, which is unexpected");
            return null;
        }
        // Vanilla TNT lighting behavior doesn't run if the player is sneaking (because it's implemented on a Block).
        // But execution does get here even if the player is sneaking, so we must check for that.
        if (!context.getPlayer().isSecondaryUseActive() &&
            (clickedBlockState.getBlock() == Blocks.TNT || clickedBlockState.getBlock() == getAe2TinyTntBlockOrNull())) {
            return new IgniteTNTLikeBlockThingToDo(clickedBlockState, level, blockPos, context);
        }

        BlockPos clickedFaceBlockPos = blockPos.relative(context.getClickedFace());
        if (BaseFireBlock.canBePlacedAt(level, clickedFaceBlockPos, context.getHorizontalDirection())) {
            return new PlaceFireThingToDo(context);
        }

        return null;
    }

    private @Nullable Block getAe2TinyTntBlockOrNull() {
        if (!ModList.get().isLoaded("ae2")) { // this check is not needed but why not
            return null;
        }
        ResourceLocation ae2TinyTntResLoc = ResourceLocation.fromNamespaceAndPath("ae2", "tiny_tnt");
        return BuiltInRegistries.BLOCK.getOptional(ae2TinyTntResLoc).orElse(null);
    }

    /**
     * Change clicked block into another (e.g., lighting a campfire) <br>
     * Based on {@link net.minecraft.world.item.FlintAndSteelItem#useOn}
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class ModifyBlockThingToDo implements IThingToDo {
        private final UseOnContext context;
        private final BlockState modifiedBlockState;

        @Override
        public void lightAThingOnFire() {
            context.getLevel().setBlock(context.getClickedPos(), modifiedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            context.getLevel().gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, context.getClickedPos());
        }

        @Override
        public void playLighterSound() {
            context.getLevel().playSound(
                context.getPlayer(),
                context.getClickedPos(),
                SoundEvents.FLINTANDSTEEL_USE,
                SoundSource.BLOCKS,
                1.0F,
                context.getLevel().getRandom().nextFloat() * 0.4F + 0.8F
            );
        }
    }

    /**
     * Place a fire block in the level <br>
     * Based on {@link net.minecraft.world.item.FlintAndSteelItem#useOn}
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class PlaceFireThingToDo implements IThingToDo {
        private final UseOnContext context;

        @Override
        public void lightAThingOnFire() {
            BlockPos blockPosToPlace = context.getClickedPos().relative(context.getClickedFace());
            context.getLevel().setBlock(blockPosToPlace, BaseFireBlock.getState(context.getLevel(), blockPosToPlace), Block.UPDATE_ALL_IMMEDIATE);
            context.getLevel().gameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, blockPosToPlace);
        }

        @Override
        public void playLighterSound() {
            context.getLevel().playSound(
                context.getPlayer(),
                context.getClickedPos().relative(context.getClickedFace()),
                SoundEvents.FLINTANDSTEEL_USE,
                SoundSource.BLOCKS,
                1.0F,
                context.getLevel().getRandom().nextFloat() * 0.4F + 0.8F
            );
        }
    }

    /**
     * Ignite a TNT-like block (including blocks like Tiny TNT from AE2) <br>
     * Based on {@link net.minecraft.world.level.block.TntBlock#useItemOn}
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class IgniteTNTLikeBlockThingToDo implements IThingToDo {
        private final BlockState clickedBlockState;
        private final Level level;
        private final BlockPos blockPos;
        private final UseOnContext context;

        @Override
        public void lightAThingOnFire() {
            clickedBlockState.getBlock().onCaughtFire(clickedBlockState, level, blockPos, context.getClickedFace(), context.getPlayer());
            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);
        }

        @Override
        public void playLighterSound() {
            // Lighter's sound isn't played
        }
    }
}
