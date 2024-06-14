package com.badbones69.blockparticles.commands.particles;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.tasks.particles.ParticleManager;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Description;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Command(value = "blockparticles", alias = {"blockparticle", "bparticles", "bparticle", "bp"})
@Description("Adds particles to blocks!")
public abstract class BaseCommand {

    protected @NotNull final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    protected @NotNull final ParticleManager manager = plugin.getParticleManager();

}