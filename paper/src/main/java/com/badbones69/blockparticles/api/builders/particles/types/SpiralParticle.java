package com.badbones69.blockparticles.api.builders.particles.types;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.interfaces.IParticleBuilder;
import com.badbones69.blockparticles.utils.ParticleUtil;
import org.bukkit.Location;
import org.bukkit.Particle;

public class SpiralParticle extends IParticleBuilder {

    private final double height_value;
    private final int inner_radius;
    private final int outer_radius;

    public SpiralParticle(final String id, final int count, final int inner_radius, final int outer_radius, final double height_value, final ParticleKey particleKey, final Particle particle, final Location location) {
        super(id, count, particleKey, particle, location);

        this.inner_radius = inner_radius;
        this.outer_radius = outer_radius;
        this.height_value = height_value;
    }

    private final Location location = getLocation().clone().add(0.5, 1.0, 0.5);

    @Override
    public IParticleBuilder execute() {
        runAtFixedRate(this.plugin, 0, getParticleKey().getPeriod());

        return this;
    }

    @Override
    public void run() {
        // Return if cancelled!
        if (this.isCancelled) {
            return;
        }

        ParticleUtil.circle(this, this.location, this.inner_radius, this.outer_radius, this.height_value);
    }
}