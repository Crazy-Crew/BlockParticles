package com.badbones69.blockparticles.hook;

import com.badbones69.blockparticles.Methods;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class HeadDatabaseHook {
    
    private static HeadDatabaseAPI headDatabaseAPI;
    private static boolean enabled;
    
    public HeadDatabaseHook() {
        enabled = Bukkit.getPluginManager().getPlugin("HeadDatabase") != null;
        if (enabled) headDatabaseAPI = new HeadDatabaseAPI();
    }
    
    public static ItemStack getHead(String head) {
        if (enabled) {
            return headDatabaseAPI.getItemHead(head);
        }
        
        return Methods.getPlayerHead("https://textures.minecraft.net/texture/" + head);
    }
}