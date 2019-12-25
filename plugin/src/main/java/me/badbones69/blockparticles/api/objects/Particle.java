package me.badbones69.blockparticles.api.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Particle {

    private String id;
    private String world;
    private int x, y, z;
    private String particleTypeName;
    private Location location;

    public Particle(String id, String world, int x, int y, int z, String particleTypeName) {
        this.id = id;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.particleTypeName = particleTypeName;
        location = new Location(Bukkit.getWorld(world), x, y, z);
    }

    public String getID() {
        return id;
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getParticleTypeName() {
        return particleTypeName;
    }

    public Location getLocation() {
        return location;
    }

}