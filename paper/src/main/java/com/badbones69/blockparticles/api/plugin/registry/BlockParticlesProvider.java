package com.badbones69.blockparticles.api.plugin.registry;

import com.badbones69.blockparticles.api.plugin.BlockParticlesPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class BlockParticlesProvider {

    private static BlockParticlesPlugin blockParticlesPlugin = null;

    public static @NotNull BlockParticlesPlugin get() {
        BlockParticlesPlugin instance = BlockParticlesProvider.blockParticlesPlugin;

        if (instance == null) throw new RuntimeException("Failed to use the get() method. Contact the developer.");

        return blockParticlesPlugin;
    }

    @ApiStatus.Internal
    public static void start(BlockParticlesPlugin plugin) {
        BlockParticlesProvider.blockParticlesPlugin = plugin;
    }

    @ApiStatus.Internal
    public static void stop() {
        BlockParticlesProvider.blockParticlesPlugin = null;
    }
}