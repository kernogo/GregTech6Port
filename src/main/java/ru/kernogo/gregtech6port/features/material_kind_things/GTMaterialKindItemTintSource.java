package ru.kernogo.gregtech6port.features.material_kind_things;

import com.mojang.serialization.MapCodec;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.material_kind_things.items.IGTTintedMaterialKindItem;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/** Tint source for Material-Kind Items to tint their models based on Material's color */
@Slf4j
public record GTMaterialKindItemTintSource() implements ItemTintSource {
    public static final MapCodec<GTMaterialKindItemTintSource> MAP_CODEC = MapCodec.unit(new GTMaterialKindItemTintSource());

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        if (!(stack.getItem() instanceof IGTTintedMaterialKindItem materialKindItem)) {
            log.error("Expected the Item of stack={} to be an instance of IGTTintedMaterialKindItem, but it was not", stack,
                new RuntimeException("Exception thrown for stack trace purposes"));
            return -1;
        }
        GTMaterial.ColorData colorData = materialKindItem.getColorDataForTinting();
        try {
            GTMaterial.ColorData.validateAndThrowIfInvalid(colorData);
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Invalid ColorData={} in Material-Kind Item Tinting", colorData, e);
        }
        return (colorData.a() << 24) + (colorData.r() << 16) + (colorData.g() << 8) + colorData.b();
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
