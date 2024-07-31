package com.badbones69.blockparticles.controllers;

import com.badbones69.blockparticles.api.objects.ItemBuilder;
import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.ParticleManager;
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
    private ParticleManager bp = ParticleManager.getInstance();
    
    public static void openGUIPage1(Player player) {
        if (inventory1 == null) {
            inventory1 = Bukkit.createInventory(null, 54, Methods.color("&8Block &bParticles"));
            inventory1.setItem(0, new ItemBuilder().setMaterial("BROWN_MUSHROOM").setName("&6Crit").build());
            inventory1.setItem(1, new ItemBuilder().setMaterial("BROWN_MUSHROOM_BLOCK", "HUGE_MUSHROOM_1").setName("&6BigCrit").build());
            inventory1.setItem(2, new ItemBuilder().setMaterial("FIREWORK_ROCKET", "FIREWORK").setName("&fSpiral").build());
            inventory1.setItem(3, new ItemBuilder().setMaterial("FIREWORK_ROCKET", "FIREWORK").setName("&fDoubleSpiral").build());
            inventory1.setItem(4, new ItemBuilder().setMaterial("FLINT").setName("&cFlame").build());
            inventory1.setItem(5, new ItemBuilder().setMaterial("FLINT_AND_STEEL").setName("&cBigFlame").build());
            inventory1.setItem(6, new ItemBuilder().setMaterial("WATER_BUCKET").setName("&7Storm").build());
            inventory1.setItem(7, new ItemBuilder().setMaterial("LAVA_BUCKET").setName("&4FireStorm").build());
            inventory1.setItem(8, new ItemBuilder().setMaterial("SNOWBALL", "SNOW_BALL").setName("&7SnowStorm").build());
            inventory1.setItem(9, new ItemBuilder().setMaterial("POTION").setName("&5Potion").build());
            inventory1.setItem(10, new ItemBuilder().setMaterial("SPIDER_EYE").setName("&dWitch").build());
            inventory1.setItem(11, new ItemBuilder().setMaterial("FERMENTED_SPIDER_EYE").setName("&dDoubleWitch").build());
            inventory1.setItem(12, new ItemBuilder().setMaterial("STICK").setName("&9Magic").build());
            inventory1.setItem(13, new ItemBuilder().setMaterial("NOTE_BLOCK").setName("&3Music").build());
            inventory1.setItem(14, new ItemBuilder().setMaterial("SNOW_BLOCK").setName("&fSnow").build());
            inventory1.setItem(15, new ItemBuilder().setMaterial("BONE").setName("&7Fog").build());
            inventory1.setItem(16, new ItemBuilder().setMaterial("NETHER_STAR").setName("&fSpew").build());
            inventory1.setItem(17, new ItemBuilder().setMaterial("NETHER_BRICK", "NETHER_BRICK_ITEM").setName("&cFireSpew").build());
            inventory1.setItem(21, new ItemBuilder().setMaterial("CHAINMAIL_CHESTPLATE").setName("&cChains").build());
            inventory1.setItem(22, new ItemBuilder().setMaterial("ENCHANTING_TABLE", "ENCHANTMENT_TABLE").setName("&7Enchant").build());
            inventory1.setItem(23, new ItemBuilder().setMaterial("LEATHER_BOOTS").setName("&6FootPrint").build());
            inventory1.setItem(31, new ItemBuilder().setMaterial("WATER_BUCKET").setName("&bWater").build());
            inventory1.setItem(30, new ItemBuilder().setMaterial("EMERALD").setName("&aHappyVillager").build());
            inventory1.setItem(32, new ItemBuilder().setMaterial("FIRE_CHARGE", "FIREBALL").setName("&4AngryVillager").build());
            inventory1.setItem(20, new ItemBuilder().setMaterial("SPAWNER", "MOB_SPAWNER").setName("&cMobSpawner").build());
            inventory1.setItem(24, new ItemBuilder().setMaterial("ENDER_EYE", "EYE_OF_ENDER").setName("&dEnderSignal").build());
            inventory1.setItem(19, new ItemBuilder().setMaterial("PRISMARINE_CRYSTALS").setName("&eRainbow").build());
            inventory1.setItem(25, new ItemBuilder().setMaterial("GOLDEN_SHOVEL", "GOLD_SPADE").setName("&7SnowBlast").build());
            inventory1.setItem(33, new ItemBuilder().setMaterial("GOLD_BLOCK").setName("&eHalo").build());
            inventory1.setItem(29, new ItemBuilder().setMaterial("SNOWBALL", "SNOW_BALL").setName("&4SantaHat").build());
            inventory1.setItem(18, new ItemBuilder().setMaterial("POTION").setName("&dSoulWell").build());
            inventory1.setItem(26, new ItemBuilder().setMaterial("BLAZE_POWDER").setName("&5BigSoulWell").build());
            inventory1.setItem(28, new ItemBuilder().setMaterial("FIRE_CHARGE", "FIREBALL").setName("&4Volcano").build());
            inventory1.setItem(34, new ItemBuilder().setMaterial("BLAZE_POWDER").setName("&cFlameWheel").build());
            inventory1.setItem(38, new ItemBuilder().setMaterial("APPLE").setName("&4LoveTornado").build());
            inventory1.setItem(39, new ItemBuilder().setMaterial("BLAZE_ROD").setName("&4WitchTornado").build());
            inventory1.setItem(41, new ItemBuilder().setMaterial("GOLDEN_APPLE").setName("&4LoveWell").build());
            inventory1.setItem(42, new ItemBuilder().setMaterial("ENCHANTED_GOLDEN_APPLE", "GOLDEN_APPLE:1").setName("&4BigLoveWell").build());
            inventory1.setItem(53, new ItemBuilder().setMaterial("ARROW").setName("&eFountains").build());
        }
        player.openInventory(inventory1);
    }
    
    private static void openGUIPage2(Player player) {
        if (inventory2 == null) {
            inventory2 = Bukkit.createInventory(null, 54, Methods.color("&8Block &bParticles"));
            inventory2.setItem(20, Methods.makeItem(Material.EMERALD, 1, "&aGems"));
            inventory2.setItem(21, Methods.getPlayerHead(
            "http://textures.minecraft.net/texture/c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f",
            "&7Food"));
            inventory2.setItem(22, Methods.makeItem(Material.JACK_O_LANTERN, 1, "&6Halloween"));
            inventory2.setItem(23, Methods.getPlayerHead(player.getName(), "&7Heads"));
            inventory2.setItem(24, Methods.getPlayerHead(
            "http://textures.minecraft.net/texture/2f2d1895fff4b1bb9116c8a9e229597f69f3eee88122776e5f973357e6b",
            "&4Presents"));
            inventory2.setItem(30, Methods.getPlayerHead(
            "http://textures.minecraft.net/texture/8c78d2102db75f1b3744a5e7e9baccf88fda4cc4979ebc0a81b7d9eb5721c0",
            "&7Mobs"));
            inventory2.setItem(31, Methods.getPlayerHead(
            "http://textures.minecraft.net/texture/625a82e966734bba3480b03dda8e1469b9f58ba494216113d129beab651cf8",
            "&ePokemon"));
            inventory2.setItem(32, Methods.getPlayerHead(
            "http://textures.minecraft.net/texture/a0c2549a893726988f3428bef799875ba871688ae64eb0cfdc43f7d6e24c6c"
            , "&cMario"));
            inventory2.setItem(45, Methods.makeItem(Material.ARROW, 1, "&eParticles"));
        }
        player.openInventory(inventory2);
    }
    
    @EventHandler
    public void invClick(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        if (bp.getSetCommandPlayers().containsKey(e.getWhoClicked())) {
            String loc = bp.getSetCommandPlayers().get(e.getWhoClicked());
            if (inv != null) {
                if (e.getView().getTitle().equals(Methods.color("&8Block &bParticles"))) {
                    e.setCancelled(true);
                    if (e.getCurrentItem() != null) {
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (e.getCurrentItem().getType() != Material.AIR) {
                                String name = Methods.removeColor(e.getCurrentItem().getItemMeta().getDisplayName());
                                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&eParticles"))) {
                                    openGUIPage1((Player) e.getWhoClicked());
                                    return;
                                }
                                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&eFountains"))) {
                                    openGUIPage2((Player) e.getWhoClicked());
                                    return;
                                }
                                Methods.setLoc((Player) e.getWhoClicked(), loc, name);
                                e.getWhoClicked().closeInventory();
                            }
                        }
                    }
                }
            }
        }
    }
    
}