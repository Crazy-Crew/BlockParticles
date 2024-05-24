package com.badbones69.blockparticles.config.impl.locale;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import java.util.List;
import static ch.jalu.configme.properties.PropertyInitializer.newListProperty;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class MiscKeys implements SettingsHolder {

    @Override
    public void registerComments(CommentsConfiguration conf) {
        String[] header = {
                "All messages related to misc things."
        };

        conf.setComment("misc", header);
    }

    @Comment("A list of available placeholders: {prefix}, {usage}")
    public static final Property<String> correct_usage = newProperty("misc.correct-usage", "{prefix}<red>The correct usage for this command is <yellow>{usage}");

    @Comment("A list of available placeholders: {prefix}, {command}")
    public static final Property<String> unknown_command = newProperty("misc.command-not-found", "{prefix}<red>{command} is not a known command.");

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> plugin_reloaded = newProperty("misc.config-reload", "{prefix}<gray>You have reloaded all the files.");

    public static final Property<List<String>> help = newListProperty("misc.help", List.of(
            "<dark_gray>- <gold>/bp help <dark_aqua>Shows the particle help menu.",
            "<dark_gray>- <gold>/bp reload <dark_aqua>Reloads all the config files.",
            "<dark_gray>- <gold>/bp list <dark_aqua>Shows all active particle locations..",
            "<dark_gray>- <gold>/bp add [page] <dark_aqua>Adds a new particle location.",
            "<dark_gray>- <gold>/bp delete <dark_aqua>Deletes a particle location.",
            "<dark_gray>- <gold>/bp set [amount] [player] <dark_aqua>Sets a location to use a particle.",
            "<dark_gray>- <gold>/bp stats <dark_aqua>Shows multiple statistics of the plugin."
    ));
}