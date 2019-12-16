package com.github.mutare.adventcalendar2019.day13;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ScreenTest {
    @Test
    @Ignore("just screen test no AoC case")
    public void test() throws IOException, InterruptedException {
        Screen screen = new Screen(null);
        screen.setVisible(true);
        screen.draw(loadGrid("/day13/test"));
        Thread.sleep(10000);
    }

    private int[][] loadGrid(String s) throws IOException {
        List<String> lines = new ArrayList<>();
        try (InputStream is = FileProgramInputSource.class.getResourceAsStream("/day13/test")) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        int[][] grid = new int[lines.get(0).length()][lines.size()];
        for (int j = 0; j < lines.size(); j++) {
            String line = lines.get(j);
            for (int i = 0; i < line.length(); i++) {
                grid[i][j] = Integer.parseInt(Character.toString(line.charAt(i)));
            }
        }
        return grid;
    }
}
