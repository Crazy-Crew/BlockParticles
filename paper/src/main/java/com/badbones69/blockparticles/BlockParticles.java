package com.badbones69.blockparticles;

import com.badbones69.blockparticles.commands.CommandManager;
import com.badbones69.blockparticles.tasks.particles.ParticleManager;
import com.ryderbelserion.vital.paper.VitalPaper;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private ParticleManager particleManager;
    private Server paperServer;

    @Override
    public void onEnable() {
        new VitalPaper(this);

        this.paperServer = new Server(getDataFolder(), getLogger());
        this.paperServer.apply();

        this.particleManager = new ParticleManager();
        this.particleManager.load(true);

        CommandManager.load();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public final ParticleManager getParticleManager() {
        return this.particleManager;
    }

    public final Server getPaperServer() {
        return this.paperServer;
    }
}