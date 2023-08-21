package com.badbones69.blockparticles.api.storage.json;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.storage.interfaces.ParticleDataManager;
import com.badbones69.blockparticles.api.storage.objects.CustomLocation;
import com.badbones69.blockparticles.api.storage.objects.ParticleData;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.nio.file.Path;

public non-sealed class JsonParticleDataManager extends JsonParticleData implements ParticleDataManager {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    public JsonParticleDataManager(Path path) {
        super(path);
    }

    @Override
    public void loadFile() {
        this.plugin.getHandler().addFile(new JsonParticleData(this.plugin.getDataFolder().toPath()));
    }

    @Override
    public void saveFile() {
        this.plugin.getHandler().saveFile(new JsonParticleData(this.plugin.getDataFolder().toPath()));
    }

    @Override
    public void reload() {
        saveFile();
        loadFile();
    }

    @Override
    public void addParticleData(String name, Location location) {
        int id = 1;

        ParticleData particleData = new ParticleData();

        // Check if the location for the particle name exists.
        if (!hasParticleData(name)) {
            // The
            this.locations.put(name, particleData);

            CustomLocation customLocation = new CustomLocation(
                    id,
                    location.getWorld().getName(),
                    location.x(), location.y(), location.z());

            particleData.addLocation(customLocation);

            saveFile();
        }
    }

    @Override
    public ParticleData getParticleData(String name) {
        if (hasParticleData(name)) return this.locations.get(name);

        return null;
    }

    @Override
    public void removeParticleData(String name, int id) {
        ParticleData particleData = getParticleData(name);

        CustomLocation customLocation = null;

        if (!particleData.getLocations().isEmpty()) {
            for (CustomLocation location : particleData.getLocations()) {
                if (location.id() == id) {
                    customLocation = location;
                    break;
                }
            }
        }

        if (customLocation != null) particleData.removeLocation(customLocation);
    }

    @Override
    public boolean hasParticleData(String name) {
        if (this.locations.isEmpty()) return false;

        return this.locations.containsKey(name);
    }

    @Override
    public void purge() {
        this.locations.clear();
    }
}