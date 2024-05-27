package com.badbones69.blockparticles.commands.particles.types.player;

import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.commands.particles.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

public class CommandHelp extends BaseCommand {

    @Command
    @Permission(value = "blockparticles.access", def = PermissionDefault.TRUE)
    public void on(CommandSender sender) {
        help(sender);
    }

    @Command("help")
    @Permission(value = "blockparticles.help", def = PermissionDefault.TRUE)
    public void help(CommandSender sender) {
        Messages.help.sendMessage(sender);
    }
}