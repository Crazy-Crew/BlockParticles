package me.badbones69.blockparticles.api.enums.types;

public enum ParticleType {
    
    PARTICLE("Particle"), FOUNTAIN("Fountain");
    
    private final String name;
    
    ParticleType(String name) {
        this.name = name;
    }
    
    public static ParticleType[] getTypes() {
        return ParticleType.values();
    }
    
    public String getName() {
        return name;
    }
}