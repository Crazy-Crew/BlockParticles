package me.badbones69.blockparticles;

import me.badbones69.blockparticles.api.FileManager.Files;
import me.badbones69.blockparticles.api.CrazyManager;
import me.badbones69.blockparticles.api.enums.BPFountains;
import me.badbones69.blockparticles.api.enums.BPParticles;
import me.badbones69.blockparticles.api.enums.types.Particles;
import me.badbones69.blockparticles.api.objects.ItemBuilder;
import me.badbones69.blockparticles.controllers.Fountains;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Methods implements Listener {
    
    public static HashMap<Location, Location> Locations = new HashMap<>();
    private static CrazyManager bp = CrazyManager.getInstance();
    
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static String removeColor(String msg) {
        return ChatColor.stripColor(msg);
    }
    
    public static void reset() {
        kill();
        startParticles();
    }
    
    private static Collection<Entity> getNearbyEntities(Location loc, double x, double y, double z) {
        try {
            return loc.getWorld().getNearbyEntities(loc, x, y, z);
        } catch (Exception ignored) {}

        return new ArrayList<>();
    }
    
    public static boolean noPlayers(Location loc, int range) {
        try {
            Collection<Entity> out = getNearbyEntities(loc, range, range, range);
            if (!out.isEmpty()) {
                for (Entity e : out) {
                    if (e instanceof LivingEntity en) {
                        if (en instanceof Player) return false;
                    }
                }
            }
        } catch (Exception ignored) {}

        return true;
    }
    
    public static ArrayList<String> getLocations() {
        if (Files.DATA.getFile().contains("locations")) {
            return new ArrayList<>(Files.DATA.getFile().getConfigurationSection("locations").getKeys(false));
        } else {
            return new ArrayList<>();
        }
    }
    
    public static void kill() {
        bp.getParticleControl().getLocations().clear();
        for (World w : Bukkit.getServer().getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (e instanceof Item item) {
                    if (bp.getFountainItem().contains(item)) item.remove();
                }
            }
        }
        bp.getFountainItem().clear();
        Bukkit.getScheduler().cancelTasks(CrazyManager.getInstance().getPlugin());
    }
    
    public static void startParticles() {
        FileConfiguration data = Files.DATA.getFile();
        if (data.contains("locations")) {
            for (final String id : data.getConfigurationSection("locations").getKeys(false)) {
                World world = Bukkit.getServer().getWorld(data.getString("locations." + id + ".world"));
                String particle = data.getString("locations." + id + ".particle");
                final Location loc = new Location(world, data.getInt("locations." + id + ".x"), data.getInt("locations." + id + ".y"), data.getInt("locations." + id + ".z"));
                if (BPFountains.getFromName(particle) != null) {
                    switch (Objects.requireNonNull(BPFountains.getFromName(particle))) {
                        case MARIO -> Fountains.startMario(loc, id);
                        case POKEMON -> Fountains.startPokemon(loc, id);
                        case FOOD -> Fountains.startFood(loc, id);
                        case MOBS -> Fountains.startMobs(loc, id);
                        case HALLOWEEN -> Fountains.startHalloween(loc, id);
                        case GEMS -> Fountains.startGems(loc, id);
                        case HEADS -> Fountains.startHeads(loc, id);
                        case PRESENTS -> Fountains.startPresents(loc, id);
                        case CUSTOM -> Fountains.startCustomFountain(loc, id, particle);
                    }
                }

                if (BPParticles.getFromName(particle) != null) {
                    switch (Objects.requireNonNull(BPParticles.getFromName(particle))) {
                        case LOVEWELL -> bp.getParticleControl().playLoveWell(loc, id);
                        case BIGLOVEWELL -> bp.getParticleControl().playBigLoveWell(loc, id);
                        case LOVETORNADO -> bp.getParticleControl().playLoveTornado(loc, id);
                        case WITCHTORNADO -> bp.getParticleControl().playWitchTornado(loc, id);
                        case FLAMEWHEEL -> bp.getParticleControl().playFlameWheel(loc, id);
                        case SOULWELL -> bp.getParticleControl().playSoulWell(loc, id);
                        case BIGSOULWELL -> bp.getParticleControl().playBigSoulWell(loc, id);
                        case SANTAHAT -> bp.getParticleControl().playSantaHat(loc, id);
                        case SNOWBLAST -> bp.getParticleControl().playSnowBlast(loc, id);
                        case RAINBOW -> bp.getParticleControl().playRainbow(loc, id);
                        case ENDERSIGNAL -> bp.getParticleControl().playEnderSignal(loc, id);
                        case MOBSPAWNER -> bp.getParticleControl().playMobSpawner(loc, id);
                        case ANGRYVILLAGER -> bp.getParticleControl().playAngryVillager(loc, id);
                        case HAPPYVILLAGER -> bp.getParticleControl().playHappyVillager(loc, id);
                        case FOOTPRINT -> bp.getParticleControl().playFootPrint(loc, id);
                        case FIRESPEW -> bp.getParticleControl().playFireSpew(loc, id);
                        case SNOWSTORM -> bp.getParticleControl().playSnowStorm(loc, id);
                        case DOUBLEWITCH -> bp.getParticleControl().playDoubleSpiral(loc, id, Particles.DOUBLEWITCH, 5);
                        case WITCH -> bp.getParticleControl().playSpiral(loc, id, Particles.WITCH, 5);
                        case MAGIC -> bp.getParticleControl().playMagic(loc, id);
                        case SPEW -> bp.getParticleControl().playSpew(loc, id);
                        case HALO -> bp.getParticleControl().playHalo(loc, id);
                        case MUSIC -> bp.getParticleControl().playMusic(loc, id);
                        case POTION -> bp.getParticleControl().playPotion(loc, id);
                        case SNOW -> bp.getParticleControl().playSnow(loc, id);
                        case FIRESTORM -> bp.getParticleControl().playFireStorm(loc, id);
                        case WATER -> bp.getParticleControl().startWater(loc, id);
                        case CHAINS -> bp.getParticleControl().playChains(loc, id);
                        case ENCHANT -> bp.getParticleControl().playEnchant(loc, id);
                        case FOG -> bp.getParticleControl().playFog(loc, id);
                        case STORM -> bp.getParticleControl().playStorm(loc, id);
                        case BIGFLAME -> bp.getParticleControl().playBigFlame(loc, id);
                        case FLAME -> bp.getParticleControl().playFlame(loc, id);
                        case VOLCANO -> bp.getParticleControl().playVolcano(loc, id);
                        case SPIRAL -> bp.getParticleControl().playSpiral(loc, id, Particles.SPIRAL, 1);
                        case DOUBLESPIRAL -> bp.getParticleControl().playDoubleSpiral(loc, id, Particles.DOUBLESPIRAL, 5);
                        case CRIT -> bp.getParticleControl().playCrit(loc, id);
                        case BIGCRIT -> bp.getParticleControl().playBigCrit(loc, id);
                    }
                }
            }
        }
    }
    
    public static void addLoc(Player player, String name) {
        String Prefix = Files.CONFIG.getFile().getString("settings.prefix");
        if (Files.DATA.getFile().contains("locations")) {
            for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    player.sendMessage(color(Prefix + "&3That location name is taken please remove it and replace it here."));
                    return;
                }
            }
        }
        Block block = player.getTargetBlock(null, 5);
        if (block.isEmpty()) {
            player.sendMessage(Methods.color(Prefix + "&cYou are not looking at a block."));
            return;
        }
        Location l = block.getLocation();
        String w = l.getWorld().getName();
        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();
        Files.DATA.getFile().set("locations." + name + ".world", w);
        Files.DATA.getFile().set("locations." + name + ".x", x);
        Files.DATA.getFile().set("locations." + name + ".y", y);
        Files.DATA.getFile().set("locations." + name + ".z", z);
        Files.DATA.getFile().set("locations." + name + ".particle", "Spiral");
        Files.DATA.saveFile();
        kill();
        startParticles();
        player.sendMessage(color(Prefix + "&3You have added &6" + name + " &3to the block."));
    }
    
    public static void delLoc(CommandSender player, String name) {
        String Prefix = Files.CONFIG.getFile().getString("settings.prefix");
        if (Files.DATA.getFile().contains("locations")) {
            for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    Files.DATA.getFile().set("locations." + loc, null);
                    Files.DATA.saveFile();
                    Bukkit.getScheduler().cancelTask(bp.getParticleControl().getLocations().get(loc));
                    player.sendMessage(color(Prefix + "&3You have just deleted &6" + name + "&3."));
                    return;
                }
            }
        }

        player.sendMessage(color(Prefix + "&3There are no locations called &6" + name + "&3."));
    }
    
    public static void listLoc(Player player) {
        String Prefix = Methods.color(Files.CONFIG.getFile().getString("settings.prefix"));
        if (!Files.DATA.getFile().contains("locations")) {
            player.sendMessage(Prefix + Methods.color("&cThere are no locations set!"));
            return;
        }
        if (Files.DATA.getFile().getConfigurationSection("locations").getKeys(false).isEmpty()) {
            player.sendMessage(Prefix + Methods.color("&cThere are no locations set!"));
            return;
        }
        String msg;
        String part;
        StringBuilder l = new StringBuilder();
        int line = 1;
        for (String L : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
            if (Files.DATA.getFile().getConfigurationSection("locations." + L).getKeys(false).isEmpty()) {
                Files.DATA.getFile().set("locations." + L, null);
                Files.DATA.saveFile();
                continue;
            }
            String W = Files.DATA.getFile().getString("locations." + L + ".world");
            String X = Files.DATA.getFile().getString("locations." + L + ".x");
            String Y = Files.DATA.getFile().getString("locations." + L + ".y");
            String Z = Files.DATA.getFile().getString("locations." + L + ".z");
            
            part = Methods.color("&8[&6" + line + "&8]: " + "&c" + L + "&8, &c" + W + "&8, &c" + X + "&8, &c" + Y + "&8, &c" + Z);
            l.append(part);
            l.append("\n");
            line++;
            
        }
        msg = l.toString();
        line = line - 1;
        player.sendMessage(Prefix + Methods.color("&6A list of all the locations."));
        player.sendMessage(Methods.color("&c[Locations Name]&8, &c[World]&8, &c[X]&8, &c[Y]&8, &c[Z]"));
        player.sendMessage(msg);
        player.sendMessage(Methods.color("&3Number of locations: &6" + line));
    }
    
    public static int randomColor() {
        Random r = new Random();
        return r.nextInt(255);
    }
    
    public static ItemStack makeItem(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(color(name));
        item.setItemMeta(m);
        return item;
    }
    
    public static void setLoc(CommandSender player, String name, String particle) {
        String prefix = Files.CONFIG.getFile().getString("settings.prefix");
        if (BPFountains.getFromName(particle) == null && BPParticles.getFromName(particle) == null && bp.getCustomFountain(particle) == null) {
            player.sendMessage(color(prefix + "&6" + particle + " &cis not a particle. Please do /bp help for more information."));
            return;
        }
        if (Files.DATA.getFile().contains("locations")) {
            for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    Files.DATA.getFile().set("locations." + loc + ".particle", particle);
                    Files.DATA.saveFile();
                    kill();
                    startParticles();
                    player.sendMessage(color(prefix + "&3You have just set &6" + name + "'s &3particle to &6" + particle + "&3."));
                    return;
                }
            }
        }
        player.sendMessage(color(prefix + "&3There are no locations called &6" + name + "&3."));
    }
    
    public static ItemStack getPlayerHead(String name) {
        return getPlayerHead(name, null);
    }
    
    public static ItemStack getPlayerHead(String playerName, String displayName) {
        return new ItemBuilder()
        .setMaterial("PLAYER_HEAD", "SKULL_ITEM:3")
        .setPlayer(playerName)
        .setName(displayName != null ? color(displayName) : new Random().nextInt(Integer.MAX_VALUE) + "")
        .build();
    }
}