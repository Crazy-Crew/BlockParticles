package com.badbones69.blockparticles.api.config.types;

import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;

public class PluginSupport implements SettingsHolder {

    public PluginSupport() {}

    @Override
    public void registerComments(CommentsConfiguration conf) {
        conf.setComment("", "");
    }
}