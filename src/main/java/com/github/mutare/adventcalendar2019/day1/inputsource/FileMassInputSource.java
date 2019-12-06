package com.github.mutare.adventcalendar2019.day1.inputsource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

public class FileMassInputSource implements MassInputSource {
    @Override
    public Stream<Integer> getMasses() throws IOException {
        Collection<Integer> masses = new LinkedList<>();
        try (InputStream is = FileMassInputSource.class.getResourceAsStream("/day1/data")) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                masses.add(Integer.parseInt(line));
            }
        }
        return masses.stream();
    }
}
