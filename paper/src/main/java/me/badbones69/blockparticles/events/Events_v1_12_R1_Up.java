package me.badbones69.blockparticles.events;

import me.badbones69.blockparticles.api.ParticleManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class Events_v1_12_R1_Up implements Listener {
    
    private ParticleManager bp = ParticleManager.getInstance();
    
    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
        if (bp.getFountainItem().contains(e.getItem())) {
            e.setCancelled(true);
        }
    }
}