package com.badbones69.blockparticles.commands;

import com.badbones69.blockparticles.api.enums.BPParticles;
import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.api.enums.BPFountains;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BPTab implements TabCompleter {
    
    private ParticleManager bp = ParticleManager.getInstance();
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLable, String[] args) {
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
        } else if (args.length == 2) {// /bp arg1
            switch (args[0].toLowerCase()) {
                case "set":
                case "add":
                case "delete":
                    completions.addAll(Methods.getLocations());
                    break;
            }
            return StringUtil.copyPartialMatches(args[1], completions, new ArrayList<>());
        } else {// /bp arg1 arg2
            if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("add")) {
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