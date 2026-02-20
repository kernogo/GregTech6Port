package ru.kernogo.gregtech6port.registration;

import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringTintSource;
import ru.kernogo.gregtech6port.features.material_kind_things.GTMaterialKindItemTintSource;
import ru.kernogo.gregtech6port.utils.GTUtils;

/**
 * Registration of
 * <a href="https://minecraft.wiki/w/Items_model_definition#Tint_sources_types">"tint sources types" for "Items Model Definition"</a>
 */
public final class GTItemTintSourcesHandler {
    private GTItemTintSourcesHandler() {}

    /** This gets subscribed with the modBus in another class */
    public static void registerItemTintSources(RegisterColorHandlersEvent.ItemTintSources event) {
        event.register(GTUtils.modLoc("tint_coloring"), GTTintColoringTintSource.MAP_CODEC);
        event.register(GTUtils.modLoc("material_kind_item"), GTMaterialKindItemTintSource.MAP_CODEC);
    }
}
