package ru.kernogo.gregtech6port.features.material_kind_things.blocks.registration;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTMaterialKindBlockDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTMaterialKindBlockDefinitionService;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;

import java.util.List;

/** Datagen for all Material-Kind Block Models and BlockStates */
public final class GTMaterialKindBlockDatagen extends BlockStateProvider {
    private final GTMaterialKindBlockDefinitionService materialKindBlockDefinitionService =
        GTMaterialKindBlockDefinitionService.getInstance();

    public GTMaterialKindBlockDatagen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GregTech6Port.MODID, exFileHelper);
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void registerStatesAndModels() {
        List<GTMaterialKindBlockDefinition> definitions = materialKindBlockDefinitionService.getGTMaterialKindBlockDefinitions();

        for (GTMaterialKindBlockDefinition definition : definitions) {
            if (definition.kind().equals(GTMaterialThingKinds.DUST_BLOCK)) {
                String textureSetName = definition.material().textureSet().name();
                String kindName = definition.kind().name();

                String outputModelPath = "block/material_items/%s/%s".formatted(textureSetName, kindName);

                String blockTexturePath = "block/material_icons/%s/%s".formatted(textureSetName, kindName);
                String blockTextureOverlayPath = "block/material_icons/%s/%s_overlay".formatted(textureSetName, kindName);

                simpleBlockWithItem(
                    definition.deferredBlock().get(),

                    models().withExistingParent(outputModelPath, modLoc("block/material_kind_blocks/dust_block_parent"))
                        .texture("particle", modLoc(blockTexturePath))
                        .texture("all_sides", modLoc(blockTexturePath))
                        .texture("overlay", modLoc(blockTextureOverlayPath))
                );
            }
        }
    }
}
