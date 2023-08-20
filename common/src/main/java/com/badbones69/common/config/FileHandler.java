package com.badbones69.common.config;

import com.badbones69.common.BlockParticlesPlugin;
import com.badbones69.common.config.types.file.JsonFile;
import com.badbones69.common.plugin.registry.BlockParticleProvider;
import java.io.File;

public class FileHandler implements FileContext {

    private final BlockParticlesPlugin blockPlugin = BlockParticleProvider.get();

    private JsonFile jsonFile;

    @Override
    public void addFile(FileEngine file) {
        switch (file.getFileType()) {
            case JSON -> {
                this.jsonFile = new JsonFile(file);
                this.jsonFile.load();
            }

            case YAML -> this.blockPlugin.getFancyLogger().info(file.getFileType().getName() + " is not supported yet.");
        }
    }

    @Override
    public void saveFile(FileEngine file) {
        switch (file.getFileType()) {
            case JSON -> {
                this.jsonFile = new JsonFile(file);
                this.jsonFile.save();
            }

            case YAML -> this.blockPlugin.getFancyLogger().info(file.getFileType().getName() + " is not supported yet.");
        }
    }

    @Override
    public void removeFile(FileEngine file) {
        File type = file.getFilePath().toFile();

        if (type.exists()) type.delete();
    }

    @Override
    public File getFile(FileEngine file) {
        return file.getNewFile();
    }
}