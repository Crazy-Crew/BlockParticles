package com.badbones69.blockparticles.api.interfaces;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.tasks.api.objects.BlockParticle;
import org.bukkit.Location;
import java.util.HashMap;
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
     * Adds an active {@link BlockParticle} to the map.
     *
     * @param id the id to write to the locations.yml
     * @param particleName the name of the {@link BlockParticle}
     * @param location the {@link Location}
     */
    boolean addActiveParticle(final String id, final String particleName, final Location location);

    /**
     * Removes an active {@link BlockParticle} to the map.
     *
     * @param particleName the name of the {@link BlockParticle}
     */
    boolean removeActiveParticle(final String particleName);

    /**
     * Gets an active {@link BlockParticle} from the {@link HashMap}.
     *
     * @param particleName the name of the {@link BlockParticle}
     * @return the {@link BlockParticle}
     */
    BlockParticle getActiveParticle(final String particleName);

    /**
     * Checks if an active {@link BlockParticle} {@link Location} already exists.
     *
     * @param particleName the name of the {@link BlockParticle}
     * @return true or false
     */
    boolean hasActiveParticle(final String particleName);

    /**
     * Gets a map of active {@link BlockParticle}
     *
     * @return the map of {@link BlockParticle}
     */
    Map<String, BlockParticle> getActiveParticles();

    /**
     * Adds a {@link BlockParticle} to the {@link HashMap}.
     *
     * @param particleName the name of the {@link BlockParticle}
     * @param particle the {@link BlockParticle}
     */
    boolean addBlockParticle(final String particleName, final BlockParticle particle);

    /**
     * Gets a {@link BlockParticle} from the {@link HashMap}.
     *
     * @param particleName the name of the {@link BlockParticle}
     * @return the {@link BlockParticle}
     */
    BlockParticle getBlockParticle(final String particleName);

    /**
     * Removes a {@link BlockParticle} from the {@link HashMap}
     *
     * @param particleName the name of the {@link BlockParticle}
     */
    void removeBlockParticle(final String particleName);

    /**
     * Checks if a {@link BlockParticle} {@link Location} already exists.
     *
     * @param particleName the name of the {@link BlockParticle}
     * @param readFile true or false
     * @return true or false
     */
    boolean hasBlockParticle(final String particleName, boolean readFile);

    /**
     * Checks if a {@link BlockParticle} {@link Location} already exists.
     *
     * @param particleName the name of the {@link BlockParticle}
     * @return true or false
     */
    boolean hasBlockParticle(final String particleName);

    /**
     * Saves {@link BlockParticle} locations to file
     */
    void saveParticleLocations();

    /**
     * Gets a map of {@link BlockParticle}
     *
     * @return the map of {@link BlockParticle}
     */
    Map<String, BlockParticle> getParticles();

}