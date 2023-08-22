package com.badbones69.blockparticles.api;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.plugin.BlockParticlesPlugin;
import com.badbones69.blockparticles.api.plugin.registry.BlockParticlesProvider;
import com.badbones69.blockparticles.api.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

public class CrazyManager {

    private final @NotNull BlockParticlesPlugin blockParticlesPlugin = BlockParticlesProvider.get();

    private final @NotNull BlockParticles plugin = this.blockParticlesPlugin.getPlugin();

    private StorageManager storageManager;

    public void load(boolean serverStart) {
        if (serverStart) {
            this.storageManager = new StorageManager();
            this.storageManager.init();
        }

        this.storageManager.getParticleDataManager().load();
    }

    public void reload(boolean serverStop) {
        this.blockParticlesPlugin.getConfigManager().reload();

        this.storageManager.getParticleDataManager().reload();

        if (serverStop) {
            this.storageManager.getParticleDataManager().save();

            this.storageManager.getParticleDataManager().purge();
        }
    }

    public StorageManager getStorageManager() {
        return this.storageManager;
    }
}