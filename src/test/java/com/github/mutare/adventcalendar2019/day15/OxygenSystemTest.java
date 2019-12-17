package com.github.mutare.adventcalendar2019.day15;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class OxygenSystemTest {

    @Test
    public void oneTwo() throws IOException, InterruptedException {
        DroidRemoteControl droidRemoteControl = new DroidRemoteControl(new FileProgramInputSource().getProgram("/day15/data"), true);
        assertEquals(254, droidRemoteControl.go());
        assertEquals(41, droidRemoteControl.oxygenSystemX);
        assertEquals(43, droidRemoteControl.oxygenSystemY);

        droidRemoteControl.turnOnSystem();
        assertEquals(268, droidRemoteControl.minutes);
    }
}
