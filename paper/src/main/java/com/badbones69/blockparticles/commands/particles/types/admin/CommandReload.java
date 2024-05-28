package com.badbones69.blockparticles.commands.particles.types.admin;

import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.commands.particles.BaseCommand;
import com.badbones69.blockparticles.config.ConfigManager;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

public class CommandReload extends BaseCommand {

    @Command("reload")
    @Permission(value = "blockparticles.reload", def = PermissionDefault.OP)
    public void reload(final CommandSender sender) {
        // Refresh the config files.
        ConfigManager.refresh();

        // Reloads the particles.
        this.manager.reload();

        // Send the message.
        Messages.reloaded.sendMessage(sender);
    }
}