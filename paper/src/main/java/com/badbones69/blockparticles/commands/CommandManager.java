package com.badbones69.blockparticles.commands;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.ParticleHandler;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.commands.envoys.types.admin.CommandReload;
import com.badbones69.blockparticles.commands.envoys.types.admin.CommandStats;
import com.badbones69.blockparticles.commands.envoys.types.admin.particle.CommandAdd;
import com.badbones69.blockparticles.commands.envoys.types.admin.particle.CommandRemove;
import com.badbones69.blockparticles.commands.envoys.types.admin.particle.CommandSet;
import com.badbones69.blockparticles.commands.envoys.types.player.CommandHelp;
import com.badbones69.blockparticles.commands.relations.ArgumentRelations;
import com.ryderbelserion.vital.paper.builders.PlayerBuilder;
import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import dev.triumphteam.cmd.core.suggestion.SuggestionKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final static @NotNull BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final static @NotNull ParticleHandler particleHandler = plugin.getParticleHandler();

    private final static @NotNull BukkitCommandManager<CommandSender> commandManager = BukkitCommandManager.create(plugin);

    /**
     * Loads commands.
     */
    public static void load() {
        new ArgumentRelations().build();

        commandManager.registerSuggestion(SuggestionKey.of("types"), (sender, context) -> {
            List<String> completions = new ArrayList<>();

            for (ParticleKey particle : ParticleKey.values()) {
                completions.add(particle.getParticleName());
            }

            return completions;
        });

        commandManager.registerSuggestion(SuggestionKey.of("ids"), (sender, context) -> {
            List<String> completions = new ArrayList<>();

            particleHandler.getParticles().keySet().forEach(particle -> completions.add(particle.toLowerCase()));

            return completions;
        });

        commandManager.registerSuggestion(SuggestionKey.of("players"), (sender, context) -> plugin.getServer().getOnlinePlayers().stream().map(Player::getName).toList());

        commandManager.registerSuggestion(SuggestionKey.of("numbers"), (sender, context) -> {
            final List<String> numbers = new ArrayList<>();

            for (int i = 1; i <= 64; i++) numbers.add(String.valueOf(i));

            return numbers;
        });

        commandManager.registerArgument(PlayerBuilder.class, (sender, context) -> new PlayerBuilder(context));

        List.of(
                new CommandRemove(),
                new CommandReload(),
                new CommandStats(),
                new CommandHelp(),
                new CommandAdd(),
                new CommandSet()
        ).forEach(commandManager::registerCommand);
    }

    public static @NotNull BukkitCommandManager<CommandSender> getCommandManager() {
        return commandManager;
    }
}