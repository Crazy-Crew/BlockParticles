package com.badbones69.blockparticles.commands;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.plugin.registry.BlockParticlesProvider;
import com.badbones69.blockparticles.commands.admin.ReloadCommand;
import com.badbones69.blockparticles.commands.player.HelpCommand;
import com.ryderbelserion.ruby.paper.plugin.builder.commands.PaperCommandContext;
import com.ryderbelserion.ruby.paper.plugin.builder.commands.PaperCommandEngine;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;

public class ParticleCommand extends PaperCommandEngine {

    private final BlockParticles plugin = BlockParticlesProvider.get().getPlugin();

    public ParticleCommand() {
        super("particles", "The base command for block particles", "particles", Collections.emptyList());

        addCommand(new ReloadCommand(), false);
        addCommand(new HelpCommand(), false);
    }

    @Override
    public void perform(PaperCommandContext context, String[] args) {
        this.plugin.getServer().dispatchCommand(context.getSender(), "particles help 1");
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return handleTabComplete(List.of(args));
    }
}