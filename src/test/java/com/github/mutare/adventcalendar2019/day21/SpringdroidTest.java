package com.github.mutare.adventcalendar2019.day21;

import com.github.mutare.adventcalendar2019.day15.FileProgramInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SpringdroidTest {
    @Test
    public void one() throws IOException {
        Springdroid springdroid = new Springdroid(new FileProgramInputSource().getProgram("/day21/data"));
        String output = springdroid.output();
        springdroid.input(
                "NOT C J\n" +
                        "NOT J J\n" +
                        "AND B J\n" +
                        "AND A J\n" +
                        "NOT J J\n" +
                        "AND D J\n" +
                "WALK\n");

        output = springdroid.output();
        System.out.println(output);
        assertEquals(19349722, springdroid.getResult());
    }

    @Test
    public void two() throws IOException {
        Springdroid springdroid = new Springdroid(new FileProgramInputSource().getProgram("/day21/data"));
        String output = springdroid.output();
        springdroid.input(
                "NOT H T\n" +
                        "NOT T T\n" +
                        "OR E T\n" +
                        "NOT C J\n" +
                        "NOT J J\n" +
                        "AND B J\n" +
                        "AND A J\n" +
                        "NOT J J\n" +
                        "AND D J\n" +
                        "AND T J\n" +
                "RUN\n");

        output = springdroid.output();
        System.out.println(output);
        assertEquals(1141685254, springdroid.getResult());
    }
}
