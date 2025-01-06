package com.badbones69.blockparticles.hooks;

import com.ryderbelserion.vital.paper.api.builders.items.ItemBuilder;
import com.ryderbelserion.vital.paper.api.enums.Support;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HeadDatabaseHook {
    
    private static HeadDatabaseAPI headDatabaseAPI;
    
    public HeadDatabaseHook() {
        if (Support.head_database.isEnabled()) headDatabaseAPI = new HeadDatabaseAPI();
    }
    
    public static ItemStack getHead(String head) {
        if (Support.head_database.isEnabled()) {
            return headDatabaseAPI.getItemHead(head);
        }
        
        return getPlayerHead(head);
    }

    public static ItemStack getPlayerHead(final String playerName) {
        return new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer(playerName).asItemStack();
    }
}