package com.badbones69.blockparticles.api.objects;

import com.badbones69.blockparticles.hooks.HeadDatabaseHook;
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

        for (final String headName : headNames) {
            this.heads.add(HeadDatabaseHook.getHead(headName));
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