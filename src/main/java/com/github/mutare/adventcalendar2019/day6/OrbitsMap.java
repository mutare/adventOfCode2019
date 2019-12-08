package com.github.mutare.adventcalendar2019.day6;

import java.util.*;

public class OrbitsMap {

    Map<String, OrbitingObject> orbitingObjects = new HashMap<>();

    public OrbitsMap(Collection<Orbit> orbits) {
        for(Orbit orbit : orbits) {
            OrbitingObject central = getByName(orbit.central);
            OrbitingObject orbiting = getByName(orbit.orbiting);
            orbiting.centralObject = central;
            central.addOrbiting(orbiting);
        }
    }

    private OrbitingObject getByName(String name) {
        if (!orbitingObjects.containsKey(name)) {
            orbitingObjects.put(name, new OrbitingObject(name));
        }
        return orbitingObjects.get(name);
    }

    public int getOrbitsNo() {
        int orbits = 0;
        for(OrbitingObject orbitingObject : orbitingObjects.values()) {
            orbits += routeToCentral(orbitingObject);
        }
        return orbits;
    }

    private int routeToCentral(OrbitingObject orbitingObject) {
        if (orbitingObject.centralObject == null) {
            return 0;
        }
        return 1 + routeToCentral(orbitingObject.centralObject);
    }
}
