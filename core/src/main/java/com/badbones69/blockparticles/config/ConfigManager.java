package com.badbones69.blockparticles.config;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import ch.jalu.configme.resource.YamlFileResourceOptions;
import com.badbones69.blockparticles.config.impl.CategoryKeys;
import com.badbones69.blockparticles.config.impl.ConfigKeys;
import com.badbones69.blockparticles.config.impl.MessageKeys;
import com.badbones69.blockparticles.config.impl.locale.ErrorKeys;
import com.badbones69.blockparticles.config.impl.locale.MiscKeys;
import com.badbones69.blockparticles.config.impl.locale.PlayerKeys;
import com.badbones69.blockparticles.config.migrate.ConfigMigration;
import com.ryderbelserion.vital.core.config.YamlManager;
import com.ryderbelserion.vital.core.util.FileUtil;
import java.io.File;

public class ConfigManager {

    private static final YamlFileResourceOptions builder = YamlFileResourceOptions.builder().indentationSize(2).build();

    private static YamlManager yamlManager;

    private static SettingsManager config;
    private static SettingsManager messages;
    private static SettingsManager heads;

    /**
     * Loads configuration files.
     */
    public static void load() {
        if (yamlManager == null) yamlManager = new YamlManager();

        // Create directory
        yamlManager.createPluginDirectory();

        // Create config.yml
        config = SettingsManagerBuilder
                .withYamlFile(yamlManager.getDataFolder().resolve("config.yml"), builder)
                .migrationService(new ConfigMigration())
                .configurationData(ConfigKeys.class)
                .create();

        heads = SettingsManagerBuilder
                .withYamlFile(yamlManager.getDataFolder().resolve("heads.yml"), builder)
                .useDefaultMigrationService()
                .configurationData(CategoryKeys.class)
                .create();

        // Update locale file
        locale(config.getProperty(ConfigKeys.locale_file) + ".yml");

        // Add files
        yamlManager.addFolder("particles").addFile("config.yml").addFile("data.yml").init();
    }

    /**
     * Refreshes configuration files.
     */
    public static void refresh() {
        // Get old locale file.
        String oldLocale = config.getProperty(ConfigKeys.locale_file) + ".yml";

        // Reload the files
        config.reload();

        // Get new locale file.
        String newLocale = config.getProperty(ConfigKeys.locale_file) + ".yml";

        if (!oldLocale.equals(newLocale)) locale(newLocale); else messages.reload();

        // Refresh static files.
        getYamlManager().reloadFiles();
    }

    private static void locale(String path) {
        File locale = yamlManager.getDataFolder().resolve("locale").toFile();

        if (!locale.exists()) {
            FileUtil.extracts(ConfigManager.class, "/locale/", locale.toPath(), true);

            locale.mkdirs();
        }

        messages = SettingsManagerBuilder
                .withYamlFile(new File(locale, path), builder)
                .useDefaultMigrationService()
                .configurationData(MessageKeys.class, PlayerKeys.class, ErrorKeys.class, MiscKeys.class)
                .create();
    }

    /**
     * @return gets config.yml
     */
    public static SettingsManager getConfig() {
        return config;
    }

    /**
     * @return gets heads.yml
     */
    public static SettingsManager getHeads() {
        return heads;
    }

    /**
     * @return gets locale file
     */
    public static SettingsManager getMessages() {
        return messages;
    }

    /**
     * @return yamlmanager object
     */
    public static YamlManager getYamlManager() {
        return yamlManager;
    }
}