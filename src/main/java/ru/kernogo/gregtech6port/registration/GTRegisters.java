package ru.kernogo.gregtech6port.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.registration.registered.GTDataMapTypes;
import ru.kernogo.gregtech6port.registration.registered.GTItems;
import ru.kernogo.gregtech6port.registration.registered.materials.GTBasicMaterials;
import ru.kernogo.gregtech6port.registration.registered.materials.GTChemicalElementMaterials;
import ru.kernogo.gregtech6port.registration.registration.GTCapabilitiesRegistration;
import ru.kernogo.gregtech6port.registration.registration.GTItemPropertiesRegistration;

public final class GTRegisters {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GregTech6Port.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GregTech6Port.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GregTech6Port.MODID);
    public static final DeferredRegister.DataComponents DATA_COMPONENTS_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, GregTech6Port.MODID);

    // Registers for custom registries go below

    public static final DeferredRegister<GTMaterial> MATERIALS = DeferredRegister.create(
        GTCustomRegistries.MATERIALS, GregTech6Port.MODID
    );

    public static void registerEverything(IEventBus modEventBus) {
        registerObjectsInRegistries(modEventBus);

        // Remember that there are "@EventBusSubscriber"s that register other listeners by themselves and are not listed here

        modEventBus.addListener(GTCustomRegistries::registerRegistries);
        modEventBus.addListener(GTDataMapTypes::registerDataMapTypes);

        modEventBus.addListener(GTCapabilitiesRegistration::registerAllCapabilities);
        modEventBus.addListener(GTItemPropertiesRegistration::registerItemProperties);

        NeoForge.EVENT_BUS.addListener(GTItemTooltipEventHandler::handleItemTooltipEvent);
    }

    private static void registerObjectsInRegistries(IEventBus modEventBus) {
        // Call an arbitrary method for each class that registers stuff
        // to make sure that all static fields there are initialized (thus registering stuff with DeferredRegisters)
        GTBlocks.init();
        GTItems.init();
        GTBlockEntityTypes.init();
        GTDataComponentTypes.init();
        GTDataMapTypes.init();

        // Custom registered data classes go below
        GTChemicalElementMaterials.init();
        GTBasicMaterials.init();

        // Then register DeferredRegisters with the mod event bus
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        DATA_COMPONENTS_TYPES.register(modEventBus);

        // Custom registers go below
        MATERIALS.register(modEventBus);

        GTCapabilities.init(); // Capabilities don't need an externally created registry
    }
}
