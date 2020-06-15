package io.github.lukeeff.spigottemplate.cooldown;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;

/**
 * For handling and storing cool downs.
 *
 * @author lukeeff
 * @since 1.0
 */
public class Cooldown {

    @Getter private final Map<UUID, Long> coolDownHandle;
    private static final long COOL_DOWN = 4000;

    public Cooldown() {
        coolDownHandle = new HashMap<>();
    }

    public long getCoolDown() {
        return COOL_DOWN;
    }

    /**
     * Adds a player to the cool down map
     *
     * @param playerId the uuid of the target player.
     */
    public void registerPlayer(UUID playerId) {
        coolDownHandle.putIfAbsent(playerId, currentTimeMillis() + COOL_DOWN);
    }

    /**
     * Gets how much longer the player is on cool down.
     *
     * @param playerId the uuid of target player.
     * @return the amount of time left in ms until cool down expires.
     */
    public long getTimeLeft(UUID playerId) {
        final Long time = coolDownHandle.get(playerId);
        return (time == null) ? 0 : time - currentTimeMillis();
    }

    /**
     * Remove a player from the cool down map.
     *
     * @param playerId the uuid of the player to be removed.
     */
    public void removePlayer(UUID playerId) {
        coolDownHandle.remove(playerId);
    }

    /**
     * Checks if a player is on cool down. Removes if not.
     *
     * @param playerId the uuid of the target player.
     * @return true if the player still is on coolDown.
     */
    public boolean isOnCoolDown(UUID playerId) {
        if((getTimeLeft(playerId) > 0)) {
            return true;
        }
        removePlayer(playerId);
        return false;
    }

}
