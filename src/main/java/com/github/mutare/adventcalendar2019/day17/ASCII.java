package com.github.mutare.adventcalendar2019.day17;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.mutare.adventcalendar2019.day17.Robot.Direction.*;
import static java.lang.System.arraycopy;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class ASCII {

    private char[][] view;
    private long[] program;
    private long outputLongValue = -1;

    public ASCII(long... program) {
        this.program = program.clone();
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

    private String findRoute() {
        List<String> route = new ArrayList<>();
        Robot robot = getRobot();
        do {
            int moves = 0;
            route.add(turnOnScaffold(robot));
            while (isWayAhead(robot)) {
                robot.move();
                moves++;
            }
            route.add("" + moves);
        } while (!isDeadEnd(robot));

        return route.stream().reduce(String::concat).orElseThrow();
    }

    private String turnOnScaffold(Robot robot) {
        switch (robot.direction) {
            case NORTH:
                if (robot.x > 0 && view[robot.y][robot.x - 1] == '#' && robot.turn(WEST)) return "L";
                if (robot.x < view[0].length - 2 && view[robot.y][robot.x + 1] == '#' && robot.turn(EAST)) return "R";
            case SOUTH:
                if (robot.x < view[0].length - 2 && view[robot.y][robot.x + 1] == '#' && robot.turn(EAST)) return "L";
                if (robot.x > 0 && view[robot.y][robot.x - 1] == '#' && robot.turn(WEST)) return "R";
            case WEST:
                if (robot.y < view.length - 2 && view[robot.y + 1][robot.x] == '#' && robot.turn(SOUTH)) return "L";
                if (robot.y > 0 && view[robot.y - 1][robot.x] == '#' && robot.turn(NORTH)) return "R";
            case EAST:
                if (robot.y > 0 && view[robot.y - 1][robot.x] == '#' && robot.turn(NORTH)) return "L";
                if (robot.y < view.length - 2 && view[robot.y + 1][robot.x] == '#' && robot.turn(SOUTH)) return "R";
        }
        throw new RuntimeException("Oh no !!!");
    }

    private boolean isDeadEnd(Robot robot) {
        int ways = 0;
        if (robot.y < view.length - 2 && view[robot.y + 1][robot.x] == '#') ways++;
        if (robot.y > 0 && view[robot.y - 1][robot.x] == '#') ways++;
        if (robot.x < view[0].length - 2 && view[robot.y][robot.x + 1] == '#') ways++;
        if (robot.x > 0 && view[robot.y][robot.x - 1] == '#') ways++;
        return ways < 2;
    }

    private boolean isWayAhead(Robot robot) {
        Point pointAhead = robot.getPointAhead();
        if (pointAhead.x < 0 || pointAhead.x > view[0].length - 1 || pointAhead.y < 0 || pointAhead.y > view.length - 1)
            return false;
        return (view[pointAhead.y][pointAhead.x] == '#');
    }

    private Robot getRobot() {
        for (int i = 0; i < view[0].length - 1; i++) {
            for (int j = 0; j < view.length - 1; j++) {
                char c = view[j][i];
                switch (c) {
                    case '<':
                        return Robot.of(i, j, WEST);
                    case '>':
                        return Robot.of(i, j, EAST);
                    case '^':
                        return Robot.of(i, j, Robot.Direction.NORTH);
                    case 'v':
                        return Robot.of(i, j, Robot.Direction.SOUTH);
                }
            }
        }
        throw new RuntimeException("No robot found :(");
    }

    public void run(boolean go) {
        IntcodeComputer intcodeComputer = new IntcodeComputer(program);
        if (go) intcodeComputer.getMemory()[0] = 2;
        IntcodeComputer.Result proccess;
        List<String> lines = new ArrayList<>();
        String line = "";
        while (!asList(IntcodeComputer.Result.Type.INPUT, IntcodeComputer.Result.Type.END).contains((proccess = intcodeComputer.proccess()).type)) {
            if (proccess.value == 10) {
                lines.add(line);
                line = "";
            } else {
                line = line.concat(String.valueOf((char) proccess.value.intValue()));
            }
        }

        view = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            String viewLine = lines.get(i);
            arraycopy(viewLine.toCharArray(), 0, view[i], 0, viewLine.toCharArray().length);
        }

        if (!go) {
            return;
        }

        String route = findRoute();
        List<String> groups = findGroups(route);
        String inputCommands = createCommand(groups, route);

        long[] inputs = stream(inputCommands.split("")).map(s -> s.codePointAt(0)).mapToLong(Long::valueOf).toArray();
        int i = 0;
        int last = -1;
        while ((proccess = intcodeComputer.proccess(last != i ? inputs[i] : null)).type != IntcodeComputer.Result.Type.END) {
            last = i;
            if (proccess.type != IntcodeComputer.Result.Type.INPUT) {
                outputLongValue = proccess.value;
            } else {
                i++;
            }
        }
    }

    private String createCommand(List<String> groups, String route) {
        StringBuilder command = new StringBuilder();

        command.append(String.join(",", route.replace(groups.get(0), "A")
                .replace(groups.get(1), "B")
                .replace(groups.get(2), "C")
                .split("")));

        for (String group : groups) {
            command.append("\n");
            command.append(stream(group.split("")).reduce((s, s2) -> ((s.endsWith("R") || s.endsWith("L") || s2.startsWith("R") || s2.startsWith("L"))) ? (s + "," + s2) : (s + s2)).orElseThrow());
        }
        command.append("\nn\n");
        return command.toString();
    }

    public long getOutputLongValue() {
        return outputLongValue;
    }

    private List<String> findGroups(String line) {
        List<String> groups = new ArrayList<>();
        Matcher m = Pattern.compile("^(.{1,21})\\1*(.{1,21})(?:\\1|\\2)*(.{1,21})(?:\\1|\\2|\\3)*$").matcher(line);

        if (m.find()) {
            for (int i = 0; i < m.groupCount(); i++) {
                groups.add(m.group(i + 1));
            }
            return groups;
        }
        throw new RuntimeException("No groups found !!!");
    }
}
