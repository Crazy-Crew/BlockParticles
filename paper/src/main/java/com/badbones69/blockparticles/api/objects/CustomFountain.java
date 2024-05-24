package com.badbones69.blockparticles.api.objects;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.hook.HeadDatabaseHook;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;

public class CustomFountain {
    
    private final String name;
    private final List<String> headNames;
    private final List<ItemStack> heads;
    
    public CustomFountain(final String name, final List<String> headNames) {
        this.name = name;
        this.headNames = headNames;
        this.heads = new ArrayList<>();

        for (String headName : headNames) {
            ItemStack item = HeadDatabaseHook.getHead(headName);
            if (item == null) {
                JavaPlugin.getPlugin(BlockParticles.class).getLogger().warning("Head item '" + name + "' for id " + headName + " is invalid!");
            } else {
                this.heads.add(item);
            }
        }
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final List<String> getHeadNames() {
        return this.headNames;
    }
    
    public final List<ItemStack> getHeads() {
        return this.heads;
    }
}