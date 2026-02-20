package ru.kernogo.gregtech6port.registration.registered;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.gametest.framework.TestData;
import ru.kernogo.gregtech6port.gametests.GTSimpleGameTestInstance;
import ru.kernogo.gregtech6port.registration.GTRegisters;

import java.util.function.Supplier;

public final class GTGameTestInstanceTypes {
    /** Type for {@link GTSimpleGameTestInstance} */
    public static final Supplier<MapCodec<GTSimpleGameTestInstance>> SIMPLE_GAME_TEST_INSTANCE =
        GTRegisters.TEST_INSTANCE_TYPES.register(
            "simple_game_test_instance",
            () -> RecordCodecBuilder.mapCodec(instance -> instance.group(
                    TestData.CODEC.forGetter(GTSimpleGameTestInstance::info)
                ).apply(instance, GTSimpleGameTestInstance::new)
            )
        );

    /** This gets called to classload this class (to initialize all static fields in this class) */
    public static void init() {}
}
