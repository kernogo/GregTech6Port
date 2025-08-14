package ru.kernogo.gregtech6port.registration.registered.materials;

import net.neoforged.neoforge.registries.DeferredHolder;
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
    public static final DeferredHolder<GTMaterial, GTMaterial> HYDROGEN =
        GTRegisters.MATERIALS.register(
            "hydrogen",
            () -> GTMaterial.builder()
                .name("hydrogen")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 0, 0, 255)
                .textureSet("dull")
                .meltingPoint(14)
                .boilingPoint(20)
                .densityGramPerCm3(0.00008988)
                .build()
        );
    // Protons, neutrons, electrons: 1, 1, 1
    public static final DeferredHolder<GTMaterial, GTMaterial> DEUTERIUM =
        GTRegisters.MATERIALS.register(
            "deuterium",
            () -> GTMaterial.builder() // Like Hydrogen-2
                .name("deuterium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 255, 255, 0)
                .textureSet("rad")
                .meltingPoint(14)
                .boilingPoint(20)
                .densityGramPerCm3(0.00008988)
                .build()
        );
    // Protons, neutrons, electrons: 1, 1, 2
    public static final DeferredHolder<GTMaterial, GTMaterial> TRITIUM =
        GTRegisters.MATERIALS.register(
            "tritium",
            () -> GTMaterial.builder() // Like Hydrogen-3
                .name("tritium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 255, 0, 0)
                .textureSet("rad")
                .meltingPoint(14)
                .boilingPoint(20)
                .densityGramPerCm3(0.00008988)
                .build()
        );
    // Protons, neutrons, electrons: 2, 2, 2
    public static final DeferredHolder<GTMaterial, GTMaterial> HELIUM =
        GTRegisters.MATERIALS.register(
            "helium",
            () -> GTMaterial.builder()
                .name("helium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 255, 255, 120)
                .textureSet("shiny")
                .meltingPoint(1)
                .boilingPoint(4)
                .densityGramPerCm3(0.0001785)
                .build()
        );
    // Protons, neutrons, electrons: 2, 2, 1
    public static final DeferredHolder<GTMaterial, GTMaterial> HELIUM_3 =
        GTRegisters.MATERIALS.register(
            "helium_3",
            () -> GTMaterial.builder()
                .name("helium_3")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 255, 255, 140)
                .textureSet("rad")
                .meltingPoint(1)
                .boilingPoint(4)
                .densityGramPerCm3(0.0001785)
                .build()
        );
    // Protons, neutrons, electrons: 3, 3, 4
    public static final DeferredHolder<GTMaterial, GTMaterial> LITHIUM =
        GTRegisters.MATERIALS.register(
            "lithium",
            () -> GTMaterial.builder()
                .name("lithium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 225, 220, 255)
                .textureSet("rough")
                .meltingPoint(453)
                .boilingPoint(1560)
                .densityGramPerCm3(0.534)
                .build()
        );
    // Protons, neutrons, electrons: 3, 3, 3
    public static final DeferredHolder<GTMaterial, GTMaterial> LITHIUM_6 =
        GTRegisters.MATERIALS.register(
            "lithium_6",
            () -> GTMaterial.builder()
                .name("lithium_6")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 230, 225, 255)
                .textureSet("rad")
                .meltingPoint(453)
                .boilingPoint(1560)
                .densityGramPerCm3(0.534)
                .build()
        );
    // Protons, neutrons, electrons: 4, 4, 5
    public static final DeferredHolder<GTMaterial, GTMaterial> BERYLLIUM =
        GTRegisters.MATERIALS.register(
            "beryllium",
            () -> GTMaterial.builder()
                .name("beryllium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 100, 180, 100)
                .textureSet("metallic")
                .meltingPoint(1560)
                .boilingPoint(2742)
                .densityGramPerCm3(1.85)
                .build()
        );
    // Protons, neutrons, electrons: 4, 4, 3
    public static final DeferredHolder<GTMaterial, GTMaterial> BERYLLIUM_7 =
        GTRegisters.MATERIALS.register(
            "beryllium_7",
            () -> GTMaterial.builder()
                .name("beryllium_7")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 110, 190, 110)
                .textureSet("rad")
                .meltingPoint(1560)
                .boilingPoint(2742)
                .densityGramPerCm3(1.85)
                .build()
        );
    // Protons, neutrons, electrons: 4, 4, 4
    public static final DeferredHolder<GTMaterial, GTMaterial> BERYLLIUM_8 =
        GTRegisters.MATERIALS.register(
            "beryllium_8",
            () -> GTMaterial.builder()
                .name("beryllium_8")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 110, 200, 110)
                .textureSet("rad")
                .meltingPoint(1560)
                .boilingPoint(2742)
                .densityGramPerCm3(1.85)
                .build()
        );
    // Protons, neutrons, electrons: 5, 5, 5
    public static final DeferredHolder<GTMaterial, GTMaterial> BORON =
        GTRegisters.MATERIALS.register(
            "boron",
            () -> GTMaterial.builder()
                .name("boron")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 250, 250, 250)
                .textureSet("dull")
                .meltingPoint(2349)
                .boilingPoint(4200)
                .densityGramPerCm3(2.34)
                .build()
        );
    // Protons, neutrons, electrons: 5, 5, 6
    public static final DeferredHolder<GTMaterial, GTMaterial> BORON_11 =
        GTRegisters.MATERIALS.register(
            "boron_11",
            () -> GTMaterial.builder()
                .name("boron_11")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 240, 240, 240)
                .textureSet("rad")
                .meltingPoint(2349)
                .boilingPoint(4200)
                .densityGramPerCm3(2.34)
                .build()
        );
    // Protons, neutrons, electrons: 6, 6, 6
    public static final DeferredHolder<GTMaterial, GTMaterial> CARBON =
        GTRegisters.MATERIALS.register(
            "carbon",
            () -> GTMaterial.builder()
                .name("carbon")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 20, 20, 20)
                .textureSet("fine")
                .meltingPoint(3800)
                .boilingPoint(4300)
                .densityGramPerCm3(2.267)
                .build()
        );
    // Protons, neutrons, electrons: 6, 6, 7
    public static final DeferredHolder<GTMaterial, GTMaterial> CARBON_13 =
        GTRegisters.MATERIALS.register(
            "carbon_13",
            () -> GTMaterial.builder()
                .name("carbon_13")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 25, 25, 25)
                .textureSet("rad")
                .meltingPoint(3800)
                .boilingPoint(4300)
                .densityGramPerCm3(2.267)
                .build()
        );
    // Protons, neutrons, electrons: 6, 6, 8
    public static final DeferredHolder<GTMaterial, GTMaterial> CARBON_14 =
        GTRegisters.MATERIALS.register(
            "carbon_14",
            () -> GTMaterial.builder()
                .name("carbon_14")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 30, 30, 30)
                .textureSet("rad")
                .meltingPoint(3800)
                .boilingPoint(4300)
                .densityGramPerCm3(2.267)
                .build()
        );
    // Protons, neutrons, electrons: 7, 7, 7
    public static final DeferredHolder<GTMaterial, GTMaterial> NITROGEN =
        GTRegisters.MATERIALS.register(
            "nitrogen",
            () -> GTMaterial.builder()
                .name("nitrogen")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 0, 150, 200)
                .textureSet("dull")
                .meltingPoint(63)
                .boilingPoint(77)
                .densityGramPerCm3(0.0012506)
                .build()
        );
    // Protons, neutrons, electrons: 8, 8, 8
    public static final DeferredHolder<GTMaterial, GTMaterial> OXYGEN =
        GTRegisters.MATERIALS.register(
            "oxygen",
            () -> GTMaterial.builder()
                .name("oxygen")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 0, 100, 200)
                .textureSet("dull")
                .meltingPoint(54)
                .boilingPoint(90)
                .densityGramPerCm3(0.001429)
                .build()
        );
    // Protons, neutrons, electrons: 9, 9, 9
    public static final DeferredHolder<GTMaterial, GTMaterial> FLUORINE =
        GTRegisters.MATERIALS.register(
            "fluorine",
            () -> GTMaterial.builder()
                .name("fluorine")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 64, 192, 0)
                .textureSet("dull")
                .meltingPoint(53)
                .boilingPoint(85)
                .densityGramPerCm3(0.001696)
                .build()
        );
    // Protons, neutrons, electrons: 10, 10, 10
    public static final DeferredHolder<GTMaterial, GTMaterial> NEON =
        GTRegisters.MATERIALS.register(
            "neon",
            () -> GTMaterial.builder()
                .name("neon")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 250, 180, 180)
                .textureSet("shiny")
                .meltingPoint(24)
                .boilingPoint(27)
                .densityGramPerCm3(0.0008999)
                .build()
        );
    // Protons, neutrons, electrons: 11, 11, 11
    public static final DeferredHolder<GTMaterial, GTMaterial> SODIUM =
        GTRegisters.MATERIALS.register(
            "sodium",
            () -> GTMaterial.builder()
                .name("sodium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 0, 0, 150)
                .textureSet("rough")
                .meltingPoint(370)
                .boilingPoint(1156)
                .densityGramPerCm3(0.971)
                .build()
        );
    // Protons, neutrons, electrons: 12, 12, 12
    public static final DeferredHolder<GTMaterial, GTMaterial> MAGNESIUM =
        GTRegisters.MATERIALS.register(
            "magnesium",
            () -> GTMaterial.builder()
                .name("magnesium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 200, 200)
                .textureSet("copper")
                .meltingPoint(923)
                .boilingPoint(1363)
                .densityGramPerCm3(1.738)
                .build()
        );
    // Protons, neutrons, electrons: 13, 13, 13
    public static final DeferredHolder<GTMaterial, GTMaterial> ALUMINIUM =
        GTRegisters.MATERIALS.register(
            "aluminium",
            () -> GTMaterial.builder()
                .name("aluminium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 128, 200, 240)
                .textureSet("copper")
                .meltingPoint(933)
                .boilingPoint(2792)
                .densityGramPerCm3(2.698)
                .build()
        );
    // Protons, neutrons, electrons: 14, 14, 14
    public static final DeferredHolder<GTMaterial, GTMaterial> SILICON =
        GTRegisters.MATERIALS.register(
            "silicon",
            () -> GTMaterial.builder()
                .name("silicon")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 60, 60, 80)
                .textureSet("copper")
                .meltingPoint(1687)
                .boilingPoint(3538)
                .densityGramPerCm3(2.3296)
                .build()
        );
    // Protons, neutrons, electrons: 15, 15, 15
    public static final DeferredHolder<GTMaterial, GTMaterial> PHOSPHOR =
        GTRegisters.MATERIALS.register(
            "phosphor",
            () -> GTMaterial.builder()
                .name("phosphor")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 255, 0)
                .textureSet("fine")
                .meltingPoint(317)
                .boilingPoint(550)
                .densityGramPerCm3(1.82)
                .build()
        );
    // Protons, neutrons, electrons: 16, 16, 16
    public static final DeferredHolder<GTMaterial, GTMaterial> SULFUR =
        GTRegisters.MATERIALS.register(
            "sulfur",
            () -> GTMaterial.builder()
                .name("sulfur")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 234, 234, 0)
                .textureSet("fine")
                .meltingPoint(388)
                .boilingPoint(717)
                .densityGramPerCm3(2.067)
                .build()
        );
    // Protons, neutrons, electrons: 17, 17, 18
    public static final DeferredHolder<GTMaterial, GTMaterial> CHLORINE =
        GTRegisters.MATERIALS.register(
            "chlorine",
            () -> GTMaterial.builder()
                .name("chlorine")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 0, 240, 255)
                .textureSet("dull")
                .meltingPoint(171)
                .boilingPoint(239)
                .densityGramPerCm3(0.003214)
                .build()
        );
    // Protons, neutrons, electrons: 18, 18, 22
    public static final DeferredHolder<GTMaterial, GTMaterial> ARGON =
        GTRegisters.MATERIALS.register(
            "argon",
            () -> GTMaterial.builder()
                .name("argon")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 0, 255, 0)
                .textureSet("shiny")
                .meltingPoint(83)
                .boilingPoint(87)
                .densityGramPerCm3(0.0017837)
                .build()
        );
    // Protons, neutrons, electrons: 19, 19, 20
    public static final DeferredHolder<GTMaterial, GTMaterial> POTASSIUM =
        GTRegisters.MATERIALS.register(
            "potassium",
            () -> GTMaterial.builder()
                .name("potassium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 250, 250, 250)
                .textureSet("rough")
                .meltingPoint(336)
                .boilingPoint(1032)
                .densityGramPerCm3(0.862)
                .build()
        );
    // Protons, neutrons, electrons: 20, 20, 20
    public static final DeferredHolder<GTMaterial, GTMaterial> CALCIUM =
        GTRegisters.MATERIALS.register(
            "calcium",
            () -> GTMaterial.builder()
                .name("calcium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 245, 245)
                .textureSet("metallic")
                .meltingPoint(1115)
                .boilingPoint(1757)
                .densityGramPerCm3(1.54)
                .build()
        );
    // Protons, neutrons, electrons: 21, 21, 24
    public static final DeferredHolder<GTMaterial, GTMaterial> SCANDIUM =
        GTRegisters.MATERIALS.register(
            "scandium",
            () -> GTMaterial.builder()
                .name("scandium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1814)
                .boilingPoint(3109)
                .densityGramPerCm3(2.989)
                .build()
        );
    // Protons, neutrons, electrons: 22, 22, 26
    public static final DeferredHolder<GTMaterial, GTMaterial> TITANIUM =
        GTRegisters.MATERIALS.register(
            "titanium",
            () -> GTMaterial.builder()
                .name("titanium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 220, 160, 240)
                .textureSet("metallic")
                .meltingPoint(1941)
                .boilingPoint(3560)
                .densityGramPerCm3(4.54)
                .build()
        );
    // Protons, neutrons, electrons: 23, 23, 28
    public static final DeferredHolder<GTMaterial, GTMaterial> VANADIUM =
        GTRegisters.MATERIALS.register(
            "vanadium",
            () -> GTMaterial.builder()
                .name("vanadium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 50, 50, 50)
                .textureSet("metallic")
                .meltingPoint(2183)
                .boilingPoint(3680)
                .densityGramPerCm3(6.11)
                .build()
        );
    // Protons, neutrons, electrons: 24, 24, 28
    public static final DeferredHolder<GTMaterial, GTMaterial> CHROMIUM =
        GTRegisters.MATERIALS.register(
            "chromium",
            () -> GTMaterial.builder()
                .name("chromium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 230, 230)
                .textureSet("shiny")
                .meltingPoint(2180)
                .boilingPoint(2944)
                .densityGramPerCm3(7.15)
                .build()
        );
    // Protons, neutrons, electrons: 25, 25, 30
    public static final DeferredHolder<GTMaterial, GTMaterial> MANGANESE =
        GTRegisters.MATERIALS.register(
            "manganese",
            () -> GTMaterial.builder()
                .name("manganese")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 250, 250, 250)
                .textureSet("dull")
                .meltingPoint(1519)
                .boilingPoint(2334)
                .densityGramPerCm3(7.44)
                .build()
        );
    // Protons, neutrons, electrons: 26, 26, 30
    public static final DeferredHolder<GTMaterial, GTMaterial> IRON =
        GTRegisters.MATERIALS.register(
            "iron",
            () -> GTMaterial.builder()
                .name("iron")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 200, 200, 200)
                .textureSet("metallic")
                .meltingPoint(1811)
                .boilingPoint(3134)
                .densityGramPerCm3(7.874)
                .build()
        );
    // Protons, neutrons, electrons: 27, 27, 32
    public static final DeferredHolder<GTMaterial, GTMaterial> COBALT =
        GTRegisters.MATERIALS.register(
            "cobalt",
            () -> GTMaterial.builder()
                .name("cobalt")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 80, 80, 250)
                .textureSet("metallic")
                .meltingPoint(1768)
                .boilingPoint(3200)
                .densityGramPerCm3(8.86)
                .build()
        );
    // Protons, neutrons, electrons: 27, 27, 33
    public static final DeferredHolder<GTMaterial, GTMaterial> COBALT_60 =
        GTRegisters.MATERIALS.register(
            "cobalt_60",
            () -> GTMaterial.builder()
                .name("cobalt_60")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 90, 90, 250)
                .textureSet("rad")
                .meltingPoint(1768)
                .boilingPoint(3200)
                .densityGramPerCm3(8.86)
                .build()
        );
    // Protons, neutrons, electrons: 28, 28, 30
    public static final DeferredHolder<GTMaterial, GTMaterial> NICKEL =
        GTRegisters.MATERIALS.register(
            "nickel",
            () -> GTMaterial.builder()
                .name("nickel")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 250, 250, 200)
                .textureSet("metallic")
                .meltingPoint(1728)
                .boilingPoint(3186)
                .densityGramPerCm3(8.912)
                .build()
        );
    // Protons, neutrons, electrons: 29, 29, 34
    public static final DeferredHolder<GTMaterial, GTMaterial> COPPER =
        GTRegisters.MATERIALS.register(
            "copper",
            () -> GTMaterial.builder()
                .name("copper")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 130, 90)
                .textureSet("copper")
                .meltingPoint(1357)
                .boilingPoint(2835)
                .densityGramPerCm3(8.96)
                .build()
        );
    // Protons, neutrons, electrons: 30, 30, 35
    public static final DeferredHolder<GTMaterial, GTMaterial> ZINC =
        GTRegisters.MATERIALS.register(
            "zinc",
            () -> GTMaterial.builder()
                .name("zinc")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 250, 240, 240)
                .textureSet("copper")
                .meltingPoint(692)
                .boilingPoint(1180)
                .densityGramPerCm3(7.134)
                .build()
        );
    // Protons, neutrons, electrons: 31, 31, 39
    public static final DeferredHolder<GTMaterial, GTMaterial> GALLIUM =
        GTRegisters.MATERIALS.register(
            "gallium",
            () -> GTMaterial.builder()
                .name("gallium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 220, 220, 255)
                .textureSet("copper")
                .meltingPoint(302)
                .boilingPoint(2477)
                .densityGramPerCm3(5.907)
                .build()
        );
    // Protons, neutrons, electrons: 32, 32, 40
    public static final DeferredHolder<GTMaterial, GTMaterial> GERMANIUM =
        GTRegisters.MATERIALS.register(
            "germanium",
            () -> GTMaterial.builder()
                .name("germanium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 212, 212, 212)
                .textureSet("copper")
                .meltingPoint(1211)
                .boilingPoint(3106)
                .densityGramPerCm3(5.323)
                .build()
        );
    // Protons, neutrons, electrons: 33, 33, 42
    public static final DeferredHolder<GTMaterial, GTMaterial> ARSENIC =
        GTRegisters.MATERIALS.register(
            "arsenic",
            () -> GTMaterial.builder()
                .name("arsenic")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 103, 103, 86)
                .textureSet("shiny")
                .meltingPoint(887)
                .boilingPoint(1090)
                .densityGramPerCm3(5.776)
                .build()
        );
    // Protons, neutrons, electrons: 34, 34, 45
    public static final DeferredHolder<GTMaterial, GTMaterial> SELENIUM =
        GTRegisters.MATERIALS.register(
            "selenium",
            () -> GTMaterial.builder()
                .name("selenium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 111, 20, 20)
                .textureSet("dull")
                .meltingPoint(453)
                .boilingPoint(958)
                .densityGramPerCm3(4.809)
                .build()
        );
    // Protons, neutrons, electrons: 35, 35, 45
    public static final DeferredHolder<GTMaterial, GTMaterial> BROMINE =
        GTRegisters.MATERIALS.register(
            "bromine",
            () -> GTMaterial.builder()
                .name("bromine")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 80, 10, 10)
                .textureSet("fluid")
                .meltingPoint(265)
                .boilingPoint(332)
                .densityGramPerCm3(3.122)
                .build()
        );
    // Protons, neutrons, electrons: 36, 36, 48
    public static final DeferredHolder<GTMaterial, GTMaterial> KRYPTON =
        GTRegisters.MATERIALS.register(
            "krypton",
            () -> GTMaterial.builder()
                .name("krypton")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 128, 255, 128)
                .textureSet("diamond")
                .meltingPoint(115)
                .boilingPoint(119)
                .densityGramPerCm3(0.003733)
                .build()
        );
    // Protons, neutrons, electrons: 37, 37, 48
    public static final DeferredHolder<GTMaterial, GTMaterial> RUBIDIUM =
        GTRegisters.MATERIALS.register(
            "rubidium",
            () -> GTMaterial.builder()
                .name("rubidium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 240, 30, 30)
                .textureSet("shiny")
                .meltingPoint(312)
                .boilingPoint(961)
                .densityGramPerCm3(1.532)
                .build()
        );
    // Protons, neutrons, electrons: 38, 38, 49
    public static final DeferredHolder<GTMaterial, GTMaterial> STRONTIUM =
        GTRegisters.MATERIALS.register(
            "strontium",
            () -> GTMaterial.builder()
                .name("strontium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 200, 200, 200)
                .textureSet("metallic")
                .meltingPoint(1050)
                .boilingPoint(1655)
                .densityGramPerCm3(2.64)
                .build()
        );
    // Protons, neutrons, electrons: 39, 39, 50
    public static final DeferredHolder<GTMaterial, GTMaterial> YTTRIUM =
        GTRegisters.MATERIALS.register(
            "yttrium",
            () -> GTMaterial.builder()
                .name("yttrium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 220, 250, 220)
                .textureSet("metallic")
                .meltingPoint(1799)
                .boilingPoint(3609)
                .densityGramPerCm3(4.469)
                .build()
        );
    // Protons, neutrons, electrons: 40, 40, 51
    public static final DeferredHolder<GTMaterial, GTMaterial> ZIRCONIUM =
        GTRegisters.MATERIALS.register(
            "zirconium",
            () -> GTMaterial.builder()
                .name("zirconium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(127, 200, 255, 255)
                .textureSet("diamond")
                .meltingPoint(2128)
                .boilingPoint(4682)
                .densityGramPerCm3(6.506)
                .build()
        );
    // Protons, neutrons, electrons: 41, 41, 53
    public static final DeferredHolder<GTMaterial, GTMaterial> NIOBIUM =
        GTRegisters.MATERIALS.register(
            "niobium",
            () -> GTMaterial.builder()
                .name("niobium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 190, 180, 200)
                .textureSet("metallic")
                .meltingPoint(2750)
                .boilingPoint(5017)
                .densityGramPerCm3(8.57)
                .build()
        );
    // Protons, neutrons, electrons: 42, 42, 53
    public static final DeferredHolder<GTMaterial, GTMaterial> MOLYBDENUM =
        GTRegisters.MATERIALS.register(
            "molybdenum",
            () -> GTMaterial.builder()
                .name("molybdenum")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 180, 180, 220)
                .textureSet("copper")
                .meltingPoint(2896)
                .boilingPoint(4912)
                .densityGramPerCm3(10.22)
                .build()
        );
    // Protons, neutrons, electrons: 43, 43, 55
    public static final DeferredHolder<GTMaterial, GTMaterial> TECHNETIUM =
        GTRegisters.MATERIALS.register(
            "technetium",
            () -> GTMaterial.builder()
                .name("technetium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 66, 66, 99)
                .textureSet("rad")
                .meltingPoint(2430)
                .boilingPoint(4538)
                .densityGramPerCm3(11.5)
                .build()
        );
    // Protons, neutrons, electrons: 44, 44, 57
    public static final DeferredHolder<GTMaterial, GTMaterial> RUTHENIUM =
        GTRegisters.MATERIALS.register(
            "ruthenium",
            () -> GTMaterial.builder()
                .name("ruthenium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 155, 155, 155)
                .textureSet("shiny")
                .meltingPoint(2607)
                .boilingPoint(4423)
                .densityGramPerCm3(12.37)
                .build()
        );
    // Protons, neutrons, electrons: 45, 45, 58
    public static final DeferredHolder<GTMaterial, GTMaterial> RHODIUM =
        GTRegisters.MATERIALS.register(
            "rhodium",
            () -> GTMaterial.builder()
                .name("rhodium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 144, 144, 144)
                .textureSet("shiny")
                .meltingPoint(2237)
                .boilingPoint(3968)
                .densityGramPerCm3(12.41)
                .build()
        );
    // Protons, neutrons, electrons: 46, 46, 60
    public static final DeferredHolder<GTMaterial, GTMaterial> PALLADIUM =
        GTRegisters.MATERIALS.register(
            "palladium",
            () -> GTMaterial.builder()
                .name("palladium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 128, 128, 128)
                .textureSet("shiny")
                .meltingPoint(1828)
                .boilingPoint(3236)
                .densityGramPerCm3(12.02)
                .build()
        );
    // Protons, neutrons, electrons: 47, 47, 60
    public static final DeferredHolder<GTMaterial, GTMaterial> SILVER =
        GTRegisters.MATERIALS.register(
            "silver",
            () -> GTMaterial.builder()
                .name("silver")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 220, 220, 255)
                .textureSet("shiny")
                .meltingPoint(1234)
                .boilingPoint(2435)
                .densityGramPerCm3(10.501)
                .build()
        );
    // Protons, neutrons, electrons: 48, 48, 64
    public static final DeferredHolder<GTMaterial, GTMaterial> CADMIUM =
        GTRegisters.MATERIALS.register(
            "cadmium",
            () -> GTMaterial.builder()
                .name("cadmium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 50, 50, 60)
                .textureSet("shiny")
                .meltingPoint(594)
                .boilingPoint(1040)
                .densityGramPerCm3(8.69)
                .build()
        );
    // Protons, neutrons, electrons: 49, 49, 65
    public static final DeferredHolder<GTMaterial, GTMaterial> INDIUM =
        GTRegisters.MATERIALS.register(
            "indium",
            () -> GTMaterial.builder()
                .name("indium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 64, 0, 128)
                .textureSet("shiny")
                .meltingPoint(429)
                .boilingPoint(2345)
                .densityGramPerCm3(7.31)
                .build()
        );
    // Protons, neutrons, electrons: 50, 50, 68
    public static final DeferredHolder<GTMaterial, GTMaterial> TIN =
        GTRegisters.MATERIALS.register(
            "tin",
            () -> GTMaterial.builder()
                .name("tin")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 220, 220, 220)
                .textureSet("copper")
                .meltingPoint(505)
                .boilingPoint(2875)
                .densityGramPerCm3(7.287)
                .build()
        );
    // Protons, neutrons, electrons: 51, 51, 70
    public static final DeferredHolder<GTMaterial, GTMaterial> ANTIMONY =
        GTRegisters.MATERIALS.register(
            "antimony",
            () -> GTMaterial.builder()
                .name("antimony")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 220, 220, 240)
                .textureSet("copper")
                .meltingPoint(903)
                .boilingPoint(1860)
                .densityGramPerCm3(6.685)
                .build()
        );
    // Protons, neutrons, electrons: 52, 52, 75
    public static final DeferredHolder<GTMaterial, GTMaterial> TELLURIUM =
        GTRegisters.MATERIALS.register(
            "tellurium",
            () -> GTMaterial.builder()
                .name("tellurium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("shiny")
                .meltingPoint(722)
                .boilingPoint(1261)
                .densityGramPerCm3(6.232)
                .build()
        );
    // Protons, neutrons, electrons: 53, 53, 74
    public static final DeferredHolder<GTMaterial, GTMaterial> IODINE =
        GTRegisters.MATERIALS.register(
            "iodine",
            () -> GTMaterial.builder()
                .name("iodine")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 240, 240)
                .textureSet("dull")
                .meltingPoint(386)
                .boilingPoint(457)
                .densityGramPerCm3(4.93)
                .build()
        );
    // Protons, neutrons, electrons: 54, 54, 77
    public static final DeferredHolder<GTMaterial, GTMaterial> XENON =
        GTRegisters.MATERIALS.register(
            "xenon",
            () -> GTMaterial.builder()
                .name("xenon")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 0, 255, 255)
                .textureSet("dull")
                .meltingPoint(161)
                .boilingPoint(165)
                .densityGramPerCm3(0.005887)
                .build()
        );
    // Protons, neutrons, electrons: 55, 55, 77
    public static final DeferredHolder<GTMaterial, GTMaterial> CAESIUM =
        GTRegisters.MATERIALS.register(
            "caesium",
            () -> GTMaterial.builder()
                .name("caesium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 128, 98, 11)
                .textureSet("shiny")
                .meltingPoint(301)
                .boilingPoint(944)
                .densityGramPerCm3(1.873)
                .build()
        );
    // Protons, neutrons, electrons: 56, 56, 81
    public static final DeferredHolder<GTMaterial, GTMaterial> BARIUM =
        GTRegisters.MATERIALS.register(
            "barium",
            () -> GTMaterial.builder()
                .name("barium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 131, 130, 76)
                .textureSet("metallic")
                .meltingPoint(1000)
                .boilingPoint(2170)
                .densityGramPerCm3(3.594)
                .build()
        );
    // Protons, neutrons, electrons: 57, 57, 81
    public static final DeferredHolder<GTMaterial, GTMaterial> LANTHANUM =
        GTRegisters.MATERIALS.register(
            "lanthanum",
            () -> GTMaterial.builder()
                .name("lanthanum")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 93, 117, 117)
                .textureSet("metallic")
                .meltingPoint(1193)
                .boilingPoint(3737)
                .densityGramPerCm3(6.145)
                .build()
        );
    // Protons, neutrons, electrons: 58, 58, 82
    public static final DeferredHolder<GTMaterial, GTMaterial> CERIUM =
        GTRegisters.MATERIALS.register(
            "cerium",
            () -> GTMaterial.builder()
                .name("cerium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 255, 190)
                .textureSet("shiny")
                .meltingPoint(1068)
                .boilingPoint(3716)
                .densityGramPerCm3(6.77)
                .build()
        );
    // Protons, neutrons, electrons: 59, 59, 81
    public static final DeferredHolder<GTMaterial, GTMaterial> PRASEODYMIUM =
        GTRegisters.MATERIALS.register(
            "praseodymium",
            () -> GTMaterial.builder()
                .name("praseodymium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1208)
                .boilingPoint(3793)
                .densityGramPerCm3(6.773)
                .build()
        );
    // Protons, neutrons, electrons: 60, 60, 84
    public static final DeferredHolder<GTMaterial, GTMaterial> NEODYMIUM =
        GTRegisters.MATERIALS.register(
            "neodymium",
            () -> GTMaterial.builder()
                .name("neodymium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 100, 100, 100)
                .textureSet("shiny")
                .meltingPoint(1297)
                .boilingPoint(3347)
                .densityGramPerCm3(7.007)
                .build()
        );
    // Protons, neutrons, electrons: 61, 61, 83
    public static final DeferredHolder<GTMaterial, GTMaterial> PROMETHIUM =
        GTRegisters.MATERIALS.register(
            "promethium",
            () -> GTMaterial.builder()
                .name("promethium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1315)
                .boilingPoint(3273)
                .densityGramPerCm3(7.26)
                .build()
        );
    // Protons, neutrons, electrons: 62, 62, 88
    public static final DeferredHolder<GTMaterial, GTMaterial> SAMARIUM =
        GTRegisters.MATERIALS.register(
            "samarium",
            () -> GTMaterial.builder()
                .name("samarium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1345)
                .boilingPoint(2067)
                .densityGramPerCm3(7.52)
                .build()
        );
    // Protons, neutrons, electrons: 63, 63, 88
    public static final DeferredHolder<GTMaterial, GTMaterial> EUROPIUM =
        GTRegisters.MATERIALS.register(
            "europium",
            () -> GTMaterial.builder()
                .name("europium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1099)
                .boilingPoint(1802)
                .densityGramPerCm3(5.243)
                .build()
        );
    // Protons, neutrons, electrons: 64, 64, 93
    public static final DeferredHolder<GTMaterial, GTMaterial> GADOLINIUM =
        GTRegisters.MATERIALS.register(
            "gadolinium",
            () -> GTMaterial.builder()
                .name("gadolinium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1585)
                .boilingPoint(3546)
                .densityGramPerCm3(7.895)
                .build()
        );
    // Protons, neutrons, electrons: 65, 65, 93
    public static final DeferredHolder<GTMaterial, GTMaterial> TERBIUM =
        GTRegisters.MATERIALS.register(
            "terbium",
            () -> GTMaterial.builder()
                .name("terbium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1629)
                .boilingPoint(3503)
                .densityGramPerCm3(8.229)
                .build()
        );
    // Protons, neutrons, electrons: 66, 66, 96
    public static final DeferredHolder<GTMaterial, GTMaterial> DYSPROSIUM =
        GTRegisters.MATERIALS.register(
            "dysprosium",
            () -> GTMaterial.builder()
                .name("dysprosium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1680)
                .boilingPoint(2840)
                .densityGramPerCm3(8.55)
                .build()
        );
    // Protons, neutrons, electrons: 67, 67, 97
    public static final DeferredHolder<GTMaterial, GTMaterial> HOLMIUM =
        GTRegisters.MATERIALS.register(
            "holmium",
            () -> GTMaterial.builder()
                .name("holmium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1734)
                .boilingPoint(2993)
                .densityGramPerCm3(8.795)
                .build()
        );
    // Protons, neutrons, electrons: 68, 68, 99
    public static final DeferredHolder<GTMaterial, GTMaterial> ERBIUM =
        GTRegisters.MATERIALS.register(
            "erbium",
            () -> GTMaterial.builder()
                .name("erbium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1802)
                .boilingPoint(3141)
                .densityGramPerCm3(9.066)
                .build()
        );
    // Protons, neutrons, electrons: 69, 69, 99
    public static final DeferredHolder<GTMaterial, GTMaterial> THULIUM =
        GTRegisters.MATERIALS.register(
            "thulium",
            () -> GTMaterial.builder()
                .name("thulium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1818)
                .boilingPoint(2223)
                .densityGramPerCm3(9.321)
                .build()
        );
    // Protons, neutrons, electrons: 70, 70, 103
    public static final DeferredHolder<GTMaterial, GTMaterial> YTTERBIUM =
        GTRegisters.MATERIALS.register(
            "ytterbium",
            () -> GTMaterial.builder()
                .name("ytterbium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 167, 167, 167)
                .textureSet("metallic")
                .meltingPoint(1097)
                .boilingPoint(1469)
                .densityGramPerCm3(6.965)
                .build()
        );
    // Protons, neutrons, electrons: 71, 71, 103
    public static final DeferredHolder<GTMaterial, GTMaterial> LUTETIUM =
        GTRegisters.MATERIALS.register(
            "lutetium",
            () -> GTMaterial.builder()
                .name("lutetium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(1925)
                .boilingPoint(3675)
                .densityGramPerCm3(9.84)
                .build()
        );
    // Protons, neutrons, electrons: 72, 72, 106
    public static final DeferredHolder<GTMaterial, GTMaterial> HAFNIUM =
        GTRegisters.MATERIALS.register(
            "hafnium",
            () -> GTMaterial.builder()
                .name("hafnium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 140, 140, 150)
                .textureSet("copper")
                .meltingPoint(2506)
                .boilingPoint(4876)
                .densityGramPerCm3(13.31)
                .build()
        );
    // Protons, neutrons, electrons: 73, 73, 107
    public static final DeferredHolder<GTMaterial, GTMaterial> TANTALUM =
        GTRegisters.MATERIALS.register(
            "tantalum",
            () -> GTMaterial.builder()
                .name("tantalum")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 120, 120, 140)
                .textureSet("copper")
                .meltingPoint(3290)
                .boilingPoint(5731)
                .densityGramPerCm3(16.654)
                .build()
        );
    // Protons, neutrons, electrons: 74, 74, 109
    public static final DeferredHolder<GTMaterial, GTMaterial> TUNGSTEN =
        GTRegisters.MATERIALS.register(
            "tungsten",
            () -> GTMaterial.builder()
                .name("tungsten")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 50, 50, 50)
                .textureSet("metallic")
                .meltingPoint(3695)
                .boilingPoint(5828)
                .densityGramPerCm3(19.25)
                .build()
        );
    // Protons, neutrons, electrons: 75, 75, 111
    public static final DeferredHolder<GTMaterial, GTMaterial> RHENIUM =
        GTRegisters.MATERIALS.register(
            "rhenium",
            () -> GTMaterial.builder()
                .name("rhenium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 255, 200)
                .textureSet("shiny")
                .meltingPoint(3459)
                .boilingPoint(5869)
                .densityGramPerCm3(21.02)
                .build()
        );
    // Protons, neutrons, electrons: 76, 76, 114
    public static final DeferredHolder<GTMaterial, GTMaterial> OSMIUM =
        GTRegisters.MATERIALS.register(
            "osmium",
            () -> GTMaterial.builder()
                .name("osmium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 50, 50, 255)
                .textureSet("metallic")
                .meltingPoint(3306)
                .boilingPoint(5285)
                .densityGramPerCm3(22.61)
                .build()
        );
    // Protons, neutrons, electrons: 77, 77, 115
    public static final DeferredHolder<GTMaterial, GTMaterial> IRIDIUM =
        GTRegisters.MATERIALS.register(
            "iridium",
            () -> GTMaterial.builder()
                .name("iridium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 240, 240, 245)
                .textureSet("dull")
                .meltingPoint(2719)
                .boilingPoint(4701)
                .densityGramPerCm3(22.56)
                .build()
        );
    // Protons, neutrons, electrons: 78, 78, 117
    public static final DeferredHolder<GTMaterial, GTMaterial> PLATINUM =
        GTRegisters.MATERIALS.register(
            "platinum",
            () -> GTMaterial.builder()
                .name("platinum")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 100, 180, 250)
                .textureSet("shiny")
                .meltingPoint(2041)
                .boilingPoint(4098)
                .densityGramPerCm3(21.46)
                .build()
        );
    // Protons, neutrons, electrons: 79, 79, 117
    public static final DeferredHolder<GTMaterial, GTMaterial> GOLD =
        GTRegisters.MATERIALS.register(
            "gold",
            () -> GTMaterial.builder()
                .name("gold")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 230, 80)
                .textureSet("shiny")
                .meltingPoint(1337)
                .boilingPoint(3129)
                .densityGramPerCm3(19.282)
                .build()
        );
    // Protons, neutrons, electrons: 79, 79, 119
    public static final DeferredHolder<GTMaterial, GTMaterial> GOLD_198 =
        GTRegisters.MATERIALS.register(
            "gold_198",
            () -> GTMaterial.builder()
                .name("gold_198")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 230, 80)
                .textureSet("shiny")
                .meltingPoint(1337)
                .boilingPoint(3129)
                .densityGramPerCm3(19.282)
                .build()
        );
    // Protons, neutrons, electrons: 80, 80, 120
    public static final DeferredHolder<GTMaterial, GTMaterial> MERCURY =
        GTRegisters.MATERIALS.register(
            "mercury",
            () -> GTMaterial.builder()
                .name("mercury")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 230, 220, 220)
                .textureSet("copper")
                .meltingPoint(234)
                .boilingPoint(629)
                .densityGramPerCm3(13.5336)
                .build()
        );
    // Protons, neutrons, electrons: 81, 81, 123
    public static final DeferredHolder<GTMaterial, GTMaterial> THALLIUM =
        GTRegisters.MATERIALS.register(
            "thallium",
            () -> GTMaterial.builder()
                .name("thallium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("metallic")
                .meltingPoint(577)
                .boilingPoint(1746)
                .densityGramPerCm3(11.85)
                .build()
        );
    // Protons, neutrons, electrons: 82, 82, 125
    public static final DeferredHolder<GTMaterial, GTMaterial> LEAD =
        GTRegisters.MATERIALS.register(
            "lead",
            () -> GTMaterial.builder()
                .name("lead")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 60, 40, 110)
                .textureSet("dull")
                .meltingPoint(600)
                .boilingPoint(2022)
                .densityGramPerCm3(11.342)
                .build()
        );
    // Protons, neutrons, electrons: 83, 83, 125
    public static final DeferredHolder<GTMaterial, GTMaterial> BISMUTH =
        GTRegisters.MATERIALS.register(
            "bismuth",
            () -> GTMaterial.builder()
                .name("bismuth")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 100, 160, 160)
                .textureSet("copper")
                .meltingPoint(544)
                .boilingPoint(1837)
                .densityGramPerCm3(9.807)
                .build()
        );
    // Protons, neutrons, electrons: 84, 84, 124
    public static final DeferredHolder<GTMaterial, GTMaterial> POLONIUM =
        GTRegisters.MATERIALS.register(
            "polonium",
            () -> GTMaterial.builder()
                .name("polonium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(527)
                .boilingPoint(1235)
                .densityGramPerCm3(9.32)
                .build()
        );
    // Protons, neutrons, electrons: 85, 85, 124
    public static final DeferredHolder<GTMaterial, GTMaterial> ASTATINE =
        GTRegisters.MATERIALS.register(
            "astatine",
            () -> GTMaterial.builder()
                .name("astatine")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 33, 33, 33)
                .textureSet("rad")
                .meltingPoint(575)
                .boilingPoint(610)
                .densityGramPerCm3(7.0)
                .build()
        );
    // Protons, neutrons, electrons: 86, 86, 134
    public static final DeferredHolder<GTMaterial, GTMaterial> RADON =
        GTRegisters.MATERIALS.register(
            "radon",
            () -> GTMaterial.builder()
                .name("radon")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(15, 255, 0, 255)
                .textureSet("dull")
                .meltingPoint(202)
                .boilingPoint(211)
                .densityGramPerCm3(0.00973)
                .build()
        );
    // Protons, neutrons, electrons: 87, 87, 134
    public static final DeferredHolder<GTMaterial, GTMaterial> FRANCIUM =
        GTRegisters.MATERIALS.register(
            "francium",
            () -> GTMaterial.builder()
                .name("francium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(300)
                .boilingPoint(950)
                .densityGramPerCm3(1.87)
                .build()
        );
    // Protons, neutrons, electrons: 88, 88, 138
    public static final DeferredHolder<GTMaterial, GTMaterial> RADIUM =
        GTRegisters.MATERIALS.register(
            "radium",
            () -> GTMaterial.builder()
                .name("radium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 255, 255, 205)
                .textureSet("rad")
                .meltingPoint(973)
                .boilingPoint(2010)
                .densityGramPerCm3(5.5)
                .build()
        );
    // Protons, neutrons, electrons: 89, 89, 136
    public static final DeferredHolder<GTMaterial, GTMaterial> ACTINIUM =
        GTRegisters.MATERIALS.register(
            "actinium",
            () -> GTMaterial.builder()
                .name("actinium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 125, 113, 113)
                .textureSet("rad")
                .meltingPoint(1323)
                .boilingPoint(3471)
                .densityGramPerCm3(10.07)
                .build()
        );
    // Protons, neutrons, electrons: 90, 90, 142
    public static final DeferredHolder<GTMaterial, GTMaterial> THORIUM =
        GTRegisters.MATERIALS.register(
            "thorium",
            () -> GTMaterial.builder()
                .name("thorium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 0, 30, 0)
                .textureSet("rad")
                .meltingPoint(2115)
                .boilingPoint(5061)
                .densityGramPerCm3(11.72)
                .build()
        );
    // Protons, neutrons, electrons: 91, 91, 138
    public static final DeferredHolder<GTMaterial, GTMaterial> PROTACTINIUM =
        GTRegisters.MATERIALS.register(
            "protactinium",
            () -> GTMaterial.builder()
                .name("protactinium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1841)
                .boilingPoint(4300)
                .densityGramPerCm3(15.37)
                .build()
        );
    // Protons, neutrons, electrons: 92, 92, 146
    public static final DeferredHolder<GTMaterial, GTMaterial> URANIUM =
        GTRegisters.MATERIALS.register(
            "uranium",
            () -> GTMaterial.builder()
                .name("uranium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 50, 240, 50)
                .textureSet("rad")
                .meltingPoint(1405)
                .boilingPoint(4404)
                .densityGramPerCm3(18.95)
                .build()
        );
    // Protons, neutrons, electrons: 92, 92, 143
    public static final DeferredHolder<GTMaterial, GTMaterial> URANIUM_235 =
        GTRegisters.MATERIALS.register(
            "uranium_235",
            () -> GTMaterial.builder()
                .name("uranium_235")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 70, 250, 70)
                .textureSet("rad")
                .meltingPoint(1405)
                .boilingPoint(4404)
                .densityGramPerCm3(18.95)
                .build()
        );
    // Protons, neutrons, electrons: 92, 92, 141
    public static final DeferredHolder<GTMaterial, GTMaterial> URANIUM_233 =
        GTRegisters.MATERIALS.register(
            "uranium_233",
            () -> GTMaterial.builder()
                .name("uranium_233")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 70, 250, 50)
                .textureSet("rad")
                .meltingPoint(1405)
                .boilingPoint(4404)
                .densityGramPerCm3(18.95)
                .build()
        );
    // Protons, neutrons, electrons: 93, 93, 144
    public static final DeferredHolder<GTMaterial, GTMaterial> NEPTUNIUM =
        GTRegisters.MATERIALS.register(
            "neptunium",
            () -> GTMaterial.builder()
                .name("neptunium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 78, 90, 78)
                .textureSet("rad")
                .meltingPoint(917)
                .boilingPoint(4273)
                .densityGramPerCm3(20.45)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 150
    public static final DeferredHolder<GTMaterial, GTMaterial> PLUTONIUM =
        GTRegisters.MATERIALS.register(
            "plutonium",
            () -> GTMaterial.builder()
                .name("plutonium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 240, 50, 50)
                .textureSet("rad")
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 146
    public static final DeferredHolder<GTMaterial, GTMaterial> PLUTONIUM_240 =
        GTRegisters.MATERIALS.register(
            "plutonium_240",
            () -> GTMaterial.builder()
                .name("plutonium_240")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 235, 30, 30)
                .textureSet("rad")
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 147
    public static final DeferredHolder<GTMaterial, GTMaterial> PLUTONIUM_241 =
        GTRegisters.MATERIALS.register(
            "plutonium_241",
            () -> GTMaterial.builder()
                .name("plutonium_241")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 245, 70, 70)
                .textureSet("rad")
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 149
    public static final DeferredHolder<GTMaterial, GTMaterial> PLUTONIUM_243 =
        GTRegisters.MATERIALS.register(
            "plutonium_243",
            () -> GTMaterial.builder()
                .name("plutonium_243")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 250, 70, 70)
                .textureSet("rad")
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 144
    public static final DeferredHolder<GTMaterial, GTMaterial> PLUTONIUM_238 =
        GTRegisters.MATERIALS.register(
            "plutonium_238",
            () -> GTMaterial.builder()
                .name("plutonium_238")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 250, 30, 30)
                .textureSet("rad")
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 94, 94, 145
    public static final DeferredHolder<GTMaterial, GTMaterial> PLUTONIUM_239 =
        GTRegisters.MATERIALS.register(
            "plutonium_239",
            () -> GTMaterial.builder()
                .name("plutonium_239")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 235, 50, 50)
                .textureSet("rad")
                .meltingPoint(912)
                .boilingPoint(3501)
                .densityGramPerCm3(19.84)
                .build()
        );
    // Protons, neutrons, electrons: 95, 95, 150
    public static final DeferredHolder<GTMaterial, GTMaterial> AMERICIUM =
        GTRegisters.MATERIALS.register(
            "americium",
            () -> GTMaterial.builder()
                .name("americium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 200, 200, 200)
                .textureSet("rad")
                .meltingPoint(1449)
                .boilingPoint(2880)
                .densityGramPerCm3(13.69)
                .build()
        );
    // Protons, neutrons, electrons: 95, 95, 146
    public static final DeferredHolder<GTMaterial, GTMaterial> AMERICIUM_241 =
        GTRegisters.MATERIALS.register(
            "americium_241",
            () -> GTMaterial.builder()
                .name("americium_241")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 210, 210, 210)
                .textureSet("rad")
                .meltingPoint(1449)
                .boilingPoint(2880)
                .densityGramPerCm3(13.69)
                .build()
        );
    // Protons, neutrons, electrons: 95, 95, 147
    public static final DeferredHolder<GTMaterial, GTMaterial> AMERICIUM_242 =
        GTRegisters.MATERIALS.register(
            "americium_242",
            () -> GTMaterial.builder()
                .name("americium_242")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 210, 210, 210)
                .textureSet("rad")
                .meltingPoint(1449)
                .boilingPoint(2880)
                .densityGramPerCm3(13.69)
                .build()
        );
    // Protons, neutrons, electrons: 96, 96, 153
    public static final DeferredHolder<GTMaterial, GTMaterial> CURIUM =
        GTRegisters.MATERIALS.register(
            "curium",
            () -> GTMaterial.builder()
                .name("curium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1613)
                .boilingPoint(3383)
                .densityGramPerCm3(13.51)
                .build()
        );
    // Protons, neutrons, electrons: 97, 97, 152
    public static final DeferredHolder<GTMaterial, GTMaterial> BERKELIUM =
        GTRegisters.MATERIALS.register(
            "berkelium",
            () -> GTMaterial.builder()
                .name("berkelium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1259)
                .boilingPoint(2900)
                .densityGramPerCm3(14.79)
                .build()
        );
    // Protons, neutrons, electrons: 98, 98, 153
    public static final DeferredHolder<GTMaterial, GTMaterial> CALIFORNIUM =
        GTRegisters.MATERIALS.register(
            "californium",
            () -> GTMaterial.builder()
                .name("californium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1173)
                .boilingPoint(1743)
                .densityGramPerCm3(15.1)
                .build()
        );
    // Protons, neutrons, electrons: 99, 99, 153
    public static final DeferredHolder<GTMaterial, GTMaterial> EINSTEINIUM =
        GTRegisters.MATERIALS.register(
            "einsteinium",
            () -> GTMaterial.builder()
                .name("einsteinium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1133)
                .boilingPoint(1269)
                .densityGramPerCm3(8.84)
                .build()
        );
    // Protons, neutrons, electrons: 100, 100, 157
    public static final DeferredHolder<GTMaterial, GTMaterial> FERMIUM =
        GTRegisters.MATERIALS.register(
            "fermium",
            () -> GTMaterial.builder()
                .name("fermium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1125)
                .boilingPoint(3000)
                .densityGramPerCm3(0.0)
                .build()
        );
    // Protons, neutrons, electrons: 101, 101, 157
    public static final DeferredHolder<GTMaterial, GTMaterial> MENDELEVIUM =
        GTRegisters.MATERIALS.register(
            "mendelevium",
            () -> GTMaterial.builder()
                .name("mendelevium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1100)
                .boilingPoint(3000)
                .densityGramPerCm3(0.0)
                .build()
        );
    // Protons, neutrons, electrons: 102, 102, 157
    public static final DeferredHolder<GTMaterial, GTMaterial> NOBELIUM =
        GTRegisters.MATERIALS.register(
            "nobelium",
            () -> GTMaterial.builder()
                .name("nobelium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1100)
                .boilingPoint(3000)
                .densityGramPerCm3(0.0)
                .build()
        );
    // Protons, neutrons, electrons: 103, 103, 159
    public static final DeferredHolder<GTMaterial, GTMaterial> LAWRENCIUM =
        GTRegisters.MATERIALS.register(
            "lawrencium",
            () -> GTMaterial.builder()
                .name("lawrencium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1900)
                .boilingPoint(3000)
                .densityGramPerCm3(0.0)
                .build()
        );
    // Protons, neutrons, electrons: 104, 104, 161
    public static final DeferredHolder<GTMaterial, GTMaterial> RUTHERFORDIUM =
        GTRegisters.MATERIALS.register(
            "rutherfordium",
            () -> GTMaterial.builder()
                .name("rutherfordium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(2400)
                .boilingPoint(5800)
                .densityGramPerCm3(23.2)
                .build()
        );
    // Protons, neutrons, electrons: 105, 105, 163
    public static final DeferredHolder<GTMaterial, GTMaterial> DUBNIUM =
        GTRegisters.MATERIALS.register(
            "dubnium",
            () -> GTMaterial.builder()
                .name("dubnium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(29.3)
                .build()
        );
    // Protons, neutrons, electrons: 106, 106, 165
    public static final DeferredHolder<GTMaterial, GTMaterial> SEABORGIUM =
        GTRegisters.MATERIALS.register(
            "seaborgium",
            () -> GTMaterial.builder()
                .name("seaborgium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(35.0)
                .build()
        );
    // Protons, neutrons, electrons: 107, 107, 163
    public static final DeferredHolder<GTMaterial, GTMaterial> BOHRIUM =
        GTRegisters.MATERIALS.register(
            "bohrium",
            () -> GTMaterial.builder()
                .name("bohrium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(37.1)
                .build()
        );
    // Protons, neutrons, electrons: 108, 108, 169
    public static final DeferredHolder<GTMaterial, GTMaterial> HASSIUM =
        GTRegisters.MATERIALS.register(
            "hassium",
            () -> GTMaterial.builder()
                .name("hassium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(40.7)
                .build()
        );
    // Protons, neutrons, electrons: 109, 109, 167
    public static final DeferredHolder<GTMaterial, GTMaterial> MEITNERIUM =
        GTRegisters.MATERIALS.register(
            "meitnerium",
            () -> GTMaterial.builder()
                .name("meitnerium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(37.4)
                .build()
        );
    // Protons, neutrons, electrons: 110, 110, 171
    public static final DeferredHolder<GTMaterial, GTMaterial> DARMSTADTIUM =
        GTRegisters.MATERIALS.register(
            "darmstadtium",
            () -> GTMaterial.builder()
                .name("darmstadtium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(34.8)
                .build()
        );
    // Protons, neutrons, electrons: 111, 111, 169
    public static final DeferredHolder<GTMaterial, GTMaterial> ROENTGENIUM =
        GTRegisters.MATERIALS.register(
            "roentgenium",
            () -> GTMaterial.builder()
                .name("roentgenium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(1000)
                .boilingPoint(3000)
                .densityGramPerCm3(28.7)
                .build()
        );
    // Protons, neutrons, electrons: 112, 112, 173
    public static final DeferredHolder<GTMaterial, GTMaterial> COPERNICIUM =
        GTRegisters.MATERIALS.register(
            "copernicium",
            () -> GTMaterial.builder()
                .name("copernicium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(150)
                .boilingPoint(357)
                .densityGramPerCm3(23.7)
                .build()
        );
    // Protons, neutrons, electrons: 113, 113, 171
    public static final DeferredHolder<GTMaterial, GTMaterial> NIHONIUM =
        GTRegisters.MATERIALS.register(
            "nihonium",
            () -> GTMaterial.builder()
                .name("nihonium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(700)
                .boilingPoint(1400)
                .densityGramPerCm3(16.0)
                .build()
        );
    // Protons, neutrons, electrons: 114, 114, 175
    public static final DeferredHolder<GTMaterial, GTMaterial> FLEROVIUM =
        GTRegisters.MATERIALS.register(
            "flerovium",
            () -> GTMaterial.builder()
                .name("flerovium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(340)
                .boilingPoint(420)
                .densityGramPerCm3(14.0)
                .build()
        );
    // Protons, neutrons, electrons: 114, 114, 184
    public static final DeferredHolder<GTMaterial, GTMaterial> FLEROVIUM_298 =
        GTRegisters.MATERIALS.register(
            "flerovium_298",
            () -> GTMaterial.builder()
                .name("flerovium_298")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(340)
                .boilingPoint(420)
                .densityGramPerCm3(14.0)
                .build()
        );
    // Protons, neutrons, electrons: 115, 115, 174
    public static final DeferredHolder<GTMaterial, GTMaterial> MOSCOVIUM =
        GTRegisters.MATERIALS.register(
            "moscovium",
            () -> GTMaterial.builder()
                .name("moscovium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(700)
                .boilingPoint(1400)
                .densityGramPerCm3(13.5)
                .build()
        );
    // Protons, neutrons, electrons: 116, 116, 177
    public static final DeferredHolder<GTMaterial, GTMaterial> LIVERMORIUM =
        GTRegisters.MATERIALS.register(
            "livermorium",
            () -> GTMaterial.builder()
                .name("livermorium")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(708)
                .boilingPoint(1085)
                .densityGramPerCm3(12.9)
                .build()
        );
    // Protons, neutrons, electrons: 117, 117, 177
    public static final DeferredHolder<GTMaterial, GTMaterial> TENNESSINE =
        GTRegisters.MATERIALS.register(
            "tennessine",
            () -> GTMaterial.builder()
                .name("tennessine")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 60, 70, 80)
                .textureSet("rad")
                .meltingPoint(673)
                .boilingPoint(823)
                .densityGramPerCm3(7.2)
                .build()
        );
    // Protons, neutrons, electrons: 118, 118, 176
    public static final DeferredHolder<GTMaterial, GTMaterial> OGANESSON =
        GTRegisters.MATERIALS.register(
            "oganesson",
            () -> GTMaterial.builder()
                .name("oganesson")
                .englishLanguageDatagenType(GTMaterial.EnglishLanguageDatagenType.CHEMICAL_ELEMENT)
                .colorData(255, 256, 256, 256)
                .textureSet("rad")
                .meltingPoint(258)
                .boilingPoint(263)
                .densityGramPerCm3(5.0)
                .build()
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
