package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.CrazyManager;
import com.badbones69.blockparticles.controllers.Fountains;
import com.ryderbelserion.ruby.other.config.FileManager;
import com.ryderbelserion.ruby.paper.PaperPlugin;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private PaperPlugin paper;
    private FileManager fileManager;

    private CrazyManager crazyManager;

    // v1
    private Fountains fountains;
    private Methods methods;
    
    @Override
    public void onEnable() {
        this.paper = new PaperPlugin(this);

        this.paper.enable(false);

        this.fileManager = new FileManager();

        this.crazyManager = new CrazyManager();
        this.crazyManager.load(true);

        this.crazyManager.getStorageManager().getParticleDataManager().addParticleData("love_well", new Location(getServer().getWorld("world"), 3.0, 3.3, 3.5));
        //this.crazyManager.getStorageManager().getParticleDataManager().addParticleData("small_love_well", new Location(getServer().getWorld("world"), 3.3, 5.4, 1.1));
    }

    @Override
    public void onDisable() {
        this.crazyManager.reload(true);

        this.paper.disable();

        //this.methods.kill();
    }

    public PaperPlugin getPaper() {
        return this.paper;
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