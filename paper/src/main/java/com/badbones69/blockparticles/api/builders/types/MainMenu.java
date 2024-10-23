package com.badbones69.blockparticles.api.builders.types;

import com.badbones69.blockparticles.api.builders.gui.StaticInventoryBuilder;
import com.ryderbelserion.vital.paper.api.builders.gui.interfaces.Gui;
import com.ryderbelserion.vital.paper.api.builders.gui.interfaces.GuiItem;
import com.ryderbelserion.vital.paper.api.builders.items.ItemBuilder;
import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends StaticInventoryBuilder {

    private final NamespacedKey key = new NamespacedKey(this.plugin, "button");
    
    private final Map<Integer, Map<Integer, ItemStack>> items;

    public MainMenu(final Player player, final String title, final int rows) {
        super(player, title, rows);
        
        this.items = new HashMap<>();
        
        this.items.put(1, new HashMap<>());
        this.items.put(2, new HashMap<>());
        
        final Map<Integer, ItemStack> page_one = new HashMap<>() {{
            put(0, new ItemBuilder().withType(Material.BROWN_MUSHROOM).setDisplayName("<gold>Crit").asItemStack());
            put(1, new ItemBuilder().withType(Material.BROWN_MUSHROOM_BLOCK).setDisplayName("<gold>BigCrit").asItemStack());
            put(2, new ItemBuilder().withType(Material.FIREWORK_ROCKET).setDisplayName("<white>Spiral").asItemStack());
            put(3, new ItemBuilder().withType(Material.FIREWORK_ROCKET).setDisplayName("<white>DoubleSpiral").asItemStack());
            put(4, new ItemBuilder().withType(Material.FLINT).setDisplayName("<red>Flame").asItemStack());
            put(5, new ItemBuilder().withType(Material.FLINT_AND_STEEL).setDisplayName("<red>BigFlame").asItemStack());
            put(6, new ItemBuilder().withType(Material.WATER_BUCKET).setDisplayName("<gray>Storm").asItemStack());
            put(7, new ItemBuilder().withType(Material.LAVA_BUCKET).setDisplayName("<dark_red>FireStorm").asItemStack());
            put(8, new ItemBuilder().withType(Material.SNOWBALL).setDisplayName("<gray>SnowStorm").asItemStack());
            put(9, new ItemBuilder().withType(Material.POTION).setDisplayName("<dark_purple>Potion").asItemStack());
            put(10, new ItemBuilder().withType(Material.SPIDER_EYE).setDisplayName("<light_purple>Witch").asItemStack());
            put(11, new ItemBuilder().withType(Material.FERMENTED_SPIDER_EYE).setDisplayName("<light_purple>DoubleWitch").asItemStack());
            put(12, new ItemBuilder().withType(Material.STICK).setDisplayName("<dark_blue>Magic").asItemStack());
            put(13, new ItemBuilder().withType(Material.NOTE_BLOCK).setDisplayName("<aqua>Music").asItemStack());
            put(14, new ItemBuilder().withType(Material.SNOW_BLOCK).setDisplayName("<white>Snow").asItemStack());
            put(15, new ItemBuilder().withType(Material.BONE).setDisplayName("<gray>Fog").asItemStack());
            put(16, new ItemBuilder().withType(Material.NETHER_STAR).setDisplayName("<white>Spew").asItemStack());
            put(17, new ItemBuilder().withType(Material.NETHER_BRICK).setDisplayName("<red>FireSpew").asItemStack());
            put(21, new ItemBuilder().withType(Material.CHAINMAIL_CHESTPLATE).setDisplayName("<red>Chains").asItemStack());
            put(22, new ItemBuilder().withType(Material.ENCHANTING_TABLE).setDisplayName("<gray>Enchant").asItemStack());
            put(23, new ItemBuilder().withType(Material.LEATHER_BOOTS).setDisplayName("<gold>FootPrint").asItemStack());
            put(31, new ItemBuilder().withType(Material.WATER_BUCKET).setDisplayName("<light_blue>Water").asItemStack());
            put(30, new ItemBuilder().withType(Material.EMERALD).setDisplayName("<green>HappyVillager").asItemStack());
            put(32, new ItemBuilder().withType(Material.FIRE_CHARGE).setDisplayName("<dark_red>AngryVillager").asItemStack());
            put(20, new ItemBuilder().withType(Material.SPAWNER).setDisplayName("<red>MobSpawner").asItemStack());
            put(24, new ItemBuilder().withType(Material.ENDER_EYE).setDisplayName("<light_purple>EnderSignal").asItemStack());
            put(19, new ItemBuilder().withType(Material.PRISMARINE_CRYSTALS).setDisplayName("<yellow>Rainbow").asItemStack());
            put(25, new ItemBuilder().withType(Material.GOLDEN_SHOVEL).setDisplayName("<gray>SnowBlast").asItemStack());
            put(33, new ItemBuilder().withType(Material.GOLD_BLOCK).setDisplayName("<yellow>Halo").asItemStack());
            put(29, new ItemBuilder().withType(Material.SNOWBALL).setDisplayName("<dark_red>SantaHat").asItemStack());
            put(18, new ItemBuilder().withType(Material.POTION).setDisplayName("<light_purple>SoulWell").asItemStack());
            put(26, new ItemBuilder().withType(Material.BLAZE_POWDER).setDisplayName("<dark_purple>BigSoulWell").asItemStack());
            put(28, new ItemBuilder().withType(Material.FIRE_CHARGE).setDisplayName("<dark_red>Volcano").asItemStack());
            put(34, new ItemBuilder().withType(Material.BLAZE_POWDER).setDisplayName("<red>FlameWheel").asItemStack());
            put(38, new ItemBuilder().withType(Material.APPLE).setDisplayName("<dark_red>LoveTornado").asItemStack());
            put(39, new ItemBuilder().withType(Material.BLAZE_ROD).setDisplayName("<dark_red>WitchTornado").asItemStack());
            put(41, new ItemBuilder().withType(Material.GOLDEN_APPLE).setDisplayName("<dark_red>LoveWell").asItemStack());
            put(42, new ItemBuilder().withType(Material.ENCHANTED_GOLDEN_APPLE).setDisplayName("<dark_red>BigLoveWell").asItemStack());
            put(53, new ItemBuilder().withType(Material.ARROW).setPersistentString(key, "fountains").setDisplayName("<yellow>Fountains").asItemStack());
        }};

        this.items.put(1, page_one);

        final Map<Integer, ItemStack> page_two = new HashMap<>() {{
            put(20, new ItemBuilder().withType(Material.EMERALD).setDisplayName("<green>Gems").asItemStack());
            put(21, new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer("c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f").setDisplayName("<gray>Food").asItemStack());
            put(22, new ItemBuilder().withType(Material.JACK_O_LANTERN).setDisplayName("<gold>Halloween").asItemStack());
            put(23, new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer(player.getName()).setDisplayName("<gray>Heads").asItemStack());
            put(24, new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer("2f2d1895fff4b1bb9116c8a9e229597f69f3eee88122776e5f973357e6b").setDisplayName("<dark_red>Presents").asItemStack());
            put(30, new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer("8c78d2102db75f1b3744a5e7e9baccf88fda4cc4979ebc0a81b7d9eb5721c0").setDisplayName("<gray>Mobs").asItemStack());
            put(31, new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer("625a82e966734bba3480b03dda8e1469b9f58ba494216113d129beab651cf8").setDisplayName("<yellow>Pokemon").asItemStack());
            put(32, new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer("a0c2549a893726988f3428bef799875ba871688ae64eb0cfdc43f7d6e24c6c").setDisplayName("<red>Mario").asItemStack());
            put(35, new ItemBuilder().withType(Material.ARROW).setPersistentString(key, "particles").setDisplayName("<yellow>Particles").asItemStack());
        }};
        
        this.items.put(2, page_two);
    }

    private final Player player = getPlayer();
    private final Gui gui = getGui();

    @Override
    public void open() {
        final Map<Integer, ItemStack> items = this.items.get(1);

        items.forEach((slot, item) -> this.gui.setItem(slot, new GuiItem(item)));

        this.gui.addSlotAction(53, action -> {
            this.items.get(1).keySet().forEach(this.gui::removeItem);

            this.items.get(2).forEach((slot, item) -> this.gui.setItem(slot, new GuiItem(item)));
        });

        this.gui.setDefaultClickAction(action -> {
            final ItemStack itemStack = action.getCurrentItem();

            if (itemStack == null) return;

            final PersistentDataContainerView container = itemStack.getPersistentDataContainer();

            if (!container.has(key)) return;

            final String type = container.get(key, PersistentDataType.STRING);

            if (type == null) return;

            switch (type) {
                case "fountains" -> {
                    this.items.get(1).keySet().forEach(this.gui::removeItem);

                    this.items.get(2).forEach((slot, item) -> this.gui.setItem(slot, new GuiItem(item)));
                }

                case "particles" -> {
                    this.items.get(2).keySet().forEach(this.gui::removeItem);

                    this.items.get(1).forEach((slot, item) -> this.gui.setItem(slot, new GuiItem(item)));
                }
            }
        });

        this.gui.open(this.player);
    }
}