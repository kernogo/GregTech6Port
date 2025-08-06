package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringCapability;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringSystem;

public final class GTCapabilities {
    private GTCapabilities() {}

    /**
     * Capability for the tint coloring of Blocks corresponding to BlockEntities. <br>
     * Works automatically after registration with the system. See {@link GTTintColoringSystem} for the details.
     */
    public static final BlockCapability<GTTintColoringCapability, @Nullable Void> TINT_COLORING =
        BlockCapability.createVoid(
            ResourceLocation.fromNamespaceAndPath(GregTech6Port.MODID, "tint_coloring"),
            GTTintColoringCapability.class
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
