package me.badbones69.blockparticles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class Methods implements Listener {
	
	public static HashMap<Location, Location> Locations = new HashMap<Location, Location>();
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("BlockParticles");
	
	@SuppressWarnings("static-access")
	public Methods(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public static String removeColor(String msg) {
		return ChatColor.stripColor(msg);
	}
	
	static void reset() {
		kill();
		startParticles();
	}
	
	private static List<Entity> getNearbyEntities(Location loc, double x, double y, double z) {
		try {
			FallingBlock ent = loc.getWorld().spawnFallingBlock(loc.subtract(0, 2, 0), 0, (byte) 0);
			List<Entity> out = ent.getNearbyEntities(x, y, z);
			ent.remove();
			return out;
		}catch(Exception e) {}
		return new ArrayList<Entity>();
	}
	
	public static Integer getVersion() {
		String ver = Bukkit.getServer().getClass().getPackage().getName();
		ver = ver.substring(ver.lastIndexOf('.') + 1);
		ver = ver.replaceAll("_", "").replaceAll("R", "").replaceAll("v", "");
		return Integer.parseInt(ver);
	}
	
	static boolean anyPlayers(Location loc, int range) {
		try {
			List<Entity> out = getNearbyEntities(loc, range, range, range);
			if(!out.isEmpty()) {
				for(Entity e : out) {
					if(e instanceof LivingEntity) {
						LivingEntity en = (LivingEntity) e;
						if(en instanceof Player) {
							return true;
						}
					}
				}
			}
		}catch(Exception e) {}
		return false;
	}
	
	static ArrayList<String> getLocations() {
		ArrayList<String> l = new ArrayList<String>();
		for(String L : Main.settings.getData().getConfigurationSection("Locations").getKeys(false)) {
			l.add(L);
		}
		return l;
	}
	
	static void kill() {
		PlayParticles.Blocks.clear();
		PlayParticles.R.clear();
		for(World w : Bukkit.getServer().getWorlds()) {
			for(Entity e : w.getEntities()) {
				if(e instanceof Item) {
					Item item = (Item) e;
					if(Fountains.items.contains(item)) {
						item.remove();
					}
				}
			}
		}
		Fountains.items.clear();
		Bukkit.getScheduler().cancelTasks(plugin);
	}
	
	static void startParticles() {
		if(Main.settings.getData().getConfigurationSection("Locations") == null) {
			Main.settings.getData().set("Locations.clear", null);
			Main.settings.saveData();
		}
		for(final String L : Main.settings.getData().getConfigurationSection("Locations").getKeys(false)) {
			String w = Main.settings.getData().getString("Locations." + L + ".World");
			World W = Bukkit.getServer().getWorld(w);
			String x = Main.settings.getData().getString("Locations." + L + ".X");
			String y = Main.settings.getData().getString("Locations." + L + ".Y");
			String z = Main.settings.getData().getString("Locations." + L + ".Z");
			String particle = Main.settings.getData().getString("Locations." + L + ".Particle");
			int X = Integer.parseInt(x);
			int Y = Integer.parseInt(y);
			int Z = Integer.parseInt(z);
			final Location loc = new Location(W, X, Y, Z);
			if(particle.equalsIgnoreCase("LoveWell")) PlayParticles.playLoveWell(loc, L);
			if(particle.equalsIgnoreCase("BigLoveWell")) PlayParticles.playBigLoveWell(loc, L);
			if(particle.equalsIgnoreCase("LoveTornado")) PlayParticles.playLoveTornado(loc, L);
			if(particle.equalsIgnoreCase("WitchTornado")) PlayParticles.playWitchTornado(loc, L);
			if(particle.equalsIgnoreCase("FlameWheel")) PlayParticles.playFlameWheel(loc, L);
			if(particle.equalsIgnoreCase("SoulWell")) PlayParticles.playSoulWell(loc, L);
			if(particle.equalsIgnoreCase("BigSoulWell")) PlayParticles.playBigSoulWell(loc, L);
			if(particle.equalsIgnoreCase("SantaHat")) PlayParticles.playSantaHat(loc, L);
			if(particle.equalsIgnoreCase("Mario")) Fountains.startMario(loc, L);
			if(particle.equalsIgnoreCase("Pokemon")) Fountains.startPokemon(loc, L);
			if(particle.equalsIgnoreCase("Food")) Fountains.startFood(loc, L);
			if(particle.equalsIgnoreCase("Mobs")) Fountains.startMobs(loc, L);
			if(particle.equalsIgnoreCase("Halo")) PlayParticles.playHalo(loc, L);
			if(particle.equalsIgnoreCase("Snow Blast") || particle.equalsIgnoreCase("SnowBlast")) PlayParticles.playSnowBlast(loc, L);
			if(particle.equalsIgnoreCase("Rainbow")) PlayParticles.playRainbow(loc, L);
			if(particle.equalsIgnoreCase("Ender Signal") || particle.equalsIgnoreCase("EnderSignal")) PlayParticles.playEnderSignal(loc, L);
			if(particle.equalsIgnoreCase("Mob Spawner") || particle.equalsIgnoreCase("MobSpawner")) PlayParticles.playMobSpawner(loc, L);
			if(particle.equalsIgnoreCase("Angry Villager") || particle.equalsIgnoreCase("AngryVillager")) PlayParticles.playAngryVillager(loc, L);
			if(particle.equalsIgnoreCase("Happy Villager") || particle.equalsIgnoreCase("HappyVillager")) PlayParticles.playHappyVillager(loc, L);
			if(particle.equalsIgnoreCase("Foot Print") || particle.equalsIgnoreCase("FootPrint")) PlayParticles.playFootPrint(loc, L);
			if(particle.equalsIgnoreCase("Fire Spew") || particle.equalsIgnoreCase("FireSpew")) PlayParticles.playFireSpew(loc, L);
			if(particle.equalsIgnoreCase("Snow Storm") || particle.equalsIgnoreCase("SnowStorm")) PlayParticles.playSnowStorm(loc, L);
			if(particle.equalsIgnoreCase("Double Witch") || particle.equalsIgnoreCase("DoubleWitch")) PlayParticles.playDoubleWitch(loc, L);
			if(particle.equalsIgnoreCase("Witch")) PlayParticles.playWitch(loc, L);
			if(particle.equalsIgnoreCase("Magic")) PlayParticles.playMagic(loc, L);
			if(particle.equalsIgnoreCase("Presents")) Fountains.startPresents(loc, L);
			if(particle.equalsIgnoreCase("Spew")) PlayParticles.playSpew(loc, L);
			if(particle.equalsIgnoreCase("Music")) PlayParticles.playMusic(loc, L);
			if(particle.equalsIgnoreCase("Potion")) PlayParticles.playPotion(loc, L);
			if(particle.equalsIgnoreCase("Snow")) PlayParticles.playSnow(loc, L);
			if(particle.equalsIgnoreCase("Fire Storm") || particle.equalsIgnoreCase("FireStorm")) PlayParticles.playFireStorm(loc, L);
			if(particle.equalsIgnoreCase("Water")) PlayParticles.startWater(loc, L);
			if(particle.equalsIgnoreCase("Chains")) PlayParticles.playChains(loc, L);
			if(particle.equalsIgnoreCase("Enchant")) PlayParticles.playEnchant(loc, L);
			if(particle.equalsIgnoreCase("Fog")) PlayParticles.playFog(loc, L);
			if(particle.equalsIgnoreCase("Storm")) PlayParticles.playStorm(loc, L);
			if(particle.equalsIgnoreCase("Heads")) Fountains.startHeads(loc, L);
			if(particle.equalsIgnoreCase("Big Flame") || particle.equalsIgnoreCase("BigFlame")) PlayParticles.playBigFlame(loc, L);
			if(particle.equalsIgnoreCase("Flame")) PlayParticles.playFlame(loc, L);
			if(particle.equalsIgnoreCase("Halloween")) Fountains.startHalloween(loc, L);
			if(particle.equalsIgnoreCase("Gems")) Fountains.startGems(loc, L);
			if(particle.equalsIgnoreCase("Valcano") || particle.equalsIgnoreCase("Volcano")) PlayParticles.playVolcano(loc, L);
			if(particle.equalsIgnoreCase("Spiral")) PlayParticles.playSpiral(loc, L);
			if(particle.equalsIgnoreCase("Double Spiral") || particle.equalsIgnoreCase("DoubleSpiral")) PlayParticles.playDoubleSpiral(loc, L);
			if(particle.equalsIgnoreCase("Crit")) PlayParticles.playCrit(loc, L);
			if(particle.equalsIgnoreCase("Big Crit") || particle.equalsIgnoreCase("BigCrit")) PlayParticles.playBigCrit(loc, L);
		}
	}
	
	static void addLoc(Player player, String name) {
		String Prefix = Main.settings.getConfig().getString("Settings.Prefix");
		if(!Main.settings.getData().contains("Locations")) {
			Main.settings.getData().set("Locations.clear", null);
			Main.settings.saveData();
		}
		for(String loc : Main.settings.getData().getConfigurationSection("Locations").getKeys(false)) {
			if(loc.equalsIgnoreCase(name)) {
				player.sendMessage(color(Prefix + "&3That Location name is Taken please remove it and replace it here."));
				return;
			}
		}
		Block block = player.getTargetBlock((Set<Material>) null, 5);
		if(block.isEmpty()) {
			player.sendMessage(Methods.color(Prefix + "&cYou are not looking at a Block."));
			return;
		}
		Location l = block.getLocation();
		String w = l.getWorld().getName();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		Main.settings.getData().set("Locations." + name + ".World", w);
		Main.settings.getData().set("Locations." + name + ".X", x);
		Main.settings.getData().set("Locations." + name + ".Y", y);
		Main.settings.getData().set("Locations." + name + ".Z", z);
		Main.settings.getData().set("Locations." + name + ".Particle", "Spiral");
		Main.settings.saveData();
		kill();
		startParticles();
		player.sendMessage(color(Prefix + "&3You have added &6" + name + " &3to the BlockParticles."));
	}
	
	static void delLoc(CommandSender player, String name) {
		String Prefix = Main.settings.getConfig().getString("Settings.Prefix");
		if(Main.settings.getData().getConfigurationSection("Locations") == null) {
			Main.settings.getData().set("Locations.clear", null);
			Main.settings.saveData();
		}
		for(String loc : Main.settings.getData().getConfigurationSection("Locations").getKeys(false)) {
			if(loc.equalsIgnoreCase(name)) {
				Main.settings.getData().set("Locations." + loc, null);
				Main.settings.saveData();
				Bukkit.getScheduler().cancelTask(PlayParticles.Blocks.get(loc));
				if(PlayParticles.R.containsKey(loc)) Bukkit.getScheduler().cancelTask(PlayParticles.R.get(loc));
				player.sendMessage(color(Prefix + "&3You have just deleted &6" + name + "&3."));
				return;
			}
		}
		player.sendMessage(color(Prefix + "&3There are no Locations called &6" + name + "&3."));
	}
	
	static void listLoc(Player player) {
		String Prefix = Methods.color(Main.settings.getConfig().getString("Settings.Prefix"));
		if(Main.settings.getData().getConfigurationSection("Locations") == null) {
			player.sendMessage(Prefix + Methods.color("&cThere are no Locations set!"));
			Main.settings.getData().set("Locations.clear", null);
			Main.settings.saveData();
			return;
		}
		if(Main.settings.getData().getConfigurationSection("Locations").getKeys(false).isEmpty()) {
			player.sendMessage(Prefix + Methods.color("&cThere are no Locations set!"));
			return;
		}
		String msg = "";
		String part = "";
		String l = "";
		int line = 1;
		for(String L : Main.settings.getData().getConfigurationSection("Locations").getKeys(false)) {
			if(Main.settings.getData().getConfigurationSection("Locations." + L).getKeys(false).isEmpty()) {
				Main.settings.getData().set("Locations." + L, null);
				Main.settings.saveData();
				continue;
			}
			String W = Main.settings.getData().getString("Locations." + L + ".World");
			String X = Main.settings.getData().getString("Locations." + L + ".X");
			String Y = Main.settings.getData().getString("Locations." + L + ".Y");
			String Z = Main.settings.getData().getString("Locations." + L + ".Z");
			
			part = Methods.color("&8[&6" + line + "&8]: " + "&c" + L + "&8, &c" + W + "&8, &c" + X + "&8, &c" + Y + "&8, &c" + Z);
			l += part;
			l += "\n";
			line++;
			part = "";
			
		}
		msg = l;
		line = line - 1;
		player.sendMessage(Prefix + Methods.color("&6A list of all the Locations."));
		player.sendMessage(Methods.color("&c[Locations Name]&8, &c[World]&8, &c[X]&8, &c[Y]&8, &c[Z]"));
		player.sendMessage(msg);
		player.sendMessage(Methods.color("&3Number of Locations: &6" + line));
		return;
	}
	
	static int randomColor() {
		Random r = new Random();
		int i = r.nextInt(255);
		return i;
	}
	
	public static ItemStack makeItem(Material material, int amount, int type, String name) {
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(color(name));
		item.setItemMeta(m);
		return item;
	}
	
	static void setLoc(CommandSender player, String name, String particle) {
		String Prefix = Main.settings.getConfig().getString("Settings.Prefix");
		if(Main.settings.getData().getConfigurationSection("Locations") == null) {
			Main.settings.getData().set("Locations.clear", null);
			Main.settings.saveData();
		}
		List<String> list = new ArrayList<String>();
		list.add("LoveWell");
		list.add("BigLoveWell");
		list.add("LoveTornado");
		list.add("WitchTornado");
		list.add("FlameWheel");
		list.add("SoulWell");
		list.add("BigSoulWell");
		list.add("SantaHat");
		list.add("Mario");
		list.add("Pokemon");
		list.add("Food");
		list.add("Mobs");
		list.add("Halo");
		list.add("SnowBlast");
		list.add("Rainbow");
		list.add("EnderSignal");
		list.add("MobSpawner");
		list.add("AngryVillager");
		list.add("HappyVillager");
		list.add("FootPrint");
		list.add("FireSpew");
		list.add("SnowStorm");
		list.add("DoubleWitch");
		list.add("Witch");
		list.add("Magic");
		list.add("Presents");
		list.add("Spew");
		list.add("Music");
		list.add("Potion");
		list.add("Snow");
		list.add("FireStorm");
		list.add("Water");
		list.add("Chains");
		list.add("Enchant");
		list.add("Fog");
		list.add("Storm");
		list.add("Heads");
		list.add("BigFlame");
		list.add("Flame");
		list.add("Halloween");
		list.add("Volcano");
		list.add("Gems");
		list.add("Spiral");
		list.add("DoubleSpiral");
		list.add("Crit");
		list.add("BigCrit");
		boolean c = false;
		for(String l : list) {
			if(particle.equalsIgnoreCase(l)) c = true;
		}
		if(!c) {
			player.sendMessage(color(Prefix + "&6" + particle + " &cis not a Particle. Please do /BP Help for more information."));
			return;
		}
		for(String loc : Main.settings.getData().getConfigurationSection("Locations").getKeys(false)) {
			if(loc.equalsIgnoreCase(name)) {
				Main.settings.getData().set("Locations." + loc + ".Particle", particle);
				Main.settings.saveData();
				kill();
				startParticles();
				player.sendMessage(color(Prefix + "&3You have just set &6" + name + "'s &3particle to &6" + particle + "&3."));
				return;
			}
		}
		player.sendMessage(color(Prefix + "&3There are no Locations called &6" + name + "&3."));
	}
	
	static boolean permCheck(Player player, String perm) {
		if(!player.hasPermission("BParticles." + perm)) {
			player.sendMessage(color("&cYou do not have permission to use that command!"));
			return true;
		}
		return false;
	}
	
	static String color(String msg) {
		msg = msg.replaceAll("(&([a-f0-9]))", "\u00A7$2");
		msg = msg.replaceAll("&l", ChatColor.BOLD + "");
		msg = msg.replaceAll("&o", ChatColor.ITALIC + "");
		msg = msg.replaceAll("&k", ChatColor.MAGIC + "");
		return msg;
	}
	
	static ItemStack getPlayerHead(String name, String N) {
		ItemStack head = new ItemStack(397, 1, (short) 3);
		SkullMeta m = (SkullMeta) head.getItemMeta();
		m.setOwner(name);
		m.setDisplayName(color(N));
		head.setItemMeta(m);
		return head;
	}
	
	static ItemStack getPlayerHead(String name) {
		ItemStack head = new ItemStack(397, 1, (short) 3);
		SkullMeta m = (SkullMeta) head.getItemMeta();
		m.setOwner(name);
		m.setDisplayName(new Random().nextInt(Integer.MAX_VALUE) + "");
		head.setItemMeta(m);
		return head;
	}
	
}