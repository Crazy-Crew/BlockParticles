package me.badbones69.blockparticles.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import me.badbones69.blockparticles.Fountains;
import me.badbones69.blockparticles.Main;
import me.badbones69.blockparticles.PlayParticles;

public class BlockParticles {
	
	static BlockParticles instance = new BlockParticles();
	
	public static BlockParticles getInstance() {
		return instance;
	}
	
	public boolean hasParticle(Location loc) {
		for(String L : Main.settings.getData().getConfigurationSection("Locations").getKeys(false)) {
			String w = Main.settings.getData().getString("Locations." + L + ".World");
			World W = Bukkit.getServer().getWorld(w);
			String x = Main.settings.getData().getString("Locations." + L + ".X");
			String y = Main.settings.getData().getString("Locations." + L + ".Y");
			String z = Main.settings.getData().getString("Locations." + L + ".Z");
			int X = Integer.parseInt(x);
			int Y = Integer.parseInt(y);
			int Z = Integer.parseInt(z);
			Location l = new Location(W, X, Y, Z);
			if(l.equals(loc)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Set a Particle to a specified Location;
	 * @param type The Particle you wish to use.
	 * @param loc The location you wish to spawn the Particles.
	 * @param name The Location Name.
	 * @return 
	 */
	public void setParticle(Particles type, Location loc, String name) {
		if(PlayParticles.Blocks.containsKey(name)) {
			Bukkit.getServer().getScheduler().cancelTask(PlayParticles.Blocks.get(name));
		}
		switch(type) {
			case LOVETORNADO:
				PlayParticles.playLoveTornado(loc, name);
				break;
			case WITCHTORNADO:
				PlayParticles.playWitchTornado(loc, name);
				break;
			case LOVEWELL:
				PlayParticles.playLoveWell(loc, name);
				break;
			case BIGLOVEWELL:
				PlayParticles.playBigLoveWell(loc, name);
				break;
			case HALO:
				PlayParticles.playHalo(loc, name);
				break;
			case SANTAHAT:
				PlayParticles.playSantaHat(loc, name);
				break;
			case SOULWELL:
				PlayParticles.playSoulWell(loc, name);
				break;
			case BIGSOULWELL:
				PlayParticles.playBigSoulWell(loc, name);
				break;
			case FLAMEWHEEL:
				PlayParticles.playFlameWheel(loc, name);
				break;
			case MARIO:
				Fountains.startMario(loc, name);
				break;
			case POKEMON:
				Fountains.startPokemon(loc, name);
				break;
			case FOOD:
				Fountains.startFood(loc, name);
				break;
			case MOBS:
				Fountains.startMobs(loc, name);
				break;
			case SNOWBLAST:
				PlayParticles.playSnowBlast(loc, name);
				break;
			case RAINBOW:
				PlayParticles.playRainbow(loc, name);
				break;
			case ENDERSIGNAL:
				PlayParticles.playEnderSignal(loc, name);
				break;
			case MOBSPAWNER:
				PlayParticles.playMobSpawner(loc, name);
				break;
			case ANGRYVILLAGER:
				PlayParticles.playAngryVillager(loc, name);
				break;
			case HAPPYVILLAGER:
				PlayParticles.playHappyVillager(loc, name);
				break;
			case FOOTPRINT:
				PlayParticles.playFootPrint(loc, name);
				break;
			case FIRESPEW:
				PlayParticles.playFireSpew(loc, name);
				break;
			case SPEW:
				PlayParticles.playSpew(loc, name);
				break;
			case STORM:
				PlayParticles.playStorm(loc, name);
				break;
			case SNOWSTORM:
				PlayParticles.playSnowStorm(loc, name);
				break;
			case FIRESTORM:
				PlayParticles.playFireStorm(loc, name);
				break;
			case WITCH:
				PlayParticles.playWitch(loc, name);
				break;
			case DOUBLEWITCH:
				PlayParticles.playDoubleWitch(loc, name);
				break;
			case MAGIC:
				PlayParticles.playMagic(loc, name);
				break;
			case PRESENTS:
				Fountains.startPresents(loc, name);
				break;
			case MUSIC:
				PlayParticles.playMusic(loc, name);
				break;
			case POTION:
				PlayParticles.playPotion(loc, name);
				break;
			case SNOW:
				PlayParticles.playSnow(loc, name);
				break;
			case WATER:
				PlayParticles.startWater(loc, name);
				break;
			case CHAINS:
				PlayParticles.playChains(loc, name);
				break;
			case ENCHANT:
				PlayParticles.playEnchant(loc, name);
				break;
			case FOG:
				PlayParticles.playFog(loc, name);
				break;
			case HEADS:
				Fountains.startHeads(loc, name);
				break;
			case FLAME:
				PlayParticles.playFlame(loc, name);
				break;
			case BIGFLAME:
				PlayParticles.playBigFlame(loc, name);
				break;
			case HALLOWEEN:
				Fountains.startHalloween(loc, name);
				break;
			case GEMS:
				Fountains.startGems(loc, name);
				break;
			case VOLCANO:
				PlayParticles.playVolcano(loc, name);
				break;
			case SPIRAL:
				PlayParticles.playSpiral(loc, name);
				break;
			case DOUBLESPIRAL:
				PlayParticles.playDoubleSpiral(loc, name);
				break;
			case CRIT:
				PlayParticles.playCrit(loc, name);
				break;
			case BIGCRIT:
				PlayParticles.playBigCrit(loc, name);
				break;
		}
	}
	
	/**
	 * Remove a Particle;
	 * @param name The Location Name.
	 */
	public void removeParticle(String name) {
		if(PlayParticles.Blocks.containsKey(name)) {
			Bukkit.getServer().getScheduler().cancelTask(PlayParticles.Blocks.get(name));
			PlayParticles.Blocks.remove(name);
		}
		if(PlayParticles.R.containsKey(name)) {
			Bukkit.getServer().getScheduler().cancelTask(PlayParticles.R.get(name));
			PlayParticles.R.remove(name);
		}
	}
	
	/**
	 * Get the Particle Type of a Particle (Particle/Fountain).
	 * @param particle The Particle you want to get the ParticleType from.
	 * @return A Particle Type.
	 */
	public ParticleType getType(Particles particle) {
		return particle.getType();
	}
}