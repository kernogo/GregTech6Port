package ru.kernogo.gregtech6port.features.behaviors.material_composition;

import com.mojang.serialization.Codec;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/**
 * Stores the amount of material. <br>
 * One material unit is supposed to be equal to one ingot of a material. <br>
 * It is stored as a mixed fraction represented as a String internally, so not to lose precision (just in case).
 */
public final class GTMaterialAmount {
    /** Encodes this class in material units using String representation of a mixed fraction */
    public static final Codec<GTMaterialAmount> MATERIAL_UNITS_CODEC = Codec.STRING.xmap(
        GTMaterialAmount::ofFractionString,
        GTMaterialAmount::toFactionString
    );

    private final long wholePart;
    private final long fractionalPartNumerator;
    private final long fractionalPartDenominator;

    private GTMaterialAmount(long wholePart, long fractionalPartNumerator, long fractionalPartDenominator) {
        this.wholePart = wholePart;
        this.fractionalPartNumerator = fractionalPartNumerator;
        this.fractionalPartDenominator = fractionalPartDenominator;
    }

    /** Create material amount equal to {@code amount} material units */
    public static GTMaterialAmount of(long amount) {
        return new GTMaterialAmount(amount, 0, 1);
    }

    /**
     * Create material amount equal to
     * {@code wholePart} + {@code numerator} / {@code denominator} material units
     */
    public static GTMaterialAmount of(long wholePart, int numerator, int denominator) {
        return new GTMaterialAmount(wholePart, numerator, denominator);
    }

    /**
     * Create material amount using the String representation of a mixed fraction in one of the formats:
     * "3 1/2" or "3" or "1/2"
     */
    public static GTMaterialAmount ofFractionString(String amountFractionStr) {
        try {
            String[] parts1 = amountFractionStr.split(" ");

            if (parts1.length == 1) { // "3" or "1/2" format
                String[] parts2 = parts1[0].split("/");

                if (parts2.length == 2) { // "1/2" format
                    int numerator = Integer.parseInt(parts2[0]);
                    int denominator = Integer.parseInt(parts2[1]);

                    return new GTMaterialAmount(0, numerator, denominator);
                } else if (parts2.length == 1) { // "3" format
                    long integerPart = Long.parseLong(parts1[0]);
                    return new GTMaterialAmount(integerPart, 0, 1);
                } else {
                    throw new GTUnexpectedValidationFailException("Wrong format");
                }
            } else if (parts1.length == 2) { // "3 1/2" format
                String[] parts2 = parts1[1].split("/");

                long integerPart = Long.parseLong(parts1[0]);
                int numerator = Integer.parseInt(parts2[0]);
                int denominator = Integer.parseInt(parts2[1]);

                return new GTMaterialAmount(integerPart, numerator, denominator);
            } else {
                throw new GTUnexpectedValidationFailException("Wrong format");
            }
        } catch (GTUnexpectedValidationFailException | NumberFormatException e) {
            throw new GTUnexpectedValidationFailException("Got a wrong format when parsing GTMaterialAmount", e);
        }
    }

    /**
     * Returns the mixed fraction String representation in one of the formats depending on the value in material units:
     * "3 1/2" or "3" or "1/2"
     */
    public String toFactionString() {
        if (wholePart == 0) {
            if (fractionalPartNumerator != 0) {
                return fractionalPartNumerator + "/" + fractionalPartDenominator;
            } else {
                return "0";
            }
        } else {
            if (fractionalPartNumerator != 0) {
                return wholePart + " " + fractionalPartNumerator + "/" + fractionalPartDenominator;
            } else {
                return String.valueOf(wholePart);
            }
        }
    }

    /**
     * Returns a numeric amount in material units.
     * One material unit is supposed to be equal to one ingot of a material.
     */
    public double getValueInMaterialUnits() {
        return wholePart + (double) fractionalPartNumerator / fractionalPartDenominator;
    }
}
