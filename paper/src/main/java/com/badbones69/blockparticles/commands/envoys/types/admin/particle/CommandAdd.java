package com.badbones69.blockparticles.commands.envoys.types.admin.particle;

import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.builders.particles.types.GenericParticle;
import com.badbones69.blockparticles.api.builders.particles.types.SpiralParticle;
import com.badbones69.blockparticles.api.interfaces.IParticleBuilder;
import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import com.badbones69.blockparticles.utils.ParticleUtil;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Optional;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import java.util.HashMap;

public class CommandAdd extends BaseCommand {

    @Command("add")
    @Permission(value = "blockparticles.add", def = PermissionDefault.OP)
    public void add(Player player, String id, @Suggestion("types") String type, Particle particle, @Suggestion("numbers") int count, @Optional @Suggestion("numbers") int size) {
        //todo() tell them it can't be empty.
        if (type.isEmpty()) return;

        final ParticleKey particleKey = ParticleUtil.getParticleByName(type);

        //todo() tell them it can't be null.
        if (particleKey == null) return;

        Block block = player.getTargetBlockExact(player.getViewDistance());

        if (block == null || block.getType() == Material.AIR) {
            Messages.not_looking_at_block.sendMessage(player);

            return;
        }

        Location location = block.getLocation();

        IParticleBuilder builder = null;

        switch (particleKey) {
            case GENERIC -> builder = new GenericParticle(id, count, particleKey, particle, location.add(0.5, 1.0, 0.5));

            case SPIRAL, DOUBLE_SPIRAL -> builder = new SpiralParticle(id, count, size, particleKey, particle, location.add(0.5, 1.1, 0.5));
        }

        if (builder != null) {
            this.particleHandler.addParticleLocation(builder);

            Messages.location_added.sendMessage(player, new HashMap<>() {{
                put("{name}", id);
            }});
        }
    }
}