package com.github.mutare.adventcalendar2019.day8;

class SpaceImageFormat {
    private final int width;
    private final int height;
    private final int layers;
    private final int[] image;

    public SpaceImageFormat(int[] image, int width, int height) {
        this.image = image;
        this.layers = image.length / (width * height);
        this.width = width;
        this.height = height;
    }

    public int getCrc() {
        int minZeros = width * height;
        int ones = -1;
        int twos = -1;
        for (int k = 0; k < layers; k++) {
            int count = getNumberOfDigitsForLayer(k, 0);
            if (minZeros > count) {
                minZeros = count;
                ones = getNumberOfDigitsForLayer(k, 1);
                twos = getNumberOfDigitsForLayer(k, 2);
            }
        }
        return ones * twos;
    }

    private int getNumberOfDigitsForLayer(int k, int d) {
        int count = 0;
        for (int i = 0; i < width * height; i++)
            if (image[k * width * height + i] == d)
                count++;
        return count;
    }

    public int[][] decode() {
        int[][] decoded = new int[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                decoded[i][j] = 2;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                for (int k = 0; k < layers; k++) {
                    int value = image[i + j * this.width + k * this.width * this.height];
                    if (value == 1 || value == 0) {
                        decoded[i][j] = value;
                        break;
                    }
                }
        return decoded;
    }
}
