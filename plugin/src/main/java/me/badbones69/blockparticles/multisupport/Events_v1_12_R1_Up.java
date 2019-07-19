package me.badbones69.blockparticles.multisupport;

import me.badbones69.blockparticles.api.BlockParticles;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class Events_v1_12_R1_Up implements Listener {
	
	private BlockParticles bp = BlockParticles.getInstance();
	
	@EventHandler
	public void onItemPickUp(EntityPickupItemEvent e) {
		if(bp.getFountainItem().contains(e.getItem())) {
			e.setCancelled(true);
		}
	}
	
}