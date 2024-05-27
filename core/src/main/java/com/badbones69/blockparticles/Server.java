package com.badbones69.blockparticles;

import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.ConfigKeys;
import com.ryderbelserion.vital.core.AbstractPlugin;
import com.ryderbelserion.vital.core.util.FileUtil;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class Server extends AbstractPlugin {

    private final File directory;
    private final File particles;
    private final Logger logger;
    private boolean isLogging;

    public Server(@NotNull final File directory, @NotNull final Logger logger) {
        this.directory = directory;
        this.particles = new File(this.directory, "particles");
        this.logger = logger;
    }

    /**
     * Loads the plugin.
     */
    public void apply() {
        ConfigManager.load();

        this.isLogging = ConfigManager.getConfig().getProperty(ConfigKeys.verbose_logging);
    }

    /**
     * Disables the plugin.
     */
    public void disable() {

    }

    /**
     * @return the plugin directory
     */
    @Override
    public @NotNull final File getDirectory() {
        return this.directory;
    }

    /**
     * @return the particles directory
     */
    public @NotNull final File getParticleFolder() {
        return this.particles;
    }

    /**
     * @return a list of particle files
     */
    public final List<File> getParticleFiles() {
        return FileUtil.getFileObjects(getDirectory().toPath(), "particles", ".yml");
    }

    /**
     * @return the plugin logger
     */
    @Override
    public @NotNull final Logger getLogger() {
        return this.logger;
    }

    @Override
    public final boolean isLogging() {
        return this.isLogging;
    }
}