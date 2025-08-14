package ru.kernogo.gregtech6port.features.behaviors.material_composition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;

/** This is simply a pair of material and its amount */
public record GTMaterialAndAmount(
    GTMaterial material,
    GTMaterialAmount amount
) {
    /** Encodes this record in material units using String representation of a mixed fraction */
    public static final Codec<GTMaterialAndAmount> MATERIAL_UNITS_CODEC = RecordCodecBuilder.create(
        builder -> builder.group(
            GTCustomRegistries.MATERIALS.byNameCodec().fieldOf("material").forGetter(GTMaterialAndAmount::material),
            GTMaterialAmount.MATERIAL_UNITS_CODEC.fieldOf("amount").forGetter(GTMaterialAndAmount::amount)
        ).apply(builder, GTMaterialAndAmount::new)
    );
}
