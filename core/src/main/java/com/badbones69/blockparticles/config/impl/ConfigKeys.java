package com.badbones69.blockparticles.config.impl;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import ch.jalu.configme.properties.PropertyInitializer;

public class ConfigKeys implements SettingsHolder {

    protected ConfigKeys() {}

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
        
        conf.setComment("player", header);
    }

    @Comment({
            "Choose the language you prefer to use on your server!",
            "",
            "Currently Available:",
            " > en-US ( English )",
            "",
            "If you do not see your language above, You can contribute by modifying the current en-US.yml",
            "https://github.com/Crazy-Crew/BlockParticles/blob/main/paper/src/main/resources/locale/en-US.yml",
            "Submit your finalized config using https://bin.bloom.host/ and send it to us in https://discord.gg/badbones-s-live-chat-182615261403283459",
            ""
    })
    public static final Property<String> locale_file = PropertyInitializer.newProperty("root.language", "en-US");

    @Comment("Whether you want BlockParticles to shut up or not, This option is ignored by errors.")
    public static final Property<Boolean> verbose_logging = PropertyInitializer.newProperty("root.verbose_logging", true);

    @Comment("This will remove all old data that no longer have an existing world.")
    public static final Property<Boolean> clean_data_file = PropertyInitializer.newProperty("root.clean_data_file", true);

    @Comment("The command prefix you want shown in front of commands!")
    public static final Property<String> command_prefix = PropertyInitializer.newProperty("root.command_prefix", "<dark_gray>[<dark_aqua>BlockParticles<dark_gray>]: ");
}