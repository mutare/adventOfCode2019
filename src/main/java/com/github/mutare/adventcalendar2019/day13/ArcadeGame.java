package com.github.mutare.adventcalendar2019.day13;

import com.github.mutare.adventcalendar2019.day2.ShipComputer;

import java.util.HashMap;
import java.util.Map;

public class ArcadeGame extends Thread {

    private boolean finish;

    public void insertCons(int i) {
        program[0] = i;
    }

    static class Point {
        int x;
        int y;

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + x;
            hash = hash * 31 + y;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Point)) return false;
            return ((Point) obj).x == this.x && ((Point) obj).y == this.y;
        }
    }

    private long[] program;
    private int[] grid;

    private Map<Point, Integer> elements = new HashMap<>();

    private ShipComputer shipComputer = new ShipComputer();

    public ArcadeGame(long[] program) throws InterruptedException {
        this.program = program;
    }

    @Override
    public void run() {
        try {
            shipComputer.proccess(program);
            finish = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void play() throws InterruptedException {
        this.start();

        while (!finish) {
            ArcadeGame.Point point = new ArcadeGame.Point();

            while (shipComputer.output().isEmpty()) if (finish) return;
            point.x = shipComputer.output().take().intValue();

            while (shipComputer.output().isEmpty()) if (finish) return;
            point.y = shipComputer.output().take().intValue();

            while (shipComputer.output().isEmpty()) if (finish) return;
            elements.put(point, shipComputer.output().take().intValue());
        }
    }

    public long getNumberOfBlockTiles() {
        return elements.values().stream().filter(integer -> integer == 2).count();
    }

}
