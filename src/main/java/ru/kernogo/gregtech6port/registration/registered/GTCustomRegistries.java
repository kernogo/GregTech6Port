package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialTextureSet;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

public final class GTCustomRegistries {
    private GTCustomRegistries() {}

    public static final Registry<GTMaterial> MATERIALS = new RegistryBuilder<GTMaterial>(
        ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(GregTech6Port.MODID, "materials"))
    ).sync(false).create();

    public static final Registry<GTMaterialTextureSet> MATERIAL_TEXTURE_SETS = new RegistryBuilder<GTMaterialTextureSet>(
        ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(GregTech6Port.MODID, "material_texture_sets"))
    ).sync(false).create();

    public static final Registry<GTMaterialThingKind> MATERIAL_THING_KINDS = new RegistryBuilder<GTMaterialThingKind>(
        ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(GregTech6Port.MODID, "material_thing_kinds"))
    ).sync(false).create();

    /** This gets subscribed with the modBus in another class */
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(MATERIALS);
        event.register(MATERIAL_TEXTURE_SETS);
        event.register(MATERIAL_THING_KINDS);
    }
}
