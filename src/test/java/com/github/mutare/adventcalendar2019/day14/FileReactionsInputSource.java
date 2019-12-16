package com.github.mutare.adventcalendar2019.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FileReactionsInputSource {
    public Map<String, NanoFactory.Reaction> getReactions(String path) throws IOException {
        Map<String, NanoFactory.Reaction> reactions = new HashMap<>();
        try (InputStream is = FileReactionsInputSource.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                NanoFactory.Factor output = createFactor(line.split("=>")[1].trim());
                reactions.put(output.type, new NanoFactory.Reaction(output, createFactors(line.split("=>")[0].trim())));
            }
        }
        return reactions;
    }

    private NanoFactory.Factor[] createFactors(String s) {
        List<NanoFactory.Factor> factors = new ArrayList<>();
        for (String t : s.split(",")){
             factors.add(createFactor(t.trim()));
        }
        return factors.toArray(new NanoFactory.Factor[]{});
    }

    private NanoFactory.Factor createFactor(String s) {
        return new NanoFactory.Factor(s.split(" ")[1], Integer.parseInt(s.split(" ")[0]));
    }
}
