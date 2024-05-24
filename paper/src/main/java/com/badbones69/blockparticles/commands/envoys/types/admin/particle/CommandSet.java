package com.badbones69.blockparticles.commands.envoys.types.admin.particle;

import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.builders.menus.MainMenu;
import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Optional;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class CommandSet extends BaseCommand {

    @Command("set")
    @Permission(value = "blockparticles.set", def = PermissionDefault.OP)
    public void set(Player player, @Suggestion("particles") String particle, @Optional @Suggestion("locations") String location) {
        if (!location.isEmpty()) {
            this.particleManager.addSetCommandPlayer(player, location);

            player.openInventory(new MainMenu(player).getInventory());

            return;
        }

        Methods.setLoc(player, particle, location);
    }
}