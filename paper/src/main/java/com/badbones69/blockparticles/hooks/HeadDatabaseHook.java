package com.badbones69.blockparticles.hooks;

import com.badbones69.blockparticles.BlockParticles;
import com.ryderbelserion.fusion.core.api.support.ModSupport;
import com.ryderbelserion.fusion.paper.FusionPaper;
import com.ryderbelserion.fusion.paper.builders.ItemBuilder;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;

public class HeadDatabaseHook {

    private static final BlockParticles plugin = BlockParticles.getPlugin();

    private static FusionPaper fusion = plugin.getFusion();
    
    private static HeadDatabaseAPI headDatabaseAPI;
    
    public HeadDatabaseHook() {
        if (fusion.isModReady(ModSupport.head_database)) {
            headDatabaseAPI = new HeadDatabaseAPI();
        }
    }
    
    public static ItemStack getHead(String head) {
        if (fusion.isModReady(ModSupport.head_database)) {
            return headDatabaseAPI.getItemHead(head);
        }
        
        return getPlayerHead(head);
    }

    public static ItemStack getPlayerHead(final String playerName) {
        return new ItemBuilder(ItemType.PLAYER_HEAD).asSkullBuilder().withName(playerName).asItemStack();
    }
}