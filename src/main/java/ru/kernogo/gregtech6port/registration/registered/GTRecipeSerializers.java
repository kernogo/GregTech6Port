package ru.kernogo.gregtech6port.registration.registered;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import ru.kernogo.gregtech6port.registration.GTRegisters;
import ru.kernogo.gregtech6port.registration.registered.custom_recipes.GTDisableExistingRecipeRecipe;
import ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe.GTOneInputModuloCraftingRecipe;

public final class GTRecipeSerializers {
    private GTRecipeSerializers() {}

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GTDisableExistingRecipeRecipe>> DISABLE_EXISTING_RECIPE =
        GTRegisters.RECIPE_SERIALIZERS.register(
            "disable_existing_recipe", () -> GTDisableExistingRecipeRecipe.SERIALIZER
        );
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GTOneInputModuloCraftingRecipe>> ONE_INPUT_MODULO_CRAFTING =
        GTRegisters.RECIPE_SERIALIZERS.register(
            "one_input_modulo_crafting", () -> GTOneInputModuloCraftingRecipe.SERIALIZER
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
