package com.badbones69.blockparticles.api.storage.objects;

public record CustomLocation(int id, String world, double x, double y, double z) {

    @Override
    public int id() {
        return this.id;
    }

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