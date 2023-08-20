package com.badbones69.blockparticles.plugin.builder.commands;

import com.badbones69.common.builder.commands.CommandEngine;
import com.badbones69.common.builder.commands.args.Argument;
import com.badbones69.blockparticles.plugin.PaperImpl;
import com.badbones69.blockparticles.plugin.registry.PaperProvider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.*;

public abstract class PaperCommandEngine extends Command implements CommandEngine {

    private final @NotNull PaperImpl paper = PaperProvider.get();

    private final LinkedList<PaperCommandEngine> subCommands = new LinkedList<>();

    private final LinkedList<Argument> requiredArgs = new LinkedList<>();
    private final LinkedList<Argument> optionalArgs = new LinkedList<>();

    public PaperCommandEngine(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    public abstract void perform(PaperCommandContext context, String[] args);

    public void execute(PaperCommandContext context, String[] args) {
        StringBuilder label = new StringBuilder(getLabel());

        if (!context.getArgs().isEmpty()) {
            for (PaperCommandEngine command : this.subCommands) {
                boolean isPresent = context.getArgs().stream().findFirst().isPresent();

                String arg = context.getArgs().stream().findFirst().get().toLowerCase();

                if (isPresent && arg.equalsIgnoreCase(command.getLabel())) {
                    label.append(" ").append(context.getArgs().get(0));

                    context.setLabel(label.toString());

                    command.execute(context, args);

                    return;
                }
            }
        }

        if (!validate(context)) return;

        perform(context, args);
    }

    private boolean validate(PaperCommandContext context) {
        if (context.getArgs().size() < this.requiredArgs.size()) {
            //context.reply(this.paper.getMessageKey().NOT_ENOUGH_ARGS());
            return false;
        }

        if (context.getArgs().size() > this.requiredArgs.size() + this.optionalArgs.size() || context.getArgs().size() > this.requiredArgs.size()) {
            //context.reply(this.paper.getMessageKey().TOO_MANY_ARGS());
            return false;
        }

        return true;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        List<String> arguments = Arrays.asList(args);

        PaperCommandContext context = new PaperCommandContext(
                sender,
                label,
                arguments
        );

        execute(context, args);

        return true;
    }

    private boolean value = true;

    @Override
    public void setVisible(boolean value) {
        this.value = value;
    }

    @Override
    public boolean isVisible() {
        return this.value;
    }

    public List<String> handleTabComplete(List<String> args) {
        if (args.size() == 1) {
            ArrayList<String> completions = new ArrayList<>();

            if (args.get(0).isEmpty()) {
                this.subCommands.forEach(sub -> {
                    if (!sub.isVisible()) return;

                    completions.add(sub.getName());
                });

                return completions;
            }

            return completions;
        }

        if (args.size() >= 2) {
            int relativeIndex = 2;
            int subCommandIndex = 0;

            PaperCommandEngine commandTab = this;

            while (!commandTab.subCommands.isEmpty()) {
                PaperCommandEngine findCommand = null;

                for (PaperCommandEngine sub : this.subCommands) {
                    if (sub.getName().equals(args.get(subCommandIndex).toLowerCase())) {
                        findCommand = sub;
                        break;
                    }
                }

                subCommandIndex++;
                if (findCommand != null) commandTab = findCommand; else break;
                relativeIndex++;
            }

            int argToComplete = args.size() + 1 - relativeIndex;

            if (commandTab.requiredArgs.size() >= argToComplete) {
                ArrayList<Argument> arguments = new ArrayList<>();

                arguments.addAll(commandTab.requiredArgs);
                arguments.addAll(commandTab.optionalArgs);

                ArrayList<String> possibleValues = new ArrayList<>();

                for (Argument argument : arguments) {
                    if (argument.order() == argToComplete) {
                        if (!argument.argumentType().getPossibleValues().isEmpty()) {
                            List<String> possibleArgs = argument.argumentType().getPossibleValues();

                            possibleValues = new ArrayList<>(possibleArgs);
                            break;
                        }
                    }
                }

                return Collections.unmodifiableList(possibleValues);
            }
        }

        return Collections.emptyList();
    }

    public void addCommand(PaperCommandEngine command) {
        if (hasCommand(command)) return;

        this.subCommands.add(command);
    }

    public void removeCommand(PaperCommandEngine command) {
        if (!hasCommand(command)) return;

        this.subCommands.remove(command);
    }

    public boolean hasCommand(PaperCommandEngine command) {
        return this.subCommands.contains(command);
    }

    public List<PaperCommandEngine> getCommands(PaperCommandEngine command) {
        return Collections.unmodifiableList(command.subCommands);
    }

    public List<Argument> getOptionalArgs(PaperCommandEngine command) {
        return command.requiredArgs;
    }

    public List<Argument> getRequiredArgs(PaperCommandEngine command) {
        return command.optionalArgs;
    }

    public void addRequiredArgument(PaperCommandEngine command, Argument argument) {
        command.requiredArgs.add(argument);
    }

    public void addOptionalArgument(PaperCommandEngine command, Argument argument) {
        command.optionalArgs.add(argument);
    }
}