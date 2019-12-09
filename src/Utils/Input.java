package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Input {

    public static Stream<String> getInputAsStream(String fileName){
        Stream<String> stream = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            stream = bufferedReader.lines();

        }
        catch(
                FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }

        return stream;
    }

    public static String getInputAsString(String fileName) {
        return getInputAsStream(fileName).collect(Collectors.joining());
    }

    public static ArrayList<Integer> getInputListInt(String filename) {

        ArrayList<Integer> input = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(filename);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            input = bufferedReader.lines().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

            bufferedReader.close();
        }
        catch(
                FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            filename + "'");
        } catch (IOException e) {
            System.out.println(
                    "Unable to open file '" +
                            filename + "'");
        }

        return input;

    }

    public static ArrayList<BigInteger> getInputListBigInt(String filename) {
        Stream<String> input = getInputAsStream(filename);
        return input
                .map(BigInteger::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
