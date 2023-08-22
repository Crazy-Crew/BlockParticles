package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.plugin.BlockParticlesLoader;
import com.badbones69.blockparticles.commands.ParticleCommand;
import com.ryderbelserion.ruby.paper.plugin.builder.commands.PaperCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private BlockParticlesLoader plugin;

    @Override
    public void onEnable() {
        this.plugin = new BlockParticlesLoader(this);
        this.plugin.enable();

        PaperCommandManager manager = this.plugin.getPaperPlugin().getManager();

        manager.setNamespace("blockparticles");

        manager.addCommand(new ParticleCommand());
    }

    @Override
    public void onDisable() {
        this.plugin.disable();
    }
}