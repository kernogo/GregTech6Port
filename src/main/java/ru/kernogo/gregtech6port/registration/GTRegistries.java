package ru.kernogo.gregtech6port.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

public final class GTRegistries {
    private GTRegistries() {}

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GregTech6Port.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GregTech6Port.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GregTech6Port.MODID);
    public static final DeferredRegister.DataComponents DATA_COMPONENTS_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, GregTech6Port.MODID);

    public static void registerEverythingWithModEventBus(IEventBus modEventBus) {
        // Call an arbitrary method for each class that registers stuff
        // to make sure that all static fields there are initialized (thus registering stuff with DeferredRegisters)
        GTBlocks.init();
        GTItems.init();
        GTBlockEntityTypes.init();
        GTDataComponentTypes.init();
        GTCapabilities.init();

        // Then register DeferredRegisters with the mod event bus
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        DATA_COMPONENTS_TYPES.register(modEventBus);
    }
}
