package Utils;

import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class General {

    public static LinkedList<ArrayList<Integer>> generateCombinations(int from, int to) {
        LinkedList<ArrayList<Integer>> combinations = new LinkedList<>();

        if (to == from) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(from);
            combinations.add(tmp);
            return combinations;
        }

        for (ArrayList<Integer> combination : generateCombinations(from, to-1) ) {
            for (int index = 0; index < to-from; index++) {
                ArrayList<Integer> tmp = new ArrayList<>(combination);
                tmp.add(index, to);
                combinations.add(tmp);
            }
        }

        return combinations;
    }

}
