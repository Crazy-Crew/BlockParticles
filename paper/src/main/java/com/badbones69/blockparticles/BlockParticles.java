package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.api.builders.menus.MainMenu;
import com.badbones69.blockparticles.commands.CommandManager;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.controllers.ParticleControl;
import com.badbones69.blockparticles.events.ItemPickUp;
import com.ryderbelserion.vital.paper.VitalPaper;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockParticles extends JavaPlugin {

    private ParticleManager particleManager;
    private ParticleControl particleControl;
    
    @Override
    public void onEnable() {
        new VitalPaper(this);

        Server server = new Server(getDataFolder(), getLogger());
        server.apply();

        this.particleManager = new ParticleManager();
        this.particleManager.load();

        this.particleControl = this.particleManager.getParticleControl();

        getServer().getPluginManager().registerEvents(new MainMenu(), this);
        getServer().getPluginManager().registerEvents(new Methods(), this);
        getServer().getPluginManager().registerEvents(new Fountains(), this);
        getServer().getPluginManager().registerEvents(new ItemPickUp(), this);

        // Load the commands.
        CommandManager.load();

        new BukkitRunnable() {
            @Override
            public void run() {
                Methods.startParticles();
            }
        }.runTaskLater(this, 200);
    }

    @Override
    public void onDisable() {
        Methods.kill();
    }

    public final ParticleManager getParticleManager() {
        return this.particleManager;
    }

    public final ParticleControl getParticleControl() {
        return this.particleControl;
    }

    private void registerCommand(final PluginCommand command, final CommandExecutor executor, final TabCompleter tabCompleter) {
        if (command != null) {
            if (executor != null) {
                command.setExecutor(executor);

                if (tabCompleter != null) command.setTabCompleter(tabCompleter);
            }
        }
    }
}