package ru.kernogo.gregtech6port.features.behaviors.item_materials;

import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/**
 * If {@code englishLanguageDatagenType} is not null,
 * the English language name of the material will be datagenned according to the {@link EnglishLanguageDatagenType}
 * (datagen is done in {@link ru.kernogo.gregtech6port.datagen.english_translations.GTMaterialNameEnglishDatagen}).
 */
public record GTMaterial(
    String name,
    @Nullable GTMaterial.EnglishLanguageDatagenType englishLanguageDatagenType,
    ColorData colorData,
    String textureSet, // TODO: texture set data type
    int meltingPoint,
    int boilingPoint,
    double densityGramPerCm3
) {
    record ColorData(int a, int r, int g, int b) {}

    /** Calculates the weight of this material with given amount. Formula taken from the original GT6. */
    public double getWeightForAmount(GTMaterialAmount amount) {
        return densityGramPerCm3 * 111.111111 * amount.getValueInMaterialUnits();
    }

    public static Builder builder() {
        return new Builder();
    }

    // We could've used Lombok's @Builder,
    // but (at this moment) the renaming of fields in IDE does not work with Lombok's @Builder for whatever reason,
    // so we create a Builder class explicitly
    public static class Builder {
        private @Nullable String name;
        private @Nullable GTMaterial.EnglishLanguageDatagenType englishLanguageDatagenType;
        private boolean isEnglishLanguageDatagenTypeSet = false;
        private @Nullable ColorData colorData;
        private @Nullable String textureSet;
        private @Nullable Integer meltingPoint;
        private boolean isMeltingPointSet = false;
        private @Nullable Integer boilingPoint;
        private boolean isBoilingPointSet = false;
        private @Nullable Double densityGramPerCm3;
        private boolean isDensityGramPerCm3Set = false;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder englishLanguageDatagenType(@Nullable GTMaterial.EnglishLanguageDatagenType englishLanguageDatagenType) {
            this.englishLanguageDatagenType = englishLanguageDatagenType;
            isEnglishLanguageDatagenTypeSet = true;
            return this;
        }

        public Builder colorData(int a, int r, int g, int b) {
            this.colorData = new ColorData(a, r, g, b);
            return this;
        }

        public Builder textureSet(String textureSet) {
            this.textureSet = textureSet;
            return this;
        }

        public Builder meltingPoint(@Nullable Integer meltingPoint) {
            this.meltingPoint = meltingPoint;
            isMeltingPointSet = true;
            return this;
        }

        public Builder boilingPoint(@Nullable Integer boilingPoint) {
            this.boilingPoint = boilingPoint;
            isBoilingPointSet = true;
            return this;
        }

        public Builder densityGramPerCm3(@Nullable Double densityGramPerCm3) {
            this.densityGramPerCm3 = densityGramPerCm3;
            isDensityGramPerCm3Set = true;
            return this;
        }

        public GTMaterial build() {
            if (name == null || !isEnglishLanguageDatagenTypeSet || colorData == null || textureSet == null ||
                !isMeltingPointSet || !isBoilingPointSet || !isDensityGramPerCm3Set) {
                throw new GTUnexpectedValidationFailException("Not all builder fields have been initialized in GTMaterial!");
            }

            // We use the default values if they are not set, as in the original GT6
            int meltingPointValue = meltingPoint != null ? meltingPoint : 1000;
            int boilingPointValue = boilingPoint != null ? boilingPoint : 3000;
            double densityGramPerCm3Value = densityGramPerCm3 != null ? densityGramPerCm3 : 1;

            return new GTMaterial(
                name,
                englishLanguageDatagenType,
                colorData,
                textureSet,
                meltingPointValue,
                boilingPointValue,
                densityGramPerCm3Value
            );
        }
    }

    /**
     * Type of English language datagen to use. Only works if specified (not null).
     * Datagen is done in {@link ru.kernogo.gregtech6port.datagen.english_translations.GTMaterialNameEnglishDatagen}.
     */
    public enum EnglishLanguageDatagenType {
        CHEMICAL_ELEMENT
    }
}
