package com.badbones69.blockparticles.commands;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.api.enums.fountains.BPFountains;
import com.badbones69.blockparticles.api.enums.particles.BPParticles;
import com.badbones69.blockparticles.commands.envoys.types.admin.CommandReload;
import com.badbones69.blockparticles.commands.envoys.types.admin.CommandStats;
import com.badbones69.blockparticles.commands.envoys.types.player.CommandList;
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

    private final static @NotNull ParticleManager particleManager = plugin.getParticleManager();

    private final static @NotNull BukkitCommandManager<CommandSender> commandManager = BukkitCommandManager.create(plugin);

    /**
     * Loads commands.
     */
    public static void load() {
        new ArgumentRelations().build();

        commandManager.registerSuggestion(SuggestionKey.of("particles"), (sender, context) -> {
            List<String> completions = new ArrayList<>();

            for (BPParticles particle : BPParticles.values()) {
                completions.add(particle.name().toLowerCase());
            }

            for (BPFountains fountain : BPFountains.values()) {
                if (fountain != BPFountains.CUSTOM) {
                    completions.add(fountain.name().toLowerCase());
                }
            }

            particleManager.getCustomFountains().forEach(fountain -> completions.add(fountain.getName()));

            return completions;
        });

        commandManager.registerSuggestion(SuggestionKey.of("locations"), (sender, context) -> Methods.getLocations());

        commandManager.registerSuggestion(SuggestionKey.of("players"), (sender, context) -> plugin.getServer().getOnlinePlayers().stream().map(Player::getName).toList());

        commandManager.registerSuggestion(SuggestionKey.of("numbers"), (sender, context) -> {
            final List<String> numbers = new ArrayList<>();

            for (int i = 1; i <= 64; i++) numbers.add(String.valueOf(i));

            return numbers;
        });

        commandManager.registerArgument(PlayerBuilder.class, (sender, context) -> new PlayerBuilder(context));

        List.of(
                new CommandAdd(),
                new CommandRemove(),
                new CommandSet(),
                new CommandReload(),
                new CommandStats(),
                new CommandList(),
                new CommandHelp(),
                new CommandList()
        ).forEach(commandManager::registerCommand);
    }

    public static @NotNull BukkitCommandManager<CommandSender> getCommandManager() {
        return commandManager;
    }
}