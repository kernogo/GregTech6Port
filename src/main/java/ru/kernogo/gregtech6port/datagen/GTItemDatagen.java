package ru.kernogo.gregtech6port.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

/** Datagen for item models */
public class GTItemDatagen extends ItemModelProvider {
    public GTItemDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GregTech6Port.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(GTItems.MATCH.get());
    }
}
