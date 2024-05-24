package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.api.enums.fountains.BPFountains;
import com.badbones69.blockparticles.api.enums.particles.BPParticles;
import com.badbones69.blockparticles.api.enums.CustomFiles;
import com.badbones69.blockparticles.api.enums.particles.Particles;
import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.ConfigKeys;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.controllers.ParticleControl;
import com.ryderbelserion.vital.core.config.YamlFile;
import com.ryderbelserion.vital.paper.builders.items.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Methods implements Listener {

    private static final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private static final ParticleManager particleManager = plugin.getParticleManager();
    
    private static final ParticleControl particleControl = plugin.getParticleControl();
    
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
                        if (en instanceof Player) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception ignored) {}

        return true;
    }
    
    public static ArrayList<String> getLocations() {
        CustomFiles file = CustomFiles.data;
        YamlFile data = file.getYamlFile();
        
        return data.contains("locations") ? new ArrayList<>(data.getConfigurationSection("locations").getKeys(false)) : new ArrayList<>();
    }
    
    public static void kill() {
        particleControl.getLocations().clear();

        for (World w : plugin.getServer().getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (e instanceof Item item) {
                    if (particleManager.getFountainItem().contains(item)) {
                        item.remove();
                    }
                }
            }
        }

        particleManager.getFountainItem().clear();
        
        plugin.getServer().getScheduler().cancelTasks(plugin);
    }
    
    public static void startParticles() {
        CustomFiles file = CustomFiles.data;
        YamlFile data = file.getYamlFile();
        
        if (data.contains("locations")) {
            for (final String id : data.getConfigurationSection("locations").getKeys(false)) {
                World world = plugin.getServer().getWorld(data.getString("locations." + id + ".world"));
                String particle = data.getString("locations." + id + ".particle");
                final Location loc = new Location(world, data.getInt("locations." + id + ".x"), data.getInt("locations." + id + ".y"), data.getInt("locations." + id + ".z"));
                
                if (BPFountains.getFromName(particle) != null) {
                    switch (BPFountains.getFromName(particle)) {
                        case MARIO:
                            Fountains.startMario(loc, id);
                            break;
                        case POKEMON:
                            Fountains.startPokemon(loc, id);
                            break;
                        case FOOD:
                            Fountains.startFood(loc, id);
                            break;
                        case MOBS:
                            Fountains.startMobs(loc, id);
                            break;
                        case HALLOWEEN:
                            Fountains.startHalloween(loc, id);
                            break;
                        case GEMS:
                            Fountains.startGems(loc, id);
                            break;
                        case HEADS:
                            Fountains.startHeads(loc, id);
                            break;
                        case PRESENTS:
                            Fountains.startPresents(loc, id);
                            break;
                        case CUSTOM:
                            Fountains.startCustomFountain(loc, id, particle);
                            break;
                    }
                }
                
                if (BPParticles.getFromName(particle) != null) {
                    switch (BPParticles.getFromName(particle)) {
                        case LOVEWELL:
                            particleControl.playLoveWell(loc, id);
                            break;
                        case BIGLOVEWELL:
                            particleControl.playBigLoveWell(loc, id);
                            break;
                        case LOVETORNADO:
                            particleControl.playLoveTornado(loc, id);
                            break;
                        case WITCHTORNADO:
                            particleControl.playWitchTornado(loc, id);
                            break;
                        case FLAMEWHEEL:
                            particleControl.playFlameWheel(loc, id);
                            break;
                        case SOULWELL:
                            particleControl.playSoulWell(loc, id);
                            break;
                        case BIGSOULWELL:
                            particleControl.playBigSoulWell(loc, id);
                            break;
                        case SANTAHAT:
                            particleControl.playSantaHat(loc, id);
                            break;
                        case SNOWBLAST:
                            particleControl.playSnowBlast(loc, id);
                            break;
                        case RAINBOW:
                            particleControl.playRainbow(loc, id);
                            break;
                        case ENDERSIGNAL:
                            particleControl.playEnderSignal(loc, id);
                            break;
                        case MOBSPAWNER:
                            particleControl.playMobSpawner(loc, id);
                            break;
                        case ANGRYVILLAGER:
                            particleControl.playAngryVillager(loc, id);
                            break;
                        case HAPPYVILLAGER:
                            particleControl.playHappyVillager(loc, id);
                            break;
                        case FOOTPRINT:
                            particleControl.playFootPrint(loc, id);
                            break;
                        case FIRESPEW:
                            particleControl.playFireSpew(loc, id);
                            break;
                        case SNOWSTORM:
                            particleControl.playSnowStorm(loc, id);
                            break;
                        case DOUBLEWITCH:
                            particleControl.playDoubleSpiral(loc, id, Particles.DOUBLEWITCH, 5);
                            break;
                        case WITCH:
                            particleControl.playSpiral(loc, id, Particles.WITCH, 5);
                            break;
                        case MAGIC:
                            particleControl.playMagic(loc, id);
                            break;
                        case SPEW:
                            particleControl.playSpew(loc, id);
                            break;
                        case HALO:
                            particleControl.playHalo(loc, id);
                            break;
                        case MUSIC:
                            particleControl.playMusic(loc, id);
                            break;
                        case POTION:
                            particleControl.playPotion(loc, id);
                            break;
                        case SNOW:
                            particleControl.playSnow(loc, id);
                            break;
                        case FIRESTORM:
                            particleControl.playFireStorm(loc, id);
                            break;
                        case WATER:
                            particleControl.startWater(loc, id);
                            break;
                        case CHAINS:
                            particleControl.playChains(loc, id);
                            break;
                        case ENCHANT:
                            particleControl.playEnchant(loc, id);
                            break;
                        case FOG:
                            particleControl.playFog(loc, id);
                            break;
                        case STORM:
                            particleControl.playStorm(loc, id);
                            break;
                        case BIGFLAME:
                            particleControl.playBigFlame(loc, id);
                            break;
                        case FLAME:
                            particleControl.playFlame(loc, id);
                            break;
                        case VOLCANO:
                            particleControl.playVolcano(loc, id);
                            break;
                        case SPIRAL:
                            particleControl.playSpiral(loc, id, Particles.SPIRAL, 1);
                            break;
                        case DOUBLESPIRAL:
                            particleControl.playDoubleSpiral(loc, id, Particles.DOUBLESPIRAL, 5);
                            break;
                        case CRIT:
                            particleControl.playCrit(loc, id);
                            break;
                        case BIGCRIT:
                            particleControl.playBigCrit(loc, id);
                            break;
                    }
                }
            }
        }
    }
    
    public static void addLoc(Player player, String name) {
        CustomFiles file = CustomFiles.data;
        YamlFile data = file.getYamlFile();

        if (data.contains("locations")) {
            for (String loc : data.getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    Messages.location_already_taken.sendMessage(player);

                    return;
                }
            }
        }
        
        Block block = player.getTargetBlock(null, 5);
        
        if (block.isEmpty()) {
            Messages.not_looking_at_block.sendMessage(player);
            
            return;
        }
        
        Location l = block.getLocation();
        String w = l.getWorld().getName();
        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();

        data.set("locations." + name + ".world", w);
        data.set("locations." + name + ".x", x);
        data.set("locations." + name + ".y", y);
        data.set("locations." + name + ".z", z);
        data.set("locations." + name + ".particle", "Spiral");
        
        file.save();
        
        kill();
        startParticles();

        Messages.location_added.sendMessage(player, new HashMap<>() {{
            put("{name}", name);
        }});
    }
    
    public static void delLoc(CommandSender player, String name) {
        CustomFiles file = CustomFiles.data;
        YamlFile data = file.getYamlFile();

        if (data.contains("locations")) {
            for (String loc : data.getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    data.set("locations." + loc, null);
                    file.save();
                    
                    plugin.getServer().getScheduler().cancelTask(particleControl.getLocations().get(loc));

                    Messages.location_deleted.sendMessage(player, new HashMap<>() {{
                        put("{name}", name);
                    }});
                    
                    return;
                }
            }
        }

        Messages.location_does_not_exist.sendMessage(player, new HashMap<>() {{
            put("{name}", name);
        }});
    }
    
    public static void listLoc(Player player) {
        CustomFiles file = CustomFiles.data;
        YamlFile data = file.getYamlFile();
        
        String prefix = ConfigManager.getConfig().getProperty(ConfigKeys.command_prefix);
        
        if (!data.contains("locations") || data.getConfigurationSection("locations").getKeys(false).isEmpty()) {
            Messages.location_empty.sendMessage(player);
            
            return;
        }
        
        String msg;
        String part;
        StringBuilder l = new StringBuilder();
        int line = 1;

        //todo() re-do storage
        for (String L : data.getConfigurationSection("locations").getKeys(false)) {
            if (data.getConfigurationSection("locations." + L).getKeys(false).isEmpty()) {
                data.set("locations." + L, null);

                file.save();
                
                continue;
            }

            String W = data.getString("locations." + L + ".world");
            String X = data.getString("locations." + L + ".x");
            String Y = data.getString("locations." + L + ".y");
            String Z = data.getString("locations." + L + ".z");

            //todo() add format for this?
            part = "&8[&6" + line + "&8]: " + "&c" + L + "&8, &c" + W + "&8, &c" + X + "&8, &c" + Y + "&8, &c" + Z;
            l.append(part);
            l.append("\n");
            line++;
            
        }

        msg = l.toString();
        line = line - 1;

        //todo() add list message
        player.sendMessage(prefix + "&6A list of all the locations.");
        player.sendMessage("&c[Locations Name]&8, &c[World]&8, &c[X]&8, &c[Y]&8, &c[Z]");
        player.sendMessage(msg);
        player.sendMessage("&3Number of locations: &6" + line);
    }
    
    public static void setLoc(CommandSender player, String name, String particle) {
        CustomFiles file = CustomFiles.data;
        YamlFile data = file.getYamlFile();

        if (BPFountains.getFromName(particle) == null && BPParticles.getFromName(particle) == null && particleManager.getCustomFountain(particle) == null) {
            Messages.not_a_particle.sendMessage(player, new HashMap<>() {{
                put("{particle}", particle);
            }});
            
            return;
        }

        if (data.contains("locations")) {
            for (String loc : data.getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    data.set("locations." + loc + ".particle", particle);
                    file.save();
                    
                    kill();
                    startParticles();

                    Messages.location_set.sendMessage(player, new HashMap<>() {{
                        put("{name}", name);
                        put("{particle}", particle);
                    }});
                    
                    return;
                }
            }
        }

        Messages.location_does_not_exist.sendMessage(player, new HashMap<>() {{
            put("{name}", name);
        }});
    }

    public static ItemStack getPlayerHead(final String playerName) {
        return new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer(playerName).getStack();
    }
}