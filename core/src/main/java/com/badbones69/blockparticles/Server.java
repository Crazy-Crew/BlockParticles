package com.badbones69.blockparticles;

import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.ConfigKeys;
import com.ryderbelserion.vital.core.AbstractPlugin;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.logging.Logger;

public class Server extends AbstractPlugin {

    private final File directory;
    private final Logger logger;
    private boolean isLogging;

    public Server(@NotNull final File directory, @NotNull final Logger logger) {
        this.directory = directory;
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
     * @return the plugin logger
     */
    @Override
    public @NotNull final Logger getLogger() {
        return this.logger;
    }

    @Override
    public boolean isLogging() {
        return this.isLogging;
    }
}