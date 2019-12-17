package com.github.mutare.adventcalendar2019.day16;

import java.util.stream.Stream;

import static com.google.common.collect.Streams.zip;
import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

public class FlawedFrequencyTransmission {

    private int[] inputInt;
    private int inputLength;
    private int[] basePhase = {0, 1, 0, -1};

    public FlawedFrequencyTransmission(String input, int repeat) {
        this.inputLength = repeat * input.length();
        this.inputInt = new int[input.length() * repeat];
        int[] inputSingle = stream(input.split("")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0 ; i < repeat ; i++) {
            arraycopy(inputSingle, 0,  inputInt, i * inputSingle.length, inputSingle.length);
        }
    }

    private Stream<Integer> phaseStream() {
        return Stream.iterate(0, i -> (i + 1) % 4).map(integer -> basePhase[integer]);
    }

    private Stream<Integer> inputStream() {
        return stream(inputInt).boxed();
    }

    private Integer getValueForPhase(Integer phaseNo) {
        return zip(getPhaseStreamFor(phaseNo), inputStream(), (phase, input) -> phase * input).reduce(0, Integer::sum);
    }

    private Stream<Integer> getPhaseStreamFor(int phase) {
        return phaseStream().flatMap(integer -> Stream.generate(() -> integer).limit(phase)).limit(inputLength + 1).skip(1);
    }

    public void decode(int noPfPhases) {
        System.out.println("Start");

        for (int j = 0; j < noPfPhases; j++) {
            inputInt = Stream.iterate(1, i -> i + 1).map(this::getValueForPhase).map(integer -> integer % 10).map(Math::abs).limit(inputLength).mapToInt(value -> value).toArray();
            System.out.println("Phase : " + j);
        }
    }

    public String getOutput() {
        return stream(inputInt).boxed().map(Object::toString).reduce(String::concat).orElseThrow();
    }
}
