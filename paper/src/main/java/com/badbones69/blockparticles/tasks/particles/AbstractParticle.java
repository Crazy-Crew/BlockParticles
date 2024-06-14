package com.badbones69.blockparticles.tasks.particles;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.objects.BlockParticle;
import com.badbones69.blockparticles.utils.LocationUtil;
import com.badbones69.blockparticles.api.config.ParticleConfig;
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

    protected final ConfigurationSection section;

    protected final ParticleConfig config;

    public AbstractParticle(final BlockParticle particle) {
        super(Bukkit.getServer().getGlobalRegionScheduler());

        this.config = particle.getConfig();
        this.section = this.config.getSettings();
    }

    public abstract void execute();

    private Location location;
    private World world;

    /**
     * Spawns a {@link Particle} at a specific {@link Location}!
     *
     * @param location the {@link Location} to spawn the particle
     */
    public void spawnParticle(Location location) {
        Particle particle = this.config.getParticleType();

        if (particle != null) {
            int amount = this.config.getParticleAmount();

            this.world.spawnParticle(particle, location, amount, 0, 0, 0, 0);
        }
    }

    /**
     * Spawns a {@link Particle} at a specific the {@link Location}!
     */
    public void spawnParticle() {
        spawnParticle(this.location);
    }

    /**
     * Sets the {@link Location} for the particle!
     *
     * @param location the {@link Location}
     */
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

    /**
     * @return the {@link Location}
     */
    public final Location getLocation() {
        return this.location;
    }

    /**
     * @return the {@link World}
     */
    public final World getWorld() {
        return this.world;
    }
}