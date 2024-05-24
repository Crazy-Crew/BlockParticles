package com.badbones69.blockparticles.config.impl.locale;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class PlayerKeys implements SettingsHolder {

    @Override
    public void registerComments(CommentsConfiguration conf) {
        String[] header = {
                "All messages related to players things."
        };

        conf.setComment("player", header);
    }

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> must_be_console_sender = newProperty("player.requirements.must-be-console-sender", "{prefix}<red>You must be using console to use this command.");

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> must_be_a_player = newProperty("player.requirements.must-be-player", "{prefix}<red>You must be a player to use this command.");

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> no_permission = newProperty("player.no-permission", "{prefix}<red>You do not have permission to use that command/menu!");

}