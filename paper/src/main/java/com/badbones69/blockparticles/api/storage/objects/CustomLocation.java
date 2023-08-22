package com.badbones69.blockparticles.api.storage.objects;

import com.google.gson.annotations.Expose;

public record CustomLocation(@Expose String world, @Expose double x, @Expose double y, @Expose double z) {

    @Override
    public String world() {
        return this.world;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }
}