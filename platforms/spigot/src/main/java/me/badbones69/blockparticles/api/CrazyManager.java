package me.badbones69.blockparticles.api;

import me.badbones69.blockparticles.api.FileManager.Files;
import me.badbones69.blockparticles.api.controllers.ParticleControl;
import me.badbones69.blockparticles.api.enums.types.ParticleType;
import me.badbones69.blockparticles.api.enums.types.Particles;
import me.badbones69.blockparticles.api.objects.CustomFountain;
import me.badbones69.blockparticles.api.objects.Particle;
import me.badbones69.blockparticles.controllers.Fountains;
import me.badbones69.blockparticles.multisupport.ParticleTasks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CrazyManager {
    
    private static CrazyManager instance = new CrazyManager();
    private FileManager fileManager = FileManager.getInstance();
    private Plugin plugin;
    private List<Entity> fountainItems = new ArrayList<>();
    private List<CustomFountain> customFountains = new ArrayList<>();
    private HashMap<Player, String> setCommandPlayers = new HashMap<>();
    private ParticleControl particleControl;
    
    public static CrazyManager getInstance() {
        return instance;
    }
    
    public void load() {
        particleControl = new ParticleTasks();
        customFountains.clear();
        if (hasOldFiles()) {
            String prefix = fileManager.getPrefix();
            boolean isLogging = fileManager.isLogging();
            if (isLogging) System.out.println(prefix + "Old files have been detected!");
            if (isLogging) System.out.println(prefix + "Converting old files.");
            convertOldFiles();
            if (isLogging) System.out.println(prefix + "Finished converting old files.");
        }

        FileConfiguration config = Files.CONFIG.getFile();

        if (config.contains("settings.heads")) {
            for (String customFountain : config.getConfigurationSection("settings.heads").getKeys(false)) {
                customFountains.add(new CustomFountain(customFountain, config.getStringList("settings.heads." + customFountain)));
            }
        }
    }
    
    public Plugin getPlugin() {
        if (plugin == null) plugin = Bukkit.getServer().getPluginManager().getPlugin("BlockParticles");

        return plugin;
    }
    
    public ParticleControl getParticleControl() {
        return particleControl;
    }
    
    public List<CustomFountain> getCustomFountains() {
        return customFountains;
    }
    
    public CustomFountain getCustomFountain(String name) {
        for (CustomFountain fountain : customFountains) {
            if (fountain.getName().equalsIgnoreCase(name)) return fountain;
            }

        return null;
    }
    
    public boolean hasOldFiles() {
        return Files.CONFIG.getFile().contains("Settings") || Files.DATA.getFile().contains("Locations");
    }
    
    public void convertOldFiles() {
        String prefix = fileManager.getPrefix();
        boolean isLogging = fileManager.isLogging();
        FileConfiguration config = Files.CONFIG.getFile();

        if (config.contains("Settings")) {
            config.set("settings.prefix", config.getString("Settings.Prefix"));
            config.set("Settings", null);
            Files.CONFIG.saveFile();
            if (isLogging) System.out.println(prefix + "Finished converting config.yml.");
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
            if (isLogging) System.out.println(prefix + "Finished converting data.yml.");
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
        return fountainItems;
    }
    
    public void addFountainItem(Entity item) {
        fountainItems.add(item);
    }
    
    public void removeFountainItem(Entity item) {
        fountainItems.remove(item);
    }
    
    /**
     * Set a Particle to a specified Location;
     *
     * @param type The Particle you wish to use.
     * @param loc  The location you wish to spawn the Particles.
     * @param name The Location Name.
     */
    public void setParticle(Particles type, Location loc, String name) {
        if (particleControl.getLocations().containsKey(name)) {
            Bukkit.getServer().getScheduler().cancelTask(particleControl.getLocations().get(name));
        }

        switch (type) {
            case LOVETORNADO -> particleControl.playLoveTornado(loc, name);
            case WITCHTORNADO -> particleControl.playWitchTornado(loc, name);
            case LOVEWELL -> particleControl.playLoveWell(loc, name);
            case BIGLOVEWELL -> particleControl.playBigLoveWell(loc, name);
            case HALO -> particleControl.playHalo(loc, name);
            case SANTAHAT -> particleControl.playSantaHat(loc, name);
            case SOULWELL -> particleControl.playSoulWell(loc, name);
            case BIGSOULWELL -> particleControl.playBigSoulWell(loc, name);
            case FLAMEWHEEL -> particleControl.playFlameWheel(loc, name);
            case MARIO -> Fountains.startMario(loc, name);
            case POKEMON -> Fountains.startPokemon(loc, name);
            case FOOD -> Fountains.startFood(loc, name);
            case MOBS -> Fountains.startMobs(loc, name);
            case SNOWBLAST -> particleControl.playSnowBlast(loc, name);
            case RAINBOW -> particleControl.playRainbow(loc, name);
            case ENDERSIGNAL -> particleControl.playEnderSignal(loc, name);
            case MOBSPAWNER -> particleControl.playMobSpawner(loc, name);
            case ANGRYVILLAGER -> particleControl.playAngryVillager(loc, name);
            case HAPPYVILLAGER -> particleControl.playHappyVillager(loc, name);
            case FOOTPRINT -> particleControl.playFootPrint(loc, name);
            case FIRESPEW -> particleControl.playFireSpew(loc, name);
            case SPEW -> particleControl.playSpew(loc, name);
            case STORM -> particleControl.playStorm(loc, name);
            case SNOWSTORM -> particleControl.playSnowStorm(loc, name);
            case FIRESTORM -> particleControl.playFireStorm(loc, name);
            case WITCH -> particleControl.playSpiral(loc, name, Particles.WITCH, 5);
            case DOUBLEWITCH -> particleControl.playDoubleSpiral(loc, name, Particles.DOUBLEWITCH, 5);
            case MAGIC -> particleControl.playMagic(loc, name);
            case PRESENTS -> Fountains.startPresents(loc, name);
            case MUSIC -> particleControl.playMusic(loc, name);
            case POTION -> particleControl.playPotion(loc, name);
            case SNOW -> particleControl.playSnow(loc, name);
            case WATER -> particleControl.startWater(loc, name);
            case CHAINS -> particleControl.playChains(loc, name);
            case ENCHANT -> particleControl.playEnchant(loc, name);
            case FOG -> particleControl.playFog(loc, name);
            case HEADS -> Fountains.startHeads(loc, name);
            case FLAME -> particleControl.playFlame(loc, name);
            case BIGFLAME -> particleControl.playBigFlame(loc, name);
            case HALLOWEEN -> Fountains.startHalloween(loc, name);
            case GEMS -> Fountains.startGems(loc, name);
            case VOLCANO -> particleControl.playVolcano(loc, name);
            case SPIRAL -> particleControl.playSpiral(loc, name, Particles.SPIRAL, 1);
            case DOUBLESPIRAL -> particleControl.playDoubleSpiral(loc, name, Particles.DOUBLESPIRAL, 1);
            case CRIT -> particleControl.playCrit(loc, name);
            case BIGCRIT -> particleControl.playBigCrit(loc, name);
        }
    }
    
    /**
     * Remove a Particle;
     *
     * @param name The Location Name.
     */
    public void removeParticle(String name) {
        if (particleControl.getLocations().containsKey(name)) {
            Bukkit.getServer().getScheduler().cancelTask(particleControl.getLocations().get(name));
            particleControl.getLocations().remove(name);
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
        setCommandPlayers.put(player, type);
    }
    
    public HashMap<Player, String> getSetCommandPlayers() {
        return setCommandPlayers;
    }
    
    public boolean useNewMaterial() {
        return true;
    }
}