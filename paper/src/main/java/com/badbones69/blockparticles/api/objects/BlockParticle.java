package com.badbones69.blockparticles.api.objects;

import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.config.ParticleConfig;
import com.badbones69.blockparticles.api.types.spiral.DoubleSpiral;
import com.badbones69.blockparticles.api.types.spiral.Spiral;

public class BlockParticle {

    private final ParticleConfig config;

    private AbstractParticle particle;

    public BlockParticle(ParticleConfig config) {
        this.config = config;

        final ParticleKey particleKey = this.config.getParticleKey();

        if (particleKey != null) {
            switch (particleKey) {
                case DOUBLE_SPIRAL -> this.particle = new DoubleSpiral(this);
                case SPIRAL -> this.particle = new Spiral(this);
            }
        }
    }

    public final ParticleConfig getConfig() {
        return this.config;
    }

    public final AbstractParticle getParticle() {
        return this.particle;
    }
}