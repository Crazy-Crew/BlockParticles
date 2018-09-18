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
        inv.setItem(0, Methods.makeItem(Material.BROWN_MUSHROOM, 1, "&6&lCrit"));
        inv.setItem(1, Methods.makeItem(Material.LEGACY_HUGE_MUSHROOM_1, 1, "&6&lBigCrit"));
        inv.setItem(2, Methods.makeItem(Material.LEGACY_FIREWORK, 1, "&f&lSpiral"));
        inv.setItem(3, Methods.makeItem(Material.LEGACY_FIREWORK, 1, "&f&lDoubleSpiral"));
        inv.setItem(4, Methods.makeItem(Material.FLINT, 1, "&c&lFlame"));
        inv.setItem(5, Methods.makeItem(Material.FLINT_AND_STEEL, 1, "&c&lBigFlame"));
        inv.setItem(6, Methods.makeItem(Material.WATER_BUCKET, 1, "&7&lStorm"));
        inv.setItem(7, Methods.makeItem(Material.LAVA_BUCKET, 1, "&4&lFireStorm"));
        inv.setItem(8, Methods.makeItem(Material.LEGACY_SNOW_BALL, 1, "&7&lSnowStorm"));
        inv.setItem(9, Methods.makeItem(Material.POTION, 1, "&5&lPotion"));
        inv.setItem(10, Methods.makeItem(Material.SPIDER_EYE, 1, "&d&lWitch"));
        inv.setItem(11, Methods.makeItem(Material.FERMENTED_SPIDER_EYE, 1, "&d&lDoubleWitch"));
        inv.setItem(12, Methods.makeItem(Material.STICK, 1, "&9&lMagic"));
        inv.setItem(13, Methods.makeItem(Material.NOTE_BLOCK, 1, "&3&lMusic"));
        inv.setItem(14, Methods.makeItem(Material.SNOW_BLOCK, 1, "&f&lSnow"));
        inv.setItem(15, Methods.makeItem(Material.BONE, 1, "&7&lFog"));
        inv.setItem(16, Methods.makeItem(Material.NETHER_STAR, 1, "&f&lSpew"));
        inv.setItem(17, Methods.makeItem(Material.LEGACY_NETHER_BRICK_ITEM, 1, "&c&lFireSpew"));
        inv.setItem(21, Methods.makeItem(Material.CHAINMAIL_CHESTPLATE, 1, "&c&lChains"));
        inv.setItem(22, Methods.makeItem(Material.LEGACY_ENCHANTMENT_TABLE, 1, "&7&lEnchant"));
        inv.setItem(23, Methods.makeItem(Material.LEATHER_BOOTS, 1, "&6&lFootPrint"));
        inv.setItem(31, Methods.makeItem(Material.WATER_BUCKET, 1, "&b&lWater"));
        inv.setItem(30, Methods.makeItem(Material.EMERALD, 1, "&a&lHappyVillager"));
        inv.setItem(32, Methods.makeItem(Material.FIRE_CHARGE, 1, "&4&lAngryVillager"));
        inv.setItem(20, Methods.makeItem(Material.LEGACY_MOB_SPAWNER, 1, "&c&lMobSpawner"));
        inv.setItem(24, Methods.makeItem(Material.LEGACY_EYE_OF_ENDER, 1, "&d&lEnderSignal"));
        inv.setItem(19, Methods.makeItem(Material.PRISMARINE_CRYSTALS, 1, "&e&lRainbow"));
        inv.setItem(25, Methods.makeItem(Material.LEGACY_GOLD_SPADE, 1, "&7&lSnowBlast"));
        inv.setItem(33, Methods.makeItem(Material.GOLD_BLOCK, 1, "&e&lHalo"));
        inv.setItem(29, Methods.makeItem(Material.LEGACY_SNOW_BALL, 1, "&4&lSantaHat"));
        inv.setItem(18, Methods.makeItem(Material.POTION, 1, "&d&lSoulWell"));
        inv.setItem(26, Methods.makeItem(Material.BLAZE_POWDER, 1, "&5&lBigSoulWell"));
        inv.setItem(28, Methods.makeItem(Material.FIRE_CHARGE, 1, "&4&lVolcano"));
        inv.setItem(34, Methods.makeItem(Material.BLAZE_POWDER, 1, "&c&lFlameWheel"));
        inv.setItem(38, Methods.makeItem(Material.APPLE, 1, "&4&lLoveTornado"));
        inv.setItem(39, Methods.makeItem(Material.BLAZE_ROD, 1, "&4&lWitchTornado"));
        inv.setItem(41, Methods.makeItem(Material.GOLDEN_APPLE, 1, "&4&lLoveWell"));
        inv.setItem(42, Methods.makeItem(Material.ENCHANTED_GOLDEN_APPLE, 1, "&4&lBigLoveWell"));
        inv.setItem(53, Methods.makeItem(Material.ARROW, 1, "&e&lFountians"));
        player.openInventory(inv);
    }

    private static void openGUIPage2(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, Methods.color("&8&lBlock &b&lParticles"));
        inv.setItem(20, Methods.makeItem(Material.EMERALD, 1, "&a&lGems"));
        inv.setItem(21, Methods.getPlayerHead("Sloggy_Whopper", "&7&lFood"));
        inv.setItem(22, Methods.makeItem(Material.JACK_O_LANTERN, 1, "&6&lHalloween"));
        inv.setItem(23, Methods.getPlayerHead("Steve", "&7&lHeads"));
        inv.setItem(24, Methods.getPlayerHead("CruXXx", "&4&lPresents"));
        inv.setItem(30, Methods.getPlayerHead("Skeleton", "&7&lMobs"));
        inv.setItem(31, Methods.getPlayerHead("Pikachubutler", "&e&lPokemon"));
        inv.setItem(32, Methods.getPlayerHead("Mario", "&c&lMario"));
        inv.setItem(45, Methods.makeItem(Material.ARROW, 1, "&e&lParticles"));
        player.openInventory(inv);
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        if(BlockParticles.B.containsKey(e.getWhoClicked())) {
            String loc = BlockParticles.B.get(e.getWhoClicked());
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