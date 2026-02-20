package ru.kernogo.gregtech6port.registration;

import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesUseStageModelProperty;
import ru.kernogo.gregtech6port.utils.GTUtils;

/**
 * Registration of
 * <a href="https://minecraft.wiki/w/Items_model_definition#select">"select" properties of Items Model Definition</a>
 */
public final class GTRegisterSelectItemModelPropertyEventHandler {
    private GTRegisterSelectItemModelPropertyEventHandler() {}

    /** This gets subscribed with the modBus in another class */
    public static void handle(RegisterSelectItemModelPropertyEvent event) {
        event.register(GTUtils.modLoc("item_with_uses_use_stage"), GTItemWithUsesUseStageModelProperty.TYPE);
    }
}
