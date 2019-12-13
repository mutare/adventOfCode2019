package com.github.mutare.adventcalendar2019.day13;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ArcadeGameTest {

    @Test
    public void one() throws IOException, InterruptedException {
        ArcadeGame arcadeGame = new ArcadeGame(new FileProgramInputSource().getProgram());
        arcadeGame.play();

        assertEquals(312, arcadeGame.getNumberOfBlockTiles());
        arcadeGame.printGrid(System.out);
    }

    @Test
    public void two() throws IOException, InterruptedException {
        Player player = new Player(new ArcadeGame(new FileProgramInputSource().getProgram()));
        player.insertCoins(2);
        player.play();
        assertEquals(-1, player.getScore());
    }

}
