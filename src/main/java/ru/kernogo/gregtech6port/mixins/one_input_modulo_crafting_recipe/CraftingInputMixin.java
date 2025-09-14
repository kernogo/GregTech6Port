package ru.kernogo.gregtech6port.mixins.one_input_modulo_crafting_recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe.GTCraftingInputWithContainerContext;

import java.util.List;

/**
 * Mixin for a custom recipe. See it for the details.
 *
 * @see ru.kernogo.gregtech6port.registration.registered.custom_recipes.one_input_module_crafting_recipe.GTOneInputModuloCraftingRecipe
 */
@Mixin(CraftingInput.class)
public class CraftingInputMixin {
    @Inject(
        method = "ofPositioned(IILjava/util/List;)Lnet/minecraft/world/item/crafting/CraftingInput$Positioned;",
        at = @At("RETURN"),
        cancellable = true
    )
    private static void ofPositioned_ChangeReturnValue(int width,
                                                       int height,
                                                       List<ItemStack> items,
                                                       CallbackInfoReturnable<CraftingInput.Positioned> cir) {
        CraftingInput.Positioned positioned = cir.getReturnValue();
        cir.setReturnValue(
            new CraftingInput.Positioned(
                new GTCraftingInputWithContainerContext(
                    positioned.input().width(),
                    positioned.input().height(),
                    positioned.input().items(),
                    width,
                    height,
                    items
                ),
                positioned.left(),
                positioned.top()
            )
        );
    }
}
