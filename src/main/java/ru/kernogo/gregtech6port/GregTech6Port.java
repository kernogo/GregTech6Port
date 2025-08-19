package ru.kernogo.gregtech6port;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import ru.kernogo.gregtech6port.registration.GTRegisters;

@Mod(GregTech6Port.MODID)
public final class GregTech6Port {
    private GregTech6Port() {}

    public static final String MODID = "gregtech6port";

    public GregTech6Port(IEventBus modEventBus, ModContainer modContainer) {
        GTRegisters.registerEverything(modEventBus);
    }
}
