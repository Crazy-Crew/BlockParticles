package com.badbones69.blockparticles.plugin.builder.commands;

import com.badbones69.common.builder.commands.args.Argument;
import com.badbones69.blockparticles.plugin.PaperImpl;
import com.badbones69.blockparticles.plugin.registry.PaperProvider;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaperCommandManager {

    private final @NotNull PaperImpl paper = PaperProvider.get();

    private final @NotNull JavaPlugin plugin = this.paper.getPlugin();

    private final ConcurrentHashMap<String, PaperCommandEngine> commands = new ConcurrentHashMap<>();

    private String namespace;

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void addCommand(PaperCommandEngine command) {
        add(command);
    }

    public void removeCommand(PaperCommandEngine command) {
        if (!hasCommand(command.getName())) return;

        Command map = this.plugin.getServer().getCommandMap().getCommand(command.getName());

        if (map != null) map.unregister(this.plugin.getServer().getCommandMap());

        if (!command.getCommands(command).isEmpty()) {
            command.getCommands(command).forEach(other -> {
                List<Argument> optional = command.getOptionalArgs(other);
                List<Argument> required = command.getRequiredArgs(other);

                if (!optional.isEmpty()) optional.clear();

                if (!required.isEmpty()) required.clear();
            });

            command.removeCommand(command);
        }

        this.commands.remove(command.getName());
    }

    public boolean hasCommand(String label) {
        return this.commands.containsKey(label);
    }

    private void add(PaperCommandEngine command) {
        if (hasCommand(command.getName())) return;

        if (!command.isVisible()) {
            if (hasCommand(command.getName())) removeCommand(command);
            return;
        }

        this.commands.put(command.getName(), command);

        // Add it to the command map.
        this.plugin.getServer().getCommandMap().register(this.namespace, command);
    }

    public Map<String, PaperCommandEngine> getCommands() {
        return Collections.unmodifiableMap(this.commands);
    }
}