package com.badbones69.blockparticles.api;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.Particles;
import com.badbones69.blockparticles.api.enums.Files;
import com.badbones69.blockparticles.api.enums.particles.CustomParticles;
import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.listeners.FountainListener;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import java.util.ArrayList;
import java.util.List;

public class ParticleManager {

    private final BlockParticles plugin = BlockParticles.getPlugin();

    private final List<Entity> fountainItems = new ArrayList<>();
    private final List<CustomFountain> customFountains = new ArrayList<>();
    private ParticleControl particleControl;

    
    public void load() {
        this.particleControl = new Particles();
        this.customFountains.clear();

        FileConfiguration config = Files.config.getConfiguration();

        final ConfigurationSection section = config.getConfigurationSection("settings.heads");

        if (section == null) return;

        for (String customFountain : section.getKeys(false)) {
            this.customFountains.add(new CustomFountain(customFountain, config.getStringList("settings.heads." + customFountain)));
        }
    }
    
    public ParticleControl getParticleControl() {
        return particleControl;
    }
    
    public List<CustomFountain> getCustomFountains() {
        return customFountains;
    }
    
    public CustomFountain getCustomFountain(String name) {
        for (CustomFountain fountain : customFountains) {
            if (fountain.getName().equalsIgnoreCase(name)) {
                return fountain;
            }
        }
        return null;
    }
    
    public boolean hasParticle(Location loc) {
        FileConfiguration data = Files.data.getConfiguration();

        final ConfigurationSection section = data.getConfigurationSection("locations");

        boolean hasLocation = false;

        if (section == null) {
            return hasLocation;
        }

        final Server server = this.plugin.getServer();

        for (String id : section.getKeys(false)) {
            final World world = server.getWorld(data.getString("locations." + id + ".World", "world"));

            if (world == null) {
                break;
            }

            final int x = data.getInt("locations." + id + ".X");
            final int y = data.getInt("locations." + id + ".Y");
            final int z = data.getInt("locations." + id + ".Z");

            Location location = new Location(world, x, y, z);

            if (location.toString().equals(loc.toString())) {
                hasLocation = true;
            }
        }

        return hasLocation;
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
    public void setParticle(CustomParticles type, Location loc, String name) {
        if (particleControl.getLocations().containsKey(name)) {
            particleControl.getLocations().get(name).cancel();
        }

        switch (type) {
            case LOVETORNADO:
                particleControl.playLoveTornado(loc, name);
                break;
            case WITCHTORNADO:
                particleControl.playWitchTornado(loc, name);
                break;
            case LOVEWELL:
                particleControl.playLoveWell(loc, name);
                break;
            case BIGLOVEWELL:
                particleControl.playBigLoveWell(loc, name);
                break;
            case HALO:
                particleControl.playHalo(loc, name);
                break;
            case SANTAHAT:
                particleControl.playSantaHat(loc, name);
                break;
            case SOULWELL:
                particleControl.playSoulWell(loc, name);
                break;
            case BIGSOULWELL:
                particleControl.playBigSoulWell(loc, name);
                break;
            case FLAMEWHEEL:
                particleControl.playFlameWheel(loc, name);
                break;
            case MARIO:
                FountainListener.startMario(loc, name);
                break;
            case POKEMON:
                FountainListener.startPokemon(loc, name);
                break;
            case FOOD:
                FountainListener.startFood(loc, name);
                break;
            case MOBS:
                FountainListener.startMobs(loc, name);
                break;
            case SNOWBLAST:
                particleControl.playSnowBlast(loc, name);
                break;
            case RAINBOW:
                particleControl.playRainbow(loc, name);
                break;
            case ENDERSIGNAL:
                particleControl.playEnderSignal(loc, name);
                break;
            case MOBSPAWNER:
                particleControl.playMobSpawner(loc, name);
                break;
            case ANGRYVILLAGER:
                particleControl.playAngryVillager(loc, name);
                break;
            case HAPPYVILLAGER:
                particleControl.playHappyVillager(loc, name);
                break;
            case FOOTPRINT:
                particleControl.playFootPrint(loc, name);
                break;
            case FIRESPEW:
                particleControl.playFireSpew(loc, name);
                break;
            case SPEW:
                particleControl.playSpew(loc, name);
                break;
            case STORM:
                particleControl.playStorm(loc, name);
                break;
            case SNOWSTORM:
                particleControl.playSnowStorm(loc, name);
                break;
            case FIRESTORM:
                particleControl.playFireStorm(loc, name);
                break;
            case WITCH:
                particleControl.playSpiral(loc, name, CustomParticles.WITCH, 5);
                break;
            case DOUBLEWITCH:
                particleControl.playDoubleSpiral(loc, name, CustomParticles.DOUBLEWITCH, 5);
                break;
            case MAGIC:
                particleControl.playMagic(loc, name);
                break;
            case PRESENTS:
                FountainListener.startPresents(loc, name);
                break;
            case MUSIC:
                particleControl.playMusic(loc, name);
                break;
            case POTION:
                particleControl.playPotion(loc, name);
                break;
            case SNOW:
                particleControl.playSnow(loc, name);
                break;
            case WATER:
                particleControl.startWater(loc, name);
                break;
            case CHAINS:
                particleControl.playChains(loc, name);
                break;
            case ENCHANT:
                particleControl.playEnchant(loc, name);
                break;
            case FOG:
                particleControl.playFog(loc, name);
                break;
            case HEADS:
                FountainListener.startHeads(loc, name);
                break;
            case FLAME:
                particleControl.playFlame(loc, name);
                break;
            case BIGFLAME:
                particleControl.playBigFlame(loc, name);
                break;
            case HALLOWEEN:
                FountainListener.startHalloween(loc, name);
                break;
            case GEMS:
                FountainListener.startGems(loc, name);
                break;
            case VOLCANO:
                particleControl.playVolcano(loc, name);
                break;
            case SPIRAL:
                particleControl.playSpiral(loc, name, CustomParticles.SPIRAL, 1);
                break;
            case DOUBLESPIRAL:
                particleControl.playDoubleSpiral(loc, name, CustomParticles.DOUBLESPIRAL, 1);
                break;
            case CRIT:
                particleControl.playCrit(loc, name);
                break;
            case BIGCRIT:
                particleControl.playBigCrit(loc, name);
                break;
        }
    }
    
    /**
     * Remove a Particle;
     *
     * @param name The Location Name.
     */
    public void removeParticle(String name) {
        if (this.particleControl.getLocations().containsKey(name)) {
            this.particleControl.getLocations().get(name).cancel();
            this.particleControl.getLocations().remove(name);
        }
    }
}