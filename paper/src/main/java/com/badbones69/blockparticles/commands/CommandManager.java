package com.badbones69.blockparticles.commands;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.commands.particles.types.admin.CommandReload;
import com.badbones69.blockparticles.commands.particles.types.admin.particle.CommandCancel;
import com.badbones69.blockparticles.commands.particles.types.admin.particle.CommandCreate;
import com.badbones69.blockparticles.commands.particles.types.player.CommandHelp;
import com.badbones69.blockparticles.commands.relations.ArgumentRelations;
import com.badbones69.blockparticles.tasks.ParticleLoader;
import com.ryderbelserion.vital.paper.builders.PlayerBuilder;
import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import dev.triumphteam.cmd.core.argument.keyed.Argument;
import dev.triumphteam.cmd.core.argument.keyed.ArgumentKey;
import dev.triumphteam.cmd.core.suggestion.SuggestionKey;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandManager {

    private final static @NotNull BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private final static @NotNull ParticleLoader particleLoader = plugin.getParticleLoader();

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

        commandManager.registerSuggestion(SuggestionKey.of("names"), (sender, context) -> new ArrayList<>(particleLoader.getParticles().keySet()));

        commandManager.registerSuggestion(SuggestionKey.of("players"), (sender, context) -> plugin.getServer().getOnlinePlayers().stream().map(Player::getName).toList());

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

        commandManager.registerSuggestion(SuggestionKey.of("uuids"), (sender, context) -> {
            final List<String> uuids = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                uuids.add(UUID.randomUUID().toString());
            }

            return uuids;
        });

        commandManager.registerNamedArguments(ArgumentKey.of("bp_add"),
                Argument.forType(ParticleKey.class).name("key").build(),
                Argument.forType(Particle.class).name("particle").build(),
                Argument.forInt().name("amount").build()
        );

        commandManager.registerArgument(PlayerBuilder.class, (sender, context) -> new PlayerBuilder(context));

        List.of(
                new CommandCreate(),
                new CommandCancel(),
                new CommandReload(),
                new CommandHelp()
                //new CommandAdd()
        ).forEach(commandManager::registerCommand);
    }

    public static @NotNull BukkitCommandManager<CommandSender> getCommandManager() {
        return commandManager;
    }
}