package ru.kernogo.gregtech6port.registration.registered.materials;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;
import ru.kernogo.gregtech6port.registration.GTRegisters;

import java.util.List;

public final class GTMaterialThingKinds {
    private GTMaterialThingKinds() {}

    // Original GT6 name: "ingot"
    public static final GTMaterialThingKind INGOT =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "ingot",
            () -> GTMaterialThingKind.of(
                "ingot",
                "%s Ingot",
                GTMaterialAmount.of(1),
                List.of()
            )
        );
    // Original GT6 name: "stick"
    public static final GTMaterialThingKind ROD =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "rod",
            () -> GTMaterialThingKind.of(
                "rod",
                "%s Rod",
                GTMaterialAmount.of(0, 1, 2),
                List.of()
            )
        );
    // Original GT6 name: "crushed"
    public static final GTMaterialThingKind CRUSHED_ORE =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "crushed_ore",
            () -> GTMaterialThingKind.of(
                "crushed_ore",
                "Crushed %s Ore",
                GTMaterialAmount.of(0, 1, 2),
                List.of(new GTMaterialAndAmount(GTBasicMaterials.STONE, GTMaterialAmount.of(1)))
            )
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
