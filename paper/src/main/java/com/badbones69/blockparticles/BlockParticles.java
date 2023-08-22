package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.plugin.BlockParticlesPlugin;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private BlockParticlesPlugin plugin;
    
    @Override
    public void onEnable() {
        this.plugin = new BlockParticlesPlugin(this);
        this.plugin.enable();

        this.plugin.getCrazyManager().getStorageManager().getParticleDataManager().load();

        this.plugin.getCrazyManager().getStorageManager().getParticleDataManager().addParticleData("love_well", new Location(getServer().getWorld("world"), 3.0, 3.3, 3.5));
        this.plugin.getCrazyManager().getStorageManager().getParticleDataManager().addParticleData("love_well", new Location(getServer().getWorld("world_the_end"), 5.1, 71.0, 90.3));

        this.plugin.getCrazyManager().getStorageManager().getParticleDataManager().addParticleData("small_love_well", new Location(getServer().getWorld("world"), 3.3, 5.4, 1.1));
        this.plugin.getCrazyManager().getStorageManager().getParticleDataManager().addParticleData("small_love_well", new Location(getServer().getWorld("world"), 5.3, 8.4, 1.3));
    }

    @Override
    public void onDisable() {
        this.plugin.disable();
    }
}