package com.badbones69.blockparticles.commands.envoys;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.ParticleManager;
import dev.triumphteam.cmd.core.annotations.Command;
import dev.triumphteam.cmd.core.annotations.Description;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Command(value = "blockparticles", alias = {"blockparticle", "bparticles", "bparticle", "bp"})
@Description("Adds particles to blocks!")
public abstract class BaseCommand {

    protected @NotNull final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    protected @NotNull final ParticleManager particleManager = this.plugin.getParticleManager();

}