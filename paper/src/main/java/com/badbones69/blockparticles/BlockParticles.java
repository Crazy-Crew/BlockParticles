package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleHandler;
import com.badbones69.blockparticles.commands.CommandManager;
import com.ryderbelserion.vital.paper.VitalPaper;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private ParticleHandler particleHandler;
    
    @Override
    public void onEnable() {
        new VitalPaper(this);

        Server server = new Server(getDataFolder(), getLogger());
        server.apply();

        this.particleHandler = new ParticleHandler();
        this.particleHandler.load(false);

        CommandManager.load();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public final ParticleHandler getParticleHandler() {
        return this.particleHandler;
    }
}