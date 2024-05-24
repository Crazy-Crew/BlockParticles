package com.badbones69.blockparticles.config.impl;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;

import java.util.List;

import static ch.jalu.configme.properties.PropertyInitializer.newListProperty;
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

    @Comment("A list of available placeholders: {prefix}, {fountains_total}, {particles_total}")
    public static final Property<List<String>> particle_types = newListProperty("particle.types", List.of(
            "<bold><gold>━━━━━━━━━━━━━━━━━━━ Particle Stats ━━━━━━━━━━━━━━━━━━━</gold></bold>",
            "<dark_gray>»</dark_gray> <red>Particle Types: </red>",
            " ⤷ <green>Spiral, DoubleSpiral, Crit, BigCrit, Flame, BigFlames, Volcano, Fog, Enchant, Storm</green>",
            " ⤷ <green>Chains, FireStorm, Snow, Potion, Music, Spew, Witch, DoubleWitch, SnowStorm, FireSpew</green>",
            " ⤷ <green>FootPrint, Water, HappyVillager, AngryVillager, MobSpawner, EnderSignal, Rainbow</green>",
            " ⤷ <green>SnowBlast, Halo, SoulWell, BigSoulWell, LoveWell, BigLoveWell, FlameWheel</green>",
            " ⤷ <green>WitchTornado, LoveTornado</green>",
            " ⤷ <red>Total Particles: {particles_total}</red>",
            "",
            "<dark_gray>»</dark_gray> <yellow>Fountain Types: </yellow>",
            " ⤷ <green>Gems, Halloween, Heads, Presents, Mobs, Food, Pokemon, Mario</green>",
            " ⤷ <red>Total Fountains: {fountains_total}</red>",
            "",
            "<dark_gray>»</dark_gray> <yellow>Active Stats: </yellow>",
            " ⤷ <red>Active Particles/Fountains: {particles_active}",
            "",
            "<dark_gray>»</dark_gray> <yellow>Other: </yellow>",
            " ⤷ <red>Fountain Items: {fountain_items}",
            "",
            "<bold><gold>━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</gold></bold>"
    ));

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