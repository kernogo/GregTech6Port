package ru.kernogo.gregtech6port.registration.registration;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesData;
import ru.kernogo.gregtech6port.registration.registered.GTItems;
import ru.kernogo.gregtech6port.utils.GTUtils;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

@Slf4j
public final class GTItemPropertiesRegistration {
    private GTItemPropertiesRegistration() {}

    private static final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();

    /** This gets subscribed with the modBus in another class */
    public static void registerItemProperties(FMLClientSetupEvent event) {
        event.enqueueWork(GTItemPropertiesRegistration::doRegisterItemProperties);
    }

    private static void doRegisterItemProperties() {
        registerItemWithUses2StageFunc(GTItems.MATCH_BOX);
        registerItemWithUses3StageFunc(GTItems.LIGHTER);
        registerItemWithUses3StageFunc(GTItems.SHINY_LIGHTER);
        registerItemWithUses3StageFunc(GTItems.PLASTIC_LIGHTER);

        registerItemWithUses2StageFunc(GTItems.WHITE_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.ORANGE_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.MAGENTA_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.LIGHT_BLUE_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.YELLOW_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.LIME_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.PINK_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.GRAY_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.LIGHT_GRAY_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.CYAN_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.PURPLE_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.BLUE_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.BROWN_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.GREEN_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.RED_PAINT_SPRAY_CAN);
        registerItemWithUses2StageFunc(GTItems.BLACK_PAINT_SPRAY_CAN);

        registerItemWithUses2StageFunc(GTItems.PAINT_REMOVAL_SPRAY_CAN);
    }

    /** @see GTItemPropertiesRegistration#makeItemWithUses2StageFunc */
    @SuppressWarnings("deprecation") // ItemPropertyFunction is deprecated, but we ignore that
    private static void registerItemWithUses2StageFunc(DeferredItem<Item> deferredItem) {
        ItemProperties.register(deferredItem.get(),
            GTUtils.modLoc("use_stage"),
            GTItemPropertiesRegistration::makeItemWithUses2StageFunc);
    }

    /** @see GTItemPropertiesRegistration#makeItemWithUses3StageFunc */
    @SuppressWarnings("deprecation") // ItemPropertyFunction is deprecated, but we ignore that
    private static void registerItemWithUses3StageFunc(DeferredItem<Item> deferredItem) {
        ItemProperties.register(deferredItem.get(),
            GTUtils.modLoc("use_stage"),
            GTItemPropertiesRegistration::makeItemWithUses3StageFunc);
    }

    /**
     * Item Property Function for items with uses with 2 variant textures - full and partially used. <br>
     * Corresponding model is {@link ru.kernogo.gregtech6port.datagen.item_model.GTItemWithUsesVariantItemModelMaker#makeItemWithUses2StageModel}
     */
    private static float makeItemWithUses2StageFunc(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
        try {
            GTItemWithUsesData itemWithUsesData = itemWithUsesBehavior.validateAndGetItemWithUsesData(stack);
            int remainingUses = itemWithUsesData.remainingUses();
            int maxRemainingUses = itemWithUsesData.maxRemainingUses();
            if (remainingUses == maxRemainingUses) { // full
                return 0.0f;
            }
            return 0.5f; // partially used
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Unexpected validation error on item with uses ItemStack={}", stack, e);
        }

        return 0.0f; // return some reasonable value on error (full)
    }

    /**
     * Item Property Function for items with uses items with 3 variant textures - full, partially used, and empty. <br>
     * Corresponding model is {@link ru.kernogo.gregtech6port.datagen.item_model.GTItemWithUsesVariantItemModelMaker#makeItemWithUses3StageModel}
     */
    private static float makeItemWithUses3StageFunc(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
        try {
            GTItemWithUsesData itemWithUsesData = itemWithUsesBehavior.validateAndGetItemWithUsesData(stack);
            int remainingUses = itemWithUsesData.remainingUses();
            int maxRemainingUses = itemWithUsesData.maxRemainingUses();
            if (remainingUses == 0) { // empty
                return 1.0f;
            }
            if (remainingUses == maxRemainingUses) { // full
                return 0.0f;
            }
            return 0.5f; // partially used
        } catch (GTUnexpectedValidationFailException e) {
            log.error("Unexpected validation error on item with uses ItemStack={}", stack, e);
        }

        return 0.0f; // return some reasonable value on error (full)
    }
}
