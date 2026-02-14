package ru.kernogo.gregtech6port.features.material_kind_things.items;

import net.minecraft.world.item.Item;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.List;
import java.util.Objects;

/**
 * This service is needed because some Material-Kind Items already exist as vanilla or modded Items. <br>
 * For example, for Material=Iron and Kind=Ingot there is a Vanilla Iron Ingot. <br>
 * We don't want to use the GregTech-generated Iron Ingot if there is already a vanilla one. <br>
 * {@link #getDominantItemByMaterialAndKind} ) returns dominant Items by given Material and Kind. <br>
 * A dominant Item is an Item that should be used as a recipe output for a given Material and Kind. <br>
 * Use {@link #getInstance()} to access this service.
 *
 * @see ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTDominantMaterialKindBlockAccessService
 * Another service which is made similarly to this service
 * (if anything changes here, you should probably change it there too)
 */
public final class GTDominantMaterialKindItemAccessService {
    private static final GTDominantMaterialKindItemAccessService INSTANCE = new GTDominantMaterialKindItemAccessService();

    private GTDominantMaterialKindItemAccessService() {}

    private static final GTMaterialKindItemDefinitionService materialKindItemDefinitionService =
        GTMaterialKindItemDefinitionService.getInstance();

    public static GTDominantMaterialKindItemAccessService getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a dominant Item by Material and Kind (vanilla / from other mods / GT's Material-Kind Item). <br>
     * A dominant Item is an Item that should be used as a recipe output for a given Material and Kind. <br>
     * The first Item of given Material and Kind found in the "existing Material-Kind Items list"
     * is used as the dominant Item,
     * or else (if not found there) then the GregTech-generated Material-Kind Item is used as the dominant Item.
     */
    public Item getDominantItemByMaterialAndKind(GTMaterial material, GTMaterialThingKind kind) {
        Item firstExistingItem = materialKindItemDefinitionService.getExistingMaterialKindItemDefinitions().stream()
            .filter(definition -> definition.material().equals(material) && definition.kind().equals(kind))
            .map(GTExistingMaterialKindItemDefinition::getItemOrElseNull)
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(null);

        if (firstExistingItem != null) {
            return firstExistingItem;
        }

        return getGTMaterialKindItemOrElseThrow(material, kind);
    }

    private static Item getGTMaterialKindItemOrElseThrow(GTMaterial material, GTMaterialThingKind kind) {
        List<GTMaterialKindItemDefinition> candidates = materialKindItemDefinitionService.getGTMaterialKindItemDefinitions().stream()
            .filter(definition -> definition.material().equals(material) && definition.kind().equals(kind))
            .toList();
        if (candidates.size() != 1) { // Should never happen, but we still check
            throw new GTUnexpectedValidationFailException(
                "Found %s (!= 1) definitions for Material=%s, Kind=%s".formatted(candidates.size(), material, kind)
            );
        }
        return candidates.getFirst().deferredItem().get();
    }
}
