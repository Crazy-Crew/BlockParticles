package com.badbones69.blockparticles.plugin.registry;

import com.badbones69.blockparticles.plugin.PaperImpl;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class PaperProvider {

    private static PaperImpl paper = null;

    public static @NotNull PaperImpl get() {
        PaperImpl instance = PaperProvider.paper;

        if (instance == null) throw new RuntimeException("Failed to utilize paper provider. Did it get enabled?");

        return paper;
    }

    @ApiStatus.Internal
    public static void start(PaperImpl paper) {
        PaperProvider.paper = paper;
    }

    @ApiStatus.Internal
    public static void stop() {
        PaperProvider.paper = null;
    }
}