package Day8;

import Utils.Input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Day8 {

    static int[][][] parseLayers(int width, int height, ArrayList<Integer> input) {
        int nLayers = input.size() / width / height;
        int[][][] layers = new int[nLayers][height][width];
        Iterator<Integer> iter = input.iterator();
        for (int i = 0; i < nLayers; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < width; k++) {
                    layers[i][j][k] = iter.next();
                }
            }
        }
        return layers;
    }

    public static int part1(int width, int height, ArrayList<Integer> input) {

        int[][][] layers = parseLayers(width, height, input);

        int indexFewZeros = -1;
        int fewZeroes = Integer.MAX_VALUE;
        for (int i = 0; i < layers.length; i++) {
            int zeros = countOf(layers[i], 0);
            if (zeros < fewZeroes) {
                fewZeroes = zeros;
                indexFewZeros = i;
            }
        }

        return countOf(layers[indexFewZeros], 1) * countOf(layers[indexFewZeros], 2);

    }

    public static int part2(int width, int height, ArrayList<Integer> input) {

        int[][][] layers = parseLayers(width, height, input);
        int[][] rendered = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rendered[y][x] = 2;
            }
        }

        for (int[][] layer : layers) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (rendered[y][x] == 2) {
                        rendered[y][x] = layer[y][x];
                    }
                }
            }
        }

        printLayer(rendered);

        return -1;
    }

    public static void printLayer(int[][] layer){
        for (int[] row : layer ) {
            for (int pixel : row ) {
                switch (pixel) {
                    case 0:
                        System.out.print(" ");
                        break;
                    case 1:
                        System.out.print("O");
                        break;
                }
            }
            System.out.println();
        }
    }

    public static int countOf(int[][] ints, int n) {
        int sum = 0;
        for (int[] row : ints ) {
            for (int i: row) {
                sum += i == n ? 1 : 0;
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        String fileName = "input/day8.txt";
        ArrayList<Integer> input = Input.getInputAsString(fileName)
                .chars()
                .map(i -> i - 48).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Part1: " + part1(25, 6, new ArrayList<>(input)));
        System.out.println("Part2: " + part2(25, 6, new ArrayList<>(input)));
    }
}