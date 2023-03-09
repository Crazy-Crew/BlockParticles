package us.crazycrew.crazycrates.configurations;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import java.util.List;
import static ch.jalu.configme.properties.PropertyInitializer.newListProperty;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

/**
 * @author RyderBelserion
 * @author BadBones69
 *
 * Date: 3/9/2023
 * Time: 5:35 PM
 * Last Edited: 3/9/2023 @ 5:35 PM
 *
 * Description: The config.yml options.
 */
public class ConfigSettings implements SettingsHolder {

    // Empty constructor required by SettingsHolder
    public ConfigSettings() {}

    @Override
    public void registerComments(CommentsConfiguration conf) {
        String[] header = {
                "Support: https://discord.gg/crazycrew",
                "Github: https://github.com/Crazy-Crew",
                "",
                "Issues: https://github.com/Crazy-Crew/CrazyCrates/issues",
                "Features: https://github.com/Crazy-Crew/CrazyCrates/discussions",
                "",
                "Legacy color codes such as &7,&c no longer work. You must use MiniMessage",
                "https://docs.advntr.dev/minimessage/format.html#color"
        };

        String[] deprecation = {
                "",
                "Warning: This section is subject to change so it is considered deprecated.",
                "This is your warning before the change happens.",
                ""
        };

        conf.setComment("settings", header);
        //conf.setComment("gui-settings.filler-items", deprecation);
        //conf.setComment("gui-settings.customizer", deprecation);
    }
}