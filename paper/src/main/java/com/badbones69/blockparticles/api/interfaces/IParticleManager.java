package com.badbones69.blockparticles.api.interfaces;

import com.badbones69.blockparticles.api.objects.BlockParticle;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.Map;

public interface IParticleManager {

    /**
     * Loads the plugin!
     */
    void load(final boolean serverStart);

    /**
     * Reloads the plugin!
     */
    void reload();

    /**
     * Adds an active {@link BlockParticle} to the map.
     *
     * @param id the id to write to the locations.yml
     * @param fileName the name of the {@link BlockParticle}
     * @param location the {@link Location}
     */
    boolean addActiveParticle(final String id, final String fileName, final Location location);

    /**
     * Removes an active {@link BlockParticle} to the map.
     *
     * @param fileName the name of the {@link BlockParticle}
     */
    void removeActiveParticle(final String fileName);

    /**
     * Gets an active {@link BlockParticle} from the {@link HashMap}.
     *
     * @param fileName the name of the {@link BlockParticle}
     * @return the {@link BlockParticle}
     */
    BlockParticle getActiveParticle(final String fileName);

    /**
     * Checks if an active {@link BlockParticle} {@link Location} already exists.
     *
     * @param fileName the name of the {@link BlockParticle}
     * @return true or false
     */
    boolean hasActiveParticle(final String fileName);

    /**
     * Gets a map of active {@link BlockParticle}
     *
     * @return the map of {@link BlockParticle}
     */
    Map<String, BlockParticle> getActiveParticles();

    /**
     * Adds a {@link BlockParticle} to the {@link HashMap}.
     *
     * @param fileName the name of the {@link BlockParticle}
     * @param blockParticle the {@link BlockParticle}
     */
    boolean addFile(final String fileName, final BlockParticle blockParticle);

    /**
     * Gets a {@link BlockParticle} from the {@link HashMap}.
     *
     * @param fileName the name of the {@link BlockParticle}
     * @return the {@link BlockParticle}
     */
    BlockParticle getFile(final String fileName);

    /**
     * Removes a {@link BlockParticle} from the {@link HashMap}
     *
     * @param fileName the name of the {@link BlockParticle}
     */
    void removeFile(final String fileName);

    /**
     * Checks if a {@link BlockParticle} {@link Location} already exists.
     *
     * @param fileName the name of the {@link BlockParticle}
     * @param readFile true or false
     * @return true or false
     */
    boolean hasFile(final String fileName, boolean readFile);

    /**
     * Checks if a {@link BlockParticle} {@link Location} already exists.
     *
     * @param fileName the name of the {@link BlockParticle}
     * @return true or false
     */
    boolean hasFile(final String fileName);

    Map<String, BlockParticle> getBlockParticles();

}