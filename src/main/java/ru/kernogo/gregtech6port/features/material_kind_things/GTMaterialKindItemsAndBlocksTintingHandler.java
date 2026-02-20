package ru.kernogo.gregtech6port.features.material_kind_things;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTMaterialKindBlockDefinitionService;
import ru.kernogo.gregtech6port.features.material_kind_things.blocks.IGTTintedMaterialKindBlock;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/** Model tinting for Material-Kind Items and Blocks */
@Slf4j
public final class GTMaterialKindItemsAndBlocksTintingHandler {
    private GTMaterialKindItemsAndBlocksTintingHandler() {}

    private static final GTMaterialKindBlockDefinitionService materialKindBlockDefinitionService =
        GTMaterialKindBlockDefinitionService.getInstance();

    /** This gets subscribed with the modBus in another class */
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        Block[] blocksToRegister = materialKindBlockDefinitionService.getGTMaterialKindBlockDefinitions().stream()
            .map(definition -> definition.deferredBlock().get())
            .toArray(Block[]::new);

        event.register(GTMaterialKindItemsAndBlocksTintingHandler::getBlockColor, blocksToRegister);
    }

    private static int getBlockColor(BlockState state, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int tintIndex) {
        if (tintIndex != 0) {
            return -1; // We only tint the tintIndex 0 (layer 0)
        }
        if (!(state.getBlock() instanceof IGTTintedMaterialKindBlock materialKindBlock)) {
            log.error("Expected the Block={} to be an instance of IGTTintedMaterialKindBlock, but it was not", state.getBlock(),
                new RuntimeException("Exception thrown for stack trace purposes"));
            return -1;
        }
        GTMaterial.ColorData colorData = materialKindBlock.getColorDataForTinting();
        try {
            GTMaterial.ColorData.validateAndThrowIfInvalid(colorData);
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Invalid ColorData={} in Material-Kind Block Tinting", colorData, e);
        }
        return (colorData.a() << 24) + (colorData.r() << 16) + (colorData.g() << 8) + colorData.b();
    }
}
