package ru.kernogo.gregtech6port.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.datagen.GTDatagenMain;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialTextureSet;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.capabilities.GTMaterialCompositionCapabilitiesRegistration;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringCapabilitiesRegistration;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringForBlocks;
import ru.kernogo.gregtech6port.features.items.like.spray.GTSprayLikeItemEntityInteractHandler;
import ru.kernogo.gregtech6port.features.material_kind_things.GTMaterialKindItemsAndBlocksTintingHandler;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.registration.registered.GTBlocks;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.registration.registered.GTDataMapTypes;
import ru.kernogo.gregtech6port.registration.registered.GTItems;
import ru.kernogo.gregtech6port.registration.registered.GTRecipeSerializers;
import ru.kernogo.gregtech6port.registration.registered.materials.GTBasicMaterials;
import ru.kernogo.gregtech6port.registration.registered.materials.GTChemicalElementMaterials;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialTextureSets;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;

public final class GTRegisters {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GregTech6Port.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GregTech6Port.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GregTech6Port.MODID);
    public static final DeferredRegister.DataComponents DATA_COMPONENTS_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, GregTech6Port.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, GregTech6Port.MODID);

    // Registers for custom registries go below

    public static final GTLessDeferredRegister<GTMaterial> MATERIALS = new GTLessDeferredRegister<>(
        GTCustomRegistries.MATERIALS, GregTech6Port.MODID
    );

    public static final GTLessDeferredRegister<GTMaterialTextureSet> MATERIAL_TEXTURE_SETS = new GTLessDeferredRegister<>(
        GTCustomRegistries.MATERIAL_TEXTURE_SETS, GregTech6Port.MODID
    );

    public static final GTLessDeferredRegister<GTMaterialThingKind> MATERIAL_THING_KINDS = new GTLessDeferredRegister<>(
        GTCustomRegistries.MATERIAL_THING_KINDS, GregTech6Port.MODID
    );

    public static void registerEverything(IEventBus modEventBus) {
        registerObjectsInRegistries(modEventBus);
        registerListeners(modEventBus);
    }

    private static void registerListeners(IEventBus modEventBus) {
        modEventBus.addListener(GTDatagenMain::datagenMain);

        modEventBus.addListener(GTRegisterEventHandler::handleRegisterEvent);

        modEventBus.addListener(GTCustomRegistries::registerRegistries);
        modEventBus.addListener(GTDataMapTypes::registerDataMapTypes);

        modEventBus.addListener(GTRegisterSelectItemModelPropertyEventHandler::handle);

        modEventBus.addListener(GTTintColoringCapabilitiesRegistration::registerCapabilities);
        modEventBus.addListener(GTMaterialCompositionCapabilitiesRegistration::registerCapabilitiesForAllItems);

        modEventBus.addListener(GTItemTintSourcesHandler::registerItemTintSources);

        modEventBus.addListener(GTTintColoringForBlocks::registerBlockColorHandlers);
        modEventBus.addListener(GTMaterialKindItemsAndBlocksTintingHandler::registerBlockColorHandlers);

        NeoForge.EVENT_BUS.addListener(GTItemTooltipEventHandler::handleItemTooltipEvent);
        NeoForge.EVENT_BUS.addListener(GTSprayLikeItemEntityInteractHandler::onEntityInteract);
    }

    private static void registerObjectsInRegistries(IEventBus modEventBus) {
        // Call an arbitrary method for each class that registers stuff
        // to make sure that all static fields there are initialized (thus registering stuff with DeferredRegisters)
        GTBlocks.init();
        GTItems.init();
        GTBlockEntityTypes.init();
        GTDataComponentTypes.init();
        GTRecipeSerializers.init();

        GTDataMapTypes.init();

        // Custom registered data classes go below
        GTMaterialTextureSets.init();
        GTMaterialThingKinds.init();
        GTChemicalElementMaterials.init();
        GTBasicMaterials.init();

        // Then register DeferredRegisters with the mod event bus
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        DATA_COMPONENTS_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);

        // Custom registers go below
        // ...

        GTCapabilities.init(); // Capabilities don't need an externally created registry
    }
}
