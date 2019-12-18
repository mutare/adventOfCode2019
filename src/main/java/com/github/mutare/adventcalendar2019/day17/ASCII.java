package com.github.mutare.adventcalendar2019.day17;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.arraycopy;

public class ASCII {

    private char[][] view;
    private IntcodeComputer intcodeComputer;

    public ASCII(long... program) {
        intcodeComputer = new IntcodeComputer(program);
        IntcodeComputer.Result proccess;
        List<String> lines = new ArrayList<>();
        String line = "";
        while ((proccess = intcodeComputer.proccess()).type != IntcodeComputer.Result.Type.END) {
            //System.out.print((char) proccess.value.intValue());
            if (proccess.value == 10) {
                lines.add(line);
                line = "";
            } else {
                line = line.concat(String.valueOf((char) proccess.value.intValue()));
            }
        }

        view = new char[lines.size()][lines.get(0).length()];
        for (int i = 0 ; i < lines.size() ; i++) {
            String viewLine = lines.get(i);
            arraycopy(viewLine.toCharArray(), 0, view[i], 0, viewLine.toCharArray().length);
        }
    }

    public ASCII(char[][] view) {
        this.view = view;
    }

    public void markIntersections() {
        for (int i = 1; i < view[0].length - 2; i++) {
            for (int j = 1; j < view.length - 2; j++) {
                if (view[j][i] == '#' && view[j - 1][i] == '#' && view[j + 1][i] == '#' && view[j][i - 1] == '#' && view[j][i + 1] == '#') {
                    view[j][i] = 'O';
                }
            }
        }
    }

    public int getIntersectionsAlignmentSum() {
        int sum = 0;
        for (int i = 1; i < view[0].length - 2; i++) {
            for (int j = 1; j < view.length - 2; j++) {
                if (view[j][i] == 'O') {
                    sum += j * i;
                }
            }
        }
        return sum;
    }

    public void printView(PrintStream out) {
        for (char[] v : view) {
            for (char c : v)
                out.print(c);
            out.println();
        }
    }
}
