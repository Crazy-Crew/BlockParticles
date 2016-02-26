package me.BadBones69.BlockParticles;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUI implements Listener{
	static void openGUIPage1(Player player){
		Inventory inv = Bukkit.createInventory(null, 54, Api.color("&8&lBlock &b&lParticles"));
		inv.setItem(0, Api.makeItem(Material.BROWN_MUSHROOM, 1, 0, "&6&lCrit"));
		inv.setItem(1, Api.makeItem(Material.HUGE_MUSHROOM_1, 1, 0, "&6&lBigCrit"));
		inv.setItem(2, Api.makeItem(Material.FIREWORK, 1, 0, "&f&lSpiral"));
		inv.setItem(3, Api.makeItem(Material.FIREWORK, 1, 0, "&f&lDoubleSpiral"));
		inv.setItem(4, Api.makeItem(Material.FLINT, 1, 0, "&c&lFlame"));
		inv.setItem(5, Api.makeItem(Material.FLINT_AND_STEEL, 1, 0, "&c&lBigFlame"));
		inv.setItem(6, Api.makeItem(Material.WATER_BUCKET, 1, 0, "&7&lStorm"));
		inv.setItem(7, Api.makeItem(Material.LAVA_BUCKET, 1, 0, "&4&lFireStorm"));
		inv.setItem(8, Api.makeItem(Material.SNOW_BALL, 1, 0, "&7&lSnowStorm"));
		inv.setItem(9, Api.makeItem(Material.POTION, 1, 0, "&5&lPotion"));
		inv.setItem(10, Api.makeItem(Material.SPIDER_EYE, 1, 0, "&d&lWitch"));
		inv.setItem(11, Api.makeItem(Material.FERMENTED_SPIDER_EYE, 1, 0, "&d&lDoubleWitch"));
		inv.setItem(12, Api.makeItem(Material.STICK, 1, 0, "&9&lMagic"));
		inv.setItem(13, Api.makeItem(Material.NOTE_BLOCK, 1, 0, "&3&lMusic"));
		inv.setItem(14, Api.makeItem(Material.SNOW_BLOCK, 1, 0, "&f&lSnow"));
		inv.setItem(15, Api.makeItem(Material.BONE, 1, 0, "&7&lFog"));
		inv.setItem(16, Api.makeItem(Material.NETHER_STAR, 1, 0, "&f&lSpew"));
		inv.setItem(17, Api.makeItem(Material.NETHER_BRICK_ITEM, 1, 0, "&c&lFireSpew"));
		inv.setItem(21, Api.makeItem(Material.CHAINMAIL_CHESTPLATE, 1, 0, "&c&lChains"));
		inv.setItem(22, Api.makeItem(Material.ENCHANTMENT_TABLE, 1, 0, "&7&lEnchant"));
		inv.setItem(23, Api.makeItem(Material.LEATHER_BOOTS, 1, 0, "&6&lFootPrint"));
		inv.setItem(31, Api.makeItem(Material.WATER_BUCKET, 1, 0, "&b&lWater"));
		inv.setItem(30, Api.makeItem(Material.EMERALD, 1, 0, "&a&lHappyVillager"));
		inv.setItem(32, Api.makeItem(Material.matchMaterial("385"), 1, 0, "&4&lAngryVillager"));
		inv.setItem(20, Api.makeItem(Material.MOB_SPAWNER, 1, 0, "&c&lMobSpawner"));
		inv.setItem(24, Api.makeItem(Material.EYE_OF_ENDER, 1, 0, "&d&lEnderSignal"));
		inv.setItem(19, Api.makeItem(Material.PRISMARINE_CRYSTALS, 1, 0, "&e&lRainbow"));
		inv.setItem(25, Api.makeItem(Material.GOLD_SPADE, 1, 0, "&7&lSnowBlast"));
		inv.setItem(33, Api.makeItem(Material.GOLD_BLOCK, 1, 0, "&e&lHalo"));
		inv.setItem(29, Api.makeItem(Material.SNOW_BALL, 1, 0, "&4&lSantaHat"));
		inv.setItem(18, Api.makeItem(Material.POTION, 1, 0, "&d&lSoulWell"));
		inv.setItem(26, Api.makeItem(Material.BLAZE_POWDER, 1, 0, "&5&lBigSoulWell"));
		inv.setItem(28, Api.makeItem(Material.matchMaterial("385"), 1, 0, "&4&lVolcano"));
		inv.setItem(34, Api.makeItem(Material.BLAZE_POWDER, 1, 0, "&c&lFlameWheel"));
		inv.setItem(38, Api.makeItem(Material.APPLE, 1, 0, "&4&lLoveTornado"));
		inv.setItem(39, Api.makeItem(Material.BLAZE_ROD, 1, 0, "&4&lWitchTornado"));
		inv.setItem(41, Api.makeItem(Material.GOLDEN_APPLE, 1, 0, "&4&lLoveWell"));
		inv.setItem(42, Api.makeItem(Material.GOLDEN_APPLE, 1, 1, "&4&lBigLoveWell"));
		inv.setItem(53, Api.makeItem(Material.ARROW, 1, 0, "&e&lFountians"));
		player.openInventory(inv);
	}
	static void openGUIPage2(Player player){
		Inventory inv = Bukkit.createInventory(null, 54, Api.color("&8&lBlock &b&lParticles"));
		inv.setItem(20, Api.makeItem(Material.EMERALD, 1, 0, "&a&lGems"));
		inv.setItem(21, Api.getPlayerHead("Sloggy_Whopper", "&7&lFood"));
		inv.setItem(22, Api.makeItem(Material.JACK_O_LANTERN, 1, 0, "&6&lHalloween"));
		inv.setItem(23, Api.getPlayerHead("Steve", "&7&lHeads"));
		inv.setItem(24, Api.getPlayerHead("CruXXx", "&4&lPresents"));
		inv.setItem(30, Api.getPlayerHead("Skeleton", "&7&lMobs"));
		inv.setItem(31, Api.getPlayerHead("Pikachubutler", "&e&lPokemon"));
		inv.setItem(32, Api.getPlayerHead("Mario", "&c&lMario"));
		inv.setItem(45, Api.makeItem(Material.ARROW, 1, 0, "&e&lParticles"));
		player.openInventory(inv);
	}
	@EventHandler
	public void invClick(InventoryClickEvent e){
		Inventory inv = e.getInventory();
		if(Main.B.containsKey(e.getWhoClicked())){
			String loc = Main.B.get(e.getWhoClicked());
			if(inv!=null){
				if(inv.getName().equals(Api.color("&8&lBlock &b&lParticles"))){
					e.setCancelled(true);
					if(e.getCurrentItem()!=null){
						if(e.getCurrentItem().hasItemMeta()){
							if(e.getCurrentItem().getType()!=Material.AIR){
								String name = Api.removeColor(e.getCurrentItem().getItemMeta().getDisplayName());
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Api.color("&e&lParticles"))){
									openGUIPage1((Player)e.getWhoClicked());
									return;
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Api.color("&e&lFountians"))){
									openGUIPage2((Player)e.getWhoClicked());
									return;
								}
								Api.setLoc((Player)e.getWhoClicked(), loc, name);
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