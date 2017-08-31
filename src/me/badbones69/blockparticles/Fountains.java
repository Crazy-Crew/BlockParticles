package me.badbones69.blockparticles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Fountains implements Listener {
	
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("BlockParticles");
	
	@SuppressWarnings("static-access")
	public Fountains(Plugin plugin) {
		this.plugin = plugin;
	}
	
	private static float Vec() {
		float Vec = (float) -.1 + (float) (Math.random() * ((.1 - -.1)));
		return Vec;
	}
	
	@EventHandler
	public void onHopperPickUp(InventoryPickupItemEvent e) {
		if(e.getItem() != null) {
			if(items.contains(e.getItem())) {
				e.setCancelled(true);
			}
		}
	}
	
	public static void startHalloween(final Location loc, String L) {
		PlayParticles.Blocks.put(L, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				ItemStack emerald = new ItemStack(Material.ROTTEN_FLESH);
				ItemMeta m = emerald.getItemMeta();
				m.setDisplayName(new Random().nextInt(Integer.MAX_VALUE) + "");
				emerald.setItemMeta(m);
				ItemStack diamond = new ItemStack(Material.REDSTONE);
				diamond.setItemMeta(m);
				ItemStack gold = new ItemStack(Material.BONE);
				gold.setItemMeta(m);
				ItemStack pumpkin = new ItemStack(Material.JACK_O_LANTERN);
				pumpkin.setItemMeta(m);
				final Item Em = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, 1, .5), emerald);
				final Item Di = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, 1, .5), diamond);
				final Item Go = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, 1, .5), gold);
				final Item Pu = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, 1, .5), pumpkin);
				Em.setVelocity(new Vector(Vec(), .3, Vec()));
				items.add(Em);
				Di.setVelocity(new Vector(Vec(), .3, Vec()));
				items.add(Di);
				Go.setVelocity(new Vector(Vec(), .3, Vec()));
				items.add(Go);
				Pu.setVelocity(new Vector(Vec(), .3, Vec()));
				items.add(Pu);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						items.remove(Em);
						Em.remove();
						items.remove(Di);
						Di.remove();
						items.remove(Go);
						Go.remove();
						items.remove(Pu);
						Pu.remove();
					}
				}, 2 * 20);
			}
		}, 0, 2));
	}
	
	public static void startGems(final Location loc, String L) {
		PlayParticles.Blocks.put(L, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				ItemStack emerald = new ItemStack(Material.EMERALD);
				ItemMeta m = emerald.getItemMeta();
				m.setDisplayName(new Random().nextInt(Integer.MAX_VALUE) + "");
				emerald.setItemMeta(m);
				ItemStack diamond = new ItemStack(Material.DIAMOND);
				diamond.setItemMeta(m);
				ItemStack gold = new ItemStack(Material.GOLD_INGOT);
				gold.setItemMeta(m);
				
				final Item Em = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, 1, .5), emerald);
				final Item Di = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, 1, .5), diamond);
				final Item Go = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, 1, .5), gold);
				
				Em.setVelocity(new Vector(Vec(), .3, Vec()));
				items.add(Em);
				Di.setVelocity(new Vector(Vec(), .3, Vec()));
				items.add(Di);
				Go.setVelocity(new Vector(Vec(), .3, Vec()));
				items.add(Go);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						items.remove(Em);
						Em.remove();
						items.remove(Di);
						Di.remove();
						items.remove(Go);
						Go.remove();
					}
				}, 2 * 20);
			}
		}, 0, 2));
	}
	
	public static void startHeads(final Location loc, String L) {
		PlayParticles.Blocks.put(L, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				String name = "skeleton";
				int radius = 10;
				for(Entity e : getNearbyEntities(loc.clone(), radius, radius, radius)) {
					if(e instanceof Player) {
						name = e.getName();
						final Item head = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, .8, .5), Methods.getPlayerHead(name));
						if(Methods.getVersion() >= 191) {
							head.setVelocity(new Vector(Vec(), .01, Vec()));
						}else {
							head.setVelocity(new Vector(Vec(), .3, Vec()));
						}
						items.add(head);
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								items.remove(head);
								head.remove();
							}
						}, 2 * 20);
					}
				}
			}
		}, 0, 3));
	}
	
	public static void startPresents(final Location loc, String L) {
		PlayParticles.Blocks.put(L, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				List<String> heads = new ArrayList<String>();
				heads.add("SeerPotion");
				heads.add("thresh3");
				heads.add("CruXXx");
				for(String e : heads) {
					final Item head = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, .8, .5), Methods.getPlayerHead(e));
					if(Methods.getVersion() >= 191) {
						head.setVelocity(new Vector(Vec(), .01, Vec()));
					}else {
						head.setVelocity(new Vector(Vec(), .3, Vec()));
					}
					items.add(head);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							items.remove(head);
							head.remove();
						}
					}, 2 * 20);
				}
			}
		}, 0, 3));
	}
	
	public static void startMobs(final Location loc, String L) {
		PlayParticles.Blocks.put(L, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				List<String> heads = new ArrayList<String>();
				List<String> picked = new ArrayList<String>();
				Random r = new Random();
				heads.add("MHF_Blaze");
				heads.add("MHF_Spider");
				heads.add("MHF_CaveSpider");
				heads.add("MHF_Chicken");
				heads.add("MHF_Cow");
				heads.add("MHF_Creeper");
				heads.add("MHF_Enderman");
				heads.add("MHF_Ghast");
				heads.add("MHF_Golem");
				heads.add("MHF_LavaSlime");
				heads.add("MHF_MushroomCow");
				heads.add("MHF_Ocelot");
				heads.add("MHF_Pig");
				heads.add("MHF_PigZombie");
				heads.add("MHF_Sheep");
				heads.add("MHF_Skeleton");
				heads.add("MHF_Slime");
				heads.add("MHF_Spider");
				heads.add("MHF_Squid");
				heads.add("MHF_Villager");
				heads.add("MHF_WSkeleton");
				heads.add("MHF_Zombie");
				for(int i = 0; i < 3; i++) {
					int p = r.nextInt(heads.size());
					picked.add(heads.get(p));
				}
				for(String e : picked) {
					final Item head = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, .8, .5), Methods.getPlayerHead(e));
					if(Methods.getVersion() >= 191) {
						head.setVelocity(new Vector(Vec(), .01, Vec()));
					}else {
						head.setVelocity(new Vector(Vec(), .3, Vec()));
					}
					items.add(head);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							items.remove(head);
							head.remove();
						}
					}, 2 * 20);
				}
			}
		}, 0, 3));
	}
	
	public static void startFood(final Location loc, String L) {
		PlayParticles.Blocks.put(L, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				List<String> heads = new ArrayList<String>();
				List<String> picked = new ArrayList<String>();
				Random r = new Random();
				heads.add("Sloggy_Whopper");
				heads.add("Hamburger");
				heads.add("AmericanOreo");
				heads.add("KylexDavis");
				heads.add("Spinken5840");
				heads.add("Crunchy_Taco34");
				heads.add("lmaoki");
				heads.add("ZachWarnerHD");
				heads.add("Chazwell777");
				heads.add("MHF_Cake");
				heads.add("Chipsandip");
				for(int i = 0; i < 3; i++) {
					int p = r.nextInt(heads.size());
					picked.add(heads.get(p));
				}
				for(String e : picked) {
					final Item head = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, .8, .5), Methods.getPlayerHead(e));
					if(Methods.getVersion() >= 191) {
						head.setVelocity(new Vector(Vec(), .01, Vec()));
					}else {
						head.setVelocity(new Vector(Vec(), .3, Vec()));
					}
					items.add(head);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							items.remove(head);
							head.remove();
						}
					}, 2 * 20);
				}
			}
		}, 0, 3));
	}
	
	public static void startPokemon(final Location loc, String L) {
		PlayParticles.Blocks.put(L, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				List<String> heads = new ArrayList<String>();
				List<String> picked = new ArrayList<String>();
				Random r = new Random();
				heads.add("Mercer444");
				heads.add("Articuno");
				heads.add("hojp");
				heads.add("FireFrisk");
				heads.add("wizard0817");
				heads.add("Chuzard");
				heads.add("Pokemon");
				heads.add("mmmaik");
				heads.add("impoli");
				heads.add("Pikachubutler");
				for(int i = 0; i < 3; i++) {
					int p = r.nextInt(heads.size());
					picked.add(heads.get(p));
				}
				for(String e : picked) {
					final Item head = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, .8, .5), Methods.getPlayerHead(e));
					if(Methods.getVersion() >= 191) {
						head.setVelocity(new Vector(Vec(), .01, Vec()));
					}else {
						head.setVelocity(new Vector(Vec(), .3, Vec()));
					}
					items.add(head);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							items.remove(head);
							head.remove();
						}
					}, 2 * 20);
				}
			}
		}, 0, 3));
	}
	
	public static void startMario(final Location loc, String L) {
		PlayParticles.Blocks.put(L, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				List<String> heads = new ArrayList<String>();
				List<String> picked = new ArrayList<String>();
				Random r = new Random();
				heads.add("kongHD");
				heads.add("Mario");
				heads.add("Luigi");
				heads.add("Yoshi");
				heads.add("Toad");
				heads.add("Bowser");
				for(int i = 0; i < 3; i++) {
					int p = r.nextInt(heads.size());
					picked.add(heads.get(p));
				}
				for(String e : picked) {
					final Item head = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc.clone().add(.5, .8, .5), Methods.getPlayerHead(e));
					if(Methods.getVersion() >= 191) {
						head.setVelocity(new Vector(Vec(), .01, Vec()));
					}else {
						head.setVelocity(new Vector(Vec(), .3, Vec()));
					}
					items.add(head);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							items.remove(head);
							head.remove();
						}
					}, 2 * 20);
				}
			}
		}, 0, 3));
	}
	
	@SuppressWarnings("deprecation")
	public static List<Entity> getNearbyEntities(Location loc, double x, double y, double z) {
		FallingBlock ent = loc.getWorld().spawnFallingBlock(loc.subtract(0, 1, 0), 132, (byte) 0);
		List<Entity> out = ent.getNearbyEntities(x, y, z);
		ent.remove();
		
		return out;
	}
	
}