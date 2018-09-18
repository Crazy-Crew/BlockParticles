package me.badbones69.blockparticles.api;

public enum ParticleType {

    PARTICLE("Particle"), FOUNTAIN("Fountain");

    String Name;

    ParticleType(String name) {
        this.Name = name;
    }

    public static ParticleType[] getTypes() {
        return ParticleType.values();
    }

}