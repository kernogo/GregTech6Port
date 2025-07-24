package ru.kernogo.gregtech6port.utils;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.Random;

/** Random utils to use globally */
@Slf4j
public final class GTUtils {
    private GTUtils() {}

    private static final Random RANDOM = new Random();

    /** Returns true with a chance (from 0.0 to 1.0), otherwise returns false */
    public static boolean rollRandomChance(double probability) {
        return RANDOM.nextDouble() < probability;
    }

    public static ResourceLocation modLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(GregTech6Port.MODID, name);
    }

    /**
     * This can be used to assure the IDE that the {@code object} is not null (because it's been validated before). <br>
     * If the {@code object} is in fact null, then this logs an error and throws a {@link GTUnexpectedValidationFailException}. <br>
     * The code that calls this method should be wrapped
     * in a try-catch block to catch the {@link GTUnexpectedValidationFailException} if it is thrown. <br>
     * TODO: static analysis to ensure that the exception is caught. This could've been a checked exception, but putting "throws" everywhere is annoying
     */
    @Contract("null -> fail; _ -> param1")
    public static <T> T assureNotNull(@Nullable T object) {
        if (object == null) {
            String errorMessage = "Object is null even though it was claimed that it's not";
            log.error(errorMessage);
            throw new GTUnexpectedValidationFailException(errorMessage);
        }
        return object;
    }
}
