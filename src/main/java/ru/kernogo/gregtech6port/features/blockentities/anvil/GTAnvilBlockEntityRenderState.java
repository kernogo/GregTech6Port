package ru.kernogo.gregtech6port.features.blockentities.anvil;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.world.item.ItemStack;

public class GTAnvilBlockEntityRenderState extends BlockEntityRenderState {
    public float rotation;
    public ItemStack leftItemStack = ItemStack.EMPTY;
    public ItemStack rightItemStack = ItemStack.EMPTY;
}
