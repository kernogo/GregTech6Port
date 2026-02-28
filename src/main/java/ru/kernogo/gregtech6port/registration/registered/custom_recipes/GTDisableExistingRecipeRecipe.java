package ru.kernogo.gregtech6port.registration.registered.custom_recipes;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import ru.kernogo.gregtech6port.registration.registered.GTRecipeSerializers;

/** Recipe that does nothing. Is only used to disable existing recipes. */
public class GTDisableExistingRecipeRecipe extends CustomRecipe {
    public static final MapCodec<GTDisableExistingRecipeRecipe> MAP_CODEC =
        MapCodec.unit(new GTDisableExistingRecipeRecipe());

    public static final StreamCodec<RegistryFriendlyByteBuf, GTDisableExistingRecipeRecipe> STREAM_CODEC =
        StreamCodec.unit(new GTDisableExistingRecipeRecipe());

    public static final RecipeSerializer<GTDisableExistingRecipeRecipe> SERIALIZER =
        new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return Items.AIR.getDefaultInstance();
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return GTRecipeSerializers.DISABLE_EXISTING_RECIPE.get();
    }
}
