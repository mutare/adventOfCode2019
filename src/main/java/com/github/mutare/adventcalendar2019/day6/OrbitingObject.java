package com.github.mutare.adventcalendar2019.day6;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class OrbitingObject {
    private String name;
    private Set<OrbitingObject> orbitingObjects = new HashSet<>();
    OrbitingObject centralObject;

    OrbitingObject(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrbitingObject)) {
            return false;
        }
        return this.name.equals(((OrbitingObject) obj).name);
    }

    void addOrbiting(OrbitingObject orbiting) {
        orbitingObjects.add(orbiting);
    }
}
