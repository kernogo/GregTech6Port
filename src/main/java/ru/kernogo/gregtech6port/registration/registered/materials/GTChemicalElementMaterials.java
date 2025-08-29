package ru.kernogo.gregtech6port.registration.registered.materials;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.registration.GTRegisters;

/**
 * This class contains materials that are chemical elements -
 * that is, arbitrarily chosen "main" isotopes, and other isotopes with the same number of protons.
 * Elements are ordered by their atomic number (number of protons that defines the element type). <br><br>
 * Right now, arbitrarily chosen "main" isotopes
 * don't have an atomic mass specified in their ids, and their localized names/abbreviations too (maybe they should?). <br>
 * For example, Radon with atomic mass of 220 is chosen here as a "main" isotope,
 * and it does not have "220" in neither its id, nor its localized name/abbreviation. <br><br>
 * These elements have been taken from the original GT6, and the data about them hasn't been re-verified.
 * Some elements have guessed numeric properties, as in the original GT6. <br>
 * Proton, neutron, electron counts are provided as a reference only and are not used.
 */
public final class GTChemicalElementMaterials {
    private GTChemicalElementMaterials() {}

    // Protons, neutrons, electrons: 1, 1, 0
    public static final GTMaterial HYDROGEN =
        GTRegisters.MATERIALS.register(
            "hydrogen",
            () -> GTMaterial.builder()
                .name("hydrogen")
                .englishNameForDatagen("Hydrogen")
                .colorData(15, 0, 0, 255)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(14)
                .boilingPoint(20)
                .densityGramPerCm3(0.00008988)
                .build()
        );
    // Protons, neutrons, electrons: 1, 1, 1
    public static final GTMaterial DEUTERIUM =
        GTRegisters.MATERIALS.register(
            "deuterium",
            () -> GTMaterial.builder() // Like Hydrogen-2
                .name("deuterium")
                .englishNameForDatagen("Deuterium")
                .colorData(15, 255, 255, 0)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(14)
                .boilingPoint(20)
                .densityGramPerCm3(0.00008988)
                .build()
        );
    // Protons, neutrons, electrons: 1, 1, 2
    public static final GTMaterial TRITIUM =
        GTRegisters.MATERIALS.register(
            "tritium",
            () -> GTMaterial.builder() // Like Hydrogen-3
                .name("tritium")
                .englishNameForDatagen("Tritium")
                .colorData(15, 255, 0, 0)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(14)
                .boilingPoint(20)
                .densityGramPerCm3(0.00008988)
                .build()
        );
    // Protons, neutrons, electrons: 2, 2, 2
    public static final GTMaterial HELIUM =
        GTRegisters.MATERIALS.register(
            "helium",
            () -> GTMaterial.builder()
                .name("helium")
                .englishNameForDatagen("Helium")
                .colorData(15, 255, 255, 120)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(1)
                .boilingPoint(4)
                .densityGramPerCm3(0.0001785)
                .build()
        );
    // Protons, neutrons, electrons: 2, 2, 1
    public static final GTMaterial HELIUM_3 =
        GTRegisters.MATERIALS.register(
            "helium_3",
            () -> GTMaterial.builder()
                .name("helium_3")
                .englishNameForDatagen("Helium-3")
                .colorData(15, 255, 255, 140)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1)
                .boilingPoint(4)
                .densityGramPerCm3(0.0001785)
                .build()
        );
    // Protons, neutrons, electrons: 3, 3, 4
    public static final GTMaterial LITHIUM =
        GTRegisters.MATERIALS.register(
            "lithium",
            () -> GTMaterial.builder()
                .name("lithium")
                .englishNameForDatagen("Lithium")
                .colorData(255, 225, 220, 255)
                .textureSet(GTMaterialTextureSets.ROUGH)
                .meltingPoint(453)
                .boilingPoint(1560)
                .densityGramPerCm3(0.534)
                .build()
        );
    // Protons, neutrons, electrons: 3, 3, 3
    public static final GTMaterial LITHIUM_6 =
        GTRegisters.MATERIALS.register(
            "lithium_6",
            () -> GTMaterial.builder()
                .name("lithium_6")
                .englishNameForDatagen("Lithium-6")
                .colorData(255, 230, 225, 255)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(453)
                .boilingPoint(1560)
                .densityGramPerCm3(0.534)
                .build()
        );
    // Protons, neutrons, electrons: 4, 4, 5
    public static final GTMaterial BERYLLIUM =
        GTRegisters.MATERIALS.register(
            "beryllium",
            () -> GTMaterial.builder()
                .name("beryllium")
                .englishNameForDatagen("Beryllium")
                .colorData(255, 100, 180, 100)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1560)
                .boilingPoint(2742)
                .densityGramPerCm3(1.85)
                .build()
        );
    // Protons, neutrons, electrons: 4, 4, 3
    public static final GTMaterial BERYLLIUM_7 =
        GTRegisters.MATERIALS.register(
            "beryllium_7",
            () -> GTMaterial.builder()
                .name("beryllium_7")
                .englishNameForDatagen("Beryllium-7")
                .colorData(255, 110, 190, 110)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1560)
                .boilingPoint(2742)
                .densityGramPerCm3(1.85)
                .build()
        );
    // Protons, neutrons, electrons: 4, 4, 4
    public static final GTMaterial BERYLLIUM_8 =
        GTRegisters.MATERIALS.register(
            "beryllium_8",
            () -> GTMaterial.builder()
                .name("beryllium_8")
                .englishNameForDatagen("Beryllium-8")
                .colorData(255, 110, 200, 110)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1560)
                .boilingPoint(2742)
                .densityGramPerCm3(1.85)
                .build()
        );
    // Protons, neutrons, electrons: 5, 5, 5
    public static final GTMaterial BORON =
        GTRegisters.MATERIALS.register(
            "boron",
            () -> GTMaterial.builder()
                .name("boron")
                .englishNameForDatagen("Boron")
                .colorData(255, 250, 250, 250)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(2349)
                .boilingPoint(4200)
                .densityGramPerCm3(2.34)
                .build()
        );
    // Protons, neutrons, electrons: 5, 5, 6
    public static final GTMaterial BORON_11 =
        GTRegisters.MATERIALS.register(
            "boron_11",
            () -> GTMaterial.builder()
                .name("boron_11")
                .englishNameForDatagen("Boron-11")
                .colorData(255, 240, 240, 240)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(2349)
                .boilingPoint(4200)
                .densityGramPerCm3(2.34)
                .build()
        );
    // Protons, neutrons, electrons: 6, 6, 6
    public static final GTMaterial CARBON =
        GTRegisters.MATERIALS.register(
            "carbon",
            () -> GTMaterial.builder()
                .name("carbon")
                .englishNameForDatagen("Carbon")
                .colorData(255, 20, 20, 20)
                .textureSet(GTMaterialTextureSets.FINE)
                .meltingPoint(3800)
                .boilingPoint(4300)
                .densityGramPerCm3(2.267)
                .build()
        );
    // Protons, neutrons, electrons: 6, 6, 7
    public static final GTMaterial CARBON_13 =
        GTRegisters.MATERIALS.register(
            "carbon_13",
            () -> GTMaterial.builder()
                .name("carbon_13")
                .englishNameForDatagen("Carbon-13")
                .colorData(255, 25, 25, 25)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(3800)
                .boilingPoint(4300)
                .densityGramPerCm3(2.267)
                .build()
        );
    // Protons, neutrons, electrons: 6, 6, 8
    public static final GTMaterial CARBON_14 =
        GTRegisters.MATERIALS.register(
            "carbon_14",
            () -> GTMaterial.builder()
                .name("carbon_14")
                .englishNameForDatagen("Carbon-14")
                .colorData(255, 30, 30, 30)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(3800)
                .boilingPoint(4300)
                .densityGramPerCm3(2.267)
                .build()
        );
    // Protons, neutrons, electrons: 7, 7, 7
    public static final GTMaterial NITROGEN =
        GTRegisters.MATERIALS.register(
            "nitrogen",
            () -> GTMaterial.builder()
                .name("nitrogen")
                .englishNameForDatagen("Nitrogen")
                .colorData(15, 0, 150, 200)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(63)
                .boilingPoint(77)
                .densityGramPerCm3(0.0012506)
                .build()
        );
    // Protons, neutrons, electrons: 8, 8, 8
    public static final GTMaterial OXYGEN =
        GTRegisters.MATERIALS.register(
            "oxygen",
            () -> GTMaterial.builder()
                .name("oxygen")
                .englishNameForDatagen("Oxygen")
                .colorData(15, 0, 100, 200)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(54)
                .boilingPoint(90)
                .densityGramPerCm3(0.001429)
                .build()
        );
    // Protons, neutrons, electrons: 9, 9, 9
    public static final GTMaterial FLUORINE =
        GTRegisters.MATERIALS.register(
            "fluorine",
            () -> GTMaterial.builder()
                .name("fluorine")
                .englishNameForDatagen("Fluorine")
                .colorData(255, 64, 192, 0)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(53)
                .boilingPoint(85)
                .densityGramPerCm3(0.001696)
                .build()
        );
    // Protons, neutrons, electrons: 10, 10, 10
    public static final GTMaterial NEON =
        GTRegisters.MATERIALS.register(
            "neon",
            () -> GTMaterial.builder()
                .name("neon")
                .englishNameForDatagen("Neon")
                .colorData(15, 250, 180, 180)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(24)
                .boilingPoint(27)
                .densityGramPerCm3(0.0008999)
                .build()
        );
    // Protons, neutrons, electrons: 11, 11, 11
    public static final GTMaterial SODIUM =
        GTRegisters.MATERIALS.register(
            "sodium",
            () -> GTMaterial.builder()
                .name("sodium")
                .englishNameForDatagen("Sodium")
                .colorData(255, 0, 0, 150)
                .textureSet(GTMaterialTextureSets.ROUGH)
                .meltingPoint(370)
                .boilingPoint(1156)
                .densityGramPerCm3(0.971)
                .build()
        );
    // Protons, neutrons, electrons: 12, 12, 12
    public static final GTMaterial MAGNESIUM =
        GTRegisters.MATERIALS.register(
            "magnesium",
            () -> GTMaterial.builder()
                .name("magnesium")
                .englishNameForDatagen("Magnesium")
                .colorData(255, 255, 200, 200)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(923)
                .boilingPoint(1363)
                .densityGramPerCm3(1.738)
                .build()
        );
    // Protons, neutrons, electrons: 13, 13, 13
    public static final GTMaterial ALUMINIUM =
        GTRegisters.MATERIALS.register(
            "aluminium",
            () -> GTMaterial.builder()
                .name("aluminium")
                .englishNameForDatagen("Aluminium")
                .colorData(255, 128, 200, 240)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(933)
                .boilingPoint(2792)
                .densityGramPerCm3(2.698)
                .build()
        );
    // Protons, neutrons, electrons: 14, 14, 14
    public static final GTMaterial SILICON =
        GTRegisters.MATERIALS.register(
            "silicon",
            () -> GTMaterial.builder()
                .name("silicon")
                .englishNameForDatagen("Silicon")
                .colorData(255, 60, 60, 80)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(1687)
                .boilingPoint(3538)
                .densityGramPerCm3(2.3296)
                .build()
        );
    // Protons, neutrons, electrons: 15, 15, 15
    public static final GTMaterial PHOSPHOR =
        GTRegisters.MATERIALS.register(
            "phosphor",
            () -> GTMaterial.builder()
                .name("phosphor")
                .englishNameForDatagen("Phosphor")
                .colorData(255, 255, 255, 0)
                .textureSet(GTMaterialTextureSets.FINE)
                .meltingPoint(317)
                .boilingPoint(550)
                .densityGramPerCm3(1.82)
                .build()
        );
    // Protons, neutrons, electrons: 16, 16, 16
    public static final GTMaterial SULFUR =
        GTRegisters.MATERIALS.register(
            "sulfur",
            () -> GTMaterial.builder()
                .name("sulfur")
                .englishNameForDatagen("Sulfur")
                .colorData(255, 234, 234, 0)
                .textureSet(GTMaterialTextureSets.FINE)
                .meltingPoint(388)
                .boilingPoint(717)
                .densityGramPerCm3(2.067)
                .build()
        );
    // Protons, neutrons, electrons: 17, 17, 18
    public static final GTMaterial CHLORINE =
        GTRegisters.MATERIALS.register(
            "chlorine",
            () -> GTMaterial.builder()
                .name("chlorine")
                .englishNameForDatagen("Chlorine")
                .colorData(255, 0, 240, 255)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(171)
                .boilingPoint(239)
                .densityGramPerCm3(0.003214)
                .build()
        );
    // Protons, neutrons, electrons: 18, 18, 22
    public static final GTMaterial ARGON =
        GTRegisters.MATERIALS.register(
            "argon",
            () -> GTMaterial.builder()
                .name("argon")
                .englishNameForDatagen("Argon")
                .colorData(15, 0, 255, 0)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(83)
                .boilingPoint(87)
                .densityGramPerCm3(0.0017837)
                .build()
        );
    // Protons, neutrons, electrons: 19, 19, 20
    public static final GTMaterial POTASSIUM =
        GTRegisters.MATERIALS.register(
            "potassium",
            () -> GTMaterial.builder()
                .name("potassium")
                .englishNameForDatagen("Potassium")
                .colorData(255, 250, 250, 250)
                .textureSet(GTMaterialTextureSets.ROUGH)
                .meltingPoint(336)
                .boilingPoint(1032)
                .densityGramPerCm3(0.862)
                .build()
        );
    // Protons, neutrons, electrons: 20, 20, 20
    public static final GTMaterial CALCIUM =
        GTRegisters.MATERIALS.register(
            "calcium",
            () -> GTMaterial.builder()
                .name("calcium")
                .englishNameForDatagen("Calcium")
                .colorData(255, 255, 245, 245)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1115)
                .boilingPoint(1757)
                .densityGramPerCm3(1.54)
                .build()
        );
    // Protons, neutrons, electrons: 21, 21, 24
    public static final GTMaterial SCANDIUM =
        GTRegisters.MATERIALS.register(
            "scandium",
            () -> GTMaterial.builder()
                .name("scandium")
                .englishNameForDatagen("Scandium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1814)
                .boilingPoint(3109)
                .densityGramPerCm3(2.989)
                .build()
        );
    // Protons, neutrons, electrons: 22, 22, 26
    public static final GTMaterial TITANIUM =
        GTRegisters.MATERIALS.register(
            "titanium",
            () -> GTMaterial.builder()
                .name("titanium")
                .englishNameForDatagen("Titanium")
                .colorData(255, 220, 160, 240)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1941)
                .boilingPoint(3560)
                .densityGramPerCm3(4.54)
                .build()
        );
    // Protons, neutrons, electrons: 23, 23, 28
    public static final GTMaterial VANADIUM =
        GTRegisters.MATERIALS.register(
            "vanadium",
            () -> GTMaterial.builder()
                .name("vanadium")
                .englishNameForDatagen("Vanadium")
                .colorData(255, 50, 50, 50)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(2183)
                .boilingPoint(3680)
                .densityGramPerCm3(6.11)
                .build()
        );
    // Protons, neutrons, electrons: 24, 24, 28
    public static final GTMaterial CHROMIUM =
        GTRegisters.MATERIALS.register(
            "chromium",
            () -> GTMaterial.builder()
                .name("chromium")
                .englishNameForDatagen("Chromium")
                .colorData(255, 255, 230, 230)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(2180)
                .boilingPoint(2944)
                .densityGramPerCm3(7.15)
                .build()
        );
    // Protons, neutrons, electrons: 25, 25, 30
    public static final GTMaterial MANGANESE =
        GTRegisters.MATERIALS.register(
            "manganese",
            () -> GTMaterial.builder()
                .name("manganese")
                .englishNameForDatagen("Manganese")
                .colorData(255, 250, 250, 250)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(1519)
                .boilingPoint(2334)
                .densityGramPerCm3(7.44)
                .build()
        );
    // Protons, neutrons, electrons: 26, 26, 30
    public static final GTMaterial IRON =
        GTRegisters.MATERIALS.register(
            "iron",
            () -> GTMaterial.builder()
                .name("iron")
                .englishNameForDatagen("Iron")
                .colorData(255, 200, 200, 200)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1811)
                .boilingPoint(3134)
                .densityGramPerCm3(7.874)
                .build()
        );
    // Protons, neutrons, electrons: 27, 27, 32
    public static final GTMaterial COBALT =
        GTRegisters.MATERIALS.register(
            "cobalt",
            () -> GTMaterial.builder()
                .name("cobalt")
                .englishNameForDatagen("Cobalt")
                .colorData(255, 80, 80, 250)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1768)
                .boilingPoint(3200)
                .densityGramPerCm3(8.86)
                .build()
        );
    // Protons, neutrons, electrons: 27, 27, 33
    public static final GTMaterial COBALT_60 =
        GTRegisters.MATERIALS.register(
            "cobalt_60",
            () -> GTMaterial.builder()
                .name("cobalt_60")
                .englishNameForDatagen("Cobalt-60")
                .colorData(255, 90, 90, 250)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1768)
                .boilingPoint(3200)
                .densityGramPerCm3(8.86)
                .build()
        );
    // Protons, neutrons, electrons: 28, 28, 30
    public static final GTMaterial NICKEL =
        GTRegisters.MATERIALS.register(
            "nickel",
            () -> GTMaterial.builder()
                .name("nickel")
                .englishNameForDatagen("Nickel")
                .colorData(255, 250, 250, 200)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1728)
                .boilingPoint(3186)
                .densityGramPerCm3(8.912)
                .build()
        );
    // Protons, neutrons, electrons: 29, 29, 34
    public static final GTMaterial COPPER =
        GTRegisters.MATERIALS.register(
            "copper",
            () -> GTMaterial.builder()
                .name("copper")
                .englishNameForDatagen("Copper")
                .colorData(255, 255, 130, 90)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(1357)
                .boilingPoint(2835)
                .densityGramPerCm3(8.96)
                .build()
        );
    // Protons, neutrons, electrons: 30, 30, 35
    public static final GTMaterial ZINC =
        GTRegisters.MATERIALS.register(
            "zinc",
            () -> GTMaterial.builder()
                .name("zinc")
                .englishNameForDatagen("Zinc")
                .colorData(255, 250, 240, 240)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(692)
                .boilingPoint(1180)
                .densityGramPerCm3(7.134)
                .build()
        );
    // Protons, neutrons, electrons: 31, 31, 39
    public static final GTMaterial GALLIUM =
        GTRegisters.MATERIALS.register(
            "gallium",
            () -> GTMaterial.builder()
                .name("gallium")
                .englishNameForDatagen("Gallium")
                .colorData(255, 220, 220, 255)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(302)
                .boilingPoint(2477)
                .densityGramPerCm3(5.907)
                .build()
        );
    // Protons, neutrons, electrons: 32, 32, 40
    public static final GTMaterial GERMANIUM =
        GTRegisters.MATERIALS.register(
            "germanium",
            () -> GTMaterial.builder()
                .name("germanium")
                .englishNameForDatagen("Germanium")
                .colorData(255, 212, 212, 212)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(1211)
                .boilingPoint(3106)
                .densityGramPerCm3(5.323)
                .build()
        );
    // Protons, neutrons, electrons: 33, 33, 42
    public static final GTMaterial ARSENIC =
        GTRegisters.MATERIALS.register(
            "arsenic",
            () -> GTMaterial.builder()
                .name("arsenic")
                .englishNameForDatagen("Arsenic")
                .colorData(255, 103, 103, 86)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(887)
                .boilingPoint(1090)
                .densityGramPerCm3(5.776)
                .build()
        );
    // Protons, neutrons, electrons: 34, 34, 45
    public static final GTMaterial SELENIUM =
        GTRegisters.MATERIALS.register(
            "selenium",
            () -> GTMaterial.builder()
                .name("selenium")
                .englishNameForDatagen("Selenium")
                .colorData(255, 111, 20, 20)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(453)
                .boilingPoint(958)
                .densityGramPerCm3(4.809)
                .build()
        );
    // Protons, neutrons, electrons: 35, 35, 45
    public static final GTMaterial BROMINE =
        GTRegisters.MATERIALS.register(
            "bromine",
            () -> GTMaterial.builder()
                .name("bromine")
                .englishNameForDatagen("Bromine")
                .colorData(255, 80, 10, 10)
                .textureSet(GTMaterialTextureSets.FLUID)
                .meltingPoint(265)
                .boilingPoint(332)
                .densityGramPerCm3(3.122)
                .build()
        );
    // Protons, neutrons, electrons: 36, 36, 48
    public static final GTMaterial KRYPTON =
        GTRegisters.MATERIALS.register(
            "krypton",
            () -> GTMaterial.builder()
                .name("krypton")
                .englishNameForDatagen("Krypton")
                .colorData(15, 128, 255, 128)
                .textureSet(GTMaterialTextureSets.DIAMOND)
                .meltingPoint(115)
                .boilingPoint(119)
                .densityGramPerCm3(0.003733)
                .build()
        );
    // Protons, neutrons, electrons: 37, 37, 48
    public static final GTMaterial RUBIDIUM =
        GTRegisters.MATERIALS.register(
            "rubidium",
            () -> GTMaterial.builder()
                .name("rubidium")
                .englishNameForDatagen("Rubidium")
                .colorData(255, 240, 30, 30)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(312)
                .boilingPoint(961)
                .densityGramPerCm3(1.532)
                .build()
        );
    // Protons, neutrons, electrons: 38, 38, 49
    public static final GTMaterial STRONTIUM =
        GTRegisters.MATERIALS.register(
            "strontium",
            () -> GTMaterial.builder()
                .name("strontium")
                .englishNameForDatagen("Strontium")
                .colorData(255, 200, 200, 200)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1050)
                .boilingPoint(1655)
                .densityGramPerCm3(2.64)
                .build()
        );
    // Protons, neutrons, electrons: 39, 39, 50
    public static final GTMaterial YTTRIUM =
        GTRegisters.MATERIALS.register(
            "yttrium",
            () -> GTMaterial.builder()
                .name("yttrium")
                .englishNameForDatagen("Yttrium")
                .colorData(255, 220, 250, 220)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1799)
                .boilingPoint(3609)
                .densityGramPerCm3(4.469)
                .build()
        );
    // Protons, neutrons, electrons: 40, 40, 51
    public static final GTMaterial ZIRCONIUM =
        GTRegisters.MATERIALS.register(
            "zirconium",
            () -> GTMaterial.builder()
                .name("zirconium")
                .englishNameForDatagen("Zirconium")
                .colorData(127, 200, 255, 255)
                .textureSet(GTMaterialTextureSets.DIAMOND)
                .meltingPoint(2128)
                .boilingPoint(4682)
                .densityGramPerCm3(6.506)
                .build()
        );
    // Protons, neutrons, electrons: 41, 41, 53
    public static final GTMaterial NIOBIUM =
        GTRegisters.MATERIALS.register(
            "niobium",
            () -> GTMaterial.builder()
                .name("niobium")
                .englishNameForDatagen("Niobium")
                .colorData(255, 190, 180, 200)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(2750)
                .boilingPoint(5017)
                .densityGramPerCm3(8.57)
                .build()
        );
    // Protons, neutrons, electrons: 42, 42, 53
    public static final GTMaterial MOLYBDENUM =
        GTRegisters.MATERIALS.register(
            "molybdenum",
            () -> GTMaterial.builder()
                .name("molybdenum")
                .englishNameForDatagen("Molybdenum")
                .colorData(255, 180, 180, 220)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(2896)
                .boilingPoint(4912)
                .densityGramPerCm3(10.22)
                .build()
        );
    // Protons, neutrons, electrons: 43, 43, 55
    public static final GTMaterial TECHNETIUM =
        GTRegisters.MATERIALS.register(
            "technetium",
            () -> GTMaterial.builder()
                .name("technetium")
                .englishNameForDatagen("Technetium")
                .colorData(255, 66, 66, 99)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(2430)
                .boilingPoint(4538)
                .densityGramPerCm3(11.5)
                .build()
        );
    // Protons, neutrons, electrons: 44, 44, 57
    public static final GTMaterial RUTHENIUM =
        GTRegisters.MATERIALS.register(
            "ruthenium",
            () -> GTMaterial.builder()
                .name("ruthenium")
                .englishNameForDatagen("Ruthenium")
                .colorData(255, 155, 155, 155)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(2607)
                .boilingPoint(4423)
                .densityGramPerCm3(12.37)
                .build()
        );
    // Protons, neutrons, electrons: 45, 45, 58
    public static final GTMaterial RHODIUM =
        GTRegisters.MATERIALS.register(
            "rhodium",
            () -> GTMaterial.builder()
                .name("rhodium")
                .englishNameForDatagen("Rhodium")
                .colorData(255, 144, 144, 144)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(2237)
                .boilingPoint(3968)
                .densityGramPerCm3(12.41)
                .build()
        );
    // Protons, neutrons, electrons: 46, 46, 60
    public static final GTMaterial PALLADIUM =
        GTRegisters.MATERIALS.register(
            "palladium",
            () -> GTMaterial.builder()
                .name("palladium")
                .englishNameForDatagen("Palladium")
                .colorData(255, 128, 128, 128)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(1828)
                .boilingPoint(3236)
                .densityGramPerCm3(12.02)
                .build()
        );
    // Protons, neutrons, electrons: 47, 47, 60
    public static final GTMaterial SILVER =
        GTRegisters.MATERIALS.register(
            "silver",
            () -> GTMaterial.builder()
                .name("silver")
                .englishNameForDatagen("Silver")
                .colorData(255, 220, 220, 255)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(1234)
                .boilingPoint(2435)
                .densityGramPerCm3(10.501)
                .build()
        );
    // Protons, neutrons, electrons: 48, 48, 64
    public static final GTMaterial CADMIUM =
        GTRegisters.MATERIALS.register(
            "cadmium",
            () -> GTMaterial.builder()
                .name("cadmium")
                .englishNameForDatagen("Cadmium")
                .colorData(255, 50, 50, 60)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(594)
                .boilingPoint(1040)
                .densityGramPerCm3(8.69)
                .build()
        );
    // Protons, neutrons, electrons: 49, 49, 65
    public static final GTMaterial INDIUM =
        GTRegisters.MATERIALS.register(
            "indium",
            () -> GTMaterial.builder()
                .name("indium")
                .englishNameForDatagen("Indium")
                .colorData(255, 64, 0, 128)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(429)
                .boilingPoint(2345)
                .densityGramPerCm3(7.31)
                .build()
        );
    // Protons, neutrons, electrons: 50, 50, 68
    public static final GTMaterial TIN =
        GTRegisters.MATERIALS.register(
            "tin",
            () -> GTMaterial.builder()
                .name("tin")
                .englishNameForDatagen("Tin")
                .colorData(255, 220, 220, 220)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(505)
                .boilingPoint(2875)
                .densityGramPerCm3(7.287)
                .build()
        );
    // Protons, neutrons, electrons: 51, 51, 70
    public static final GTMaterial ANTIMONY =
        GTRegisters.MATERIALS.register(
            "antimony",
            () -> GTMaterial.builder()
                .name("antimony")
                .englishNameForDatagen("Antimony")
                .colorData(255, 220, 220, 240)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(903)
                .boilingPoint(1860)
                .densityGramPerCm3(6.685)
                .build()
        );
    // Protons, neutrons, electrons: 52, 52, 75
    public static final GTMaterial TELLURIUM =
        GTRegisters.MATERIALS.register(
            "tellurium",
            () -> GTMaterial.builder()
                .name("tellurium")
                .englishNameForDatagen("Tellurium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(722)
                .boilingPoint(1261)
                .densityGramPerCm3(6.232)
                .build()
        );
    // Protons, neutrons, electrons: 53, 53, 74
    public static final GTMaterial IODINE =
        GTRegisters.MATERIALS.register(
            "iodine",
            () -> GTMaterial.builder()
                .name("iodine")
                .englishNameForDatagen("Iodine")
                .colorData(255, 255, 240, 240)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(386)
                .boilingPoint(457)
                .densityGramPerCm3(4.93)
                .build()
        );
    // Protons, neutrons, electrons: 54, 54, 77
    public static final GTMaterial XENON =
        GTRegisters.MATERIALS.register(
            "xenon",
            () -> GTMaterial.builder()
                .name("xenon")
                .englishNameForDatagen("Xenon")
                .colorData(15, 0, 255, 255)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(161)
                .boilingPoint(165)
                .densityGramPerCm3(0.005887)
                .build()
        );
    // Protons, neutrons, electrons: 55, 55, 77
    public static final GTMaterial CAESIUM =
        GTRegisters.MATERIALS.register(
            "caesium",
            () -> GTMaterial.builder()
                .name("caesium")
                .englishNameForDatagen("Caesium")
                .colorData(255, 128, 98, 11)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(301)
                .boilingPoint(944)
                .densityGramPerCm3(1.873)
                .build()
        );
    // Protons, neutrons, electrons: 56, 56, 81
    public static final GTMaterial BARIUM =
        GTRegisters.MATERIALS.register(
            "barium",
            () -> GTMaterial.builder()
                .name("barium")
                .englishNameForDatagen("Barium")
                .colorData(255, 131, 130, 76)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1000)
                .boilingPoint(2170)
                .densityGramPerCm3(3.594)
                .build()
        );
    // Protons, neutrons, electrons: 57, 57, 81
    public static final GTMaterial LANTHANUM =
        GTRegisters.MATERIALS.register(
            "lanthanum",
            () -> GTMaterial.builder()
                .name("lanthanum")
                .englishNameForDatagen("Lanthanum")
                .colorData(255, 93, 117, 117)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1193)
                .boilingPoint(3737)
                .densityGramPerCm3(6.145)
                .build()
        );
    // Protons, neutrons, electrons: 58, 58, 82
    public static final GTMaterial CERIUM =
        GTRegisters.MATERIALS.register(
            "cerium",
            () -> GTMaterial.builder()
                .name("cerium")
                .englishNameForDatagen("Cerium")
                .colorData(255, 255, 255, 190)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(1068)
                .boilingPoint(3716)
                .densityGramPerCm3(6.77)
                .build()
        );
    // Protons, neutrons, electrons: 59, 59, 81
    public static final GTMaterial PRASEODYMIUM =
        GTRegisters.MATERIALS.register(
            "praseodymium",
            () -> GTMaterial.builder()
                .name("praseodymium")
                .englishNameForDatagen("Praseodymium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1208)
                .boilingPoint(3793)
                .densityGramPerCm3(6.773)
                .build()
        );
    // Protons, neutrons, electrons: 60, 60, 84
    public static final GTMaterial NEODYMIUM =
        GTRegisters.MATERIALS.register(
            "neodymium",
            () -> GTMaterial.builder()
                .name("neodymium")
                .englishNameForDatagen("Neodymium")
                .colorData(255, 100, 100, 100)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(1297)
                .boilingPoint(3347)
                .densityGramPerCm3(7.007)
                .build()
        );
    // Protons, neutrons, electrons: 61, 61, 83
    public static final GTMaterial PROMETHIUM =
        GTRegisters.MATERIALS.register(
            "promethium",
            () -> GTMaterial.builder()
                .name("promethium")
                .englishNameForDatagen("Promethium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1315)
                .boilingPoint(3273)
                .densityGramPerCm3(7.26)
                .build()
        );
    // Protons, neutrons, electrons: 62, 62, 88
    public static final GTMaterial SAMARIUM =
        GTRegisters.MATERIALS.register(
            "samarium",
            () -> GTMaterial.builder()
                .name("samarium")
                .englishNameForDatagen("Samarium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1345)
                .boilingPoint(2067)
                .densityGramPerCm3(7.52)
                .build()
        );
    // Protons, neutrons, electrons: 63, 63, 88
    public static final GTMaterial EUROPIUM =
        GTRegisters.MATERIALS.register(
            "europium",
            () -> GTMaterial.builder()
                .name("europium")
                .englishNameForDatagen("Europium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1099)
                .boilingPoint(1802)
                .densityGramPerCm3(5.243)
                .build()
        );
    // Protons, neutrons, electrons: 64, 64, 93
    public static final GTMaterial GADOLINIUM =
        GTRegisters.MATERIALS.register(
            "gadolinium",
            () -> GTMaterial.builder()
                .name("gadolinium")
                .englishNameForDatagen("Gadolinium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1585)
                .boilingPoint(3546)
                .densityGramPerCm3(7.895)
                .build()
        );
    // Protons, neutrons, electrons: 65, 65, 93
    public static final GTMaterial TERBIUM =
        GTRegisters.MATERIALS.register(
            "terbium",
            () -> GTMaterial.builder()
                .name("terbium")
                .englishNameForDatagen("Terbium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1629)
                .boilingPoint(3503)
                .densityGramPerCm3(8.229)
                .build()
        );
    // Protons, neutrons, electrons: 66, 66, 96
    public static final GTMaterial DYSPROSIUM =
        GTRegisters.MATERIALS.register(
            "dysprosium",
            () -> GTMaterial.builder()
                .name("dysprosium")
                .englishNameForDatagen("Dysprosium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1680)
                .boilingPoint(2840)
                .densityGramPerCm3(8.55)
                .build()
        );
    // Protons, neutrons, electrons: 67, 67, 97
    public static final GTMaterial HOLMIUM =
        GTRegisters.MATERIALS.register(
            "holmium",
            () -> GTMaterial.builder()
                .name("holmium")
                .englishNameForDatagen("Holmium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1734)
                .boilingPoint(2993)
                .densityGramPerCm3(8.795)
                .build()
        );
    // Protons, neutrons, electrons: 68, 68, 99
    public static final GTMaterial ERBIUM =
        GTRegisters.MATERIALS.register(
            "erbium",
            () -> GTMaterial.builder()
                .name("erbium")
                .englishNameForDatagen("Erbium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1802)
                .boilingPoint(3141)
                .densityGramPerCm3(9.066)
                .build()
        );
    // Protons, neutrons, electrons: 69, 69, 99
    public static final GTMaterial THULIUM =
        GTRegisters.MATERIALS.register(
            "thulium",
            () -> GTMaterial.builder()
                .name("thulium")
                .englishNameForDatagen("Thulium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1818)
                .boilingPoint(2223)
                .densityGramPerCm3(9.321)
                .build()
        );
    // Protons, neutrons, electrons: 70, 70, 103
    public static final GTMaterial YTTERBIUM =
        GTRegisters.MATERIALS.register(
            "ytterbium",
            () -> GTMaterial.builder()
                .name("ytterbium")
                .englishNameForDatagen("Ytterbium")
                .colorData(255, 167, 167, 167)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1097)
                .boilingPoint(1469)
                .densityGramPerCm3(6.965)
                .build()
        );
    // Protons, neutrons, electrons: 71, 71, 103
    public static final GTMaterial LUTETIUM =
        GTRegisters.MATERIALS.register(
            "lutetium",
            () -> GTMaterial.builder()
                .name("lutetium")
                .englishNameForDatagen("Lutetium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(1925)
                .boilingPoint(3675)
                .densityGramPerCm3(9.84)
                .build()
        );
    // Protons, neutrons, electrons: 72, 72, 106
    public static final GTMaterial HAFNIUM =
        GTRegisters.MATERIALS.register(
            "hafnium",
            () -> GTMaterial.builder()
                .name("hafnium")
                .englishNameForDatagen("Hafnium")
                .colorData(255, 140, 140, 150)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(2506)
                .boilingPoint(4876)
                .densityGramPerCm3(13.31)
                .build()
        );
    // Protons, neutrons, electrons: 73, 73, 107
    public static final GTMaterial TANTALUM =
        GTRegisters.MATERIALS.register(
            "tantalum",
            () -> GTMaterial.builder()
                .name("tantalum")
                .englishNameForDatagen("Tantalum")
                .colorData(255, 120, 120, 140)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(3290)
                .boilingPoint(5731)
                .densityGramPerCm3(16.654)
                .build()
        );
    // Protons, neutrons, electrons: 74, 74, 109
    public static final GTMaterial TUNGSTEN =
        GTRegisters.MATERIALS.register(
            "tungsten",
            () -> GTMaterial.builder()
                .name("tungsten")
                .englishNameForDatagen("Tungsten")
                .colorData(255, 50, 50, 50)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(3695)
                .boilingPoint(5828)
                .densityGramPerCm3(19.25)
                .build()
        );
    // Protons, neutrons, electrons: 75, 75, 111
    public static final GTMaterial RHENIUM =
        GTRegisters.MATERIALS.register(
            "rhenium",
            () -> GTMaterial.builder()
                .name("rhenium")
                .englishNameForDatagen("Rhenium")
                .colorData(255, 255, 255, 200)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(3459)
                .boilingPoint(5869)
                .densityGramPerCm3(21.02)
                .build()
        );
    // Protons, neutrons, electrons: 76, 76, 114
    public static final GTMaterial OSMIUM =
        GTRegisters.MATERIALS.register(
            "osmium",
            () -> GTMaterial.builder()
                .name("osmium")
                .englishNameForDatagen("Osmium")
                .colorData(255, 50, 50, 255)
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(3306)
                .boilingPoint(5285)
                .densityGramPerCm3(22.61)
                .build()
        );
    // Protons, neutrons, electrons: 77, 77, 115
    public static final GTMaterial IRIDIUM =
        GTRegisters.MATERIALS.register(
            "iridium",
            () -> GTMaterial.builder()
                .name("iridium")
                .englishNameForDatagen("Iridium")
                .colorData(255, 240, 240, 245)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(2719)
                .boilingPoint(4701)
                .densityGramPerCm3(22.56)
                .build()
        );
    // Protons, neutrons, electrons: 78, 78, 117
    public static final GTMaterial PLATINUM =
        GTRegisters.MATERIALS.register(
            "platinum",
            () -> GTMaterial.builder()
                .name("platinum")
                .englishNameForDatagen("Platinum")
                .colorData(255, 100, 180, 250)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(2041)
                .boilingPoint(4098)
                .densityGramPerCm3(21.46)
                .build()
        );
    // Protons, neutrons, electrons: 79, 79, 117
    public static final GTMaterial GOLD =
        GTRegisters.MATERIALS.register(
            "gold",
            () -> GTMaterial.builder()
                .name("gold")
                .englishNameForDatagen("Gold")
                .colorData(255, 255, 230, 80)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(1337)
                .boilingPoint(3129)
                .densityGramPerCm3(19.282)
                .build()
        );
    // Protons, neutrons, electrons: 79, 79, 119
    public static final GTMaterial GOLD_198 =
        GTRegisters.MATERIALS.register(
            "gold_198",
            () -> GTMaterial.builder()
                .name("gold_198")
                .englishNameForDatagen("Gold-198")
                .colorData(255, 255, 230, 80)
                .textureSet(GTMaterialTextureSets.SHINY)
                .meltingPoint(1337)
                .boilingPoint(3129)
                .densityGramPerCm3(19.282)
                .build()
        );
    // Protons, neutrons, electrons: 80, 80, 120
    public static final GTMaterial MERCURY =
        GTRegisters.MATERIALS.register(
            "mercury",
            () -> GTMaterial.builder()
                .name("mercury")
                .englishNameForDatagen("Mercury")
                .colorData(255, 230, 220, 220)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(234)
                .boilingPoint(629)
                .densityGramPerCm3(13.5336)
                .build()
        );
    // Protons, neutrons, electrons: 81, 81, 123
    public static final GTMaterial THALLIUM =
        GTRegisters.MATERIALS.register(
            "thallium",
            () -> GTMaterial.builder()
                .name("thallium")
                .englishNameForDatagen("Thallium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.METALLIC)
                .meltingPoint(577)
                .boilingPoint(1746)
                .densityGramPerCm3(11.85)
                .build()
        );
    // Protons, neutrons, electrons: 82, 82, 125
    public static final GTMaterial LEAD =
        GTRegisters.MATERIALS.register(
            "lead",
            () -> GTMaterial.builder()
                .name("lead")
                .englishNameForDatagen("Lead")
                .colorData(255, 60, 40, 110)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(600)
                .boilingPoint(2022)
                .densityGramPerCm3(11.342)
                .build()
        );
    // Protons, neutrons, electrons: 83, 83, 125
    public static final GTMaterial BISMUTH =
        GTRegisters.MATERIALS.register(
            "bismuth",
            () -> GTMaterial.builder()
                .name("bismuth")
                .englishNameForDatagen("Bismuth")
                .colorData(255, 100, 160, 160)
                .textureSet(GTMaterialTextureSets.COPPER)
                .meltingPoint(544)
                .boilingPoint(1837)
                .densityGramPerCm3(9.807)
                .build()
        );
    // Protons, neutrons, electrons: 84, 84, 124
    public static final GTMaterial POLONIUM =
        GTRegisters.MATERIALS.register(
            "polonium",
            () -> GTMaterial.builder()
                .name("polonium")
                .englishNameForDatagen("Polonium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(527)
                .boilingPoint(1235)
                .densityGramPerCm3(9.32)
                .build()
        );
    // Protons, neutrons, electrons: 85, 85, 124
    public static final GTMaterial ASTATINE =
        GTRegisters.MATERIALS.register(
            "astatine",
            () -> GTMaterial.builder()
                .name("astatine")
                .englishNameForDatagen("Astatine")
                .colorData(255, 33, 33, 33)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(575)
                .boilingPoint(610)
                .densityGramPerCm3(7.0)
                .build()
        );
    // Protons, neutrons, electrons: 86, 86, 134
    public static final GTMaterial RADON =
        GTRegisters.MATERIALS.register(
            "radon",
            () -> GTMaterial.builder()
                .name("radon")
                .englishNameForDatagen("Radon")
                .colorData(15, 255, 0, 255)
                .textureSet(GTMaterialTextureSets.DULL)
                .meltingPoint(202)
                .boilingPoint(211)
                .densityGramPerCm3(0.00973)
                .build()
        );
    // Protons, neutrons, electrons: 87, 87, 134
    public static final GTMaterial FRANCIUM =
        GTRegisters.MATERIALS.register(
            "francium",
            () -> GTMaterial.builder()
                .name("francium")
                .englishNameForDatagen("Francium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(300)
                .boilingPoint(950)
                .densityGramPerCm3(1.87)
                .build()
        );
    // Protons, neutrons, electrons: 88, 88, 138
    public static final GTMaterial RADIUM =
        GTRegisters.MATERIALS.register(
            "radium",
            () -> GTMaterial.builder()
                .name("radium")
                .englishNameForDatagen("Radium")
                .colorData(255, 255, 255, 205)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(973)
                .boilingPoint(2010)
                .densityGramPerCm3(5.5)
                .build()
        );
    // Protons, neutrons, electrons: 89, 89, 136
    public static final GTMaterial ACTINIUM =
        GTRegisters.MATERIALS.register(
            "actinium",
            () -> GTMaterial.builder()
                .name("actinium")
                .englishNameForDatagen("Actinium")
                .colorData(255, 125, 113, 113)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1323)
                .boilingPoint(3471)
                .densityGramPerCm3(10.07)
                .build()
        );
    // Protons, neutrons, electrons: 90, 90, 142
    public static final GTMaterial THORIUM =
        GTRegisters.MATERIALS.register(
            "thorium",
            () -> GTMaterial.builder()
                .name("thorium")
                .englishNameForDatagen("Thorium")
                .colorData(255, 0, 30, 0)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(2115)
                .boilingPoint(5061)
                .densityGramPerCm3(11.72)
                .build()
        );
    // Protons, neutrons, electrons: 91, 91, 138
    public static final GTMaterial PROTACTINIUM =
        GTRegisters.MATERIALS.register(
            "protactinium",
            () -> GTMaterial.builder()
                .name("protactinium")
                .englishNameForDatagen("Protactinium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1841)
                .boilingPoint(4300)
                .densityGramPerCm3(15.37)
                .build()
        );
    // Protons, neutrons, electrons: 92, 92, 146
    public static final GTMaterial URANIUM =
        GTRegisters.MATERIALS.register(
            "uranium",
            () -> GTMaterial.builder()
                .name("uranium")
                .englishNameForDatagen("Uranium")
                .colorData(255, 50, 240, 50)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1405)
                .boilingPoint(4404)
                .densityGramPerCm3(18.95)
                .build()
        );
    // Protons, neutrons, electrons: 92, 92, 143
    public static final GTMaterial URANIUM_235 =
        GTRegisters.MATERIALS.register(
            "uranium_235",
            () -> GTMaterial.builder()
                .name("uranium_235")
                .englishNameForDatagen("Uranium-235")
                .colorData(255, 70, 250, 70)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1405)
                .boilingPoint(4404)
                .densityGramPerCm3(18.95)
                .build()
        );
    // Protons, neutrons, electrons: 92, 92, 141
    public static final GTMaterial URANIUM_233 =
        GTRegisters.MATERIALS.register(
            "uranium_233",
            () -> GTMaterial.builder()
                .name("uranium_233")
                .englishNameForDatagen("Uranium-233")
                .colorData(255, 70, 250, 50)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1405)
                .boilingPoint(4404)
                .densityGramPerCm3(18.95)
                .build()
        );
    // Protons, neutrons, electrons: 93, 93, 144
    public static final GTMaterial NEPTUNIUM =
        GTRegisters.MATERIALS.register(
            "neptunium",
            () -> GTMaterial.builder()
                .name("neptunium")
                .englishNameForDatagen("Neptunium")
                .colorData(255, 78, 90, 78)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(917)
                .boilingPoint(4273)
                .densityGramPerCm3(20.45)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 150
    public static final GTMaterial PLUTONIUM =
        GTRegisters.MATERIALS.register(
            "plutonium",
            () -> GTMaterial.builder()
                .name("plutonium")
                .englishNameForDatagen("Plutonium")
                .colorData(255, 240, 50, 50)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 146
    public static final GTMaterial PLUTONIUM_240 =
        GTRegisters.MATERIALS.register(
            "plutonium_240",
            () -> GTMaterial.builder()
                .name("plutonium_240")
                .englishNameForDatagen("Plutonium-240")
                .colorData(255, 235, 30, 30)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 147
    public static final GTMaterial PLUTONIUM_241 =
        GTRegisters.MATERIALS.register(
            "plutonium_241",
            () -> GTMaterial.builder()
                .name("plutonium_241")
                .englishNameForDatagen("Plutonium-241")
                .colorData(255, 245, 70, 70)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 149
    public static final GTMaterial PLUTONIUM_243 =
        GTRegisters.MATERIALS.register(
            "plutonium_243",
            () -> GTMaterial.builder()
                .name("plutonium_243")
                .englishNameForDatagen("Plutonium-243")
                .colorData(255, 250, 70, 70)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 144
    public static final GTMaterial PLUTONIUM_238 =
        GTRegisters.MATERIALS.register(
            "plutonium_238",
            () -> GTMaterial.builder()
                .name("plutonium_238")
                .englishNameForDatagen("Plutonium-238")
                .colorData(255, 250, 30, 30)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 145
    public static final GTMaterial PLUTONIUM_239 =
        GTRegisters.MATERIALS.register(
            "plutonium_239",
            () -> GTMaterial.builder()
                .name("plutonium_239")
                .englishNameForDatagen("Plutonium-239")
                .colorData(255, 235, 50, 50)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 95, 95, 150
    public static final GTMaterial AMERICIUM =
        GTRegisters.MATERIALS.register(
            "americium",
            () -> GTMaterial.builder()
                .name("americium")
                .englishNameForDatagen("Americium")
                .colorData(255, 200, 200, 200)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1449)
                .boilingPoint(2880)
                .densityGramPerCm3(13.69)
                .build()
        );
    // Protons, neutrons, electrons: 95, 95, 146
    public static final GTMaterial AMERICIUM_241 =
        GTRegisters.MATERIALS.register(
            "americium_241",
            () -> GTMaterial.builder()
                .name("americium_241")
                .englishNameForDatagen("Americium-241")
                .colorData(255, 210, 210, 210)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1449)
                .boilingPoint(2880)
                .densityGramPerCm3(13.69)
                .build()
        );
    // Protons, neutrons, electrons: 95, 95, 147
    public static final GTMaterial AMERICIUM_242 =
        GTRegisters.MATERIALS.register(
            "americium_242",
            () -> GTMaterial.builder()
                .name("americium_242")
                .englishNameForDatagen("Americium-242")
                .colorData(255, 210, 210, 210)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1449)
                .boilingPoint(2880)
                .densityGramPerCm3(13.69)
                .build()
        );
    // Protons, neutrons, electrons: 96, 96, 153
    public static final GTMaterial CURIUM =
        GTRegisters.MATERIALS.register(
            "curium",
            () -> GTMaterial.builder()
                .name("curium")
                .englishNameForDatagen("Curium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1613)
                .boilingPoint(3383)
                .densityGramPerCm3(13.51)
                .build()
        );
    // Protons, neutrons, electrons: 97, 97, 152
    public static final GTMaterial BERKELIUM =
        GTRegisters.MATERIALS.register(
            "berkelium",
            () -> GTMaterial.builder()
                .name("berkelium")
                .englishNameForDatagen("Berkelium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1259)
                .boilingPoint(2900)
                .densityGramPerCm3(14.79)
                .build()
        );
    // Protons, neutrons, electrons: 98, 98, 153
    public static final GTMaterial CALIFORNIUM =
        GTRegisters.MATERIALS.register(
            "californium",
            () -> GTMaterial.builder()
                .name("californium")
                .englishNameForDatagen("Californium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1173)
                .boilingPoint(1743)
                .densityGramPerCm3(15.1)
                .build()
        );
    // Protons, neutrons, electrons: 99, 99, 153
    public static final GTMaterial EINSTEINIUM =
        GTRegisters.MATERIALS.register(
            "einsteinium",
            () -> GTMaterial.builder()
                .name("einsteinium")
                .englishNameForDatagen("Einsteinium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1133)
                .boilingPoint(1269)
                .densityGramPerCm3(8.84)
                .build()
        );
    // Protons, neutrons, electrons: 100, 100, 157
    public static final GTMaterial FERMIUM =
        GTRegisters.MATERIALS.register(
            "fermium",
            () -> GTMaterial.builder()
                .name("fermium")
                .englishNameForDatagen("Fermium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1125)
                .boilingPoint(3000)
                .densityGramPerCm3(0.0)
                .build()
        );
    // Protons, neutrons, electrons: 101, 101, 157
    public static final GTMaterial MENDELEVIUM =
        GTRegisters.MATERIALS.register(
            "mendelevium",
            () -> GTMaterial.builder()
                .name("mendelevium")
                .englishNameForDatagen("Mendelevium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1100)
                .boilingPoint(3000)
                .densityGramPerCm3(0.0)
                .build()
        );
    // Protons, neutrons, electrons: 102, 102, 157
    public static final GTMaterial NOBELIUM =
        GTRegisters.MATERIALS.register(
            "nobelium",
            () -> GTMaterial.builder()
                .name("nobelium")
                .englishNameForDatagen("Nobelium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1100)
                .boilingPoint(3000)
                .densityGramPerCm3(0.0)
                .build()
        );
    // Protons, neutrons, electrons: 103, 103, 159
    public static final GTMaterial LAWRENCIUM =
        GTRegisters.MATERIALS.register(
            "lawrencium",
            () -> GTMaterial.builder()
                .name("lawrencium")
                .englishNameForDatagen("Lawrencium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1900)
                .boilingPoint(3000)
                .densityGramPerCm3(0.0)
                .build()
        );
    // Protons, neutrons, electrons: 104, 104, 161
    public static final GTMaterial RUTHERFORDIUM =
        GTRegisters.MATERIALS.register(
            "rutherfordium",
            () -> GTMaterial.builder()
                .name("rutherfordium")
                .englishNameForDatagen("Rutherfordium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(2400)
                .boilingPoint(5800)
                .densityGramPerCm3(23.2)
                .build()
        );
    // Protons, neutrons, electrons: 105, 105, 163
    public static final GTMaterial DUBNIUM =
        GTRegisters.MATERIALS.register(
            "dubnium",
            () -> GTMaterial.builder()
                .name("dubnium")
                .englishNameForDatagen("Dubnium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(29.3)
                .build()
        );
    // Protons, neutrons, electrons: 106, 106, 165
    public static final GTMaterial SEABORGIUM =
        GTRegisters.MATERIALS.register(
            "seaborgium",
            () -> GTMaterial.builder()
                .name("seaborgium")
                .englishNameForDatagen("Seaborgium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(35.0)
                .build()
        );
    // Protons, neutrons, electrons: 107, 107, 163
    public static final GTMaterial BOHRIUM =
        GTRegisters.MATERIALS.register(
            "bohrium",
            () -> GTMaterial.builder()
                .name("bohrium")
                .englishNameForDatagen("Bohrium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(37.1)
                .build()
        );
    // Protons, neutrons, electrons: 108, 108, 169
    public static final GTMaterial HASSIUM =
        GTRegisters.MATERIALS.register(
            "hassium",
            () -> GTMaterial.builder()
                .name("hassium")
                .englishNameForDatagen("Hassium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(40.7)
                .build()
        );
    // Protons, neutrons, electrons: 109, 109, 167
    public static final GTMaterial MEITNERIUM =
        GTRegisters.MATERIALS.register(
            "meitnerium",
            () -> GTMaterial.builder()
                .name("meitnerium")
                .englishNameForDatagen("Meitnerium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(37.4)
                .build()
        );
    // Protons, neutrons, electrons: 110, 110, 171
    public static final GTMaterial DARMSTADTIUM =
        GTRegisters.MATERIALS.register(
            "darmstadtium",
            () -> GTMaterial.builder()
                .name("darmstadtium")
                .englishNameForDatagen("Darmstadtium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(34.8)
                .build()
        );
    // Protons, neutrons, electrons: 111, 111, 169
    public static final GTMaterial ROENTGENIUM =
        GTRegisters.MATERIALS.register(
            "roentgenium",
            () -> GTMaterial.builder()
                .name("roentgenium")
                .englishNameForDatagen("Roentgenium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(28.7)
                .build()
        );
    // Protons, neutrons, electrons: 112, 112, 173
    public static final GTMaterial COPERNICIUM =
        GTRegisters.MATERIALS.register(
            "copernicium",
            () -> GTMaterial.builder()
                .name("copernicium")
                .englishNameForDatagen("Copernicium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(150)
                .boilingPoint(357)
                .densityGramPerCm3(23.7)
                .build()
        );
    // Protons, neutrons, electrons: 113, 113, 171
    public static final GTMaterial NIHONIUM =
        GTRegisters.MATERIALS.register(
            "nihonium",
            () -> GTMaterial.builder()
                .name("nihonium")
                .englishNameForDatagen("Nihonium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(700)
                .boilingPoint(1400)
                .densityGramPerCm3(16.0)
                .build()
        );
    // Protons, neutrons, electrons: 114, 114, 175
    public static final GTMaterial FLEROVIUM =
        GTRegisters.MATERIALS.register(
            "flerovium",
            () -> GTMaterial.builder()
                .name("flerovium")
                .englishNameForDatagen("Flerovium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(340)
                .boilingPoint(420)
                .densityGramPerCm3(14.0)
                .build()
        );
    // Protons, neutrons, electrons: 114, 114, 184
    public static final GTMaterial FLEROVIUM_298 =
        GTRegisters.MATERIALS.register(
            "flerovium_298",
            () -> GTMaterial.builder()
                .name("flerovium_298")
                .englishNameForDatagen("Flerovium-298")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(340)
                .boilingPoint(420)
                .densityGramPerCm3(14.0)
                .build()
        );
    // Protons, neutrons, electrons: 115, 115, 174
    public static final GTMaterial MOSCOVIUM =
        GTRegisters.MATERIALS.register(
            "moscovium",
            () -> GTMaterial.builder()
                .name("moscovium")
                .englishNameForDatagen("Moscovium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(700)
                .boilingPoint(1400)
                .densityGramPerCm3(13.5)
                .build()
        );
    // Protons, neutrons, electrons: 116, 116, 177
    public static final GTMaterial LIVERMORIUM =
        GTRegisters.MATERIALS.register(
            "livermorium",
            () -> GTMaterial.builder()
                .name("livermorium")
                .englishNameForDatagen("Livermorium")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(708)
                .boilingPoint(1085)
                .densityGramPerCm3(12.9)
                .build()
        );
    // Protons, neutrons, electrons: 117, 117, 177
    public static final GTMaterial TENNESSINE =
        GTRegisters.MATERIALS.register(
            "tennessine",
            () -> GTMaterial.builder()
                .name("tennessine")
                .englishNameForDatagen("Tennessine")
                .colorData(255, 60, 70, 80)
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(673)
                .boilingPoint(823)
                .densityGramPerCm3(7.2)
                .build()
        );
    // Protons, neutrons, electrons: 118, 118, 176
    public static final GTMaterial OGANESSON =
        GTRegisters.MATERIALS.register(
            "oganesson",
            () -> GTMaterial.builder()
                .name("oganesson")
                .englishNameForDatagen("Oganesson")
                .unknownColorDataSoUseDefault()
                .textureSet(GTMaterialTextureSets.RAD)
                .meltingPoint(258)
                .boilingPoint(263)
                .densityGramPerCm3(5.0)
                .build()
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
