package com.badbones69.blockparticles.api.enums.particles;

public enum BPParticles {
    
    LOVEWELL(),
    BIGLOVEWELL(),
    LOVETORNADO(),
    WITCHTORNADO(),
    FLAMEWHEEL(),
    SOULWELL(),
    BIGSOULWELL(),
    SANTAHAT(),
    SNOWBLAST(),
    RAINBOW(),
    ENDERSIGNAL(),
    MOBSPAWNER(),
    ANGRYVILLAGER(),
    HAPPYVILLAGER(),
    FOOTPRINT(),
    FIRESPEW(),
    SNOWSTORM(),
    DOUBLEWITCH(),
    WITCH(),
    MAGIC(),
    SPEW(),
    HALO(),
    MUSIC(),
    POTION(),
    SNOW(),
    FIRESTORM(),
    WATER(),
    CHAINS(),
    ENCHANT(),
    FOG(),
    STORM(),
    BIGFLAME(),
    FLAME(),
    VOLCANO(),
    SPIRAL(),
    DOUBLESPIRAL(),
    CRIT(),
    BIGCRIT();
    
    public static BPParticles getFromName(final String name) {
        for (final BPParticles fountain : values()) {
            if (fountain.name().equalsIgnoreCase(name.replace(" ", ""))) {
                return fountain;
            }
        }

        return null;
    }
}