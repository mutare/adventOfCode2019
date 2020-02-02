package com.github.mutare.adventcalendar2019.day25;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Droid {
    public Room room;
    IntcodeComputer computer;
    List<String> commands = new ArrayList<>();
    private String output;

    public Droid(long[] program) {
        computer = new IntcodeComputer(program);
    }

    public Droid(Droid droid) {
        this.computer = new IntcodeComputer(droid.computer);
        this.commands = new ArrayList<>(droid.commands);
        this.output = droid.output;
        this.room = new Room(droid.room);
    }

    public String getOutput() {
        return this.output;
    }

    public void command(String command) {
        for (char c : command.toCharArray()) {
            computer.proccess((long) c);
        }
        computer.proccess((long) '\n');
        if (!command.isBlank()) {
            commands.add(command);
        }

        StringBuilder output = new StringBuilder();
        IntcodeComputer.Result result;
        while (!asList(IntcodeComputer.Result.Type.INPUT, IntcodeComputer.Result.Type.END).contains((result = computer.proccess()).type)) {
            if (result.type == IntcodeComputer.Result.Type.OUTPUT) {
                output.append((char) result.value.intValue());
            }
        }
        this.output = output.toString();
        Room room = new Room(this.output, this.commands);
        this.room = room.name.startsWith("==") ? room : this.room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Droid droid = (Droid) o;
        return this.room.name.equals(droid.room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room.name);
    }

    public void goFor(Room room, String item) {
        command("");
        for (String command : room.route) {
            command(command);
        }
        //System.out.println(output);
        command("take " + item);
        //System.out.println(item + " : " + output);

        for (String command : room.getReverseRoute()) {
            command(command);
        }

    }

    public void goTo(Room room) {
        command("");
        for (String command : room.route) {
            command(command);
        }
        //System.out.println(output);
    }
}
