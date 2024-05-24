package com.badbones69.blockparticles.commands.envoys.types.admin;

import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import com.badbones69.blockparticles.config.ConfigManager;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

public class CommandReload extends BaseCommand {

    @Command("reload")
    @Permission(value = "blockparticles.reload", def = PermissionDefault.OP)
    public void reload(CommandSender sender) {
        ConfigManager.refresh();

        this.particleManager.load();

        Methods.kill();
        Methods.startParticles();

        Messages.reloaded.sendMessage(sender);
    }
}