package com.github.mutare.adventcalendar2019.day23;

import com.github.mutare.adventcalendar2019.day15.FileProgramInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class NICTest {
    @Test
    public void test() throws IOException {
        NIC nic = new NIC(new FileProgramInputSource().getProgram("/day23/data"));
        assertEquals(23626, nic.run());
    }
}
