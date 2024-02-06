package com.ryderbelserion.blockparticles;

import com.ryderbelserion.blockparticles.commands.ParticleCommand;
import com.ryderbelserion.blockparticles.manager.ParticleManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.Timer;

public class BlockParticles extends JavaPlugin {

    @NotNull
    public static BlockParticles get() {
        return JavaPlugin.getPlugin(BlockParticles.class);
    }

    private ParticleManager particleManager;

    private Timer timer;

    @Override
    public void onEnable() {
        this.timer = new Timer();

        this.particleManager = new ParticleManager();

        registerCommand(getCommand("bp"), new ParticleCommand(), new ParticleCommand());
    }

    @Override
    public void onDisable() {
        if (this.timer != null) this.timer.cancel();
    }

    @NotNull
    public ParticleManager getParticleManager() {
        return this.particleManager;
    }

    @NotNull
    public Timer getTimer() {
        return this.timer;
    }

    private void registerCommand(PluginCommand pluginCommand, TabCompleter tabCompleter, CommandExecutor commandExecutor) {
        if (pluginCommand != null) {
            pluginCommand.setExecutor(commandExecutor);

            if (tabCompleter != null) pluginCommand.setTabCompleter(tabCompleter);
        }
    }
}