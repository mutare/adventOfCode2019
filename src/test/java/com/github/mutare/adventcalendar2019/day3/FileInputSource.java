package com.github.mutare.adventcalendar2019.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

class FileInputSource {

    private List<String> lines = new LinkedList<>();

    FileInputSource() {
        try {
            loadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadData() throws IOException {
        try (InputStream is = FileInputSource.class.getResourceAsStream("/day3/data")) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }

    }

    public Stream<String> getLine(int lineNo) {
        return Stream.of(lines.get(lineNo).split(","));
    }
}
