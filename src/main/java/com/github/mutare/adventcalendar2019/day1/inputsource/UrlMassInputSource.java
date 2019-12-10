package com.github.mutare.adventcalendar2019.day1.inputsource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

public class UrlMassInputSource implements MassInputSource {
    private final static String url = "https://adventofcode.com/2019/day/1/input";

    @Override
    public Stream<Integer> getMasses() throws IOException {
        Collection<Integer> masses = new LinkedList<>();
        try (InputStream is = new URL(url).openStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            masses.add(Integer.parseInt(bufferedReader.readLine()));
        }
        return masses.stream();
    }
}
