package com.badbones69.blockparticles.config.migrate;

import ch.jalu.configme.configurationdata.ConfigurationData;
import ch.jalu.configme.migration.PlainMigrationService;
import ch.jalu.configme.resource.PropertyReader;
import com.badbones69.blockparticles.api.enums.FileProperty;
import org.jetbrains.annotations.NotNull;

public class ConfigMigration extends PlainMigrationService {

    @Override
    protected boolean performMigrations(@NotNull PropertyReader reader, @NotNull ConfigurationData configurationData) {
        return migrateLocale(reader, configurationData);
    }

    private boolean migrateLocale(@NotNull PropertyReader reader, @NotNull ConfigurationData configurationData) {
        return FileProperty.command_prefix.moveString(reader, configurationData);
    }
}