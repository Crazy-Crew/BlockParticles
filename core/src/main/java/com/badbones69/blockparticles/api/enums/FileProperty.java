package com.badbones69.blockparticles.api.enums;

import ch.jalu.configme.configurationdata.ConfigurationData;
import ch.jalu.configme.properties.Property;
import ch.jalu.configme.resource.PropertyReader;
import com.badbones69.blockparticles.config.impl.ConfigKeys;
import java.util.ArrayList;
import java.util.List;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public enum FileProperty {

    command_prefix(ConfigKeys.command_prefix, newProperty("settings.prefix", ConfigKeys.command_prefix.getDefaultValue()));

    private Property<String> newString;
    private Property<String> oldString;

    /**
     * A constructor moving the new and old string property for migration
     *
     * @param newString the new property
     * @param oldString the old property
     */
    FileProperty(Property<String> newString, Property<String> oldString) {
        this.newString = newString;
        this.oldString = oldString;
    }

    /**
     * Moves the old value to the new value
     *
     * @param reader the config reader
     * @param configuration the configuration data
     * @return true or false
     */
    public boolean moveString(PropertyReader reader, ConfigurationData configuration) {
        String key = reader.getString(this.oldString.getPath());

        if (key == null) return false;

        configuration.setValue(this.newString, this.oldString.determineValue(reader).getValue());

        return true;
    }

    private Property<Boolean> newBoolean;
    private Property<Boolean> oldBoolean;

    /**
     * A constructor consisting of the new and old boolean property for migration
     *
     * @param newBoolean the new property
     * @param oldBoolean the old property
     * @param dummy only to differentiate from previous constructors
     */
    FileProperty(Property<Boolean> newBoolean, Property<Boolean> oldBoolean, boolean dummy) {
        this.newBoolean = newBoolean;
        this.oldBoolean = oldBoolean;
    }

    /**
     * Moves the old value to the new value
     *
     * @param reader the config reader
     * @param configuration the configuration data
     * @return true or false
     */
    public boolean moveBoolean(PropertyReader reader, ConfigurationData configuration) {
        Boolean key = reader.getBoolean(this.oldBoolean.getPath());

        if (key == null) return false;

        configuration.setValue(this.newBoolean, this.oldBoolean.determineValue(reader).getValue());

        return true;
    }

    private Property<Integer> newInteger;
    private Property<Integer> oldInteger;

    /**
     * A constructor consisting of the new and old int property for migration
     *
     * @param newInteger the new property
     * @param oldInteger the old property
     * @param dummy only to differentiate from previous constructors
     */
    FileProperty(Property<Integer> newInteger, Property<Integer> oldInteger, int dummy) {
        this.newInteger = newInteger;
        this.oldInteger = oldInteger;
    }

    /**
     * Moves the old value to the new value
     *
     * @param reader the config reader
     * @param configuration the configuration data
     * @return true or false
     */
    public boolean moveInteger(PropertyReader reader, ConfigurationData configuration) {
        Integer key = reader.getInt(this.oldInteger.getPath());

        if (key == null) return false;

        configuration.setValue(this.newInteger, this.oldInteger.determineValue(reader).getValue());

        return true;
    }

    private Property<List<String>> newList;
    private Property<List<String>> oldList;

    /**
     * A constructor consisting of the new and old list property for migration
     *
     * @param newList the new property
     * @param oldList the old property
     * @param dummy only to differentiate from previous constructors
     */
    FileProperty(Property<List<String>> newList, Property<List<String>> oldList, List<String> dummy) {
        this.newList = newList;
        this.oldList = oldList;
    }

    /**
     * Moves the old value to the new value
     *
     * @param reader the config reader
     * @param configuration the configuration data
     * @return true or false
     */
    public boolean moveList(PropertyReader reader, ConfigurationData configuration) {
        List<?> key = reader.getList(this.oldList.getPath());

        if (key == null) return false;

        List<String> list = new ArrayList<>(this.oldList.determineValue(reader).getValue());

        configuration.setValue(this.newList, list);

        return true;
    }

    /**
     * Replaces old colors in the option when migrating.
     *
     * @param message the message to check
     * @return the finalized message to set
     */
    private String replace(String message) {
        return message.replaceAll(
                "&0", "<black>"
        ).replaceAll(
                "&9", "<dark_blue>"
        ).replaceAll(
                "&2", "<dark_green>"
        ).replaceAll(
                "&3", "<dark_aqua>"
        ).replaceAll(
                "&4", "<dark_red>"
        ).replaceAll(
                "&5", "<dark_purple>"
        ).replaceAll(
                "&6", "<gold>"
        ).replaceAll(
                "&7", "<gray>"
        ).replaceAll(
                "&8", "<dark_gray>"
        ).replaceAll(
                "&b", "<blue>"
        ).replaceAll(
                "&a", "<green>"
        ).replaceAll(
                "&c", "<red>"
        ).replaceAll(
                "&d", "<light_purple>"
        ).replaceAll(
                "&e", "<yellow>"
        ).replaceAll(
                "&f", "<white>"
        ).replaceAll(
                "&l", "<bold>"
        ).replaceAll(
                "&o", "<italic>"
        ).replaceAll(
                "&n", "<underlined>"
        ).replaceAll(
                "&m", "<strikethrough>"
        ).replaceAll(
                "&k", "<obfuscated>"
        );
    }
}