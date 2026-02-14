package ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import ru.kernogo.gregtech6port.registration.registered.GTRecipeSerializers;

import java.util.List;

/**
 * Recipe that allows only one input,
 * and works in a crafting grid only on the coordinates that match the following value: <br>
 * {@code (r + c) % modulo == remainder)}, where:
 * <ul>
 *   <li>{@code r} - row index in a crafting grid;</li>
 *   <li>{@code c} - column index in a crafting grid;</li>
 *   <li>{@code modulo}, {@code remainder} - parameters of this recipe.</li>
 * </ul>
 * A typical usage would be to set {@code modulo} to a number of recipes that use the same input item,
 * and {@code remainder} to an ordinal of this recipe amongst others with the same input item. <br>
 * This recipe only works if {@link CraftingInput} is an instance of the {@link GTCraftingInputWithContainerContext} wrapper,
 * and the wrapping is done by mixins ({@link ru.kernogo.gregtech6port.mixins.one_input_modulo_crafting_recipe}). <br>
 * Because it's a mixin hack, this recipe is not guaranteed to work correctly in all cases, so be careful when using it. <br>
 * TODO: does not work correctly with vanilla crafter because of vanilla crafter's caching
 */
@Slf4j
public class GTOneInputModuloCraftingRecipe extends CustomRecipe {
    private final String group;
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int modulo;
    private final int remainder;

    public GTOneInputModuloCraftingRecipe(String group,
                                          CraftingBookCategory category,
                                          Ingredient ingredient,
                                          ItemStack result,
                                          int modulo,
                                          int remainder) {
        super(category);
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.modulo = modulo;
        this.remainder = remainder;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (!(input instanceof GTCraftingInputWithContainerContext inputWithContext)) {
            // If CraftingInput is not wrapped, we don't do anything.
            log.warn("CraftingInput is not wrapped for GTOneInputModuloCraftingRecipe. CraftingInput={}", input,
                new RuntimeException("Exception thrown for stack trace purposes"));
            return false;
        }

        if (inputWithContext.ingredientCount() != 1) {
            return false;
        }

        List<ItemStack> stacksInCraftingGrid = inputWithContext.getContainerItems();

        for (int r = 0; r < inputWithContext.getContainerHeight(); r++) {
            for (int c = 0; c < inputWithContext.getContainerWidth(); c++) {
                int index = r * inputWithContext.getContainerWidth() + c;

                ItemStack stack = stacksInCraftingGrid.get(index);

                if (stack.isEmpty()) {
                    // Empty stacks indicate empty places in the crafting grid
                    continue;
                }

                // We already checked that there is only one item, so we just use it right away

                if ((r + c) % modulo != remainder) {
                    return false;
                }

                return ingredient.test(stack);
            }
        }

        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
            new ShapelessCraftingRecipeDisplay(
                List.of(this.ingredient.display()),
                new SlotDisplay.ItemStackSlotDisplay(this.result),
                new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)
            )
        );
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return GTRecipeSerializers.ONE_INPUT_MODULO_CRAFTING.get();
    }

    @Override
    public String group() {
        return this.group;
    }

    public static class Serializer implements RecipeSerializer<GTOneInputModuloCraftingRecipe> {
        private static final MapCodec<GTOneInputModuloCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                    CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(GTOneInputModuloCraftingRecipe::category),
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                    Codec.INT.fieldOf("modulo").forGetter(recipe -> recipe.modulo),
                    Codec.INT.fieldOf("remainder").forGetter(recipe -> recipe.remainder)
                )
                .apply(builder, GTOneInputModuloCraftingRecipe::new)
        );

        private static final StreamCodec<RegistryFriendlyByteBuf, GTOneInputModuloCraftingRecipe> STREAM_CODEC = StreamCodec.of(
            GTOneInputModuloCraftingRecipe.Serializer::toNetwork, GTOneInputModuloCraftingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<GTOneInputModuloCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, GTOneInputModuloCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static GTOneInputModuloCraftingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            CraftingBookCategory category = buffer.readEnum(CraftingBookCategory.class);
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            int modulo = buffer.readVarInt();
            int remainder = buffer.readVarInt();

            return new GTOneInputModuloCraftingRecipe(group, category, ingredient, result, modulo, remainder);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, GTOneInputModuloCraftingRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category());
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeVarInt(recipe.modulo);
            buffer.writeVarInt(recipe.remainder);
        }
    }
}
