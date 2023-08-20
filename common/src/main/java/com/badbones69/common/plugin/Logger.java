package com.badbones69.common.plugin;

import com.badbones69.common.BlockParticlesPlugin;
import com.badbones69.common.plugin.registry.BlockParticleProvider;
import org.jetbrains.annotations.NotNull;

public class Logger {

    private final @NotNull BlockParticlesPlugin blockPlugin = BlockParticleProvider.get();

    private final String pluginName;

    public Logger(String pluginName) {
        this.pluginName = pluginName;
    }

    public void info(String message) {
        send("[" + this.pluginName + "] <blue>INFO</blue> " + message);
    }

    public void warn(String message) {
        send("[" + this.pluginName + "] <gold>WARNING</gold> " + message);
    }

    public void error(String message) {
        send("[" + this.pluginName + "] <red>ERROR</red> " + message);
    }

    public void debug(String message) {
        send("[" + this.pluginName + "] <yellow>DEBUG</yellow> " + message);
    }

    private void send(String message) {
        this.blockPlugin.getAudience().console().sendMessage(this.blockPlugin.getAdventure().parse(message));
    }
}