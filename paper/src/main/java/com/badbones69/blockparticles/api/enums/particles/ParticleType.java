package com.badbones69.blockparticles.api.enums.particles;

public enum ParticleType {
    
    PARTICLE("Particle"), FOUNTAIN("Fountain");
    
    private final String name;
    
    ParticleType(final String name) {
        this.name = name;
    }
    
    public static ParticleType[] getTypes() {
        return ParticleType.values();
    }
    
    public final String getName() {
        return this.name;
    }
}