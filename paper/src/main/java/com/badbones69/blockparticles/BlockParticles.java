package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.commands.BPCommands;
import com.badbones69.blockparticles.commands.BPTab;
import com.badbones69.blockparticles.listeners.FountainListener;
import com.badbones69.blockparticles.listeners.HeadDatabaseListener;
import com.badbones69.blockparticles.listeners.ParticleListener;
import com.ryderbelserion.fusion.core.api.support.ModSupport;
import com.ryderbelserion.fusion.paper.FusionPaper;
import com.ryderbelserion.fusion.paper.files.PaperFileManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import java.nio.file.Path;
import java.util.Locale;

public class BlockParticles extends JavaPlugin {

    @ApiStatus.Internal
    public static BlockParticles getPlugin() {
        return JavaPlugin.getPlugin(BlockParticles.class);
    }

    private final long startTime;

    public BlockParticles() {
        this.startTime = System.nanoTime();
    }

    private ParticleManager particleManager;
    private PaperFileManager fileManager;
    private FusionPaper fusion;

    @Override
    public void onEnable() {
        this.fusion = new FusionPaper(this);
        this.fusion.init();

        this.fileManager = this.fusion.getFileManager();

        final Path path = getDataPath();

        this.fileManager.addPaperFile(path.resolve("config.yml")).addPaperFile(path.resolve("data.yml"));

        this.particleManager = new ParticleManager();

        final PluginManager pluginManager = getServer().getPluginManager();

        if (this.fusion.isModReady(ModSupport.head_database)) {
            pluginManager.registerEvents(new HeadDatabaseListener(), this);
        }

        pluginManager.registerEvents(new FountainListener(), this);
        pluginManager.registerEvents(new ParticleListener(), this);

        this.particleManager.load();

        Methods.startParticles();

        final PluginCommand command = getCommand("blockparticle");

        if (command != null) {
            command.setExecutor(new BPCommands());

            command.setTabCompleter(new BPTab());
        }

        getComponentLogger().info("Done ({})!", String.format(Locale.ROOT, "%.3fs", (double) (System.nanoTime() - this.startTime) / 1.0E9D));
    }

    @Override
    public void onDisable() {
        Methods.kill();
    }

    public final PaperFileManager getFileManager() {
        return this.fileManager;
    }

    public final ParticleManager getParticleManager() {
        return this.particleManager;
    }

    public final FusionPaper getFusion() {
        return this.fusion;
    }
}