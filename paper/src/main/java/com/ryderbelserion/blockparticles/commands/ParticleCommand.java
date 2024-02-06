package com.ryderbelserion.blockparticles.commands;

import com.ryderbelserion.blockparticles.BlockParticles;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticleCommand implements CommandExecutor, TabCompleter {

    private final BlockParticles plugin = BlockParticles.get();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("No sub-command is specified.");

            return true;
        }

        switch (args[0].toLowerCase()) {
            case "add" -> {
                Particle particle = Particle.valueOf(args[1]);

                Player player = (Player) sender;

                Block block = player.getTargetBlock(null, 5);

                if (block.getType() == Material.AIR) {
                    sender.sendMessage("Block cannot be air.");
                    return true;
                }

                this.plugin.getParticleManager().volcano(block.getLocation(), particle);

                return true;
            }

            case "remove" -> {
                Player player = (Player) sender;

                Block block = player.getTargetBlock(null, 5);

                this.plugin.getParticleManager().removeTask(block.getLocation());

                return true;
            }

            default -> {
                sender.sendMessage("Invalid command.");

                return true;
            }
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("add");
            completions.add("remove");

            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        } else if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "add" -> Arrays.stream(Particle.values()).toList().forEach(particle -> completions.add(particle.name()));
                case "remove" -> {

                }
            }

            return StringUtil.copyPartialMatches(args[1], completions, new ArrayList<>());
        }

        return new ArrayList<>();
    }
}