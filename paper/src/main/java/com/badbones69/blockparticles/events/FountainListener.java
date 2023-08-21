package com.badbones69.blockparticles.events;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.CrazyManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FountainListener implements Listener {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);
    
    private final CrazyManager crazyManager = null;
    
    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
        if (crazyManager.getFountainItem().contains(e.getItem())) e.setCancelled(true);
    }
}