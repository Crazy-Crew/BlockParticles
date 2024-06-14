package com.badbones69.blockparticles.api.enums.particles;

public enum ParticleKey {

    BIG_LOVE_WELL(ParticleType.PARTICLE, "BigLoveWell", 25L),
    LOVE_WELL(ParticleType.PARTICLE, "LoveWell", 16L),
    WITCH_TORNADO(ParticleType.PARTICLE, "WitchTornado", 30L),
    LOVE_TORNADO(ParticleType.PARTICLE, "LoveTornado", 30L),
    HALO(ParticleType.PARTICLE, "Halo", 5L),
    SANTAHAT(ParticleType.PARTICLE, "SantaHat", 5L),
    SOUL_WELL(ParticleType.PARTICLE, "SoulWell", 16L),
    BIG_SOUL_WELL(ParticleType.PARTICLE, "BigSoulWell", 25L),
    FLAME_WHEEL(ParticleType.PARTICLE, "FlameWheel", 25L),
    SNOW_BLAST(ParticleType.PARTICLE, "SnowBlast", 2L),
    RAINBOW(ParticleType.PARTICLE, "Rainbow", 5L),
    ENDER_SIGNAL(ParticleType.PARTICLE, "EnderSignal", 8L),
    MOB_SPAWNER(ParticleType.PARTICLE, "MobSpawner", 8L),
    ANGRY_VILLAGER(ParticleType.PARTICLE, "AngryVillager", 10L),
    HAPPY_VILLAGER(ParticleType.PARTICLE, "HappyVillager", 5L),
    FOOTPRINT(ParticleType.PARTICLE, "FootPrint", 20L),
    FIRE_SPEW(ParticleType.PARTICLE, "FireSpew", 2L),
    SPEW(ParticleType.PARTICLE, "Spew", 2L),
    STORM(ParticleType.PARTICLE, "Storm", 2L),
    SNOW_STORM(ParticleType.PARTICLE, "SnowStorm", 2L),
    FIRE_STORM(ParticleType.PARTICLE, "FireStorm", 2L),
    WITCH(ParticleType.PARTICLE, "Witch", 2L),
    DOUBLE_WITCH(ParticleType.PARTICLE, "DoubleWitch", 2L),
    MAGIC(ParticleType.PARTICLE, "Magic", 2L),
    MUSIC(ParticleType.PARTICLE, "Music", 2L),
    POTION(ParticleType.PARTICLE, "Potion", 2L),
    SNOW(ParticleType.PARTICLE, "Snow", 2L),
    WATER(ParticleType.PARTICLE, "Water", 2L),
    CHAINS(ParticleType.PARTICLE, "Chains", 5L),
    ENCHANT(ParticleType.PARTICLE, "Enchant", 2L),
    FOG(ParticleType.PARTICLE, "Fog", 2L),
    FLAME(ParticleType.PARTICLE, "Flame", 5L),
    BIG_FLAME(ParticleType.PARTICLE, "BigFlame", 5L),
    VOLCANO(ParticleType.PARTICLE, "Volcano", 4L),

    CRITICAL(ParticleType.PARTICLE, "Crit", 2L),
    BIG_CRITICAL(ParticleType.PARTICLE, "BigCrit", 2L),

    DOUBLE_SPIRAL(ParticleType.PARTICLE, "DoubleSpiral", 2L),
    SPIRAL(ParticleType.PARTICLE, "Spiral", 2L),
    GENERIC(ParticleType.PARTICLE, "Generic", 2L),

    MARIO_FOUNTAIN(ParticleType.FOUNTAIN, "Mario", 0L),
    POKEMON_FOUNTAIN(ParticleType.FOUNTAIN, "Pokemon", 0L),
    FOOD_FOUNTAIN(ParticleType.FOUNTAIN, "Food", 0L),
    MOBS_FOUNTAIN(ParticleType.FOUNTAIN, "Mobs", 0L),
    HEADS_FOUNTAIN(ParticleType.FOUNTAIN, "Heads", 0L);

    private final ParticleType particleType;
    private final String particleName;
    private final Long period;

    ParticleKey(final ParticleType particleType, final String particleName, final long period) {
        this.particleType = particleType;
        this.particleName = particleName;
        this.period = period;
    }

    /**
     * @return the particle type
     */
    public final ParticleType getParticleType() {
        return this.particleType;
    }

    /**
     * @return the particle name
     */
    public final String getParticleName() {
        return this.particleName;
    }

    /**
     * @return the interval of when to run
     */
    public final long getPeriod() {
        return this.period;
    }
}