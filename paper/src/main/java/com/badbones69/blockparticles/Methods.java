package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.enums.fountains.BPFountains;
import com.badbones69.blockparticles.api.enums.fountains.BPParticles;
import com.badbones69.blockparticles.api.enums.Files;
import com.badbones69.blockparticles.api.enums.particles.CustomParticles;
import com.badbones69.blockparticles.listeners.FountainListener;
import com.badbones69.blockparticles.api.ParticleManager;
import com.ryderbelserion.vital.paper.api.builders.items.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Methods {

    private static final BlockParticles plugin = BlockParticles.getPlugin();

    private static final Server server = plugin.getServer();

    private static final ParticleManager particleManager = plugin.getParticleManager();
    
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static ArrayList<String> getLocations() {
        final FileConfiguration data = Files.data.getConfiguration();

        final ConfigurationSection section = data.getConfigurationSection("locations");

        if (section == null) return new ArrayList<>();

        return new ArrayList<>(section.getKeys(false));
    }
    
    public static void kill() {
        particleManager.getParticleControl().getLocations().clear();

        for (World w : server.getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (e instanceof Item item) {
                    if (particleManager.getFountainItem().contains(item)) {
                        item.remove();
                    }
                }
            }
        }

        particleManager.getFountainItem().clear();

        server.getGlobalRegionScheduler().cancelTasks(plugin);
    }
    
    public static void startParticles() {
        final FileConfiguration data = Files.data.getConfiguration();

        final ConfigurationSection section = data.getConfigurationSection("locations");

        if (section == null) return;

        for (final String id : section.getKeys(false)) {
            final World world = server.getWorld(data.getString("locations." + id + ".world", "world"));

            if (world == null) break;

            String particle = data.getString("locations." + id + ".particle");

            final Location loc = new Location(world, data.getInt("locations." + id + ".x"), data.getInt("locations." + id + ".y"), data.getInt("locations." + id + ".z"));

            final BPFountains fountain = BPFountains.getFromName(particle);

            if (fountain != null) {
                switch (fountain) {
                    case MARIO:
                        FountainListener.startMario(loc, id);
                        break;
                    case POKEMON:
                        FountainListener.startPokemon(loc, id);
                        break;
                    case FOOD:
                        FountainListener.startFood(loc, id);
                        break;
                    case MOBS:
                        FountainListener.startMobs(loc, id);
                        break;
                    case HALLOWEEN:
                        FountainListener.startHalloween(loc, id);
                        break;
                    case GEMS:
                        FountainListener.startGems(loc, id);
                        break;
                    case HEADS:
                        FountainListener.startHeads(loc, id);
                        break;
                    case PRESENTS:
                        FountainListener.startPresents(loc, id);
                        break;
                    case CUSTOM:
                        FountainListener.startCustomFountain(loc, id, particle);
                        break;
                }
            }

            final BPParticles particles = BPParticles.getFromName(particle);

            if (particles != null) {
                switch (particles) {
                    case LOVEWELL:
                        particleManager.getParticleControl().playLoveWell(loc, id);
                        break;
                    case BIGLOVEWELL:
                        particleManager.getParticleControl().playBigLoveWell(loc, id);
                        break;
                    case LOVETORNADO:
                        particleManager.getParticleControl().playLoveTornado(loc, id);
                        break;
                    case WITCHTORNADO:
                        particleManager.getParticleControl().playWitchTornado(loc, id);
                        break;
                    case FLAMEWHEEL:
                        particleManager.getParticleControl().playFlameWheel(loc, id);
                        break;
                    case SOULWELL:
                        particleManager.getParticleControl().playSoulWell(loc, id);
                        break;
                    case BIGSOULWELL:
                        particleManager.getParticleControl().playBigSoulWell(loc, id);
                        break;
                    case SANTAHAT:
                        particleManager.getParticleControl().playSantaHat(loc, id);
                        break;
                    case SNOWBLAST:
                        particleManager.getParticleControl().playSnowBlast(loc, id);
                        break;
                    case RAINBOW:
                        particleManager.getParticleControl().playRainbow(loc, id);
                        break;
                    case ENDERSIGNAL:
                        particleManager.getParticleControl().playEnderSignal(loc, id);
                        break;
                    case MOBSPAWNER:
                        particleManager.getParticleControl().playMobSpawner(loc, id);
                        break;
                    case ANGRYVILLAGER:
                        particleManager.getParticleControl().playAngryVillager(loc, id);
                        break;
                    case HAPPYVILLAGER:
                        particleManager.getParticleControl().playHappyVillager(loc, id);
                        break;
                    case FOOTPRINT:
                        particleManager.getParticleControl().playFootPrint(loc, id);
                        break;
                    case FIRESPEW:
                        particleManager.getParticleControl().playFireSpew(loc, id);
                        break;
                    case SNOWSTORM:
                        particleManager.getParticleControl().playSnowStorm(loc, id);
                        break;
                    case DOUBLEWITCH:
                        particleManager.getParticleControl().playDoubleSpiral(loc, id, CustomParticles.DOUBLEWITCH, 5);
                        break;
                    case WITCH:
                        particleManager.getParticleControl().playSpiral(loc, id, CustomParticles.WITCH, 5);
                        break;
                    case MAGIC:
                        particleManager.getParticleControl().playMagic(loc, id);
                        break;
                    case SPEW:
                        particleManager.getParticleControl().playSpew(loc, id);
                        break;
                    case HALO:
                        particleManager.getParticleControl().playHalo(loc, id);
                        break;
                    case MUSIC:
                        particleManager.getParticleControl().playMusic(loc, id);
                        break;
                    case POTION:
                        particleManager.getParticleControl().playPotion(loc, id);
                        break;
                    case SNOW:
                        particleManager.getParticleControl().playSnow(loc, id);
                        break;
                    case FIRESTORM:
                        particleManager.getParticleControl().playFireStorm(loc, id);
                        break;
                    case WATER:
                        particleManager.getParticleControl().startWater(loc, id);
                        break;
                    case CHAINS:
                        particleManager.getParticleControl().playChains(loc, id);
                        break;
                    case ENCHANT:
                        particleManager.getParticleControl().playEnchant(loc, id);
                        break;
                    case FOG:
                        particleManager.getParticleControl().playFog(loc, id);
                        break;
                    case STORM:
                        particleManager.getParticleControl().playStorm(loc, id);
                        break;
                    case BIGFLAME:
                        particleManager.getParticleControl().playBigFlame(loc, id);
                        break;
                    case FLAME:
                        particleManager.getParticleControl().playFlame(loc, id);
                        break;
                    case VOLCANO:
                        particleManager.getParticleControl().playVolcano(loc, id);
                        break;
                    case SPIRAL:
                        particleManager.getParticleControl().playSpiral(loc, id, CustomParticles.SPIRAL, 1);
                        break;
                    case DOUBLESPIRAL:
                        particleManager.getParticleControl().playDoubleSpiral(loc, id, CustomParticles.DOUBLESPIRAL, 5);
                        break;
                    case CRIT:
                        particleManager.getParticleControl().playCrit(loc, id);
                        break;
                    case BIGCRIT:
                        particleManager.getParticleControl().playBigCrit(loc, id);
                        break;
                }
            }
        }
    }
    
    public static void addLoc(Player player, String name, String particle) {
        final FileConfiguration configuration = Files.config.getConfiguration();

        final String prefix = configuration.getString("settings.prefix", "");

        final FileConfiguration data = Files.data.getConfiguration();

        final ConfigurationSection section = data.getConfigurationSection("locations");

        boolean alreadyExists = false;

        if (section != null) {
            for (final String loc : section.getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    alreadyExists = true;

                    break;
                }
            }
        }

        if (alreadyExists) {
            player.sendMessage(color(prefix + "&3That location name is taken, please remove it and replace it here."));

            return;
        }

        final Block block = player.getTargetBlock(null, 5);

        if (block.isEmpty()) {
            player.sendMessage(Methods.color(prefix + "&cYou are not looking at a block."));

            return;
        }

        Location location = block.getLocation();
        String world = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        data.set("locations." + name + ".world", world);
        data.set("locations." + name + ".x", x);
        data.set("locations." + name + ".y", y);
        data.set("locations." + name + ".z", z);
        data.set("locations." + name + ".particle", particle);

        Files.data.save();

        kill();

        startParticles();

        player.sendMessage(color(prefix + "&3You have added &6" + name + " &3to the block."));
    }
    
    public static void delLoc(CommandSender player, String name) {
        final FileConfiguration configuration = Files.config.getConfiguration();

        final String prefix = configuration.getString("settings.prefix", "");

        final FileConfiguration data = Files.data.getConfiguration();

        final ConfigurationSection section = data.getConfigurationSection("locations");

        if (section == null) {
            player.sendMessage(color(prefix + "&3There are no locations called &6" + name + "&3."));

            return;
        }

        boolean hasLocation = false;

        for (String loc : section.getKeys(false)) {
            if (loc.equalsIgnoreCase(name)) {
                data.set("locations." + loc, null);
                
                Files.data.save();

                particleManager.getParticleControl().getLocations().get(loc).cancel();

                player.sendMessage(color(prefix + "&3You have deleted &6" + name + "&3."));

                hasLocation = true;

                break;
            }
        }

        if (!hasLocation) {
            player.sendMessage(color(prefix + "&3There are no locations called &6" + name + "&3."));
        }
    }
    
    public static void listLoc(Player player) {
        final FileConfiguration configuration = Files.config.getConfiguration();

        final String prefix = configuration.getString("settings.prefix", "");

        final FileConfiguration data = Files.data.getConfiguration();

        final ConfigurationSection section = data.getConfigurationSection("locations");

        if (section == null) {
            player.sendMessage(Methods.color(prefix + "&cThere are no locations set!"));

            return;
        }

        String msg;
        String part;
        StringBuilder l = new StringBuilder();
        int line = 1;

        for (String id : section.getKeys(false)) {
            String world = data.getString("locations." + id + ".world");
            String x = data.getString("locations." + id + ".x");
            String y = data.getString("locations." + id + ".y");
            String z = data.getString("locations." + id + ".z");
            
            part = Methods.color("&8[&6" + line + "&8]: " + "&c" + id + "&8, &c" + world + "&8, &c" + x + "&8, &c" + y + "&8, &c" + z);

            l.append(part);
            l.append("\n");

            line++;
        }

        msg = l.toString();
        line = line - 1;

        player.sendMessage(Methods.color(prefix + "&6A list of all the locations."));
        player.sendMessage(Methods.color("&c[Locations Name]&8, &c[World]&8, &c[X]&8, &c[Y]&8, &c[Z]"));
        player.sendMessage(msg);
        player.sendMessage(Methods.color("&3Number of locations: &6" + line));
    }
    
    public static void setLoc(Player player, String name, String particle) {
        final FileConfiguration configuration = Files.config.getConfiguration();

        final String prefix = configuration.getString("settings.prefix", "");

        final FileConfiguration data = Files.data.getConfiguration();

        if (BPFountains.getFromName(particle) == null && BPParticles.getFromName(particle) == null && particleManager.getCustomFountain(particle) == null) {
            player.sendMessage(color(prefix + "&6" + particle + " &cis not a particle. Please do /bp help for more information."));

            return;
        }

        final ConfigurationSection section = data.getConfigurationSection("locations");

        boolean hasLocation = false;

        if (section != null) {
            for (String loc : section.getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    data.set("locations." + loc + ".particle", particle);

                    Files.data.save();

                    kill();

                    startParticles();

                    player.sendMessage(color(prefix + "&3You have just set &6" + name + "'s &3particle to &6" + particle + "&3."));

                    hasLocation = true;

                    break;
                }
            }
        }

        if (!hasLocation) player.sendMessage(color(prefix + "&3There are no locations called &6" + name + "&3."));
    }
}