package ru.kernogo.gregtech6port.features.blockentities.anvil.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.model.standalone.SimpleUnbakedStandaloneModel;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialTextureSet;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.blockentities.anvil.GTAnvilBlockCommon;
import ru.kernogo.gregtech6port.features.blockentities.anvil.GTAnvilBlockEntityRenderer;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;
import ru.kernogo.gregtech6port.utils.GTUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Retrieves relevant models for the {@link GTAnvilBlockEntityRenderer}
 */
public final class GTAnvilModelGetterService {
    private GTAnvilModelGetterService() {}

    private static final StandaloneModelKey<BlockStateModel> ANVIL_MODEL_KEY = new StandaloneModelKey<>(
        () -> GregTech6Port.MODID + ": Anvil model" // This name does not matter and is only used for debug
    );

    private static final Map<GTThingOnAnvilKey, StandaloneModelKey<BlockStateModel>> map = new HashMap<>();

    public static @Nullable BlockStateModel getAnvilModel(GTMaterial material) { // TODO generate a model with a solid block texture for the anvil
        return Minecraft.getInstance().getModelManager().getStandaloneModel(ANVIL_MODEL_KEY);
    }

    public static @Nullable BlockStateModel getLeftThingOnAnvilModel(GTMaterialThingKind kind, GTMaterial material) {
        return getBlockStateModel(kind, material, GTThingOnAnvilKey.LeftOrRight.LEFT);
    }

    public static @Nullable BlockStateModel getRightThingOnAnvilModel(GTMaterialThingKind kind, GTMaterial material) {
        return getBlockStateModel(kind, material, GTThingOnAnvilKey.LeftOrRight.RIGHT);
    }

    private static @Nullable BlockStateModel getBlockStateModel(GTMaterialThingKind kind,
                                                                GTMaterial material,
                                                                GTThingOnAnvilKey.LeftOrRight leftOrRight) {
        GTMaterialThingKind kindUsedForTexture;
        if (kind.name().equals(GTMaterialThingKinds.GEM.name())) {
            kindUsedForTexture = GTMaterialThingKinds.GEM_BLOCK;
            // } else if () { TODO add ORE, ROCK -> GTMaterialThingKinds.RAW_BLOCK;
        } else {
            kindUsedForTexture = GTMaterialThingKinds.SOLID_BLOCK;
        }

        GTThingOnAnvilKey key = new GTThingOnAnvilKey(
            kind,
            material.textureSet(),
            kindUsedForTexture,
            leftOrRight
        );

        StandaloneModelKey<BlockStateModel> standaloneModelKey = map.get(key);

        // TODO even if the model can't be found, it returns an empty transparent model without any error
        return Minecraft.getInstance().getModelManager().getStandaloneModel(standaloneModelKey);
    }

    /** This gets subscribed with the modBus in another class */
    public static void handleRegisterAdditional(ModelEvent.RegisterStandalone event) {
        event.register(ANVIL_MODEL_KEY, SimpleUnbakedStandaloneModel.blockStateModel(GTUtils.modLoc("standalone/anvil/anvil")));

        // Registries should be populated by this point
        List<GTMaterialTextureSet> allTextureSets = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .lookupOrThrow(GTCustomRegistries.MATERIAL_TEXTURE_SETS.key()).stream().toList();

        // TODO extract the loop somewhere
        for (GTMaterialThingKind kind : GTAnvilBlockCommon.getSupportedThingOnAnvilKinds()) {
            for (GTMaterialThingKind kindUsedForTexture : List.of(
                GTMaterialThingKinds.SOLID_BLOCK,
                GTMaterialThingKinds.GEM_BLOCK,
                GTMaterialThingKinds.RAW_BLOCK
            )) {
                for (GTMaterialTextureSet textureSet : allTextureSets) {
                    GTThingOnAnvilKey keyForLeftModel = new GTThingOnAnvilKey(
                        kind, textureSet, kindUsedForTexture, GTThingOnAnvilKey.LeftOrRight.LEFT
                    );
                    GTThingOnAnvilKey keyForRightModel = new GTThingOnAnvilKey(
                        kind, textureSet, kindUsedForTexture, GTThingOnAnvilKey.LeftOrRight.RIGHT
                    );

                    registerModelForKey(event, keyForLeftModel);
                    registerModelForKey(event, keyForRightModel);
                }
            }
        }
    }

    private static void registerModelForKey(ModelEvent.RegisterStandalone event, GTThingOnAnvilKey key) {
        StandaloneModelKey<BlockStateModel> standaloneModelKey = new StandaloneModelKey<>(
            () -> GregTech6Port.MODID + ": " + key.modelPath() // This name does not matter and is only used for debug
        );

        event.register(
            standaloneModelKey,
            SimpleUnbakedStandaloneModel.blockStateModel(GTUtils.modLoc(key.modelPath()))
        );

        map.put(key, standaloneModelKey);
    }
}
