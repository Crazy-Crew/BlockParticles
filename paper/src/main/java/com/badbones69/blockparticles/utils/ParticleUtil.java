package com.badbones69.blockparticles.utils;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.enums.particles.ParticleType;
import com.badbones69.blockparticles.api.interfaces.IParticleBuilder;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;
import java.util.Arrays;
import java.util.List;

public class ParticleUtil {

    public static void circle(final IParticleBuilder builder, Location location, final int inner, final int outer, final double height) {
        boolean isDoubleSpiral = builder.getParticleKey() == ParticleKey.DOUBLE_SPIRAL;

        for (int key = 0; key <= 30; key += 1) {
            if (isDoubleSpiral && outer > 0) {
                final Location loc = location.clone();

                loc.setX(loc.x() + cos(key, outer));
                loc.setZ(loc.z() + sin(key, outer));

                if (height > 0.0) {
                    location.setY(loc.y() + height);
                }

                builder.spawnParticle(loc);
            }

            final Location loc = location.clone();

            loc.setX(loc.x() + cos(key, inner));
            loc.setZ(loc.z() + sin(key, inner));

            builder.spawnParticle(loc);
        }
    }

    public static void circle(final IParticleBuilder builder, final int inner) {
        circle(builder, builder.getLocation(), inner, inner, 0.0);
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