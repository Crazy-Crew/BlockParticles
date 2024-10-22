package com.badbones69.blockparticles.api.enums.particles;

public enum ParticleType {
    
    PARTICLE("Particle"), FOUNTAIN("Fountain");
    
    private final String name;
    
    ParticleType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}