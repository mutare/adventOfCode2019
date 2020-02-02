package com.github.mutare.adventcalendar2019.day25;

import com.github.mutare.adventcalendar2019.day15.FileProgramInputSource;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class DroidTest {


    @Test
    public void test() throws IOException {
        Droid droid = init();
        droid.command("\n");
        Set<Room> rooms = new HashSet<>();
        Deque<Droid> q = new LinkedList<>();
        Set<Droid> seen = new HashSet<>();
        q.add(droid);
        int i = 0;
        while (!q.isEmpty()) {
            i++;
            Droid pop = q.pop();
            if (seen.contains(pop)) {
                continue;
            }
            seen.add(pop);

            if (i % 10 == 0)
                System.out.println(format("Q : %d, seen : %d, rooms : %d", q.size(), seen.size(), rooms.size()));

            rooms.add(pop.room);
            for (String command : pop.room.doors) {
                Droid cloneDroid = new Droid(pop);
                cloneDroid.command(command);
                q.add(cloneDroid);
            }
        }

        System.out.println(format("Rooms (%d) : ", rooms.size()));
        rooms.forEach(System.out::println);

        System.out.println(format("Items (%d) : ", rooms.stream().mapToLong(room -> room.items.size()).sum()));
        rooms.stream().flatMap(room -> room.items.stream()).forEach(System.out::println);

        List<String> items = rooms.stream().flatMap(room -> room.items.stream()).collect(Collectors.toCollection(ArrayList::new));
        Map<String, Room> itemsRooms = rooms.stream().filter(room -> !room.items.isEmpty()).collect(Collectors.toMap(room -> room.items.get(0), room -> room));
        Map<String, Room> roomByNames = rooms.stream().collect(Collectors.toMap(room -> room.name, room -> room));
        System.out.println("################################################");

        List<String> pickabeItems = items.stream()
                .filter(s -> !asList("photons", "molten lava", "escape pod", "infinite loop",
                        "giant electromagnet").contains(s)).collect(Collectors.toList());

        for (String item : pickabeItems) {
            droid = init();
            droid.goFor(itemsRooms.get(item), item);
        }

        System.out.println("################################################");

        boolean end = false;
        int n = 1;
        while (!end) {
            System.out.println("N : " + n);
            droid = init();
            for (int it = 0; it < pickabeItems.size(); it++) {
                if (BigInteger.valueOf(n).testBit(it))
                    droid.goFor(itemsRooms.get(pickabeItems.get(it)), pickabeItems.get(it));
            }

            droid.goTo(roomByNames.get("== Pressure-Sensitive Floor =="));
            if (!droid.getOutput().contains("Alert!")) {
                end = true;
                System.out.println(droid.getOutput());
            }
            n++;
        }
        assertTrue(droid.getOutput().contains("Oh, hello! You should be able to get in by typing 35332 on the keypad at the main airlock."));
    }

    private Droid init() throws IOException {
        return new Droid(new FileProgramInputSource().getProgram("/day25/data"));
    }

}
