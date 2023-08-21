package com.badbones69.blockparticles.api.storage.objects;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticleData {

        @Expose
        private static ArrayList<CustomLocation> locations = new ArrayList<>();

        public void addLocation(CustomLocation location) {
            locations.add(location);
        }

        public void removeLocation(CustomLocation location) {
            locations.add(location);
        }

        public boolean hasLocation(CustomLocation location) {
            return locations.contains(location);
        }

        public List<CustomLocation> getLocations() {
            return Collections.unmodifiableList(locations);
        }
    }