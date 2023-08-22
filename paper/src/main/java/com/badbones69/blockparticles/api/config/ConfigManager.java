package com.badbones69.blockparticles.api.config;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.plugin.BlockParticlesPlugin;
import com.badbones69.blockparticles.api.plugin.registry.BlockParticlesProvider;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class ConfigManager {

    private final @NotNull BlockParticlesPlugin blockParticlesPlugin = BlockParticlesProvider.get();

    private final @NotNull BlockParticles plugin = this.blockParticlesPlugin.getPlugin();

    private final @NotNull File dataFolder = this.plugin.getDataFolder();

    private static SettingsManager support;

    public void load() {
        // Create the plugin-support.yml file object.
        File pluginSupport = new File(this.dataFolder, "plugin-support.yml");

        // Bind it to settings manager
        support = SettingsManagerBuilder
                .withYamlFile(pluginSupport)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.support())
                .create();
    }

    public void reload() {
        // Reload plugin-support.yml
        support.reload();
    }

    public static SettingsManager support() {
        return support;
    }
}