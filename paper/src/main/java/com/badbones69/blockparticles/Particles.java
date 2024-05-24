package com.badbones69.blockparticles;

import com.badbones69.blockparticles.controllers.ParticleControl;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Particles implements ParticleControl {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);
    
    private final HashMap<String, Integer> locations = new HashMap<>();
    private final int range = 25;
    
    private Location randomDrop(final Location location) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        double x = random.nextInt(100) / 100.0 - .50;
        double z = random.nextInt(100) / 100.0 - .50;

        return location.add(x, 0, z);
    }
    
    private float randomVector() {
        return (float) -.05 + (float) (Math.random() * ((.05 - -.05)));
    }
    
    public final HashMap<String, Integer> getLocations() {
        return this.locations;
    }
    
    public void playVolcano(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .8, .5).clone();
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                world.spawnParticle(Particle.LAVA, copy, 10, 0, 0, 0, 0);
            }
        }, 0, 4));
    }
    
    public void playBigFlame(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .1, .5).clone();
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                for (Location location : getCircle(copy, 1, 15)) world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
                for (Location location : getCircle(copy, 2, 25)) world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
            }
        }, 0, 5));
    }
    
    public void playFlame(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .1, .5).clone();
            final World world = copy.getWorld();

            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                for (Location location : getCircle(copy, .6, 15)) world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
                for (Location location : getCircle(copy, 1, 20)) world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
            }
        }, 0, 5));
    }
    
    public void playDoubleSpiral(final Location location, final String id, final com.badbones69.blockparticles.api.enums.particles.Particles particles, final int amount) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .7, .5).clone();
            final World world = copy.getWorld();

            int time = 16;

            final Particle particle = particles == com.badbones69.blockparticles.api.enums.particles.Particles.DOUBLEWITCH ? Particle.WITCH : Particle.FIREWORK;
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                if (time == 15) {
                    world.spawnParticle(particle, copy.add(.8, 0, 0), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(-.8, 0, 0), amount, 0, 0, 0, 0);
                }
                if (time == 14) {
                    world.spawnParticle(particle, copy.add(.75, 0, .43), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                }
                if (time == 13) {
                    world.spawnParticle(particle, copy.add(.65, 0, .65), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                }
                if (time == 12) {
                    world.spawnParticle(particle, copy.add(.43, 0, .75), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                }
                if (time == 11) {
                    world.spawnParticle(particle, copy.add(0, 0, .8), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(0, 0, -.8), amount, 0, 0, 0, 0);
                }
                if (time == 10) {
                    world.spawnParticle(particle, copy.add(-.43, 0, .75), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(.43, 0, -.75), amount, 0, 0, 0, 0);
                }
                if (time == 9) {
                    world.spawnParticle(particle, copy.add(-.65, 0, .65), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(.65, 0, -.65), amount, 0, 0, 0, 0);
                }
                if (time == 8) {
                    world.spawnParticle(particle, copy.add(-.75, 0, .43), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(.75, 0, -.43), amount, 0, 0, 0, 0);
                }
                if (time == 7) {
                    world.spawnParticle(particle, copy.add(-.8, 0, 0), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(.8, 0, 0), amount, 0, 0, 0, 0);
                }
                if (time == 6) {
                    world.spawnParticle(particle, copy.add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(.75, 0, .43), amount, 0, 0, 0, 0);
                }
                if (time == 5) {
                    world.spawnParticle(particle, copy.add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(.65, 0, .65), amount, 0, 0, 0, 0);
                }
                if (time == 4) {
                    world.spawnParticle(particle, copy.add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(.43, 0, .75), amount, 0, 0, 0, 0);
                }
                if (time == 3) {
                    world.spawnParticle(particle, copy.add(0, 0, -.8), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(0, 0, .8), amount, 0, 0, 0, 0);
                }
                if (time == 2) {
                    world.spawnParticle(particle, copy.add(.43, 0, -.75), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(-.43, 0, .75), amount, 0, 0, 0, 0);
                }
                if (time == 1) {
                    world.spawnParticle(particle, copy.add(.65, 0, -.65), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(-.65, 0, .65), amount, 0, 0, 0, 0);
                }
                if (time == 0) {
                    world.spawnParticle(particle, copy.add(.75, 0, -.43), amount, 0, 0, 0, 0);
                    world.spawnParticle(particle, copy.add(-.75, 0, .43), amount, 0, 0, 0, 0);
                    time = 16;
                }
                time--;
            }
        }, 0, 2));
    }
    
    public void playSpiral(final Location location, final String id, final com.badbones69.blockparticles.api.enums.particles.Particles particles, final int amount) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .7, .5);
            final World world = copy.getWorld();

            int time = 16;

            final Particle particle = particles == com.badbones69.blockparticles.api.enums.particles.Particles.WITCH ? Particle.WITCH : Particle.FIREWORK;
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                if (time == 15) world.spawnParticle(particle, copy.add(.8, 0, 0), amount, 0, 0, 0, 0);
                if (time == 14) world.spawnParticle(particle, copy.add(.75, 0, .43), amount, 0, 0, 0, 0);
                if (time == 13) world.spawnParticle(particle, copy.add(.65, 0, .65), amount, 0, 0, 0, 0);
                if (time == 12) world.spawnParticle(particle, copy.add(.43, 0, .75), amount, 0, 0, 0, 0);
                if (time == 11) world.spawnParticle(particle, copy.add(0, 0, .8), amount, 0, 0, 0, 0);
                if (time == 10) world.spawnParticle(particle, copy.add(-.43, 0, .75), amount, 0, 0, 0, 0);
                if (time == 9) world.spawnParticle(particle, copy.add(-.65, 0, .65), amount, 0, 0, 0, 0);
                if (time == 8) world.spawnParticle(particle, copy.add(-.75, 0, .43), amount, 0, 0, 0, 0);
                if (time == 7) world.spawnParticle(particle, copy.add(-.8, 0, 0), amount, 0, 0, 0, 0);
                if (time == 6) world.spawnParticle(particle, copy.add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                if (time == 5) world.spawnParticle(particle, copy.add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                if (time == 4) world.spawnParticle(particle, copy.add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                if (time == 3) world.spawnParticle(particle, copy.add(0, 0, -.8), amount, 0, 0, 0, 0);
                if (time == 2) world.spawnParticle(particle, copy.add(.43, 0, -.75), amount, 0, 0, 0, 0);
                if (time == 1) world.spawnParticle(particle, copy.add(.65, 0, -.65), amount, 0, 0, 0, 0);

                if (time == 0) {
                    world.spawnParticle(particle, copy.add(.75, 0, -.43), amount, 0, 0, 0, 0);
                    time = 16;
                }

                time--;
            }
        }, 0, 2));
    }
    
    public void playCrit(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 1.1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.CRIT, copy, 1, 0, 0, 0, 0);
            }
        }, 0, 2));
    }
    
    public void playBigCrit(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .5, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                for (Location location : getCircle(copy, 2, 20)) world.spawnParticle(Particle.CRIT, location, 1, 0, 0, 0, 0);
            }
        }, 0, 2));
    }

    public void playStorm(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 2, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.CLOUD, copy, 15, .3f, 0, 0.3f, 0);
                world.spawnParticle(Particle.FALLING_WATER, copy.add(0, 0, .1), 10, 0.2f, 0, 0.2f, 0);
            }
        }, 0, 2));
    }
    
    public void playFog(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .5, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.CLOUD, copy, 20, .3f, 0, .3f, 0.05f);
            }
        }, 0, 2));
    }
    
    public void playEnchant(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 1.5, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.ENCHANT, copy, 20, 0, 0, 0, 2);
            }
        }, 0, 2));
    }
    
    public void playChains(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.FLAME, copy.add(1, 0, 1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.9, .1, .9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.8, .2, .8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.7, .3, .7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.6, .4, .6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.5, .6, .5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.4, .8, .4), 1, 0, 0, 0, 0);
                
                world.spawnParticle(Particle.FLAME, copy.add(-1, 0, 1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.9, .1, .9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.8, .2, .8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.7, .3, .7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.6, .4, .6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.5, .6, .5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.4, .8, .4), 1, 0, 0, 0, 0);
                
                world.spawnParticle(Particle.FLAME, copy.add(-1, 0, -1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.9, .1, -.9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.8, .2, -.8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.7, .3, -.7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.6, .4, -.6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.5, .6, -.5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(-.4, .8, -.4), 1, 0, 0, 0, 0);
                
                world.spawnParticle(Particle.FLAME, copy.add(1, 0, -1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.9, .1, -.9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.8, .2, -.8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.7, .3, -.7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.6, .4, -.6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.5, .6, -.5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, copy.add(.4, .8, -.4), 1, 0, 0, 0, 0);
            }
        }, 0, 5));
    }
    
    public void playFireStorm(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 2, .5);

            final World world = copy.getWorld();
            
            @Override
            public void run() {
                try {
                    if (!noPlayers(copy, range)) {
                        world.spawnParticle(Particle.LARGE_SMOKE, copy, 15, 0.3f, 0, 0.3f, 0);
                        world.spawnParticle(Particle.FLAME, randomDrop(copy), 0, 0, -0.2f, 0, 1);
                        world.spawnParticle(Particle.FLAME, randomDrop(copy), 0, 0, -0.2f, 0, 1);
                    }
                } catch (Exception e) {
                    plugin.getServer().getScheduler().cancelTask(locations.get(id));
                    
                    e.printStackTrace();
                }
            }
        }, 0, 2));
    }
    
    public void playSnow(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 2, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.FIREWORK, copy, 1, .7f, .7f, .7f, 0);
            }
        }, 0, 2));
    }
    
    public void playSpew(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.FIREWORK, copy, 0, randomVector(), .1f, randomVector(), 1);
            }
        }, 0, 2));
    }
    
    public void playPotion(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .2, .5);
            final World world = copy.getWorld();

            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.INSTANT_EFFECT, copy, 6, .3f, 0, .3f, randomColor());
                world.spawnParticle(Particle.INSTANT_EFFECT, copy, 6, .3f, 0, .3f, randomColor());
                world.spawnParticle(Particle.INSTANT_EFFECT, copy, 6, .3f, 0, .3f, randomColor());
            }
        }, 0, 2));
    }
    
    public void playMusic(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .2, .5);
            final World world = copy.getWorld();
            final List<Location> locs = getCircle(copy, 1, 16);

            int time = 0;
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                int i = time;

                if (time == 15) {
                    world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                    
                    time = -1;
                }

                if (time == 14) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 13) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 12) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 11) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 10) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 9) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 8) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 7) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 6) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 5) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 4) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 3) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 2) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 1) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 0) world.spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());

                time++;
            }
        }, 0, 2));
    }
    
    public void playMagic(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .5, .5);
            final World world = copy.getWorld();
            int time = 16;
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                
                if (time == 15) world.spawnParticle(Particle.CRIT, copy.add(0, .8, 0), 5, 0, 0, 0, 0);
                if (time == 0) world.spawnParticle(Particle.CRIT, copy.add(0, .75, .43), 5, 0, 0, 0, 0);
                if (time == 1) world.spawnParticle(Particle.CRIT, copy.add(0, .65, .65), 5, 0, 0, 0, 0);
                if (time == 2) world.spawnParticle(Particle.CRIT, copy.add(0, .43, .75), 5, 0, 0, 0, 0);
                if (time == 3) world.spawnParticle(Particle.CRIT, copy.add(0, 0, .8), 5, 0, 0, 0, 0);
                if (time == 4) world.spawnParticle(Particle.CRIT, copy.add(0, -.43, .75), 5, 0, 0, 0, 0);
                if (time == 5) world.spawnParticle(Particle.CRIT, copy.add(0, -.65, .65), 5, 0, 0, 0, 0);
                if (time == 86) world.spawnParticle(Particle.CRIT, copy.add(0, -.75, .43), 5, 0, 0, 0, 0);
                if (time == 7) world.spawnParticle(Particle.CRIT, copy.add(0, -.8, 0), 5, 0, 0, 0, 0);
                if (time == 8) world.spawnParticle(Particle.CRIT, copy.add(0, -.75, -.43), 5, 0, 0, 0, 0);
                if (time == 9) world.spawnParticle(Particle.CRIT, copy.add(0, -.65, -.65), 5, 0, 0, 0, 0);
                if (time == 10) world.spawnParticle(Particle.CRIT, copy.add(0, -.43, -.75), 5, 0, 0, 0, 0);
                if (time == 11) world.spawnParticle(Particle.CRIT, copy.add(0, 0, -.8), 5, 0, 0, 0, 0);
                if (time == 12) world.spawnParticle(Particle.CRIT, copy.add(0, .43, -.75), 5, 0, 0, 0, 0);
                if (time == 13) world.spawnParticle(Particle.CRIT, copy.add(0, .65, -.65), 5, 0, 0, 0, 0);
                if (time == 14) world.spawnParticle(Particle.CRIT, copy.add(0, .75, -.43), 5, 0, 0, 0, 0);
                
                if (time == 15) world.spawnParticle(Particle.CRIT, copy.add(.8, 0, 0), 5, 0, 0, 0, 0);
                if (time == 14) world.spawnParticle(Particle.CRIT, copy.add(.75, 0, .43), 5, 0, 0, 0, 0);
                if (time == 13) world.spawnParticle(Particle.CRIT, copy.add(.65, 0, .65), 5, 0, 0, 0, 0);
                if (time == 12) world.spawnParticle(Particle.CRIT, copy.add(.43, 0, .75), 5, 0, 0, 0, 0);
                if (time == 11) world.spawnParticle(Particle.CRIT, copy.add(0, 0, .8), 5, 0, 0, 0, 0);
                if (time == 10) world.spawnParticle(Particle.CRIT, copy.add(-.43, 0, .75), 5, 0, 0, 0, 0);
                if (time == 9) world.spawnParticle(Particle.CRIT, copy.add(-.65, 0, .65), 5, 0, 0, 0, 0);
                if (time == 8) world.spawnParticle(Particle.CRIT, copy.add(-.75, 0, .43), 5, 0, 0, 0, 0);
                if (time == 7) world.spawnParticle(Particle.CRIT, copy.add(-.8, 0, 0), 5, 0, 0, 0, 0);
                if (time == 6) world.spawnParticle(Particle.CRIT, copy.add(-.75, 0, -.43), 5, 0, 0, 0, 0);
                if (time == 5) world.spawnParticle(Particle.CRIT, copy.add(-.65, 0, -.65), 5, 0, 0, 0, 0);
                if (time == 4) world.spawnParticle(Particle.CRIT, copy.add(-.43, 0, -.75), 5, 0, 0, 0, 0);
                if (time == 3) world.spawnParticle(Particle.CRIT, copy.add(0, 0, -.8), 5, 0, 0, 0, 0);
                if (time == 2) world.spawnParticle(Particle.CRIT, copy.add(.43, 0, -.75), 5, 0, 0, 0, 0);
                if (time == 1) world.spawnParticle(Particle.CRIT, copy.add(.65, 0, -.65), 5, 0, 0, 0, 0);
                
                if (time == 0) {
                    world.spawnParticle(Particle.CRIT, copy.add(.75, 0, -.43), 5, 0, 0, 0, 0);
                    
                    time = 16;
                }
                
                time--;
            }
        }, 0, 2));
    }
    
    public void playSnowStorm(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 2, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                
                world.spawnParticle(Particle.CLOUD, copy, 15, .3f, 0, .3f, 0);
                world.spawnParticle(Particle.FIREWORK, copy, 2, .3f, 0, .3f, 0);
            }
        }, 0, 2));
    }
    
    public void playFireSpew(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                
                world.spawnParticle(Particle.FLAME, copy, 0, randomVector(), .1f, randomVector(), 1.5f);
                world.spawnParticle(Particle.FLAME, copy, 0, randomVector(), .1f, randomVector(), 1.5f);
                world.spawnParticle(Particle.FLAME, copy, 0, randomVector(), .1f, randomVector(), 1.5f);
            }
        }, 0, 2));
    }
    
    public void playFootPrint(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                
                world.spawnParticle(Particle.EGG_CRACK, copy, 3, 1, 0, 1, 0);
            }
        }, 0, 20));
    }
    
    public void playHappyVillager(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                
                world.spawnParticle(Particle.HAPPY_VILLAGER, copy, 10, .5f, .5f, .5f, 0);
            }
        }, 0, 5));
    }
    
    public void playAngryVillager(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                world.spawnParticle(Particle.ANGRY_VILLAGER, copy, 5, .5f, .5f, .5f, 0);
            }
        }, 0, 10));
    }
    
    public void playMobSpawner(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                world.spawnParticle(Particle.FLAME, copy, 15, .5f, .5f, .5f, 0);
            }
        }, 0, 8));
    }
    
    public void startWater(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .8, .6);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                world.spawnParticle(Particle.FALLING_WATER, copy.add(0, .1, 0), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, copy.add(0, .5, 0), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, copy.add(.2, .3, .2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, copy.add(-.2, .3, .2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, copy.add(.2, .3, -.2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, copy.add(-.2, .3, -.2), 10, 0, 0, 0, 0);
            }
        }, 0, 2));
    }
    
    public void playEnderSignal(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 0, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                
                world.playEffect(copy, Effect.ENDER_SIGNAL, 1);
                world.playEffect(copy, Effect.ENDER_SIGNAL, 1);
                world.playEffect(copy, Effect.ENDER_SIGNAL, 1);
                world.playEffect(copy, Effect.ENDER_SIGNAL, 1);
            }
        }, 0, 8));
    }
    
    public void playRainbow(final Location location, final String id) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .1, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);

                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(r, g, b), 1);
                world.spawnParticle(Particle.DUST, copy, 10, .5f, .5f, .5f, 1, dustOptions);
            }
        }, 0, 5));
    }
    
    public void playSnowBlast(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, .5, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                world.spawnParticle(Particle.SNOWFLAKE, copy, 40, 0, 0, 0, .2f);
            }
        }, 0, 2));
    }
    
    public void playHalo(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.add(.5, 1.3, .5);
            final World world = copy.getWorld();
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                try {
                    for (int i = 0; i < 3; i++) {
                        world.spawnParticle(Particle.DUST, copy.add(.5, 0, 0), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(.45, 0, .13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(.35, 0, .35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(.13, 0, .45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(0, 0, .5), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(-.13, 0, .45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(-.35, 0, .35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(-.45, 0, .13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(-.5, 0, 0), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(-.45, 0, -.13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(-.35, 0, -.35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(-.13, 0, -.45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(0, 0, -.5), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(.13, 0, -.45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(.35, 0, -.35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, copy.add(.45, 0, -.13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5));
    }
    
    public void playSantaHat(final Location location, final String id) {
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
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

            final World world = l1.getWorld();

            @Override
            public void run() {
                if (noPlayers(l1, 20)) return;

                try {
                    for (int i = 0; i < 3; i++) {
                        Color red = Color.RED;
                        Color white = Color.fromRGB(255, 255, 255);

                        for (Location location : getCircle(l1, .5, 20)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                        for (Location location : getCircle(l2, .4, 15)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l3, .35, 15)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l4, .3, 15)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l5, .2, 15)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l6, .15, 15)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l7, .1, 15)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l8, .05, 10)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l9, .05, 10)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l10, .05, 15)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                        for (Location location : getCircle(l11, .05, 15)) world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5));
    }
    
    public void playSoulWell(final Location location, final String id) {
        final Map<Integer, Integer> keys = new HashMap<>();

        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.clone().add(.5, 0, .5);
            
            void startSoulWell(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

                keys.put(num, plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    final Location height = location.clone();
                    final World world = height.getWorld();

                    int loc = 0;
                    int lifeSpan = 0;
                    
                    @Override
                    public void run() {
                        List<Location> locs = getCircle(height, 2, 50);
                        List<Location> locs2 = getCircleReverse(height, 2, 50);

                        world.spawnParticle(Particle.WITCH, locs.get(loc), 1, 0, 0, 0, 0);
                        world.spawnParticle(Particle.WITCH, locs2.get(loc), 1, 0, 0, 0, 0);

                        loc++;
                        lifeSpan++;

                        height.add(0, .035, 0);

                        if (loc == 50) loc = 0;
                        if (lifeSpan == 75) {
                            plugin.getServer().getScheduler().cancelTask(keys.get(num));
                            keys.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                startSoulWell(copy, id);
            }
        }, 0, 16));
    }
    
    public void playBigSoulWell(final Location location, final String id) {
        final Map<Integer, Integer> keys = new HashMap<>();

        this.locations.put(id, plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.clone().add(.5, 0, .5);
            
            void startBigSoulWell(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                keys.put(num, plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    final Location height = location.clone();
                    final World world = height.getWorld();
                    int loc = 0;
                    int lifeSpan = 0;
                    
                    @Override
                    public void run() {
                        List<Location> locs = getCircle(height, 3.5, 75);
                        List<Location> locs2 = getCircleReverse(height, 3.5, 75);

                        world.spawnParticle(Particle.WITCH, locs.get(loc), 1, 0, 0, 0, 0);
                        world.spawnParticle(Particle.WITCH, locs2.get(loc), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;

                        height.add(0, .04, 0);

                        if (loc == 75) loc = 0;

                        if (lifeSpan == 105) {
                            plugin.getServer().getScheduler().cancelTask(keys.get(num));
                            keys.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                startBigSoulWell(copy, id);
            }
        }, 0, 25));
    }
    
    public void playFlameWheel(final Location location, final String id) {
        final HashMap<Integer, Integer> keys = new HashMap<>();

        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.clone().add(.5, .1, .5);
            
            void startFlameWheel(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

                keys.put(num, plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    final Location copy = location.clone();
                    final World world = copy.getWorld();
                    int i = 0;
                    int o = 74;
                    int f = 0;
                    int ringTimer = 0;
                    
                    @Override
                    public void run() {
                        List<Location> locs = getCircle(copy, 3.5, 75);
                        List<Location> locs2 = getCircleReverse(copy, 3.5, 75);
                        float speed = (float) .15;

                        Vector v = locs.get(i).toVector().subtract(copy.toVector()).normalize();
                        Vector v2 = locs2.get(i).toVector().subtract(copy.toVector()).normalize();
                        Vector v3 = locs.get(o).toVector().subtract(copy.toVector()).normalize();
                        Vector v4 = locs2.get(o).toVector().subtract(copy.toVector()).normalize();

                        //Makes the ring around the edge
                        if (ringTimer == 10) {
                            for (Location i : locs) {
                                world.spawnParticle(Particle.FLAME, i, 0);
                            }
                        }

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
                            plugin.getServer().getScheduler().cancelTask(keys.get(num));

                            keys.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                startFlameWheel(copy, id);
            }
        }, 0, 25));
    }
    
    public void playWitchTornado(final Location location, final String id) {
        final HashMap<Integer, Integer> keys = new HashMap<>();

        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.clone().add(.5, 0, .5);
            
            void startWitchTornado(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                keys.put(num, plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    final Location height = location.clone().add(0, 5, 0);
                    final World world = height.getWorld();

                    int nextLocation = 0;
                    int diameterSwitch = 0;
                    double radius = 1.5;
                    int lifeSpan = 0;
                    
                    @Override
                    public void run() {
                        List<Location> locs = getCircle(height, radius, 50);
                        world.spawnParticle(Particle.WITCH, locs.get(nextLocation), 0, 0, 0, 0, 1);

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
                            plugin.getServer().getScheduler().cancelTask(keys.get(num));
                            keys.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;
                
                startWitchTornado(copy, id);
            }
        }, 0, 30));
    }
    
    public void playLoveTornado(final Location location, final String id) {
        final Map<Integer, Integer> keys = new HashMap<>();
        
        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.clone().add(.5, 0, .5);
            
            void startLoveTornado(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                
                keys.put(num, plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    final Location height = location.clone().add(0, 5, 0);
                    final World world = height.getWorld();
                    int diameterShrink = 0;
                    double radius = 1.5;
                    int lifeSpan = 0;
                    int nextLocation = 0;
                    
                    @Override
                    public void run() {
                        List<Location> locs = getCircle(height, radius, 50);
                        world.spawnParticle(Particle.HEART, locs.get(nextLocation), 0, 0, 0, 0, 1);
                        
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
                            plugin.getServer().getScheduler().cancelTask(keys.get(num));

                            keys.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                startLoveTornado(copy, id);
            }
        }, 0, 30));
    }
    
    public void playBigLoveWell(final Location location, final String id) {
        final HashMap<Integer, Integer> keys = new HashMap<>();

        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            final Location copy = location.clone().add(.5, 0, .5);
            
            void startBigLoveWell(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

                keys.put(num, plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    final Location height = location.clone();
                    final World world = height.getWorld();

                    int loc = 0;
                    int lifeSpan = 0;
                    
                    @Override
                    public void run() {
                        List<Location> locs = getCircle(height, 3.5, 75);
                        List<Location> locs2 = getCircleReverse(height, 3.5, 75);

                        world.spawnParticle(Particle.HEART, locs.get(loc), 1, 0, 0, 0, 0);
                        world.spawnParticle(Particle.HEART, locs2.get(loc), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;

                        height.add(0, .04, 0);

                        if (loc == 75) loc = 0;

                        if (lifeSpan == 105) {
                            plugin.getServer().getScheduler().cancelTask(keys.get(num));
                            keys.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                startBigLoveWell(copy, id);
            }
        }, 0, 25));
    }
    
    public void playLoveWell(final Location location, final String id) {
        final HashMap<Integer, Integer> keys = new HashMap<>();

        this.locations.put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            final Location copy = location.clone().add(.5, 0, .5);
            
            void startLoveWell(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

                keys.put(num, plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    final Location height = location.clone();
                    final World world = height.getWorld();
                    int loc = 0;
                    int lifeSpan = 0;
                    
                    @Override
                    public void run() {
                        List<Location> locs = getCircle(height, 2, 50);
                        List<Location> locs2 = getCircleReverse(height, 2, 50);

                        world.spawnParticle(Particle.HEART, locs.get(loc), 1, 0, 0, 0, 0);
                        world.spawnParticle(Particle.HEART, locs2.get(loc), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;

                        height.add(0, .035, 0);

                        if (loc == 50) loc = 0;

                        if (lifeSpan == 75) {
                            plugin.getServer().getScheduler().cancelTask(keys.get(num));
                            keys.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(copy, range)) return;

                startLoveWell(copy, id);
            }
        }, 0, 16));
    }
    
    private List<Location> getCircle(final Location center, final double radius, final int amount) {
        final World world = center.getWorld();
        final double increment = (2 * Math.PI) / amount;

        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            final double angle = i * increment;
            final double x = center.getX() + (radius * Math.cos(angle));
            final double z = center.getZ() + (radius * Math.sin(angle));

            locations.add(new Location(world, x, center.getY(), z));
        }

        return locations;
    }
    
    private List<Location> getCircleReverse(final Location center, final double radius, final int amount) {
        final World world = center.getWorld();

        final double increment = (2 * Math.PI) / amount;
        final List<Location> locations = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            final double angle = i * increment;
            final double x = center.getX() - (radius * Math.cos(angle));
            final double z = center.getZ() - (radius * Math.sin(angle));

            locations.add(new Location(world, x, center.getY(), z));
        }

        return locations;
    }
    
    private Collection<Entity> getNearbyEntities(final Location location, final double x, final double y, final double z) {
        try {
            return location.getWorld().getNearbyEntities(location, x, y, z);
        } catch (Exception ignored) {}

        return new ArrayList<>();
    }
    
    private boolean noPlayers(final Location location, final int range) {
        try {
            Collection<Entity> out = getNearbyEntities(location, range, range, range);

            if (!out.isEmpty()) {
                for (Entity e : out) {
                    if (e instanceof LivingEntity en) {
                        if (en instanceof Player) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception ignored) {}

        return true;
    }
    
    private int randomColor() {
        return ThreadLocalRandom.current().nextInt(255);
    }
}