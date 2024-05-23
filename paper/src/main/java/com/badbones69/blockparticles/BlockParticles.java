package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.FileManager;
import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.commands.BPCommands;
import com.badbones69.blockparticles.commands.BPTab;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.controllers.GUI;
import com.badbones69.blockparticles.events.Events_v1_12_R1_Up;
import com.badbones69.blockparticles.hook.HeadDatabaseHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockParticles extends JavaPlugin {

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
        pm.registerEvents(new Events_v1_12_R1_Up(), this);
        new BukkitRunnable() {
            @Override
            public void run() {
                Methods.startParticles();
            }
        }.runTaskLater(bp.getPlugin(), 200);
    }
    
}