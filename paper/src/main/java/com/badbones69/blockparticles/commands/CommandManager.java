package com.badbones69.blockparticles.commands;

import com.badbones69.blockparticles.commands.particles.types.admin.CommandDelete;
import com.badbones69.blockparticles.commands.particles.types.admin.CommandReload;
import com.badbones69.blockparticles.commands.particles.types.admin.CommandSet;
import com.badbones69.blockparticles.commands.particles.types.player.CommandHelp;
import com.badbones69.blockparticles.commands.relations.ArgumentRelations;
import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.tasks.particles.ParticleManager;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.ryderbelserion.vital.paper.builders.PlayerBuilder;
import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import dev.triumphteam.cmd.core.suggestion.SuggestionKey;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandManager {

    private final static @NotNull BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final static @NotNull ParticleManager particleManager = plugin.getParticleManager();

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

        commandManager.registerSuggestion(SuggestionKey.of("integers"), (sender, context) -> {
            final List<String> numbers = new ArrayList<>();

            for (int i = 1; i <= 10; i++) numbers.add(String.valueOf(i));

            return numbers;
        });

        commandManager.registerSuggestion(SuggestionKey.of("doubles"), (sender, context) -> {
            final List<String> numbers = new ArrayList<>();

            for (int i = 1; i <= 15; i++) {
                numbers.add(String.valueOf(i / 10.0));
            }

            return numbers;
        });

        commandManager.registerSuggestion(SuggestionKey.of("files"), (sender, context) -> particleManager.getActiveParticles().keySet().stream().toList());

        // Used in /bp set
        commandManager.registerSuggestion(SuggestionKey.of("ids"), (sender, context) -> {
            final List<String> uuids = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                uuids.add(UUID.randomUUID().toString().substring(0, 6));
            }

            return uuids;
        });

        commandManager.registerArgument(PlayerBuilder.class, (sender, context) -> new PlayerBuilder(context));

        List.of(
                new CommandReload(),
                new CommandDelete(),
                new CommandHelp(),
                new CommandSet()
        ).forEach(commandManager::registerCommand);
    }

    public static @NotNull BukkitCommandManager<CommandSender> getCommandManager() {
        return commandManager;
    }
}