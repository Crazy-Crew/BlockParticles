package me.badbones69.blockparticles.controllers;

import me.badbones69.blockparticles.Methods;
import me.badbones69.blockparticles.api.BlockParticles;
import me.badbones69.blockparticles.api.objects.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUI implements Listener {
	
	private static Inventory inventory1;
	private static Inventory inventory2;
	private BlockParticles bp = BlockParticles.getInstance();
	
	public static void openGUIPage1(Player player) {
		if(inventory1 == null) {
			inventory1 = Bukkit.createInventory(null, 54, Methods.color("&8&lBlock &b&lParticles"));
			inventory1.setItem(0, new ItemBuilder().setMaterial("BROWN_MUSHROOM").setName("&6&lCrit").build());
			inventory1.setItem(1, new ItemBuilder().setMaterial("BROWN_MUSHROOM_BLOCK", "HUGE_MUSHROOM_1").setName("&6&lBigCrit").build());
			inventory1.setItem(2, new ItemBuilder().setMaterial("FIREWORK_ROCKET", "FIREWORK").setName("&f&lSpiral").build());
			inventory1.setItem(3, new ItemBuilder().setMaterial("FIREWORK_ROCKET", "FIREWORK").setName("&f&lDoubleSpiral").build());
			inventory1.setItem(4, new ItemBuilder().setMaterial("FLINT").setName("&c&lFlame").build());
			inventory1.setItem(5, new ItemBuilder().setMaterial("FLINT_AND_STEEL").setName("&c&lBigFlame").build());
			inventory1.setItem(6, new ItemBuilder().setMaterial("WATER_BUCKET").setName("&7&lStorm").build());
			inventory1.setItem(7, new ItemBuilder().setMaterial("LAVA_BUCKET").setName("&4&lFireStorm").build());
			inventory1.setItem(8, new ItemBuilder().setMaterial("SNOWBALL", "SNOW_BALL").setName("&7&lSnowStorm").build());
			inventory1.setItem(9, new ItemBuilder().setMaterial("POTION").setName("&5&lPotion").build());
			inventory1.setItem(10, new ItemBuilder().setMaterial("SPIDER_EYE").setName("&d&lWitch").build());
			inventory1.setItem(11, new ItemBuilder().setMaterial("FERMENTED_SPIDER_EYE").setName("&d&lDoubleWitch").build());
			inventory1.setItem(12, new ItemBuilder().setMaterial("STICK").setName("&9&lMagic").build());
			inventory1.setItem(13, new ItemBuilder().setMaterial("NOTE_BLOCK").setName("&3&lMusic").build());
			inventory1.setItem(14, new ItemBuilder().setMaterial("SNOW_BLOCK").setName("&f&lSnow").build());
			inventory1.setItem(15, new ItemBuilder().setMaterial("BONE").setName("&7&lFog").build());
			inventory1.setItem(16, new ItemBuilder().setMaterial("NETHER_STAR").setName("&f&lSpew").build());
			inventory1.setItem(17, new ItemBuilder().setMaterial("NETHER_BRICK", "NETHER_BRICK_ITEM").setName("&c&lFireSpew").build());
			inventory1.setItem(21, new ItemBuilder().setMaterial("CHAINMAIL_CHESTPLATE").setName("&c&lChains").build());
			inventory1.setItem(22, new ItemBuilder().setMaterial("ENCHANTING_TABLE", "ENCHANTMENT_TABLE").setName("&7&lEnchant").build());
			inventory1.setItem(23, new ItemBuilder().setMaterial("LEATHER_BOOTS").setName("&6&lFootPrint").build());
			inventory1.setItem(31, new ItemBuilder().setMaterial("WATER_BUCKET").setName("&b&lWater").build());
			inventory1.setItem(30, new ItemBuilder().setMaterial("EMERALD").setName("&a&lHappyVillager").build());
			inventory1.setItem(32, new ItemBuilder().setMaterial("FIRE_CHARGE", "FIREBALL").setName("&4&lAngryVillager").build());
			inventory1.setItem(20, new ItemBuilder().setMaterial("SPAWNER", "MOB_SPAWNER").setName("&c&lMobSpawner").build());
			inventory1.setItem(24, new ItemBuilder().setMaterial("ENDER_EYE", "EYE_OF_ENDER").setName("&d&lEnderSignal").build());
			inventory1.setItem(19, new ItemBuilder().setMaterial("PRISMARINE_CRYSTALS").setName("&e&lRainbow").build());
			inventory1.setItem(25, new ItemBuilder().setMaterial("GOLDEN_SHOVEL", "GOLD_SPADE").setName("&7&lSnowBlast").build());
			inventory1.setItem(33, new ItemBuilder().setMaterial("GOLD_BLOCK").setName("&e&lHalo").build());
			inventory1.setItem(29, new ItemBuilder().setMaterial("SNOWBALL", "SNOW_BALL").setName("&4&lSantaHat").build());
			inventory1.setItem(18, new ItemBuilder().setMaterial("POTION").setName("&d&lSoulWell").build());
			inventory1.setItem(26, new ItemBuilder().setMaterial("BLAZE_POWDER").setName("&5&lBigSoulWell").build());
			inventory1.setItem(28, new ItemBuilder().setMaterial("FIRE_CHARGE", "FIREBALL").setName("&4&lVolcano").build());
			inventory1.setItem(34, new ItemBuilder().setMaterial("BLAZE_POWDER").setName("&c&lFlameWheel").build());
			inventory1.setItem(38, new ItemBuilder().setMaterial("APPLE").setName("&4&lLoveTornado").build());
			inventory1.setItem(39, new ItemBuilder().setMaterial("BLAZE_ROD").setName("&4&lWitchTornado").build());
			inventory1.setItem(41, new ItemBuilder().setMaterial("GOLDEN_APPLE").setName("&4&lLoveWell").build());
			inventory1.setItem(42, new ItemBuilder().setMaterial("ENCHANTED_GOLDEN_APPLE", "GOLDEN_APPLE:1").setName("&4&lBigLoveWell").build());
			inventory1.setItem(53, new ItemBuilder().setMaterial("ARROW").setName("&e&lFountians").build());
		}
		player.openInventory(inventory1);
	}
	
	private static void openGUIPage2(Player player) {
		if(inventory2 == null) {
			inventory2 = Bukkit.createInventory(null, 54, Methods.color("&8&lBlock &b&lParticles"));
			inventory2.setItem(20, Methods.makeItem(Material.EMERALD, 1, "&a&lGems"));
			inventory2.setItem(21, Methods.getPlayerHead(
			"http://textures.minecraft.net/texture/c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f",
			"&7&lFood"));
			inventory2.setItem(22, Methods.makeItem(Material.JACK_O_LANTERN, 1, "&6&lHalloween"));
			inventory2.setItem(23, Methods.getPlayerHead(player.getName(), "&7&lHeads"));
			inventory2.setItem(24, Methods.getPlayerHead(
			"http://textures.minecraft.net/texture/2f2d1895fff4b1bb9116c8a9e229597f69f3eee88122776e5f973357e6b",
			"&4&lPresents"));
			inventory2.setItem(30, Methods.getPlayerHead(
			"http://textures.minecraft.net/texture/8c78d2102db75f1b3744a5e7e9baccf88fda4cc4979ebc0a81b7d9eb5721c0",
			"&7&lMobs"));
			inventory2.setItem(31, Methods.getPlayerHead(
			"http://textures.minecraft.net/texture/625a82e966734bba3480b03dda8e1469b9f58ba494216113d129beab651cf8",
			"&e&lPokemon"));
			inventory2.setItem(32, Methods.getPlayerHead(
			"http://textures.minecraft.net/texture/a0c2549a893726988f3428bef799875ba871688ae64eb0cfdc43f7d6e24c6c"
			, "&c&lMario"));
			inventory2.setItem(45, Methods.makeItem(Material.ARROW, 1, "&e&lParticles"));
		}
		player.openInventory(inventory2);
	}
	
	@EventHandler
	public void invClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		if(bp.getSetCommandPlayers().containsKey(e.getWhoClicked())) {
			String loc = bp.getSetCommandPlayers().get(e.getWhoClicked());
			if(inv != null) {
				if(e.getView().getTitle().equals(Methods.color("&8&lBlock &b&lParticles"))) {
					e.setCancelled(true);
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem().hasItemMeta()) {
							if(e.getCurrentItem().getType() != Material.AIR) {
								String name = Methods.removeColor(e.getCurrentItem().getItemMeta().getDisplayName());
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&e&lParticles"))) {
									openGUIPage1((Player) e.getWhoClicked());
									return;
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&e&lFountains"))) {
									openGUIPage2((Player) e.getWhoClicked());
									return;
								}
								Methods.setLoc(e.getWhoClicked(), loc, name);
								e.getWhoClicked().closeInventory();
							}
						}
					}
				}
			}
		}
	}
	
}