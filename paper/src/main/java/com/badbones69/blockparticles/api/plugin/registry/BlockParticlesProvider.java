package com.badbones69.blockparticles.api.plugin.registry;

import com.badbones69.blockparticles.api.plugin.InternalPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class BlockParticlesProvider {

    private static InternalPlugin internalPlugin = null;

    public static @NotNull InternalPlugin get() {
        InternalPlugin instance = BlockParticlesProvider.internalPlugin;

        if (instance == null) throw new RuntimeException("Failed to use the get() method. Contact the developer.");

        return internalPlugin;
    }

    @ApiStatus.Internal
    public static void start(InternalPlugin plugin) {
        BlockParticlesProvider.internalPlugin = plugin;
    }

    @ApiStatus.Internal
    public static void stop() {
        BlockParticlesProvider.internalPlugin = null;
    }
}