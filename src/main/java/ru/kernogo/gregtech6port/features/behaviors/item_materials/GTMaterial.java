package ru.kernogo.gregtech6port.features.behaviors.item_materials;

import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/**
 * Material (like Cobalt or Leather)
 *
 * @param name               name (identifier) of a Material
 * @param translationKey     key of a {@link #englishTranslation} in language JSON files
 * @param englishTranslation English-translated Material name
 * @param colorData          color or a Material which is used for the coloring
 * @param textureSet         a set of textures used as model textures for Material-Kind Items
 *                           ({@link ru.kernogo.gregtech6port.features.material_kind_items.GTMaterialKindItem})
 *                           (and Blocks TODO) having this Material
 * @param meltingPoint       melting point in Kelvin
 * @param boilingPoint       boiling point in Kelvin
 * @param densityGramPerCm3  density in gram per cubic centimeter
 */
public record GTMaterial(
    String name,
    String translationKey,
    String englishTranslation,
    ColorData colorData,
    GTMaterialTextureSet textureSet,
    int meltingPoint,
    int boilingPoint,
    double densityGramPerCm3
) {
    public record ColorData(int a, int r, int g, int b) {
        public static void validateAndThrowIfInvalid(ColorData colorData) {
            int a = colorData.a();
            int r = colorData.r();
            int g = colorData.g();
            int b = colorData.b();
            if (a < 0 || a > 255 || r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                throw new GTUnexpectedValidationFailException(
                    "Some of the argb components are out of bounds. a=%s, r=%s, g=%s, b=%s".formatted(a, r, g, b)
                );
            }
        }
    }

    /** Calculates the weight of this material with given amount. Formula taken from the original GT6. */
    public double getWeightForAmount(GTMaterialAmount amount) {
        return densityGramPerCm3 * 111.111111 * amount.getValueInMaterialUnits();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * We don't use Lombok's @Builder because we want additional validations. <br>
     * {@link GTMaterial#translationKey} is created automatically.
     */
    public static class Builder {
        private @Nullable String name;
        private @Nullable String englishTranslation;
        private @Nullable ColorData colorData;
        private @Nullable GTMaterialTextureSet textureSet;
        private @Nullable Integer meltingPoint;
        private @Nullable Integer boilingPoint;
        private @Nullable Double densityGramPerCm3;

        public Builder name(String name) {
            assertNotSet(this.name, "name");
            this.name = name;
            return this;
        }

        public Builder englishNameForDatagen(String englishNameForDatagen) {
            assertNotSet(this.englishTranslation, "englishTranslation");
            this.englishTranslation = englishNameForDatagen;
            return this;
        }

        public Builder colorData(int a, int r, int g, int b) {
            assertNotSet(this.colorData, "colorData");
            this.colorData = new ColorData(a, r, g, b);
            ColorData.validateAndThrowIfInvalid(this.colorData);
            return this;
        }

        /** Use this if color data is not known, it will be set to 255, 255, 255, 255 */
        public Builder unknownColorDataSoUseDefault() {
            assertNotSet(this.colorData, "colorData");
            this.colorData = new ColorData(255, 255, 255, 255);
            return this;
        }

        public Builder textureSet(GTMaterialTextureSet textureSet) {
            assertNotSet(this.textureSet, "textureSet");
            this.textureSet = textureSet;
            return this;
        }

        public Builder meltingPoint(int meltingPoint) {
            assertNotSet(this.meltingPoint, "meltingPoint");
            this.meltingPoint = meltingPoint;
            return this;
        }

        public Builder boilingPoint(int boilingPoint) {
            assertNotSet(this.boilingPoint, "boilingPoint");
            this.boilingPoint = boilingPoint;
            return this;
        }

        public Builder densityGramPerCm3(double densityGramPerCm3) {
            assertNotSet(this.densityGramPerCm3, "densityGramPerCm3");
            this.densityGramPerCm3 = densityGramPerCm3;
            return this;
        }

        /** Use this if melting and boiling points are unknown, default will be set, like in the original GT6 */
        public Builder meltingBoilingPointsUnknownSoUseDefault() {
            assertNotSet(this.meltingPoint, "meltingPoint");
            assertNotSet(this.boilingPoint, "boilingPoint");
            this.meltingPoint = 1000;
            this.boilingPoint = 3000;
            return this;
        }

        /** Use this if melting point is known but boiling point is unknown, default will be set, like in the original GT6 */
        public Builder meltingPointKnownOnlySoUseDefault(int meltingPoint) {
            assertNotSet(this.meltingPoint, "meltingPoint");
            assertNotSet(this.boilingPoint, "boilingPoint");
            this.meltingPoint = meltingPoint;
            this.boilingPoint = meltingPoint * 2;
            return this;
        }

        /** Use this if density is unknown, default will be set, like in the original GT6 */
        public Builder densityGramPerCm3UnknownSoUseDefault() {
            assertNotSet(this.densityGramPerCm3, "densityGramPerCm3");
            this.densityGramPerCm3 = 1.0;
            return this;
        }

        public GTMaterial build() {
            if (name == null || englishTranslation == null || colorData == null || textureSet == null ||
                meltingPoint == null || boilingPoint == null || densityGramPerCm3 == null) {
                throw new GTUnexpectedValidationFailException("Not all builder fields have been initialized in GTMaterial!");
            }

            String translationKey = "gregtech6port.material." + name;

            return new GTMaterial(
                name,
                translationKey,
                englishTranslation,
                colorData,
                textureSet,
                meltingPoint,
                boilingPoint,
                densityGramPerCm3
            );
        }

        private static void assertNotSet(@Nullable Object field, String fieldName) {
            if (field != null) {
                throw new GTUnexpectedValidationFailException(fieldName + " is already set");
            }
        }
    }
}
