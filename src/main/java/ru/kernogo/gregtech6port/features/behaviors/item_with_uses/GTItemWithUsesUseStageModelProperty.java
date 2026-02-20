package ru.kernogo.gregtech6port.features.behaviors.item_with_uses;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/**
 * "Select" property of Items Model Definition,
 * that is used to select concrete Model
 * based on ItemStack's use stage (calculated from ItemWithUsesData)
 */
@Slf4j
public record GTItemWithUsesUseStageModelProperty() implements SelectItemModelProperty<GTItemWithUsesUseStageModelProperty.UseStage> {
    private static final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();

    /** Enum representing possible cases to select */
    @AllArgsConstructor
    public enum UseStage implements StringRepresentable {
        FULL("full"), PARTIALLY_USED("partially_used"), EMPTY("empty");

        private final String name;

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public static final Codec<UseStage> VALUE_CODEC = StringRepresentable.fromValues(UseStage::values);

    public static final SelectItemModelProperty.Type<GTItemWithUsesUseStageModelProperty, UseStage> TYPE =
        SelectItemModelProperty.Type.create(
            MapCodec.unit(new GTItemWithUsesUseStageModelProperty()),
            VALUE_CODEC
        );

    @Override
    public UseStage get(ItemStack stack,
                        @Nullable ClientLevel level,
                        @Nullable LivingEntity entity,
                        int seed,
                        ItemDisplayContext displayContext) {
        try {
            GTItemWithUsesData itemWithUsesData = itemWithUsesBehavior.validateAndGetItemWithUsesData(stack);
            int remainingUses = itemWithUsesData.remainingUses();
            int maxRemainingUses = itemWithUsesData.maxRemainingUses();
            if (remainingUses == 0) {
                return UseStage.EMPTY;
            }
            if (remainingUses == maxRemainingUses) {
                return UseStage.FULL;
            }
            return UseStage.PARTIALLY_USED;
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Unexpected validation error on item with uses ItemStack={}", stack, e);
        }

        return UseStage.FULL; // return some reasonable value on error (full)
    }

    @Override
    public Codec<UseStage> valueCodec() {
        return VALUE_CODEC;
    }

    @Override
    public Type<? extends SelectItemModelProperty<UseStage>, UseStage> type() {
        return TYPE;
    }
}
