package ru.kernogo.gregtech6port.datagen.recipes.crafting;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.conditions.FalseCondition;
import ru.kernogo.gregtech6port.registration.registered.custom_recipes.GTDisableExistingRecipeRecipe;

/**
 * Datagen of recipes that will disable existing vanilla crafting recipes <br>
 * TODO: make it work with config
 */
public final class GTDisabledVanillaCraftingRecipeDatagen {
    /** This is called {@link ru.kernogo.gregtech6port.datagen.GTRecipeDatagen} */
    public void buildRecipes(RecipeOutput recipeOutput) {
        // Disable 1 Redstone Block -> 9 Redstone,
        // because it would take precedence over dust-to-dust conversion recipes
        // (1 Block -> 36 small dust, for instance)
        disableVanillaRecipe(recipeOutput, "redstone");

        // TODO: add more disabled recipes
    }

    private static void disableVanillaRecipe(RecipeOutput recipeOutput, String name) {
        SpecialRecipeBuilder.special(craftingBookCategory -> new GTDisableExistingRecipeRecipe())
            .save(recipeOutput.withConditions(FalseCondition.INSTANCE), ResourceLocation.withDefaultNamespace(name));
    }
}
