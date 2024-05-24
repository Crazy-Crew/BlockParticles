package com.badbones69.blockparticles.api;

import ch.jalu.configme.SettingsManager;
import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.Particles;
import com.badbones69.blockparticles.api.enums.CustomFiles;
import com.badbones69.blockparticles.api.enums.particles.ParticleType;
import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.CategoryKeys;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.controllers.ParticleControl;
import com.ryderbelserion.vital.core.config.YamlFile;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ParticleManager {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final Map<UUID, String> setCommandPlayers = new HashMap<>();
    private final List<CustomFountain> customFountains = new ArrayList<>();
    private final List<Entity> fountainItems = new ArrayList<>();

    private ParticleControl particleControl;
    
    public void load() {
        if (this.particleControl != null) this.particleControl = new Particles();

        if (!this.customFountains.isEmpty()) this.customFountains.clear();

        ConfigManager.getHeads().getProperty(CategoryKeys.heads).getHeads().forEach((name, values) -> this.customFountains.add(new CustomFountain(name, values)));
    }
    
    public final ParticleControl getParticleControl() {
        return this.particleControl;
    }
    
    public final List<CustomFountain> getCustomFountains() {
        return this.customFountains;
    }
    
    public final CustomFountain getCustomFountain(final String name) {
        for (final CustomFountain fountain : this.customFountains) {
            if (fountain.getName().equalsIgnoreCase(name)) {
                return fountain;
            }
        }

        return null;
    }
    
    public boolean hasParticle(Location loc) {
        YamlFile data = CustomFiles.data.getYamlFile();

        if (data.contains("locations")) {
            for (String id : data.getConfigurationSection("locations").getKeys(false)) {
                World world = this.plugin.getServer().getWorld(data.getString("locations." + id + ".World"));

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
    
    public final List<Entity> getFountainItem() {
        return this.fountainItems;
    }
    
    public void addFountainItem(final Entity item) {
        this.fountainItems.add(item);
    }
    
    public void removeFountainItem(final Entity item) {
        this.fountainItems.remove(item);
    }
    
    /**
     * Set a Particle to a specified Location;
     *
     * @param type The Particle you wish to use.
     * @param loc  The location you wish to spawn the Particles.
     * @param name The Location Name.
     */
    public void setParticle(final com.badbones69.blockparticles.api.enums.particles.Particles type, final Location loc, final String name) {
        if (this.particleControl.getLocations().containsKey(name)) {
            this.plugin.getServer().getScheduler().cancelTask(particleControl.getLocations().get(name));
        }

        switch (type) {
            case LOVETORNADO:
                this.particleControl.playLoveTornado(loc, name);
                break;
            case WITCHTORNADO:
                this.particleControl.playWitchTornado(loc, name);
                break;
            case LOVEWELL:
                this.particleControl.playLoveWell(loc, name);
                break;
            case BIGLOVEWELL:
                this.particleControl.playBigLoveWell(loc, name);
                break;
            case HALO:
                this.particleControl.playHalo(loc, name);
                break;
            case SANTAHAT:
                this.particleControl.playSantaHat(loc, name);
                break;
            case SOULWELL:
                this.particleControl.playSoulWell(loc, name);
                break;
            case BIGSOULWELL:
                this.particleControl.playBigSoulWell(loc, name);
                break;
            case FLAMEWHEEL:
                this.particleControl.playFlameWheel(loc, name);
                break;
            case MARIO:
                Fountains.startMario(loc, name);
                break;
            case POKEMON:
                Fountains.startPokemon(loc, name);
                break;
            case FOOD:
                Fountains.startFood(loc, name);
                break;
            case MOBS:
                Fountains.startMobs(loc, name);
                break;
            case SNOWBLAST:
                this.particleControl.playSnowBlast(loc, name);
                break;
            case RAINBOW:
                this.particleControl.playRainbow(loc, name);
                break;
            case ENDERSIGNAL:
                this.particleControl.playEnderSignal(loc, name);
                break;
            case MOBSPAWNER:
                this.particleControl.playMobSpawner(loc, name);
                break;
            case ANGRYVILLAGER:
                this.particleControl.playAngryVillager(loc, name);
                break;
            case HAPPYVILLAGER:
                this.particleControl.playHappyVillager(loc, name);
                break;
            case FOOTPRINT:
                this.particleControl.playFootPrint(loc, name);
                break;
            case FIRESPEW:
                this.particleControl.playFireSpew(loc, name);
                break;
            case SPEW:
                this.particleControl.playSpew(loc, name);
                break;
            case STORM:
                this.particleControl.playStorm(loc, name);
                break;
            case SNOWSTORM:
                this.particleControl.playSnowStorm(loc, name);
                break;
            case FIRESTORM:
                this.particleControl.playFireStorm(loc, name);
                break;
            case WITCH:
                this.particleControl.playSpiral(loc, name, com.badbones69.blockparticles.api.enums.particles.Particles.WITCH, 5);
                break;
            case DOUBLEWITCH:
                this.particleControl.playDoubleSpiral(loc, name, com.badbones69.blockparticles.api.enums.particles.Particles.DOUBLEWITCH, 5);
                break;
            case MAGIC:
                this.particleControl.playMagic(loc, name);
                break;
            case PRESENTS:
                Fountains.startPresents(loc, name);
                break;
            case MUSIC:
                this.particleControl.playMusic(loc, name);
                break;
            case POTION:
                this.particleControl.playPotion(loc, name);
                break;
            case SNOW:
                this.particleControl.playSnow(loc, name);
                break;
            case WATER:
                this.particleControl.startWater(loc, name);
                break;
            case CHAINS:
                this.particleControl.playChains(loc, name);
                break;
            case ENCHANT:
                this.particleControl.playEnchant(loc, name);
                break;
            case FOG:
                this.particleControl.playFog(loc, name);
                break;
            case HEADS:
                Fountains.startHeads(loc, name);
                break;
            case FLAME:
                this.particleControl.playFlame(loc, name);
                break;
            case BIGFLAME:
                this.particleControl.playBigFlame(loc, name);
                break;
            case HALLOWEEN:
                Fountains.startHalloween(loc, name);
                break;
            case GEMS:
                Fountains.startGems(loc, name);
                break;
            case VOLCANO:
                this.particleControl.playVolcano(loc, name);
                break;
            case SPIRAL:
                this.particleControl.playSpiral(loc, name, com.badbones69.blockparticles.api.enums.particles.Particles.SPIRAL, 1);
                break;
            case DOUBLESPIRAL:
                this.particleControl.playDoubleSpiral(loc, name, com.badbones69.blockparticles.api.enums.particles.Particles.DOUBLESPIRAL, 1);
                break;
            case CRIT:
                this.particleControl.playCrit(loc, name);
                break;
            case BIGCRIT:
                this.particleControl.playBigCrit(loc, name);
                break;
        }
    }
    
    /**
     * Remove a Particle;
     *
     * @param name The Location Name.
     */
    public void removeParticle(final String name) {
        if (this.particleControl.getLocations().containsKey(name)) {
            this.plugin.getServer().getScheduler().cancelTask(this.particleControl.getLocations().get(name));

            this.particleControl.getLocations().remove(name);
        }
    }
    
    /**
     * Get the Particle type of Particle (Particle/Fountain).
     *
     * @param particle The Particle you want to get the ParticleType from.
     * @return A Particle Type.
     */
    public final ParticleType getType(final com.badbones69.blockparticles.api.enums.particles.Particles particle) {
        return particle.getType();
    }
    
    public void addSetCommandPlayer(final Player player, final String type) {
        this.setCommandPlayers.put(player.getUniqueId(), type);
    }

    public String getLocation(final Player player) {
        return this.setCommandPlayers.get(player.getUniqueId());
    }

    public boolean containsPlayer(final Player player) {
        return this.setCommandPlayers.containsKey(player.getUniqueId());
    }
    
    public final Map<UUID, String> getSetCommandPlayers() {
        return Collections.unmodifiableMap(this.setCommandPlayers);
    }
}