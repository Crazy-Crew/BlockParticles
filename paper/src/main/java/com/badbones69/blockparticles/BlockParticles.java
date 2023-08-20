package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.CrazyManager;
import com.badbones69.blockparticles.api.FileManager;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.persist.Locations;
import com.badbones69.blockparticles.plugin.PaperImpl;
import com.badbones69.common.config.FileHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockParticles extends JavaPlugin {

    private PaperImpl paper;
    private FileHandler fileHandler;

    private FileManager fileManager;

    private CrazyManager crazyManager;

    private Methods methods;

    private Fountains fountains;
    
    @Override
    public void onEnable() {
        this.paper = new PaperImpl(this);

        this.paper.enable(false);

        this.fileHandler = new FileHandler();

        Locations locations = new Locations(getDataFolder().toPath());

        this.fileHandler.addFile(locations);

        locations.addLocation("extra", "extra2");
        locations.addLocation("other", "other2");

        this.fileHandler.saveFile(locations);

        this.paper.getFancyLogger().info(String.valueOf(locations.getLocations().size()));

        //this.fileManager = new FileManager();
        //this.fileManager.setup();

        //this.methods = new Methods();

        //this.crazyManager = new CrazyManager();

        //getServer().getPluginManager().registerEvents(this.fountains = new Fountains(), this);
        //getServer().getPluginManager().registerEvents(new FountainListener(), this);
    }

    @Override
    public void onDisable() {
        this.paper.disable();

        //this.methods.kill();
    }

    public PaperImpl getPaper() {
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