package us.crazycrew.crazyparticles.api;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface ParticleController {

    void add(Location location, Particle particle);

    void remove(Location location, Particle particle);

}