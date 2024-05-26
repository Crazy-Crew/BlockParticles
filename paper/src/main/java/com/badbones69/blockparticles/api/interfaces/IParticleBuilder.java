package com.badbones69.blockparticles.api.interfaces;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.utils.LocationUtil;
import com.ryderbelserion.vital.paper.util.scheduler.FoliaRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class IParticleBuilder extends FoliaRunnable {

    protected final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final ParticleKey particleKey;
    private final Location location;
    private final Particle particle;
    private final World world;
    private final String id;

    private final int count;

    public IParticleBuilder(final String id, final int count, final ParticleKey particleKey, final Particle particle, final Location location) {
        super(Bukkit.getServer().getRegionScheduler(), location);

        this.particleKey = particleKey;
        this.location = location;
        this.particle = particle;
        this.world = location.getWorld();
        this.count = count;
        this.id = id;
    }

    public abstract IParticleBuilder execute();

    protected boolean isCancelled = false;

    /**
     * This will cancel the task!
     */
    @Override
    public void cancel() {
        super.cancel();

        this.isCancelled = true;
    }

    /**
     * Checks if players are near a particle with a specific range.
     *
     * @param range the range around a location
     * @return true or false
     */
    public boolean isPlayersPresent(final int range) {
        return getLocation().getNearbyPlayers(range, range, range).isEmpty();
    }

    /**
     * Spawns a {@link Particle} at a specific location with a vector.
     */
    public void spawnParticle(Location location) {
        this.world.spawnParticle(getParticle(), location, this.count, 0, 0, 0, 0);
    }

    /**
     * Spawns a {@link Particle} at a specific location with a vector.
     */
    public void spawnParticle() {
        this.world.spawnParticle(getParticle(), getLocation(), this.count, 0, 0, 0, 0);
    }

    /**
     * @return the {@link ParticleKey}
     */
    public final ParticleKey getParticleKey() {
        return this.particleKey;
    }

    /**
     * Gets the {@link Particle} from the {@link org.bukkit.Server}.
     *
     * @return the {@link Particle}
     */
    public final Particle getParticle() {
        return this.particle;
    }

    /**
     * @return the {@link Location}
     */
    public final Location getLocation() {
        return this.location;
    }

    /**
     * @return the {@link Location} converted to a {@link String}
     */
    public String asString() {
        return LocationUtil.location(getLocation());
    }

    /**
     * @return the amount of particles to show
     */
    public final int getCount() {
        return this.count;
    }

    /**
     * @return the id
     */
    public final String getId() {
        return this.id;
    }
}