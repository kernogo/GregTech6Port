package ru.kernogo.gregtech6port.features.material_kind_things.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This service should be used
 * to access the all definitions of Material-Kind Items that need to be registered. <br>
 * The definitions are for registration purpose only. <br>
 * Use {@link #getInstance()} to access this service.
 *
 * @see GTMaterialKindItemDefinition
 * @see ru.kernogo.gregtech6port.features.material_kind_things.blocks.GTMaterialKindBlockDefinitionService
 * Another service which is made similarly to this service
 * (if anything changes here, you should probably change it there too)
 */
public final class GTMaterialKindItemDefinitionService {
    private static final GTMaterialKindItemDefinitionService INSTANCE = new GTMaterialKindItemDefinitionService();

    private GTMaterialKindItemDefinitionService() {}

    private final List<GTMaterialKindItemDefinition> gtMaterialKindItemDefinitions = new ArrayList<>();

    /** List order matters for {@link GTDominantMaterialKindItemAccessService} */
    private final List<GTExistingMaterialKindItemDefinition> existingMaterialKindItemDefinitions = new ArrayList<>();

    public static GTMaterialKindItemDefinitionService getInstance() {
        return INSTANCE;
    }

    /** Add definition for pending registration */
    public void addGTMaterialKindItemDefinition(GTMaterialKindItemDefinition definition) {
        gtMaterialKindItemDefinitions.add(definition);
    }

    /** This method is called by classes that need the definitions to register stuff */
    public List<GTMaterialKindItemDefinition> getGTMaterialKindItemDefinitions() {
        return Collections.unmodifiableList(gtMaterialKindItemDefinitions);
    }

    /** Insertion order matters for {@link GTDominantMaterialKindItemAccessService} */
    public void addExistingMaterialKindItemDefinition(GTExistingMaterialKindItemDefinition definition) {
        existingMaterialKindItemDefinitions.add(definition);
    }

    /** List order matters for {@link GTDominantMaterialKindItemAccessService} */
    public List<GTExistingMaterialKindItemDefinition> getExistingMaterialKindItemDefinitions() {
        return Collections.unmodifiableList(existingMaterialKindItemDefinitions);
    }
}
