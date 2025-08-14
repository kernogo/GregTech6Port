package ru.kernogo.gregtech6port.registration;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialCompositionTooltipProvider;

import java.util.List;

/** Builds the final Item tooltip using various providers from different features */
public final class GTItemTooltipEventHandler {
    private GTItemTooltipEventHandler() {}

    /** This gets subscribed with the modBus in another class */
    public static void handleItemTooltipEvent(ItemTooltipEvent event) {
        List<Component> materialCompositionTooltip = GTMaterialCompositionTooltipProvider.getMaterialCompositionTooltip(
            event.getItemStack()
        );

        event.getToolTip().addAll(event.getToolTip().size() - 1, materialCompositionTooltip);
    }
}
