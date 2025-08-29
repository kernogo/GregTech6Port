package ru.kernogo.gregtech6port.features.behaviors.item_with_uses;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.registration.registered.GTItems;
import ru.kernogo.gregtech6port.utils.GTUtils;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

@Slf4j
public final class GTItemWithUsesItemPropertiesRegistration {
    private GTItemWithUsesItemPropertiesRegistration() {}

    private static final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();

    /** This gets subscribed with the modBus in another class */
    public static void registerItemProperties(FMLClientSetupEvent event) {
        event.enqueueWork(GTItemWithUsesItemPropertiesRegistration::doRegisterItemProperties);
    }

    private static void doRegisterItemProperties() {
        register2StageVariantFunc(GTItems.MATCH_BOX);
        register3StageVariantFunc(GTItems.LIGHTER);
        register3StageVariantFunc(GTItems.SHINY_LIGHTER);
        register3StageVariantFunc(GTItems.PLASTIC_LIGHTER);

        register2StageVariantFunc(GTItems.WHITE_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.ORANGE_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.MAGENTA_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.LIGHT_BLUE_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.YELLOW_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.LIME_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.PINK_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.GRAY_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.LIGHT_GRAY_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.CYAN_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.PURPLE_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.BLUE_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.BROWN_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.GREEN_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.RED_PAINT_SPRAY_CAN);
        register2StageVariantFunc(GTItems.BLACK_PAINT_SPRAY_CAN);

        register2StageVariantFunc(GTItems.PAINT_REMOVAL_SPRAY_CAN);
    }

    /** @see GTItemWithUsesItemPropertiesRegistration#make2StageFunc */
    @SuppressWarnings("deprecation") // ItemPropertyFunction is deprecated, but we ignore that
    private static void register2StageVariantFunc(DeferredItem<Item> deferredItem) {
        ItemProperties.register(deferredItem.get(),
            GTUtils.modLoc("use_stage"),
            GTItemWithUsesItemPropertiesRegistration::make2StageFunc);
    }

    /** @see GTItemWithUsesItemPropertiesRegistration#make3StageFunc */
    @SuppressWarnings("deprecation") // ItemPropertyFunction is deprecated, but we ignore that
    private static void register3StageVariantFunc(DeferredItem<Item> deferredItem) {
        ItemProperties.register(deferredItem.get(),
            GTUtils.modLoc("use_stage"),
            GTItemWithUsesItemPropertiesRegistration::make3StageFunc);
    }

    /**
     * Item Property Function for items with uses with 2 variant textures - full and partially used. <br>
     * Corresponding model is {@link GTItemWithUsesVariantModelMaker#make2StageVariantModel}
     */
    private static float make2StageFunc(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
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
     * Corresponding model is {@link GTItemWithUsesVariantModelMaker#make3StageVariantModel}
     */
    private static float make3StageFunc(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
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
