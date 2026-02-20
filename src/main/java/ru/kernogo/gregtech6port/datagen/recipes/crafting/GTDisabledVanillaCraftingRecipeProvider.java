package ru.kernogo.gregtech6port.datagen.recipes.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.conditions.NeverCondition;
import ru.kernogo.gregtech6port.datagen.GTRecipeProvider;
import ru.kernogo.gregtech6port.registration.registered.custom_recipes.GTDisableExistingRecipeRecipe;

/** Provides recipes that will disable existing vanilla crafting recipes */
public final class GTDisabledVanillaCraftingRecipeProvider {
    private final HolderLookup.Provider registryLookup;
    private final RecipeOutput recipeOutput;
    private final HolderLookup.RegistryLookup<Item> itemsLookup;

    public GTDisabledVanillaCraftingRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput recipeOutput) {
        this.registryLookup = registryLookup;
        this.recipeOutput = recipeOutput;
        this.itemsLookup = registryLookup.lookupOrThrow(Registries.ITEM);
    }

    /** This is called from {@link GTRecipeProvider} */
    public void buildRecipes() {
        // Disable 1 Redstone Block -> 9 Redstone,
        // because it would take precedence over dust-to-dust conversion recipes
        // (1 Block -> 36 small dust, for instance)
        disableVanillaRecipe("redstone");

        // TODO: add more disabled recipes
    }

    private void disableVanillaRecipe(String name) {
        SpecialRecipeBuilder.special(craftingBookCategory -> new GTDisableExistingRecipeRecipe())
            .save(
                recipeOutput.withConditions(NeverCondition.INSTANCE),
                ResourceKey.create(Registries.RECIPE, ResourceLocation.withDefaultNamespace(name))
            );
    }
}
