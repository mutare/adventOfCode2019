package com.github.mutare.adventcalendar2019.day1.inputsource;

import java.io.IOException;
import java.util.stream.Stream;

public interface MassInputSource {
    Stream<Integer> getMasses() throws IOException;
}
