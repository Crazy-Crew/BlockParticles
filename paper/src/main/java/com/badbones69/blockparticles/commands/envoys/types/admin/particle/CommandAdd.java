package com.badbones69.blockparticles.commands.envoys.types.admin.particle;

import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class CommandAdd extends BaseCommand {

    @Command("add")
    @Permission(value = "blockparticles.add", def = PermissionDefault.OP)
    public void add(Player player, String id) {
        Methods.addLoc(player, id);
    }
}