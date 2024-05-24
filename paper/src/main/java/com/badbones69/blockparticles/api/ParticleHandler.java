package com.badbones69.blockparticles.api;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.enums.CustomFiles;
import com.badbones69.blockparticles.api.interfaces.IParticleHandler;
import com.badbones69.blockparticles.api.objects.particles.ParticleTypeLocation;
import com.ryderbelserion.vital.core.config.YamlFile;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleyaml.configuration.ConfigurationSection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParticleHandler implements IParticleHandler {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final Map<String, ParticleTypeLocation> particles = new HashMap<>();

    private final CustomFiles file = CustomFiles.data;

    private final YamlFile data = this.file.getYamlFile();

    @Override
    public void load(boolean isServerStart) {
        if (isServerStart) { // Only used for migration, That should only be handled on startup!
            // Get the configuration section.
            final ConfigurationSection section = this.data.getConfigurationSection("locations");

            // Check if it is not null.
            if (section != null) {
                // Start looping!
                for (final String id : section.getKeys(false)) {
                    boolean hasOldKey = section.contains(id + ".world");

                    // If it has the world section.
                    if (hasOldKey) {
                        // Get the world name.
                        String worldName = section.getString(id + ".world");

                        // Get the world object.
                        World world = this.plugin.getServer().getWorld(worldName);

                        // Check if world is not null.
                        if (world != null) {
                            // Get the old values.
                            int x = section.getInt(id + ".x");
                            int y = section.getInt(id + ".y");
                            int z = section.getInt(id + ".z");

                            // Convert it to the string format.
                            String asString = world.getUID() + "," + x + "," + y + "," + z;

                            // Set the new value.
                            section.set(id + ".key", asString);

                            // Remove the old values.
                            section.remove(id + ".world");
                            section.remove(id + ".x");
                            section.remove(id + ".y");
                            section.remove(id + ".z");
                        } else {
                            // If the world is null, we remove the data.
                            this.data.set("locations." + id, null);

                            // Save to file immediately.
                            this.file.save();
                        }
                    }
                }
            }
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void reload() {
        load(false);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void addParticleLocation(final String id, final String particle, final Location location, final int task) {
        final ParticleTypeLocation type = new ParticleTypeLocation(id, particle, location, task);

        this.particles.put(id, type);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void removeParticleLocation(final String id) {
        ParticleTypeLocation type = this.particles.remove(id);

        // Cancel the task if not null.
        if (type != null) {
            type.cancelTask();
        }

        // Only save to file if the id is in the file.
        if (this.data.contains("locations." + id)) {
            // Remove the old particle
            this.data.remove("locations." + id);

            // Save to file
            this.file.save();
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public boolean hasParticleLocation(final String id, final boolean readFile) {
        if (readFile) {
            return this.data.contains("locations." + id);
        }

        return this.particles.containsKey(id);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public boolean hasParticleLocation(final String id) {
        return hasParticleLocation(id, false);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void saveParticleLocations() {
        // Get the configuration section.
        final ConfigurationSection section = this.data.getConfigurationSection("locations");

        // Loop through locations section.
        for (String id : section.getKeys(false)) {
            // Check if id exists in the file.
            if (hasParticleLocation(id, true)) {
                ParticleTypeLocation type = this.particles.get(id);

                // "key" is the location data, We are simply updating the location!
                section.set(id + "key", type.asString());
                // Updating the particle type!
                section.set(id + "key", type.particle());

                // We return and continue the loop.
                return;
            }

            // We only loop through this after the above check. This means the id does not exist, so we are adding it.
            for (final ParticleTypeLocation key : this.particles.values()) {
                section.set(id + ".particle", key.particle());
                section.set(id + ".key", key.asString());

                // Save the file after each edit!
                this.file.save();
            }
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final Map<String, ParticleTypeLocation> getParticles() {
        return Collections.unmodifiableMap(this.particles);
    }
}