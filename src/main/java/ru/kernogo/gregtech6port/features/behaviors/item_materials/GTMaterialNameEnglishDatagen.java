package ru.kernogo.gregtech6port.features.behaviors.item_materials;

import ru.kernogo.gregtech6port.datagen.GTEnglishLanguageDatagen;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;

/**
 * Datagen for English names of materials.
 * Value of {@link GTMaterial#englishTranslation} is used to create
 * {@link GTMaterial#translationKey} entries in en_us.json.
 */
public final class GTMaterialNameEnglishDatagen {
    private final GTEnglishLanguageDatagen provider;

    /** @param provider The instance of {@link GTEnglishLanguageDatagen} */
    public GTMaterialNameEnglishDatagen(GTEnglishLanguageDatagen provider) {
        this.provider = provider;
    }

    /** This is called from {@link GTEnglishLanguageDatagen} */
    public void addTranslations() {
        for (GTMaterial material : GTCustomRegistries.MATERIALS) {
            provider.add(material.translationKey(), material.englishTranslation());
        }
    }
}
