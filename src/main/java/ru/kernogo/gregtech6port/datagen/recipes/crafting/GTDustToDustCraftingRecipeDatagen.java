package ru.kernogo.gregtech6port.datagen.recipes.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.material_kind_items.GTMaterialKindItemDefinition;
import ru.kernogo.gregtech6port.features.material_kind_items.GTMaterialKindItemDefinitionService;
import ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe.GTOneInputModuloCraftingRecipeBuilder;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;
import ru.kernogo.gregtech6port.utils.GTUtils;

import java.util.concurrent.CompletableFuture;

/**
 * Dust to dust conversion crafting recipes, for example: <br>
 * 1 Dust -> 4 Small Dust <br>
 * 8 Div72 Dust -> 1 Tiny Dust
 */
public class GTDustToDustCraftingRecipeDatagen extends RecipeProvider {
    private final GTMaterialKindItemDefinitionService materialKindItemDefinitionService =
        GTMaterialKindItemDefinitionService.getInstance();

    public GTDustToDustCraftingRecipeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Go over all Material-Kind Item definitions.
        // For each dust: datagen dust-to-dust crafting recipes with that dust as an output.

        for (GTMaterialKindItemDefinition definition : materialKindItemDefinitionService.getDefinitions()) {
            GTMaterialThingKind kind = definition.kind();

            if (kind.equals(GTMaterialThingKinds.DUST)) {
                shapelessDustRecipe(recipeOutput, 4, GTMaterialThingKinds.SMALL_DUST, 1, definition);
                shapelessDustRecipe(recipeOutput, 8, GTMaterialThingKinds.SMALL_DUST, 2, definition);
                shapelessDustRecipe(recipeOutput, 9, GTMaterialThingKinds.TINY_DUST, 1, definition);
                // TODO: dust block -> 9 dust
            }
            if (kind.equals(GTMaterialThingKinds.SMALL_DUST)) {
                oneInputModuloCraftingRecipe(recipeOutput, GTMaterialThingKinds.DUST, 4, definition, 2, 1);
                // TODO: dust block -> 36 small dust
            }
            if (kind.equals(GTMaterialThingKinds.TINY_DUST)) {
                oneInputModuloCraftingRecipe(recipeOutput, GTMaterialThingKinds.DUST, 9, definition, 2, 0);
                shapelessDustRecipe(recipeOutput, 8, GTMaterialThingKinds.DIV72_DUST, 1, definition);
            }
            if (kind.equals(GTMaterialThingKinds.DIV72_DUST)) {
                shapelessDustRecipe(recipeOutput, 1, GTMaterialThingKinds.SMALL_DUST, 18, definition);
                shapelessDustRecipe(recipeOutput, 1, GTMaterialThingKinds.TINY_DUST, 8, definition);
            }
        }
    }

    private void shapelessDustRecipe(RecipeOutput recipeOutput,
                                     int inputCount,
                                     GTMaterialThingKind inputKind,
                                     int outputCount,
                                     GTMaterialKindItemDefinition outputDefinition) {
        DeferredItem<Item> inputItem = materialKindItemDefinitionService.getDefinitionByMaterialAndKind(
            outputDefinition.material(), inputKind
        ).deferredItem();

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, outputDefinition.deferredItem(), outputCount)
            .requires(inputItem, inputCount)
            .unlockedBy("has_item", has(inputItem))
            .save(
                recipeOutput,
                GTUtils.modLoc(
                    "%s/%s_%s_to_%s_%s"
                        .formatted(outputDefinition.material().name(),
                            inputCount,
                            inputKind.name(),
                            outputCount,
                            outputDefinition.kind().name()
                        )
                )
            );
    }

    private void oneInputModuloCraftingRecipe(RecipeOutput recipeOutput,
                                              GTMaterialThingKind inputKind,
                                              int outputCount,
                                              GTMaterialKindItemDefinition outputDefinition,
                                              int modulo,
                                              int remainder) {
        DeferredItem<Item> inputItem = materialKindItemDefinitionService.getDefinitionByMaterialAndKind(
            outputDefinition.material(), inputKind
        ).deferredItem();

        new GTOneInputModuloCraftingRecipeBuilder(
            RecipeCategory.MISC,
            outputDefinition.deferredItem().toStack(outputCount),
            modulo,
            remainder
        )
            .requires(inputItem)
            .unlockedBy("has_item", has(inputItem))
            .save(
                recipeOutput,
                GTUtils.modLoc(
                    "%s/1_%s_to_%s_%s"
                        .formatted(
                            outputDefinition.material().name(),
                            inputKind.name(),
                            outputCount,
                            outputDefinition.kind().name()
                        )
                )
            );
    }
}
