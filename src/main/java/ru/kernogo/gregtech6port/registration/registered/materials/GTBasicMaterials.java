package ru.kernogo.gregtech6port.registration.registered.materials;

import net.neoforged.neoforge.registries.DeferredHolder;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.registration.GTRegisters;

/** Basic materials */
public final class GTBasicMaterials {
    private GTBasicMaterials() {}

    public static final DeferredHolder<GTMaterial, GTMaterial> LEATHER =
        GTRegisters.MATERIALS.register(
            "leather",
            () -> GTMaterial.builder()
                .name("leather")
                .englishLanguageDatagenType(null)
                .colorData(141, 65, 37, 255)
                .textureSet("rough")
                .meltingPoint(null)
                .boilingPoint(null)
                .densityGramPerCm3(null)
                .build()
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
