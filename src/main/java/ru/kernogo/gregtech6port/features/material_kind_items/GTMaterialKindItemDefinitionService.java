package ru.kernogo.gregtech6port.features.material_kind_items;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link #INSTANCE} of this service should be used
 * to access the all definitions of {@link GTMaterialKindItem}s that need to be registered. <br>
 * The definitions are for registration purpose only.
 *
 * @see GTMaterialKindItemDefinition
 */
public final class GTMaterialKindItemDefinitionService {
    private static final GTMaterialKindItemDefinitionService INSTANCE = new GTMaterialKindItemDefinitionService();

    private GTMaterialKindItemDefinitionService() {}

    private final List<GTMaterialKindItemDefinition> definitions = new ArrayList<>();

    public static GTMaterialKindItemDefinitionService getInstance() {
        return INSTANCE;
    }

    /** Add definition for pending registration */
    public void addDefinition(GTMaterialKindItemDefinition definition) {
        definitions.add(definition);
    }

    /** This method is called by classes that need the definitions to register stuff */
    public List<GTMaterialKindItemDefinition> getDefinitions() {
        return definitions;
    }

    /**
     * Returns a definition by Material and Kind
     * and throws an exception if it was not found. <br>
     * Be careful when using this method outside registration
     * (because it throws an exception which may cause crash during gameplay if thrown and uncaught).
     */
    public GTMaterialKindItemDefinition getDefinitionByMaterialAndKind(GTMaterial material, GTMaterialThingKind kind) {
        List<GTMaterialKindItemDefinition> candidates = definitions.stream()
            .filter(definition -> definition.material().equals(material) && definition.kind().equals(kind))
            .toList();
        if (candidates.size() != 1) {
            throw new GTUnexpectedValidationFailException(
                "Found %s (!= 1) definitions for Material=%s, Kind=%s".formatted(candidates.size(), material, kind)
            );
        }
        return candidates.getFirst();
    }
}
