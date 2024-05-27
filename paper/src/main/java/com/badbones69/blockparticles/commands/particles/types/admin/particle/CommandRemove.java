package com.badbones69.blockparticles.commands.particles.types.admin.particle;

import com.badbones69.blockparticles.commands.particles.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class CommandRemove extends BaseCommand {

    @Command("remove")
    @Permission(value = "blockparticles.remove", def = PermissionDefault.OP)
    public void remove(Player player, @Suggestion("ids") String id) {

    }
}