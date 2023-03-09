package me.badbones69.blockparticles.commands;

import me.badbones69.blockparticles.Methods;
import me.badbones69.blockparticles.api.CrazyManager;
import me.badbones69.blockparticles.api.enums.BPFountains;
import me.badbones69.blockparticles.api.enums.BPParticles;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BPTab implements TabCompleter {
    
    private final CrazyManager bp = CrazyManager.getInstance();
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {// /bp
            if (sender.hasPermission("bparticles.admin")) {
                completions.addAll(Arrays.asList(
                "help",
                "reload",
                "list",
                "types",
                "set",
                "add",
                "delete"));
            }
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        } else if (args.length == 2) { // /bp arg1
            switch (args[0].toLowerCase()) {
                case "set", "add", "delete" -> completions.addAll(Methods.getLocations());
            }
            return StringUtil.copyPartialMatches(args[1], completions, new ArrayList<>());
        } else {// /bp arg1 arg2
            if (args[0].equalsIgnoreCase("set")) {
                for (BPParticles particle : BPParticles.values()) {
                    completions.add(particle.name().toLowerCase());
                }
                for (BPFountains fountain : BPFountains.values()) {
                    if (fountain != BPFountains.CUSTOM) {
                        completions.add(fountain.name().toLowerCase());
                    }
                }
                bp.getCustomFountains().forEach(fountain -> completions.add(fountain.getName()));
            }

            return StringUtil.copyPartialMatches(args[2], completions, new ArrayList<>());
        }
    }
}