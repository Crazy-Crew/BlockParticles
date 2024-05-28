package com.badbones69.blockparticles.api.config;

import com.badbones69.blockparticles.utils.ParticleUtil;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.BlockParticles;
import com.ryderbelserion.vital.core.config.YamlFile;
import com.ryderbelserion.vital.paper.util.ItemUtil;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.configuration.ConfigurationSection;
import java.io.File;

public class ParticleConfig extends YamlFile {

    protected final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    protected final String fileName;

    /**
     * Creates a {@link ParticleConfig}
     *
     * @param file the {@link File}
     */
    public ParticleConfig(@NotNull File file) {
        super(file);

        this.fileName = file.getName().replace(".yml", "");
    }

    /**
     * Get the {@link ConfigurationSection}.
     *
     * @return the {@link ConfigurationSection}
     */
    public final ConfigurationSection getSettings() {
        return getConfigurationSection("settings");
    }

    /**
     * Gets the {@link org.bukkit.Particle}.
     *
     * @return the {@link org.bukkit.Particle}
     */
    public final @Nullable Particle getParticleType() {
        return ItemUtil.getParticleType(getSettings().getString("particle.type", "flame"));
    }

    /**
     * Gets the amount of particles to show.
     *
     * @return the amount of particles to show
     */
    public final int getParticleAmount() {
        return getSettings().getInt("particle.amount", 1);
    }

    /**
     * Gets the shape of particle i.e. Spiral or Generic.
     *
     * @return the shape the {@link ParticleKey} will take
     */
    public final ParticleKey getParticleKey() {
        return ParticleUtil.getParticleByName(getSettings().getString("type", "Generic"));
    }

    /**
     * Gets the file name without .yml
     *
     * @return the file name
     */
    public final String getFileName() {
        return this.fileName;
    }
}