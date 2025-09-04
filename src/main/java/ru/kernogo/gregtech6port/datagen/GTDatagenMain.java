package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialNameEnglishDatagen;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesModelDatagen;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTBaseMaterialCompositionDataMapDatagen;
import ru.kernogo.gregtech6port.features.material_kind_items.registration.GTMaterialKindItemModelDatagen;

import java.util.concurrent.CompletableFuture;

/**
 * Entrypoint into the datagen. <br>
 * The {@link net.minecraft.data.DataProvider#getName} method is overridden on each of the providers,
 * because otherwise they would have duplicate names, and Minecraft does not allow that. <br>
 * Non-specific datagen providers are located in this package. <br>
 * Specific datagen providers are located in their respective feature packages. <br>
 * All datagen providers are listed here in {@link #datagenMain} <br>
 * Remember that each datagen provider may overwrite previously generated files.
 * That's why some datagen providers call delegate classes
 * (like {@link GTEnglishLanguageDatagen} calls {@link GTMaterialNameEnglishDatagen}, for instance)
 */
public final class GTDatagenMain {
    private GTDatagenMain() {}

    /** This gets subscribed with the modBus in another class */
    public static void datagenMain(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new GTItemWithUsesModelDatagen(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new GTMaterialKindItemModelDatagen(packOutput, existingFileHelper));

        generator.addProvider(event.includeClient(), new GTBlockDatagen(packOutput, existingFileHelper));

        GTBlockTagsDatagen blockDatagen = generator.addProvider(event.includeServer(), new GTBlockTagsDatagen(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new GTItemTagsDatagen(packOutput, lookupProvider, blockDatagen.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new GTBaseMaterialCompositionDataMapDatagen(packOutput, lookupProvider));

        generator.addProvider(event.includeClient(), new GTEnglishLanguageDatagen(packOutput));
    }
}
