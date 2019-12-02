import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {
    // The name of the file to open.

    static int fuelWeight(int fuel) {
        int x = fuel / 3 - 2;
        if (x < 0) {
            return 0;
        } else {
            return x + fuelWeight(x);
        }
    }

    public static void main(String[] args) {
        String fileName = "input/day1.txt";

        // This will reference one line at a time
        String line;
        int sum = 0;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                int mass = Integer.parseInt(line);
                int x = mass / 3;
                int y = x - 2;
                sum += y + fuelWeight(y);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        System.out.println(sum);
    }

}


