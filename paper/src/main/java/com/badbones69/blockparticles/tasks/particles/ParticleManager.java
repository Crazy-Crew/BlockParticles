package com.badbones69.blockparticles.tasks.particles;

import ch.jalu.configme.SettingsManager;
import com.badbones69.blockparticles.Server;
import com.badbones69.blockparticles.api.enums.CustomFiles;
import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.ConfigKeys;
import com.badbones69.blockparticles.api.objects.BlockParticle;
import com.badbones69.blockparticles.api.config.ParticleConfig;
import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.interfaces.IParticleManager;
import com.ryderbelserion.vital.core.config.YamlFile;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.exceptions.InvalidConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class ParticleManager implements IParticleManager {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final SettingsManager config = ConfigManager.getConfig();

    private final Server server = this.plugin.getPaperServer();

    private final CustomFiles file = CustomFiles.data;
    private final YamlFile data = this.file.getYamlFile();

    private final Map<String, BlockParticle> activeLocations = new HashMap<>();
    private final Map<String, BlockParticle> files = new HashMap<>();

    @Override
    public void load(final boolean serverStart) {
        this.activeLocations.clear();
        this.files.clear();

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

            this.files.put(file.getName().replace(".yml", ""), new BlockParticle(config));
        }

        this.plugin.getLogger().warning("=====================================");
        this.plugin.getLogger().warning("Debug Level 1 Start: " + this.files.size());

        this.files.keySet().forEach(file -> {
            BlockParticle particle = this.files.get(file);

            if (particle == null) return;

            final ParticleConfig config = particle.getConfig();

            this.plugin.getLogger().warning("Particle Type: " + config.getParticleType());
            this.plugin.getLogger().warning("Amount: " + config.getParticleAmount());
            this.plugin.getLogger().warning("Animation Type: " + config.getParticleKey().getParticleName());
            this.plugin.getLogger().warning("File Name: " + config.getFileName());
        });

        this.plugin.getLogger().warning("Debug Level 1 End: " + this.files.size());
        this.plugin.getLogger().warning("=====================================");

        final ConfigurationSection section = this.data.getConfigurationSection("locations");

        if (section != null) {
            this.plugin.getLogger().warning("Debug Level 2 Start: " + this.activeLocations.size());

            for (String id : section.getKeys(false)) {
                final String[] locationKey = section.getString(id + ".key").split(",");

                final String uuid = locationKey[0];

                final World world = this.plugin.getServer().getWorld(UUID.fromString(uuid));

                if (world == null) {
                    if (this.config.getProperty(ConfigKeys.clean_data_file)) {
                        section.remove(id);

                        this.file.save();
                    }

                    continue;
                }

                this.plugin.getLogger().warning("World: " + world.getName());

                int x = Integer.parseInt(locationKey[1]);
                int y = Integer.parseInt(locationKey[2]);
                int z = Integer.parseInt(locationKey[3]);

                final Location location = new Location(world, x, y, z);

                addActiveParticle(id, section.getString(id + ".file"), location);
            }

            this.plugin.getLogger().warning("Debug Level 2 End: " + this.activeLocations.size());
            this.plugin.getLogger().warning("=====================================");
        }
    }

    @Override
    public void reload() {
        load(false);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean addActiveParticle(final String id, final String fileName, final Location location) {
        this.plugin.getLogger().warning("ID: " + id);
        this.plugin.getLogger().warning("File Name: " + fileName);
        this.plugin.getLogger().warning("Location: " + location.toString());

        final BlockParticle blockParticle = getFile(fileName);

        if (blockParticle == null) return true;

        final ParticleConfig config = blockParticle.getConfig();

        final AbstractParticle particle = blockParticle.getParticle();

        this.plugin.getLogger().warning("Particle A: " + config.getFileName());
        this.plugin.getLogger().warning("Particle B:" + config.getParticleType().getKey().getKey());

        // Set the location
        particle.setLocation(location);
        particle.execute();

        this.plugin.getLogger().warning("Particle C: " + particle.getLocation().toString());
        this.plugin.getLogger().warning("Particle DC: " + particle.isCancelled());
        this.plugin.getLogger().warning("Particle DI: " + particle.getTaskId());

        this.plugin.getLogger().warning("Particle Size 1: " + this.activeLocations.size());

        this.activeLocations.put(id, blockParticle);

        this.plugin.getLogger().warning("Particle Size 2: " + this.activeLocations.size());

        final ConfigurationSection section = this.data.getConfigurationSection("locations");

        if (!section.contains(id)) {
            section.set(id + ".type", config.getParticleKey().getParticleName());

            section.set(id + ".file", fileName);

            section.set(id + ".key", particle.asString());

            this.file.save();
        }

        return false;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void removeActiveParticle(final String fileName) {
        this.plugin.getLogger().warning("Size Z: " + fileName);

        this.plugin.getLogger().warning("Size A: " + this.activeLocations.size());

        final BlockParticle particle = this.activeLocations.remove(fileName);

        if (particle != null) {
            final AbstractParticle abstractParticle = particle.getParticle();

            if (abstractParticle != null) {
                this.plugin.getLogger().warning("Task A: " + abstractParticle.getTaskId());
                this.plugin.getLogger().warning("Task B: " + abstractParticle.isCancelled());
                this.plugin.getLogger().warning("Task C: " + abstractParticle.asString());
            }
        }

        this.plugin.getLogger().warning("Size AB: " + this.activeLocations.size());

        removeFile(fileName);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final BlockParticle getActiveParticle(final String fileName) {
        return this.activeLocations.getOrDefault(fileName, null);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean hasActiveParticle(final String fileName) {
        return this.activeLocations.containsKey(fileName);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final Map<String, BlockParticle> getActiveParticles() {
        return Collections.unmodifiableMap(this.activeLocations);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean addFile(final String fileName, final BlockParticle blockParticle) {
        if (hasFile(fileName)) return false;

        this.files.put(fileName, blockParticle);

        return true;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final BlockParticle getFile(final String fileName) {
        return this.files.getOrDefault(fileName, null);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void removeFile(final String fileName) {
        if (hasFile(fileName, true)) { //todo() this has to be put in it's own method, this is unrelated coded lol.
            this.data.remove("locations." + fileName);

            this.file.save();

            return;
        }

        this.files.remove(fileName);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean hasFile(final String fileName, final boolean readFile) {
        if (readFile) { //todo() this needs to be it's own method maybe.
            return this.data.contains("locations." + fileName);
        }

        return this.files.containsKey(fileName);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final boolean hasFile(final String fileName) {
        return hasFile(fileName, false);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final Map<String, BlockParticle> getBlockParticles() {
        return Collections.unmodifiableMap(this.files);
    }
}