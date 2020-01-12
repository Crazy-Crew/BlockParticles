package me.badbones69.blockparticles.commands;

import me.badbones69.blockparticles.Methods;
import me.badbones69.blockparticles.api.FileManager;
import me.badbones69.blockparticles.api.FileManager.Files;
import me.badbones69.blockparticles.api.ParticleManager;
import me.badbones69.blockparticles.api.enums.ParticleType;
import me.badbones69.blockparticles.api.enums.Particles;
import me.badbones69.blockparticles.controllers.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BPCommands implements CommandExecutor {
    
    private FileManager fileManager = FileManager.getInstance();
    private ParticleManager bp = ParticleManager.getInstance();
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (hasPermission(sender, "admin")) {
            if (args.length == 0) {// /bp
                sender.sendMessage(Methods.color("&cPlease do /blockparticle help for more information."));
                return true;
            } else {
                String prefix = Files.CONFIG.getFile().getString("settings.prefix");
                switch (args[0].toLowerCase()) {
                    case "help":
                        sender.sendMessage(Methods.color(prefix + "&6List of all Block Particle Commands."));
                        sender.sendMessage(Methods.color("&8- &6/bp help &3Lists all Block Particle Commands."));
                        sender.sendMessage(Methods.color("&8- &6/bp list &3Lists all Block Particle Locations."));
                        sender.sendMessage(Methods.color("&8- &6/bp add <id> &3Create a new Block Particle Location."));
                        sender.sendMessage(Methods.color("&8- &6/bp delete <id> &3Delete a Block Particle Location."));
                        sender.sendMessage(Methods.color("&8- &6/bp set <id> [type] &3Set the Block Particle Locations Particle."));
                        sender.sendMessage(Methods.color("&8- &6/bp types &3Shows all Types of Particles that can be used."));
                        sender.sendMessage(Methods.color("&8- &6/bp reload &3Reload all Block Particle Locations."));
                        return true;
                    case "reload":
                    case "r":
                        Methods.kill();
                        Methods.startParticles();
                        fileManager.setup(bp.getPlugin());
                        sender.sendMessage(Methods.color(prefix + "&3You have just reloaded the config.yml and block particles."));
                        return true;
                    case "show":
                        int items = bp.getFountainItem().size();
                        int blocks = bp.getParticleControl().getLocations().size();
                        sender.sendMessage(Methods.color(prefix + "&3There are &6" + items + " &3items in the List."));
                        sender.sendMessage(Methods.color(prefix + "&3There are &6" + blocks + " &3particles/fountains running."));
                        return true;
                    case "list":
                    case "l":
                        Methods.listLoc((Player) sender);
                        return true;
                    case "types":
                        sender.sendMessage(Methods.color(prefix + "&3List of all particle types."));
                        sender.sendMessage(Methods.color("&6&lParticles&8: &3Total " + Particles.getParticles(ParticleType.PARTICLE).size() + "."));
                        sender.sendMessage(Methods.color("&aSpiral&8, &aDoubleSpiral&8, &aCrit&8, &aBigCrit&8, &aFlame&8, &aBigFlame&8, &aVolcano" + "&8, &aFog&8, &aEnchant&8, &aStorm&8, &aChains&8, &aFireStorm&8, &aSnow&8, &aPotion&8, &aMusic&8, &aSpew&8," + "&aMagic&8, &aWitch&8, &aDoubleWitch&8, &aSnowStorm&8, &aFireSpew&8, &aFootPrint&8, &aWater&8, &aHappyVillager" + "&8, &aAngryVillager&8, &aMobSpawner&8, &aEnderSignal&8, &aRainbow&8," + "&aSnowBlast&8, &aHalo&8, &aSoulWell&8, &aBigSoulWell&8, &aLoveWell&8, &aBigLoveWell&8," + "&aFlameWheel&8, &aWitchTornado&8, &aLoveTornado"));
                        sender.sendMessage(Methods.color("&6&lFountains&8: &3Total " + Particles.getParticles(ParticleType.FOUNTAIN).size() + "."));
                        sender.sendMessage(Methods.color("&aGems&8, &aHalloween&8, &aHeads&8, &aPresents&8, &aMobs&8, &aFood&8, &aPokemon&8, &aMario"));
                        return true;
                    case "set":
                    case "s":
                        if (args.length == 2) {
                            for (String location : Methods.getLocations()) {
                                if (location.equalsIgnoreCase(args[1])) {
                                    bp.addSetCommandPlayer((Player) sender, location);
                                    GUI.openGUIPage1((Player) sender);
                                    return true;
                                }
                            }
                            sender.sendMessage(Methods.color(prefix + "&3There are no locations called &6" + args[1] + "&3."));
                        } else if (args.length >= 3) {
                            Methods.setLoc(sender, args[1], args[2]);
                        } else {
                            sender.sendMessage(Methods.color(prefix + "&c/bp set <id> [type]"));
                        }
                        return true;
                    case "add":
                    case "a":
                        if (args.length >= 2) {
                            Methods.addLoc((Player) sender, args[1]);
                        } else {
                            sender.sendMessage(Methods.color(prefix + "&c/bp add <id>"));
                        }
                        return true;
                    case "delete":
                    case "del":
                    case "d":
                    case "remove":
                        if (args.length >= 2) {
                            Methods.delLoc(sender, args[1]);
                        } else {
                            sender.sendMessage(Methods.color(prefix + "&c/bp delete <id>"));
                        }
                        return true;
                    default:
                        sender.sendMessage(Methods.color("&cPlease do /blockparticle help for more information."));
                        return true;
                }
            }
        }
        return false;
    }
    
    private boolean hasPermission(CommandSender sender, String node) {
        if (sender instanceof Player) {
            return sender.hasPermission("bparticles." + node);
        } else {
            return true;
        }
    }
    
}