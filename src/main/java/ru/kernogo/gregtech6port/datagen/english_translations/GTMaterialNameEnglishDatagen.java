package ru.kernogo.gregtech6port.datagen.english_translations;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Datagen for English names of materials.
 * If {@link GTMaterial#englishLanguageDatagenType} is not null,
 * the type from that property will be used for the English language datagen. <br>
 * Of course, not all names are datagenned, some names will be specified manually in en_us.json file.
 */
public final class GTMaterialNameEnglishDatagen extends LanguageProvider {
    public GTMaterialNameEnglishDatagen(PackOutput output) {
        super(output, GregTech6Port.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (GTMaterial material : GTCustomRegistries.MATERIALS) {
            if (material.englishLanguageDatagenType() == null) {
                continue;
            }

            String englishNameForDatagen = switch (material.englishLanguageDatagenType()) {
                case CHEMICAL_ELEMENT -> getChemicalElementEnglishNameForDatagen(material);
            };

            add("gregtech6port.material." + material.name(), englishNameForDatagen);
        }
    }

    /** Material's {@link GTMaterial#name} property is used for the datagen here */
    private String getChemicalElementEnglishNameForDatagen(GTMaterial material) {
        class Patterns {
            private static final Pattern CHEMICAL_ELEMENT_WITHOUT_ISOTOPE_NUMBER = Pattern.compile("[a-zA-Z]+");
            private static final Pattern CHEMICAL_ELEMENT_WITH_ISOTOPE_NUMBER = Pattern.compile("[a-zA-Z]+_[0-9]+");
        }

        String name = material.name();

        if (Patterns.CHEMICAL_ELEMENT_WITHOUT_ISOTOPE_NUMBER.matcher(name).matches()) { // Like "oxygen"
            String nameStartingWithCapitalLetter = name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
            return nameStartingWithCapitalLetter;
        } else if (Patterns.CHEMICAL_ELEMENT_WITH_ISOTOPE_NUMBER.matcher(name).matches()) { // Like "plutonium_239"
            String namePart = name.split("_")[0];
            int numberPart = Integer.parseInt(name.split("_")[1]);
            String namePartStartingWithCapitalLetter = namePart.substring(0, 1).toUpperCase(Locale.ROOT) + namePart.substring(1);
            return namePartStartingWithCapitalLetter + "-" + numberPart;
        } else {
            throw new GTUnexpectedValidationFailException(
                "Wrong format for %s English language datagen. Name=%s"
                    .formatted(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT, name)
            );
        }
    }
}
