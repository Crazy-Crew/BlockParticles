package com.badbones69.blockparticles.api.enums;

import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.api.ParticleManager;

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
    
    private static ParticleManager bp = ParticleManager.getInstance();
    
    public static BPFountains getFromName(String name) {
        for (BPFountains fountain : values()) {
            if (fountain == CUSTOM) {
                for (CustomFountain customFountain : bp.getCustomFountains()) {
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