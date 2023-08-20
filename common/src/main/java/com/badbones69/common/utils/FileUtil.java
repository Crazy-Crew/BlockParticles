package com.badbones69.common.utils;

import com.badbones69.common.BlockParticlesPlugin;
import com.badbones69.common.plugin.registry.BlockParticleProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class FileUtil {

    private final BlockParticlesPlugin blockParticles = BlockParticleProvider.get();

    public void copyFiles(Path directory, String folder, List<String> names) {
        names.forEach(name -> copyFile(directory, folder, name));
    }

    public void copyFile(Path directory, String folder, String name) {
        File file = directory.resolve(name).toFile();

        if (file.exists()) return;

        File dir = directory.toFile();

        if (!dir.exists()) dir.mkdirs();

        ClassLoader loader = getClass().getClassLoader();

        String url = folder + "/" + name;

        URL resource = loader.getResource(url);

        if (resource == null) {
            this.blockParticles.getFancyLogger().error("Failed to find file: " + url);

            return;
        }

        try {
            grab(resource.openStream(), file);
        } catch (Exception exception) {
            this.blockParticles.getFancyLogger().error("Failed to copy file: " + url);
        }
    }

    private void grab(InputStream input, File output) throws Exception {
        try (InputStream inputStream = input; FileOutputStream outputStream = new FileOutputStream(output)) {
            byte[] buf = new byte[1024];
            int i;

            while ((i = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, i);
            }
        }
    }
}