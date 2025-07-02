package ru.kernogo.gregtech6port.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin.GTEnderGarbageBinBlock;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;

/** Datagen for block models and BlockStates */
public class GTBlockDatagen extends BlockStateProvider {
    public GTBlockDatagen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GregTech6Port.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        register("block/ender_garbage_bin", GTBlocks.ENDER_GARBAGE_BIN);
    }

    /** Shorthand method for the datagen of simple BlockItems with an existing model */
    private void register(String modelName, DeferredBlock<GTEnderGarbageBinBlock> deferredBlock) {
        simpleBlockWithItem(
            deferredBlock.get(),
            models().getExistingFile(modLoc(modelName))
        );
    }
}
