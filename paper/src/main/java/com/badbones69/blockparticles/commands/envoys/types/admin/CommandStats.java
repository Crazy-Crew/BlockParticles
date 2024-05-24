package com.badbones69.blockparticles.commands.envoys.types.admin;

import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.api.enums.particles.ParticleType;
import com.badbones69.blockparticles.api.enums.particles.Particles;
import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;
import java.util.HashMap;

public class CommandStats extends BaseCommand {

    @Command("stats")
    @Permission(value = "blockparticles.stats", def = PermissionDefault.OP)
    public void stats(CommandSender sender) {
        int items = particleManager.getFountainItem().size();
        int blocks = particleManager.getParticleControl().getLocations().size();

        Messages.particle_types.sendMessage(sender, new HashMap<>() {{
            put("{fountains_total}", String.valueOf(Particles.getParticles(ParticleType.FOUNTAIN).size()));
            put("{particles_total}", String.valueOf(Particles.getParticles(ParticleType.PARTICLE).size()));
            put("{particles_active}", String.valueOf(blocks));
            put("{fountain_items}", String.valueOf(items));
        }});
    }
}