package com.badbones69.blockparticles.plugin.items;

import com.badbones69.blockparticles.plugin.PaperImpl;
import com.badbones69.blockparticles.plugin.items.skulls.SkullCreator;
import com.badbones69.blockparticles.plugin.registry.PaperProvider;
import com.badbones69.blockparticles.plugin.utils.ItemUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import java.util.*;

@SuppressWarnings("unchecked")
public class BaseItemBuilder<Base extends BaseItemBuilder<Base>> {

    private final @NotNull PaperImpl paper = PaperProvider.get();

    private final @NotNull ItemUtil items = this.paper.getItemUtil();

    private final @NotNull SkullCreator skullCreator = this.paper.getSkullCreator();

    // Core.
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    private String displayName;

    private Material material;

    private int damage;

    // Custom Model Data.
    private boolean hasCustomModelData;

    private int customModelData;

    // Custom Heads.
    private String player;
    private boolean isHash;
    private boolean isURL;
    private boolean isHead;

    // Potions
    private boolean isPotion;
    private boolean isTippedArrow;
    private Color potionColor;
    private PotionType potionType;

    // Leather.
    private boolean isLeather;
    private boolean isArmor;
    private TrimMaterial trimMaterial;
    private TrimPattern trimPattern;
    private Color armorColor;

    // Banners.
    private final boolean isBanner;
    private List<Pattern> patterns;

    // Shields.
    private boolean isShield;

    // Firework.
    private boolean isFirework;
    private boolean isFireworkStar;

    // Enchantments/Flags
    private boolean isDurable;
    private boolean hideFlags;
    private boolean isGlowing;

    // Lore.
    private List<String> lore;

    protected BaseItemBuilder() {
        this.itemStack = null;
        this.itemMeta = null;
        this.material = Material.AIR;

        this.trimMaterial = null;
        this.trimPattern = null;

        this.hasCustomModelData = false;
        this.customModelData = 0;

        this.isHash = false;
        this.isURL = false;
        this.isHead = false;

        this.isPotion = false;
        this.potionColor = Color.WHITE;
        this.potionType = PotionType.MUNDANE;

        this.isTippedArrow = false;

        this.isLeather = false;
        this.armorColor = Color.WHITE;

        this.damage = 0;

        this.isBanner = false;
        this.isShield = false;
        this.patterns = Collections.emptyList();

        this.isFirework = false;
        this.isFireworkStar = false;

        this.isDurable = false;
        this.isGlowing = false;

        this.hideFlags = false;

        this.lore = Collections.emptyList();
    }

    protected BaseItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;

        this.material = itemStack.getType();

        switch (this.material) {
            case PLAYER_HEAD -> this.isHead = true;
            case POTION, SPLASH_POTION -> this.isPotion = true;
            case TIPPED_ARROW -> this.isTippedArrow = true;
            case LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS, LEATHER_HORSE_ARMOR -> this.isLeather = true;
            case SHIELD -> this.isShield = true;
            case FIREWORK_ROCKET -> this.isFirework = true;
            case FIREWORK_STAR -> this.isFireworkStar = true;
        }

        String name = this.material.name();

        this.isArmor = name.endsWith("_HELMET") || name.endsWith("_CHESTPLATE") || name.endsWith("_LEGGINGS") || name.endsWith("_BOOTS");

        this.isBanner = this.material.name().contains("BANNER");

        this.itemMeta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getServer().getItemFactory().getItemMeta(this.material);
    }

    public Base setDisplayName(String displayName) {
        this.displayName = displayName;
        return (Base) this;
    }

    public Base setLore(List<String> lore) {
        if (lore != null) {
            this.lore.clear();

            this.lore.addAll(lore);
        }

        return (Base) this;
    }

    public List<Component> getColoredLore() {
        List<Component> coloredLore = new ArrayList<>();

        for (String line : this.lore) {
            coloredLore.add(this.paper.getAdventure().parse(line));
        }

        return coloredLore;
    }

    public Base setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return (Base) this;
    }

    public Base addEnchantment(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        this.itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        return (Base) this;
    }

    public Base removeEnchantment(Enchantment enchantment) {
        this.itemMeta.removeEnchant(enchantment);
        return (Base) this;
    }

    public Base setEnchantments(HashMap<Enchantment, Integer> enchantments, boolean ignoreLevelRestriction) {
        enchantments.forEach((enchantment, integer) -> this.itemMeta.addEnchant(enchantment, integer, ignoreLevelRestriction));
        return (Base) this;
    }

    public Base addPatterns(List<String> patterns) {
        patterns.forEach(this::addPatterns);
        return (Base) this;
    }

    public Base addPattern(Pattern pattern) {
        this.patterns.add(pattern);
        return (Base) this;
    }

    public Base setPattern(List<Pattern> patterns) {
        this.patterns = patterns;
        return (Base) this;
    }

    public Base addItemFlags(List<String> flags) {
        flags.forEach(flag -> {
            try {
                ItemFlag itemFlag = ItemFlag.valueOf(flag.toUpperCase());

                addItemFlag(itemFlag);
            } catch (Exception exception) {
                this.paper.getFancyLogger().warn("Failed to add item-flag: " + flag + ". The flag is invalid!");
            }
        });

        return (Base) this;
    }

    public Base setPlayerName(String playerName) {
        this.player = playerName;

        if (player != null && player.length() > 16) {
            this.isHash = true;
            this.isURL = player.startsWith("http");
        }

        return (Base) this;
    }

    public Base setValue(String material) {
        if (material == null || material.isEmpty()) {
            this.paper.getFancyLogger().warn("Material cannot be null or empty, Output: " + material + ".");
            this.paper.getFancyLogger().warn("Please take a screenshot of this before asking for support.");

            return (Base) this;
        }

        String metaData;

        if (this.isPotion || this.isTippedArrow) {
            if (material.contains(";")) {
                String[] section = material.split(";");

                String[] sectionOne = section[0].split(":");
                String[] sectionTwo = section[1].split(":");

                try {
                    this.potionType = PotionType.valueOf(sectionOne[1]);
                } catch (Exception exception) {
                    this.paper.getFancyLogger().warn("Failed to set potion type " + sectionOne[1] + ". The potion type inputted is invalid.");
                }

                this.potionColor = getColor(sectionTwo[1]);
            }

            return (Base) this;
        }

        if (material.contains(":")) { // Sets the durability or another value option.
            String[] materialSplit = material.split(":");

            material = materialSplit[0];
            metaData = materialSplit[1];

            if (metaData.contains("#")) { // <ID>:<Durability>#<CustomModelData>
                String modelData = metaData.split("#")[1];

                if (isValidInteger(modelData)) {
                    this.hasCustomModelData = true;
                    this.customModelData = Integer.parseInt(modelData);
                }
            }

            metaData = metaData.replace("#" + this.customModelData, "");

            if (isValidInteger(metaData)) { // Value is durability.
                int damage = Integer.parseInt(metaData);

                if (this.itemMeta instanceof Damageable) ((Damageable) this.itemMeta).setDamage(damage);
            } else { // Value is something else.
                if (this.isPotion) {
                    this.potionType = PotionType.valueOf(metaData);

                    if (getColor(metaData) != null) this.potionColor = getColor(metaData);
                }

                if (this.isLeather) this.armorColor = getColor(metaData);
            }
        } else if (material.contains("#")) {
            String[] materialSplit = material.split("#");
            material = materialSplit[0];

            if (isValidInteger(materialSplit[1])) { // Value is a number.
                this.hasCustomModelData = true;
                this.customModelData = Integer.parseInt(materialSplit[1]);
            }
        }

        Material matchedMaterial = Material.matchMaterial(material);

        if (matchedMaterial != null) this.material = matchedMaterial;

        if (this.isArmor) ((ArmorMeta) this.itemMeta).setTrim(new ArmorTrim(this.trimMaterial, this.trimPattern));

        this.itemStack.setType(this.material);

        setItemMeta(this.itemStack.getItemMeta());

        return (Base) this;
    }

    public Base hideFlags(boolean hideFlags) {
        this.hideFlags = hideFlags;
        return (Base) this;
    }

    public Base setGlow(boolean isGlowing) {
        this.isGlowing = isGlowing;
        return (Base) this;
    }

    public Base setTrimMaterial(TrimMaterial trimMaterial) {
        this.trimMaterial = trimMaterial;
        return (Base) this;
    }

    public Base setTrimPattern(TrimPattern trimPattern) {
        this.trimPattern = trimPattern;
        return (Base) this;
    }

    public Base setDurable(boolean isDurable) {
        this.isDurable = isDurable;
        return (Base) this;
    }

    public Base setEffect(FireworkEffect... effects) {
        return setEffect(Arrays.asList(effects));
    }

    public Base setEffect(List<FireworkEffect> effects) {
        if (effects.isEmpty()) return (Base) this;

        if (this.isFireworkStar) {
            FireworkEffectMeta effectMeta = (FireworkEffectMeta) this.getItemMeta();

            effectMeta.setEffect(effects.get(0));
            this.setItemMeta(effectMeta);
        }

        if (this.isFirework) {
            FireworkMeta fireworkMeta = (FireworkMeta) this.getItemMeta();

            fireworkMeta.addEffects(effects);
            this.setItemMeta(fireworkMeta);
        }

        return (Base) this;
    }

    public Base setPower(int power) {
        if (this.isFirework) {
            FireworkMeta fireworkMeta = (FireworkMeta) this.getItemMeta();

            fireworkMeta.setPower(power);

            this.setItemMeta(fireworkMeta);
        }

        return (Base) this;
    }

    public Base setDamage(int damage) {
        this.damage = damage;

        return (Base) this;
    }

    public ItemStack build() {
        if (this.material != Material.AIR) {
            if (this.isHead) { // Has to go 1st due to it removing all data when finished.
                if (this.isHash) { // Sauce: https://github.com/deanveloper/SkullCreator
                    if (this.isURL) {
                        this.skullCreator.itemWithUrl(this.itemStack, player);
                    } else {
                        this.skullCreator.itemWithBase64(this.itemStack, player);
                    }
                }
            }

            ItemMeta meta = this.itemMeta;

            List<Component> newLore = getColoredLore();

            if (!newLore.isEmpty()) meta.lore(getColoredLore());

            this.itemMeta.displayName(this.paper.getAdventure().parse(this.displayName));

            if (this.isPotion || this.isTippedArrow && (this.potionType != null || this.potionColor != null)) {
                PotionMeta potionMeta = (PotionMeta) meta;

                if (this.potionType != null) potionMeta.setBasePotionData(new PotionData(this.potionType));

                if (this.potionColor != null) potionMeta.setColor(this.potionColor);

                this.setItemMeta(potionMeta);
            }

            if (meta instanceof Damageable) {
                if (this.damage >= 1) {
                    if (this.damage >= this.itemStack.getType().getMaxDurability()) {
                        ((Damageable) meta).setDamage(this.itemStack.getType().getMaxDurability());
                    } else {
                        ((Damageable) meta).setDamage(this.damage);
                    }
                }
            }

            if (this.isLeather && this.armorColor != null) {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
                leatherArmorMeta.setColor(this.armorColor);
            }

            if (this.isBanner && !this.patterns.isEmpty()) {
                BannerMeta bannerMeta = (BannerMeta) meta;
                bannerMeta.setPatterns(this.patterns);
            }

            if (this.isShield && !this.patterns.isEmpty()) {
                BlockStateMeta shieldMeta = (BlockStateMeta) meta;
                Banner banner = (Banner) shieldMeta.getBlockState();

                banner.setPatterns(this.patterns);
                banner.update();

                shieldMeta.setBlockState(banner);
            }

            if (this.hasCustomModelData) meta.setCustomModelData(this.customModelData);

            if (this.hideFlags) meta.addItemFlags(ItemFlag.values());

            meta.setUnbreakable(this.isDurable);

            addGlow();
        } else {
            this.paper.getFancyLogger().warn("Material cannot be AIR.");
        }

        this.itemStack.setItemMeta(this.itemMeta);

        return this.itemStack;
    }

    private void addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
    }

    private void addGlow() {
        if (this.isGlowing) {
            if (this.itemMeta.hasEnchants()) return;

            this.itemMeta.addEnchant(Enchantment.LUCK, 1, false);

            this.setItemMeta(this.itemMeta);
        }
    }

    private boolean isValidInteger(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return false;
        }

        return true;
    }

    private Color getColor(String color) {
        if (color != null) {
            switch (color.toUpperCase()) {
                case "AQUA" -> {
                    return Color.AQUA;
                }
                case "BLACK" -> {
                    return Color.BLACK;
                }
                case "BLUE" -> {
                    return Color.BLUE;
                }
                case "FUCHSIA" -> {
                    return Color.FUCHSIA;
                }
                case "GRAY" -> {
                    return Color.GRAY;
                }
                case "GREEN" -> {
                    return Color.GREEN;
                }
                case "LIME" -> {
                    return Color.LIME;
                }
                case "MAROON" -> {
                    return Color.MAROON;
                }
                case "NAVY" -> {
                    return Color.NAVY;
                }
                case "OLIVE" -> {
                    return Color.OLIVE;
                }
                case "ORANGE" -> {
                    return Color.ORANGE;
                }
                case "PURPLE" -> {
                    return Color.PURPLE;
                }
                case "RED" -> {
                    return Color.RED;
                }
                case "SILVER" -> {
                    return Color.SILVER;
                }
                case "TEAL" -> {
                    return Color.TEAL;
                }
                case "WHITE" -> {
                    return Color.WHITE;
                }
                case "YELLOW" -> {
                    return Color.YELLOW;
                }
            }

            try {
                String[] rgb = color.split(",");
                return Color.fromRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            } catch (Exception ignore) {}
        }

        return null;
    }

    private void addPatterns(String stringPattern) {
        try {
            String[] split = stringPattern.split(":");

            for (PatternType pattern : PatternType.values()) {

                if (split[0].equalsIgnoreCase(pattern.name()) || split[0].equalsIgnoreCase(pattern.getIdentifier())) {
                    DyeColor color = getDyeColor(split[1]);

                    if (color != null) this.addPattern(new Pattern(color, pattern));

                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    public DyeColor getDyeColor(String color) {
        if (color != null) {
            try {
                return DyeColor.valueOf(color.toUpperCase());
            } catch (Exception exception) {
                try {
                    String[] rgb = color.split(",");
                    return DyeColor.getByColor(Color.fromRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
                } catch (Exception ignore) {}
            }
        }

        return null;
    }

    // Protected getters for extended builders.
    protected ItemStack getItemStack() {
        return this.itemStack;
    }

    protected void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    protected ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    protected void setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }
}