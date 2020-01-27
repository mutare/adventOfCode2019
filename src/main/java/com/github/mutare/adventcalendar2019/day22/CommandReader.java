package com.github.mutare.adventcalendar2019.day22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class CommandReader {
    List<String> getCommands(String path) throws IOException {
        List<String> commands = new LinkedList<>();
        try (InputStream is = CommandReader.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                commands.add(line);
            }
        }
        return commands;
    }

}
