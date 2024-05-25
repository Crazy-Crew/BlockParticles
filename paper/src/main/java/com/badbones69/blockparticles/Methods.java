package com.badbones69.blockparticles;

import com.ryderbelserion.vital.paper.builders.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Methods implements Listener {

    public static ItemStack getPlayerHead(final String playerName) {
        return new ItemBuilder().withType(Material.PLAYER_HEAD).setPlayer(playerName).getStack();
    }
}