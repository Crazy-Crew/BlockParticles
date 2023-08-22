package com.badbones69.blockparticles.api.storage.types.file.json;

import com.badbones69.blockparticles.api.plugin.BlockParticlesPlugin;
import com.badbones69.blockparticles.api.plugin.registry.BlockParticlesProvider;
import com.badbones69.blockparticles.api.storage.interfaces.ParticleDataManager;
import com.badbones69.blockparticles.api.storage.objects.CustomLocation;
import com.badbones69.blockparticles.api.storage.objects.ParticleData;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import java.nio.file.Path;

public non-sealed class DataManager extends ParticleJson implements ParticleDataManager {

    private final @NotNull BlockParticlesPlugin plugin = BlockParticlesProvider.get();

    public DataManager(Path path) {
        super(path);
    }

    @Override
    public void load() {
        this.plugin.getFileManager().addFile(new ParticleJson(this.plugin.getPlugin().getDataFolder().toPath()));
    }

    @Override
    public void save() {
        this.plugin.getFileManager().saveFile(new ParticleJson(this.plugin.getPlugin().getDataFolder().toPath()));
    }

    @Override
    public void reload() {
        save();
        load();
    }

    @Override
    public void addParticleData(String name, Location location) {
        // Create new particle data instance.
        ParticleData particleData = new ParticleData();

        // Create custom location.
        CustomLocation customLocation = new CustomLocation(
                location.getWorld().getName(),
                location.x(), location.y(), location.z());

        // Check if the location for the particle name exists.
        if (!hasParticleData(name)) {

            // Adds the particle location.
            particleData.addLocation(customLocation);

            // Adds the particle name and data.
            particles.put(name, particleData);

            // Reload the file and concurrent hashmap.
            reload();

            return;
        }

        ParticleData data = getParticleData(name);

        // Check if we already have stored the object.
        if (data.hasLocation(customLocation)) {
            //TODO() Add a proper message for the sender.
            this.plugin.getFancyLogger().debug("This location is already in the map.");
            // Return since we don't want to double up.
            return;
        }

        // Add the new location.
        data.addLocation(customLocation);

        // Reload the contents.
        reload();
    }

    @Override
    public ParticleData getParticleData(String name) {
        if (hasParticleData(name)) return particles.get(name);

        return null;
    }

    @Override
    public void removeParticleData(String name, Location location) {
        ParticleData particleData = getParticleData(name);

        CustomLocation customLocation = new CustomLocation(
                location.getWorld().getName(),
                location.x(),
                location.y(),
                location.z()
        );

        this.plugin.getFancyLogger().debug("Particle Remove Before: " + particleData.getLocations().size());

        if (particleData.hasLocation(customLocation)) {
            particleData.removeLocation(customLocation);

            reload();
        }

        this.plugin.getFancyLogger().debug("Particle Remove After: " + particleData.getLocations().size());
    }

    @Override
    public boolean hasParticleData(String name) {
        return particles.containsKey(name);
    }

    @Override
    public void purge() {
        save();

        particles.clear();
    }
}