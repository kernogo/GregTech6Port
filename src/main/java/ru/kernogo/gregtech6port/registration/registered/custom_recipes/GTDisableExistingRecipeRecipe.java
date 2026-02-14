package ru.kernogo.gregtech6port.registration.registered.custom_recipes;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import ru.kernogo.gregtech6port.registration.registered.GTRecipeSerializers;

/** Recipe that does nothing. Is only used to disable existing recipes. */
public class GTDisableExistingRecipeRecipe extends CustomRecipe {
    public GTDisableExistingRecipeRecipe() {
        super(CraftingBookCategory.MISC);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return Items.AIR.getDefaultInstance();
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return GTRecipeSerializers.DISABLE_EXISTING_RECIPE.get();
    }

    public static class Serializer implements RecipeSerializer<GTDisableExistingRecipeRecipe> {
        private static final MapCodec<GTDisableExistingRecipeRecipe> CODEC = MapCodec.unit(null);

        private static final StreamCodec<RegistryFriendlyByteBuf, GTDisableExistingRecipeRecipe> STREAM_CODEC = StreamCodec.of(
            GTDisableExistingRecipeRecipe.Serializer::toNetwork, GTDisableExistingRecipeRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<GTDisableExistingRecipeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, GTDisableExistingRecipeRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static GTDisableExistingRecipeRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            return new GTDisableExistingRecipeRecipe();
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, GTDisableExistingRecipeRecipe recipe) {}
    }
}
