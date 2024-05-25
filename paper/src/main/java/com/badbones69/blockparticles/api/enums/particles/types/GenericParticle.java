package com.badbones69.blockparticles.api.enums.particles.types;

import com.badbones69.blockparticles.api.enums.particles.ParticleKey;
import com.badbones69.blockparticles.api.interfaces.IParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;

public class GenericParticle extends IParticleBuilder {

    public GenericParticle(final String id, final int count, final ParticleKey particleKey, final Particle particle, final Location location) {
        super(id, count, particleKey, particle, location);
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

        spawnParticle();
    }
}