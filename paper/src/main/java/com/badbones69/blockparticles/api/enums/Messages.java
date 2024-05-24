package com.badbones69.blockparticles.api.enums;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.properties.Property;
import com.badbones69.blockparticles.config.ConfigManager;
import com.badbones69.blockparticles.config.impl.ConfigKeys;
import com.badbones69.blockparticles.config.impl.MessageKeys;
import com.badbones69.blockparticles.config.impl.locale.ErrorKeys;
import com.badbones69.blockparticles.config.impl.locale.MiscKeys;
import com.badbones69.blockparticles.config.impl.locale.PlayerKeys;
import com.ryderbelserion.vital.core.util.StringUtil;
import com.ryderbelserion.vital.paper.enums.Support;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Messages {


    internal_error(ErrorKeys.internal_error),
    not_a_particle(ErrorKeys.not_a_particle),
    not_looking_at_block(ErrorKeys.not_looking_at_block),

    must_be_console_sender(PlayerKeys.must_be_console_sender),
    must_be_a_player(PlayerKeys.must_be_a_player),
    no_permission(PlayerKeys.no_permission),

    location_empty(MessageKeys.location_empty),
    location_already_taken(MessageKeys.location_already_taken),
    location_added(MessageKeys.location_added),
    location_deleted(MessageKeys.location_deleted),
    location_does_not_exist(MessageKeys.location_does_not_exist),
    location_set(MessageKeys.location_set),

    unknown_command(MiscKeys.unknown_command),
    correct_usage(MiscKeys.correct_usage),
    reloaded(MiscKeys.plugin_reloaded),
    help(MiscKeys.help, true),
    particle_types(MessageKeys.particle_types, true);

    private Property<String> property;

    private Property<List<String>> properties;
    private boolean isList = false;

    Messages(@NotNull final Property<String> property) {
        this.property = property;
    }

    Messages(@NotNull final Property<List<String>> properties, final boolean isList) {
        this.properties = properties;
        this.isList = isList;
    }

    private final SettingsManager config = ConfigManager.getConfig();

    private boolean isList() {
        return this.isList;
    }

    public String getString() {
        return ConfigManager.getMessages().getProperty(this.property);
    }

    public List<String> getList() {
        return ConfigManager.getMessages().getProperty(this.properties);
    }

    public String getMessage() {
        return getMessage(null, new HashMap<>());
    }

    public String getMessage(@Nullable final CommandSender sender) {
        if (sender instanceof Player player) {
            return getMessage(player, new HashMap<>());
        }

        return getMessage(null, new HashMap<>());
    }

    public String getMessage(@NotNull final Map<String, String> placeholders) {
        return getMessage(null, placeholders);
    }

    public String getMessage(@NotNull final String placeholder, @NotNull final String replacement) {
        return getMessage(null, placeholder, replacement);
    }

    public String getMessage(@Nullable final CommandSender sender, @NotNull final String placeholder, @NotNull final String replacement) {
        Map<String, String> placeholders = new HashMap<>() {{
            put(placeholder, replacement);
        }};

        if (sender instanceof Player player) {
            return getMessage(player, placeholders);
        }

        return getMessage(null, placeholders);
    }

    public String getMessage(@Nullable final CommandSender sender, @NotNull final Map<String, String> placeholders) {
        if (sender instanceof Player player) {
            return getMessage(player, placeholders);
        }

        return getMessage(null, placeholders);
    }

    public String getMessage(@Nullable final Player player, @NotNull final Map<String, String> placeholders) {
        String prefix = this.config.getProperty(ConfigKeys.command_prefix);

        String message = parse(placeholders);

        if (Support.placeholder_api.isEnabled() && player != null) {
            return PlaceholderAPI.setPlaceholders(player, message.replaceAll("\\{prefix}", prefix));
        }

        return message.replaceAll("\\{prefix}", prefix);
    }

    private @NotNull String parse(@NotNull final Map<String, String> placeholders) {
        String message;

        if (isList()) {
            message = StringUtil.chomp(StringUtil.convertList(getList()));
        } else {
            message = getString();
        }

        if (!placeholders.isEmpty()) {
            for (Map.Entry<String, String> placeholder : placeholders.entrySet()) {
                message = message.replace(placeholder.getKey(), placeholder.getValue()).replace(placeholder.getKey().toLowerCase(), placeholder.getValue());
            }
        }

        return message;
    }

    public void sendMessage(Player player) {
        sendMessage(player, new HashMap<>());
    }

    public void sendMessage(Player player, Map<String, String> placeholder) {
        String message = getMessage(placeholder);

        if (message.isEmpty() || message.isBlank()) {
            return;
        }

        player.sendRichMessage(message);
    }

    public void sendMessage(CommandSender sender) {
        sendMessage(sender, new HashMap<>());
    }

    public void sendMessage(CommandSender sender, Map<String, String> placeholder) {
        String message = getMessage(placeholder);

        if (message.isEmpty() || message.isBlank()) {
            return;
        }

        sender.sendRichMessage(message);
    }
}