package com.github.mutare.adventcalendar2019.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class FileOrbitInputSource {
    Collection<Orbit> getOrbits(String fileName) throws IOException {
        Collection<Orbit> orbits = new LinkedList<>();
        try (InputStream is = FileOrbitInputSource.class.getResourceAsStream(fileName)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0) {
                    orbits.add(Orbit.createOrbit(line));
                }
            }
        }
        return orbits;
    }

}
