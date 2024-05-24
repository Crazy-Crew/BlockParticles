package com.badbones69.blockparticles.config.impl;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;

import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class MessageKeys implements SettingsHolder {

    protected MessageKeys() {}

    @Override
    public void registerComments(CommentsConfiguration conf) {
        String[] header = {
                "Support: https://discord.gg/badbones-s-live-chat-182615261403283459",
                "Github: https://github.com/Crazy-Crew",
                "",
                "Features: https://github.com/Crazy-Crew/BlockParticles/discussions/categories/feature-requests",
                "Issues: https://github.com/Crazy-Crew/BlockParticles/issues",
                ""
        };
        
        conf.setComment("particle", header);
    }

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> location_empty = newProperty("particle.location.empty", "{prefix}<dark_aqua>There are no locations set!");

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> location_already_taken = newProperty("particle.location.exists", "{prefix}<dark_aqua>That location name is taken, You cannot re-use it until you remove it!");

    @Comment("A list of available placeholders: {prefix}, {name}, {particle}")
    public static final Property<String> location_added = newProperty("particle.location.added", "{prefix}<dark_aqua>You have added <gold>{name} <dark_aqua>to the block.");

    @Comment("A list of available placeholders: {prefix}, {name}")
    public static final Property<String> location_set = newProperty("particle.location.added", "{prefix}<dark_aqua>You have set <gold>{name} <dark_aqua>to <gold>{particle}.");

    @Comment("A list of available placeholders: {prefix}, {name}")
    public static final Property<String> location_deleted = newProperty("particle.location.deleted", "{prefix}<dark_aqua>You have deleted <gold>{name}.");

    @Comment("A list of available placeholders: {prefix}, {name}")
    public static final Property<String> location_does_not_exist = newProperty("particle.location.does-not-exist", "{prefix}<dark_aqua>There are no locations called <gold>{name}.");
}