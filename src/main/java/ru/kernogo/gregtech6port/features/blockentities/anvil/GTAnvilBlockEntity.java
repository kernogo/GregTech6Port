package ru.kernogo.gregtech6port.features.blockentities.anvil;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringCapability;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringData;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringNbtTagSaveLoader;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.utils.GTUtils;

@Slf4j
public class GTAnvilBlockEntity extends BlockEntity {
    private @Nullable GTTintColoringData tintColoringData;
    private final GTTintColoringNbtTagSaveLoader tintColoringNbtTagSaveLoader = new GTTintColoringNbtTagSaveLoader();

    private final ItemStacksResourceHandler thingsOnAnvilHandler = new ItemStacksResourceHandler(2);

    public GTAnvilBlockEntity(BlockPos pos, BlockState blockState) {
        super(GTBlockEntityTypes.ANVIL.get(), pos, blockState);
    }

    public void addLeftStack(ItemStack stack) {
        addStack(stack, 0);
    }

    public void addRightStack(ItemStack stack) {
        addStack(stack, 1);
    }

    public void removeLeftStackIntoPlayerInventory(Player player) {
        removeStackIntoPlayerInventory(player, 0);
    }

    public void removeRightStackIntoPlayerInventory(Player player) {
        removeStackIntoPlayerInventory(player, 1);
    }

    public ItemStack getLeftStack() {
        return getStack(0);
    }

    public ItemStack getRightStack() {
        return getStack(1);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        super.preRemoveSideEffects(pos, state);
        if (this.level == null) {
            log.warn("Could not drop contents of GT Anvil because Level is null");
            return;
        }
        GTUtils.dropHandlerContents(level, pos, thingsOnAnvilHandler);
    }

    public void spreadThingsOnAnvilLeftAndRight(Player player) {
        ItemResource leftResource = thingsOnAnvilHandler.getResource(0);
        ItemResource rightResource = thingsOnAnvilHandler.getResource(1);
        if (leftResource.isEmpty() && rightResource.isEmpty() || !leftResource.isEmpty() && !rightResource.isEmpty()) {
            log.warn("Tried to spread resources when either: " +
                    "both resources are empty, or both resources are non-empty. " +
                    "leftResource={}, rightResource={}",
                leftResource, rightResource);
            return;
        }
        // Else: one resource is empty, the other resource is non-empty

        try (Transaction tx = Transaction.openRoot()) {
            // First extract things on Anvil

            int indexToExtractFrom = leftResource.isEmpty() ? 1 : 0;
            ItemResource resourceToUse = leftResource.isEmpty() ? rightResource : leftResource;

            int amountToExtract = thingsOnAnvilHandler.getAmountAsInt(indexToExtractFrom);
            int extractedAmount = thingsOnAnvilHandler.extract(indexToExtractFrom, resourceToUse, amountToExtract, tx);
            if (extractedAmount != amountToExtract) { // Should never happen, still checking
                log.error("Could not extract everything from a GT Anvil when spreading. " +
                        "extractedAmount={}, amountToExtract={}",
                    extractedAmount, amountToExtract);
                return;
            }

            // Then insert things on Anvil back

            int amountToInsertIntoEachSide = extractedAmount / 2; // If extractedAmount is not even, we give the Player 1 item later
            int leftInsertedAmount = thingsOnAnvilHandler.insert(0, resourceToUse, amountToInsertIntoEachSide, tx);
            int rightInsertedAmount = thingsOnAnvilHandler.insert(1, resourceToUse, amountToInsertIntoEachSide, tx);

            // Should never happen, still checking
            if (leftInsertedAmount + rightInsertedAmount != 2 * amountToInsertIntoEachSide) {
                log.error("Could not insert everything into a GT Anvil when spreading. " +
                        "leftInsertedAmount={} + rightInsertedAmount={} != 2*amountToInsertIntoEachSide={}",
                    extractedAmount, amountToExtract, 2 * amountToInsertIntoEachSide);
                return;
            }

            tx.commit();

            if (extractedAmount % 2 == 1) {
                ItemStack stackToGiveToPlayer = resourceToUse.toStack(1);
                player.getInventory().placeItemBackInInventory(stackToGiveToPlayer); // TODO use some other method to give to player
            }
        }
    }

    private void addStack(ItemStack stack, int index) {
        int insertedAmount;
        try (Transaction tx = Transaction.openRoot()) {
            insertedAmount = thingsOnAnvilHandler.insert(index, ItemResource.of(stack), stack.count(), tx);
            tx.commit();
        }
        stack.shrink(insertedAmount);
    }

    private void removeStackIntoPlayerInventory(Player player, int index) {
        int amountToExtract = thingsOnAnvilHandler.getAmountAsInt(index);
        ItemResource resourceToExtract = thingsOnAnvilHandler.getResource(index);

        try (Transaction tx = Transaction.openRoot()) {
            int extractedAmount = thingsOnAnvilHandler.extract(index, resourceToExtract, amountToExtract, tx);

            if (extractedAmount != amountToExtract) { // Should never happen, still checking
                log.error("Could not extract everything from a slot with index={} in GT Anvil. " +
                        "extractedAmount={}, amountToExtract={}",
                    index, extractedAmount, amountToExtract);
                return;
            }

            tx.commit();
        }

        ItemStack resultStack = resourceToExtract.toStack(amountToExtract);

        player.getInventory().placeItemBackInInventory(resultStack); // TODO use some other method to give to player
    }

    private ItemStack getStack(int index) {
        int stackAmount = thingsOnAnvilHandler.getAmountAsInt(index);
        return thingsOnAnvilHandler.getResource(index).toStack(stackAmount);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        tintColoringData = tintColoringNbtTagSaveLoader.getDataForLoadAdditional(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        tintColoringNbtTagSaveLoader.saveAdditional(output, tintColoringData);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        tintColoringNbtTagSaveLoader.collectImplicitComponents(components, tintColoringData);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        super.applyImplicitComponents(componentGetter);
        tintColoringData = tintColoringNbtTagSaveLoader.getDataForApplyImplicitComponents(componentGetter);
    }

    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        super.removeComponentsFromTag(output);
        tintColoringNbtTagSaveLoader.removeComponentsFromTag(output);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        TagValueOutput output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, registries);
        saveAdditional(output);
        return output.buildResult();
    }

    public static GTTintColoringCapability getTintColoringCapability(GTAnvilBlockEntity blockEntity,
                                                                     @Nullable Void ignored) {
        return new GTTintColoringCapability(
            gtColoringData -> {
                blockEntity.tintColoringData = gtColoringData;
                GTUtils.updateTheBlockEntity(blockEntity);
            },
            () -> blockEntity.tintColoringData
        );
    }
}
