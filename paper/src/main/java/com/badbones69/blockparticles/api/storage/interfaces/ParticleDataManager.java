package com.badbones69.blockparticles.api.storage.interfaces;

import com.badbones69.blockparticles.api.storage.objects.ParticleData;
import org.bukkit.Location;

public interface ParticleDataManager {

    void load();

    void save();

    void reload();

    void addParticleData(String name, Location location);

    ParticleData getParticleData(String name);

    void removeParticleData(String name, int id);

    boolean hasParticleData(String name);

    void purge();

}