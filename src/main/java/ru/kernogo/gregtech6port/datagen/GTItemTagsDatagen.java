package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;

import java.util.concurrent.CompletableFuture;

/** Datagen for some Item Tags */
final class GTItemTagsDatagen extends ItemTagsProvider {
    GTItemTagsDatagen(PackOutput output,
                      CompletableFuture<HolderLookup.Provider> lookupProvider,
                      CompletableFuture<TagLookup<Block>> blockTags,
                      @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, GregTech6Port.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
