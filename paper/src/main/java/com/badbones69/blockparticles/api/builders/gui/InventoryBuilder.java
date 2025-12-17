package com.badbones69.blockparticles.api.builders.gui;

import com.badbones69.blockparticles.BlockParticles;
import com.ryderbelserion.fusion.paper.FusionPaper;
import org.bukkit.entity.Player;

public abstract class InventoryBuilder {

    protected final Player player;

    public InventoryBuilder(final Player player) {
        this.player = player;
    }

    protected final BlockParticles plugin = BlockParticles.getPlugin();

    protected final FusionPaper fusion = this.plugin.getFusion();

    public final String parse(final Player player, final String title) {
        return this.fusion.papi(player, title);
    }
}