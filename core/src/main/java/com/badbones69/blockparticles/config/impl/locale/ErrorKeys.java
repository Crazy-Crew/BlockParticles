package com.badbones69.blockparticles.config.impl.locale;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class ErrorKeys implements SettingsHolder {

    @Override
    public void registerComments(CommentsConfiguration conf) {
        String[] header = {
                "All messages related to errors."
        };

        conf.setComment("errors", header);
    }

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> internal_error = newProperty("errors.internal-error", "{prefix}<red>An internal error has occurred. Please check the console for the full error.");

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> generic_error = newProperty("errors.generic-error", "{prefix}<red>The argument <gold>{argument} <red>cannot be empty or blank.");

    @Comment("A list of available placeholders: {prefix}, {particle}")
    public static final Property<String> not_a_particle = newProperty("errors.not-a-particle", "{prefix}<gold>{particle} <red>is not a particle. Please do <gold>/blockparticles help <red>for more information.");

    @Comment("A list of available placeholders: {prefix}")
    public static final Property<String> not_looking_at_block = newProperty("errors.not-looking-at-block", "{prefix}<red>You are not looking at a block.");

}