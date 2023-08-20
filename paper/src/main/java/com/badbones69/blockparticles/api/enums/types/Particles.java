package com.badbones69.blockparticles.api.enums.types;

import java.util.ArrayList;

public enum Particles {
    
    BIG_LOVE_WELL("BigLoveWell", ParticleType.PARTICLE),
    LOVE_WELL("LoveWell", ParticleType.PARTICLE),
    WITCH_TORNADO("WitchTornado", ParticleType.PARTICLE),
    LOVE_TORNADO("LoveTornado", ParticleType.PARTICLE),
    HALO("Halo", ParticleType.PARTICLE),
    SANTA_HAT("SantaHat", ParticleType.PARTICLE),
    SOUL_WELL("SoulWell", ParticleType.PARTICLE),
    BIG_SOUL_WELL("BigSoulWell", ParticleType.PARTICLE),
    FLAME_WHEEL("FlameWheel", ParticleType.PARTICLE),
    MARIO("Mario", ParticleType.FOUNTAIN),
    POKEMON("Pokemon", ParticleType.FOUNTAIN),
    FOOD("Food", ParticleType.FOUNTAIN),
    MOBS("Mobs", ParticleType.FOUNTAIN),
    SNOWBLAST("SnowBlast", ParticleType.PARTICLE),
    RAINBOW("Rainbow", ParticleType.PARTICLE),
    ENDER_SIGNAL("EnderSignal", ParticleType.PARTICLE),
    MOB_SPAWNER("MobSpawner", ParticleType.PARTICLE),
    ANGRY_VILLAGER("AngryVillager", ParticleType.PARTICLE),
    HAPPY_VILLAGER("HappyVillager", ParticleType.PARTICLE),
    FOOTPRINT("FootPrint", ParticleType.PARTICLE),
    FIRE_SPEW("FireSpew", ParticleType.PARTICLE),
    SPEW("Spew", ParticleType.PARTICLE),
    STORM("Storm", ParticleType.PARTICLE),
    SNOW_STORM("SnowStorm", ParticleType.PARTICLE),
    FIRE_STORM("FireStorm", ParticleType.PARTICLE),
    WITCH("Witch", ParticleType.PARTICLE),
    DOUBLE_WITCH("DoubleWitch", ParticleType.PARTICLE),
    MAGIC("Magic", ParticleType.PARTICLE),
    PRESENTS("Presents", ParticleType.FOUNTAIN),
    MUSIC("Music", ParticleType.PARTICLE),
    POTION("Potion", ParticleType.PARTICLE),
    SNOW("Snow", ParticleType.PARTICLE),
    WATER("Water", ParticleType.PARTICLE),
    CHAINS("Chains", ParticleType.PARTICLE),
    ENCHANT("Enchant", ParticleType.PARTICLE),
    FOG("Fog", ParticleType.PARTICLE),
    HEADS("Heads", ParticleType.FOUNTAIN),
    FLAME("Flame", ParticleType.PARTICLE),
    BIG_FLAME("BigFlame", ParticleType.PARTICLE),
    HALLOWEEN("Halloween", ParticleType.FOUNTAIN),
    GEMS("Gems", ParticleType.FOUNTAIN),
    VOLCANO("Volcano", ParticleType.PARTICLE),
    SPIRAL("Spiral", ParticleType.PARTICLE),
    DOUBLE_SPIRAL("DoubleSpiral", ParticleType.PARTICLE),
    CRIT("Crit", ParticleType.PARTICLE),
    BIG_CRIT("BigCrit", ParticleType.PARTICLE);
    
    private final String name;
    private final ParticleType type;
    
    Particles(String name, ParticleType type) {
        this.name = name;
        this.type = type;
    }
    
    /**
     * @param name the Particle you wish to get.
     * @return a Particle.
     */
    public static Particles fromName(String name) {
        for (Particles particle : Particles.getParticles()) {
            if (particle.toString().equalsIgnoreCase(name)) return particle;
        }

        return null;
    }
    
    /**
     * returns all the particles.
     */
    public static Particles[] getParticles() {
        return Particles.values();
    }
    
    /**
     * @param type the ParticleType you want all the Particles From.
     * @return all the particles in a ParticleType.
     */
    public static ArrayList<Particles> getParticles(ParticleType type) {
        ArrayList<Particles> list = new ArrayList<>();

        for (Particles particle : Particles.values()) {
            if (particle.getType().equals(type)) list.add(particle);
        }

        return list;
    }
    
    /**
     * returns the particle's name in a String.
     */
    public String toString() {
        return name;
    }
    
    /**
     * returns the particle's type (Particle/Fountain).
     */
    public ParticleType getType() {
        return type;
    }
}