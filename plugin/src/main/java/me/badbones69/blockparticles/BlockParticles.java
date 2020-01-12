package me.badbones69.blockparticles;

import me.badbones69.blockparticles.api.FileManager;
import me.badbones69.blockparticles.api.ParticleManager;
import me.badbones69.blockparticles.commands.BPCommands;
import me.badbones69.blockparticles.commands.BPTab;
import me.badbones69.blockparticles.controllers.Fountains;
import me.badbones69.blockparticles.controllers.GUI;
import me.badbones69.blockparticles.controllers.Metrics;
import me.badbones69.blockparticles.events.Events_v1_11_R1_Down;
import me.badbones69.blockparticles.events.Events_v1_12_R1_Up;
import me.badbones69.blockparticles.hook.HeadDatabaseHook;
import me.badbones69.blockparticles.multisupport.Version;
import org.bukkit.Bukkit;
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
        getCommand("blockparticle").setExecutor(new BPCommands());
        getCommand("blockparticle").setTabCompleter(new BPTab());
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
    
}