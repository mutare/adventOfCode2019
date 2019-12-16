package com.github.mutare.adventcalendar2019.day13;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ArcadeGameTest {

    @Test
    public void one() throws IOException, InterruptedException {
        ArcadeGame arcadeGame = new ArcadeGame(new FileProgramInputSource().getProgram(), false);
        arcadeGame.play();

        assertEquals(312, arcadeGame.getNumberOfBlockTiles());
        arcadeGame.printGrid(System.out);
    }

    @Test
    public void two() throws IOException, InterruptedException {
        ArcadeGame arcadeGame = new ArcadeGame(new FileProgramInputSource().getProgram(), false);
        arcadeGame.insertCoins(2);
        arcadeGame.play();

        assertEquals(15909, arcadeGame.getScore());
    }

}
