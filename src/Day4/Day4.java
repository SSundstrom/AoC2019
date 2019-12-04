package Day4;

import java.util.Iterator;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Day4 {

    static class increasing implements IntPredicate {

        @Override
        public boolean test(int i) {
            IntStream numbers = String.valueOf(i).chars();
            int[] ints = numbers.toArray();
            for (int j = 1; j < ints.length; j++) {
                if (ints[j-1] > ints[j]) {
                    return false;
                }
            }
            return true;
        }
    }

    static class multiples implements IntPredicate {
        @Override
        public boolean test(int i) {
            IntStream numbers = String.valueOf(i).chars();
            Iterator<Integer> iter = numbers.iterator();
            int curr = iter.next();
            while (iter.hasNext()) {
                int next = iter.next();
                if (next == curr) {return true;}
                curr = next;
            }
            return false;
        }
    }

    static class doubles implements IntPredicate {
        @Override
        public boolean test(int i) {
            IntStream numbers = String.valueOf(i).chars();
            Iterator<Integer> iter = numbers.iterator();
            int curr = iter.next();
            int count = 1;
            while (iter.hasNext()) {
                int next = iter.next();
                if (next == curr) {
                    count++;
                } else {
                    if (count == 2) {
                        return true;
                    }
                    count = 1;
                }
                curr = next;
            }
            return count == 2;
        }
    }

    public static void run(){

        IntStream p1 = IntStream.range(136818, 685979);
        p1 = p1.filter(new increasing());
        p1 = p1.filter(new multiples());
        System.out.println("Part1: " + p1.count());

        IntStream p2 = IntStream.range(136818, 685979);
        p2 = p2.filter(new increasing());
        p2 = p2.filter(new doubles());
        System.out.println("Part2: " + p2.count());
    }

    public static void main(String[] args) {
        System.out.println(" === Day4 === " );
        run();
    }
}