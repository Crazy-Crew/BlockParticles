package me.badbones69.blockparticles.multisupport;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.badbones69.blockparticles.Fountains;

public class Events_v1_11_R1_Down implements Listener {
	
	@EventHandler
	public void onItemPickUp(PlayerPickupItemEvent e) {
		Entity item = e.getItem();
		if(item != null) {
			if(Fountains.items.contains(item)) {
				e.setCancelled(true);
			}
		}
	}
	
}