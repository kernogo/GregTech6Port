package ru.kernogo.gregtech6port.registration.registered.materials;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.impls.GTSimpleMaterialKindBlock;
import ru.kernogo.gregtech6port.features.material_kind_things.items.impls.GTSimpleMaterialKindBlockItem;
import ru.kernogo.gregtech6port.features.material_kind_things.items.impls.GTSimpleMaterialKindItem;
import ru.kernogo.gregtech6port.registration.GTRegisters;

import java.util.List;
import java.util.Objects;

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
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "dustSmall"
    public static final GTMaterialThingKind SMALL_DUST =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "small_dust",
            () -> GTMaterialThingKind.of(
                "small_dust",
                "Small Pile of %s Dust",
                GTMaterialAmount.of(0, 1, 4),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "dustTiny"
    public static final GTMaterialThingKind TINY_DUST =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "tiny_dust",
            () -> GTMaterialThingKind.of(
                "tiny_dust",
                "Tiny Pile of %s Dust",
                GTMaterialAmount.of(0, 1, 9),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "dustDiv72"
    public static final GTMaterialThingKind DIV72_DUST =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "div72_dust",
            () -> GTMaterialThingKind.of(
                "div72_dust",
                "1/72nd of a Pile of %s Dust",
                GTMaterialAmount.of(0, 1, 72),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
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
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "plate"
    public static final GTMaterialThingKind PLATE =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "plate",
            () -> GTMaterialThingKind.of(
                "plate",
                "%s Plate",
                GTMaterialAmount.of(1),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
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
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
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
                List.of(new GTMaterialAndAmount(GTBasicMaterials.STONE, GTMaterialAmount.of(1))),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "chunkGt"
    public static final GTMaterialThingKind CHUNK =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "chunk",
            () -> GTMaterialThingKind.of(
                "chunk",
                "%s Chunk",
                GTMaterialAmount.of(0, 1, 4),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "ring"
    public static final GTMaterialThingKind RING =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "ring",
            () -> GTMaterialThingKind.of(
                "ring",
                "%s Ring",
                GTMaterialAmount.of(0, 1, 4),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "gemChipped"
    public static final GTMaterialThingKind CHIPPED_GEM =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "chipped_gem",
            () -> GTMaterialThingKind.of(
                "chipped_gem",
                "Chipped %s",
                GTMaterialAmount.of(0, 1, 4),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "gemFlawed"
    public static final GTMaterialThingKind FLAWED_GEM =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "flawed_gem",
            () -> GTMaterialThingKind.of(
                "flawed_gem",
                "Flawed %s",
                GTMaterialAmount.of(0, 1, 2),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "gem"
    public static final GTMaterialThingKind GEM =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "gem",
            () -> GTMaterialThingKind.of(
                "gem",
                "%s",
                GTMaterialAmount.of(1),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties, material, kind),
                null
            )
        );
    // Original GT6 name: "gemFlawless"
    public static final GTMaterialThingKind FLAWLESS_GEM =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "flawless_gem",
            () -> GTMaterialThingKind.of(
                "flawless_gem",
                "Flawless %s",
                GTMaterialAmount.of(2),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties.stacksTo(32), material, kind),
                null
            )
        );
    // Original GT6 name: "gemExquisite"
    public static final GTMaterialThingKind EXQUISITE_GEM =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "exquisite_gem",
            () -> GTMaterialThingKind.of(
                "exquisite_gem",
                "Exquisite %s",
                GTMaterialAmount.of(4),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties.stacksTo(16), material, kind),
                null
            )
        );
    // Original GT6 name: "gemLegendary"
    public static final GTMaterialThingKind LEGENDARY_GEM =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "legendary_gem",
            () -> GTMaterialThingKind.of(
                "legendary_gem",
                "Legendary %s",
                GTMaterialAmount.of(8),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindItem(itemProperties.stacksTo(8), material, kind),
                null
            )
        );

    // Items that also have Blocks go below

    // Original GT6 name: "blockDust"
    public static final GTMaterialThingKind DUST_BLOCK =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "dust_block",
            () -> GTMaterialThingKind.of(
                "dust_block",
                "Block of %s Dust",
                GTMaterialAmount.of(9),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindBlockItem(Objects.requireNonNull(block), itemProperties, material, kind),
                (blockProperties, material, kind) -> new GTSimpleMaterialKindBlock(blockProperties, material, kind)
            )
        );
    // Original GT6 name: "blockSolid"
    public static final GTMaterialThingKind SOLID_BLOCK =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "solid_block",
            () -> GTMaterialThingKind.of(
                "solid_block",
                "Block of solid %s",
                GTMaterialAmount.of(9),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindBlockItem(Objects.requireNonNull(block), itemProperties, material, kind),
                (blockProperties, material, kind) -> new GTSimpleMaterialKindBlock(blockProperties, material, kind)
            )
        );
    // Original GT6 name: "blockGem"
    public static final GTMaterialThingKind GEM_BLOCK =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "gem_block",
            () -> GTMaterialThingKind.of(
                "gem_block",
                "Block of %s",
                GTMaterialAmount.of(9),
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindBlockItem(Objects.requireNonNull(block), itemProperties, material, kind),
                (blockProperties, material, kind) -> new GTSimpleMaterialKindBlock(blockProperties, material, kind)
            )
        );
    // Original GT6 name: "blockRaw"
    public static final GTMaterialThingKind RAW_BLOCK =
        GTRegisters.MATERIAL_THING_KINDS.register(
            "raw_block",
            () -> GTMaterialThingKind.of(
                "raw_block",
                "Block of %s Ore",
                GTMaterialAmount.of(0), // TODO nullable?
                List.of(),
                (itemProperties, block, material, kind) -> new GTSimpleMaterialKindBlockItem(Objects.requireNonNull(block), itemProperties, material, kind),
                (blockProperties, material, kind) -> new GTSimpleMaterialKindBlock(blockProperties, material, kind)
            )
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
