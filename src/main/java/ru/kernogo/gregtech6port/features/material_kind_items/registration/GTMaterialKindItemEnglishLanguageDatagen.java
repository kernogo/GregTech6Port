package ru.kernogo.gregtech6port.features.material_kind_items.registration;

import ru.kernogo.gregtech6port.datagen.GTEnglishLanguageDatagen;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;

/**
 * Datagen for all Material-Kind Item English translations
 * ({@link ru.kernogo.gregtech6port.features.material_kind_items.GTMaterialKindItem})
 */
public final class GTMaterialKindItemEnglishLanguageDatagen {
    private final GTEnglishLanguageDatagen provider;

    /** @param provider The instance of {@link GTEnglishLanguageDatagen} */
    public GTMaterialKindItemEnglishLanguageDatagen(GTEnglishLanguageDatagen provider) {
        this.provider = provider;
    }

    /**
     * This is called from {@link GTEnglishLanguageDatagen}. <br>
     * Only the patterns are datagenned, and concrete Item names use the patterns.
     */
    public void addTranslations() {
        for (GTMaterialThingKind kind : GTCustomRegistries.MATERIAL_THING_KINDS) {
            provider.add(kind.itemTranslationKey(), kind.itemEnglishTranslationPattern());
        }
    }
}
