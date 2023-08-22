package com.badbones69.blockparticles.api.plugin;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.CrazyManager;
import com.badbones69.blockparticles.api.config.ConfigManager;
import com.badbones69.blockparticles.api.plugin.registry.BlockParticlesRegistry;
import com.ryderbelserion.ruby.minecraft.plugin.FancyLogger;
import com.ryderbelserion.ruby.other.config.FileManager;
import com.ryderbelserion.ruby.paper.PaperPlugin;

public abstract class BlockParticlesPlugin {

    public abstract ConfigManager getConfigManager();

    public abstract CrazyManager getCrazyManager();

    public abstract FileManager getFileManager();

    public abstract FancyLogger getFancyLogger();

    public abstract PaperPlugin getPaperPlugin();

    public abstract BlockParticles getPlugin();


    public void enable() {
        BlockParticlesRegistry.start(this);
    }

    public void disable() {
        BlockParticlesRegistry.stop();
    }
}