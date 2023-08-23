package com.badbones69.blockparticles.api.config.types;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import ch.jalu.configme.properties.PropertyInitializer;

public class PluginSupport implements SettingsHolder {

    public PluginSupport() {}

    @Override
    public void registerComments(CommentsConfiguration conf) {
        //conf.setComment("", "");
    }

    private static final String path = "commands.";

    public static final Property<String> COMMAND_PREFIX = PropertyInitializer.newProperty("prefix", "<gradient:#f6426e:#725bf1>[BlockParticles]</gradient> ");
    public static final Property<String> REQUIRED_ARGUMENT = PropertyInitializer.newProperty(path + "required-argument", "{prefix}<red>This argument is not optional</red>");

    public static final Property<String> OPTIONAL_ARGUMENT = PropertyInitializer.newProperty(path + "optional-argument", "{prefix}<green>This argument is optional</green>");

    public static final Property<String> NOT_ENOUGH_ARGS = PropertyInitializer.newProperty(path + "not-enough-args", "{prefix}<red>You did not supply enough arguments.</red>");

    public static final Property<String> TOO_MANY_ARGS = PropertyInitializer.newProperty(path + "too-many-args", "{prefix}<red>You put more arguments then I can handle.</red>");

    public static final Property<String> HELP_HOVER_FORMAT = PropertyInitializer.newProperty(path + "help.hover-message", "{prefix}<gray>Click me to run the command.</gray> <gold>{command}</gold>");

    @Comment("How many commands should be displayed per page in /crazycrates help?")
    public static final Property<Integer> HELP_MAX_PAGE_ENTRIES = PropertyInitializer.newProperty(path + "help.max-help-page-entries", 10);

    public static final Property<String> HELP_INVALID_PAGE = PropertyInitializer.newProperty(path + "help.invalid-page", "{prefix}<red>The page</red> <gold>{page}</gold> <red>does not exist.</red>");

    public static final Property<String> HELP_INVALID_FORMAT = PropertyInitializer.newProperty(path + "help.invalid-format", "{prefix}<red>Invalid command used! Here is the correct one:</red> <blue>{command} {args}</blue>");

    public static final Property<String> HELP_PAGE_FORMAT = PropertyInitializer.newProperty(path + "help.page.format", "<gold>{command}</gold> <dark_gray>»</dark_gray> <reset>{description}");

    public static final Property<String> HELP_PAGE_HEADER = PropertyInitializer.newProperty(path + "help.page.header", "<dark_gray>────────</dark_gray> <gold>BlockParticles Help {page}</gold> <dark_gray>────────</dark_gray>");

    public static final Property<String> HELP_PAGE_FOOTER = PropertyInitializer.newProperty(path + "help.page.footer", "<dark_gray>────────</dark_gray> <gold>BlockParticles Help {page}");

    @Comment({
            "The only options that work here are run_command, suggest_command, copy_to_clipboard",
            "",
            "Warning: They are case-sensitive names so type them exactly as given above!",
            "",
            "This is what happens if you click the command in the /crazycrates:help menu."
    })
    public static final Property<String> HELP_HOVER_ACTION = PropertyInitializer.newProperty(path + "help.hover-action", "copy_to_clipboard");

    public static final Property<String> HELP_PAGE_NEXT = PropertyInitializer.newProperty(path + "help.page.navigation.next-button", " <green>»»»</green>");

    public static final Property<String> HELP_PAGE_BACK = PropertyInitializer.newProperty(path + "help.page.navigation.back-button", " <red>«««</red>");

    public static final Property<String> HELP_PAGE_GO_TO_PAGE = PropertyInitializer.newProperty(path + "help.page.navigation.go-to-page", "<gray>Go to page</gray> <gold>{page}</gold>");
}