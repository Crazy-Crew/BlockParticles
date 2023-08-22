package com.badbones69.blockparticles.commands;

import com.badbones69.blockparticles.commands.admin.ReloadCommand;
import com.ryderbelserion.ruby.paper.plugin.builder.commands.PaperCommandContext;
import com.ryderbelserion.ruby.paper.plugin.builder.commands.PaperCommandEngine;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;

public class ParticleCommand extends PaperCommandEngine {


    public ParticleCommand() {
        super("particles", "The base command for block particles", "blockparticles:particles", Collections.emptyList());

        addCommand(new ReloadCommand());
    }

    @Override
    public void perform(PaperCommandContext context, String[] args) {
        context.reply("<green>This is the base command.</green>");
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return handleTabComplete(List.of(args));
    }
}