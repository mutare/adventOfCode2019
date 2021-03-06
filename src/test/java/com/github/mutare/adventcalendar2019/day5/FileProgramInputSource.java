package com.github.mutare.adventcalendar2019.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

class FileProgramInputSource {
    public long[] getProgram() throws IOException {
        Collection<Integer> program = new LinkedList<>();
        try (InputStream is = FileProgramInputSource.class.getResourceAsStream("/day5/data")) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                program.addAll(Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
            }
        }
        return program.stream().mapToLong(Integer::longValue).toArray();
    }
}
