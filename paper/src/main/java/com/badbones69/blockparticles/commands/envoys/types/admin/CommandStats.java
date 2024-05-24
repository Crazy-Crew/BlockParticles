package com.badbones69.blockparticles.commands.envoys.types.admin;

import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

public class CommandStats extends BaseCommand {

    @Command("show")
    @Permission(value = "blockparticles.show", def = PermissionDefault.OP)
    public void show(CommandSender sender) {
        int items = particleManager.getFountainItem().size();
        int blocks = particleManager.getParticleControl().getLocations().size();

        //sender.sendMessage(Methods.color(prefix + "&3There are &6" + items + " &3items in the List."));
        //sender.sendMessage(Methods.color(prefix + "&3There are &6" + blocks + " &3particles/fountains running."));
    }
}