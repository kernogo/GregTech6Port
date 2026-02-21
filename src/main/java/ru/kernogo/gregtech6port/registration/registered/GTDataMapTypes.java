package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.GTMaterialAndAmount;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.capabilities.IGTMaterialCompositionCapability;

import java.util.List;

public final class GTDataMapTypes {
    private GTDataMapTypes() {}

    /**
     * Map from Item to its base material composition,
     * which is used in calculations of Item's actual material composition. <br>
     * Actual material composition may be different from it
     * (for example, a damaged Item will contain less of each material than an undamaged one). <br>
     * Use {@link IGTMaterialCompositionCapability}
     * to get the actual material composition of an item.
     */
    public static final DataMapType<Item, List<GTMaterialAndAmount>> BASE_MATERIAL_COMPOSITION = DataMapType.builder(
        Identifier.fromNamespaceAndPath(GregTech6Port.MODID, "base_material_composition"),
        Registries.ITEM,
        GTMaterialAndAmount.MATERIAL_UNITS_CODEC.listOf()
    ).synced(GTMaterialAndAmount.MATERIAL_UNITS_CODEC.listOf(), false).build();

    /** This gets subscribed with the modBus in another class */
    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(GTDataMapTypes.BASE_MATERIAL_COMPOSITION);
    }

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
