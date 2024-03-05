package com.ryderbelserion.blockparticles;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.Timer;

public class BlockParticles extends JavaPlugin {

    @NotNull
    public static BlockParticles get() {
        return JavaPlugin.getPlugin(BlockParticles.class);
    }

    private Timer timer;

    @Override
    public void onEnable() {
        this.timer = new Timer();
    }

    @Override
    public void onDisable() {
        if (this.timer != null) {
            // Cancel it.
            this.timer.cancel();
            // Set it to null.
            this.timer = null;
        }
    }

    @NotNull
    public Timer getTimer() {
        return this.timer;
    }
}