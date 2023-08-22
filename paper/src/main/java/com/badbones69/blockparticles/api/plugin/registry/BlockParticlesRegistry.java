package com.badbones69.blockparticles.api.plugin.registry;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.plugin.BlockParticlesPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import java.lang.reflect.Method;

public class BlockParticlesRegistry {

    private static final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    private static final Method start;
    private static final Method stop;

    static {
        try {
            start = BlockParticlesProvider.class.getDeclaredMethod("start", BlockParticlesPlugin.class);
            start.setAccessible(true);

            stop = BlockParticlesProvider.class.getDeclaredMethod("stop");
            stop.setAccessible(true);
        } catch (NoSuchMethodException exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }

    @ApiStatus.Internal
    public static void start(BlockParticlesPlugin blockParticlesPlugin) {
        try {
            start.invoke(null, blockParticlesPlugin);
        } catch (Exception exception) {
            plugin.getLogger().severe("Failed to enable the block particles plugin.");
            plugin.getLogger().severe("Reason: " + exception.getMessage());
        }
    }

    @ApiStatus.Internal
    public static void stop() {
        try {
            stop.invoke(null);
        } catch (Exception exception) {
            plugin.getLogger().severe("Failed to disable block particles plugin.");
            plugin.getLogger().severe("Reason: " + exception.getMessage());
        }
    }
}