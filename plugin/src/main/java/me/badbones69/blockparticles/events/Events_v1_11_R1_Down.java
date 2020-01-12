package me.badbones69.blockparticles.events;

import me.badbones69.blockparticles.api.ParticleManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Events_v1_11_R1_Down implements Listener {
    
    private ParticleManager bp = ParticleManager.getInstance();
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent e) {
        if (bp.getFountainItem().contains(e.getItem())) {
            e.setCancelled(true);
        }
    }
    
}