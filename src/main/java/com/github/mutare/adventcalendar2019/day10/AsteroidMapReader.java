package com.github.mutare.adventcalendar2019.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

class AsteroidMapReader {
    public char[][] getMap(String path) throws IOException {
        List<String> mapLines = new LinkedList<>();
        try (InputStream is = AsteroidMapReader.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                mapLines.add(line);
            }
        }
        char[][] map = new char[mapLines.size()][mapLines.iterator().next().length()];
        for (int j = 0; j < mapLines.size(); j++) {
            String line = mapLines.get(j);
            for (int i = 0; i < line.length(); i++) {
                map[j][i] = line.charAt(i);
            }
        }
        return map;
    }
}
