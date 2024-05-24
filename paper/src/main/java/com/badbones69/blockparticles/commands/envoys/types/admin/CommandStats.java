package com.badbones69.blockparticles.commands.envoys.types.admin;

import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.api.enums.particles.ParticleType;
import com.badbones69.blockparticles.api.enums.particles.ParticleData;
import com.badbones69.blockparticles.api.objects.ParticleLocation;
import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import com.badbones69.blockparticles.controllers.ParticleControl;
import com.ryderbelserion.vital.core.util.StringUtil;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandStats extends BaseCommand {

    private @NotNull final ParticleManager particleManager = this.plugin.getParticleManager();

    private @NotNull final ParticleControl particleControl = this.plugin.getParticleControl();

    @Command("stats")
    @Permission(value = "blockparticles.stats", def = PermissionDefault.OP)
    public void stats(CommandSender sender) {
        int items = this.particleManager.getFountainItem().size();
        int blocks = this.particleControl.getLocations().size();

        final List<String> locations = new ArrayList<>();

        for (Map.Entry<ParticleLocation, Integer> key : this.particleControl.getLocations().entrySet()) {
            final ParticleLocation particleLocation = key.getKey();
            final Location location = particleLocation.getLocation();
            final int taskId = key.getValue();

            final String id = particleLocation.getID();

            final String world = location.getWorld().getName();
            final int x = location.getBlockX();
            final int y = location.getBlockY();
            final int z = location.getBlockZ();

            Map<String, String> placeholders = new HashMap<>();

            placeholders.put("{x}", String.valueOf(x));
            placeholders.put("{y}", String.valueOf(y));
            placeholders.put("{z}", String.valueOf(z));
            placeholders.put("{task_id}", String.valueOf(taskId));
            placeholders.put("{world}", world);
            placeholders.put("{id}", id);

            locations.add(Messages.particle_format.getMessage(placeholders));
        }

        Messages.particle_types.sendMessage(sender, new HashMap<>() {{
            put("{locations}", particleManager.getLocations().isEmpty() ? "N/A" : StringUtil.chomp(StringUtil.convertList(locations)));
            put("{fountains_total}", String.valueOf(ParticleData.getParticles(ParticleType.FOUNTAIN).size()));
            put("{particles_total}", String.valueOf(ParticleData.getParticles(ParticleType.PARTICLE).size()));
            put("{particles_active}", String.valueOf(blocks));
            put("{fountain_items}", String.valueOf(items));
        }});
    }
}