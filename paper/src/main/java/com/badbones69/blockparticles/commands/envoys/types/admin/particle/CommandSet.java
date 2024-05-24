package com.badbones69.blockparticles.commands.envoys.types.admin.particle;

import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.api.builders.menus.MainMenu;
import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

public class CommandSet extends BaseCommand {

    private @NotNull final ParticleManager particleManager = this.plugin.getParticleManager();

    @Command("set")
    @Permission(value = "blockparticles.set", def = PermissionDefault.OP)
    public void set(Player player, String id, @Suggestion("particles") String particle) {
        if (particle != null && !particle.isEmpty()) {
            this.particleManager.addSetCommandPlayer(player, particle);

            player.openInventory(new MainMenu(player).getInventory());

            return;
        }

        Methods.setLoc(player, id, particle);
    }
}