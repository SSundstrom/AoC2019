import Utils.Input;

import java.util.ArrayList;
import java.util.stream.Stream;

public class DayTemplate {

    public static void run(String filename){
        String fileName = "input/" + filename + ".txt";

//        Stream<String> input = Input.getInputAsStream(fileName);
//        ArrayList<Integer> input = Input.getInputListInt(fileName);

    }

    public static void main(String[] args) {
        for (String file  : args) {
            System.out.println(" === " + file + " === " );
            run(file.toLowerCase());
        }
    }
}