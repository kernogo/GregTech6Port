package ru.kernogo.gregtech6port.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAmount;

import java.util.List;

final class GTMaterialAmountTests {
    private GTMaterialAmountTests() {}

    @ParameterizedTest
    @FieldSource("testCreationFromStringAndValue_cases")
    void testCreationFromStringAndValue(String input, GTMaterialAmount expectedAmount, double expectedDoubleValue) {
        GTMaterialAmount parsed = GTMaterialAmount.ofFractionString(input);
        double parsedValue = parsed.getValueInMaterialUnits();
        Assertions.assertEquals(expectedAmount.getValueInMaterialUnits(), parsedValue);
        Assertions.assertEquals(expectedDoubleValue, parsedValue);
    }

    static final List<Arguments> testCreationFromStringAndValue_cases = List.of(
        Arguments.of("0", GTMaterialAmount.of(0, 0, 1), 0),
        Arguments.of("0 0/1", GTMaterialAmount.of(0, 0, 1), 0),
        Arguments.of("1 0/1", GTMaterialAmount.of(1, 0, 1), 1),
        Arguments.of("1 1/1", GTMaterialAmount.of(1, 1, 1), 2),
        Arguments.of("1 2/2", GTMaterialAmount.of(1, 2, 2), 2),
        Arguments.of("10 3/8", GTMaterialAmount.of(10, 3, 8), 10 + 3.0 / 8),
        Arguments.of("3/8", GTMaterialAmount.of(0, 3, 8), 3.0 / 8),
        Arguments.of("0/8", GTMaterialAmount.of(0, 0, 8), 0)
    );

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "abc", "1abc", "1 abc", "a/b", "a a/b", "1 1", "1  1/1", "1 1/ 1", "1 1 /1"})
    void testCreationFromStringFail(String input) {
        Assertions.assertThrows(Exception.class, () -> GTMaterialAmount.ofFractionString(input));
    }

    @ParameterizedTest
    @FieldSource("testToString_cases")
    void testToFactionString(GTMaterialAmount input, String expected) {
        String actual = input.toFactionString();
        Assertions.assertEquals(expected, actual);
    }

    static final List<Arguments> testToString_cases = List.of(
        Arguments.of(GTMaterialAmount.of(0, 0, 1), "0"),
        Arguments.of(GTMaterialAmount.of(1, 0, 1), "1"),
        Arguments.of(GTMaterialAmount.of(1, 1, 1), "1 1/1"),
        Arguments.of(GTMaterialAmount.of(1, 2, 2), "1 2/2"),
        Arguments.of(GTMaterialAmount.of(10, 3, 8), "10 3/8"),
        Arguments.of(GTMaterialAmount.of(0, 3, 8), "3/8")
    );
}
