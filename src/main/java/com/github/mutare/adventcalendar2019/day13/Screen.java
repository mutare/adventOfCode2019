package com.github.mutare.adventcalendar2019.day13;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private int[][] grid = new int[0][0];
    ArcadeGame arcadeGame;
    JLabel bricks;
    JLabel score;
    JPanel jPanel0;
    JPanel jPanel1;
    JPanel jPanel2;

    public Screen(ArcadeGame arcadeGame) {
        this.arcadeGame = arcadeGame;
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        jPanel0 = new JPanel();
        jPanel0.setLayout(new BorderLayout());

        jPanel1 = new JPanel();
        jPanel1.setSize(800, 100);

        jPanel2 = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                for (int j = 0; j < grid.length; j++) {
                    for (int i = 0; i < grid[0].length; i++) {
                        switch (grid[j][i]) {
                            case 0://empty
                                g.setColor(Color.WHITE);
                                break;
                            case 1: //wall
                                g.setColor(Color.RED);
                                break;
                            case 2: //block
                                g.setColor(Color.BLACK);
                                break;
                            case 3://paddle
                                g.setColor(Color.BLUE);
                                break;
                            case 4://ball
                                g.setColor(Color.MAGENTA);
                                break;
                        }
                        g.fillRect(i * SIZE, j * SIZE, SIZE, SIZE);
                    }
                }
            }
        };
        jPanel2.setBackground(Color.BLUE);

        jPanel0.add(jPanel1, BorderLayout.NORTH);
        jPanel0.add(jPanel2, BorderLayout.CENTER);
        add(jPanel0);

        this.add(jPanel0);

        JButton next = new JButton("NEXT");
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 30, 10);
        slider.addChangeListener(e -> arcadeGame.setDelay(((JSlider) e.getSource()).getValue()));

        bricks = new JLabel("BRICKS");
        bricks.setSize(new Dimension(300, 50));
        score = new JLabel("SCORE");
        score.setSize(new Dimension(300, 50));
        next.addActionListener(e -> arcadeGame.next());
        jPanel1.add(next);
        jPanel1.add(score);
        jPanel1.add(bricks);
        jPanel1.add(slider);
    }

    int SIZE = 10;


    public void draw(int[][] grid) {
        this.grid = grid;
        if (arcadeGame != null) {
            bricks.setText("" + this.arcadeGame.getNumberOfBlockTiles());
            score.setText("" + this.arcadeGame.getScore());
        }
        this.invalidate();
        jPanel2.setSize(SIZE * grid[0].length, SIZE * grid.length);
        jPanel2.repaint();
    }
}
