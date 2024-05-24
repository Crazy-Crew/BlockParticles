package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleHandler;
import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.controllers.ParticleControl;
import com.ryderbelserion.vital.paper.VitalPaper;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private ParticleHandler particleHandler;

    private ParticleManager particleManager;
    private ParticleControl particleControl;
    
    @Override
    public void onEnable() {
        new VitalPaper(this);

        Server server = new Server(getDataFolder(), getLogger());
        server.apply();

        this.particleHandler = new ParticleHandler();
        this.particleHandler.load(true);

        //this.particleManager = new ParticleManager();
        //this.particleManager.load();

        //this.particleControl = new Particles();

        //getServer().getPluginManager().registerEvents(new MainMenu(), this);
        //getServer().getPluginManager().registerEvents(new Methods(), this);
        //getServer().getPluginManager().registerEvents(new Fountains(), this);
        //getServer().getPluginManager().registerEvents(new ItemPickUp(), this);

        // Load the commands.
        //CommandManager.load();

        /*new BukkitRunnable() {
            @Override
            public void run() {
                Methods.startParticles();
            }
        }.runTaskLater(this, 200);*/
    }

    @Override
    public void onDisable() {
        //Methods.kill();
    }

    public final ParticleHandler getParticleHandler() {
        return this.particleHandler;
    }

    public final ParticleManager getParticleManager() {
        return this.particleManager;
    }

    public final ParticleControl getParticleControl() {
        return this.particleControl;
    }
}