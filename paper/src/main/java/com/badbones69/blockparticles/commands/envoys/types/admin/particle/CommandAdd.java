package com.badbones69.blockparticles.commands.envoys.types.admin.particle;

import com.badbones69.blockparticles.api.builders.particles.types.SpiralParticle;
import com.badbones69.blockparticles.api.enums.Messages;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.builders.particles.types.GenericParticle;
import com.badbones69.blockparticles.api.interfaces.IParticleBuilder;
import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.CommandFlags;
import dev.triumphteam.cmd.core.annotations.Flag;
import dev.triumphteam.cmd.core.annotations.NamedArguments;
import dev.triumphteam.cmd.core.annotations.Suggestion;
import dev.triumphteam.cmd.core.argument.keyed.Arguments;
import dev.triumphteam.cmd.core.argument.keyed.Flags;
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
    @CommandFlags({
       @Flag(longFlag = "inner", argument = int.class, suggestion = "integers"),
       @Flag(longFlag = "outer", argument = int.class, suggestion = "integers"),
       @Flag(longFlag = "height", argument = double.class, suggestion = "doubles")
    })
    @NamedArguments("bp_add")
    public void add(Player player, @Suggestion("uuids") String id, Arguments arguments, Flags flags) {
        final Block block = player.getTargetBlockExact(player.getViewDistance());

        if (block == null || block.getType() == Material.AIR) {
            Messages.not_looking_at_block.sendMessage(player);

            return;
        }

        final ParticleKey particleKey = arguments.getArgument("key", ParticleKey.class).orElse(ParticleKey.GENERIC);
        final Particle particle = arguments.getArgument("particle", Particle.class).orElse(Particle.FLAME);
        final int amount = arguments.getArgument("amount", Integer.class).orElse(1);
        final Location location = block.getLocation();

        IParticleBuilder builder = null;

        switch (particleKey) {
            case SPIRAL, DOUBLE_SPIRAL -> {
                boolean isPresent = flags.getFlagValue("inner").isPresent();

                if (isPresent) {
                    builder = new SpiralParticle(id, amount, 0, 0, 0, particleKey, particle, location);
                }
            }

            case GENERIC -> builder = new GenericParticle(id, amount, particleKey, particle, location);
        }

        if (builder != null) {
            this.particleHandler.addParticleLocation(builder);

            Messages.location_added.sendMessage(player, new HashMap<>() {{
                put("{name}", id);
            }});
        }
    }
}