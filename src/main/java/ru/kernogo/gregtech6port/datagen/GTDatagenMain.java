package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialNameEnglishDatagen;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesModelDatagen;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTBaseMaterialCompositionDataMapDatagen;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.registration.GTMaterialKindBlockDatagen;
import ru.kernogo.gregtech6port.features.material_kind_things.items.registration.GTMaterialKindItemModelDatagen;
import ru.kernogo.gregtech6port.features.material_kind_things.items.registration.GTMaterialKindItemTagsDatagen;

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
    public static void datagenMain(GatherDataEvent.Client event) {
        // We generate all data in one "clientData" run, we don't use "serverData" run.

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new GTItemWithUsesModelDatagen(packOutput));
        event.addProvider(new GTMaterialKindItemModelDatagen(packOutput));

        event.addProvider(new GTBlockDatagen(packOutput));
        event.addProvider(new GTMaterialKindBlockDatagen(packOutput));

        GTBlockTagsDatagen blockTagsDatagen = event.addProvider(new GTBlockTagsDatagen(packOutput, lookupProvider));
        event.addProvider(new GTItemTagsDatagen(packOutput, lookupProvider, blockTagsDatagen.contentsGetter()));
        event.addProvider(new GTMaterialKindItemTagsDatagen(packOutput, lookupProvider, blockTagsDatagen.contentsGetter()));

        event.addProvider(new GTBaseMaterialCompositionDataMapDatagen(packOutput, lookupProvider));

        event.addProvider(new GTEnglishLanguageDatagen(packOutput));

        event.addProvider(new GTRecipeProvider.Runner(packOutput, lookupProvider));
    }
}
