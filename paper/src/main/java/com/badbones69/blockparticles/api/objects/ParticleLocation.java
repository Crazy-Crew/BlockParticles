package com.badbones69.blockparticles.api.objects;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class ParticleLocation {

    private final String id;
    private final String particle;
    private final Location loc;

    public ParticleLocation(@NotNull final String id, @NotNull final String particle, @NotNull final Location loc) {
        this.id = id;
        this.particle = particle;
        this.loc = loc;
    }

    /**
     * Get the ID of the location.
     *
     * @return the location's ID.
     */
    public @NotNull final String getID() {
        return this.id;
    }

    /**
     * Returns the particle name.
     *
     * @return the name of the particle
     */
    public @NotNull final String getParticle() {
        return this.particle;
    }

    /**
     * Get the physical location of the crate.
     *
     * @return the location of the crate.
     */
    public @NotNull final Location getLocation() {
        return this.loc;
    }
}