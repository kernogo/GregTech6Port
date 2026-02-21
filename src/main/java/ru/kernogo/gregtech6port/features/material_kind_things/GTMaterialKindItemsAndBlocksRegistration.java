package ru.kernogo.gregtech6port.features.material_kind_things;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTExistingMaterialKindBlockDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTMaterialKindBlockDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTMaterialKindBlockDefinitionService;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTExistingMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinitionService;
import ru.kernogo.gregtech6port.registration.GTRegisters;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;
import ru.kernogo.gregtech6port.registration.registered.materials.GTBasicMaterials;
import ru.kernogo.gregtech6port.registration.registered.materials.GTChemicalElementMaterials;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;
import ru.kernogo.gregtech6port.utils.GTUtils;

/**
 * This class adds all definitions Material-Kind Items and Blocks (GregTech-generated and existing)
 * into {@link GTMaterialKindItemDefinitionService} and {@link GTMaterialKindBlockDefinitionService}
 * for future registration.
 */
public final class GTMaterialKindItemsAndBlocksRegistration {
    private GTMaterialKindItemsAndBlocksRegistration() {}

    private static final GTMaterialKindItemDefinitionService materialKindItemDefinitionService =
        GTMaterialKindItemDefinitionService.getInstance();
    private static final GTMaterialKindBlockDefinitionService materialKindBlockDefinitionService =
        GTMaterialKindBlockDefinitionService.getInstance();

    /**
     * This gets called from another class. <br>
     * Requires registries {@code Registry<GTMaterial>} and {@code Registry<GTMaterialThingKind>} to be populated.
     */
    public static void registerAllMaterialKindItemsAndBlocks() {
        // These registries are already registered and populated by this point
        Registry<GTMaterial> materialsRegistry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .lookupOrThrow(GTCustomRegistries.MATERIALS.key());
        Registry<GTMaterialThingKind> kindsRegistry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .lookupOrThrow(GTCustomRegistries.MATERIAL_THING_KINDS.key());

        // We register all Material-Kind Items and Blocks, whether or not they will be used.
        // TODO: hide unused Items
        for (GTMaterial material : materialsRegistry) {
            for (GTMaterialThingKind kind : kindsRegistry) {
                registerEverythingForAMaterialKindItemAndBlock(material, kind);
            }
        }
    }

    /** This gets called from another class */
    public static void registerExistingMaterialKindItemsAndBlocks() {
        registerExistingMaterialKindItem(Items.IRON_INGOT, GTChemicalElementMaterials.IRON, GTMaterialThingKinds.INGOT);
        registerExistingMaterialKindItem(Items.GOLD_INGOT, GTChemicalElementMaterials.GOLD, GTMaterialThingKinds.INGOT);
        registerExistingMaterialKindItem(Items.COPPER_INGOT, GTChemicalElementMaterials.COPPER, GTMaterialThingKinds.INGOT);

        registerExistingMaterialKindItem(Items.REDSTONE, GTBasicMaterials.REDSTONE, GTMaterialThingKinds.DUST);
        registerExistingMaterialKindItem(Items.GLOWSTONE_DUST, GTBasicMaterials.GLOWSTONE, GTMaterialThingKinds.DUST);

        registerExistingMaterialKindItemWithBlock(
            Items.REDSTONE_BLOCK, Blocks.REDSTONE_BLOCK, GTBasicMaterials.REDSTONE, GTMaterialThingKinds.DUST_BLOCK
        );
        // We don't register vanilla Glowstone Block because it contains 4 (and not 9) Glowstone Dust.
        // Instead, separate GregTech's "Block of Glowstone Dust" will exist.

        // TODO: add more
    }

    /** Add all Material-Kind Items (and corresponding Blocks if there is a need for them) for future registration. */
    private static void registerEverythingForAMaterialKindItemAndBlock(
        GTMaterial material,
        GTMaterialThingKind kind
    ) {
        String blockAndItemName = "%s_%s".formatted(material.name(), kind.name());

        final DeferredBlock<Block> deferredBlockOrNull = kind.blockCreator() == null ? null :
            GTRegisters.BLOCKS.registerBlock(
                blockAndItemName,
                blockProperties -> kind.blockCreator().createBlock(
                    blockProperties,
                    material,
                    kind
                )
            );

        // Minecraft registers Items after Blocks,
        // so deferredBlockOrNull.get() will return an already registered Block.
        final DeferredItem<Item> deferredItem = GTRegisters.ITEMS.registerItem(
            blockAndItemName,
            itemProperties -> kind.itemCreator().createItem(
                itemProperties,
                deferredBlockOrNull != null ? deferredBlockOrNull.get() : null,
                material,
                kind
            )
        );

        materialKindItemDefinitionService.addGTMaterialKindItemDefinition(
            new GTMaterialKindItemDefinition(
                deferredItem,
                material,
                kind,
                deferredBlockOrNull
            )
        );

        if (deferredBlockOrNull != null) {
            materialKindBlockDefinitionService.addGTMaterialKindBlockDefinition(
                new GTMaterialKindBlockDefinition(
                    deferredBlockOrNull,
                    material,
                    kind
                )
            );
        }
    }

    private static void registerExistingMaterialKindItem(Item item, GTMaterial material, GTMaterialThingKind kind) {
        materialKindItemDefinitionService.addExistingMaterialKindItemDefinition(
            new GTExistingMaterialKindItemDefinition(
                GTUtils.getIdentifier(item),
                material,
                kind
            )
        );
    }

    private static void registerExistingMaterialKindItemWithBlock(Item item, Block block, GTMaterial material, GTMaterialThingKind kind) {
        registerExistingMaterialKindItem(item, material, kind);

        materialKindBlockDefinitionService.addExistingMaterialKindBlockDefinition(
            new GTExistingMaterialKindBlockDefinition(
                GTUtils.getIdentifier(block),
                material,
                kind
            )
        );
    }
}
