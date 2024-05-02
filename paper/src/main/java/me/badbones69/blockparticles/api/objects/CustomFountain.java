package me.badbones69.blockparticles.api.objects;

import me.badbones69.blockparticles.BlockParticles;
import me.badbones69.blockparticles.hook.HeadDatabaseHook;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CustomFountain {
    
    private String name;
    private List<String> headNames;
    private List<ItemStack> heads;
    
    public CustomFountain(String name, List<String> headNames) {
        this.name = name;
        this.headNames = headNames;
        this.heads = new ArrayList<>();
        for (String headName : headNames) {
            ItemStack item = HeadDatabaseHook.getHead(headName);
            if (item == null) {
                JavaPlugin.getPlugin(BlockParticles.class).getLogger().warning("Head item '" + name + "' for id " + headName + " is invalid!");
            } else {
                heads.add(item);
            }
        }
    }
    
    public String getName() {
        return name;
    }
    
    public List<String> getHeadNames() {
        return headNames;
    }
    
    public List<ItemStack> getHeads() {
        return heads;
    }
    
}