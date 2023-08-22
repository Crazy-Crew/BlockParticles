package com.badbones69.blockparticles.api.storage;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.storage.interfaces.ParticleDataManager;
import com.badbones69.blockparticles.api.storage.types.file.json.DataManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StorageManager {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private ParticleDataManager particleDataManager;

    public void init() {
        this.particleDataManager = new DataManager(this.plugin.getDataFolder().toPath());
    }

    public ParticleDataManager getParticleDataManager() {
        return this.particleDataManager;
    }
}