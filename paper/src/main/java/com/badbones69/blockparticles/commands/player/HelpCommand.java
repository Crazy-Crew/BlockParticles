package com.badbones69.blockparticles.commands.player;

import com.ryderbelserion.ruby.other.commands.args.Argument;
import com.ryderbelserion.ruby.other.commands.args.types.IntArgument;
import com.ryderbelserion.ruby.paper.plugin.commands.PaperCommandContext;
import com.ryderbelserion.ruby.paper.plugin.commands.PaperCommandEngine;
import com.ryderbelserion.ruby.paper.plugin.commands.PaperCommandHelpEntry;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;

public class HelpCommand extends PaperCommandEngine {

    public HelpCommand() {
        super("help", "The help command for block particles", "particles help", Collections.emptyList());

        addRequiredArgument(this, new Argument("page", 0, new IntArgument(3)));
    }

    @Override
    public void perform(PaperCommandContext context, String[] args) {
        PaperCommandHelpEntry helpEntry = new PaperCommandHelpEntry();

        helpEntry.showHelp(context);
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return handleTabComplete(List.of(args));
    }
}