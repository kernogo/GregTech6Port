package ru.kernogo.gregtech6port.features.behaviors.material_composition;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.material_composition.capabilities.IGTMaterialCompositionCapability;
import ru.kernogo.gregtech6port.registration.registered.GTCapabilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Provides the tooltip showing the material composition ({@link GTMaterialCompositionData}) of an Item */
@Slf4j
public final class GTMaterialCompositionTooltipProvider {
    private GTMaterialCompositionTooltipProvider() {}

    /** This gets called from an external class */
    public static List<Component> getMaterialCompositionTooltip(ItemStack itemStack) {
        IGTMaterialCompositionCapability materialCompositionCapability = itemStack.getCapability(GTCapabilities.MATERIAL_COMPOSITION);

        if (materialCompositionCapability == null) {
            log.error("Material composition capability is supposed to exist on all items, but it does not exist on: {}", itemStack,
                new RuntimeException("Exception thrown for stack trace purposes"));
            return List.of();
        }

        GTMaterialCompositionData materialComposition = materialCompositionCapability.getMaterialComposition(itemStack);

        if (materialComposition == null) {
            return List.of();
        }

        List<Component> result = new ArrayList<>();

        result.add(
            Component.translatable("tooltip.gregtech6port.material_composition.header")
                .withStyle(ChatFormatting.DARK_AQUA)
        );

        for (GTMaterialAndAmount materialStack : materialComposition.materialStacks()) {
            GTMaterial material = materialStack.material();
            GTMaterialAmount amount = materialStack.amount();

            DecimalFormat decimalFormat = new DecimalFormat("0.000", DecimalFormatSymbols.getInstance(Locale.ROOT));
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

            String amountInMaterialUnits = decimalFormat.format(amount.getValueInMaterialUnits());
            String materialName = material.name();
            int meltingPoint = material.meltingPoint();
            int boilingPoint = material.boilingPoint();
            String weight = decimalFormat.format(material.getWeightForAmount(amount));

            result.add(Component.translatable(
                "tooltip.gregtech6port.material_composition.pattern",
                amountInMaterialUnits,
                Component.translatable("gregtech6port.material." + materialName)
                    .withStyle(ChatFormatting.YELLOW),
                Component.translatable("tooltip.gregtech6port.material_composition.pattern.melting_point")
                    .withStyle(ChatFormatting.AQUA),
                meltingPoint,
                Component.translatable("tooltip.gregtech6port.material_composition.pattern.kelvin")
                    .withStyle(ChatFormatting.RED),
                Component.translatable("tooltip.gregtech6port.material_composition.pattern.boiling_point")
                    .withStyle(ChatFormatting.AQUA),
                boilingPoint,
                Component.translatable("tooltip.gregtech6port.material_composition.pattern.kelvin")
                    .withStyle(ChatFormatting.RED),
                Component.translatable("tooltip.gregtech6port.material_composition.pattern.weight")
                    .withStyle(ChatFormatting.AQUA),
                weight,
                Component.translatable("tooltip.gregtech6port.material_composition.pattern.kg")
                    .withStyle(ChatFormatting.YELLOW)
            ));
        }

        return result;
    }
}
