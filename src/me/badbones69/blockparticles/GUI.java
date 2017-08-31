package me.badbones69.blockparticles;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUI implements Listener {
	
	static void openGUIPage1(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, Methods.color("&8&lBlock &b&lParticles"));
		inv.setItem(0, Methods.makeItem(Material.BROWN_MUSHROOM, 1, 0, "&6&lCrit"));
		inv.setItem(1, Methods.makeItem(Material.HUGE_MUSHROOM_1, 1, 0, "&6&lBigCrit"));
		inv.setItem(2, Methods.makeItem(Material.FIREWORK, 1, 0, "&f&lSpiral"));
		inv.setItem(3, Methods.makeItem(Material.FIREWORK, 1, 0, "&f&lDoubleSpiral"));
		inv.setItem(4, Methods.makeItem(Material.FLINT, 1, 0, "&c&lFlame"));
		inv.setItem(5, Methods.makeItem(Material.FLINT_AND_STEEL, 1, 0, "&c&lBigFlame"));
		inv.setItem(6, Methods.makeItem(Material.WATER_BUCKET, 1, 0, "&7&lStorm"));
		inv.setItem(7, Methods.makeItem(Material.LAVA_BUCKET, 1, 0, "&4&lFireStorm"));
		inv.setItem(8, Methods.makeItem(Material.SNOW_BALL, 1, 0, "&7&lSnowStorm"));
		inv.setItem(9, Methods.makeItem(Material.POTION, 1, 0, "&5&lPotion"));
		inv.setItem(10, Methods.makeItem(Material.SPIDER_EYE, 1, 0, "&d&lWitch"));
		inv.setItem(11, Methods.makeItem(Material.FERMENTED_SPIDER_EYE, 1, 0, "&d&lDoubleWitch"));
		inv.setItem(12, Methods.makeItem(Material.STICK, 1, 0, "&9&lMagic"));
		inv.setItem(13, Methods.makeItem(Material.NOTE_BLOCK, 1, 0, "&3&lMusic"));
		inv.setItem(14, Methods.makeItem(Material.SNOW_BLOCK, 1, 0, "&f&lSnow"));
		inv.setItem(15, Methods.makeItem(Material.BONE, 1, 0, "&7&lFog"));
		inv.setItem(16, Methods.makeItem(Material.NETHER_STAR, 1, 0, "&f&lSpew"));
		inv.setItem(17, Methods.makeItem(Material.NETHER_BRICK_ITEM, 1, 0, "&c&lFireSpew"));
		inv.setItem(21, Methods.makeItem(Material.CHAINMAIL_CHESTPLATE, 1, 0, "&c&lChains"));
		inv.setItem(22, Methods.makeItem(Material.ENCHANTMENT_TABLE, 1, 0, "&7&lEnchant"));
		inv.setItem(23, Methods.makeItem(Material.LEATHER_BOOTS, 1, 0, "&6&lFootPrint"));
		inv.setItem(31, Methods.makeItem(Material.WATER_BUCKET, 1, 0, "&b&lWater"));
		inv.setItem(30, Methods.makeItem(Material.EMERALD, 1, 0, "&a&lHappyVillager"));
		inv.setItem(32, Methods.makeItem(Material.matchMaterial("385"), 1, 0, "&4&lAngryVillager"));
		inv.setItem(20, Methods.makeItem(Material.MOB_SPAWNER, 1, 0, "&c&lMobSpawner"));
		inv.setItem(24, Methods.makeItem(Material.EYE_OF_ENDER, 1, 0, "&d&lEnderSignal"));
		inv.setItem(19, Methods.makeItem(Material.PRISMARINE_CRYSTALS, 1, 0, "&e&lRainbow"));
		inv.setItem(25, Methods.makeItem(Material.GOLD_SPADE, 1, 0, "&7&lSnowBlast"));
		inv.setItem(33, Methods.makeItem(Material.GOLD_BLOCK, 1, 0, "&e&lHalo"));
		inv.setItem(29, Methods.makeItem(Material.SNOW_BALL, 1, 0, "&4&lSantaHat"));
		inv.setItem(18, Methods.makeItem(Material.POTION, 1, 0, "&d&lSoulWell"));
		inv.setItem(26, Methods.makeItem(Material.BLAZE_POWDER, 1, 0, "&5&lBigSoulWell"));
		inv.setItem(28, Methods.makeItem(Material.matchMaterial("385"), 1, 0, "&4&lVolcano"));
		inv.setItem(34, Methods.makeItem(Material.BLAZE_POWDER, 1, 0, "&c&lFlameWheel"));
		inv.setItem(38, Methods.makeItem(Material.APPLE, 1, 0, "&4&lLoveTornado"));
		inv.setItem(39, Methods.makeItem(Material.BLAZE_ROD, 1, 0, "&4&lWitchTornado"));
		inv.setItem(41, Methods.makeItem(Material.GOLDEN_APPLE, 1, 0, "&4&lLoveWell"));
		inv.setItem(42, Methods.makeItem(Material.GOLDEN_APPLE, 1, 1, "&4&lBigLoveWell"));
		inv.setItem(53, Methods.makeItem(Material.ARROW, 1, 0, "&e&lFountians"));
		player.openInventory(inv);
	}
	
	static void openGUIPage2(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, Methods.color("&8&lBlock &b&lParticles"));
		inv.setItem(20, Methods.makeItem(Material.EMERALD, 1, 0, "&a&lGems"));
		inv.setItem(21, Methods.getPlayerHead("Sloggy_Whopper", "&7&lFood"));
		inv.setItem(22, Methods.makeItem(Material.JACK_O_LANTERN, 1, 0, "&6&lHalloween"));
		inv.setItem(23, Methods.getPlayerHead("Steve", "&7&lHeads"));
		inv.setItem(24, Methods.getPlayerHead("CruXXx", "&4&lPresents"));
		inv.setItem(30, Methods.getPlayerHead("Skeleton", "&7&lMobs"));
		inv.setItem(31, Methods.getPlayerHead("Pikachubutler", "&e&lPokemon"));
		inv.setItem(32, Methods.getPlayerHead("Mario", "&c&lMario"));
		inv.setItem(45, Methods.makeItem(Material.ARROW, 1, 0, "&e&lParticles"));
		player.openInventory(inv);
	}
	
	@EventHandler
	public void invClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		if(Main.B.containsKey(e.getWhoClicked())) {
			String loc = Main.B.get(e.getWhoClicked());
			if(inv != null) {
				if(inv.getName().equals(Methods.color("&8&lBlock &b&lParticles"))) {
					e.setCancelled(true);
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem().hasItemMeta()) {
							if(e.getCurrentItem().getType() != Material.AIR) {
								String name = Methods.removeColor(e.getCurrentItem().getItemMeta().getDisplayName());
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&e&lParticles"))) {
									openGUIPage1((Player) e.getWhoClicked());
									return;
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&e&lFountians"))) {
									openGUIPage2((Player) e.getWhoClicked());
									return;
								}
								Methods.setLoc((Player) e.getWhoClicked(), loc, name);
								e.getWhoClicked().closeInventory();
								return;
							}
						}
					}
				}
			}
		}
	}
	
}