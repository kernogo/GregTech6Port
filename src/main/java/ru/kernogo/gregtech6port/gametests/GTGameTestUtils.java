package ru.kernogo.gregtech6port.gametests;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import ru.kernogo.gregtech6port.GregTech6Port;

import java.util.function.Consumer;

/** Utils for the game tests */
public final class GTGameTestUtils {
    private GTGameTestUtils() {}

    /**
     * Make a test functions with good defaults for use in
     * {@link net.minecraft.gametest.framework.GameTestGenerator}-annotated methods. <br>
     * Caller-sensitive, prepends the calling class and method names to the {@code baseTestName}.
     */
    public static TestFunction makeTestFunction(@Nullable String baseTestName,
                                                String baseStructureName,
                                                Consumer<GameTestHelper> consumer) {
        String callingClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        callingClassName = callingClassName.substring(callingClassName.lastIndexOf('.') + 1);
        String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return new TestFunction(
            "defaultBatch",
            baseTestName != null ?
                callingClassName + "." + callingMethodName + "." + baseTestName :
                callingClassName + "." + callingMethodName,
            GregTech6Port.MODID + ":" + baseStructureName,
            Rotation.NONE,
            100,
            0,
            true,
            false,
            1,
            1,
            false,
            consumer
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
            throw new GameTestAssertException(errorMessage);
        }
    }

    /** Works like {@link GameTestHelper#assertValueEqual}, but with a different error message structure */
    public static <T> void assertEquals(GameTestHelper gameTestHelper, T actual, T expected, String errorMessage) {
        gameTestHelper.assertTrue(expected.equals(actual),
            "%s. Expected=%s, actual=%s".formatted(errorMessage, expected, actual));
    }

    /** Asserts that {@code actual} is null */
    public static <T> void assertNull(GameTestHelper gameTestHelper, @Nullable T actual, String errorMessage) {
        gameTestHelper.assertTrue(actual == null,
            "%s. Expected=null, actual=%s".formatted(errorMessage, actual));
    }

    /** Asserts that {@code actual} is not null */
    public static <T> void assertNotNull(GameTestHelper gameTestHelper, @Nullable T actual, String errorMessage) {
        gameTestHelper.assertTrue(actual != null,
            "%s. Expected!=null, actual=null".formatted(errorMessage));
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
