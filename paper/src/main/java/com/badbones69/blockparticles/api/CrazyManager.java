package com.badbones69.blockparticles.api;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.FileManager.Files;
import com.badbones69.blockparticles.api.controllers.ParticleControl;
import com.badbones69.blockparticles.api.enums.types.ParticleType;
import com.badbones69.blockparticles.api.enums.types.Particles;
import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.api.objects.Particle;
import com.badbones69.blockparticles.api.storage.StorageManager;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.controllers.ParticleTasks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CrazyManager {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private StorageManager storageManager;

    public void load(boolean serverStart) {
        if (serverStart) {
            this.storageManager = new StorageManager();
            this.storageManager.init();
        }

        this.storageManager.getParticleDataManager().load();
    }

    public void reload(boolean serverStop) {
        if (serverStop) {
            this.storageManager.getParticleDataManager().save();

            this.storageManager.getParticleDataManager().purge();

            return;
        }

        this.storageManager.getParticleDataManager().reload();
    }

    public StorageManager getStorageManager() {
        return this.storageManager;
    }

    /**
     * v1
     */
    private final Fountains fountains = this.plugin.getFountains();

    private final List<Entity> fountainItems = new ArrayList<>();
    private final List<CustomFountain> customFountains = new ArrayList<>();
    private final HashMap<Player, String> setCommandPlayers = new HashMap<>();
    private ParticleControl particleControl;

    public void load() {
        this.particleControl = new ParticleTasks();
        this.customFountains.clear();
        if (hasOldFiles()) convertOldFiles();

        FileConfiguration config = Files.CONFIG.getFile();

        if (config.contains("settings.heads")) {
            for (String customFountain : config.getConfigurationSection("settings.heads").getKeys(false)) {
                this.customFountains.add(new CustomFountain(customFountain, config.getStringList("settings.heads." + customFountain)));
            }
        }
    }
    
    public ParticleControl getParticleControl() {
        return this.particleControl;
    }
    
    public List<CustomFountain> getCustomFountains() {
        return this.customFountains;
    }
    
    public CustomFountain getCustomFountain(String name) {
        for (CustomFountain fountain : this.customFountains) {
            if (fountain.getName().equalsIgnoreCase(name)) return fountain;
            }

        return null;
    }
    
    public boolean hasOldFiles() {
        return Files.CONFIG.getFile().contains("Settings") || Files.DATA.getFile().contains("Locations");
    }
    
    public void convertOldFiles() {
        FileConfiguration config = Files.CONFIG.getFile();

        if (config.contains("Settings")) {
            config.set("settings.prefix", config.getString("Settings.Prefix"));
            config.set("Settings", null);
            Files.CONFIG.saveFile();
        }

        FileConfiguration data = Files.DATA.getFile();

        if (data.contains("Locations")) {
            List<Particle> particles = new ArrayList<>();
            for (String id : data.getConfigurationSection("Locations").getKeys(false)) {
                particles.add(new Particle(id,
                data.getString("Locations." + id + ".World"),
                data.getInt("Locations." + id + ".X"),
                data.getInt("Locations." + id + ".Y"),
                data.getInt("Locations." + id + ".Z"),
                data.getString("Locations." + id + ".Particle")));
            }

            data.set("Locations", null);
            for (Particle particle : particles) {
                String id = particle.getID();
                data.set("locations." + id + ".world", particle.getWorld());
                data.set("locations." + id + ".x", particle.getX());
                data.set("locations." + id + ".y", particle.getY());
                data.set("locations." + id + ".z", particle.getZ());
                data.set("locations." + id + ".particle", particle.getParticleTypeName());
            }

            Files.DATA.saveFile();
        }
    }
    
    public boolean hasParticle(Location loc) {
        FileConfiguration data = Files.DATA.getFile();
        if (data.contains("locations")) {
            for (String id : data.getConfigurationSection("locations").getKeys(false)) {
                World world = Bukkit.getServer().getWorld(data.getString("locations." + id + ".World"));
                int X = data.getInt("locations." + id + ".X");
                int Y = data.getInt("locations." + id + ".Y");
                int Z = data.getInt("locations." + id + ".Z");
                Location l = new Location(world, X, Y, Z);
                if (l.equals(loc)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public List<Entity> getFountainItem() {
        return this.fountainItems;
    }
    
    public void addFountainItem(Entity item) {
        this.fountainItems.add(item);
    }
    
    public void removeFountainItem(Entity item) {
        this.fountainItems.remove(item);
    }
    
    /**
     * Set a Particle to a specified Location;
     *
     * @param type The Particle you wish to use.
     * @param loc  The location you wish to spawn the Particles.
     * @param name The Location Name.
     */
    public void setParticle(Particles type, Location loc, String name) {
        if (this.particleControl.getLocations().containsKey(name)) {
            this.plugin.getServer().getScheduler().cancelTask(this.particleControl.getLocations().get(name));
        }

        switch (type) {
            case LOVE_TORNADO -> this.particleControl.playLoveTornado(loc, name);
            case WITCH_TORNADO -> this.particleControl.playWitchTornado(loc, name);
            case LOVE_WELL -> this.particleControl.playLoveWell(loc, name);
            case BIG_LOVE_WELL -> this.particleControl.playBigLoveWell(loc, name);
            case HALO -> this.particleControl.playHalo(loc, name);
            case SANTA_HAT -> this.particleControl.playSantaHat(loc, name);
            case SOUL_WELL -> this.particleControl.playSoulWell(loc, name);
            case BIG_SOUL_WELL -> this.particleControl.playBigSoulWell(loc, name);
            case FLAME_WHEEL -> this.particleControl.playFlameWheel(loc, name);
            case MARIO -> this.fountains.startMario(loc, name);
            case POKEMON -> this.fountains.startPokemon(loc, name);
            case FOOD -> this.fountains.startFood(loc, name);
            case MOBS -> this.fountains.startMobs(loc, name);
            case SNOWBLAST -> this.particleControl.playSnowBlast(loc, name);
            case RAINBOW -> this.particleControl.playRainbow(loc, name);
            case ENDER_SIGNAL -> this.particleControl.playEnderSignal(loc, name);
            case MOB_SPAWNER -> this.particleControl.playMobSpawner(loc, name);
            case ANGRY_VILLAGER -> this.particleControl.playAngryVillager(loc, name);
            case HAPPY_VILLAGER -> this.particleControl.playHappyVillager(loc, name);
            case FOOTPRINT -> this.particleControl.playFootPrint(loc, name);
            case FIRE_SPEW -> this.particleControl.playFireSpew(loc, name);
            case SPEW -> this.particleControl.playSpew(loc, name);
            case STORM -> this.particleControl.playStorm(loc, name);
            case SNOW_STORM -> this.particleControl.playSnowStorm(loc, name);
            case FIRE_STORM -> this.particleControl.playFireStorm(loc, name);
            case WITCH -> this.particleControl.playSpiral(loc, name, Particles.WITCH, 5);
            case DOUBLE_WITCH -> this.particleControl.playDoubleSpiral(loc, name, Particles.DOUBLE_WITCH, 5);
            case MAGIC -> this.particleControl.playMagic(loc, name);
            case PRESENTS -> this.fountains.startPresents(loc, name);
            case MUSIC -> this.particleControl.playMusic(loc, name);
            case POTION -> this.particleControl.playPotion(loc, name);
            case SNOW -> this.particleControl.playSnow(loc, name);
            case WATER -> this.particleControl.startWater(loc, name);
            case CHAINS -> this.particleControl.playChains(loc, name);
            case ENCHANT -> this.particleControl.playEnchant(loc, name);
            case FOG -> this.particleControl.playFog(loc, name);
            case HEADS -> this.fountains.startHeads(loc, name);
            case FLAME -> this.particleControl.playFlame(loc, name);
            case BIG_FLAME -> this.particleControl.playBigFlame(loc, name);
            case HALLOWEEN -> this.fountains.startHalloween(loc, name);
            case GEMS -> this.fountains.startGems(loc, name);
            case VOLCANO -> this.particleControl.playVolcano(loc, name);
            case SPIRAL -> this.particleControl.playSpiral(loc, name, Particles.SPIRAL, 1);
            case DOUBLE_SPIRAL -> this.particleControl.playDoubleSpiral(loc, name, Particles.DOUBLE_SPIRAL, 1);
            case CRIT -> this.particleControl.playCrit(loc, name);
            case BIG_CRIT -> this.particleControl.playBigCrit(loc, name);
        }
    }
    
    /**
     * Remove a Particle;
     *
     * @param name The Location Name.
     */
    public void removeParticle(String name) {
        if (this.particleControl.getLocations().containsKey(name)) {
            this.plugin.getServer().getScheduler().cancelTask(this.particleControl.getLocations().get(name));
            this.particleControl.getLocations().remove(name);
        }
    }
    
    /**
     * Get the particle type of Particle (Particle/Fountain).
     *
     * @param particle The Particle you want to get the ParticleType from.
     * @return a Particle Type.
     */
    public ParticleType getType(Particles particle) {
        return particle.getType();
    }
    
    public void addSetCommandPlayer(Player player, String type) {
        this.setCommandPlayers.put(player, type);
    }
    
    public HashMap<Player, String> getSetCommandPlayers() {
        return this.setCommandPlayers;
    }
    
    public boolean useNewMaterial() {
        return true;
    }
}