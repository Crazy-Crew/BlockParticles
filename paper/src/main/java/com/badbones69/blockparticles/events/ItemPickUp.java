package com.badbones69.blockparticles.events;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.ParticleManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemPickUp implements Listener {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);
    private final ParticleManager particleManager = this.plugin.getParticleManager();
    
    @EventHandler(ignoreCancelled = true)
    public void onItemPickUp(EntityPickupItemEvent event) {
        if (this.particleManager.getFountainItem().contains(event.getItem())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemPickUp(PlayerAttemptPickupItemEvent event) {
        if (this.particleManager.getFountainItem().contains(event.getItem())) {
            event.setCancelled(true);
        }
    }
}