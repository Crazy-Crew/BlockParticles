package me.badbones69.blockparticles.events;

import me.badbones69.blockparticles.api.CrazyManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class FountainListener implements Listener {
    
    private final CrazyManager bp = CrazyManager.getInstance();
    
    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
        if (bp.getFountainItem().contains(e.getItem())) e.setCancelled(true);
    }
}