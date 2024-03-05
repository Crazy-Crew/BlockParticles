package com.ryderbelserion.blockparticles.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.meta.CommandMeta;
import com.ryderbelserion.blockparticles.platform.Sender;

public abstract class CommandEngine {

    public abstract CommandManager<Sender> getManager();

    public abstract Command.Builder<Sender> getRoot();

    //TODO() Add exception handler.
    //TODO() Add register command method
    //TODO() Add a method to get the root command.
    //TODO() Add a way to expose registered commands, args etc for the help generator.

    private Command.Builder<Sender> root() {
        return getManager().commandBuilder("blockparticles")
                .permission("blockparticles.access")
                .meta(CommandMeta.DESCRIPTION, "BlockParticles command. /blockparticles help")
                .handler(context -> {
                    context.getSender().sendMessage("<red>This is the help command.");

                    //TODO() Add some type of wrapper to open gui's
                });
    }

    protected void register() {

    }
}