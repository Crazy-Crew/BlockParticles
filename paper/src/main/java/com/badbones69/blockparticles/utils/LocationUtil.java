package com.badbones69.blockparticles.utils;

import com.badbones69.blockparticles.api.objects.BlockParticle;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class LocationUtil {

    /**
     * Converts a location object to a string for storage
     *
     * @param location the {@link Location} to convert
     * @param getName true or false
     * @return the location string
     */
    public static @NotNull String location(@NotNull final Location location, boolean getName) {
        String name = getName ? location.getWorld().getName() : String.valueOf(location.getWorld().getUID());

        return name + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
    }

    /**
     * Converts a {@link Location} object with the {@link org.bukkit.World} {@link java.util.UUID}
     *
     * @param location the {@link Location}
     * @return the location string
     */
    public static @NotNull String location(@NotNull final Location location) {
        return location(location, false);
    }

    /**
     * Checks if players are near a {@link BlockParticle} with a specific range.
     *
     * @param range the range around a {@link Location}
     * @return true or false
     */
    public static boolean isPlayerPresent(final Location location, final int range) {
        return location.getNearbyPlayers(range, range, range).isEmpty();
    }
}