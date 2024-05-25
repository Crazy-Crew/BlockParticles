package com.badbones69.blockparticles.api.enums.particles.types;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.interfaces.IParticleBuilder;
import com.badbones69.blockparticles.utils.ParticleUtil;
import org.bukkit.Location;
import org.bukkit.Particle;

public class SpiralParticle extends IParticleBuilder {

    public SpiralParticle(final String id, final int count, final int size, final ParticleKey particleKey, final Particle particle, final Location location) {
        super(id, count, size, particleKey, particle, location);
    }

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

        ParticleUtil.circle(this, getSize());

        if (getParticleKey() == ParticleKey.DOUBLE_SPIRAL) {
            ParticleUtil.circle(this, getSize());
        }
    }
}