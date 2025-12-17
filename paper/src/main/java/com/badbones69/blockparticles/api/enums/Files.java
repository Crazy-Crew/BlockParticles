package com.badbones69.blockparticles.api.enums;

import com.badbones69.blockparticles.BlockParticles;
import com.ryderbelserion.fusion.core.api.exceptions.FusionException;
import com.ryderbelserion.fusion.paper.files.PaperFileManager;
import com.ryderbelserion.fusion.paper.files.types.PaperCustomFile;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import java.nio.file.Path;
import java.util.Optional;

public enum Files {

    config("config.yml"),
    data("data.yml");

    private final BlockParticles plugin = BlockParticles.getPlugin();

    private final Path dataPath = this.plugin.getDataPath();

    private final PaperFileManager fileManager = this.plugin.getFileManager();

    private final Path path;

    /**
     * A constructor to build a file
     *
     * @param fileName the name of the file
     */
    Files(final String fileName) {
        this.path = this.dataPath.resolve(fileName);
    }

    public final YamlConfiguration getConfiguration() {
        @NotNull final Optional<PaperCustomFile> customFile = this.fileManager.getPaperFile(this.path);

        if (customFile.isEmpty()) {
            throw new FusionException("Could not find custom file for " + this.path);
        }

        return customFile.get().getConfiguration();
    }

    public void save() {
        this.fileManager.saveFile(this.path);
    }
}