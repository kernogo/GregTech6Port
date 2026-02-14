package ru.kernogo.gregtech6port.datagen.recipes.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.IntersectionIngredient;
import ru.kernogo.gregtech6port.datagen.GTRecipeProvider;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterial;
import ru.kernogo.gregtech6port.features.behaviors.item_materials.GTMaterialThingKind;
import ru.kernogo.gregtech6port.features.material_kind_things.items.GTDominantMaterialKindItemAccessService;
import ru.kernogo.gregtech6port.registration.registered.GTCustomRegistries;
import ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe.GTOneInputModuloCraftingRecipeBuilder;
import ru.kernogo.gregtech6port.registration.registered.materials.GTMaterialThingKinds;
import ru.kernogo.gregtech6port.utils.GTUtils;

/**
 * Dust to dust conversion crafting recipes, for example: <br>
 * 1 Dust -> 4 Small Dust <br>
 * 8 Div72 Dust -> 1 Tiny Dust
 */
public final class GTDustToDustCraftingRecipeProvider {
    private final HolderLookup.Provider registryLookup;
    private final RecipeOutput recipeOutput;
    private final HolderLookup.RegistryLookup<Item> itemsLookup;

    private final GTDominantMaterialKindItemAccessService dominantMaterialKindItemAccessService =
        GTDominantMaterialKindItemAccessService.getInstance();

    public GTDustToDustCraftingRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput recipeOutput) {
        this.registryLookup = registryLookup;
        this.recipeOutput = recipeOutput;
        this.itemsLookup = registryLookup.lookupOrThrow(Registries.ITEM);
    }

    /** This is called from {@link GTRecipeProvider} */
    public void buildRecipes() {
        // Go over all Material-Kind Item definitions.
        // For each dust: datagen dust-to-dust crafting recipes with that dust as an output.

        Registry<GTMaterial> materialsRegistry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .lookupOrThrow(GTCustomRegistries.MATERIALS.key());
        Registry<GTMaterialThingKind> kindsRegistry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            .lookupOrThrow(GTCustomRegistries.MATERIAL_THING_KINDS.key());

        for (GTMaterial material : materialsRegistry) {
            for (GTMaterialThingKind kind : kindsRegistry) {
                if (kind.equals(GTMaterialThingKinds.DUST)) {
                    shapelessDustRecipe(4, GTMaterialThingKinds.SMALL_DUST, 1, material, kind);
                    shapelessDustRecipe(8, GTMaterialThingKinds.SMALL_DUST, 2, material, kind);
                    shapelessDustRecipe(9, GTMaterialThingKinds.TINY_DUST, 1, material, kind);
                    oneInputModuloCraftingRecipe(GTMaterialThingKinds.DUST_BLOCK, 9, material, kind, 2, 1);
                }
                if (kind.equals(GTMaterialThingKinds.SMALL_DUST)) {
                    oneInputModuloCraftingRecipe(GTMaterialThingKinds.DUST, 4, material, kind, 2, 1);
                    oneInputModuloCraftingRecipe(GTMaterialThingKinds.DUST_BLOCK, 36, material, kind, 2, 0);
                }
                if (kind.equals(GTMaterialThingKinds.TINY_DUST)) {
                    oneInputModuloCraftingRecipe(GTMaterialThingKinds.DUST, 9, material, kind, 2, 0);
                    shapelessDustRecipe(8, GTMaterialThingKinds.DIV72_DUST, 1, material, kind);
                }
                if (kind.equals(GTMaterialThingKinds.DIV72_DUST)) {
                    shapelessDustRecipe(1, GTMaterialThingKinds.SMALL_DUST, 18, material, kind);
                    shapelessDustRecipe(1, GTMaterialThingKinds.TINY_DUST, 8, material, kind);
                }
                if (kind.equals(GTMaterialThingKinds.DUST_BLOCK)) {
                    shapelessDustRecipe(9, GTMaterialThingKinds.DUST, 1, material, kind);
                }
            }
        }
    }

    private void shapelessDustRecipe(int inputCount,
                                     GTMaterialThingKind inputKind,
                                     int outputCount,
                                     GTMaterial outputMaterial,
                                     GTMaterialThingKind outputKind) {
        Ingredient inputIngredient = IntersectionIngredient.of(
            Ingredient.of(itemsLookup.getOrThrow(outputMaterial.itemTag())),
            Ingredient.of(itemsLookup.getOrThrow(inputKind.itemTag()))
        );

        Item outputItem = dominantMaterialKindItemAccessService.getDominantItemByMaterialAndKind(
            outputMaterial, outputKind
        );

        ShapelessRecipeBuilder.shapeless(itemsLookup, RecipeCategory.MISC, outputItem, outputCount)
            .requires(inputIngredient, inputCount)
            .unlockedBy("has_item", GTUtils.dummyUnlockedByTrigger(itemsLookup))
            .save(
                recipeOutput,
                GTUtils.modLocRecipeResourceKey(
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

    private void oneInputModuloCraftingRecipe(GTMaterialThingKind inputKind,
                                              int outputCount,
                                              GTMaterial outputMaterial,
                                              GTMaterialThingKind outputKind,
                                              int modulo,
                                              int remainder) {
        Ingredient inputIngredient = IntersectionIngredient.of(
            Ingredient.of(itemsLookup.getOrThrow(outputMaterial.itemTag())),
            Ingredient.of(itemsLookup.getOrThrow(inputKind.itemTag()))
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
            .unlockedBy("has_item", GTUtils.dummyUnlockedByTrigger(itemsLookup))
            .save(
                recipeOutput,
                GTUtils.modLocRecipeResourceKey(
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
