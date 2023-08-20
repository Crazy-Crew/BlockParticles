package com.badbones69.blockparticles.persist;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.common.config.FileEngine;
import com.badbones69.common.config.types.FileType;
import com.google.gson.annotations.Expose;
import org.bukkit.plugin.java.JavaPlugin;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Locations extends FileEngine {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    public Locations(Path folder) {
        super("locations.json", folder, FileType.JSON);
    }

    @Expose
    private final HashMap<String, String> locations = new HashMap<>();

    @Override
    public void load() {
        this.plugin.getPaper().getFancyLogger().info("<red>Running extra code</red>");
    }

    @Override
    public void save() {
        this.plugin.getPaper().getFancyLogger().info("<green>Running extra code</green>");
    }

    public Map<String, String> getLocations() {
        return Collections.unmodifiableMap(this.locations);
    }

    public void addLocation(String name, String other) {
        this.locations.put(name, other);
    }
}