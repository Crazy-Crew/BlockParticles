package com.badbones69.blockparticles.tasks.api.objects;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import com.badbones69.blockparticles.tasks.particles.ParticleConfig;
import com.badbones69.blockparticles.tasks.particles.types.spiral.DoubleSpiralParticle;
import com.badbones69.blockparticles.tasks.particles.types.spiral.SpiralParticle;
import com.badbones69.blockparticles.utils.ParticleUtil;
import org.jetbrains.annotations.Nullable;

public class BlockParticle {

    private final ParticleConfig config;

    private AbstractParticle particle;

    public BlockParticle(ParticleConfig config) {
        this.config = config;

        @Nullable ParticleKey type = ParticleUtil.getParticleByName(this.config.getType());

        if (type != null) {
            switch (type) {
                case DOUBLE_SPIRAL -> this.particle = new DoubleSpiralParticle(this);
                case SPIRAL -> this.particle = new SpiralParticle(this);
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