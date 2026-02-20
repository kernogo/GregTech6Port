package ru.kernogo.gregtech6port.features.blockentities.ender_garbage_bin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringCapability;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringData;
import ru.kernogo.gregtech6port.features.behaviors.tint_coloring.GTTintColoringNbtTagSaveLoader;
import ru.kernogo.gregtech6port.registration.registered.GTBlockEntityTypes;
import ru.kernogo.gregtech6port.utils.GTUtils;

public class GTEnderGarbageBinBlockEntity extends BlockEntity {
    private @Nullable GTTintColoringData tintColoringData;
    private final GTTintColoringNbtTagSaveLoader tintColoringNbtTagSaveLoader = new GTTintColoringNbtTagSaveLoader();

    public GTEnderGarbageBinBlockEntity(BlockPos pos, BlockState blockState) {
        super(GTBlockEntityTypes.ENDER_GARBAGE_BIN.get(), pos, blockState);
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

    public static GTTintColoringCapability getTintColoringCapability(GTEnderGarbageBinBlockEntity blockEntity,
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
