package com.badbones69.blockparticles.tasks;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.Server;
import com.badbones69.blockparticles.api.enums.CustomFiles;
import com.badbones69.blockparticles.api.interfaces.IParticleHandler;
import com.badbones69.blockparticles.tasks.api.objects.BlockParticle;
import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import com.badbones69.blockparticles.tasks.particles.ParticleConfig;
import com.ryderbelserion.vital.core.config.YamlFile;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.exceptions.InvalidConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

public class ParticleLoader implements IParticleHandler {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);
    private final Server server = this.plugin.getPaperServer();

    private final CustomFiles file = CustomFiles.data;
    private final YamlFile data = this.file.getYamlFile();

    private final Map<String, BlockParticle> particles = new HashMap<>();

    private final Set<BlockParticle> activeParticles = new HashSet<>();

    /**
     {@inheritDoc}
     */
    @Override
    public void load(boolean isServerStart) {
        this.particles.clear();

        for (File file : this.server.getParticleFiles()) {
            ParticleConfig config = new ParticleConfig(file);

            try {
                config.loadWithComments();
            } catch (InvalidConfigurationException exception) {
                this.plugin.getLogger().log(Level.WARNING, file.getName() + " contains invalid YAML structure.", exception);

                continue;
            } catch (IOException exception) {
                this.plugin.getLogger().log(Level.WARNING, "Could not load file: " + file.getName(), exception);

                continue;
            }

            addBlockParticle(file.getName().replace(".yml", ""), new BlockParticle(config));
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void reload() {
        saveParticleLocations();

        load(false);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean addBlockParticleLocation(String particleName, Location location) {
        final BlockParticle blockParticle = getBlockParticle(particleName);

        final AbstractParticle abstractParticle = getBlockParticle(particleName).getParticle();

        abstractParticle.setLocation(location);
        abstractParticle.execute();

        this.activeParticles.add(blockParticle);

        return false;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean hasBlockParticle(final String particleName, final boolean readFile) {
        if (readFile) {
            return this.data.contains("locations." + particleName);
        }

        return this.particles.containsKey(particleName);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean hasBlockParticle(final String particleName) {
        return hasBlockParticle(particleName, false);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void saveParticleLocations() {
        // Get the configuration section.
        final ConfigurationSection section = this.data.getConfigurationSection("locations");

        for (String id : section.getKeys(false)) {
            final ConfigurationSection particle = section.getConfigurationSection(id);

            if (hasBlockParticle(id, true)) {
                final BlockParticle type = this.particles.get(id);

                final AbstractParticle key = type.getParticle();

                // Update the particle name from `particles` directory!
                particle.set("particle-type", id);

                // "key" is the location data, We are simply updating the location!
                particle.set("key", key.asString());

                // Save the file after each edit!
                this.file.save();
            }
        }

        this.plugin.getLogger().warning("Size: " + this.particles.size());

        // We only loop through this after the above check. This means the id does not exist, so we are adding it.
        for (final BlockParticle particle : this.particles.values()) {
            final String fileName = particle.getConfig().getFileName();

            this.plugin.getLogger().warning("File: " + fileName);

            final AbstractParticle key = particle.getParticle();

            // Set the particle name from `particles` directory!
            section.set(fileName + ".particle-type", fileName);

            // "key" is the location data, We are simply updating the location!
            section.set(fileName + ".key", key.asString());

            // Save the file after each edit!
            this.file.save();
        }
    }

    /**
     {@inheritDoc}
     */
    public final BlockParticle getBlockParticle(final String particleName) {
        return this.particles.getOrDefault(particleName, null);
    }

    /**
     {@inheritDoc}
     */
    public final boolean addBlockParticle(final String particleName, final BlockParticle particle) {
        // Do not add if it already exists.
        if (hasBlockParticle(particleName, false)) return true;

        this.particles.put(particleName, particle);

        return false;
    }

    /**
     {@inheritDoc}
     */
    public void removeBlockParticle(final String particleName) {
        this.particles.remove(particleName);
    }

    /**
     {@inheritDoc}
     */
    public final Map<String, BlockParticle> getParticles() {
        return Collections.unmodifiableMap(this.particles);
    }
}