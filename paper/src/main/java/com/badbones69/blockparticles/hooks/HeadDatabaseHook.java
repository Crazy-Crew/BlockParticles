package com.badbones69.blockparticles.hooks;

import com.badbones69.blockparticles.Methods;
import com.ryderbelserion.vital.paper.enums.Support;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
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
        
        return Methods.getPlayerHead(head);
    }
}