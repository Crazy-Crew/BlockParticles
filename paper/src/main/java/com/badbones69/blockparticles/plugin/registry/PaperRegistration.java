package com.badbones69.blockparticles.plugin.registry;

import com.badbones69.blockparticles.plugin.PaperImpl;
import org.jetbrains.annotations.ApiStatus;
import java.lang.reflect.Method;

public class PaperRegistration {

    private static final Method start;
    private static final Method stop;

    static {
        try {
            start = PaperProvider.class.getDeclaredMethod("start", PaperImpl.class);
            start.setAccessible(true);

            stop = PaperProvider.class.getDeclaredMethod("stop");
            stop.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @ApiStatus.Internal
    public static void start(PaperImpl paper) {
        try {
            start.invoke(null, paper);
        } catch (Exception exception) {
            System.out.println("[ERROR] Failed to enable paper provider");
            System.out.println("[ERROR] Reason: " + exception.getMessage());
        }
    }

    @ApiStatus.Internal
    public static void stop() {
        try {
            stop.invoke(null);
        } catch (Exception exception) {
            System.out.println("[ERROR] Failed to disable paper provider");
            System.out.println("[ERROR] Reason: " + exception.getMessage());
        }
    }
}