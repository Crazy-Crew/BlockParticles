package com.badbones69.blockparticles.commands.particles.types.admin;

import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.commands.particles.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import java.util.HashMap;

public class CommandSet extends BaseCommand {

    @Command("set")
    @Permission(value = "blockparticles.set", def = PermissionDefault.OP)
    public void set(Player player, @Suggestion("ids") String id, @Suggestion("files") String file) {
        final Block block = player.getTargetBlockExact(player.getViewDistance());

        if (block == null || block.getType() == Material.AIR) {
            Messages.not_looking_at_block.sendMessage(player);

            return;
        }

        boolean isAdded = this.manager.addActiveParticle(id, file, block.getLocation());

        if (isAdded) {
            Messages.location_added.sendMessage(player, new HashMap<>() {{
                put("{name}", id);
            }});
        }
    }
}