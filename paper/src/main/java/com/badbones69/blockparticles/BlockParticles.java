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

        this.plugin.getCrazyManager().getStorageManager().getParticleDataManager().addParticleData("love_well", new Location(getServer().getWorld("world"), 3.0, 3.3, 3.5));
        //this.crazyManager.getStorageManager().getParticleDataManager().addParticleData("small_love_well", new Location(getServer().getWorld("world"), 3.3, 5.4, 1.1));
    }

    @Override
    public void onDisable() {
        this.plugin.disable();
    }
}