/*
 * CS2852 - 021
 * Spring 2018
 * Lab 4 - AutoCompleter
 * Name: Rock Boynton
 * Created: 3/28/2018
 */

package boyntonrl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores the words in an ArrayList and uses index methods (e.g., get()) to navigate the list. for
 * looking up words that begin with the specified prefix.
 */
public class ArrayListIndexAutoCompleter implements AutoCompleter {

    private ArrayList<String> words;


    /**
     * Loads the dictionary file into an ArrayList
     * @param fileName name of dictionary file
     * @throws IOException if something goes wrong reading the file
     */
    @Override
    public void initialize(String fileName) throws IOException{
        Path path = Paths.get(fileName);
        File file = new File(path.toString());
        try(Scanner fileIn = new Scanner(file)) {
            while (fileIn.hasNextLine()) {
                words.add(fileIn.nextLine());
            }
        }
    }

    @Override
    public List<String> allThatBeginWith(String prefix) {
        return null;
    }

    @Override
    public double getLastOperationTime() {
        return 0;
    }
}
