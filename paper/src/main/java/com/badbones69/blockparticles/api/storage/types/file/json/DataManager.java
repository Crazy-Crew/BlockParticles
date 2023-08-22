package com.badbones69.blockparticles.api.storage.types.file.json;

import com.badbones69.blockparticles.api.plugin.InternalPlugin;
import com.badbones69.blockparticles.api.plugin.registry.BlockParticlesProvider;
import com.badbones69.blockparticles.api.storage.interfaces.ParticleDataManager;
import com.badbones69.blockparticles.api.storage.objects.CustomLocation;
import com.badbones69.blockparticles.api.storage.objects.ParticleData;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public non-sealed class DataManager extends ParticleJson implements ParticleDataManager {

    private final @NotNull InternalPlugin plugin = BlockParticlesProvider.get();

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
        save();load();
    }

    @Override
    public void addParticleData(String name, Location location) {
        // Create atomic integer.
        AtomicInteger id = new AtomicInteger(1);

        // Create new particle data instance.
        ParticleData particleData = new ParticleData();

        // Check if the location for the particle name exists.
        if (!hasParticleData(name)) {
            // Create custom location.
            CustomLocation customLocation = new CustomLocation(
                    id.get(),
                    location.getWorld().getName(),
                    location.x(), location.y(), location.z());

            // Adds the particle location.
            particleData.addLocation(customLocation);

            // Adds the particle name and data.
            particles.put(name, particleData);

            // Reload the file and concurrent hashmap.
            reload();

            return;
        }

        // Create custom location.
        CustomLocation customLocation = new CustomLocation(
                id.get(),
                location.getWorld().getName(),
                location.x(), location.y(), location.z());

        ParticleData data = getParticleData(name);

        // This runs if we found that the concurrent hashmap has data.
        // If the locations aren't empty.
            /*if (!data.getLocations().isEmpty()) {
                // We loop through the locations.
                for (CustomLocation custom : data.getLocations()) {
                    // Check if an object matches an object above.
                    if (custom.equals(customLocation)) {
                        // Set the atomic integer id to the one we have in the file.
                        id.set(customLocation.id());
                        // Break since we found it.
                        break;
                    }
                }
            }*/

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
    public void removeParticleData(String name, int id) {
        //ParticleData particleData = getParticleData(name);

        //CustomLocation customLocation = null;

        /*if (!particleData.getLocations().isEmpty()) {
            for (CustomLocation location : particleData.getLocations()) {
                if (location.id() == id) {
                    customLocation = location;
                    break;
                }
            }
        }*/

        //if (customLocation != null) particleData.removeLocation(customLocation);
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