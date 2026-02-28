package ru.kernogo.gregtech6port.features.blockentities.anvil;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;

import java.util.List;
import java.util.stream.Stream;

/** Common information about the GT Anvil */
public final class GTAnvilBlockCommon {
    private GTAnvilBlockCommon() {}

    public static List<GTMaterialThingKind> getSupportedThingOnAnvilKinds() {
        return List.of(
            GTMaterialThingKinds.INGOT, // TODO validate the list with the GT Anvil recipes
            GTMaterialThingKinds.PLATE,
            GTMaterialThingKinds.ROD,
            GTMaterialThingKinds.CHUNK,
            GTMaterialThingKinds.RING,
            GTMaterialThingKinds.CHIPPED_GEM,
            GTMaterialThingKinds.FLAWED_GEM,
            GTMaterialThingKinds.GEM,
            GTMaterialThingKinds.FLAWLESS_GEM,
            GTMaterialThingKinds.EXQUISITE_GEM,
            GTMaterialThingKinds.LEGENDARY_GEM
            // TODO: ORE and ROCK
        );
    }

    public static boolean isHammerItem(ItemStack itemStack) {
        return itemStack.getItem() == Items.STICK; // TODO change this to a hammer tool item
    }

    public static boolean isSupportedThingOnAnvilExceptHammer(ItemStack itemStack) {
        List<GTMaterialThingKind> supportedKinds = getSupportedThingOnAnvilKinds();
        Stream<TagKey<Item>> supportedTags = supportedKinds.stream().map(GTMaterialThingKind::itemTag);

        return supportedTags.anyMatch(
            supportedTag -> itemStack.tags().anyMatch(tag -> tag.equals(supportedTag))
        );
    }
}
