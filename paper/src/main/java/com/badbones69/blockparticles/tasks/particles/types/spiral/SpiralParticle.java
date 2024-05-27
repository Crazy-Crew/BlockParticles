package com.badbones69.blockparticles.tasks.particles.types.spiral;

import com.badbones69.blockparticles.tasks.api.objects.BlockParticle;
import com.badbones69.blockparticles.tasks.particles.AbstractParticle;
import com.badbones69.blockparticles.utils.ParticleUtil;
import org.bukkit.Location;

public class SpiralParticle extends AbstractParticle {

    public SpiralParticle(final BlockParticle particle) {
        super(particle);
    }

    public final double getHeight() {
        return this.section.getDouble("inner-radius.y-level", 0.5);
    }

    public final int getRadius() {
        return this.section.getInt("inner-radius.radius", 3);
    }

    private Location clone;

    @Override
    public void execute() {
        this.clone = this.location.clone().add(0.5, 1.0, 0.5);

        runAtFixedRate(this.plugin, 0, getParticleKey().getPeriod());
    }

    @Override
    public void run() {
        if (this.isCancelled) {
            return;
        }

        ParticleUtil.circle(this, this.clone, getRadius(), getHeight(), 0, 0);
    }
}