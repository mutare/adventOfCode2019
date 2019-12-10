package com.github.mutare.adventcalendar2019.day6;

class OrbitingObject {
    final String name;
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

}
