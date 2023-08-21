package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.CrazyManager;
import com.badbones69.blockparticles.api.FileManager;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.plugin.PaperImpl;
import com.badbones69.common.config.FileHandler;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private PaperImpl paper;
    private FileHandler handler;

    private CrazyManager crazyManager;

    // v1
    private FileManager fileManager;
    private Fountains fountains;
    private Methods methods;
    
    @Override
    public void onEnable() {
        this.paper = new PaperImpl(this);

        this.paper.enable(false);

        this.handler = new FileHandler();

        this.crazyManager = new CrazyManager();
        this.crazyManager.load(true);

        this.crazyManager.getStorageManager().getParticleDataManager().addParticleData("love_well", new Location(getServer().getWorld("world"), 3.0, 3.3, 3.5));
        this.crazyManager.getStorageManager().getParticleDataManager().addParticleData("small_love_well", new Location(getServer().getWorld("world"), 3.3, 5.4, 1.1));
    }

    @Override
    public void onDisable() {
        this.crazyManager.reload(true);

        this.paper.disable();

        //this.methods.kill();
    }

    public PaperImpl getPaper() {
        return this.paper;
    }

    public FileHandler getHandler() {
        return this.handler;
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public CrazyManager getCrazyManager() {
        return this.crazyManager;
    }

    public Methods getMethods() {
        return this.methods;
    }

    public Fountains getFountains() {
        return this.fountains;
    }
}