package ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe;

import lombok.RequiredArgsConstructor;
import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.Nullable;

/**
 * Recipe builder for {@link GTOneInputModuloCraftingRecipe}. <br>
 * Code from {@link net.minecraft.data.recipes.ShapelessRecipeBuilder} was reused here.
 *
 * @see GTOneInputModuloCraftingRecipe
 */
@RequiredArgsConstructor
public class GTOneInputModuloCraftingRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final int modulo;
    private final int remainder;
    private final ItemStackTemplate result;

    private final boolean showNotification;
    private final RecipeCategory category;
    private @Nullable String group;

    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();

    /** @see GTOneInputModuloCraftingRecipe */
    public GTOneInputModuloCraftingRecipeBuilder(Ingredient ingredient,
                                                 int modulo,
                                                 int remainder,
                                                 ItemStackTemplate result,
                                                 boolean showNotification,
                                                 RecipeCategory category,
                                                 String group) {
        this.ingredient = ingredient;
        this.modulo = modulo;
        this.remainder = remainder;
        this.result = result;

        this.showNotification = showNotification;
        this.category = category;
        this.group = group;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(result);
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey) {
        GTOneInputModuloCraftingRecipe recipe = new GTOneInputModuloCraftingRecipe(
            RecipeBuilder.createCraftingCommonInfo(showNotification),
            RecipeBuilder.createCraftingBookInfo(category, group),
            ingredient,
            modulo,
            remainder,
            result
        );

        recipeOutput.accept(
            resourceKey,
            recipe,
            advancementBuilder.build(recipeOutput, resourceKey, category)
        );
    }
}
