import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class DayTemplate {

    static int part1() {
        return -1;
    }

    static int part2(){
        return -1;
    }

    static void parse(String line) throws ParseException {

    }

    public static void run(String filename){
        String fileName = "input/" + filename + ".txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);


            while((line = bufferedReader.readLine()) != null) {

                // todo: add parse
                parse(line);

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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Part1: " + part1());
        System.out.println("Part2: " + part2());

    }

    public static void main(String[] args) {
        for (String file  : args) {
            System.out.println(" === " + file + " === " );
            run(args[0].toLowerCase());
        }
    }
}