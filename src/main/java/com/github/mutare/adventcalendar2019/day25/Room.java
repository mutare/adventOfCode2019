package com.github.mutare.adventcalendar2019.day25;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Room {
    String name;
    String description;
    List<String> doors;
    List<String> items;
    String text;
    List<String> route;

    public Room(Room room) {
        this.name = room.name;
        this.description = room.description;
        this.doors = new LinkedList<>(room.doors);
        this.items = new LinkedList<>(room.items);
        this.text = room.text;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", doors=" + doors +
                ", items=" + items +
                ", route=" + route +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Room(String text, List<String> route) {
        this.text = text;
        BufferedReader bufferedReader = new BufferedReader(new StringReader(text));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        this.name = getLine(lines, 1);
        this.description = getLine(lines, 1);
        this.doors = getDoors(lines);
        this.items = getItems(lines);
        this.route = new LinkedList<>(route);
    }

    private String getLine(List<String> lines, int i) {
        int n = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            n++;
            if (n == i) return line;
        }
        return null;
    }

    private List<String> getDoors(List<String> lines) {
        return getElements(lines, "Doors here lead:");
    }

    private List<String> getItems(List<String> lines) {
        return getElements(lines, "Items here:");
    }

    private List<String> getElements(List<String> lines, String startLine) {
        List<String> elements = new LinkedList<>();
        boolean found = false;
        for (String line : lines) {
            if (found && line.equals("")) break;
            if (found) {
                elements.add(line.replace("-", " ").trim());
            }
            if (line.startsWith(startLine)) found = true;
        }
        return elements;
    }

    public List<String> getReverseRoute() {
        List<String> reverse = new LinkedList<>();
        for (String command : route) {
            reverse.add(reverseCommand(command));
        }
        Collections.reverse(reverse);
        return reverse;
    }

    private String reverseCommand(String command) {
        switch (command) {
            case "north" : return "south";
            case "south" : return "north";
            case "west" : return "east";
            case "east" : return "west";
        }
        throw new RuntimeException("Unknown command to reverse");
    }

}
