package com.badbones69.blockparticles.listeners;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.ParticleManager;
import com.ryderbelserion.vital.paper.api.builders.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.HashMap;
import java.util.UUID;

public class MenuListener implements Listener {

    private static final BlockParticles plugin = BlockParticles.getPlugin();

    private static final ParticleManager particleManager = plugin.getParticleManager();
    
    private static Inventory inventory1;
    private static Inventory inventory2;
    
    public static void openGUIPage1(Player player) {
        if (inventory1 == null) {
            inventory1 = Bukkit.createInventory(null, 54, Methods.color("&8Block &bParticles"));
            inventory1.setItem(0, new ItemBuilder().withType(Material.BROWN_MUSHROOM).setDisplayName("&6Crit").asItemStack());
            inventory1.setItem(1, new ItemBuilder().withType(Material.BROWN_MUSHROOM_BLOCK).setDisplayName("&6BigCrit").asItemStack());
            inventory1.setItem(2, new ItemBuilder().withType(Material.FIREWORK_ROCKET).setDisplayName("&fSpiral").asItemStack());
            inventory1.setItem(3, new ItemBuilder().withType(Material.FIREWORK_ROCKET).setDisplayName("&fDoubleSpiral").asItemStack());
            inventory1.setItem(4, new ItemBuilder().withType(Material.FLINT).setDisplayName("&cFlame").asItemStack());
            inventory1.setItem(5, new ItemBuilder().withType(Material.FLINT_AND_STEEL).setDisplayName("&cBigFlame").asItemStack());
            inventory1.setItem(6, new ItemBuilder().withType(Material.WATER_BUCKET).setDisplayName("&7Storm").asItemStack());
            inventory1.setItem(7, new ItemBuilder().withType(Material.LAVA_BUCKET).setDisplayName("&4FireStorm").asItemStack());
            inventory1.setItem(8, new ItemBuilder().withType(Material.SNOWBALL).setDisplayName("&7SnowStorm").asItemStack());
            inventory1.setItem(9, new ItemBuilder().withType(Material.POTION).setDisplayName("&5Potion").asItemStack());
            inventory1.setItem(10, new ItemBuilder().withType(Material.SPIDER_EYE).setDisplayName("&dWitch").asItemStack());
            inventory1.setItem(11, new ItemBuilder().withType(Material.FERMENTED_SPIDER_EYE).setDisplayName("&dDoubleWitch").asItemStack());
            inventory1.setItem(12, new ItemBuilder().withType(Material.STICK).setDisplayName("&9Magic").asItemStack());
            inventory1.setItem(13, new ItemBuilder().withType(Material.NOTE_BLOCK).setDisplayName("&3Music").asItemStack());
            inventory1.setItem(14, new ItemBuilder().withType(Material.SNOW_BLOCK).setDisplayName("&fSnow").asItemStack());
            inventory1.setItem(15, new ItemBuilder().withType(Material.BONE).setDisplayName("&7Fog").asItemStack());
            inventory1.setItem(16, new ItemBuilder().withType(Material.NETHER_STAR).setDisplayName("&fSpew").asItemStack());
            inventory1.setItem(17, new ItemBuilder().withType(Material.NETHER_BRICK).setDisplayName("&cFireSpew").asItemStack());
            inventory1.setItem(21, new ItemBuilder().withType(Material.CHAINMAIL_CHESTPLATE).setDisplayName("&cChains").asItemStack());
            inventory1.setItem(22, new ItemBuilder().withType(Material.ENCHANTING_TABLE).setDisplayName("&7Enchant").asItemStack());
            inventory1.setItem(23, new ItemBuilder().withType(Material.LEATHER_BOOTS).setDisplayName("&6FootPrint").asItemStack());
            inventory1.setItem(31, new ItemBuilder().withType(Material.WATER_BUCKET).setDisplayName("&bWater").asItemStack());
            inventory1.setItem(30, new ItemBuilder().withType(Material.EMERALD).setDisplayName("&aHappyVillager").asItemStack());
            inventory1.setItem(32, new ItemBuilder().withType(Material.FIRE_CHARGE).setDisplayName("&4AngryVillager").asItemStack());
            inventory1.setItem(20, new ItemBuilder().withType(Material.SPAWNER).setDisplayName("&cMobSpawner").asItemStack());
            inventory1.setItem(24, new ItemBuilder().withType(Material.ENDER_EYE).setDisplayName("&dEnderSignal").asItemStack());
            inventory1.setItem(19, new ItemBuilder().withType(Material.PRISMARINE_CRYSTALS).setDisplayName("&eRainbow").asItemStack());
            inventory1.setItem(25, new ItemBuilder().withType(Material.GOLDEN_SHOVEL).setDisplayName("&7SnowBlast").asItemStack());
            inventory1.setItem(33, new ItemBuilder().withType(Material.GOLD_BLOCK).setDisplayName("&eHalo").asItemStack());
            inventory1.setItem(29, new ItemBuilder().withType(Material.SNOWBALL).setDisplayName("&4SantaHat").asItemStack());
            inventory1.setItem(18, new ItemBuilder().withType(Material.POTION).setDisplayName("&dSoulWell").asItemStack());
            inventory1.setItem(26, new ItemBuilder().withType(Material.BLAZE_POWDER).setDisplayName("&5BigSoulWell").asItemStack());
            inventory1.setItem(28, new ItemBuilder().withType(Material.FIRE_CHARGE).setDisplayName("&4Volcano").asItemStack());
            inventory1.setItem(34, new ItemBuilder().withType(Material.BLAZE_POWDER).setDisplayName("&cFlameWheel").asItemStack());
            inventory1.setItem(38, new ItemBuilder().withType(Material.APPLE).setDisplayName("&4LoveTornado").asItemStack());
            inventory1.setItem(39, new ItemBuilder().withType(Material.BLAZE_ROD).setDisplayName("&4WitchTornado").asItemStack());
            inventory1.setItem(41, new ItemBuilder().withType(Material.GOLDEN_APPLE).setDisplayName("&4LoveWell").asItemStack());
            inventory1.setItem(42, new ItemBuilder().withType(Material.ENCHANTED_GOLDEN_APPLE).setDisplayName("&4BigLoveWell").asItemStack());
            inventory1.setItem(53, new ItemBuilder().withType(Material.ARROW).setDisplayName("&eFountains").asItemStack());
        }
        player.openInventory(inventory1);
    }
    
    private static void openGUIPage2(Player player) {
        if (inventory2 == null) {
            inventory2 = Bukkit.createInventory(null, 54, Methods.color("&8Block &bParticles"));
            inventory2.setItem(20, Methods.makeItem(Material.EMERALD, 1, "&aGems"));
            inventory2.setItem(21, Methods.getPlayerHead(
            "https://textures.minecraft.net/texture/c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f",
            "&7Food"));
            inventory2.setItem(22, Methods.makeItem(Material.JACK_O_LANTERN, 1, "&6Halloween"));
            inventory2.setItem(23, Methods.getPlayerHead(player.getName(), "&7Heads"));
            inventory2.setItem(24, Methods.getPlayerHead(
            "https://textures.minecraft.net/texture/2f2d1895fff4b1bb9116c8a9e229597f69f3eee88122776e5f973357e6b",
            "&4Presents"));
            inventory2.setItem(30, Methods.getPlayerHead(
            "https://textures.minecraft.net/texture/8c78d2102db75f1b3744a5e7e9baccf88fda4cc4979ebc0a81b7d9eb5721c0",
            "&7Mobs"));
            inventory2.setItem(31, Methods.getPlayerHead(
            "https://textures.minecraft.net/texture/625a82e966734bba3480b03dda8e1469b9f58ba494216113d129beab651cf8",
            "&ePokemon"));
            inventory2.setItem(32, Methods.getPlayerHead(
            "https://textures.minecraft.net/texture/a0c2549a893726988f3428bef799875ba871688ae64eb0cfdc43f7d6e24c6c"
            , "&cMario"));
            inventory2.setItem(45, Methods.makeItem(Material.ARROW, 1, "&eParticles"));
        }

        player.openInventory(inventory2);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;

        final HashMap<UUID, String> set = particleManager.getSetCommandPlayers();

        if (set.isEmpty()) return;

        if (!set.containsKey(player.getUniqueId())) return;

        final String location = set.get(player.getUniqueId());

        if (!e.getView().getTitle().equals("&8Block &bParticles")) return;

        final ItemStack itemStack = e.getCurrentItem();

        if (itemStack == null || itemStack.getType() == Material.AIR) return;

        if (itemStack.hasItemMeta()) return;

        final ItemMeta meta = itemStack.getItemMeta();

        final String displayName = Methods.removeColor(meta.getDisplayName());

        e.setCancelled(true);

        if (displayName.equals("Particles")) {
            openGUIPage1(player);

            return;
        }

        if (displayName.equals("Fountains")) {
            openGUIPage2(player);

            return;
        }

        Methods.setLoc(player, location, displayName);
        player.closeInventory();
    }
}