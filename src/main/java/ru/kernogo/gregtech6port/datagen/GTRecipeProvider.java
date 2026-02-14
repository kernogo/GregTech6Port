package ru.kernogo.gregtech6port.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import ru.kernogo.gregtech6port.datagen.recipes.crafting.GTDisabledVanillaCraftingRecipeProvider;
import ru.kernogo.gregtech6port.datagen.recipes.crafting.GTDustToDustCraftingRecipeProvider;

import java.util.concurrent.CompletableFuture;

/**
 * Recipe provider for all recipes. <br>
 * We probably can create multiple RecipeProviders, but for now we don't.
 */
public final class GTRecipeProvider extends RecipeProvider {
    private final GTDisabledVanillaCraftingRecipeProvider gtDisabledVanillaCraftingRecipeProvider;
    private final GTDustToDustCraftingRecipeProvider gtDustToDustCraftingRecipeProvider;

    public GTRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.gtDisabledVanillaCraftingRecipeProvider = new GTDisabledVanillaCraftingRecipeProvider(registries, output);
        this.gtDustToDustCraftingRecipeProvider = new GTDustToDustCraftingRecipeProvider(registries, output);
    }

    @Override
    protected void buildRecipes() {
        gtDisabledVanillaCraftingRecipeProvider.buildRecipes();
        gtDustToDustCraftingRecipeProvider.buildRecipes();
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new GTRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "GT recipes";
        }
    }
}
