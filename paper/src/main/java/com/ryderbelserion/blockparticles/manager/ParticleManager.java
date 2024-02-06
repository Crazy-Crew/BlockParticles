package com.ryderbelserion.blockparticles.manager;

import com.ryderbelserion.blockparticles.BlockParticles;
import org.bukkit.Location;
import org.bukkit.Particle;
import java.util.HashMap;
import java.util.TimerTask;

public class ParticleManager {

    private final BlockParticles plugin = BlockParticles.get();

    private final HashMap<Location, TimerTask> tasks = new HashMap<>();

    private void addTask(Location location, TimerTask task, Long delay, Long period) {
        this.tasks.put(location, task);

        this.plugin.getTimer().scheduleAtFixedRate(task, delay, period);
    }

    public void removeTask(Location location) {
        if (this.tasks.containsKey(location)) {
            this.tasks.get(location).cancel();

            this.tasks.remove(location);
        }
    }

    public void volcano(Location location, Particle particle) {
        addTask(location, new TimerTask() {
            final Location newLocation = location.add(0.5, 2, 0.5);

            @Override
            public void run() {
                location.getWorld().spawnParticle(particle, newLocation, 10, 0, 0, 0, 0);
            }
        }, 0L, 4L);
    }
}