package com.badbones69.blockparticles.api.builders.gui;

import com.badbones69.blockparticles.BlockParticles;
import com.ryderbelserion.vital.paper.api.enums.Support;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public abstract class InventoryBuilder {

    protected final Player player;

    public InventoryBuilder(final Player player) {
        this.player = player;
    }

    protected final BlockParticles plugin = BlockParticles.getPlugin();

    public final String parse(final Player player, final String title) {
        return Support.placeholder_api.isEnabled() ? PlaceholderAPI.setPlaceholders(player, title) : title;
    }
}