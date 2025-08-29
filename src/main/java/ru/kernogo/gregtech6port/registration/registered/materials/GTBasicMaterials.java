package ru.kernogo.gregtech6port.registration.registered.materials;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.registration.GTRegisters;

/** Basic materials */
public final class GTBasicMaterials {
    private GTBasicMaterials() {}

    public static final GTMaterial LEATHER =
        GTRegisters.MATERIALS.register(
            "leather",
            () -> GTMaterial.builder()
                .name("leather")
                .englishNameForDatagen("Leather")
                .colorData(255, 141, 65, 37)
                .textureSet(GTMaterialTextureSets.ROUGH)
                .meltingBoilingPointsUnknownSoUseDefault()
                .densityGramPerCm3UnknownSoUseDefault()
                .build()
        );
    public static final GTMaterial STONE =
        GTRegisters.MATERIALS.register(
            "stone",
            () -> GTMaterial.builder()
                .name("stone")
                .englishNameForDatagen("Stone")
                .colorData(255, 205, 205, 205)
                .textureSet(GTMaterialTextureSets.STONE)
                .meltingPointKnownOnlySoUseDefault(1100)
                .densityGramPerCm3UnknownSoUseDefault() // TODO: set density same as in SiO2 (add automatic calculations)
                .build()
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
