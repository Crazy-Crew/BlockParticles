package com.badbones69.blockparticles.api.objects.particles;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public record ParticleTypeLocation(String id, String particle, Location location, int task) {

    private static final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    /**
     * Cancels the task based on the task id.
     */
    public void cancelTask() {
        plugin.getServer().getScheduler().cancelTask(this.task);
    }

    /**
     * @return the {@link Location} converted to a {@link String}
     */
    public String asString() {
        return LocationUtil.location(this.location);
    }
}