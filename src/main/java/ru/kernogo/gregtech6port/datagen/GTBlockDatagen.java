package ru.kernogo.gregtech6port.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringTintSource;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.stream.Stream;

/**
 * Datagen generating BlockState/model JSONs for some Blocks
 * (generates corresponding Items Model Definitions as well)
 */
final class GTBlockDatagen extends ModelProvider {
    GTBlockDatagen(PackOutput output) {
        super(output, GregTech6Port.MODID);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        createTintableBlockStateAndItemModel(blockModels, itemModels, GTBlocks.ENDER_GARBAGE_BIN, GTItems.ENDER_GARBAGE_BIN);
    }

    private void createTintableBlockStateAndItemModel(BlockModelGenerators blockModels,
                                                      ItemModelGenerators itemModels,
                                                      DeferredBlock<Block> deferredBlock,
                                                      DeferredItem<BlockItem> deferredItem) {
        // Block model corresponding to deferredBlock is in manually created resources

        blockModels.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(
                deferredBlock.get(),
                new MultiVariant(
                    WeightedList.of(new Variant(ModelLocationUtils.getModelLocation(deferredBlock.get())))
                )
            )
        );

        itemModels.itemModelOutput.accept(
            deferredItem.get(),
            ItemModelUtils.tintedModel(
                ModelLocationUtils.getModelLocation(deferredBlock.get()),
                new GTTintColoringTintSource()
            )
        );
    }

    // We don't generate JSONs for all Blocks/Items here,
    // so we disable validations like that
    // @formatter:off
    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() { return Stream.of(); }
    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() { return Stream.of(); }
    // @formatter:on
}
