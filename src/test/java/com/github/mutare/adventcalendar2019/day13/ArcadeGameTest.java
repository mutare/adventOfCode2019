package com.github.mutare.adventcalendar2019.day13;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ArcadeGameTest {

    @Test
    public void one() throws IOException, InterruptedException {
        ArcadeGame arcadeGame = new ArcadeGame(new FileProgramInputSource().getProgram("/day13/data"), false);
        arcadeGame.play();

        assertEquals(312, arcadeGame.getParameter(0));
        arcadeGame.printGrid(System.out);
    }

    @Test
    public void two() throws IOException, InterruptedException {
        ArcadeGame arcadeGame = new ArcadeGame(new FileProgramInputSource().getProgram("/day13/data"), false);
        arcadeGame.insertCoins(2);
        arcadeGame.play();

        assertEquals(15909, arcadeGame.getParameter(1));
    }

}
