package me.badbones69.blockparticles.api.objects;

import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class CustomFountain {
    
    private final String name;
    private final List<String> headNames;
    private final List<ItemStack> heads;
    
    public CustomFountain(String name, List<String> headNames) {
        this.name = name;
        this.headNames = headNames;
        this.heads = new ArrayList<>();

        for (String headName : headNames) {
            //ItemStack item = HeadDatabaseHook.getHead(headName);
            //if (item == null) {
            //    JavaPlugin.getPlugin(BlockParticles.class).getLogger().warning("Head item '" + name + "' for id " + headName + " is invalid!");
            //} else {
            //    heads.add(item);
            //}
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