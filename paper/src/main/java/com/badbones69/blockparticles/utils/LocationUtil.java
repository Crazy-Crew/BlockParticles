package com.badbones69.blockparticles.utils;

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
}