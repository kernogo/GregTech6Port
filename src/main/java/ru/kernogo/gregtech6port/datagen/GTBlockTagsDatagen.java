package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;

import java.util.concurrent.CompletableFuture;

/** Datagen for block tags */
public final class GTBlockTagsDatagen extends BlockTagsProvider {
    public GTBlockTagsDatagen(PackOutput output,
                              CompletableFuture<HolderLookup.Provider> lookupProvider,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, GregTech6Port.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
