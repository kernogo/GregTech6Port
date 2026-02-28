package ru.kernogo.gregtech6port.features.blockentities.anvil.models;

import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialTextureSet;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;

import java.util.Objects;

/** Key to get the "standalone" models for things on anvil */
record GTThingOnAnvilKey(
    GTMaterialThingKind kind,
    GTMaterialTextureSet textureSetUsedForTexture,
    GTMaterialThingKind kindUsedForTexture,
    LeftOrRight leftOrRight
) {
    // Kinds and TextureSets are identified by their names, so we use that in equals() and hashCode()

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GTThingOnAnvilKey that = (GTThingOnAnvilKey) o;
        return leftOrRight == that.leftOrRight &&
            Objects.equals(kind.name(), that.kind.name()) &&
            Objects.equals(kindUsedForTexture.name(), that.kindUsedForTexture.name()) &&
            Objects.equals(textureSetUsedForTexture.name(), that.textureSetUsedForTexture.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind.name(), textureSetUsedForTexture.name(), kindUsedForTexture.name(), leftOrRight);
    }

    enum LeftOrRight {
        LEFT, RIGHT
    }

    /** TODO javadoc */
    String modelPath() {
        // like "standalone/anvil/things_on_anvil/ingot__metallic__solid_block__left"
        return "standalone/anvil/things_on_an_anvil/%s__%s__%s__%s"
            .formatted(kind.name(), textureSetUsedForTexture.name(), kindUsedForTexture.name(), leftOrRight.name().toLowerCase());
    }

    /** TODO javadoc */
    String parentModelPath() {
        // like "standalone/anvil/things_on_anvil/ingot_left_parent"
        return "standalone/anvil/things_on_an_anvil/%s_%s_parent"
            .formatted(kind.name(), leftOrRight.name().toLowerCase());
    }
}
