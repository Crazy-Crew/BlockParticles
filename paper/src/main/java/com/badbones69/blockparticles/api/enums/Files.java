package com.badbones69.blockparticles.api.enums;

import com.badbones69.blockparticles.BlockParticles;
import com.ryderbelserion.vital.paper.api.files.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public enum Files {

    config("config.yml"),
    data("data.yml");


    private final String fileName;
    private final String folder;

    private final BlockParticles plugin = BlockParticles.getPlugin();

    private final FileManager fileManager = this.plugin.getFileManager();

    /**
     * A constructor to build a file
     *
     * @param fileName the name of the file
     */
    Files(final String fileName, final String folder) {
        this.fileName = fileName;
        this.folder = folder;
    }

    /**
     * A constructor to build a file
     *
     * @param fileName the name of the file
     */
    Files(final String fileName) {
        this.fileName = fileName;
        this.folder = "";
    }

    public final YamlConfiguration getConfiguration() {
        return this.fileManager.getFile(this.fileName).getConfiguration();
    }

    public void reload() {
        this.fileManager.addFile(new File(this.plugin.getDataFolder(), this.fileName));
    }

    public void save() {
        this.fileManager.saveFile(this.fileName);
    }

    public final File getFile() {
        return new File(this.folder.isEmpty() ? this.plugin.getDataFolder() : new File(this.plugin.getDataFolder(), this.folder), this.fileName);
    }
}