package ru.kernogo.gregtech6port.gametests;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import ru.kernogo.gregtech6port.registration.registered.GTGameTestInstanceTypes;

import java.util.function.Consumer;

/** A simple Game Test Instance accepting an arbitrary test method ({@link #testRunFunc}) */
public class GTSimpleGameTestInstance extends GameTestInstance {
    private final String description;
    private final Consumer<GameTestHelper> testRunFunc;

    public GTSimpleGameTestInstance(TestData<Holder<TestEnvironmentDefinition<?>>> info) {
        super(info);
        this.description = "";
        this.testRunFunc = x -> {};
    }

    public GTSimpleGameTestInstance(String description,
                                    Consumer<GameTestHelper> testRunFunc,
                                    TestData<Holder<TestEnvironmentDefinition<?>>> info) {
        super(info);
        this.description = description;
        this.testRunFunc = testRunFunc;
    }

    @Override
    public void run(GameTestHelper helper) {
        testRunFunc.accept(helper);
    }

    @Override
    public MapCodec<? extends GameTestInstance> codec() {
        return GTGameTestInstanceTypes.SIMPLE_GAME_TEST_INSTANCE.get();
    }

    @Override
    protected MutableComponent typeDescription() {
        return Component.literal(description);
    }

    @Override
    public TestData<Holder<TestEnvironmentDefinition<?>>> info() {
        return super.info();
    }
}
