package com.github.mutare.adventcalendar2019.day21;

import com.github.mutare.adventcalendar2019.day15.FileProgramInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SpringdroidTest {
    @Test
    public void test() throws IOException {
        Springdroid springdroid = new Springdroid(new FileProgramInputSource().getProgram("/day21/data"));
        String output = springdroid.output();
        springdroid.input("NOT C T\n" +
                "NOT T T\n" +
                "AND B T\n" +
                "AND A T\n" +
                "NOT T T\n" +
                "AND D T\n" +
                "NOT T T\n" +
                "NOT T J\n" +
                "WALK\n");

        output = springdroid.output();
        System.out.println(output);
        assertEquals(19349722, springdroid.getResult());
    }
}
