package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;

import java.util.concurrent.CompletableFuture;

/** Datagen for some Block Tags */
final class GTBlockTagsDatagen extends BlockTagsProvider {
    GTBlockTagsDatagen(PackOutput output,
                       CompletableFuture<HolderLookup.Provider> lookupProvider,
                       @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, GregTech6Port.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
