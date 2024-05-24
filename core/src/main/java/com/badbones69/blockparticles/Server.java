package com.badbones69.blockparticles;

import com.badbones69.blockparticles.config.ConfigManager;
import com.ryderbelserion.vital.core.AbstractPlugin;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.logging.Logger;

public class Server extends AbstractPlugin {

    private final File directory;
    private final Logger logger;

    public Server(@NotNull final File directory, @NotNull final Logger logger) {
        this.directory = directory;
        this.logger = logger;
    }

    /**
     * Loads the plugin.
     */
    public void apply() {
        ConfigManager.load();
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
}