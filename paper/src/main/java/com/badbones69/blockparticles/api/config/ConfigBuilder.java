package com.badbones69.blockparticles.api.config;

import ch.jalu.configme.configurationdata.ConfigurationData;
import ch.jalu.configme.configurationdata.ConfigurationDataBuilder;
import com.badbones69.blockparticles.api.config.types.PluginSupport;

public class ConfigBuilder {

    private ConfigBuilder() {}

    public static ConfigurationData support() {
        return ConfigurationDataBuilder.createConfiguration(PluginSupport.class);
    }
}