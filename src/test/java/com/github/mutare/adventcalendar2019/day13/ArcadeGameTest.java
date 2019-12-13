package com.github.mutare.adventcalendar2019.day13;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ArcadeGameTest {

    @Test
    public void one() throws IOException, InterruptedException {
        ArcadeGame arcadeGame = new ArcadeGame(new FileProgramInputSource().getProgram());
        arcadeGame.play();

        assertEquals(312, arcadeGame.getNumberOfBlockTiles());
    }

    @Test
    @Ignore
    public void two() throws IOException, InterruptedException {
        ArcadeGame arcadeGame = new ArcadeGame(new FileProgramInputSource().getProgram());
        arcadeGame.insertCons(2);
        arcadeGame.play();
        assertEquals(312, arcadeGame.getNumberOfBlockTiles());
    }

}
