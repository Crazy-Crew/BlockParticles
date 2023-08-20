package com.badbones69.common.plugin.registry;

import com.badbones69.common.BlockParticlesPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class BlockParticleProvider {

    private static BlockParticlesPlugin ruby = null;

    public static @NotNull BlockParticlesPlugin get() {
        BlockParticlesPlugin instance = BlockParticleProvider.ruby;

        if (instance == null) throw new RuntimeException("Failed to utilize provider. Did it get enabled?");

        return ruby;
    }

    @ApiStatus.Internal
    public static void start(BlockParticlesPlugin ruby) {
        BlockParticleProvider.ruby = ruby;
    }

    @ApiStatus.Internal
    public static void stop() {
        BlockParticleProvider.ruby = null;
    }
}