package ru.kernogo.gregtech6port.features.behaviors.item_materials;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;

import java.util.List;

/**
 * Contains information about a Kind part of a Material-Kind Item/Block
 * ({@link ru.kernogo.gregtech6port.features.material_kind_things})
 * for registration purposes only
 *
 * @param name                                  name (identifier) of a Kind
 * @param itemTranslationKey                    key of a {@link #itemEnglishTranslationPattern} in language JSON files
 * @param itemEnglishTranslationPattern         English-translated item name pattern
 *                                              (like "Crushed %s Ore", which becomes "Crushed Nickel Ore" for Nickel Material)
 * @param amountForAnItem                       how much of a primary material is needed to create one Item of this Kind
 * @param additionalMaterialCompositionInAnItem what additional Materials (other than the primary Material)
 *                                              are contained in an Item of this Kind
 * @param itemTag                               Item Tag that all Items of this Kind will have.
 *                                              Note that not only Material-Kind Items can have that tag,
 *                                              but also other Items (vanilla or modded).
 * @param itemCreator                           Method to create an instance of Item of this Kind (for registration).
 *                                              See available parameters in {@link IItemCreator}.
 * @param blockCreator                          Method to create an instance of Block of this Kind (for registration).
 *                                              Nullable. If null, then no Block will be registered.
 *                                              See available parameters in {@link IBlockCreator}.
 */
public record GTMaterialThingKind(
    String name,
    String itemTranslationKey,
    String itemEnglishTranslationPattern,
    GTMaterialAmount amountForAnItem,
    List<GTMaterialAndAmount> additionalMaterialCompositionInAnItem,
    TagKey<Item> itemTag,
    IItemCreator itemCreator,
    @Nullable IBlockCreator blockCreator
) {
    /**
     * Factory method with every field except
     * {@link #itemEnglishTranslationPattern} and {@link #itemTag}
     * which are created automatically
     */
    public static GTMaterialThingKind of(
        String name,
        String englishTranslationPattern,
        GTMaterialAmount amountForAnItem,
        List<GTMaterialAndAmount> additionalMaterialCompositionInAnItem,
        IItemCreator itemCreator,
        @Nullable IBlockCreator blockCreator
    ) {
        String translationKey = "gregtech6port.material_kind_item." + name + ".pattern";

        TagKey<Item> itemTag = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(GregTech6Port.MODID, "kinds/" + name)
        );

        return new GTMaterialThingKind(
            name,
            translationKey,
            englishTranslationPattern,
            amountForAnItem,
            additionalMaterialCompositionInAnItem,
            itemTag,
            itemCreator,
            blockCreator
        );
    }

    @FunctionalInterface
    public interface IItemCreator {
        /**
         * @param block    Block created with {@link IBlockCreator} will be supplied here
         * @param material Material for an Item
         * @param kind     Kind for an Item
         * @return {@link Item} created using parameters above
         */
        Item createItem(@Nullable Block block, GTMaterial material, GTMaterialThingKind kind);
    }

    @FunctionalInterface
    public interface IBlockCreator {
        /**
         * @param material Material for a Block
         * @param kind     Kind for a Block
         * @return {@link Block} created using parameters above
         */
        Block createBlock(GTMaterial material, GTMaterialThingKind kind);
    }
}
