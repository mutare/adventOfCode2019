package com.github.mutare.adventcalendar2019.day16;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;

public class FlawedFrequencyTransmission {

    private int index;
    private int[] inputInt;
    private int[] basePhase = {0, 1, 0, -1};
    private int repeat;
    private int[] output;

    public FlawedFrequencyTransmission(String input, int repeat) {
        this.inputInt = new int[input.length() * repeat];
        int[] singleArray = stream(input.split("")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < repeat; i++)
            System.arraycopy(singleArray, 0, inputInt, i * input.length(), singleArray.length);
        this.repeat = repeat;
        this.index = repeat > 1 ? Integer.parseInt(input.substring(0, 7)) : 0;
    }

    public void decode(int noPfPhases) {
        for (int p = 0; p < noPfPhases; p++) {
            if (repeat > 1) decodeByModBackwards();
            else decodeBySumming();
        }
    }

    private void decodeByModBackwards() {
        int[] temp = new int[inputInt.length];
        int mod = 0;
        for (int i = inputInt.length - 1; i > inputInt.length / 2; i--) {
            mod = temp[i] = (inputInt[i] + mod) % 10;
        }
        inputInt = temp;
    }

    private void decodeBySumming() {
        int[] temp = new int[inputInt.length];
        for (int i = 0; i < inputInt.length; i++) {
            int sum = 0;

            for (int j = i; j < inputInt.length; j = j + 4 * (i + 1)) {
                for (int k = j; k < j + i + 1 && k < inputInt.length; k++) {
                    sum += inputInt[k];
                }
                for (int k = j; k < j + i + 1 && k + 2 * (i + 1) < inputInt.length; k++) {
                    sum -= inputInt[k + 2 * (i + 1)];
                }
            }
            temp[i] = sum > 0 ? sum % 10 : -sum % 10;
        }
        inputInt = temp;
    }

    public String getOutput() {
        if (repeat > 1) {
            return stream(copyOfRange(inputInt, index, index + 8)).boxed().map(Object::toString).reduce(String::concat).orElseThrow();
        } else {
            return stream(inputInt).boxed().map(Object::toString).reduce(String::concat).orElseThrow();
        }
    }
}
