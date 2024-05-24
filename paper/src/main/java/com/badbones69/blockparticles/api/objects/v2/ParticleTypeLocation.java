package com.badbones69.blockparticles.api.objects.v2;

import com.badbones69.blockparticles.util.LocationUtil;
import org.bukkit.Location;

public record ParticleTypeLocation(String id, String particle, Location location) {

    /**
     * @return the {@link Location} converted to a {@link String}
     */
    public String asString() {
        return LocationUtil.location(this.location);
    }
}