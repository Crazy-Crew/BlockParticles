package com.badbones69.blockparticles.commands.particles.types.admin.particle;

import com.badbones69.blockparticles.commands.particles.BaseCommand;
import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class CommandCancel extends BaseCommand {

    @Command("cancel")
    @Permission(value = "blockparticles.cancel", def = PermissionDefault.OP)
    public void remove(Player player, @Suggestion("ids") String id) {
        // Get active particle!
        AbstractParticle task = this.particleLoader.getActiveParticle(id).getParticle();

        // Check if null!
        if (task != null) {
            // Cancel task!
            task.cancel();

            // Remove from the hashset!
            this.particleLoader.removeActiveParticle(id);

            //todo() if in locations.yml, also remove it!
        }
    }
}