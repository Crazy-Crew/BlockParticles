package com.badbones69.blockparticles.utils;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.enums.particles.ParticleType;
import com.badbones69.blockparticles.api.interfaces.IParticleBuilder;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;
import java.util.Arrays;
import java.util.List;

public class ParticleUtil {

    public static void circle(final IParticleBuilder builder, final int size) {
        for (int key = 0; key <= 30; key += 1) {
            final Location location = builder.getLocation().clone();

            location.setX(location.x() + cos(key, size));
            location.setZ(location.z() + sin(key, size));

            builder.spawnParticle(location);
        }
    }

    public static double cos(final int number, final int size) {
        return Math.cos(number) * size;
    }

    public static double sin(final int number, final int size) {
        return Math.sin(number) * size;
    }

    /**
     * Get {@link ParticleKey} by name.
     *
     * @param particleName the name of the particle
     * @return the {@link ParticleKey}
     */
    public static @Nullable ParticleKey getParticleByName(final String particleName) {
        ParticleKey foundKey = null;

        for (final ParticleKey key : ParticleKey.values()) {
            if (!key.getParticleName().equalsIgnoreCase(particleName)) continue;

            foundKey = key;

            break;
        }

        return foundKey;
    }

    /**
     * @return a list of fountains
     */
    public static List<ParticleKey> getFountains() {
        return Arrays.stream(ParticleKey.values()).filter(type -> type.getParticleType() == ParticleType.FOUNTAIN).toList();
    }

    /**
     * @return a list of particles
     */
    public static List<ParticleKey> getParticles() {
        return Arrays.stream(ParticleKey.values()).filter(type -> type.getParticleType() == ParticleType.PARTICLE).toList();
    }
}