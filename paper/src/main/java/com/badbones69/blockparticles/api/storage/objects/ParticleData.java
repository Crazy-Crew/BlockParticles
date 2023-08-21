package com.badbones69.blockparticles.api.storage.objects;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticleData {

    @Expose
    private final ArrayList<CustomLocation> locations = new ArrayList<>();

    public void addLocation(CustomLocation location) {
        this.locations.add(location);
    }

    public void removeLocation(CustomLocation location) {
        this.locations.add(location);
    }

    public List<CustomLocation> getLocations() {
        return Collections.unmodifiableList(this.locations);
    }
}