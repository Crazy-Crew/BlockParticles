package com.badbones69.blockparticles.api;

import ch.jalu.configme.SettingsManager;
import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.enums.CustomFiles;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.interfaces.IParticleBuilder;
import com.badbones69.blockparticles.api.interfaces.IParticleHandler;
import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.ConfigKeys;
import com.badbones69.blockparticles.utils.ParticleUtil;
import com.ryderbelserion.vital.core.config.YamlFile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleyaml.configuration.ConfigurationSection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParticleHandler implements IParticleHandler {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final Map<String, IParticleBuilder> particles = new HashMap<>();

    private final SettingsManager config = ConfigManager.getConfig();

    private final CustomFiles file = CustomFiles.data;

    private final YamlFile data = this.file.getYamlFile();

    @Override
    public void load(boolean isServerStart) {
        // Get the configuration section.
        final ConfigurationSection section = this.data.getConfigurationSection("locations");

        if (isServerStart) { // Only used for migration, That should only be handled on startup!
            // Check if it is not null.
            if (section != null) {
                // Start looping!
                for (final String id : section.getKeys(false)) {
                    boolean hasOldKey = section.contains(id + ".world");

                    ConfigurationSection key = section.getConfigurationSection(id);

                    // If it has the world section.
                    if (hasOldKey) {
                        // Get the world name.
                        String worldName = key.getString("world");

                        // Get the world object.
                        World world = this.plugin.getServer().getWorld(worldName);

                        if (world == null) {
                            if (this.config.getProperty(ConfigKeys.clean_data_file)) {
                                // If the world is null, we remove the data.
                                section.set(id, null);

                                // Save to file immediately.
                                this.file.save();
                            }

                            continue;
                        }

                        // Get the old values.
                        int x = key.getInt("x");
                        int y = key.getInt("y");
                        int z = key.getInt("z");

                        // Convert it to the string format.
                        String asString = world.getUID() + "," + x + "," + y + "," + z;

                        // Set the new value.
                        key.set("key", asString);

                        // Remove the old values.
                        key.remove("world");
                        key.remove("x");
                        key.remove("y");
                        key.remove("z");

                        /*ParticleKey type = ParticleUtil.getParticleByName(section.getString("particle", ParticleKey.SPIRAL.getParticleName().toLowerCase()));

                        if (type != null) {
                            key.set("type", type.getParticleName().toLowerCase());

                            // Update default particles
                            switch (type) {
                                case SPIRAL, DOUBLE_SPIRAL -> key.set("particle", Particle.WITCH.getKey().getKey());
                                case FLAME, BIG_FLAME, FLAME_WHEEL -> key.set("particle", Particle.FLAME.getKey().getKey());
                                case VOLCANO -> key.set("particle", Particle.LAVA.getKey().getKey());
                                case CRITICAL, BIG_CRITICAL -> key.set("particle", Particle.CRIT.getKey().getKey());
                                case STORM -> {
                                    List<String> particles = new ArrayList<>();



                                    key.set("particle", Particle.CLOUD.getKey().getKey());
                                }
                            }
                        }*/

                        // Save to file immediately.
                        this.file.save();
                    }
                }
            }
        }

        // Clear old particles.
        this.particles.clear();

        // Check if section is null.
        if (section != null) {
            // Loop through section.
            for (final String id : section.getKeys(false)) {
                // Get the location key.
                final String[] key = section.getString(id + ".key").split(",");

                // Get the world uuid.
                final String uuid = key[0];

                // get the world.
                final World world = this.plugin.getServer().getWorld(uuid);

                // Check if null.
                if (world == null) {
                    // Clean the data if they want to.
                    if (this.config.getProperty(ConfigKeys.clean_data_file)) {
                        // If the world is null, we remove the data.
                        this.data.set("locations." + id, null);

                        // Save to file immediately.
                        this.file.save();
                    }

                    // Keep going.
                    continue;
                }

                // Get the x,y,z.
                int x = Integer.parseInt(key[3]);
                int y = Integer.parseInt(key[3]);
                int z = Integer.parseInt(key[3]);

                // Add the particle.
                addParticleLocation(id, ParticleUtil.getParticleByName(section.getString(id + ".particle", ParticleKey.SPIRAL.getParticleName())), new Location(world, x, y, z));
            }
        }
    }

    @Override
    public void load() {

    }

    /**
     {@inheritDoc}
     */
    @Override
    public void reload() {
        saveParticleLocations();

        load();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void addParticleLocation(final String id, final ParticleKey particle, final Location location) {
        //final ParticleType type = new ParticleType(id, location);

        //this.particles.put(id, type);
    }

    @Override
    public void addParticleLocation(final IParticleBuilder builder) {
        String id = builder.getId();

        this.particles.put(id, builder.execute());
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void removeParticleLocation(final String id) {
        final IParticleBuilder type = this.particles.remove(id);

        // Cancel the task if not null.
        if (type != null) {
            type.cancel();
        }

        // Only save to file if the id is in the file.
        if (hasParticleLocation(id, true)) {
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
            final ConfigurationSection particle = section.getConfigurationSection(id);

            // Check if id exists in the file.
            if (hasParticleLocation(id, true)) {
                IParticleBuilder type = this.particles.get(id);

                // Updating the minecraft particle!
                particle.set("particle", type.getParticle());

                // Updating the particle type!
                particle.set("type", type.getParticleKey().getParticleName().toLowerCase());

                // "key" is the location data, We are simply updating the location!
                particle.set("key", type.asString());

                // Save the file after each edit!
                this.file.save();
            }
        }

        // We only loop through this after the above check. This means the id does not exist, so we are adding it.
        for (final IParticleBuilder type : this.particles.values()) {
            String id = type.getId();

            // Updating the minecraft particle!
            section.set(id + ".particle", type.getParticle().getKey().getKey());

            // Updating the particle type!
            section.set(id + ".type", type.getParticleKey().getParticleName().toLowerCase());

            // "key" is the location data, We are simply updating the location!
            section.set(id + ".key", type.asString());

            // Save the file after each edit!
            this.file.save();
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final Map<String, IParticleBuilder> getParticles() {
        return Collections.unmodifiableMap(this.particles);
    }

    public void playVolcano(final Location location, final Particle particle, final String id) {
        //final BukkitRunnable runnable = new GenericParticle(id,
        // ParticleKey.VOLCANO, particle, location.add(.5, .8, .5)).setCount(10);
        //final BukkitScheduler scheduler = this.plugin.getServer().getScheduler();

        //scheduler.scheduleSyncRepeatingTask(this.plugin, runnable, 0, ParticleKey.VOLCANO.getPeriod());
    }

    public void playCritical(final Location location, final Particle particle, final String id) {
        //final BukkitRunnable runnable = new GenericParticle(id,
        // ParticleKey.CRITICAL, particle, location.add(0.5, 1.1, .5));
        //final BukkitScheduler scheduler = this.plugin.getServer().getScheduler();

        //scheduler.scheduleSyncRepeatingTask(this.plugin, runnable, 0, ParticleKey.CRITICAL.getPeriod());
    }

    public void playSpiral(final Location location, final String id, final Particle particle, final int amount) {
        //final BukkitRunnable runnable = new SpiralParticle(id, amount,
        // ParticleKey.SPIRAL, particle, location.add(.5, .7, .5));
        //final BukkitScheduler scheduler = this.plugin.getServer().getScheduler();

        //scheduler.scheduleSyncRepeatingTask(this.plugin, runnable, 0, ParticleKey.SPIRAL.getPeriod());
    }

    public void playDoubleSpiral(final Location location, final String id, final Particle particle, final int amount) {
        //final BukkitRunnable runnable = new SpiralParticle(id, amount,
        // ParticleKey.DOUBLE_SPIRAL, particle, location.add(.5, .7, .5));
        //final BukkitScheduler scheduler = this.plugin.getServer().getScheduler();

        //scheduler.scheduleSyncRepeatingTask(this.plugin, runnable, 0, ParticleKey.DOUBLE_SPIRAL.getPeriod());
    }
}