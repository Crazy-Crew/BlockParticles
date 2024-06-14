package com.badbones69.blockparticles.utils;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.enums.particles.ParticleType;
import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import com.badbones69.blockparticles.api.objects.BlockParticle;
import com.badbones69.blockparticles.api.types.spiral.DoubleSpiral;
import com.badbones69.blockparticles.api.types.spiral.Spiral;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;
import java.util.Arrays;
import java.util.List;

public class ParticleUtil {

    /**
     * Creates a circle
     *
     * @param particle the {@link AbstractParticle}
     * @param location the {@link Location} of the {@link org.bukkit.Particle}
     * @param inner the size of the {@link Spiral}
     * @param innerHeight the height of the {@link Spiral}
     * @param outer the size of the {@link DoubleSpiral}
     * @param outerHeight the height of the {@link DoubleSpiral}
     */
    public static void circle(final BlockParticle particle, final Location location, final int inner, final double innerHeight, final int outer, final double outerHeight) {
        final boolean isDoubleSpiral = particle.getConfig().getParticleKey() == ParticleKey.DOUBLE_SPIRAL;

        final AbstractParticle abstractParticle = particle.getParticle();

        // Check if it's double spiral
        if (isDoubleSpiral && outer > 0) {
            modify(location, outer, outerHeight, abstractParticle);
        }

        modify(location, inner, innerHeight, abstractParticle);
    }

    /**
     * Spawns the particle!
     *
     * @param location the {@link Location} of the {@link org.bukkit.Particle}
     * @param outer the size of the {@link DoubleSpiral}
     * @param outerHeight the height of the {@link DoubleSpiral}
     */
    private static void modify(Location location, int outer, double outerHeight, AbstractParticle abstractParticle) {
        for (int key = 0; key <= 30; key += 1) {
            final Location loc = location.clone();

            loc.setX(loc.x() + cos(key, outer));
            loc.setZ(loc.z() + sin(key, outer));

            if (outerHeight > 0.0) {
                location.setY(loc.y() + outerHeight);
            }

            abstractParticle.spawnParticle(loc);
        }
    }

    /**
     * Creates a circle
     *
     * @param particle the {@link AbstractParticle}
     * @param inner the size of the inner spiral
     */
    public static void circle(final BlockParticle particle, final int inner, final int innerHeight) {
        circle(particle, particle.getParticle().getLocation().clone(), inner, innerHeight, inner, 0.0);
    }

    /**
     * A mathematical equation to create a circle if paired with the correct methods and fancy numbers.
     *
     * @param number the base number converted to a trigonometric cosine for angles
     * @param size the number used in the equation i.e. the size of the circle
     * @return the final double
     */
    public static double cos(final int number, final int size) {
        return Math.cos(number) * size;
    }

    /**
     * A mathematical equation to create a circle if paired with the correct methods and fancy numbers.
     *
     * @param number the base number converted to a trigonometric sine for angles
     * @param size the number used in the equation i.e. the size of the circle
     * @return the final double
     */
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