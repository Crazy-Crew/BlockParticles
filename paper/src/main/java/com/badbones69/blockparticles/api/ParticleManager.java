package com.badbones69.blockparticles.api;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.enums.CustomFiles;
import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.api.objects.ParticleLocation;
import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.CategoryKeys;
import com.badbones69.blockparticles.controllers.ParticleControl;
import com.ryderbelserion.vital.core.config.YamlFile;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleyaml.configuration.ConfigurationSection;
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

    private final List<ParticleLocation> locations = new ArrayList<>();
    
    public void load() {
        if (!this.customFountains.isEmpty()) this.customFountains.clear();

        ConfigManager.getHeads().getProperty(CategoryKeys.heads).getHeads().forEach((name, values) -> this.customFountains.add(new CustomFountain(name, values)));

        YamlFile data = CustomFiles.data.getYamlFile();

        final ConfigurationSection section = data.getConfigurationSection("locations");

        if (section != null) {
            for (final String locationName : section.getKeys(false)) {
                try {
                    final String worldName = data.getString("locations." + locationName + ".world");

                    // If world name is not null, we should convert it to be an uuid.
                    if (worldName != null) {
                        final World world = this.plugin.getServer().getWorld(worldName);

                        if (world != null) {
                            UUID uuid = world.getUID();

                            // Set new value.
                            data.set("locations." + locationName + ".uuid", uuid);

                            // Remove old value.
                            data.remove("locations." + locationName + ".world");
                        }
                    }

                    final UUID uuid = UUID.fromString(data.getString("locations." + locationName + ".uuid"));

                    final World world = this.plugin.getServer().getWorld(uuid);

                    if (world == null) return;

                    final int x = data.getInt("locations." + locationName + ".X");
                    final int y = data.getInt("locations." + locationName + ".Y");
                    final int z = data.getInt("locations." + locationName + ".Z");

                    final Location location = new Location(world, x, y, z);

                    addLocation(locationName, data.getString("locations." + locationName + ".particle"), location);
                } catch (Exception ignored) {}
            }
        }
    }

    public void addLocation(String name, String particle, Location location) {
        this.locations.add(new ParticleLocation(name, particle, location));
    }

    public final List<ParticleLocation> getLocations() {
        return this.locations;
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
    
    public final List<Entity> getFountainItem() {
        return this.fountainItems;
    }
    
    public void addFountainItem(final Entity item) {
        this.fountainItems.add(item);
    }
    
    public void removeFountainItem(final Entity item) {
        this.fountainItems.remove(item);
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