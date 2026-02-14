package ru.kernogo.gregtech6port.registration;

import net.neoforged.neoforge.registries.RegisterEvent;
import ru.kernogo.gregtech6port.features.material_kind_things.GTMaterialKindItemsAndBlocksRegistration;

public final class GTRegisterEventHandler {
    private GTRegisterEventHandler() {}

    private static boolean isAlreadyRan = false;

    /** This gets called from another class */
    public static void handleRegisterEvent(RegisterEvent event) {
        // We only run this code once after the first of many "RegisterEvent"s is run
        if (isAlreadyRan) {
            return;
        }

        // Write objects from our manual registers into Minecraft's registries
        GTRegisters.MATERIAL_THING_KINDS.actuallyRegister();
        GTRegisters.MATERIAL_TEXTURE_SETS.actuallyRegister();
        GTRegisters.MATERIALS.actuallyRegister();

        // NeoForge's wiki tells us not to query registries while they're still unfrozen
        // (https://docs.neoforged.net/docs/1.21.1/concepts/registries#querying-registries).
        // But we ignore that for some of our custom registries

        GTMaterialKindItemsAndBlocksRegistration.registerAllMaterialKindItemsAndBlocks();
        GTMaterialKindItemsAndBlocksRegistration.registerExistingMaterialKindItemsAndBlocks();

        isAlreadyRan = true;
    }
}
