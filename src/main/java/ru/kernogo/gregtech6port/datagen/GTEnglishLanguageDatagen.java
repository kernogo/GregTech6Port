package ru.kernogo.gregtech6port.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialNameEnglishDatagen;
import ru.kernogo.gregtech6port.features.material_kind_items.registration.GTMaterialKindItemEnglishLanguageDatagen;

/** Datagen for all entries in en_us.json file */
public final class GTEnglishLanguageDatagen extends LanguageProvider {
    private final GTMaterialNameEnglishDatagen materialNameEnglishDatagen = new GTMaterialNameEnglishDatagen(this);
    private final GTMaterialKindItemEnglishLanguageDatagen materialKindItemEnglishLanguageDatagen =
        new GTMaterialKindItemEnglishLanguageDatagen(this);

    public GTEnglishLanguageDatagen(PackOutput output) {
        super(output, GregTech6Port.MODID, "en_us");
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName() + " " + super.getName();
    }

    @Override
    protected void addTranslations() {
        materialNameEnglishDatagen.addTranslations();
        materialKindItemEnglishLanguageDatagen.addTranslations();
    }
}
