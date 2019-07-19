package me.badbones69.blockparticles.multisupport;

import me.badbones69.blockparticles.api.enums.Particles;
import me.badbones69.blockparticles.controllers.ParticleControl;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class NMS_v1_12_Down implements ParticleControl {
	
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
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.LAVA.display(0, 0, 0, 0, 10, l, 100);
			}
		}, 0, 4));
	}
	
	public void playBigFlame(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.clone().add(.5, .1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				for(Location loc : getCircle(l, 1, 15))
					ParticleEffect.FLAME.display(0, 0, 0, 0, 1, loc, 100);
				for(Location loc : getCircle(l, 2, 25))
					ParticleEffect.FLAME.display(0, 0, 0, 0, 1, loc, 100);
			}
		}, 0, 5));
	}
	
	public void playFlame(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				for(Location loc : getCircle(l, .6, 15))
					ParticleEffect.FLAME.display(0, 0, 0, 0, 1, loc, 100);
				for(Location loc : getCircle(l, 1, 20))
					ParticleEffect.FLAME.display(0, 0, 0, 0, 1, loc, 100);
			}
		}, 0, 5));
	}
	
	public void playDoubleSpiral(final Location location, String id, Particles particle, int amount) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .7, .5);
			int time = 16;
			ParticleEffect particleEffect = particle == Particles.DOUBLEWITCH ? ParticleEffect.SPELL_WITCH : ParticleEffect.FIREWORKS_SPARK;
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				if(time == 15) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.8, 0, 0), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.8, 0, 0), 100);
				}
				if(time == 14) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.75, 0, .43), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.75, 0, -.43), 100);
				}
				if(time == 13) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.65, 0, .65), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.65, 0, -.65), 100);
				}
				if(time == 12) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.43, 0, .75), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.43, 0, -.75), 100);
				}
				if(time == 11) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(0, 0, .8), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(0, 0, -.8), 100);
				}
				if(time == 10) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.43, 0, .75), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.43, 0, -.75), 100);
				}
				if(time == 9) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.65, 0, .65), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.65, 0, -.65), 100);
				}
				if(time == 8) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.75, 0, .43), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.75, 0, -.43), 100);
				}
				if(time == 7) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.8, 0, 0), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.8, 0, 0), 100);
				}
				if(time == 6) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.75, 0, -.43), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.75, 0, .43), 100);
				}
				if(time == 5) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.65, 0, -.65), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.65, 0, .65), 100);
				}
				if(time == 4) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.43, 0, -.75), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.43, 0, .75), 100);
				}
				if(time == 3) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(0, 0, -.8), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(0, 0, .8), 100);
				}
				if(time == 2) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.43, 0, -.75), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.43, 0, .75), 100);
				}
				if(time == 1) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.65, 0, -.65), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.65, 0, .65), 100);
				}
				if(time == 0) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.75, 0, -.43), 100);
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.75, 0, .43), 100);
					time = 16;
				}
				time--;
			}
		}, 0, 2));
	}
	
	public void playSpiral(final Location location, String id, Particles particle, int amount) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .7, .5);
			int time = 16;
			ParticleEffect particleEffect = particle == Particles.WITCH ? ParticleEffect.SPELL_WITCH : ParticleEffect.FIREWORKS_SPARK;
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				if(time == 15) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.8, 0, 0), 100);
				if(time == 14) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.75, 0, .43), 100);
				if(time == 13) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.65, 0, .65), 100);
				if(time == 12) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.43, 0, .75), 100);
				if(time == 11) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(0, 0, .8), 100);
				if(time == 10) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.43, 0, .75), 100);
				if(time == 9) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.65, 0, .65), 100);
				if(time == 8) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.75, 0, .43), 100);
				if(time == 7) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.8, 0, 0), 100);
				if(time == 6) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.75, 0, -.43), 100);
				if(time == 5) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.65, 0, -.65), 100);
				if(time == 4) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(-.43, 0, -.75), 100);
				if(time == 3) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(0, 0, -.8), 100);
				if(time == 2) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.43, 0, -.75), 100);
				if(time == 1) particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.65, 0, -.65), 100);
				if(time == 0) {
					particleEffect.display(0, 0, 0, 0, 1, l.clone().add(.75, 0, -.43), 100);
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
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.CRIT.display(0, 0, 0, 0, 1, l.clone(), 100);
			}
		}, 0, 2));
	}
	
	public void playBigCrit(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .5, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				for(Location location : getCircle(l, 2, 20))
					ParticleEffect.CRIT.display(0, 0, 0, 0, 1, location, 100);
			}
		}, 0, 2));
	}
	
	public void playStorm(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, 2, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.CLOUD.display((float) .3, 0, (float) .3, 0, 15, l, 100);
				ParticleEffect.WATER_DROP.display((float) .2, 0, (float) .2, 0, 10, l.clone().add(0, 0, .1), 100);
			}
		}, 0, 2));
	}
	
	public void playFog(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .5, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.CLOUD.display((float) .3, 0, (float) .3, (float) .05, 20, l, 100);
			}
		}, 0, 2));
	}
	
	public void playEnchant(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, 1.5, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.ENCHANTMENT_TABLE.display((float) 0, 0, (float) 0, (float) 2, 20, l, 100);
			}
		}, 0, 2));
	}
	
	public void playChains(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(1, 0, 1), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.9, .1, .9), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.8, .2, .8), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.7, .3, .7), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.6, .4, .6), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.5, .6, .5), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.4, .8, .4), 100);
				
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-1, 0, 1), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.9, .1, .9), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.8, .2, .8), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.7, .3, .7), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.6, .4, .6), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.5, .6, .5), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.4, .8, .4), 100);
				
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-1, 0, -1), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.9, .1, -.9), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.8, .2, -.8), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.7, .3, -.7), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.6, .4, -.6), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.5, .6, -.5), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(-.4, .8, -.4), 100);
				
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(1, 0, -1), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.9, .1, -.9), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.8, .2, -.8), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.7, .3, -.7), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.6, .4, -.6), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.5, .6, -.5), 100);
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, l.clone().add(.4, .8, -.4), 100);
			}
		}, 0, 5));
	}
	
	public void playFireStorm(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, 2, .5);
			
			@Override
			public void run() {
				try {
					if(!noPlayers(l.clone(), range)) {
						ParticleEffect.SMOKE_LARGE.display((float) .3, 0, (float) .3, 0, 15, l, 100);
						ParticleEffect.FLAME.display(new Vector(0, -.2, 0), 1, randomDrop(l.clone()), 100);
						ParticleEffect.FLAME.display(new Vector(0, -.2, 0), 1, randomDrop(l.clone()), 100);
					}
				}catch(Exception e) {
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
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.FIREWORKS_SPARK.display((float) .7, (float) .7, (float) .7, 0, 1, l, 100);
			}
		}, 0, 2));
	}
	
	public void playSpew(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, 1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.FIREWORKS_SPARK.display(new Vector(randomVector(), .1, randomVector()), 1, l, 100);
			}
		}, 0, 2));
	}
	
	public void playPotion(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .2, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.SPELL_MOB.display((float) .3, (float) 0, (float) .3, randomColor(), 6, l, 100);
				ParticleEffect.SPELL_MOB.display((float) .3, (float) 0, (float) .3, randomColor(), 6, l, 100);
				ParticleEffect.SPELL_MOB.display((float) .3, (float) 0, (float) .3, randomColor(), 6, l, 100);
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
				if(noPlayers(l.clone(), range)) return;
				int i = time;
				if(time == 15) {
					ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
					time = -1;
				}
				if(time == 14) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 13) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 12) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 11) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 10) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 9) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 8) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 7) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 6) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 5) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 4) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 3) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 2) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 1) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
				if(time == 0) ParticleEffect.NOTE.display(0, 0, 0, randomColor(), 1, locs.get(i), 100);
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
				if(noPlayers(l.clone(), range)) return;
				if(time == 15) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, .8, 0), 100);
				if(time == 0) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, .75, .43), 100);
				if(time == 1) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, .65, .65), 100);
				if(time == 2) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, .43, .75), 100);
				if(time == 3) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, 0, .8), 100);
				if(time == 4) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, -.43, .75), 100);
				if(time == 5) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, -.65, .65), 100);
				if(time == 86) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, -.75, .43), 100);
				if(time == 7) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, -.8, 0), 100);
				if(time == 8) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, -.75, -.43), 100);
				if(time == 9) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, -.65, -.65), 100);
				if(time == 10) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, -.43, -.75), 100);
				if(time == 11) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, 0, -.8), 100);
				if(time == 12) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, .43, -.75), 100);
				if(time == 13) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, .65, -.65), 100);
				if(time == 14) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, .75, -.43), 100);
				
				if(time == 15) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(.8, 0, 0), 100);
				if(time == 14) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(.75, 0, .43), 100);
				if(time == 13) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(.65, 0, .65), 100);
				if(time == 12) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(.43, 0, .75), 100);
				if(time == 11) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, 0, .8), 100);
				if(time == 10) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(-.43, 0, .75), 100);
				if(time == 9) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(-.65, 0, .65), 100);
				if(time == 8) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(-.75, 0, .43), 100);
				if(time == 7) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(-.8, 0, 0), 100);
				if(time == 6) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(-.75, 0, -.43), 100);
				if(time == 5) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(-.65, 0, -.65), 100);
				if(time == 4) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(-.43, 0, -.75), 100);
				if(time == 3) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(0, 0, -.8), 100);
				if(time == 2) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(.43, 0, -.75), 100);
				if(time == 1) ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(.65, 0, -.65), 100);
				if(time == 0) {
					ParticleEffect.CRIT_MAGIC.display(0, 0, 0, 0, 5, l.clone().add(.75, 0, -.43), 100);
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
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.CLOUD.display((float) .3, 0, (float) .3, 0, 15, l, 100);
				ParticleEffect.FIREWORKS_SPARK.display((float) .3, (float) 0, (float) .3, 0, 2, l, 100);
			}
		}, 0, 2));
	}
	
	public void playFireSpew(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, 1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.FLAME.display(new Vector(randomVector(), .1, randomVector()), (float) 1.5, l, 100);
				ParticleEffect.FLAME.display(new Vector(randomVector(), .1, randomVector()), (float) 1.5, l, 100);
				ParticleEffect.FLAME.display(new Vector(randomVector(), .1, randomVector()), (float) 1.5, l, 100);
			}
		}, 0, 2));
	}
	
	public void playFootPrint(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.FOOTSTEP.display(1, 0, 1, 0, 3, l, 100);
			}
		}, 0, 20));
	}
	
	public void playHappyVillager(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.VILLAGER_HAPPY.display((float) .5, (float) .5, (float) .5, 0, 10, l, 100);
			}
		}, 0, 5));
	}
	
	public void playAngryVillager(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.VILLAGER_ANGRY.display((float) .5, (float) .5, (float) .5, 0, 5, l, 100);
			}
		}, 0, 10));
	}
	
	public void playMobSpawner(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .1, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.FLAME.display((float) .5, (float) .5, (float) .5, 0, 15, l, 100);
			}
		}, 0, 8));
	}
	
	public void startWater(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .8, .6);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.WATER_DROP.display(0, 0, 0, 0, 10, l.clone().add(0, .1, 0), 100);
				ParticleEffect.WATER_DROP.display(0, 0, 0, 0, 10, l.clone().add(0, .5, 0), 100);
				ParticleEffect.WATER_DROP.display(0, 0, 0, 0, 10, l.clone().add(.2, .3, .2), 100);
				ParticleEffect.WATER_DROP.display(0, 0, 0, 0, 10, l.clone().add(-.2, .3, .2), 100);
				ParticleEffect.WATER_DROP.display(0, 0, 0, 0, 10, l.clone().add(.2, .3, -.2), 100);
				ParticleEffect.WATER_DROP.display(0, 0, 0, 0, 10, l.clone().add(-.2, .3, -.2), 100);
			}
		}, 0, 2));
	}
	
	public void playEnderSignal(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, 0, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
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
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.REDSTONE.display((float) .5, (float) .5, (float) .5, 1, 10, l, 100);
			}
		}, 0, 5));
	}
	
	public void playSnowBlast(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, .5, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				ParticleEffect.SNOW_SHOVEL.display(0, 0, 0, (float) .2, 40, l, 100);
			}
		}, 0, 2));
	}
	
	public void playHalo(final Location location, String id) {
		locations.put(id, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			Location l = location.add(.5, 1.3, .5);
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				try {
					for(int i = 0; i < 3; i++) {
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(.5, 0, 0), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(.45, 0, .13), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(.35, 0, .35), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(.13, 0, .45), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(0, 0, .5), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(-.13, 0, .45), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(-.35, 0, .35), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(-.45, 0, .13), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(-.5, 0, 0), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(-.45, 0, -.13), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(-.35, 0, -.35), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(-.13, 0, -.45), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(0, 0, -.5), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(.13, 0, -.45), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(.35, 0, -.35), Color.fromRGB(255, 255, 0));
						ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), l.clone().add(.45, 0, -.13), Color.fromRGB(255, 255, 0));
					}
				}catch(Exception e) {
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
				if(noPlayers(l1.clone(), 20)) return;
				try {
					for(int i = 0; i < 3; i++) {
						Color red = Color.RED;
						Color white = Color.fromRGB(255, 255, 255);
						for(Location loc : getCircle(l1, .5, 20)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, white);
						for(Location loc : getCircle(l2, .4, 15)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, red);
						for(Location loc : getCircle(l3, .35, 15)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, red);
						for(Location loc : getCircle(l4, .3, 15)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, red);
						for(Location loc : getCircle(l5, .2, 15)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, red);
						for(Location loc : getCircle(l6, .15, 15)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, red);
						for(Location loc : getCircle(l7, .1, 15)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, red);
						for(Location loc : getCircle(l8, .05, 10)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, red);
						for(Location loc : getCircle(l9, .05, 10)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, red);
						for(Location loc : getCircle(l10, .05, 15)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, white);
						for(Location loc : getCircle(l11, .05, 15)) ParticleEffects.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, white);
					}
				}catch(Exception e) {
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
						ParticleEffect.SPELL_WITCH.display(0, 0, 0, 0, 1, locs.get(loc), 100);
						ParticleEffect.SPELL_WITCH.display(0, 0, 0, 0, 1, locs2.get(loc), 100);
						loc++;
						lifeSpan++;
						height.add(0, .035, 0);
						if(loc == 50) loc = 0;
						if(lifeSpan == 75) {
							Bukkit.getScheduler().cancelTask(S.get(num));
							S.remove(num);
						}
					}
				}, 0, 1));
			}
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
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
						ParticleEffect.SPELL_WITCH.display(0, 0, 0, 0, 1, locs.get(loc), 100);
						ParticleEffect.SPELL_WITCH.display(0, 0, 0, 0, 1, locs2.get(loc), 100);
						loc++;
						lifeSpan++;
						height.add(0, .04, 0);
						if(loc == 75) loc = 0;
						if(lifeSpan == 105) {
							Bukkit.getScheduler().cancelTask(S.get(num));
							S.remove(num);
						}
					}
				}, 0, 1));
			}
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
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
						if(ringTimer == 10) {
							for(Location i : locs) {
								ParticleEffect.FLAME.display(0, 0, 0, 0, 1, i, 100);
							}
						}
						//Throws the fire inwords.
						ParticleEffect.FLAME.display(v.multiply(-2), speed, locs.get(i), 100);
						ParticleEffect.FLAME.display(v2.multiply(-2), speed, locs2.get(i), 100);
						ParticleEffect.FLAME.display(v3.multiply(-2), speed, locs.get(o), 100);
						ParticleEffect.FLAME.display(v4.multiply(-2), speed, locs2.get(o), 100);
						i++;
						f++;
						o--;
						ringTimer++;
						if(ringTimer == 11) ringTimer = 0;
						if(i == 75) i = 0;
						if(o == 0) o = 74;
						if(f == 105) {
							Bukkit.getScheduler().cancelTask(S.get(num));
							S.remove(num);
						}
					}
				}, 0, 1));
			}
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
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
						ParticleEffect.SPELL_WITCH.display(0, 0, 0, 0, 1, locs.get(nextLocation), 100);
						nextLocation++;
						diamaterSwitch++;
						lifeSpan++;
						if(nextLocation == 50) nextLocation = 0;
						height.add(0, -.02, 0); //Controls how far each particle goes Down.
						if(diamaterSwitch == 7) { //Controls when diameter Changes.
							diamaterSwitch = 0;
							radius = radius - .05; //Controls how far it goes in.
						}
						if(lifeSpan == 207) { //Controls how far the particle effect go down.
							Bukkit.getScheduler().cancelTask(S.get(num));
							S.remove(num);
						}
					}
				}, 0, 1));
			}
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
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
						ParticleEffect.HEART.display(0, 0, 0, 0, 1, locs.get(nextLocation), 100);
						diameterShrink++;
						lifeSpan++;
						nextLocation++;
						if(nextLocation == 50) nextLocation = 0; //Controls the next x & z locations.
						height.add(0, -.02, 0); //Controls how far each particle goes Down.
						if(diameterShrink == 7) { //Controls when diameter Changes.
							diameterShrink = 0;
							radius = radius - .05; //Controls how far it goes in.
						}
						if(lifeSpan == 207) { //Controls how far the particle effect go down.
							Bukkit.getScheduler().cancelTask(S.get(num));
							S.remove(num);
						}
					}
				}, 0, 1));
			}
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
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
						ParticleEffect.HEART.display(0, 0, 0, 0, 1, locs.get(loc), 100);
						ParticleEffect.HEART.display(0, 0, 0, 0, 1, locs2.get(loc), 100);
						loc++;
						lifeSpan++;
						height.add(0, .04, 0);
						if(loc == 75) loc = 0;
						if(lifeSpan == 105) {
							Bukkit.getScheduler().cancelTask(S.get(num));
							S.remove(num);
						}
					}
				}, 0, 1));
			}
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
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
						ParticleEffect.HEART.display(0, 0, 0, 0, 1, locs.get(loc), 100);
						ParticleEffect.HEART.display(0, 0, 0, 0, 1, locs2.get(loc), 100);
						loc++;
						lifeSpan++;
						height.add(0, .035, 0);
						if(loc == 50) loc = 0;
						if(lifeSpan == 75) {
							Bukkit.getScheduler().cancelTask(S.get(num));
							S.remove(num);
						}
					}
				}, 0, 1));
			}
			
			@Override
			public void run() {
				if(noPlayers(l.clone(), range)) return;
				startLoveWell(l, id);
			}
		}, 0, 16));
	}
	
	private ArrayList<Location> getCircle(Location center, double radius, int amount) {
		World world = center.getWorld();
		double increment = (2 * Math.PI) / amount;
		ArrayList<Location> locations = new ArrayList<>();
		for(int i = 0; i < amount; i++) {
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
		for(int i = 0; i < amount; i++) {
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
		}catch(Exception ignored) {
		}
		return new ArrayList<>();
	}
	
	private boolean noPlayers(Location location, int range) {
		try {
			Collection<Entity> out = getNearbyEntities(location, range, range, range);
			if(!out.isEmpty()) {
				for(Entity e : out) {
					if(e instanceof LivingEntity) {
						LivingEntity en = (LivingEntity) e;
						if(en instanceof Player) {
							return false;
						}
					}
				}
			}
		}catch(Exception ignored) {
		}
		return true;
	}
	
	private int randomColor() {
		return random.nextInt(255);
	}
	
}