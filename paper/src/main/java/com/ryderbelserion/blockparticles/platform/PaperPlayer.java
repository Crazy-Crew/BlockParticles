package com.ryderbelserion.blockparticles.platform;

import java.util.UUID;

public class PaperPlayer extends PaperSender implements Player {

    private final org.bukkit.entity.Player player;

    public PaperPlayer(org.bukkit.entity.Player player) {
        super(player);

        this.player = player;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public String getName() {
        return this.player.getName();
    }

    @Override
    public UUID getUUID() {
        return this.player.getUniqueId();
    }
}