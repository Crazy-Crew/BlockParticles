package com.badbones69.blockparticles.api.objects;

import com.badbones69.blockparticles.BlockParticles;
import com.ryderbelserion.vital.paper.api.builders.items.ItemBuilder;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class CustomFountain {

    private final BlockParticles plugin = BlockParticles.getPlugin();

    private final HeadDatabaseAPI api = this.plugin.getApi();

    private final String name;
    private final List<String> headNames;
    private final List<ItemStack> heads;
    
    public CustomFountain(String name, List<String> headNames) {
        this.name = name;
        this.headNames = headNames;
        this.heads = new ArrayList<>();

        for (final String headName : headNames) {
            final ItemBuilder itemBuilder = new ItemBuilder().withType(Material.PLAYER_HEAD);

            itemBuilder.setSkull(headName, this.api);

            this.heads.add(itemBuilder.asItemStack());
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