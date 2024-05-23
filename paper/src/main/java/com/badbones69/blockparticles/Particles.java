package com.badbones69.blockparticles;

import com.badbones69.blockparticles.controllers.ParticleControl;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Particles implements ParticleControl {
    
    private HashMap<String, Integer> locations = new HashMap<>();
    private Plugin plugin = Bukkit.getPluginManager().getPlugin("BlockParticles");
    private int range = 25;
    private Random random = new Random();
    
    private Location randomDrop(Location location) {
        double x = random.nextInt(100) / 100.0 - .50;
        double z = random.nextInt(100) / 100.0 - .50;
        return location.add(x, 0, z);
    }
    
    private float randomVector() {
        return (float) -.05 + (float) (Math.random() * ((.05 - -.05)));
    }
    
    public HashMap<String, Integer> getLocations() {
        return locations;
    }
    
    public void playVolcano(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .8, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.LAVA, l, 10, 0, 0, 0, 0);
            }
        }, 0, 4));
    }
    
    public void playBigFlame(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.clone().add(.5, .1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                for (Location location : getCircle(l, 1, 15))
                    l.getWorld().spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
                for (Location location : getCircle(l, 2, 25))
                    l.getWorld().spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
            }
        }, 0, 5));
    }
    
    public void playFlame(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                for (Location location : getCircle(l, .6, 15))
                    l.getWorld().spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
                for (Location location : getCircle(l, 1, 20))
                    l.getWorld().spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
            }
        }, 0, 5));
    }
    
    public void playDoubleSpiral(final Location location, String id, com.badbones69.blockparticles.api.enums.Particles particles, int amount) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .7, .5);
            int time = 16;
            Particle particle = particles == com.badbones69.blockparticles.api.enums.Particles.DOUBLEWITCH ? Particle.WITCH : Particle.FIREWORK;
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                if (time == 15) {
                    l.getWorld().spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                }
                if (time == 14) {
                    l.getWorld().spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                }
                if (time == 13) {
                    l.getWorld().spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                }
                if (time == 12) {
                    l.getWorld().spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                }
                if (time == 11) {
                    l.getWorld().spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                }
                if (time == 10) {
                    l.getWorld().spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                }
                if (time == 9) {
                    l.getWorld().spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                }
                if (time == 8) {
                    l.getWorld().spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                }
                if (time == 7) {
                    l.getWorld().spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                }
                if (time == 6) {
                    l.getWorld().spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                }
                if (time == 5) {
                    l.getWorld().spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                }
                if (time == 4) {
                    l.getWorld().spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                }
                if (time == 3) {
                    l.getWorld().spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                }
                if (time == 2) {
                    l.getWorld().spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                }
                if (time == 1) {
                    l.getWorld().spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                }
                if (time == 0) {
                    l.getWorld().spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                    l.getWorld().spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                    time = 16;
                }
                time--;
            }
        }, 0, 2));
    }
    
    public void playSpiral(final Location location, String id, com.badbones69.blockparticles.api.enums.Particles particles, int amount) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .7, .5);
            int time = 16;
            Particle particle = particles == com.badbones69.blockparticles.api.enums.Particles.WITCH ? Particle.WITCH : Particle.FIREWORK;
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                if (time == 15) l.getWorld().spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                if (time == 14) l.getWorld().spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                if (time == 13) l.getWorld().spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                if (time == 12) l.getWorld().spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                if (time == 11) l.getWorld().spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                if (time == 10) l.getWorld().spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                if (time == 9) l.getWorld().spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                if (time == 8) l.getWorld().spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                if (time == 7) l.getWorld().spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                if (time == 6) l.getWorld().spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                if (time == 5) l.getWorld().spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                if (time == 4) l.getWorld().spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                if (time == 3) l.getWorld().spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                if (time == 2) l.getWorld().spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                if (time == 1) l.getWorld().spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                if (time == 0) {
                    l.getWorld().spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                    time = 16;
                }
                time--;
            }
        }, 0, 2));
    }
    
    public void playCrit(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 1.1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.CRIT, l.clone(), 1, 0, 0, 0, 0);
            }
        }, 0, 2));
    }
    
    public void playBigCrit(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .5, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                for (Location location : getCircle(l, 2, 20))
                    l.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0, 0);
            }
        }, 0, 2));
    }
    
    public void playStorm(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 2, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.CLOUD, l.clone(), 15, .3f, 0, 0.3f, 0);
                l.getWorld().spawnParticle(Particle.FALLING_WATER, l.clone().add(0, 0, .1), 10, 0.2f, 0, 0.2f, 0);
            }
        }, 0, 2));
    }
    
    public void playFog(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .5, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.CLOUD, l, 20, .3f, 0, .3f, 0.05f);
            }
        }, 0, 2));
    }
    
    public void playEnchant(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 1.5, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.ENCHANT, l, 20, 0, 0, 0, 2);
            }
        }, 0, 2));
    }
    
    public void playChains(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(1, 0, 1), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.9, .1, .9), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.8, .2, .8), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.7, .3, .7), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.6, .4, .6), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.5, .6, .5), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.4, .8, .4), 1, 0, 0, 0, 0);
                
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-1, 0, 1), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.9, .1, .9), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.8, .2, .8), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.7, .3, .7), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.6, .4, .6), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.5, .6, .5), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.4, .8, .4), 1, 0, 0, 0, 0);
                
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-1, 0, -1), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.9, .1, -.9), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.8, .2, -.8), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.7, .3, -.7), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.6, .4, -.6), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.5, .6, -.5), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(-.4, .8, -.4), 1, 0, 0, 0, 0);
                
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(1, 0, -1), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.9, .1, -.9), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.8, .2, -.8), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.7, .3, -.7), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.6, .4, -.6), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.5, .6, -.5), 1, 0, 0, 0, 0);
                l.getWorld().spawnParticle(Particle.FLAME, l.clone().add(.4, .8, -.4), 1, 0, 0, 0, 0);
            }
        }, 0, 5));
    }
    
    public void playFireStorm(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 2, .5);
            
            @Override
            public void run() {
                try {
                    if (!noPlayers(l.clone(), range)) {
                        l.getWorld().spawnParticle(Particle.LARGE_SMOKE, l, 15, 0.3f, 0, 0.3f, 0);
                        l.getWorld().spawnParticle(Particle.FLAME, randomDrop(l.clone()), 0, 0, -0.2f, 0, 1);
                        l.getWorld().spawnParticle(Particle.FLAME, randomDrop(l.clone()), 0, 0, -0.2f, 0, 1);
                    }
                } catch (Exception e) {
                    Bukkit.getServer().getScheduler().cancelTask(locations.get(id));
                    e.printStackTrace();
                }
            }
        }, 0, 2));
    }
    
    public void playSnow(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 2, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.FIREWORK, l, 1, .7f, .7f, .7f, 0);
            }
        }, 0, 2));
    }
    
    public void playSpew(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.FIREWORK, l, 0, randomVector(), .1f, randomVector(), 1);
            }
        }, 0, 2));
    }
    
    public void playPotion(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .2, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
                l.getWorld().spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
                l.getWorld().spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
            }
        }, 0, 2));
    }
    
    public void playMusic(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .2, .5);
            ArrayList<Location> locs = getCircle(l, 1, 16);
            int time = 0;
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                int i = time;
                if (time == 15) {
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                    time = -1;
                }
                if (time == 14)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 13)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 12)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 11)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 10)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 9)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 8)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 7)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 6)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 5)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 4)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 3)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 2)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 1)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                if (time == 0)
                    l.getWorld().spawnParticle(Particle.NOTE, locs.get(i), 1, 0, 0, 0, randomColor());
                time++;
            }
        }, 0, 2));
    }
    
    public void playMagic(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .5, .5);
            int time = 16;
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                if (time == 15) l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, .8, 0), 5, 0, 0, 0, 0);
                if (time == 0)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, .75, .43), 5, 0, 0, 0, 0);
                if (time == 1)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, .65, .65), 5, 0, 0, 0, 0);
                if (time == 2)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, .43, .75), 5, 0, 0, 0, 0);
                if (time == 3) l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, 0, .8), 5, 0, 0, 0, 0);
                if (time == 4)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, -.43, .75), 5, 0, 0, 0, 0);
                if (time == 5)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, -.65, .65), 5, 0, 0, 0, 0);
                if (time == 86)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, -.75, .43), 5, 0, 0, 0, 0);
                if (time == 7) l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, -.8, 0), 5, 0, 0, 0, 0);
                if (time == 8)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, -.75, -.43), 5, 0, 0, 0, 0);
                if (time == 9)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, -.65, -.65), 5, 0, 0, 0, 0);
                if (time == 10)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, -.43, -.75), 5, 0, 0, 0, 0);
                if (time == 11)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, 0, -.8), 5, 0, 0, 0, 0);
                if (time == 12)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, .43, -.75), 5, 0, 0, 0, 0);
                if (time == 13)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, .65, -.65), 5, 0, 0, 0, 0);
                if (time == 14)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, .75, -.43), 5, 0, 0, 0, 0);
                
                if (time == 15) l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(.8, 0, 0), 5, 0, 0, 0, 0);
                if (time == 14)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(.75, 0, .43), 5, 0, 0, 0, 0);
                if (time == 13)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(.65, 0, .65), 5, 0, 0, 0, 0);
                if (time == 12)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(.43, 0, .75), 5, 0, 0, 0, 0);
                if (time == 11) l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, 0, .8), 5, 0, 0, 0, 0);
                if (time == 10)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(-.43, 0, .75), 5, 0, 0, 0, 0);
                if (time == 9)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(-.65, 0, .65), 5, 0, 0, 0, 0);
                if (time == 8)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(-.75, 0, .43), 5, 0, 0, 0, 0);
                if (time == 7) l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(-.8, 0, 0), 5, 0, 0, 0, 0);
                if (time == 6)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(-.75, 0, -.43), 5, 0, 0, 0, 0);
                if (time == 5)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(-.65, 0, -.65), 5, 0, 0, 0, 0);
                if (time == 4)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(-.43, 0, -.75), 5, 0, 0, 0, 0);
                if (time == 3) l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(0, 0, -.8), 5, 0, 0, 0, 0);
                if (time == 2)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(.43, 0, -.75), 5, 0, 0, 0, 0);
                if (time == 1)
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(.65, 0, -.65), 5, 0, 0, 0, 0);
                if (time == 0) {
                    l.getWorld().spawnParticle(Particle.CRIT, l.clone().add(.75, 0, -.43), 5, 0, 0, 0, 0);
                    time = 16;
                }
                time--;
            }
        }, 0, 2));
    }
    
    public void playSnowStorm(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 2, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.CLOUD, l, 15, .3f, 0, .3f, 0);
                l.getWorld().spawnParticle(Particle.FIREWORK, l, 2, .3f, 0, .3f, 0);
            }
        }, 0, 2));
    }
    
    public void playFireSpew(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
                l.getWorld().spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
                l.getWorld().spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
            }
        }, 0, 2));
    }
    
    public void playFootPrint(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.EGG_CRACK, l, 3, 1, 0, 1, 0);
            }
        }, 0, 20));
    }
    
    public void playHappyVillager(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                location.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, l, 10, .5f, .5f, .5f, 0);
            }
        }, 0, 5));
    }
    
    public void playAngryVillager(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, l, 5, .5f, .5f, .5f, 0);
            }
        }, 0, 10));
    }
    
    public void playMobSpawner(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.FLAME, l, 15, .5f, .5f, .5f, 0);
            }
        }, 0, 8));
    }
    
    public void startWater(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .8, .6);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                location.getWorld().spawnParticle(Particle.FALLING_WATER, l.clone().add(0, .1, 0), 10, 0, 0, 0, 0);
                location.getWorld().spawnParticle(Particle.FALLING_WATER, l.clone().add(0, .5, 0), 10, 0, 0, 0, 0);
                location.getWorld().spawnParticle(Particle.FALLING_WATER, l.clone().add(.2, .3, .2), 10, 0, 0, 0, 0);
                location.getWorld().spawnParticle(Particle.FALLING_WATER, l.clone().add(-.2, .3, .2), 10, 0, 0, 0, 0);
                location.getWorld().spawnParticle(Particle.FALLING_WATER, l.clone().add(.2, .3, -.2), 10, 0, 0, 0, 0);
                location.getWorld().spawnParticle(Particle.FALLING_WATER, l.clone().add(-.2, .3, -.2), 10, 0, 0, 0, 0);
            }
        }, 0, 2));
    }
    
    public void playEnderSignal(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 0, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                location.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 1);
                location.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 1);
                location.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 1);
                location.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 1);
            }
        }, 0, 8));
    }
    
    public void playRainbow(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .1, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(r, g, b), 1);
                l.getWorld().spawnParticle(Particle.DUST, l, 10, .5f, .5f, .5f, 1, dustOptions);
            }
        }, 0, 5));
    }
    
    public void playSnowBlast(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, .5, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                l.getWorld().spawnParticle(Particle.SNOWFLAKE, l, 40, 0, 0, 0, .2f);
            }
        }, 0, 2));
    }
    
    public void playHalo(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.add(.5, 1.3, .5);
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                try {
                    for (int i = 0; i < 3; i++) {
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(.5, 0, 0), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(.45, 0, .13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(.35, 0, .35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(.13, 0, .45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(0, 0, .5), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(-.13, 0, .45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(-.35, 0, .35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(-.45, 0, .13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(-.5, 0, 0), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(-.45, 0, -.13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(-.35, 0, -.35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(-.13, 0, -.45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(0, 0, -.5), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(.13, 0, -.45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(.35, 0, -.35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        l.getWorld().spawnParticle(Particle.DUST, l.clone().add(.45, 0, -.13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5));
    }
    
    public void playSantaHat(final Location location, String id) {
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l1 = location.clone().add(.5, 1, .5);
            Location l2 = l1.clone().add(0, .05, 0);
            Location l3 = l2.clone().add(0, .05, 0);
            Location l4 = l3.clone().add(0, .05, 0);
            Location l5 = l4.clone().add(0, .05, 0);
            Location l6 = l5.clone().add(0, .05, 0);
            Location l7 = l6.clone().add(0, .05, 0);
            Location l8 = l7.clone().add(0, .05, 0);
            Location l9 = l8.clone().add(0, .05, 0);
            Location l10 = l9.clone().add(0, .1, 0);
            Location l11 = l10.clone().add(0, .05, 0);
            
            @Override
            public void run() {
                if (noPlayers(l1.clone(), 20)) return;
                try {
                    for (int i = 0; i < 3; i++) {
                        Color red = Color.RED;
                        Color white = Color.fromRGB(255, 255, 255);
                        for (Location location : getCircle(l1, .5, 20))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                        for (Location location : getCircle(l2, .4, 15))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l3, .35, 15))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l4, .3, 15))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l5, .2, 15))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l6, .15, 15))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l7, .1, 15))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l8, .05, 10))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l9, .05, 10))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(red, 1));
                        for (Location location : getCircle(l10, .05, 15))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                        for (Location location : getCircle(l11, .05, 15))
                            location.getWorld().spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, new Particle.DustOptions(white, 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5));
    }
    
    public void playSoulWell(final Location location, final String id) {
        final HashMap<Integer, Integer> S = new HashMap<>();
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.clone().add(.5, 0, .5);
            
            void startSoulWell(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    Location height = location.clone();
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
                            Bukkit.getScheduler().cancelTask(S.get(num));
                            S.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startSoulWell(l, id);
            }
        }, 0, 16));
    }
    
    public void playBigSoulWell(final Location location, final String id) {
        final HashMap<Integer, Integer> S = new HashMap<>();
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.clone().add(.5, 0, .5);
            
            void startBigSoulWell(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    Location height = location.clone();
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
                            Bukkit.getScheduler().cancelTask(S.get(num));
                            S.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startBigSoulWell(l, id);
            }
        }, 0, 25));
    }
    
    public void playFlameWheel(final Location location, final String id) {
        final HashMap<Integer, Integer> S = new HashMap<>();
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.clone().add(.5, .1, .5);
            
            void startFlameWheel(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    Location l = location.clone();
                    int i = 0;
                    int o = 74;
                    int f = 0;
                    int ringTimer = 0;
                    
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
                                l.getWorld().spawnParticle(Particle.FLAME, i, 0);
                            }
                        }
                        //Throws the fire inwords.
                        l.getWorld().spawnParticle(Particle.FLAME, locs.get(i), 0, -v.getX(), 0, -v.getZ(), speed);
                        l.getWorld().spawnParticle(Particle.FLAME, locs2.get(i), 0, -v2.getX(), 0, -v2.getZ(), speed);
                        l.getWorld().spawnParticle(Particle.FLAME, locs.get(o), 0, -v3.getX(), 0, -v3.getZ(), speed);
                        l.getWorld().spawnParticle(Particle.FLAME, locs2.get(o), 0, -v4.getX(), 0, -v4.getZ(), speed);
                        /*ParticleEffect.FLAME.display(v.multiply(-2), speed, locs.get(i), 100);
                        ParticleEffect.FLAME.display(v2.multiply(-2), speed, locs2.get(i), 100);
                        ParticleEffect.FLAME.display(v3.multiply(-2), speed, locs.get(o), 100);
                        ParticleEffect.FLAME.display(v4.multiply(-2), speed, locs2.get(o), 100);*/
                        i++;
                        f++;
                        o--;
                        ringTimer++;
                        if (ringTimer == 11) ringTimer = 0;
                        if (i == 75) i = 0;
                        if (o == 0) o = 74;
                        if (f == 105) {
                            Bukkit.getScheduler().cancelTask(S.get(num));
                            S.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startFlameWheel(l.clone(), id);
            }
        }, 0, 25));
    }
    
    public void playWitchTornado(final Location location, final String id) {
        final HashMap<Integer, Integer> S = new HashMap<>();
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.clone().add(.5, 0, .5);
            
            void startWitchTornado(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    Location height = location.clone().add(0, 5, 0);
                    int nextLocation = 0;
                    int diamaterSwitch = 0;
                    double radius = 1.5;
                    int lifeSpan = 0;
                    
                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(height, radius, 50);
                        height.getWorld().spawnParticle(Particle.WITCH, locs.get(nextLocation), 0, 0, 0, 0, 1);
                        nextLocation++;
                        diamaterSwitch++;
                        lifeSpan++;
                        if (nextLocation == 50) nextLocation = 0;
                        height.add(0, -.02, 0); //Controls how far each particle goes Down.
                        if (diamaterSwitch == 7) { //Controls when diameter Changes.
                            diamaterSwitch = 0;
                            radius = radius - .05; //Controls how far it goes in.
                        }
                        if (lifeSpan == 207) { //Controls how far the particle effect go down.
                            Bukkit.getScheduler().cancelTask(S.get(num));
                            S.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startWitchTornado(l, id);
            }
        }, 0, 30));
    }
    
    public void playLoveTornado(final Location location, final String id) {
        final HashMap<Integer, Integer> S = new HashMap<>();
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.clone().add(.5, 0, .5);
            
            void startLoveTornado(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    Location height = location.clone().add(0, 5, 0);
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
                            Bukkit.getScheduler().cancelTask(S.get(num));
                            S.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startLoveTornado(l, id);
            }
        }, 0, 30));
    }
    
    public void playBigLoveWell(final Location location, final String id) {
        final HashMap<Integer, Integer> S = new HashMap<>();
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.clone().add(.5, 0, .5);
            
            void startBigLoveWell(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    Location height = location.clone();
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
                            Bukkit.getScheduler().cancelTask(S.get(num));
                            S.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startBigLoveWell(l, id);
            }
        }, 0, 25));
    }
    
    public void playLoveWell(final Location location, final String id) {
        final HashMap<Integer, Integer> S = new HashMap<>();
        locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            Location l = location.clone().add(.5, 0, .5);
            
            void startLoveWell(final Location location, final String id) {
                final int num = random.nextInt(Integer.MAX_VALUE);
                S.put(num, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    Location height = location.clone();
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
                            Bukkit.getScheduler().cancelTask(S.get(num));
                            S.remove(num);
                        }
                    }
                }, 0, 1));
            }
            
            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startLoveWell(l, id);
            }
        }, 0, 16));
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
    
    private Collection<Entity> getNearbyEntities(Location location, double x, double y, double z) {
        try {
            return location.getWorld().getNearbyEntities(location, x, y, z);
        } catch (Exception ignored) {
        }
        return new ArrayList<>();
    }
    
    private boolean noPlayers(Location location, int range) {
        try {
            Collection<Entity> out = getNearbyEntities(location, range, range, range);
            if (!out.isEmpty()) {
                for (Entity e : out) {
                    if (e instanceof LivingEntity) {
                        LivingEntity en = (LivingEntity) e;
                        if (en instanceof Player) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return true;
    }
    
    private int randomColor() {
        return random.nextInt(255);
    }
    
}