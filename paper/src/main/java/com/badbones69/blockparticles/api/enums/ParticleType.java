package com.badbones69.blockparticles.api.enums;

public enum ParticleType {
    
    PARTICLE("Particle"), FOUNTAIN("Fountain");
    
    private String name;
    
    private ParticleType(String name) {
        this.name = name;
    }
    
    public static ParticleType[] getTypes() {
        return ParticleType.values();
    }
    
    public String getName() {
        return name;
    }
    
}