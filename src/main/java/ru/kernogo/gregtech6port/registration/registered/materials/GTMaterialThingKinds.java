package ru.kernogo.gregtech6port.registration.registered.materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
                (block, material, kind) -> new GTSimpleMaterialKindItem(new Item.Properties(), material, kind),
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
                (block, material, kind) -> new GTSimpleMaterialKindItem(new Item.Properties(), material, kind),
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
                (block, material, kind) -> new GTSimpleMaterialKindItem(new Item.Properties(), material, kind),
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
                (block, material, kind) -> new GTSimpleMaterialKindItem(new Item.Properties(), material, kind),
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
                (block, material, kind) -> new GTSimpleMaterialKindItem(new Item.Properties(), material, kind),
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
                (block, material, kind) -> new GTSimpleMaterialKindItem(new Item.Properties(), material, kind),
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
                (block, material, kind) -> new GTSimpleMaterialKindItem(new Item.Properties(), material, kind),
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
                (block, material, kind) -> new GTSimpleMaterialKindBlockItem(Objects.requireNonNull(block), new Item.Properties(), material, kind),
                (material, kind) -> new GTSimpleMaterialKindBlock(BlockBehaviour.Properties.of(), material, kind)
            )
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
