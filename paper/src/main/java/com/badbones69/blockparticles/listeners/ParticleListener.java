package com.badbones69.blockparticles.listeners;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.ParticleManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class ParticleListener implements Listener {

    private final BlockParticles plugin = BlockParticles.getPlugin();

    private final ParticleManager particleManager = this.plugin.getParticleManager();
    
    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
        if (this.particleManager.getFountainItem().contains(e.getItem())) {
            e.setCancelled(true);
        }
    }
}