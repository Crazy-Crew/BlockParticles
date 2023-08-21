package com.badbones69.common.config;

import com.badbones69.common.BlockParticlesPlugin;
import com.badbones69.common.config.types.FileType;
import com.badbones69.common.plugin.registry.BlockParticleProvider;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.nio.file.Path;

public abstract class FileEngine {

    private final BlockParticlesPlugin blockPlugin = BlockParticleProvider.get();

    private final String fileName;
    private final Path filePath;
    private final FileType fileType;

    private GsonBuilder gson;

    public FileEngine(String fileName, Path filePath, FileType fileType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public void setGsonBuilder(GsonBuilder gson) {
        if (this.fileType != FileType.JSON) {
            this.blockPlugin.getFancyLogger().error("You cannot use json if the file type isn't " + FileType.JSON.getName());

            return;
        }

        this.gson = gson;
    }

    public FileType getFileType() {
        return this.fileType;
    }

    public GsonBuilder getGson() {
        return this.gson;
    }

    public Path getFilePath() {
        return this.filePath;
    }

    public File getNewFile() {
        return this.filePath.resolve(this.fileName).toFile();
    }

    public String getFileName() {
        return this.fileName;
    }
}