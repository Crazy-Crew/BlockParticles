package com.badbones69.blockparticles.api.plugin;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.CrazyManager;
import com.ryderbelserion.ruby.minecraft.plugin.FancyLogger;
import com.ryderbelserion.ruby.other.config.FileManager;
import com.ryderbelserion.ruby.paper.PaperPlugin;

public class BlockParticlesPlugin extends InternalPlugin {

    private final BlockParticles plugin;

    private PaperPlugin paperPlugin;
    private CrazyManager crazyManager;
    private FileManager fileManager;

    public BlockParticlesPlugin(BlockParticles plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        super.enable();

        this.paperPlugin = new PaperPlugin(this.plugin);
        this.paperPlugin.enable(false);

        this.fileManager = new FileManager();

        this.crazyManager = new CrazyManager();
        this.crazyManager.load(true);
    }

    @Override
    public void disable() {
        super.disable();

        this.crazyManager.reload(true);
    }
    @Override
    public CrazyManager getCrazyManager() {
        return this.crazyManager;
    }

    @Override
    public FileManager getFileManager() {
        return this.fileManager;
    }

    @Override
    public FancyLogger getFancyLogger() {
        return this.paperPlugin.getFancyLogger();
    }

    @Override
    public PaperPlugin getPaperPlugin() {
        return this.paperPlugin;
    }

    @Override
    public BlockParticles getPlugin() {
        return this.plugin;
    }
}