package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import ru.kernogo.gregtech6port.datagen.recipes.crafting.GTDisabledVanillaCraftingRecipeDatagen;
import ru.kernogo.gregtech6port.datagen.recipes.crafting.GTDustToDustCraftingRecipeDatagen;

import java.util.concurrent.CompletableFuture;

/**
 * Datagen for all recipes. <br>
 * We don't create multiple RecipeProviders because
 * the {@link RecipeProvider#getName()} is final (maybe for a good reason, maybe not). <br>
 * We could use Access Transformers, but we take the safer approach of using only one RecipeProvider.
 */
public final class GTRecipeDatagen extends RecipeProvider {
    private final GTDisabledVanillaCraftingRecipeDatagen gtDisabledVanillaCraftingRecipeDatagen =
        new GTDisabledVanillaCraftingRecipeDatagen();
    private final GTDustToDustCraftingRecipeDatagen gtDustToDustCraftingRecipeDatagen =
        new GTDustToDustCraftingRecipeDatagen();

    public GTRecipeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        gtDisabledVanillaCraftingRecipeDatagen.buildRecipes(recipeOutput);
        gtDustToDustCraftingRecipeDatagen.buildRecipes(recipeOutput);
    }
}
