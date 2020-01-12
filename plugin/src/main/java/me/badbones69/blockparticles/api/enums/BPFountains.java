package me.badbones69.blockparticles.api.enums;

import me.badbones69.blockparticles.api.ParticleManager;
import me.badbones69.blockparticles.api.objects.CustomFountain;

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