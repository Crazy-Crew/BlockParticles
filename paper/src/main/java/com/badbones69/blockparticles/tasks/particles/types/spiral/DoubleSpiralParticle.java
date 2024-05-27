package com.badbones69.blockparticles.tasks.particles.types.spiral;

import com.badbones69.blockparticles.tasks.api.objects.BlockParticle;
import com.badbones69.blockparticles.utils.ParticleUtil;

public class DoubleSpiralParticle extends SpiralParticle {

    public DoubleSpiralParticle(final BlockParticle particle) {
        super(particle);
    }

    public final int getOuterRadius() {
        return this.section.getInt("outer-radius.radius", 5);
    }

    public final double getOuterHeight() {
        return this.section.getDouble("outer-radius.y-level", 0.8);
    }

    @Override
    public void run() {
        if (this.isCancelled) {
            return;
        }

        ParticleUtil.circle(this, this.location, getRadius(), getHeight(), getOuterRadius(), getOuterHeight());
    }
}