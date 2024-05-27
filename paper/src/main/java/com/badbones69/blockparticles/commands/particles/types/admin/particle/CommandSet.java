package com.badbones69.blockparticles.commands.particles.types.admin.particle;

import com.badbones69.blockparticles.commands.particles.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class CommandSet extends BaseCommand {

    @Command("set")
    @Permission(value = "blockparticles.set", def = PermissionDefault.OP)
    public void set(Player player, String id, @Suggestion("types") String type) {

    }
}