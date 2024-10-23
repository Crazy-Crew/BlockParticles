package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.commands.BPCommands;
import com.badbones69.blockparticles.commands.BPTab;
import com.badbones69.blockparticles.listeners.FountainListener;
import com.badbones69.blockparticles.listeners.ParticleListener;
import com.ryderbelserion.vital.paper.VitalPaper;
import com.ryderbelserion.vital.paper.api.enums.Support;
import com.ryderbelserion.vital.paper.api.files.FileManager;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public class BlockParticles extends JavaPlugin {

    @ApiStatus.Internal
    public static BlockParticles getPlugin() {
        return JavaPlugin.getPlugin(BlockParticles.class);
    }

    private VitalPaper instance;
    private FileManager fileManager;
    private ParticleManager particleManager;

    private HeadDatabaseAPI api;

    @Override
    public void onEnable() {
        this.instance = new VitalPaper(this);

        this.fileManager = this.instance.getFileManager();
        this.fileManager.addFile("config.yml").addFile("data.yml");

        if (Support.head_database.isEnabled()) {
            this.api = new HeadDatabaseAPI();
        }

        this.particleManager = new ParticleManager();
        this.particleManager.load();

        final PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new FountainListener(), this);
        pluginManager.registerEvents(new ParticleListener(), this);

        final PluginCommand command = getCommand("blockparticle");

        if (command != null) {
            command.setExecutor(new BPCommands());

            command.setTabCompleter(new BPTab());
        }

        Methods.startParticles();
    }

    @Override
    public void onDisable() {
        Methods.kill();
    }

    public final FileManager getFileManager() {
        return this.fileManager;
    }

    public final VitalPaper getVital() {
        return this.instance;
    }

    public ParticleManager getParticleManager() {
        return this.particleManager;
    }

    @ApiStatus.Internal
    public @Nullable final HeadDatabaseAPI getApi() {
        if (this.api == null) {
            return null;
        }

        return this.api;
    }
}