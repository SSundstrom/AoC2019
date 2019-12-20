package Day16;

import Utils.Input;

import java.util.stream.IntStream;

public class Day16 {

    private final static int[] base = {0, 1, 0, -1};

    public static void run(int[] input, int phases) {
        for (int phase = 0; phase < phases; phase++) {
//            System.out.println(java.time.LocalTime.now() + " : " + phase);
            int [] tmp = input.clone();
            IntStream.range(0, input.length).parallel().forEach(i -> input[i] = multiplyArray(tmp, i));
        }
    }

    public static void run2(int[] input, int phases) {
        for (int phase = 0; phase < phases; phase++) {
            int [] tmp = input.clone();
            IntStream.range(0, input.length).forEach(i -> input[i] = multiplyArray(tmp, i));
        }
    }

    public static void run3(int[] input, int phases) {
        for (int phase = 0; phase < phases; phase++) {
            int [] tmp = input.clone();
            for (int i = 0; i < input.length; i++) {
                input[i] = multiplyArray(tmp, i);
            }
        }
    }

    public static int getPatternSymbol(int row, int col) {
        row++;
        col++;
        int loop = row * 4;
        col = col % loop;
        int i = 0;
        while (col >= 0) {
            i++;
            col -= row;
        }
        return base[i-1];
    }

    public static int multiplyArray(int[] input, int rowIndex) {
        int items = rowIndex + 1;
        final int itemsLoop = items * 4;
        int sum = 0;
//        drop items
//        take items
        sum += getAll(items - 1, items * 2 - 1, itemsLoop, input);
//        drop items
//        take negative items
        sum -= getAll(items * 3 - 1, items * 4 - 1, itemsLoop, input);

        return Math.abs(sum) % 10;
    }

    public static int getAll(final int itemsStart, final int itemsEnd, final int itemsLoop, int[] input) {
        int sum = 0;
        for (int j = itemsStart; j < itemsEnd; j++) {
            int k = 0;
            int index = j + k * itemsLoop;
            while (index < input.length) {
                sum += input[index];
                k++;
                index = j + k * itemsLoop;
            }
        }
        return sum;
    }

    public static String part1(int[] input, int phases) {
        run(input, phases);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(input[i]);
        }
        return sb.toString();
    }

    public static int[] getLargeInput(int[] input, int scale) {
        int[] largeInput = new int[input.length * scale];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < scale; j++) {
                largeInput[i + j * input.length] = input[i];
            }
        }
        return largeInput;
    }

    public static String part2(int[] input, int phases, int size) {
        int[] largeInput = getLargeInput(input, size);
        String part1Res = part1(largeInput, phases);
        int offset = Integer.parseInt(part1Res);
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset+8; i++) {
            sb.append(largeInput[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String filename = "input/day16.txt";
        int phases = 100;

        int[] input = Input.getInputAsString(filename)
                .chars()
                .map(p -> p - 48)
                .toArray();

//        System.out.println("Part1: " + part1(input, phases));
        System.out.println("Part2: " + part2(input, phases, 10000));
    }
}
