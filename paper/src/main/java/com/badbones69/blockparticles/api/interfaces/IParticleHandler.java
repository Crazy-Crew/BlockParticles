package com.badbones69.blockparticles.api.interfaces;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import org.bukkit.Location;
import java.util.Map;

public interface IParticleHandler {

    /**
     * Loads all particles and migrates configurations
     */
    void load(boolean isServerStart);

    /**
     * Only used in the /blockparticles reload command
     */
    void reload();

    /**
     * Adds a particle to the map.
     *
     * @param id the id to identify the particle
     * @param particle the type of particle
     * @param location the {@link Location}
     */
    void addParticleLocation(final String id, final int count, final int size, final ParticleKey particleKey, final String particle, final Location location);

    /**
     * Adds a particle to the map.
     *
     * @param builder the particle builder
     */
    void addParticleLocation(IParticleBuilder builder);

    /**
     * Removes a particle from the map.
     *
     * @param id the id of the particle
     */
    void removeParticleLocation(final String id);

    /**
     * Checks if a particle location already exists.
     *
     * @param id the id of the particle
     * @param readFile true or false
     * @return true or false
     */
    boolean hasParticleLocation(final String id, boolean readFile);

    /**
     * Checks if a particle location already exists.
     *
     * @param id the id of the particle
     * @return true or false
     */
    boolean hasParticleLocation(final String id);

    /**
     * Saves particle locations to file
     */
    void saveParticleLocations();

    /**
     * A map of currently active particle locations.
     *
     * @return a map of active particle locations
     */
    Map<String, IParticleBuilder> getParticles();

}