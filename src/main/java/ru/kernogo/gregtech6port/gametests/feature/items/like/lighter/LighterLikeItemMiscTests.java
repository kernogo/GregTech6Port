package ru.kernogo.gregtech6port.gametests.feature.items.like.lighter;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesBehavior;
import ru.kernogo.gregtech6port.features.behaviors.item_with_uses.GTItemWithUsesData;
import ru.kernogo.gregtech6port.gametests.GTGameTestUtils;
import ru.kernogo.gregtech6port.registration.registered.GTDataComponentTypes;
import ru.kernogo.gregtech6port.registration.registered.GTItems;

import java.util.List;

/** Miscellaneous tests for the lighter-like items */
@EventBusSubscriber
public class LighterLikeItemMiscTests {
    private static final GTItemWithUsesBehavior itemWithUsesBehavior = new GTItemWithUsesBehavior();

    @SubscribeEvent
    public static void tests_ClickingMultiUseLightersWithZeroRemainingUsesDoesNothing(RegisterGameTestsEvent event) {
        for (DeferredItem<Item> deferredItem : List.of(
            GTItems.LIGHTER,
            GTItems.SHINY_LIGHTER
        )) {
            GTGameTestUtils.registerTestFunction(
                event,
                deferredItem.getKey().identifier().getPath(),
                "gametest_bedrock_1x2x1",
                gameTestHelper -> doTest_ClickingMultiUseLightersWithZeroRemainingUsesDoesNothing(gameTestHelper, deferredItem)
            );
        }
    }

    private static void doTest_ClickingMultiUseLightersWithZeroRemainingUsesDoesNothing(GameTestHelper gameTestHelper,
                                                                                        DeferredItem<Item> deferredLighterItem) {
        Player player = gameTestHelper.makeMockPlayer(GameType.SURVIVAL);
        ItemStack initialLighterStack = deferredLighterItem.get().getDefaultInstance();
        GTItemWithUsesData initialItemWithUsesData = itemWithUsesBehavior.validateAndGetItemWithUsesData(initialLighterStack);
        GTGameTestUtils.assertTrue(gameTestHelper,
            initialItemWithUsesData.breaksInto() == null && !itemWithUsesBehavior.isSingleUse(initialLighterStack),
            "This test was made only for multi-use lighters that don't break into anything"
        );
        initialLighterStack.set(GTDataComponentTypes.ITEM_WITH_USES, initialItemWithUsesData.withRemainingUses(0));
        player.setItemInHand(InteractionHand.MAIN_HAND, initialLighterStack.copy());

        BlockPos posToClick = new BlockPos(0, 0, 0);
        int numberOfTimesToClick = 50;
        for (int i = 0; i < numberOfTimesToClick; i++) {
            GTGameTestUtils.useBlockOutsideUp(gameTestHelper, player, posToClick);
            gameTestHelper.assertBlockNotPresent(Blocks.FIRE, posToClick.above());
        }

        GTGameTestUtils.assertTrue(gameTestHelper,
            ItemStack.isSameItem(player.getMainHandItem(), initialLighterStack) &&
                ItemStack.isSameItemSameComponents(player.getMainHandItem(), initialLighterStack),
            "Lighter in hand with zero uses has been changed after clicking"
        );
        GTGameTestUtils.assertEquals(gameTestHelper,
            1, GTGameTestUtils.getInventoryItemCount(player),
            "number of items in player's inventory");

        gameTestHelper.succeed();
    }
}
