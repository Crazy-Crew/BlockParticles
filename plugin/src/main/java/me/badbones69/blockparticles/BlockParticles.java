package me.badbones69.blockparticles;

import me.badbones69.blockparticles.api.FileManager;
import me.badbones69.blockparticles.api.FileManager.Files;
import me.badbones69.blockparticles.api.ParticleManager;
import me.badbones69.blockparticles.api.enums.ParticleType;
import me.badbones69.blockparticles.api.enums.Particles;
import me.badbones69.blockparticles.controllers.Fountains;
import me.badbones69.blockparticles.controllers.GUI;
import me.badbones69.blockparticles.controllers.Metrics;
import me.badbones69.blockparticles.events.Events_v1_11_R1_Down;
import me.badbones69.blockparticles.events.Events_v1_12_R1_Up;
import me.badbones69.blockparticles.hook.HeadDatabaseHook;
import me.badbones69.blockparticles.multisupport.Version;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockParticles extends JavaPlugin {

    private Boolean updateChecker = false;
    private FileManager fileManager = FileManager.getInstance();
    private ParticleManager bp = ParticleManager.getInstance();

    @Override
    public void onDisable() {
        Methods.kill();
    }

    @Override
    public void onEnable() {
        fileManager.logInfo(true).setup(this);
        bp.load();
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new GUI(), this);
        pm.registerEvents(new Methods(), this);
        pm.registerEvents(new Fountains(), this);
        new HeadDatabaseHook();
        if (Version.getCurrentVersion().isNewer(Version.v1_11_R1)) {
            pm.registerEvents(new Events_v1_12_R1_Up(), this);
        } else {
            pm.registerEvents(new Events_v1_11_R1_Down(), this);
        }
        new Metrics(this);
        new BukkitRunnable() {
            @Override
            public void run() {
                Methods.startParticles();
            }
        }.runTaskLater(bp.getPlugin(), 200);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("blockparticle") || commandLabel.equalsIgnoreCase("bp")) {
            String Prefix = Files.CONFIG.getFile().getString("settings.prefix");
            if (sender instanceof Player) if (Methods.permCheck((Player) sender, "Admin")) return true;
            if (args.length == 0) {
                sender.sendMessage(Methods.color("&cPlease do /blockparticle help for more information."));
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("show")) {
                    int items = bp.getFountainItem().size();
                    int Blocks = bp.getParticleControl().getLocations().size();
                    sender.sendMessage(Methods.color(Prefix + "&3There are &6" + items + " &3items in the List."));
                    sender.sendMessage(Methods.color(Prefix + "&3There are &6" + Blocks + " &3particles/fountains running."));
                    return true;
                }
                if (args[0].equalsIgnoreCase("types")) {
                    int p = Particles.getParticles(ParticleType.PARTICLE).size();
                    int f = Particles.getParticles(ParticleType.FOUNTAIN).size();
                    sender.sendMessage(Methods.color(Prefix + "&3List of all particle types."));
                    sender.sendMessage(Methods.color("&6&lParticles&8: &3Total " + p + "."));
                    sender.sendMessage(Methods.color("&aSpiral&8, &aDoubleSpiral&8, &aCrit&8, &aBigCrit&8, &aFlame&8, &aBigFlame&8, &aVolcano" + "&8, &aFog&8, &aEnchant&8, &aStorm&8, &aChains&8, &aFireStorm&8, &aSnow&8, &aPotion&8, &aMusic&8, &aSpew&8," + "&aMagic&8, &aWitch&8, &aDoubleWitch&8, &aSnowStorm&8, &aFireSpew&8, &aFootPrint&8, &aWater&8, &aHappyVillager" + "&8, &aAngryVillager&8, &aMobSpawner&8, &aEnderSignal&8, &aRainbow&8," + "&aSnowBlast&8, &aHalo&8, &aSoulWell&8, &aBigSoulWell&8, &aLoveWell&8, &aBigLoveWell&8," + "&aFlameWheel&8, &aWitchTornado&8, &aLoveTornado"));
                    sender.sendMessage(Methods.color("&6&lFountains&8: &3Total " + f + "."));
                    sender.sendMessage(Methods.color("&aGems&8, &aHalloween&8, &aHeads&8, &aPresents&8, &aMobs&8, &aFood&8, &aPokemon&8, &aMario"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(Methods.color(Prefix + "&6List of all Block Particle Commands."));
                    sender.sendMessage(Methods.color("&8- &6/bp help &3Lists all Block Particle Commands."));
                    sender.sendMessage(Methods.color("&8- &6/bp list &3Lists all Block Particle Locations."));
                    sender.sendMessage(Methods.color("&8- &6/bp add <id> &3Create a new Block Particle Location."));
                    sender.sendMessage(Methods.color("&8- &6/bp delete <id> &3Delete a Block Particle Location."));
                    sender.sendMessage(Methods.color("&8- &6/bp set <id> <Type> &3Set the Block Particle Locations Particle."));
                    sender.sendMessage(Methods.color("&8- &6/bp types &3Shows all Types of Particles that can be used."));
                    sender.sendMessage(Methods.color("&8- &6/bp reload &3Reload all Block Particle Locations."));
                    return true;
                }
                if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l")) {
                    Methods.listLoc((Player) sender);
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("r")) {
                    Files.CONFIG.relaodFile();
                    Methods.kill();
                    Methods.startParticles();
                    sender.sendMessage(Methods.color(Prefix + "&3You have just reloaded the config.yml and block particles."));
                    return true;
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("s")) {
                    for (String l : Methods.getLocations()) {
                        if (l.equalsIgnoreCase(args[1])) {
                            bp.addSetCommandPlayer((Player) sender, l);
                            GUI.openGUIPage1((Player) sender);
                            return true;
                        }
                    }
                    sender.sendMessage(Methods.color(Prefix + "&3There are no locations called &6" + args[1] + "&3."));
                    return true;
                }
                if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("a")) {
                    Methods.addLoc((Player) sender, args[1]);
                    return true;
                }
                if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("r")) {
                    Methods.delLoc(sender, args[1]);
                    return true;
                }
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("s")) {
                    Methods.setLoc(sender, args[1], args[2]);
                    return true;
                }
            }

            sender.sendMessage(Methods.color("&cPlease do /blockparticles help for more information."));
            return true;
        }
        return false;
    }

}