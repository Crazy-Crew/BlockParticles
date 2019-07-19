package me.badbones69.blockparticles.api;

import me.badbones69.blockparticles.api.FileManager.Files;
import me.badbones69.blockparticles.api.enums.ParticleType;
import me.badbones69.blockparticles.api.enums.Particles;
import me.badbones69.blockparticles.controllers.Fountains;
import me.badbones69.blockparticles.controllers.ParticleControl;
import me.badbones69.blockparticles.multisupport.NMS_v1_12_Down;
import me.badbones69.blockparticles.multisupport.NMS_v1_13_Up;
import me.badbones69.blockparticles.multisupport.Version;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlockParticles {
	
	private static BlockParticles instance = new BlockParticles();
	private Plugin plugin;
	private List<Entity> fountainItems = new ArrayList<>();
	private HashMap<Player, String> setCommandPlayers = new HashMap<>();
	private ParticleControl particleControl;
	
	public static BlockParticles getInstance() {
		return instance;
	}
	
	public void load() {
		particleControl = Version.getCurrentVersion().isNewer(Version.v1_12_R1) ? new NMS_v1_13_Up() : new NMS_v1_12_Down();
	}
	
	public Plugin getPlugin() {
		if(plugin == null) {
			plugin = Bukkit.getServer().getPluginManager().getPlugin("BlockParticles");
		}
		return plugin;
	}
	
	public ParticleControl getParticleControl() {
		return particleControl;
	}
	
	public boolean hasParticle(Location loc) {
		for(String id : Files.DATA.getFile().getConfigurationSection("Locations").getKeys(false)) {
			World world = Bukkit.getServer().getWorld(Files.DATA.getFile().getString("Locations." + id + ".World"));
			int X = Integer.parseInt(Files.DATA.getFile().getString("Locations." + id + ".X"));
			int Y = Integer.parseInt(Files.DATA.getFile().getString("Locations." + id + ".Y"));
			int Z = Integer.parseInt(Files.DATA.getFile().getString("Locations." + id + ".Z"));
			Location l = new Location(world, X, Y, Z);
			if(l.equals(loc)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Entity> getFountainItem() {
		return fountainItems;
	}
	
	public void addFountainItem(Entity item) {
		fountainItems.add(item);
	}
	
	public void removeFountainItem(Entity item) {
		fountainItems.remove(item);
	}
	
	/**
	 * Set a Particle to a specified Location;
	 *
	 * @param type The Particle you wish to use.
	 * @param loc  The location you wish to spawn the Particles.
	 * @param name The Location Name.
	 */
	public void setParticle(Particles type, Location loc, String name) {
		if(particleControl.getLocations().containsKey(name)) {
			Bukkit.getServer().getScheduler().cancelTask(particleControl.getLocations().get(name));
		}
		
		switch(type) {
			case LOVETORNADO:
				particleControl.playLoveTornado(loc, name);
				break;
			case WITCHTORNADO:
				particleControl.playWitchTornado(loc, name);
				break;
			case LOVEWELL:
				particleControl.playLoveWell(loc, name);
				break;
			case BIGLOVEWELL:
				particleControl.playBigLoveWell(loc, name);
				break;
			case HALO:
				particleControl.playHalo(loc, name);
				break;
			case SANTAHAT:
				particleControl.playSantaHat(loc, name);
				break;
			case SOULWELL:
				particleControl.playSoulWell(loc, name);
				break;
			case BIGSOULWELL:
				particleControl.playBigSoulWell(loc, name);
				break;
			case FLAMEWHEEL:
				particleControl.playFlameWheel(loc, name);
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
				particleControl.playSnowBlast(loc, name);
				break;
			case RAINBOW:
				particleControl.playRainbow(loc, name);
				break;
			case ENDERSIGNAL:
				particleControl.playEnderSignal(loc, name);
				break;
			case MOBSPAWNER:
				particleControl.playMobSpawner(loc, name);
				break;
			case ANGRYVILLAGER:
				particleControl.playAngryVillager(loc, name);
				break;
			case HAPPYVILLAGER:
				particleControl.playHappyVillager(loc, name);
				break;
			case FOOTPRINT:
				particleControl.playFootPrint(loc, name);
				break;
			case FIRESPEW:
				particleControl.playFireSpew(loc, name);
				break;
			case SPEW:
				particleControl.playSpew(loc, name);
				break;
			case STORM:
				particleControl.playStorm(loc, name);
				break;
			case SNOWSTORM:
				particleControl.playSnowStorm(loc, name);
				break;
			case FIRESTORM:
				particleControl.playFireStorm(loc, name);
				break;
			case WITCH:
				particleControl.playSpiral(loc, name, Particles.WITCH, 5);
				break;
			case DOUBLEWITCH:
				particleControl.playDoubleSpiral(loc, name, Particles.DOUBLEWITCH, 5);
				break;
			case MAGIC:
				particleControl.playMagic(loc, name);
				break;
			case PRESENTS:
				Fountains.startPresents(loc, name);
				break;
			case MUSIC:
				particleControl.playMusic(loc, name);
				break;
			case POTION:
				particleControl.playPotion(loc, name);
				break;
			case SNOW:
				particleControl.playSnow(loc, name);
				break;
			case WATER:
				particleControl.startWater(loc, name);
				break;
			case CHAINS:
				particleControl.playChains(loc, name);
				break;
			case ENCHANT:
				particleControl.playEnchant(loc, name);
				break;
			case FOG:
				particleControl.playFog(loc, name);
				break;
			case HEADS:
				Fountains.startHeads(loc, name);
				break;
			case FLAME:
				particleControl.playFlame(loc, name);
				break;
			case BIGFLAME:
				particleControl.playBigFlame(loc, name);
				break;
			case HALLOWEEN:
				Fountains.startHalloween(loc, name);
				break;
			case GEMS:
				Fountains.startGems(loc, name);
				break;
			case VOLCANO:
				particleControl.playVolcano(loc, name);
				break;
			case SPIRAL:
				particleControl.playSpiral(loc, name, Particles.SPIRAL, 1);
				break;
			case DOUBLESPIRAL:
				particleControl.playDoubleSpiral(loc, name, Particles.DOUBLESPIRAL, 1);
				break;
			case CRIT:
				particleControl.playCrit(loc, name);
				break;
			case BIGCRIT:
				particleControl.playBigCrit(loc, name);
				break;
		}
	}
	
	/**
	 * Remove a Particle;
	 *
	 * @param name The Location Name.
	 */
	public void removeParticle(String name) {
		if(particleControl.getLocations().containsKey(name)) {
			Bukkit.getServer().getScheduler().cancelTask(particleControl.getLocations().get(name));
			particleControl.getLocations().remove(name);
		}
	}
	
	/**
	 * Get the Particle Type of a Particle (Particle/Fountain).
	 *
	 * @param particle The Particle you want to get the ParticleType from.
	 * @return A Particle Type.
	 */
	public ParticleType getType(Particles particle) {
		return particle.getType();
	}
	
	public void addSetCommandPlayer(Player player, String type) {
		setCommandPlayers.put(player, type);
	}
	
	public HashMap<Player, String> getSetCommandPlayers() {
		return setCommandPlayers;
	}
	
	public boolean useNewMaterial() {
		return Version.getCurrentVersion().isNewer(Version.v1_12_R1);
	}
	
}