package ru.kernogo.gregtech6port.registration.registered.materials;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;
import ru.kernogo.gregtech6port.registration.GTRegisters;

import java.util.List;

public final class GTMaterialThingKinds {
    private GTMaterialThingKinds() {}

    // Original GT6 name: "dust"
    public static final GTMaterialThingKind DUST =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "dust",
            () -> GTMaterialThingKind.of(
                "dust",
                "%s Dust",
                GTMaterialAmount.of(1),
                List.of()
            )
        );
    // Original GT6 name: "dust_small"
    public static final GTMaterialThingKind SMALL_DUST =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "small_dust",
            () -> GTMaterialThingKind.of(
                "small_dust",
                "Small Pile of %s Dust",
                GTMaterialAmount.of(0, 1, 4),
                List.of()
            )
        );
    // Original GT6 name: "dust_tiny"
    public static final GTMaterialThingKind TINY_DUST =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "tiny_dust",
            () -> GTMaterialThingKind.of(
                "tiny_dust",
                "Tiny Pile of %s Dust",
                GTMaterialAmount.of(0, 1, 9),
                List.of()
            )
        );
    // Original GT6 name: "dust_div72"
    public static final GTMaterialThingKind DIV72_DUST =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "div72_dust",
            () -> GTMaterialThingKind.of(
                "div72_dust",
                "1/72nd of a Pile of %s Dust",
                GTMaterialAmount.of(0, 1, 72),
                List.of()
            )
        );
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
