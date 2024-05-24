package com.badbones69.blockparticles.api.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Particle {
    
    private final String id;
    private final String world;
    private final int x, y, z;
    private final String particleTypeName;
    private final Location location;
    
    public Particle(final String id, final String world, final int x, final int y, final int z, final String particleTypeName) {
        this.id = id;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.particleTypeName = particleTypeName;
        this.location = new Location(Bukkit.getWorld(world), x, y, z);
    }
    
    public final String getID() {
        return this.id;
    }
    
    public final String getWorld() {
        return this.world;
    }
    
    public final int getX() {
        return this.x;
    }
    
    public final int getY() {
        return this.y;
    }
    
    public final int getZ() {
        return this.z;
    }
    
    public final String getParticleTypeName() {
        return this.particleTypeName;
    }
    
    public final Location getLocation() {
        return this.location;
    }
}