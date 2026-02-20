package ru.kernogo.gregtech6port.features.material_kind_things.items.registration;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTExistingMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinitionService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class GTMaterialKindItemTagsDatagen extends ItemTagsProvider {
    private final GTMaterialKindItemDefinitionService materialKindItemDefinitionService =
        GTMaterialKindItemDefinitionService.getInstance();

    public GTMaterialKindItemTagsDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, GregTech6Port.MODID);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addTagsForGTMaterialKindItems();
        addTagsForExistingMaterialKindItems();
    }

    private void addTagsForGTMaterialKindItems() {
        List<GTMaterialKindItemDefinition> definitions =
            materialKindItemDefinitionService.getGTMaterialKindItemDefinitions();

        for (GTMaterialKindItemDefinition definition : definitions) {
            tag(definition.kind().itemTag()).add(definition.deferredItem().get());
            tag(definition.material().itemTag()).add(definition.deferredItem().get());
        }
    }

    private void addTagsForExistingMaterialKindItems() {
        List<GTExistingMaterialKindItemDefinition> definitions =
            materialKindItemDefinitionService.getExistingMaterialKindItemDefinitions();

        for (GTExistingMaterialKindItemDefinition definition : definitions) {
            Item itemFromDefinition = definition.getItemOrElseNull();
            if (itemFromDefinition == null) {
                continue;
            }

            tag(definition.kind().itemTag()).add(itemFromDefinition);
            tag(definition.material().itemTag()).add(itemFromDefinition);
        }
    }
}
