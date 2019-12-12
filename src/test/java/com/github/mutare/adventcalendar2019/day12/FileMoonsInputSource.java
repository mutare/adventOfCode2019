package com.github.mutare.adventcalendar2019.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

class FileMoonsInputSource {
    List<Moon> getMoons(String path) throws IOException {
        List<Moon> moons = new LinkedList<>();
        try (InputStream is = FileMoonsInputSource.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Moon moon = new Moon();
                Moon reduce = Arrays.stream(line.substring(1, line.length() - 1).split(",")).reduce(moon, (moon1, s) -> {
                    String[] c = s.split("=");
                    switch(c[0].trim()) {
                        case "x" : moon1.position.x = Integer.parseInt(c[1]); break;
                        case "y" : moon1.position.y = Integer.parseInt(c[1]); break;
                        case "z" : moon1.position.z = Integer.parseInt(c[1]); break;
                    }
                    return moon1;
                }, (moon12, moon2) -> null);
                moons.add(reduce);
            }
        }
        return moons;
    }
}
