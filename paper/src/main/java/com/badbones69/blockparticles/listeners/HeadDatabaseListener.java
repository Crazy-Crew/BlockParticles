package com.badbones69.blockparticles.listeners;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.hooks.HeadDatabaseHook;
import me.arcaniax.hdb.api.DatabaseLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class HeadDatabaseListener implements Listener {

    private final BlockParticles plugin = BlockParticles.getPlugin();

    private final PluginManager pluginManager = this.plugin.getServer().getPluginManager();

    @EventHandler
    public void onDatabaseLoad(DatabaseLoadEvent e) {
        new HeadDatabaseHook();

        this.plugin.getParticleManager().load();

        this.pluginManager.registerEvents(new FountainListener(), this.plugin);
        this.pluginManager.registerEvents(new ParticleListener(), this.plugin);

        Methods.startParticles();
    }
}