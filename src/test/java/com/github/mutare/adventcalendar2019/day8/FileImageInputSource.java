package com.github.mutare.adventcalendar2019.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FileImageInputSource {
    public int[] getImage() throws IOException {
        List<Integer> image = new LinkedList<>();
        try (InputStream is = FileImageInputSource.class.getResourceAsStream("/day8/data")) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    image.add(Integer.parseInt(Character.toString(c)));
                }
            }
        }
        return image.stream().mapToInt(value -> value).toArray();
    }
}
