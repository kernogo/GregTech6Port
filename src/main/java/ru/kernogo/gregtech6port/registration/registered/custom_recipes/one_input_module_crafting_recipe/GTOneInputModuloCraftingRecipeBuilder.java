package ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Recipe builder for {@link GTOneInputModuloCraftingRecipe}. <br>
 * Code from {@link net.minecraft.data.recipes.ShapelessRecipeBuilder} was reused here.
 *
 * @see GTOneInputModuloCraftingRecipe
 */
public class GTOneInputModuloCraftingRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final ItemStack resultStack;
    private final int modulo;
    private final int remainder;

    private @Nullable String group;
    private @Nullable Ingredient ingredient;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    /** @see GTOneInputModuloCraftingRecipe */
    public GTOneInputModuloCraftingRecipeBuilder(RecipeCategory category,
                                                 ItemStack resultStack,
                                                 int modulo,
                                                 int remainder) {
        this.category = category;
        this.resultStack = resultStack;
        this.modulo = modulo;
        this.remainder = remainder;
    }

    public GTOneInputModuloCraftingRecipeBuilder requires(ItemLike item) {
        this.ingredient = Ingredient.of(item);
        return this;
    }

    public GTOneInputModuloCraftingRecipeBuilder requires(Ingredient ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public Item getResult() {
        return this.resultStack.getItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey) {
        this.ensureValid(resourceKey);
        Advancement.Builder advancementBuilder = recipeOutput.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
            .rewards(AdvancementRewards.Builder.recipe(resourceKey))
            .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);

        GTOneInputModuloCraftingRecipe recipe = new GTOneInputModuloCraftingRecipe(
            Objects.requireNonNullElse(this.group, ""),
            RecipeBuilder.determineBookCategory(this.category),
            Objects.requireNonNull(this.ingredient),
            this.resultStack,
            this.modulo,
            this.remainder
        );

        recipeOutput.accept(
            resourceKey,
            recipe,
            advancementBuilder.build(resourceKey.location().withPrefix("recipes/" + this.category.getFolderName() + "/"))
        );
    }

    /** Makes sure that this recipe is valid and obtainable. */
    private void ensureValid(ResourceKey<Recipe<?>> recipe) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipe.location());
        }
    }
}
