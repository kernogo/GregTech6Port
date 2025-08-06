package ru.kernogo.gregtech6port.features.behaviors.tint_coloring;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

/**
 * Data for the tint coloring of GT blocks. See {@link GTTintColoringSystem} for the details.
 * Alpha channel seems to be ignored in Minecraft's tinting (at least in MC 1.21.1), but still we include it just in case. <br>
 *
 * @param argbColor color in ARGB format stored as an {@code int} variable;
 *                  good thing about ARGB {@code int} is that any {@code int} value is a valid ARGB color.
 */
public record GTTintColoringData(int argbColor) {
    public static final Codec<GTTintColoringData> CODEC =
        Codec.INT.xmap(GTTintColoringData::new, gtColoringData -> gtColoringData.argbColor);

    public static final StreamCodec<ByteBuf, GTTintColoringData> STREAM_CODEC =
        ByteBufCodecs.INT.map(GTTintColoringData::new, gtColoringData -> gtColoringData.argbColor);

    public int getAlphaChannel() {
        return (argbColor >> 24) & 0xFF;
    }

    public int getRedChannel() {
        return (argbColor >> 16) & 0xFF;
    }

    public int getGreenChannel() {
        return (argbColor >> 8) & 0xFF;
    }

    public int getBlueChannel() {
        return argbColor & 0xFF;
    }

    /** Create a new tint coloring data from the ARGB components */
    public static GTTintColoringData createFromArgbComponents(int a, int r, int g, int b) {
        validate(a, r, g, b);

        int argbColorToUse = (a << 24) + (r << 16) + (g << 8) + b;

        return new GTTintColoringData(argbColorToUse);
    }

    /** Create a new tint coloring data by mixing the existing two together */
    public static GTTintColoringData createMixedColoringData(GTTintColoringData first, GTTintColoringData second) {
        return GTTintColoringData.createFromArgbComponents(
            255,
            (first.getRedChannel() + second.getRedChannel()) / 2,
            (first.getGreenChannel() + second.getGreenChannel()) / 2,
            (first.getBlueChannel() + second.getBlueChannel()) / 2
        );
    }

    private static void validate(int a, int r, int g, int b) {
        if (a < 0 || a > 255 || r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new GTUnexpectedValidationFailException(
                "Some of the argb components are out of bounds. a=%s, r=%s, g=%s, b=%s".formatted(a, r, g, b)
            );
        }
    }
}
