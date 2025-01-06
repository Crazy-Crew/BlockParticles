package com.badbones69.blockparticles.api.enums.fountains;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.objects.CustomFountain;

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
    
    public static BPFountains getFromName(String name) {
        for (BPFountains fountain : values()) {
            if (fountain == CUSTOM) {
                for (CustomFountain customFountain : BlockParticles.getPlugin().getParticleManager().getCustomFountains()) {
                    if (customFountain.getName().equalsIgnoreCase(name.replace(" ", ""))) {
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