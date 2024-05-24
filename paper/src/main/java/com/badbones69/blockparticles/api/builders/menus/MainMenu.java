package com.badbones69.blockparticles.api.builders.menus;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.api.builders.InventoryBuilder;
import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.api.enums.PersistentKeys;
import com.ryderbelserion.vital.paper.builders.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;

public class MainMenu extends InventoryBuilder {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final ParticleManager particleManager = this.plugin.getParticleManager();

    private static final Map<Integer, Map<ItemStack, Integer>> items = new HashMap<>() {{
        putIfAbsent(1, new HashMap<>() {{
            put(getItemStack(Material.BROWN_MUSHROOM, "<gold>Crit", PersistentKeys.generic_icon, "Crit"), 0);
            put(getItemStack(Material.BROWN_MUSHROOM_BLOCK, "<gold>BigCrit", PersistentKeys.generic_icon, "BigCrit"), 1);
            put(getItemStack(Material.FIREWORK_ROCKET, "<white>Spiral", PersistentKeys.generic_icon, "Spiral"), 2);
            put(getItemStack(Material.FIREWORK_ROCKET, "<white>DoubleSpiral", PersistentKeys.generic_icon, "DoubleSpiral"), 3);
            put(getItemStack(Material.FLINT, "<red>Flame", PersistentKeys.generic_icon, "Flame"), 4);
            put(getItemStack(Material.FLINT_AND_STEEL, "<red>BigFlame", PersistentKeys.generic_icon, "BigFlame"), 5);
            put(getItemStack(Material.WATER_BUCKET, "<gray>Storm", PersistentKeys.generic_icon, "Storm"), 6);
            put(getItemStack(Material.LAVA_BUCKET, "<dark_red>FireStorm", PersistentKeys.generic_icon, "FireStorm"), 7);
            put(getItemStack(Material.SNOWBALL, "<gray>SnowStorm", PersistentKeys.generic_icon, "SnowStorm"), 8);
            put(getItemStack(Material.POTION, "<dark_purple>Potion", PersistentKeys.generic_icon, "Potion"), 9);
            put(getItemStack(Material.SPIDER_EYE, "<light_purple>Witch", PersistentKeys.generic_icon, "Witch"), 10);
            put(getItemStack(Material.FERMENTED_SPIDER_EYE, "<light_purple>DoubleWitch", PersistentKeys.generic_icon, "DoubleWitch"), 11);
            put(getItemStack(Material.STICK, "<dark_blue>Magic", PersistentKeys.generic_icon, "Magic"), 12);
            put(getItemStack(Material.NOTE_BLOCK, "<dark_aqua>Music", PersistentKeys.generic_icon, "Music"), 13);
            put(getItemStack(Material.SNOW_BLOCK, "<white>Snow", PersistentKeys.generic_icon, "Snow"), 14);
            put(getItemStack(Material.BONE, "<gray>Fog", PersistentKeys.generic_icon, "Fog"), 15);
            put(getItemStack(Material.NETHER_STAR, "<white>Spew", PersistentKeys.generic_icon, "Spew"), 16);
            put(getItemStack(Material.NETHER_BRICK, "<red>FireSpew", PersistentKeys.generic_icon, "FireSpew"), 17);
            put(getItemStack(Material.CHAINMAIL_CHESTPLATE, "<red>Chains", PersistentKeys.generic_icon, "Chains"), 18);
            put(getItemStack(Material.ENCHANTING_TABLE, "<gray>Enchant", PersistentKeys.generic_icon, "Enchant"), 19);
            put(getItemStack(Material.LEATHER_BOOTS, "<gold>FootPrint", PersistentKeys.generic_icon, "FootPrint"), 20);
            put(getItemStack(Material.WATER_BUCKET, "<light_blue>Water", PersistentKeys.generic_icon, "Water"), 21);
            put(getItemStack(Material.EMERALD, "<green>HappyVillager", PersistentKeys.generic_icon, "HappyVillager"), 22);
            put(getItemStack(Material.FIRE_CHARGE, "<dark_red>AngryVillager", PersistentKeys.generic_icon, "AngryVillager"), 23);
            put(getItemStack(Material.SPAWNER, "<red>MobSpawner", PersistentKeys.generic_icon, "MobSpawner"), 24);
            put(getItemStack(Material.ENDER_EYE, "<light_purple>EnderSignal", PersistentKeys.generic_icon, "EnderSignal"), 25);
            put(getItemStack(Material.PRISMARINE_CRYSTALS, "<yellow>Rainbow", PersistentKeys.generic_icon, "Rainbow"), 26);
            put(getItemStack(Material.GOLDEN_SHOVEL, "<gray>SnowBlast", PersistentKeys.generic_icon, "SnowBlast"), 27);
            put(getItemStack(Material.GOLD_BLOCK, "<yellow>Halo", PersistentKeys.generic_icon, "Halo"), 28);
            put(getItemStack(Material.SNOWBALL, "<dark_red>SantaHat", PersistentKeys.generic_icon, "SantaHat"), 29);
            put(getItemStack(Material.POTION, "<light_purple>SoulWell", PersistentKeys.generic_icon, "SoulWell"), 30);
            put(getItemStack(Material.BLAZE_POWDER, "<dark_purple>BigSoulWell", PersistentKeys.generic_icon, "BigSoulWell"), 31);
            put(getItemStack(Material.APPLE, "<dark_red>LoveTornado", PersistentKeys.generic_icon, "LoveTornado"), 32);
            put(getItemStack(Material.BLAZE_ROD, "<dark_red>WitchTornado", PersistentKeys.generic_icon, "WitchTornado"), 33);
            put(getItemStack(Material.GOLDEN_APPLE, "<dark_red>LoveWell", PersistentKeys.generic_icon, "LoveWell"), 34);
            put(getItemStack(Material.ENCHANTED_GOLDEN_APPLE, "<dark_red>BigLoveWell", PersistentKeys.generic_icon, "BigLoveWell"), 35);
            put(getItemStack(Material.ARROW, "<yellow>Fountains", PersistentKeys.next_button, "Fountains"), 36);
        }});

        putIfAbsent(2, new HashMap<>() {{
            put(getItemStack(Material.EMERALD, "<green>Gems", PersistentKeys.generic_icon, "Gems"), 20);
            put(getItemStack(Material.PLAYER_HEAD, "<gray>Food", PersistentKeys.generic_icon, "Food"), 21);
            put(getItemStack(Material.JACK_O_LANTERN, "<gold>Halloween", PersistentKeys.generic_icon, "Halloween"), 22);
            put(getItemStack(Material.PLAYER_HEAD, "<dark_red>Presents", PersistentKeys.generic_icon, "Presents"), 23);
            put(getItemStack(Material.PLAYER_HEAD, "<gray>Mobs", PersistentKeys.generic_icon, "Mobs"), 24);
            put(getItemStack(Material.PLAYER_HEAD, "<yellow>Pokemon", PersistentKeys.generic_icon, "Pokemon"), 30);
            put(getItemStack(Material.PLAYER_HEAD, "<red>Mario", PersistentKeys.generic_icon, "Mario"), 31);
            put(getItemStack(Material.PLAYER_HEAD, "<gray>Heads", PersistentKeys.generic_icon, "Heads"), 32);
            put(getItemStack(Material.ARROW, "<yellow>Particles", PersistentKeys.back_button, "Particles"), 45);
        }});
    }};
    
    private static ItemStack getItemStack(Material material, String name, PersistentKeys key, String value) {
        return new ItemBuilder().withType(material).setDisplayName(name).setPersistentString(key.getNamespacedKey(), value).getStack();
    }

    public MainMenu() {}

    public MainMenu(Player player) {
        super(player, "<red>Block Particles", 54);
    }

    @Override
    public final InventoryBuilder build() {
        final Inventory inventory = getInventory();

        update(inventory, true);

        return this;
    }

    private void update(final Inventory inventory, final boolean page) {
        if (page) {
            // Get the first page and set items.
            items.get(1).forEach((item, slot) -> inventory.setItem(slot, item));

            return;
        }

        items.get(2).forEach((item, slot) -> inventory.setItem(slot, item));
    }

    @Override
    public void run(InventoryClickEvent event) {
        final Inventory inventory = event.getInventory();

        if (!(inventory.getHolder(false) instanceof MainMenu holder)) return;

        event.setCancelled(true);

        Player player = holder.getPlayer();

        final InventoryView view = holder.getView();

        if (event.getClickedInventory() != view.getTopInventory()) return;

        if (!player.hasPermission("blockparticles.set")) {
            Messages.no_permission.getMessage();

            return;
        }

        if (!this.particleManager.containsPlayer(player)) return;

        final ItemStack item = event.getCurrentItem();

        if (item == null || !item.hasItemMeta() || item.getType() == Material.AIR) return;

        ItemMeta itemMeta = item.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        // This is the particles button.
        if (container.has(PersistentKeys.back_button.getNamespacedKey())) {
            // Clear the inventory.
            getInventory().clear();

            // Set new items.
            update(getInventory(), true);

            return;
        }

        // This is the fountains button.
        if (container.has(PersistentKeys.next_button.getNamespacedKey())) {
            // Clear the inventory.
            getInventory().clear();

            // Set new items.
            update(getInventory(), false);

            return;
        }

        // This is all the other generic stuff.
        if (container.has(PersistentKeys.generic_icon.getNamespacedKey())) {
            String name = container.get(PersistentKeys.generic_icon.getNamespacedKey(), PersistentDataType.STRING);
            Methods.setLoc(player, this.particleManager.getLocation(player), name);

            player.closeInventory();
        }
    }
}