package com.badbones69.blockparticles.tasks.particles;


import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.tasks.api.objects.BlockParticle;
import com.badbones69.blockparticles.utils.LocationUtil;
import com.badbones69.blockparticles.utils.ParticleUtil;
import com.ryderbelserion.vital.paper.util.ItemUtil;
import com.ryderbelserion.vital.paper.util.scheduler.FoliaRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.ConfigurationSection;

public abstract class AbstractParticle extends FoliaRunnable {

    protected final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    protected final ParticleConfig config;

    protected final ConfigurationSection section;

    protected final ParticleKey particleKey;

    protected final Particle particle;

    public AbstractParticle(final BlockParticle particle) {
        super(Bukkit.getServer().getGlobalRegionScheduler());

        this.config = particle.getConfig();
        this.section = this.config.getSettings();

        this.particleKey = ParticleUtil.getParticleByName(this.config.getParticleType());

        this.particle = ItemUtil.getParticleType(this.config.getParticleType());
    }

    protected boolean isCancelled = false;
    protected Location location;
    protected World world;

    public abstract void execute();

    /**
     * This will cancel the task!
     */
    @Override
    public void cancel() {
        super.cancel();

        this.isCancelled = true;
    }

    /**
     * Checks if players are near a {@link BlockParticle} with a specific range.
     *
     * @param range the range around a {@link Location}
     * @return true or false
     */
    protected final boolean isPlayerPresent(final int range) {
        return this.location.getNearbyPlayers(range, range, range).isEmpty();
    }

    /**
     * Spawns a {@link Particle} at a specific location with a vector.
     *
     * @param location the {@link Location} to spawn the particle
     */
    public void spawnParticle(Location location) {
        this.world.spawnParticle(this.particle, location, this.config.getParticleAmount(), 0, 0, 0, 0);
    }

    /**
     * Spawns a {@link Particle} at a specific location with a vector.
     */
    public void spawnParticle() {
        spawnParticle(this.location);
    }

    public void setLocation(@NotNull final Location location) {
        this.location = location;

        this.world = this.location.getWorld();
    }

    /**
     * @return the {@link Location} converted to a {@link String}
     */
    public final String asString() {
        return LocationUtil.location(this.location);
    }

    public final Location getLocation() {
        return this.location;
    }

    public final ParticleKey getParticleKey() {
        return this.particleKey;
    }
}