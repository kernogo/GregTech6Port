package ru.kernogo.gregtech6port.features.material_kind_items;

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
}
