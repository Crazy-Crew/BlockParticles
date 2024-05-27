package com.badbones69.blockparticles;

import com.badbones69.blockparticles.commands.CommandManager;
import com.badbones69.blockparticles.tasks.ParticleLoader;
import com.ryderbelserion.vital.paper.VitalPaper;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private Server paperServer;
    private ParticleLoader particleLoader;
    
    @Override
    public void onEnable() {
        new VitalPaper(this);

        this.paperServer = new Server(getDataFolder(), getLogger());
        this.paperServer.apply();

        this.particleLoader = new ParticleLoader();
        this.particleLoader.load(false);

        CommandManager.load();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public final ParticleLoader getParticleLoader() {
        return this.particleLoader;
    }

    public final Server getPaperServer() {
        return this.paperServer;
    }
}