package ru.kernogo.gregtech6port.features.material_kind_things.blocks;

import net.minecraft.world.level.block.Block;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.List;
import java.util.Objects;

/**
 * @see ru.kernogo.gregtech6port.features.material_kind_things.items.GTDominantMaterialKindItemAccessService
 * Another sevice which is made similarly to this service,
 * see it for the details.
 * (If anything changes here, you should probably change it there too)
 */
public final class GTDominantMaterialKindBlockAccessService {
    private static final GTDominantMaterialKindBlockAccessService INSTANCE = new GTDominantMaterialKindBlockAccessService();

    private GTDominantMaterialKindBlockAccessService() {}

    private static final GTMaterialKindBlockDefinitionService materialKindBlockDefinitionService =
        GTMaterialKindBlockDefinitionService.getInstance();

    public static GTDominantMaterialKindBlockAccessService getInstance() {
        return INSTANCE;
    }

    /**
     * Works similarly to {@link ru.kernogo.gregtech6port.features.material_kind_things.items.GTDominantMaterialKindItemAccessService#getDominantItemByMaterialAndKind}.
     * See it for the details.
     */
    public Block getDominantBlockByMaterialAndKind(GTMaterial material, GTMaterialThingKind kind) {
        Block firstExistingBlock = materialKindBlockDefinitionService.getExistingMaterialKindItemDefinitions().stream()
            .filter(definition -> definition.material().equals(material) && definition.kind().equals(kind))
            .map(GTExistingMaterialKindBlockDefinition::getItemOrElseNull)
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(null);

        if (firstExistingBlock != null) {
            return firstExistingBlock;
        }

        return getGTMaterialKindBlockOrElseThrow(material, kind);
    }

    private static Block getGTMaterialKindBlockOrElseThrow(GTMaterial material, GTMaterialThingKind kind) {
        List<GTMaterialKindBlockDefinition> candidates = materialKindBlockDefinitionService.getGTMaterialKindBlockDefinitions().stream()
            .filter(definition -> definition.material().equals(material) && definition.kind().equals(kind))
            .toList();
        if (candidates.size() != 1) { // Should never happen, but we still check
            throw new GTUnexpectedValidationFailException(
                "Found %s (!= 1) definitions for Material=%s, Kind=%s".formatted(candidates.size(), material, kind)
            );
        }
        return candidates.getFirst().deferredBlock().get();
    }
}
