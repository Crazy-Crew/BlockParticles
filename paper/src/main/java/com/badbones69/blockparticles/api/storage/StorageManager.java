package com.badbones69.blockparticles.api.storage;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.plugin.BlockParticlesPlugin;
import com.badbones69.blockparticles.api.plugin.registry.BlockParticlesProvider;
import com.badbones69.blockparticles.api.storage.interfaces.ParticleDataManager;
import com.badbones69.blockparticles.api.storage.types.file.json.DataManager;
import org.jetbrains.annotations.NotNull;

public class StorageManager {

    private final @NotNull BlockParticlesPlugin blockParticlesPlugin = BlockParticlesProvider.get();

    private final @NotNull BlockParticles plugin = this.blockParticlesPlugin.getPlugin();

    private ParticleDataManager particleDataManager;

    public void init() {
        this.particleDataManager = new DataManager(this.plugin.getDataFolder().toPath());
    }

    public ParticleDataManager getParticleDataManager() {
        return this.particleDataManager;
    }
}