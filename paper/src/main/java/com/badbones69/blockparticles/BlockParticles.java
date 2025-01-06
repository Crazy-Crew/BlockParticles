package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.commands.BPCommands;
import com.badbones69.blockparticles.commands.BPTab;
import com.badbones69.blockparticles.hooks.HeadDatabaseHook;
import com.badbones69.blockparticles.listeners.FountainListener;
import com.badbones69.blockparticles.listeners.HeadDatabaseListener;
import com.badbones69.blockparticles.listeners.ParticleListener;
import com.ryderbelserion.vital.paper.VitalPaper;
import com.ryderbelserion.vital.paper.api.enums.Support;
import com.ryderbelserion.vital.paper.api.files.FileManager;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
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

    private VitalPaper instance;
    private FileManager fileManager;
    private ParticleManager particleManager;

    @Override
    public void onEnable() {
        this.instance = new VitalPaper(this);

        this.fileManager = this.instance.getFileManager();
        this.fileManager.addFile("config.yml").addFile("data.yml");

        this.particleManager = new ParticleManager();

        final PluginManager pluginManager = getServer().getPluginManager();

        if (Support.head_database.isEnabled()) {
            pluginManager.registerEvents(new HeadDatabaseListener(), this);
        } else {
            pluginManager.registerEvents(new FountainListener(), this);
            pluginManager.registerEvents(new ParticleListener(), this);

            this.particleManager.load();

            Methods.startParticles();
        }

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

    public final FileManager getFileManager() {
        return this.fileManager;
    }

    public ParticleManager getParticleManager() {
        return this.particleManager;
    }

    public final VitalPaper getVital() {
        return this.instance;
    }
}