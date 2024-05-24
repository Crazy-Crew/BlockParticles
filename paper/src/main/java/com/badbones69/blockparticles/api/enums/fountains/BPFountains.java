package com.badbones69.blockparticles.api.enums.fountains;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.api.ParticleManager;
import org.bukkit.plugin.java.JavaPlugin;

public enum BPFountains {
    
    MARIO(),
    POKEMON(),
    FOOD(),
    MOBS(),
    HALLOWEEN(),
    GEMS(),
    HEADS(),
    PRESENTS(),
    CUSTOM();
    
    private static final ParticleManager particleManager = JavaPlugin.getPlugin(BlockParticles.class).getParticleManager();
    
    public static BPFountains getFromName(final String name) {
        for (final BPFountains fountain : values()) {
            if (fountain == CUSTOM) {
                for (final CustomFountain customFountain : particleManager.getCustomFountains()) {
                    if (customFountain.getFountainName().equalsIgnoreCase(name.replace(" ", ""))) {
                        return CUSTOM;
                    }
                }
            } else if (fountain.name().equalsIgnoreCase(name)) {
                return fountain;
            }
        }

        return null;
    }
}