package ru.kernogo.gregtech6port.registration;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Defers the actual registration (calls to {@link Registry#register}), <br>
 * but the actual registration must be called manually
 * instead of subscribing to the event bus like in {@link net.neoforged.neoforge.registries.DeferredRegister}. <br>
 * Be careful when using it, because it's prone to mistakes that have to do with static initialization. <br>
 * {@link T} is the type of the Registry.
 */
public final class GTLessDeferredRegister<T> {
    /** {@link Registry} in which the objects will be registered */
    private final Registry<T> registry;
    /** Namespace of objects */
    private final String namespace;

    /** Objects to write into Minecraft's {@link Registry} */
    private final List<Pair<ResourceLocation, T>> toRegister = new ArrayList<>();

    /** True if this class has already written objects into Minecraft's {@link Registry} */
    private boolean isActuallyRegistered = false;

    public GTLessDeferredRegister(Registry<T> registry, String namespace) {
        this.registry = Objects.requireNonNull(registry);
        this.namespace = Objects.requireNonNull(namespace);
    }

    /** Add a pending object to register */
    public <I extends T> T register(final String name, final Supplier<? extends I> sup) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(sup);
        T result = sup.get();
        Objects.requireNonNull(result);

        if (isActuallyRegistered) {
            throw new GTUnexpectedValidationFailException(
                "GTLessDeferredRegister for registry=%s, namespace=%s has already registered objects into Minecraft's registry"
                    .formatted(registry, namespace)
            );
        }

        toRegister.add(Pair.of(ResourceLocation.fromNamespaceAndPath(namespace, name), result));

        return result;
    }

    /** Call {@link Registry#register} for each pending object */
    public void actuallyRegister() {
        isActuallyRegistered = true;

        for (Pair<ResourceLocation, T> pair : toRegister) {
            ResourceLocation location = pair.getLeft();
            T object = pair.getRight();
            Registry.register(registry, location, object);
        }
    }
}
