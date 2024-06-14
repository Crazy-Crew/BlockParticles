package com.badbones69.blockparticles.commands.particles.types.admin;

import com.badbones69.blockparticles.api.objects.BlockParticle;
import com.badbones69.blockparticles.commands.particles.BaseCommand;
import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class CommandDelete extends BaseCommand {

    @Command("delete")
    @Permission(value = "blockparticles.delete", def = PermissionDefault.OP)
    public void delete(Player player, @Suggestion("files") String file) {
        final BlockParticle blockParticle = this.manager.getActiveParticle(file);

        // If null, return anyway.
        if (blockParticle == null) return;

        final AbstractParticle particle = blockParticle.getParticle();

        // If this is somehow null, also return.
        if (particle == null) return;

        this.plugin.getLogger().warning("Debug Level 3 Start");

        // Cancel the task.
        particle.cancel();

        // Remove the active particle from the map!
        this.manager.removeActiveParticle(file);

        this.plugin.getLogger().warning("Debug Level 3 End");
        this.plugin.getLogger().warning("=====================================");
    }
}