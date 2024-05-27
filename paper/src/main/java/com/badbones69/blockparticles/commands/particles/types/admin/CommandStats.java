package com.badbones69.blockparticles.commands.particles.types.admin;

import com.badbones69.blockparticles.commands.particles.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

public class CommandStats extends BaseCommand {

    @Command("stats")
    @Permission(value = "blockparticles.stats", def = PermissionDefault.OP)
    public void stats(CommandSender sender) {

    }
}