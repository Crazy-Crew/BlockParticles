package com.badbones69.blockparticles.api;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.CategoryKeys;
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
    
    public void load() {
        this.customFountains.clear();

        ConfigManager.getHeads().getProperty(CategoryKeys.heads).getHeads().forEach((name, values) -> this.customFountains.add(new CustomFountain(name, values)));
    }
    
    public final List<CustomFountain> getCustomFountains() {
        return this.customFountains;
    }
    
    public final CustomFountain getCustomFountain(final String name) {
        for (final CustomFountain fountain : this.customFountains) {
            if (fountain.getFountainName().equalsIgnoreCase(name)) {
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