package ru.kernogo.gregtech6port.features.material_kind_items;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/** Item model tinting for {@link GTMaterialKindItem} */
@Slf4j
public final class GTMaterialKindItemTintingHandler {
    private GTMaterialKindItemTintingHandler() {}

    private static final GTMaterialKindItemDefinitionService materialKindItemDefinitionService = GTMaterialKindItemDefinitionService.getInstance();

    /** This gets subscribed with the modBus in another class */
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        Item[] itemsToRegister = materialKindItemDefinitionService.getDefinitions().stream()
            .map(definition -> definition.deferredItem().get())
            .toArray(Item[]::new);

        event.register(GTMaterialKindItemTintingHandler::getItemColor, itemsToRegister);
    }

    private static int getItemColor(ItemStack stack, int tintIndex) {
        if (tintIndex != 0) {
            return -1; // We only tint the tintIndex 0 (layer 0)
        }
        if (!(stack.getItem() instanceof GTMaterialKindItem materialKindItem)) {
            log.error("Expected the Item of stack={} to be an instance of GTMaterialKindItem, but it was not", stack,
                new RuntimeException("Exception thrown for stack trace purposes"));
            return -1;
        }
        GTMaterial.ColorData colorData = materialKindItem.getColorDataForTinting();
        try {
            GTMaterial.ColorData.validateAndThrowIfInvalid(colorData);
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Invalid ColorData={} in Material-Kind Item Tinting", colorData, e);
        }
        return (colorData.a() << 24) + (colorData.r() << 16) + (colorData.g() << 8) + colorData.b();
    }
}
