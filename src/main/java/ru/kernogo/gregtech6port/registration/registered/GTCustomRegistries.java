package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;

public final class GTCustomRegistries {
    private GTCustomRegistries() {}

    public static final Registry<GTMaterial> MATERIALS = new RegistryBuilder<GTMaterial>(
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(GregTech6Port.MODID, "materials"))
    ).sync(false).create();

    /** This gets subscribed with the modBus in another class */
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(MATERIALS);
    }
}
