package com.badbones69.blockparticles.api.types.spiral;

import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import com.badbones69.blockparticles.api.objects.BlockParticle;

public class Spiral extends AbstractParticle {

    public Spiral(final BlockParticle particle) {
        super(particle);
    }

    public final double getHeight() {
        return this.section.getDouble("inner-radius.y-level", 0.5);
    }

    public final int getRadius() {
        return this.section.getInt("inner-radius.radius", 3);
    }

    //private Location clone;

    @Override
    public void execute() {
        //this.clone = getLocation().clone().add(0.5, 1.0, 0.5);

        runAtFixedRate(this.plugin, 0, this.config.getParticleKey().getPeriod());
    }

    @Override
    public void run() {
        //ParticleUtil.circle(this, this.clone, getRadius(), getHeight(), 0, 0);
    }
}