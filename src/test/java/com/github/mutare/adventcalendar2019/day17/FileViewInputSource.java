package com.github.mutare.adventcalendar2019.day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.arraycopy;

public class FileViewInputSource {
    public char[][] getView(String path) throws IOException {
        List<String> lines = new LinkedList<>();
        try (InputStream is = FileViewInputSource.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        char[][] view = new char[lines.size()][lines.get(0).length()];
        for (int i = 0 ; i < lines.size() ; i++) {
            String line = lines.get(i);
            arraycopy(line.toCharArray(), 0, view[i], 0, line.toCharArray().length);
        }
        return view;
    }
}
