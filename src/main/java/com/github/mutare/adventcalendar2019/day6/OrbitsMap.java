package com.github.mutare.adventcalendar2019.day6;

import java.util.*;

import static java.util.Arrays.asList;

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

    public int getOrbitsNoBetween(String you, String san) {
        List<OrbitingObject> youRoute = routeOf(you);
        List<OrbitingObject> sanRoute = routeOf(san);
        Collections.reverse(youRoute);
        Collections.reverse(sanRoute);
        OrbitingObject meetPoint = null;
        for(OrbitingObject orbitingObject : youRoute) {
            if (sanRoute.contains(orbitingObject)) {
                meetPoint = orbitingObject;
                break;
            }
        }
        int r = 0;
        for (int i = 1 ; i < youRoute.size() ; i ++){
            if (youRoute.get(i).equals(meetPoint)) break;
            r++;
        }
        for (int i = 1 ; i < sanRoute.size() ; i ++){
            if (sanRoute.get(i).equals(meetPoint)) break;
            r++;
        }

        return r;
    }

    private List<OrbitingObject> routeOf(String name) {
        OrbitingObject orbitingObject = orbitingObjects.get(name);
        if (orbitingObject.centralObject == null) {
            return new LinkedList<>(asList(orbitingObject));
        }
        List<OrbitingObject> orbitingObjects = routeOf(this.orbitingObjects.get(name).centralObject.name);
        orbitingObjects.add(orbitingObject);
        return orbitingObjects;
    }


}
