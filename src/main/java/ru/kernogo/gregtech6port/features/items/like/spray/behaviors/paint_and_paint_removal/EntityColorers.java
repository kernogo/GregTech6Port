package ru.kernogo.gregtech6port.features.items.like.spray.behaviors.paint_and_paint_removal;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import ru.kernogo.gregtech6port.utils.exception.GTUnexpectedValidationFailException;

import java.util.List;

/** Used in {@link PaintSprayBehavior} as an accessor to classes that paint entities into colors */
final class EntityColorers {
    private static final SheepEntityColorer SHEEP_ENTITY_COLORER = new SheepEntityColorer();
    private static final WolfEntityColorer WOLF_ENTITY_COLORER = new WolfEntityColorer();
    private static final CatEntityColorer CAT_ENTITY_COLORER = new CatEntityColorer();

    // WARNING: Do not forget to add new entity colorers here when they appear!
    private static final List<IEntityColorer> ALL_ENTITY_COLORERS = List.of(
        SHEEP_ENTITY_COLORER, WOLF_ENTITY_COLORER, CAT_ENTITY_COLORER
    );

    EntityColorers() {}

    /** Access all available entity colorers */
    List<IEntityColorer> getAllEntityColorers() {
        return ALL_ENTITY_COLORERS;
    }

    interface IEntityColorer {
        /** Returns true if this entity colorer can be used for this class of {@code entity} */
        boolean isApplicableEntityClass(Entity entity);

        /** Paints an entity into a color and return true if entity was painted, false otherwise */
        boolean colorAnEntity(Entity entity, Player player, DyeColor dyeColor);
    }

    static final class SheepEntityColorer implements IEntityColorer {
        @Override
        public boolean isApplicableEntityClass(Entity entity) {
            return entity instanceof Sheep;
        }

        @Override
        public boolean colorAnEntity(Entity entity, Player player, DyeColor dyeColor) {
            if (!(entity instanceof Sheep sheep)) {
                throw new GTUnexpectedValidationFailException("isApplicableEntityClass should've been called first");
            }

            if (sheep.getColor() == dyeColor) {
                return false;
            }
            sheep.setColor(dyeColor);
            return true;
        }
    }

    static final class WolfEntityColorer implements IEntityColorer {
        @Override
        public boolean isApplicableEntityClass(Entity entity) {
            return entity instanceof Wolf;
        }

        /** Based on Wolf#mobInteract */
        @Override
        public boolean colorAnEntity(Entity entity, Player player, DyeColor dyeColor) {
            if (!(entity instanceof Wolf wolf)) {
                throw new GTUnexpectedValidationFailException("isApplicableEntityClass should've been called first");
            }

            if (!isOwner(player, wolf) || !wolf.isTame() || wolf.getCollarColor() == dyeColor) {
                return false;
            }
            wolf.setCollarColor(dyeColor); // Access transformer allows this call
            return true;
        }
    }

    static final class CatEntityColorer implements IEntityColorer {
        @Override
        public boolean isApplicableEntityClass(Entity entity) {
            return entity instanceof Cat;
        }

        /** Based on Wolf#mobInteract */
        @Override
        public boolean colorAnEntity(Entity entity, Player player, DyeColor dyeColor) {
            if (!(entity instanceof Cat cat)) {
                throw new GTUnexpectedValidationFailException("isApplicableEntityClass should've been called first");
            }

            if (!isOwner(player, cat) || !cat.isTame() || cat.getCollarColor() == dyeColor) {
                return false;
            }
            cat.setCollarColor(dyeColor); // Access transformer allows this call
            return true;
        }
    }

    private static boolean isOwner(Player player, OwnableEntity ownableEntity) {
        // Mock player in game tests does not show up in TamableEntity#isOwnedBy,
        // so we compare the UUIDs instead of calling TamableEntity#isOwnedBy.
        return ownableEntity.getOwnerUUID() != null && ownableEntity.getOwnerUUID().equals(player.getUUID());
    }
}
