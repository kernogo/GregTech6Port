package ru.kernogo.gregtech6port.features.behaviors.item_with_uses;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.With;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;

import java.util.Optional;

/**
 * This is a data class for the {@link GTDataComponentTypes#ITEM_WITH_USES} data component -
 * for items that have a finite number of uses. <br>
 * Notes:
 * <ul>
 *   <li>The definition of a single-use item is:
 *   it breaks into something ({@code breaksInto != null}) and {@code maxRemainingUses == 1}. <br>
 *   If the item isn't single-use, it's multi-use.</li>
 *   <li>Item with this data component may be stackable, but if it's multi-use, it must be unstacked before using.</li>
 *   <li>Use {@link GTItemWithUsesBehavior} for the implementation of the functionality associated with this data component.</li>
 * </ul>
 *
 * @param remainingUses    remaining number of uses
 * @param maxRemainingUses maximum remaining number of uses
 * @param breaksInto       what item remains after the item with this data component reaches zero remaining uses;
 *                         breaksInto can be AIR;
 *                         if null, then the item with this data component remains with zero uses left instead of breaking
 */
@With
public record GTItemWithUsesData(
    int remainingUses,
    int maxRemainingUses,
    @Nullable Holder<Item> breaksInto
) {
    public static final Codec<GTItemWithUsesData> CODEC = RecordCodecBuilder.create(
        builder -> builder.group(
            Codec.INT.fieldOf("remaining_uses").forGetter(GTItemWithUsesData::remainingUses),
            Codec.INT.fieldOf("max_remaining_uses").forGetter(GTItemWithUsesData::maxRemainingUses),
            BuiltInRegistries.ITEM.holderByNameCodec()
                .optionalFieldOf("breaks_into")
                .forGetter(GTItemWithUsesData -> Optional.ofNullable(GTItemWithUsesData.breaksInto))
        ).apply(builder, GTItemWithUsesData::create)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, GTItemWithUsesData> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.VAR_INT,
        GTItemWithUsesData::remainingUses,
        ByteBufCodecs.VAR_INT,
        GTItemWithUsesData::maxRemainingUses,
        ByteBufCodecs.optional(ByteBufCodecs.holderRegistry(BuiltInRegistries.ITEM.key())),
        GTItemWithUsesData -> Optional.ofNullable(GTItemWithUsesData.breaksInto()),
        GTItemWithUsesData::create
    );

    // This is needed because of the nullability
    private static GTItemWithUsesData create(Integer remainingUses, Integer maxRemainingUses, Optional<Holder<Item>> breaksInto) {
        return new GTItemWithUsesData(remainingUses, maxRemainingUses, breaksInto.orElse(null));
    }
}
