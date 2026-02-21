package ru.kernogo.gregtech6port.gametests;

import com.google.common.base.CaseFormat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;
import org.jspecify.annotations.Nullable;
import ru.kernogo.gregtech6port.utils.GTUtils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/** Utils for the game tests */
public final class GTGameTestUtils {
    private GTGameTestUtils() {}

    /** Used for {@link #registerTestFunction} only. Created and cached test environment that does nothing. */
    private static @Nullable Holder<TestEnvironmentDefinition<?>> nopEnv;

    /**
     * Registers the Game Test method ({@code testRunFunc}) with Minecraft. <br>
     * Caller-sensitive, prepends the calling class and method names to the {@code baseTestName}.
     */
    public static void registerTestFunction(RegisterGameTestsEvent event,
                                            @Nullable String baseTestName,
                                            String baseStructureName,
                                            Consumer<GameTestHelper> testRunFunc) {
        if (nopEnv == null) { // Create and register it only once
            nopEnv = event.registerEnvironment(GTUtils.modLoc("nop_environment"));
        }

        String callingClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        callingClassName = callingClassName.substring(callingClassName.lastIndexOf('.') + 1);
        String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        event.registerTest(
            GTUtils.modLoc(
                CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, // Hopefully convert the String to snake_case
                    baseTestName != null ?
                        callingClassName + "." + callingMethodName + "." + baseTestName :
                        callingClassName + "." + callingMethodName)
            ),
            new GTSimpleGameTestInstance(
                "Description",
                testRunFunc,
                new TestData<>(
                    nopEnv,
                    GTUtils.modLoc(baseStructureName),
                    100,
                    0,
                    true,
                    Rotation.NONE,
                    true,
                    1,
                    1,
                    false,
                    0
                )
            )
        );
    }

    /** Works like clicking on the block from the upper side with whatever is in the player's hands */
    public static void useBlockOutsideUp(GameTestHelper gameTestHelper, Player player, BlockPos pos) {
        gameTestHelper.useBlock(pos, player, new BlockHitResult(
            gameTestHelper.absolutePos(pos).getCenter(),
            Direction.UP,
            gameTestHelper.absolutePos(pos),
            false
        ));
    }

    /** Works like {@link GameTestHelper#assertEntityPresent(EntityType)}, but with a custom error message */
    public static void assertEntityPresent(GameTestHelper gameTestHelper, EntityType<?> type, String errorMessage) {
        if (gameTestHelper.getEntities(type).isEmpty()) {
            throw new GameTestAssertException(
                Component.literal("%s. Expected entity: %s".formatted(errorMessage, type.getDescription())),
                gameTestHelper.testInfo.getTick()); // Access transformer allows this call
        }
    }

    /** Works similarly to {@link GameTestHelper#assertValueEqual}, but with a different error message structure */
    public static <T> void assertEquals(GameTestHelper gameTestHelper, T expected, T actual, String errorMessage) {
        gameTestHelper.assertTrue(expected.equals(actual),
            Component.literal("%s: Expected=%s, actual=%s".formatted(errorMessage, expected, actual)));
    }

    /** Works similarly to {@link GameTestHelper#assertTrue} */
    public static <T> void assertTrue(GameTestHelper gameTestHelper, boolean condition, String errorMessage) {
        if (!condition) {
            throw gameTestHelper.assertionException(Component.literal(errorMessage));
        }
    }

    /** Works similarly to {@link GameTestHelper#assertBlockState(BlockPos, Predicate, Function)} */
    public static <T> void assertBlockState(GameTestHelper gameTestHelper, BlockPos pos, Predicate<BlockState> predicate, String errorMessage) {
        BlockState blockState = gameTestHelper.getBlockState(pos);
        if (!predicate.test(blockState)) {
            throw gameTestHelper.assertionException(
                pos,
                Component.literal(
                    "Error at BlockState=%s : %s".formatted(blockState, errorMessage)
                )
            );
        }
    }

    /** Asserts that {@code actual} is null */
    public static <T> void assertNull(GameTestHelper gameTestHelper, @Nullable T actual, String errorMessage) {
        gameTestHelper.assertTrue(actual == null,
            Component.literal("%s. Expected=null, actual=%s".formatted(errorMessage, actual)));
    }

    /** Asserts that {@code actual} is not null */
    public static <T> void assertNotNull(GameTestHelper gameTestHelper, @Nullable T actual, String errorMessage) {
        gameTestHelper.assertTrue(actual != null,
            Component.literal("%s. Expected!=null, actual=null".formatted(errorMessage)));
    }

    /** Get the number of items in player's inventory */
    public static int getInventoryItemCount(Player player) {
        Inventory inventory = player.getInventory();
        int totalCount = 0;
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            totalCount += stack.getCount();
        }
        return totalCount;
    }
}
