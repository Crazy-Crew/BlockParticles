package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.api.enums.fountains.BPFountains;
import com.badbones69.blockparticles.api.enums.particles.BPParticles;
import com.badbones69.blockparticles.api.enums.CustomFiles;
import com.badbones69.blockparticles.api.enums.particles.ParticleData;
import com.badbones69.blockparticles.api.objects.ParticleLocation;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.controllers.ParticleControl;
import com.ryderbelserion.vital.core.config.YamlFile;
import com.ryderbelserion.vital.paper.builders.items.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.List;

public class Methods implements Listener {

    private static final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private static final ParticleManager particleManager = plugin.getParticleManager();
    
    public static List<ParticleLocation> getLocations() {
        return particleManager.getLocations();
    }
    
    public static void kill() {
        plugin.getParticleControl().getLocations().clear();

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
        for (ParticleLocation key : getLocations()) {
            String id = key.getID();
            Location loc = key.getLocation();

            BPFountains fountains = BPFountains.getFromName(key.getParticle());

            if (fountains != null) {
                switch (fountains) {
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
                        Fountains.startCustomFountain(loc, id, key.getParticle());
                        break;
                }
            }

            BPParticles particles = BPParticles.getFromName(key.getParticle());

            ParticleControl particleControl = plugin.getParticleControl();

            if (particles != null) {
                switch (particles) {
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
                        particleControl.playDoubleSpiral(loc, id, ParticleData.DOUBLEWITCH, 5);
                        break;
                    case WITCH:
                        particleControl.playSpiral(loc, id, ParticleData.WITCH, 5);
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
                        particleControl.playSpiral(loc, id, ParticleData.SPIRAL, 1);
                        break;
                    case DOUBLESPIRAL:
                        particleControl.playDoubleSpiral(loc, id, ParticleData.DOUBLESPIRAL, 5);
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
        
        final Block block = player.getTargetBlock(null, 5);
        
        if (block.isEmpty()) {
            Messages.not_looking_at_block.sendMessage(player);
            
            return;
        }
        
        final Location location = block.getLocation();
        final String uuid = String.valueOf(location.getWorld().getUID());
        final int x = location.getBlockX();
        final int y = location.getBlockY();
        final int z = location.getBlockZ();

        data.set("locations." + name + ".uuid", uuid);
        data.set("locations." + name + ".x", x);
        data.set("locations." + name + ".y", y);
        data.set("locations." + name + ".z", z);
        data.set("locations." + name + ".particle", "Spiral");
        
        file.save();

        particleManager.addLocation(name, "Spiral", location);
        
        kill();
        startParticles();

        Messages.location_added.sendMessage(player, new HashMap<>() {{
            put("{name}", name);
        }});
    }
    
    public static void delLoc(CommandSender player, String name) {
        CustomFiles file = CustomFiles.data;
        YamlFile data = file.getYamlFile();

        ParticleControl particleControl = plugin.getParticleControl();

        if (data.contains("locations")) {
            for (String loc : data.getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    data.set("locations." + loc, null);

                    file.save();

                    particleControl.removeParticle(name);

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