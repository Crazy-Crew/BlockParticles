package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleControl;
import com.badbones69.blockparticles.api.enums.particles.CustomParticles;
import com.ryderbelserion.fusion.paper.scheduler.FoliaScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Particles implements ParticleControl {

    private final BlockParticles plugin = BlockParticles.getPlugin();
    private final Server server = plugin.getServer();
    
    private final Map<String, ScheduledTask> locations = new HashMap<>();
    private final int range = 25;
    private final Random random = new Random();
    
    private Location randomDrop(Location location) {
        double x = random.nextInt(100) / 100.0 - .50;
        double z = random.nextInt(100) / 100.0 - .50;
        return location.add(x, 0, z);
    }
    
    private float randomVector() {
        return (float) -.05 + (float) (Math.random() * ((.05 - -.05)));
    }

    @Override
    public Map<String, ScheduledTask> getLocations() {
        return locations;
    }

    public void playVolcano(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location clonedLocation = location.clone().add(.5, .8, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(location, range)) return;

                world.spawnParticle(Particle.LAVA, clonedLocation, 10, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 4));
    }
    
    public void playBigFlame(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                for (Location location : getCircle(l, 1, 15))
                    world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
                for (Location location : getCircle(l, 2, 25))
                    world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playFlame(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                for (Location location : getCircle(l, .6, 15))
                    world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
                for (Location location : getCircle(l, 1, 20))
                    world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playDoubleSpiral(final Location location, String id, CustomParticles customParticles, int amount) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .7, .5);
            int time = 16;
            final Particle particle = customParticles == CustomParticles.DOUBLEWITCH ? Particle.WITCH : Particle.FIREWORK;

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;

                switch (time) {
                    case 15 -> {
                        world.spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                    }

                    case 14 -> {
                        world.spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                    }

                    case 13 -> {
                        world.spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                    }

                    case 12 -> {
                        world.spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                    }

                    case 11 -> {
                        world.spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                    }

                    case 10 -> {
                        world.spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                    }

                    case 9 -> {
                        world.spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                    }

                    case 8 -> {
                        world.spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                    }

                    case 7 -> {
                        world.spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                    }

                    case 6 -> {
                        world.spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                    }

                    case 5 -> {
                        world.spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                    }

                    case 4 -> {
                        world.spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                    }

                    case 3 -> {
                        world.spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                    }

                    case 2 -> {
                        world.spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                    }

                    case 1 -> {
                        world.spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                    }

                    case 0 -> {
                        world.spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                        time = 16;
                    }
                }

                time--;
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playSpiral(final Location location, String id, CustomParticles customParticles, int amount) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .7, .5);
            int time = 16;
            final Particle particle = customParticles == CustomParticles.WITCH ? Particle.WITCH : Particle.FIREWORK;

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;

                switch (time) {
                    case 15 -> world.spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                    case 14 -> world.spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                    case 13 -> world.spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                    case 12 -> world.spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                    case 11 -> world.spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                    case 10 -> world.spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                    case 9 -> world.spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                    case 8 -> world.spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                    case 7 -> world.spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                    case 6 -> world.spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                    case 5 -> world.spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                    case 4 -> world.spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                    case 3 -> world.spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                    case 2 -> world.spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                    case 1 -> world.spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                    case 0 -> {
                        world.spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                        time = 16;
                    }
                }

                time--;
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playCrit(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1.1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.CRIT, l.clone(), 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playBigCrit(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .5, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                for (Location location : getCircle(l, 2, 20))
                    world.spawnParticle(Particle.CRIT, location, 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playStorm(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.CLOUD, l.clone(), 15, .3f, 0, 0.3f, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(0, 0, .1), 10, 0.2f, 0, 0.2f, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playFog(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .5, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.CLOUD, l, 20, .3f, 0, .3f, 0.05f);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playEnchant(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1.5, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.ENCHANT, l, 20, 0, 0, 0, 2);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playChains(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FLAME, l.clone().add(1, 0, 1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.9, .1, .9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.8, .2, .8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.7, .3, .7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.6, .4, .6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.5, .6, .5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.4, .8, .4), 1, 0, 0, 0, 0);

                world.spawnParticle(Particle.FLAME, l.clone().add(-1, 0, 1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.9, .1, .9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.8, .2, .8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.7, .3, .7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.6, .4, .6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.5, .6, .5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.4, .8, .4), 1, 0, 0, 0, 0);

                world.spawnParticle(Particle.FLAME, l.clone().add(-1, 0, -1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.9, .1, -.9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.8, .2, -.8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.7, .3, -.7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.6, .4, -.6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.5, .6, -.5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.4, .8, -.4), 1, 0, 0, 0, 0);

                world.spawnParticle(Particle.FLAME, l.clone().add(1, 0, -1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.9, .1, -.9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.8, .2, -.8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.7, .3, -.7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.6, .4, -.6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.5, .6, -.5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.4, .8, -.4), 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playFireStorm(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                try {
                    if (!noPlayers(l.clone(), range)) {
                        world.spawnParticle(Particle.LARGE_SMOKE, l, 15, 0.3f, 0, 0.3f, 0);
                        world.spawnParticle(Particle.FLAME, randomDrop(l.clone()), 0, 0, -0.2f, 0, 1);
                        world.spawnParticle(Particle.FLAME, randomDrop(l.clone()), 0, 0, -0.2f, 0, 1);
                    }
                } catch (Exception e) {
                    locations.get(id).cancel();
                    locations.remove(id); // this might need further testing
                    e.printStackTrace();
                }
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playSnow(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FIREWORK, l, 1, .7f, .7f, .7f, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playSpew(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FIREWORK, l, 0, randomVector(), .1f, randomVector(), 1);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playPotion(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
                world.spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
                world.spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playMusic(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .2, .5);
            final ArrayList<Location> locs = getCircle(l, 1, 16);
            int time = 0;

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                int i = time;

                switch (time) {
                    case 15 -> {
                        world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                        time = -1;
                    }

                    case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 -> world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                }

                time++;
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playMagic(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .5, .5);

            final World world = location.getWorld();
            int time = 16;

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;

                switch (time) {
                    case 0 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .75, .43), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.75, 0, -.43), 5, 0, 0, 0, 0);
                        time = 16;
                    }

                    case 1 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .65, .65), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.65, 0, -.65), 5, 0, 0, 0, 0);
                    }

                    case 2 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .43, .75), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.43, 0, -.75), 5, 0, 0, 0, 0);
                    }

                    case 3 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, 0, .8), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, 0, -.8), 5, 0, 0, 0, 0);
                    }

                    case 4 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.43, .75), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.43, 0, -.75), 5, 0, 0, 0, 0);
                    }

                    case 5 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.65, .65), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.65, 0, -.65), 5, 0, 0, 0, 0);
                    }

                    case 6 -> world.spawnParticle(Particle.CRIT, l.clone().add(-.75, 0, -.43), 5, 0, 0, 0, 0);

                    case 7 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.8, 0), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.8, 0, 0), 5, 0, 0, 0, 0);
                    }

                    case 8 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.75, -.43), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.75, 0, .43), 5, 0, 0, 0, 0);
                    }

                    case 9 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.65, -.65), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.65, 0, .65), 5, 0, 0, 0, 0);
                    }

                    case 10 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.43, -.75), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.43, 0, .75), 5, 0, 0, 0, 0);
                    }

                    case 11 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, 0, -.8), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, 0, .8), 5, 0, 0, 0, 0);
                    }

                    case 12 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .43, -.75), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.43, 0, .75), 5, 0, 0, 0, 0);
                    }

                    case 13 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .65, -.65), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.65, 0, .65), 5, 0, 0, 0, 0);
                    }

                    case 14 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .75, -.43), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.75, 0, .43), 5, 0, 0, 0, 0);
                    }

                    case 15 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .8, 0), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.8, 0, 0), 5, 0, 0, 0, 0);
                    }

                    case 86 -> world.spawnParticle(Particle.CRIT, l.clone().add(0, -.75, .43), 5, 0, 0, 0, 0);
                }

                time--;
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playSnowStorm(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.CLOUD, l, 15, .3f, 0, .3f, 0);
                world.spawnParticle(Particle.FIREWORK, l, 2, .3f, 0, .3f, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playFireSpew(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
                world.spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
                world.spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playFootPrint(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.EGG_CRACK, l, 3, 1, 0, 1, 0);
            }
        }.runAtFixedRate(0, 20));
    }
    
    public void playHappyVillager(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.HAPPY_VILLAGER, l, 10, .5f, .5f, .5f, 0);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playAngryVillager(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.ANGRY_VILLAGER, l, 5, .5f, .5f, .5f, 0);
            }
        }.runAtFixedRate(0, 10));
    }
    
    public void playMobSpawner(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FLAME, l, 15, .5f, .5f, .5f, 0);
            }
        }.runAtFixedRate(0, 8));
    }
    
    public void startWater(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .8, .6);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(0, .1, 0), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(0, .5, 0), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(.2, .3, .2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(-.2, .3, .2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(.2, .3, -.2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(-.2, .3, -.2), 10, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playEnderSignal(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 0, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.playEffect(l, Effect.ENDER_SIGNAL, 1);
                world.playEffect(l, Effect.ENDER_SIGNAL, 1);
                world.playEffect(l, Effect.ENDER_SIGNAL, 1);
                world.playEffect(l, Effect.ENDER_SIGNAL, 1);
            }
        }.runAtFixedRate(0, 8));
    }
    
    public void playRainbow(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(r, g, b), 1);
                world.spawnParticle(Particle.DUST, l, 10, .5f, .5f, .5f, 1, dustOptions);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playSnowBlast(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .5, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.SNOWFLAKE, l, 40, 0, 0, 0, .2f);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playHalo(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1.3, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                try {
                    for (int i = 0; i < 3; i++) {
                        world.spawnParticle(Particle.DUST, l.clone().add(.5, 0, 0), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.45, 0, .13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.35, 0, .35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.13, 0, .45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(0, 0, .5), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.13, 0, .45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.35, 0, .35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.45, 0, .13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.5, 0, 0), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.45, 0, -.13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.35, 0, -.35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.13, 0, -.45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(0, 0, -.5), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.13, 0, -.45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.35, 0, -.35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.45, 0, -.13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playSantaHat(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l1 = location.clone().add(.5, 1, .5);
            final Location l2 = l1.clone().add(0, .05, 0);
            final Location l3 = l2.clone().add(0, .05, 0);
            final Location l4 = l3.clone().add(0, .05, 0);
            final Location l5 = l4.clone().add(0, .05, 0);
            final Location l6 = l5.clone().add(0, .05, 0);
            final Location l7 = l6.clone().add(0, .05, 0);
            final Location l8 = l7.clone().add(0, .05, 0);
            final Location l9 = l8.clone().add(0, .05, 0);
            final Location l10 = l9.clone().add(0, .1, 0);
            final Location l11 = l10.clone().add(0, .05, 0);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l1.clone(), 20)) return;
                try {
                    for (int i = 0; i < 3; i++) {
                        Color red = Color.RED;
                        Color white = Color.fromRGB(255, 255, 255);
                        for (Location location : getCircle(l1, .5, 20))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                        for (Location location : getCircle(l2, .4, 15))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l3, .35, 15))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l4, .3, 15))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l5, .2, 15))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l6, .15, 15))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l7, .1, 15))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l8, .05, 10))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l9, .05, 10))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l10, .05, 15))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                        for (Location location : getCircle(l11, .05, 15))
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playSoulWell(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, 0, .5);

            void startSoulWell(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);

                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone();
                    int loc = 0;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(height, 2, 50);
                        ArrayList<Location> locs2 = getCircleReverse(height, 2, 50);
                        height.getWorld().spawnParticle(Particle.WITCH, locs.get(loc), 1, 0, 0, 0, 0);
                        height.getWorld().spawnParticle(Particle.WITCH, locs2.get(loc), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;
                        height.add(0, .035, 0);
                        if (loc == 50) loc = 0;
                        if (lifeSpan == 75) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startSoulWell(l, id);
            }
        }.runAtFixedRate(0, 16));
    }
    
    public void playBigSoulWell(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, 0, .5);

            void startBigSoulWell(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone();
                    int loc = 0;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(height, 3.5, 75);
                        ArrayList<Location> locs2 = getCircleReverse(height, 3.5, 75);
                        height.getWorld().spawnParticle(Particle.WITCH, locs.get(loc), 1, 0, 0, 0, 0);
                        height.getWorld().spawnParticle(Particle.WITCH, locs2.get(loc), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;
                        height.add(0, .04, 0);
                        if (loc == 75) loc = 0;
                        if (lifeSpan == 105) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startBigSoulWell(l, id);
            }
        }.runAtFixedRate(0, 25));
    }
    
    public void playFlameWheel(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .1, .5);

            void startFlameWheel(final Location location) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location l = location.clone();
                    int i = 0;
                    int o = 74;
                    int f = 0;
                    int ringTimer = 0;

                    final World world = location.getWorld();

                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(l, 3.5, 75);
                        ArrayList<Location> locs2 = getCircleReverse(l, 3.5, 75);
                        float speed = (float) .15;
                        Vector v = locs.get(i).toVector().subtract(l.toVector()).normalize();
                        Vector v2 = locs2.get(i).toVector().subtract(l.toVector()).normalize();
                        Vector v3 = locs.get(o).toVector().subtract(l.toVector()).normalize();
                        Vector v4 = locs2.get(o).toVector().subtract(l.toVector()).normalize();
                        //Makes the ring around the edge
                        if (ringTimer == 10) {
                            for (Location i : locs) {
                                world.spawnParticle(Particle.FLAME, i, 0);
                            }
                        }

                        //Throws the fire inwords.
                        world.spawnParticle(Particle.FLAME, locs.get(i), 0, -v.getX(), 0, -v.getZ(), speed);
                        world.spawnParticle(Particle.FLAME, locs2.get(i), 0, -v2.getX(), 0, -v2.getZ(), speed);
                        world.spawnParticle(Particle.FLAME, locs.get(o), 0, -v3.getX(), 0, -v3.getZ(), speed);
                        world.spawnParticle(Particle.FLAME, locs2.get(o), 0, -v4.getX(), 0, -v4.getZ(), speed);
                        i++;
                        f++;
                        o--;
                        ringTimer++;
                        if (ringTimer == 11) ringTimer = 0;
                        if (i == 75) i = 0;
                        if (o == 0) o = 74;
                        if (f == 105) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startFlameWheel(l.clone());
            }
        }.runAtFixedRate(0, 25));
    }
    
    public void playWitchTornado(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .1, .5);

            void startWitchTornado(final Location location) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone().add(0, 5, 0);
                    int nextLocation = 0;
                    int diameterSwitch = 0;
                    double radius = 1.5;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(height, radius, 50);
                        height.getWorld().spawnParticle(Particle.WITCH, locs.get(nextLocation), 0, 0, 0, 0, 1);
                        nextLocation++;
                        diameterSwitch++;
                        lifeSpan++;
                        if (nextLocation == 50) nextLocation = 0;
                        height.add(0, -.02, 0); //Controls how far each particle goes Down.
                        if (diameterSwitch == 7) { //Controls when diameter Changes.
                            diameterSwitch = 0;
                            radius = radius - .05; //Controls how far it goes in.
                        }
                        if (lifeSpan == 207) { //Controls how far the particle effect go down.
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startWitchTornado(l);
            }
        }.runAtFixedRate(0, 30));
    }
    
    public void playLoveTornado(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, 0, .5);

            void startLoveTornado(final Location location) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {

                    final Location height = location.clone().add(0, 5, 0);
                    int diameterShrink = 0;
                    double radius = 1.5;
                    int lifeSpan = 0;
                    int nextLocation = 0;

                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(height, radius, 50);
                        height.getWorld().spawnParticle(Particle.HEART, locs.get(nextLocation), 0, 0, 0, 0, 1);
                        diameterShrink++;
                        lifeSpan++;
                        nextLocation++;
                        if (nextLocation == 50) nextLocation = 0; //Controls the next x & z locations.
                        height.add(0, -.02, 0); //Controls how far each particle goes Down.
                        if (diameterShrink == 7) { //Controls when diameter Changes.
                            diameterShrink = 0;
                            radius = radius - .05; //Controls how far it goes in.
                        }
                        if (lifeSpan == 207) { //Controls how far the particle effect go down.
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startLoveTornado(l);
            }
        }.runAtFixedRate(0, 30));
    }
    
    public void playBigLoveWell(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, 0, .5);

            void startBigLoveWell(final Location location) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone();
                    int loc = 0;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(height, 3.5, 75);
                        ArrayList<Location> locs2 = getCircleReverse(height, 3.5, 75);
                        height.getWorld().spawnParticle(Particle.HEART, locs.get(loc), 1, 0, 0, 0, 0);
                        height.getWorld().spawnParticle(Particle.HEART, locs2.get(loc), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;
                        height.add(0, .04, 0);
                        if (loc == 75) loc = 0;
                        if (lifeSpan == 105) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startBigLoveWell(l);
            }
        }.runAtFixedRate(0, 25));
    }
    
    public void playLoveWell(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            Location l = location.clone().add(.5, 0, .5);

            void startLoveWell(final Location location) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone();
                    int loc = 0;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(height, 2, 50);
                        ArrayList<Location> locs2 = getCircleReverse(height, 2, 50);
                        height.getWorld().spawnParticle(Particle.HEART, locs.get(loc), 1, 0, 0, 0, 0);
                        height.getWorld().spawnParticle(Particle.HEART, locs2.get(loc), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;
                        height.add(0, .035, 0);
                        if (loc == 50) loc = 0;
                        if (lifeSpan == 75) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startLoveWell(l);
            }
        }.runAtFixedRate(0, 16));
    }
    
    private ArrayList<Location> getCircle(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }

        return locations;
    }
    
    private ArrayList<Location> getCircleReverse(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() - (radius * Math.cos(angle));
            double z = center.getZ() - (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }

        return locations;
    }

    private boolean noPlayers(Location location, double range) {
        double rangeSquared = range * range;
        World world = location.getWorld();

        for (Player player : world.getPlayers()) {
            if (distanceSquared(player.getX(), player.getY(), player.getZ(), location) <= rangeSquared) {
                return false;
            }
        }
        return true;
    }

    private static double distanceSquared(double x, double y, double z, Location location) {
        return NumberConversions.square(x - location.getX())
                + NumberConversions.square(y - location.getY())
                + NumberConversions.square(z - location.getZ());
    }

    private int randomColor() {
        return random.nextInt(255);
    }
    
}
