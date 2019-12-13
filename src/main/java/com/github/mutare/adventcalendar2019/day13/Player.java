package com.github.mutare.adventcalendar2019.day13;

import static java.lang.String.format;

public class Player extends Thread {
    private final ArcadeGame arcadeGame;

    public Player(ArcadeGame arcadeGame) {
        this.arcadeGame = arcadeGame;
    }


    public void insertCoins(int i) {
        arcadeGame.insertCoins(i);
    }

    @Override
    public void run() {
        int currentScore = -1;
        int numberOfBlockTiles = -1;
        while(!arcadeGame.isFinish()) {
            //arcadeGame.printGrid(System.out);
            arcadeGame.move(-1);
            int nobt = arcadeGame.getNumberOfBlockTiles();
            if (numberOfBlockTiles != nobt) {
                numberOfBlockTiles = nobt;
                System.out.println(format("Score : %d, Block tiles : %d", arcadeGame.getScore(), arcadeGame.getNumberOfBlockTiles()));
            }
            if (currentScore != arcadeGame.getScore()) {
                currentScore = arcadeGame.getScore();
                System.out.println(format("Score : %d, Block tiles : %d", arcadeGame.getScore(), arcadeGame.getNumberOfBlockTiles()));
            }
        }
    }

    public void play() throws InterruptedException {
        start();
        arcadeGame.play();

    }

    public int getScore() {
        return arcadeGame.getScore();
    }
}
