package com.badbones69.blockparticles.commands.particles.types.admin.particle;

import com.badbones69.blockparticles.commands.particles.BaseCommand;
import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class CommandCancel extends BaseCommand {

    @Command("cancel")
    @Permission(value = "blockparticles.cancel", def = PermissionDefault.OP)
    public void remove(Player player, @Suggestion("names") String id) {
        AbstractParticle task = this.particleLoader.getBlockParticle(id).getParticle();

        if (task != null) {
            this.plugin.getLogger().warning("Task: " + task.getTaskId());

            task.cancel();
        }
    }
}