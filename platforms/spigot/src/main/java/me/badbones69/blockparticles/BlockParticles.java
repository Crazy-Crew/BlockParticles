package me.badbones69.blockparticles;

import me.badbones69.blockparticles.api.FileManager;
import me.badbones69.blockparticles.api.CrazyManager;
import me.badbones69.blockparticles.commands.BPCommands;
import me.badbones69.blockparticles.commands.BPTab;
import me.badbones69.blockparticles.controllers.Fountains;
import me.badbones69.blockparticles.controllers.ParticlePicker;
import me.badbones69.blockparticles.events.FountainListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Objects;

public class BlockParticles extends JavaPlugin {

    private final FileManager fileManager = FileManager.getInstance();
    private final CrazyManager bp = CrazyManager.getInstance();
    
    @Override
    public void onEnable() {
        fileManager.logInfo(true).setup(this);

        bp.load();

        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new ParticlePicker(), this);
        pm.registerEvents(new Methods(), this);
        pm.registerEvents(new Fountains(), this);

        Objects.requireNonNull(getCommand("blockparticle")).setExecutor(new BPCommands());
        Objects.requireNonNull(getCommand("blockparticle")).setTabCompleter(new BPTab());

        pm.registerEvents(new FountainListener(), this);
        new BukkitRunnable() {
            @Override
            public void run() {
                Methods.startParticles();
            }
        }.runTaskLater(bp.getPlugin(), 200);
    }

    @Override
    public void onDisable() {
        Methods.kill();
    }
}