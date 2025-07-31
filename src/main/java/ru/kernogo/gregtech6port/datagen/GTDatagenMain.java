package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import ru.kernogo.gregtech6port.datagen.item_model.GTItemDatagen;

import java.util.concurrent.CompletableFuture;

/** Entrypoint into the datagen */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public final class GTDatagenMain {
    private GTDatagenMain() {}

    @SubscribeEvent
    public static void datagenMain(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new GTItemDatagen(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new GTBlockDatagen(packOutput, existingFileHelper));

        GTBlockTagsDatagen blockDatagen = generator.addProvider(event.includeServer(), new GTBlockTagsDatagen(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new GTItemTagsDatagen(packOutput, lookupProvider, blockDatagen.contentsGetter(), existingFileHelper));
    }
}
