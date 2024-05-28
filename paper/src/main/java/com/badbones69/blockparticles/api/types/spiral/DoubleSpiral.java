package com.badbones69.blockparticles.api.types.spiral;

import com.badbones69.blockparticles.api.objects.BlockParticle;

public class DoubleSpiral extends Spiral {

    public DoubleSpiral(final BlockParticle particle) {
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
        //ParticleUtil.circle(this, this.location, getRadius(), getHeight(), getOuterRadius(), getOuterHeight());
    }
}