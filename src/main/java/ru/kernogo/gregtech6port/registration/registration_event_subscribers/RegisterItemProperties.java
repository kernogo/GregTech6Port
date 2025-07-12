package ru.kernogo.gregtech6port.registration.registration_event_subscribers;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.registration.registered.GTItems;
import ru.kernogo.gregtech6port.utils.GTUtils;

@Slf4j
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class RegisterItemProperties {
    private RegisterItemProperties() {}

    @SubscribeEvent
    private static void registerItemProperties(FMLClientSetupEvent event) {
        event.enqueueWork(RegisterItemProperties::doRegisterItemProperties);
    }

    @SuppressWarnings("deprecation") // ItemPropertyFunction is deprecated, but we ignore that
    private static void doRegisterItemProperties() {
        ItemProperties.register(GTItems.MATCH_BOX.get(), GTUtils.modLoc("gt_use_stage"), RegisterItemProperties::lighter2StagePropFunc);
        ItemProperties.register(GTItems.LIGHTER.get(), GTUtils.modLoc("gt_use_stage"), RegisterItemProperties::lighter3StagePropFunc);
        ItemProperties.register(GTItems.SHINY_LIGHTER.get(), GTUtils.modLoc("gt_use_stage"), RegisterItemProperties::lighter3StagePropFunc);
        ItemProperties.register(GTItems.PLASTIC_LIGHTER.get(), GTUtils.modLoc("gt_use_stage"), RegisterItemProperties::lighter3StagePropFunc);
    }

    /**
     * Item Property Function for lighter-like items with 2 textures - full and partially used. <br>
     * Corresponding model is {@link ru.kernogo.gregtech6port.datagen.GTItemDatagen#lighter2StageModel}
     */
    private static float lighter2StagePropFunc(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
        Integer remainingUses = stack.get(GTDataComponentTypes.REMAINING_USES);
        Integer maxRemainingUses = stack.get(GTDataComponentTypes.MAX_REMAINING_USES);
        if (remainingUses == null || maxRemainingUses == null) {
            log.error("One of remainingUses={}, maxRemainingUses={} is null", remainingUses, maxRemainingUses);
            return 0.0f; // return some reasonable value (full)
        }
        if (remainingUses.intValue() == maxRemainingUses.intValue()) { // full
            return 0.0f;
        }
        return 0.5f; // partially used
    }

    /**
     * Item Property Function for lighter-like items with 3 textures - full, partially used, and empty. <br>
     * Corresponding model is {@link ru.kernogo.gregtech6port.datagen.GTItemDatagen#lighter3StageModel}
     */
    private static float lighter3StagePropFunc(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
        Integer remainingUses = stack.get(GTDataComponentTypes.REMAINING_USES);
        Integer maxRemainingUses = stack.get(GTDataComponentTypes.MAX_REMAINING_USES);
        if (remainingUses == null || maxRemainingUses == null) {
            log.error("One of remainingUses={}, maxRemainingUses={} is null", remainingUses, maxRemainingUses);
            return 0.0f; // return some reasonable value (full)
        }
        if (remainingUses.intValue() == 0) { // empty
            return 1.0f;
        }
        if (remainingUses.intValue() == maxRemainingUses.intValue()) { // full
            return 0.0f;
        }
        return 0.5f; // partially used
    }
}
