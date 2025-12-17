package com.badbones69.blockparticles.api.builders.types;

import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.builders.gui.StaticInventoryBuilder;
import com.ryderbelserion.fusion.paper.builders.ItemBuilder;
import com.ryderbelserion.fusion.paper.builders.gui.interfaces.Gui;
import com.ryderbelserion.fusion.paper.builders.gui.interfaces.GuiItem;
import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.persistence.PersistentDataType;
import java.util.HashMap;
import java.util.Map;

public class MainMenu extends StaticInventoryBuilder {

    private final NamespacedKey key = new NamespacedKey(this.plugin, "button");
    
    private final Map<Integer, Map<Integer, ItemStack>> items;

    private final String location;

    public MainMenu(final Player player, final String title, final int rows, String location) {
        super(player, title, rows);
        
        this.items = new HashMap<>();
        
        this.items.put(1, new HashMap<>());
        this.items.put(2, new HashMap<>());
        
        final Map<Integer, ItemStack> page_one = new HashMap<>() {{
            put(0, new ItemBuilder(ItemType.BROWN_MUSHROOM).setPersistentString(key, "Crit").withDisplayName("<gold>Crit").asItemStack());
            put(1, new ItemBuilder(ItemType.BROWN_MUSHROOM_BLOCK).setPersistentString(key, "BigCrit").withDisplayName("<gold>BigCrit").asItemStack());
            put(2, new ItemBuilder(ItemType.FIREWORK_ROCKET).setPersistentString(key, "Spiral").withDisplayName("<white>Spiral").asItemStack());
            put(3, new ItemBuilder(ItemType.FIREWORK_ROCKET).setPersistentString(key, "DoubleSpiral").withDisplayName("<white>DoubleSpiral").asItemStack());
            put(4, new ItemBuilder(ItemType.FLINT).setPersistentString(key, "Flame").withDisplayName("<red>Flame").asItemStack());
            put(5, new ItemBuilder(ItemType.FLINT_AND_STEEL).setPersistentString(key, "BigFlame").withDisplayName("<red>BigFlame").asItemStack());
            put(6, new ItemBuilder(ItemType.WATER_BUCKET).setPersistentString(key, "Storm").withDisplayName("<gray>Storm").asItemStack());
            put(7, new ItemBuilder(ItemType.LAVA_BUCKET).setPersistentString(key, "FireStorm").withDisplayName("<dark_red>FireStorm").asItemStack());
            put(8, new ItemBuilder(ItemType.SNOWBALL).setPersistentString(key, "SnowStorm").withDisplayName("<gray>SnowStorm").asItemStack());
            put(9, new ItemBuilder(ItemType.POTION).setPersistentString(key, "Potion").withDisplayName("<dark_purple>Potion").asItemStack());
            put(10, new ItemBuilder(ItemType.SPIDER_EYE).setPersistentString(key, "Witch").withDisplayName("<light_purple>Witch").asItemStack());
            put(11, new ItemBuilder(ItemType.FERMENTED_SPIDER_EYE).setPersistentString(key, "DoubleWitch").withDisplayName("<light_purple>DoubleWitch").asItemStack());
            put(12, new ItemBuilder(ItemType.STICK).setPersistentString(key, "Magic").withDisplayName("<dark_blue>Magic").asItemStack());
            put(13, new ItemBuilder(ItemType.NOTE_BLOCK).setPersistentString(key, "Music").withDisplayName("<aqua>Music").asItemStack());
            put(14, new ItemBuilder(ItemType.SNOW_BLOCK).setPersistentString(key, "Snow").withDisplayName("<white>Snow").asItemStack());
            put(15, new ItemBuilder(ItemType.BONE).setPersistentString(key, "Fog").withDisplayName("<gray>Fog").asItemStack());
            put(16, new ItemBuilder(ItemType.NETHER_STAR).setPersistentString(key, "Spew").withDisplayName("<white>Spew").asItemStack());
            put(17, new ItemBuilder(ItemType.NETHER_BRICK).setPersistentString(key, "FireSpew").withDisplayName("<red>FireSpew").asItemStack());
            put(21, new ItemBuilder(ItemType.CHAINMAIL_CHESTPLATE).setPersistentString(key, "Chains").withDisplayName("<red>Chains").asItemStack());
            put(22, new ItemBuilder(ItemType.ENCHANTING_TABLE).setPersistentString(key, "Enchant").withDisplayName("<gray>Enchant").asItemStack());
            put(23, new ItemBuilder(ItemType.LEATHER_BOOTS).setPersistentString(key, "FootPrint").withDisplayName("<gold>FootPrint").asItemStack());
            put(31, new ItemBuilder(ItemType.WATER_BUCKET).setPersistentString(key, "Water").withDisplayName("<light_blue>Water").asItemStack());
            put(30, new ItemBuilder(ItemType.EMERALD).setPersistentString(key, "HappyVillager").withDisplayName("<green>HappyVillager").asItemStack());
            put(32, new ItemBuilder(ItemType.FIRE_CHARGE).setPersistentString(key, "AngryVillager").withDisplayName("<dark_red>AngryVillager").asItemStack());
            put(20, new ItemBuilder(ItemType.SPAWNER).setPersistentString(key, "MobSpawner").withDisplayName("<red>MobSpawner").asItemStack());
            put(24, new ItemBuilder(ItemType.ENDER_EYE).setPersistentString(key, "EnderSignal").withDisplayName("<light_purple>EnderSignal").asItemStack());
            put(19, new ItemBuilder(ItemType.PRISMARINE_CRYSTALS).setPersistentString(key, "Rainbow").withDisplayName("<yellow>Rainbow").asItemStack());
            put(25, new ItemBuilder(ItemType.GOLDEN_SHOVEL).setPersistentString(key, "SnowBlast").withDisplayName("<gray>SnowBlast").asItemStack());
            put(33, new ItemBuilder(ItemType.GOLD_BLOCK).setPersistentString(key, "Halo").withDisplayName("<yellow>Halo").asItemStack());
            put(29, new ItemBuilder(ItemType.SNOWBALL).setPersistentString(key, "SantaHat").withDisplayName("<dark_red>SantaHat").asItemStack());
            put(18, new ItemBuilder(ItemType.POTION).setPersistentString(key, "SoulWell").withDisplayName("<light_purple>SoulWell").asItemStack());
            put(26, new ItemBuilder(ItemType.BLAZE_POWDER).setPersistentString(key, "BigSoulWell").withDisplayName("<dark_purple>BigSoulWell").asItemStack());
            put(28, new ItemBuilder(ItemType.FIRE_CHARGE).setPersistentString(key, "Volcano").withDisplayName("<dark_red>Volcano").asItemStack());
            put(34, new ItemBuilder(ItemType.BLAZE_POWDER).setPersistentString(key, "FlameWheel").withDisplayName("<red>FlameWheel").asItemStack());
            put(38, new ItemBuilder(ItemType.APPLE).setPersistentString(key, "LoveTornado").withDisplayName("<dark_red>LoveTornado").asItemStack());
            put(39, new ItemBuilder(ItemType.BLAZE_ROD).setPersistentString(key, "WitchTornado").withDisplayName("<dark_red>WitchTornado").asItemStack());
            put(41, new ItemBuilder(ItemType.GOLDEN_APPLE).setPersistentString(key, "LoveWell").withDisplayName("<dark_red>LoveWell").asItemStack());
            put(42, new ItemBuilder(ItemType.ENCHANTED_GOLDEN_APPLE).setPersistentString(key, "BigLoveWell").withDisplayName("<dark_red>BigLoveWell").asItemStack());
            put(53, new ItemBuilder(ItemType.ARROW).setPersistentString(key, "fountains").withDisplayName("<yellow>Fountains").asItemStack());
        }};

        this.items.put(1, page_one);

        final Map<Integer, ItemStack> page_two = new HashMap<>() {{
            put(20, new ItemBuilder(ItemType.EMERALD).setPersistentString(key, "Gems").withDisplayName("<green>Gems").asItemStack());
            put(21, new ItemBuilder(ItemType.PLAYER_HEAD).setPersistentString(key, "Food").asSkullBuilder().withUrl("c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f").withDisplayName("<gray>Food").asItemStack());
            put(22, new ItemBuilder(ItemType.JACK_O_LANTERN).setPersistentString(key, "Halloween").withDisplayName("<gold>Halloween").asItemStack());
            put(23, new ItemBuilder(ItemType.PLAYER_HEAD).setPersistentString(key, "Heads").asSkullBuilder().withName(player.getName()).withDisplayName("<gray>Heads").asItemStack());
            put(24, new ItemBuilder(ItemType.PLAYER_HEAD).setPersistentString(key, "Presents").asSkullBuilder().withUrl("2f2d1895fff4b1bb9116c8a9e229597f69f3eee88122776e5f973357e6b").withDisplayName("<dark_red>Presents").asItemStack());
            put(30, new ItemBuilder(ItemType.PLAYER_HEAD).setPersistentString(key, "Mobs").asSkullBuilder().withUrl("8c78d2102db75f1b3744a5e7e9baccf88fda4cc4979ebc0a81b7d9eb5721c0").withDisplayName("<gray>Mobs").asItemStack());
            put(31, new ItemBuilder(ItemType.PLAYER_HEAD).setPersistentString(key, "Pokemon").asSkullBuilder().withUrl("625a82e966734bba3480b03dda8e1469b9f58ba494216113d129beab651cf8").withDisplayName("<yellow>Pokemon").asItemStack());
            put(32, new ItemBuilder(ItemType.PLAYER_HEAD).setPersistentString(key, "Mario").asSkullBuilder().withUrl("a0c2549a893726988f3428bef799875ba871688ae64eb0cfdc43f7d6e24c6c").withDisplayName("<red>Mario").asItemStack());
            put(35, new ItemBuilder(ItemType.ARROW).setPersistentString(key, "particles").withDisplayName("<yellow>Particles").asItemStack());
        }};
        
        this.items.put(2, page_two);

        this.location = location;
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

            final Inventory inventory = this.gui.getInventory();

            switch (type) {
                case "fountains" -> {
                    this.items.get(1).keySet().forEach(slot -> {
                        this.gui.removeItem(slot);

                        inventory.setItem(slot, null);
                    });

                    this.items.get(2).forEach((slot, item) -> this.gui.setItem(slot, new GuiItem(item)));
                }

                case "particles" -> {
                    this.items.get(2).keySet().forEach(slot -> {
                        this.gui.removeItem(slot);

                        inventory.setItem(slot, null);
                    });

                    this.items.get(1).forEach((slot, item) -> this.gui.setItem(slot, new GuiItem(item)));
                }

                default -> Methods.setLoc(this.player, this.location, type);
            }
        });

        this.gui.open(this.player);
    }
}