package com.badbones69.common.plugin.registry;

import com.badbones69.common.BlockParticlesPlugin;
import org.jetbrains.annotations.ApiStatus;
import java.lang.reflect.Method;

public class BlockParticleRegistration {

    private static final Method start;
    private static final Method stop;

    static {
        try {
            start = BlockParticleProvider.class.getDeclaredMethod("start", BlockParticlesPlugin.class);
            start.setAccessible(true);

            stop = BlockParticleProvider.class.getDeclaredMethod("stop");
            stop.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @ApiStatus.Internal
    public static void start(BlockParticlesPlugin ruby) {
        try {
            start.invoke(null, ruby);
        } catch (Exception exception) {
            System.out.println("[ERROR] Failed to enable provider");
            System.out.println("[ERROR] Reason: " + exception.getMessage());
        }
    }

    @ApiStatus.Internal
    public static void stop() {
        try {
            stop.invoke(null);
        } catch (Exception exception) {
            System.out.println("[ERROR] Failed to disable provider");
            System.out.println("[ERROR] Reason: " + exception.getMessage());
        }
    }
}