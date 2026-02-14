package ru.kernogo.gregtech6port.features.material_kind_things.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This service should be used
 * to access the all definitions of Material-Kind Blocks that need to be registered. <br>
 * The definitions are for registration purpose only. <br>
 * Use {@link #getInstance()} to access this service.
 *
 * @see GTMaterialKindBlockDefinition
 * @see ru.kernogo.gregtech6port.features.material_kind_things.items.GTMaterialKindItemDefinitionService
 * Another sevice which is made similarly to this service
 * (if anything changes here, you should probably change it there too)
 */
public final class GTMaterialKindBlockDefinitionService {
    private static final GTMaterialKindBlockDefinitionService INSTANCE = new GTMaterialKindBlockDefinitionService();

    private GTMaterialKindBlockDefinitionService() {}

    private final List<GTMaterialKindBlockDefinition> gtMaterialKindBlockDefinitions = new ArrayList<>();

    /** List order matters for {@link GTDominantMaterialKindBlockAccessService} */
    private final List<GTExistingMaterialKindBlockDefinition> existingMaterialKindBlockDefinitions = new ArrayList<>();

    public static GTMaterialKindBlockDefinitionService getInstance() {
        return INSTANCE;
    }

    /** Add definition for pending registration */
    public void addGTMaterialKindBlockDefinition(GTMaterialKindBlockDefinition definition) {
        gtMaterialKindBlockDefinitions.add(definition);
    }

    /** This method is called by classes that need the definitions to register stuff */
    public List<GTMaterialKindBlockDefinition> getGTMaterialKindBlockDefinitions() {
        return Collections.unmodifiableList(gtMaterialKindBlockDefinitions);
    }

    /** Insertion order matters for {@link GTDominantMaterialKindBlockAccessService} */
    public void addExistingMaterialKindBlockDefinition(GTExistingMaterialKindBlockDefinition definition) {
        existingMaterialKindBlockDefinitions.add(definition);
    }

    /** List order matters for {@link GTDominantMaterialKindBlockAccessService} */
    public List<GTExistingMaterialKindBlockDefinition> getExistingMaterialKindItemDefinitions() {
        return Collections.unmodifiableList(existingMaterialKindBlockDefinitions);
    }
}
