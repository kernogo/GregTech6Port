package ru.kernogo.gregtech6port.features.items.like.spray;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * This event listener is needed because there are no Item class's methods that run before the entity-specific methods
 * (e.g., {@link Entity#interact}) in the interaction pipeline for the entities. <br>
 * Existing {@link Entity#interact} often consumes the event so that the item-specific logic is never run.
 */
public final class GTSprayLikeItemEntityInteractHandler {
    private GTSprayLikeItemEntityInteractHandler() {}

    /** This gets subscribed with the NeoForge event bus in another class */
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getItemStack().getItem() instanceof GTSprayLikeItem sprayLikeItem)) {
            return;
        }

        Player player = event.getEntity();
        Entity entity = event.getTarget();

        InteractionResult result = sprayLikeItem.onEntityInteract(
            event.getItemStack(),
            player,
            entity,
            event.getHand()
        );

        if (result.consumesAction()) {
            event.setCancellationResult(result);
            event.setCanceled(true);
        }
    }
}
