package ru.kernogo.gregtech6port.datagen.recipes.crafting;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.IntersectionIngredient;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTDominantMaterialKindItemAccessService;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;
import ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe.GTOneInputModuloCraftingRecipeBuilder;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;
import ru.kernogo.gregtech6port.utils.GTUtils;

import java.util.Arrays;

/**
 * Dust to dust conversion crafting recipes, for example: <br>
 * 1 Dust -> 4 Small Dust <br>
 * 8 Div72 Dust -> 1 Tiny Dust
 */
public final class GTDustToDustCraftingRecipeDatagen {
    private final GTDominantMaterialKindItemAccessService dominantMaterialKindItemAccessService =
        GTDominantMaterialKindItemAccessService.getInstance();

    /** This is called {@link ru.kernogo.gregtech6port.datagen.GTRecipeDatagen} */
    public void buildRecipes(RecipeOutput recipeOutput) {
        // Go over all Material-Kind Item definitions.
        // For each dust: datagen dust-to-dust crafting recipes with that dust as an output.

        Registry<GTMaterial> materialsRegistry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .registryOrThrow(GTCustomRegistries.MATERIALS.key());
        Registry<GTMaterialThingKind> kindsRegistry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .registryOrThrow(GTCustomRegistries.MATERIAL_THING_KINDS.key());

        for (GTMaterial material : materialsRegistry) {
            for (GTMaterialThingKind kind : kindsRegistry) {
                if (kind.equals(GTMaterialThingKinds.DUST)) {
                    shapelessDustRecipe(recipeOutput, 4, GTMaterialThingKinds.SMALL_DUST, 1, material, kind);
                    shapelessDustRecipe(recipeOutput, 8, GTMaterialThingKinds.SMALL_DUST, 2, material, kind);
                    shapelessDustRecipe(recipeOutput, 9, GTMaterialThingKinds.TINY_DUST, 1, material, kind);
                    oneInputModuloCraftingRecipe(recipeOutput, GTMaterialThingKinds.DUST_BLOCK, 9, material, kind, 2, 1);
                }
                if (kind.equals(GTMaterialThingKinds.SMALL_DUST)) {
                    oneInputModuloCraftingRecipe(recipeOutput, GTMaterialThingKinds.DUST, 4, material, kind, 2, 1);
                    shapelessDustRecipe(recipeOutput, 9, GTMaterialThingKinds.DUST_BLOCK, 9, material, kind);
                    shapelessDustRecipe(recipeOutput, 8, GTMaterialThingKinds.DIV72_DUST, 1, material, kind);
                    oneInputModuloCraftingRecipe(recipeOutput, GTMaterialThingKinds.DUST_BLOCK, 36, material, kind, 2, 0);
                }
                if (kind.equals(GTMaterialThingKinds.TINY_DUST)) {
                    oneInputModuloCraftingRecipe(recipeOutput, GTMaterialThingKinds.DUST, 9, material, kind, 2, 0);
                    shapelessDustRecipe(recipeOutput, 8, GTMaterialThingKinds.DIV72_DUST, 1, material, kind);
                }
                if (kind.equals(GTMaterialThingKinds.DIV72_DUST)) {
                    shapelessDustRecipe(recipeOutput, 1, GTMaterialThingKinds.SMALL_DUST, 18, material, kind);
                    shapelessDustRecipe(recipeOutput, 1, GTMaterialThingKinds.TINY_DUST, 8, material, kind);
                }
                if (kind.equals(GTMaterialThingKinds.DUST_BLOCK)) {
                    shapelessDustRecipe(recipeOutput, 9, GTMaterialThingKinds.DUST, 1, material, kind);
                }
            }
        }
    }

    private void shapelessDustRecipe(RecipeOutput recipeOutput,
                                     int inputCount,
                                     GTMaterialThingKind inputKind,
                                     int outputCount,
                                     GTMaterial outputMaterial,
                                     GTMaterialThingKind outputKind) {
        Ingredient inputIngredient = IntersectionIngredient.of(
            Ingredient.of(outputMaterial.itemTag()),
            Ingredient.of(inputKind.itemTag())
        );

        Item outputItem = dominantMaterialKindItemAccessService.getDominantItemByMaterialAndKind(
            outputMaterial, outputKind
        );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, outputItem, outputCount)
            .requires(inputIngredient, inputCount)
            .unlockedBy("has_item", RecipeProvider.inventoryTrigger( // Access transformer allows this call
                ItemPredicate.Builder.item().of(
                    Arrays.stream(inputIngredient.getItems()).map(ItemStack::getItem).toArray(Item[]::new)
                )
            ))
            .save(
                recipeOutput,
                GTUtils.modLoc(
                    "%s/%s_%s_to_%s_%s"
                        .formatted(outputMaterial.name(),
                            inputCount,
                            inputKind.name(),
                            outputCount,
                            outputKind.name()
                        )
                )
            );
    }

    private void oneInputModuloCraftingRecipe(RecipeOutput recipeOutput,
                                              GTMaterialThingKind inputKind,
                                              int outputCount,
                                              GTMaterial outputMaterial,
                                              GTMaterialThingKind outputKind,
                                              int modulo,
                                              int remainder) {
        Ingredient inputIngredient = IntersectionIngredient.of(
            Ingredient.of(outputMaterial.itemTag()),
            Ingredient.of(inputKind.itemTag())
        );

        Item outputItem = dominantMaterialKindItemAccessService.getDominantItemByMaterialAndKind(
            outputMaterial, outputKind
        );

        ItemStack outputItemStack = outputItem.getDefaultInstance();
        outputItemStack.setCount(outputCount);

        new GTOneInputModuloCraftingRecipeBuilder(
            RecipeCategory.MISC,
            outputItemStack,
            modulo,
            remainder
        )
            .requires(inputIngredient)
            .unlockedBy("has_item", RecipeProvider.inventoryTrigger( // Access transformer allows this call
                ItemPredicate.Builder.item().of(
                    Arrays.stream(inputIngredient.getItems()).map(ItemStack::getItem).toArray(Item[]::new)
                )
            ))
            .save(
                recipeOutput,
                GTUtils.modLoc(
                    "%s/1_%s_to_%s_%s"
                        .formatted(
                            outputMaterial.name(),
                            inputKind.name(),
                            outputCount,
                            outputKind.name()
                        )
                )
            );
    }
}
