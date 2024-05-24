package com.badbones69.blockparticles.api;

import com.badbones69.blockparticles.api.objects.v2.ParticleTypeLocation;
import org.bukkit.Location;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParticleHandler {

    private final Map<String, ParticleTypeLocation> particles = new HashMap<>();

    /**
     * Adds a particle to the map.
     *
     * @param id the id to identify the particle
     * @param particle the type of particle
     * @param location the {@link Location}
     */
    public void addParticleLocation(final String id, final String particle, final Location location) {
        final ParticleTypeLocation type = new ParticleTypeLocation(id, particle, location);

        this.particles.put(id, type);
    }

    /**
     * Removes a particle from the map.
     *
     * @param id the id of the particle
     */
    public void removeParticleLocation(final String id) {
        this.particles.remove(id);
    }

    /**
     * @return a map of active particle locations
     */
    public final Map<String, ParticleTypeLocation> getParticles() {
        return Collections.unmodifiableMap(this.particles);
    }
}