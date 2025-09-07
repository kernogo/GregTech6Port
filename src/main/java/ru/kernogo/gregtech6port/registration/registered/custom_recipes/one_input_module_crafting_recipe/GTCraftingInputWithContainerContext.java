package ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe;

import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

import java.util.List;

/**
 * Mixins (in package {@link ru.kernogo.gregtech6port.mixins.one_input_modulo_crafting_recipe})
 * replace normal {@link CraftingInput} with this wrapper in some Minecraft's classes. <br>
 * This wrapper is still a normal {@link CraftingInput}, so this wrapping should be transparent. <br>
 *
 * @see GTOneInputModuloCraftingRecipe
 */
public final class GTCraftingInputWithContainerContext extends CraftingInput {
    /** Actual width of a crafting grid */
    @Getter
    private final int containerWidth;
    /** Actual height of a crafting grid */
    @Getter
    private final int containerHeight;
    /** All Item Stacks in a crafting grid (including the empty stacks) */
    @Getter
    private final List<ItemStack> containerItems;

    public GTCraftingInputWithContainerContext(int width,
                                               int height,
                                               List<ItemStack> item,
                                               int containerWidth,
                                               int containerHeight,
                                               List<ItemStack> containerItems) {
        // Access transformer makes this constructor public
        super(width, height, item);
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.containerItems = containerItems;
    }
}
