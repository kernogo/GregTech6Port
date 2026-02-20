package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import ru.kernogo.gregtech6port.GregTech6Port;

import java.util.concurrent.CompletableFuture;

/** Datagen for some Item Tags */
final class GTItemTagsDatagen extends ItemTagsProvider {
    GTItemTagsDatagen(PackOutput output,
                      CompletableFuture<HolderLookup.Provider> lookupProvider,
                      CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, GregTech6Port.MODID);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
