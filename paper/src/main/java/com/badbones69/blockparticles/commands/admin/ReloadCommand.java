package com.badbones69.blockparticles.commands.admin;

import com.ryderbelserion.ruby.paper.plugin.builder.commands.PaperCommandContext;
import com.ryderbelserion.ruby.paper.plugin.builder.commands.PaperCommandEngine;
import java.util.Collections;

public class ReloadCommand extends PaperCommandEngine {

    public ReloadCommand() {
        super("reload", "The reload commands for block particles", "blockparticles:particles reload", Collections.emptyList());
    }

    @Override
    public void perform(PaperCommandContext context, String[] args) {
        context.reply("<red>This is a reload command</red>");
    }
}