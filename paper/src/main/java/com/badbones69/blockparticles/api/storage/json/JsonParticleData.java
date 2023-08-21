package com.badbones69.blockparticles.api.storage.json;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.storage.objects.CustomLocation;
import com.badbones69.blockparticles.api.storage.objects.ParticleData;
import com.badbones69.blockparticles.plugin.config.json.CustomLocationTypeAdapter;
import com.badbones69.blockparticles.plugin.config.json.LocationTypeAdapter;
import com.badbones69.common.config.FileEngine;
import com.badbones69.common.config.types.FileType;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public sealed class JsonParticleData extends FileEngine permits JsonParticleDataManager {

    final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    public JsonParticleData(Path path) {
        super("locations.json", path, FileType.JSON);

        GsonBuilder builder = new GsonBuilder().disableHtmlEscaping()
                .registerTypeAdapter(Location.class, new LocationTypeAdapter())
                .registerTypeAdapter(CustomLocation.class, new CustomLocationTypeAdapter())
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .excludeFieldsWithoutExposeAnnotation();

        setGsonBuilder(builder);
    }

    @Expose
    protected final ConcurrentHashMap<String, ParticleData> locations = new ConcurrentHashMap<>();
}