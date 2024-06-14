package com.badbones69.blockparticles.commands.relations;

import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.commands.MessageManager;
import dev.triumphteam.cmd.bukkit.message.BukkitMessageKey;
import dev.triumphteam.cmd.core.message.MessageKey;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ArgumentRelations extends MessageManager {

    private String getContext(String command, String order) {
        if (command.isEmpty() || order.isEmpty()) return "";

        //String usage = "";

        //switch (command) {
        //    case "remove" -> usage = order + " [id]";
        //    case "create" -> usage = order + " [id] [particle]";
        //    case "set" -> usage = order + " [id] [type]";
        //}

        //return usage;
        return "";
    }

    @Override
    public void build() {
        this.commandManager.registerMessage(BukkitMessageKey.UNKNOWN_COMMAND, (sender, context) -> send(sender, Messages.unknown_command.getMessage(sender, "{command}", context.getInvalidInput())));

        this.commandManager.registerMessage(MessageKey.INVALID_ARGUMENT, (sender, context) -> send(sender, Messages.correct_usage.getMessage(sender, "{usage}", context.getArgumentName())));

        this.commandManager.registerMessage(BukkitMessageKey.NO_PERMISSION, (sender, context) -> send(sender, Messages.no_permission.getMessage(sender, "{permission}", context.getPermission().toString())));

        this.commandManager.registerMessage(BukkitMessageKey.PLAYER_ONLY, (sender, context) -> send(sender, Messages.must_be_a_player.getMessage(sender)));

        this.commandManager.registerMessage(BukkitMessageKey.CONSOLE_ONLY, (sender, context) -> send(sender, Messages.must_be_console_sender.getMessage(sender)));
    }

    @Override
    public void send(@NotNull CommandSender sender, @NotNull String component) {
        sender.sendRichMessage(component);
    }
}