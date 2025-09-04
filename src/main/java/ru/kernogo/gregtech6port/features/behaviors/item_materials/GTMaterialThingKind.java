package ru.kernogo.gregtech6port.features.behaviors.item_materials;

import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;

import java.util.List;

/**
 * Contains information about a Kind part of a Material-Kind Item (or Block TODO)
 * ({@link ru.kernogo.gregtech6port.features.material_kind_items.GTMaterialKindItem})
 * for registration purposes only
 *
 * @param name                                  name (identifier) of a Kind
 * @param itemTranslationKey                    key of a {@link #itemEnglishTranslationPattern} in language JSON files
 * @param itemEnglishTranslationPattern         English-translated item name pattern
 *                                              (like "Crushed %s Ore", which becomes "Crushed Nickel Ore" for Nickel Material)
 * @param amountForAnItem                       how much of a primary material is needed to create one Item of this Kind
 * @param additionalMaterialCompositionInAnItem what additional Materials (other than the primary Material)
 *                                              are contained in an Item of this Kind
 */
public record GTMaterialThingKind(
    String name,
    String itemTranslationKey,
    String itemEnglishTranslationPattern,
    GTMaterialAmount amountForAnItem,
    List<GTMaterialAndAmount> additionalMaterialCompositionInAnItem
) {
    /** Factory method with every field except {@link #itemEnglishTranslationPattern} which is created automatically */
    public static GTMaterialThingKind of(
        String name,
        String englishTranslationPattern,
        GTMaterialAmount amountForAnItem,
        List<GTMaterialAndAmount> additionalMaterialCompositionInAnItem
    ) {
        String translationKey = "gregtech6port.material_kind_item." + name + ".pattern";
        return new GTMaterialThingKind(
            name,
            translationKey,
            englishTranslationPattern,
            amountForAnItem,
            additionalMaterialCompositionInAnItem
        );
    }
}
