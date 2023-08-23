package com.badbones69.blockparticles.commands.admin;

import com.ryderbelserion.ruby.other.commands.args.Argument;
import com.ryderbelserion.ruby.other.commands.args.types.IntArgument;
import com.ryderbelserion.ruby.paper.plugin.commands.PaperCommandContext;
import com.ryderbelserion.ruby.paper.plugin.commands.PaperCommandEngine;
import com.ryderbelserion.ruby.paper.plugin.commands.reqs.PaperRequirements;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;

public class ReloadCommand extends PaperCommandEngine {

    public ReloadCommand() {
        super("reload", "The reload commands for block particles", "particles reload", Collections.emptyList());

        addRequiredArgument(this, new Argument("page", 0, new IntArgument(5)));

        this.paperRequirements = new PaperRequirements(
                true,
                null,
                "blockparticles.reload"
        );
    }

    @Override
    public void perform(PaperCommandContext context, String[] args) {
        context.reply("<red>This is a reload command</red>");
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return handleTabComplete(List.of(args));
    }
}