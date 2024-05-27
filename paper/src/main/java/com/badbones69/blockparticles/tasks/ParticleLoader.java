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
import java.util.Map;
import java.util.logging.Level;

public class ParticleLoader implements IParticleHandler {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);
    private final Server server = this.plugin.getPaperServer();

    private final CustomFiles file = CustomFiles.data;
    private final YamlFile data = this.file.getYamlFile();

    private final Map<String, BlockParticle> particles = new HashMap<>();

    private final Map<String, BlockParticle> activeParticles = new HashMap<>();

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
    public final boolean removeActiveParticle(final String particleName) {
        return this.activeParticles.remove(particleName) != null;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final BlockParticle getActiveParticle(final String particleName) {
        return this.activeParticles.getOrDefault(particleName, null);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean hasActiveParticle(final String particleName) {
        return this.activeParticles.containsKey(particleName);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final Map<String, BlockParticle> getActiveParticles() {
        return Collections.unmodifiableMap(this.activeParticles);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean addActiveParticle(final String id, final String particleName, final Location location) {
        final BlockParticle blockParticle = getBlockParticle(particleName);

        final AbstractParticle abstractParticle = getBlockParticle(particleName).getParticle();

        abstractParticle.setLocation(location);
        abstractParticle.execute();

        this.activeParticles.put(id, blockParticle);

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
    //todo() check if the world the particle is in is null, if so add option to prune old locations.
    @Override
    public void saveParticleLocations() {
        // Get the configuration section.
        final ConfigurationSection section = this.data.getConfigurationSection("locations");

        for (String id : section.getKeys(false)) {
            final ConfigurationSection particle = section.getConfigurationSection(id);

            // Check if the file has an active particle.
            if (hasBlockParticle(id, true)) {
                final BlockParticle type = this.activeParticles.get(id);

                final AbstractParticle key = type.getParticle();

                final String locationKey = particle.getString("key");

                // Only update the location if they are different.
                if (locationKey != null && !locationKey.equals(key.asString())) {
                    // "key" is the location data, We are simply updating the location!
                    particle.set("key", key.asString());
                }

                final String particleType = particle.getString("type");

                // Only update the particle name if it is different.
                if (particleType != null && !particleType.equals(key.getParticleKey().getParticleName())) {
                    // Update the particle type as the file name might not always be the same!
                    particle.set("type", key.getParticleKey().getParticleName());
                }

                // Save the file after each edit!
                this.file.save();
            }
        }

        // We only loop through this after the above check. This means the id does not exist, so we are adding it.
        for (final String id : this.activeParticles.keySet()) {
            final BlockParticle particle = this.activeParticles.get(id);

            // Get the abstract particle.
            final AbstractParticle key = particle.getParticle();

            // Set the particle type as the file name might not always be the same!
            section.set(id + ".type", key.getParticleKey().getParticleName());

            // Set the particle name from the `particles` directory!
            section.set(id + ".file", particle.getConfig().getFileName());

            // "key" is the location data, We are simply setting the location!
            section.set(id + ".key", key.asString());

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