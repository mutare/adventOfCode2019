package com.github.mutare.adventcalendar2019.day8;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SpaceImageFormatTest {

    private int[] image = new FileImageInputSource().getImage();

    public SpaceImageFormatTest() throws IOException {
    }

    @Test
    public void one() {
        SpaceImageFormat spaceImageFormat = new SpaceImageFormat(image, 25, 6);
        assertEquals(1690, spaceImageFormat.getCrc());
    }

    @Test
    public void two() {
        SpaceImageFormat spaceImageFormat = new SpaceImageFormat(image, 25, 6);
        int[][] decodedImage = spaceImageFormat.decode();
        for (int j = 0 ; j <  6 ; j++) {
            for (int i = 0; i < 25; i++) {
                System.out.print(getSign(decodedImage[i][j]));
            }
            System.out.println();
        }
    }

    private char getSign(int i) {
        return i == 0 ? ' ' : '#';
    }

}
