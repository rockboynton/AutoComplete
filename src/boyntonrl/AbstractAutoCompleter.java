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
import java.util.List;
import java.util.Scanner;

/**
 * Abstract AutoCompleter defines the initialize and getLastOpTime methods for implementations of
 * the AutoCompleter interface.
 */
public abstract class AbstractAutoCompleter implements AutoCompleter {

    List<String> words;
    long lastOpTime;

    /**
     * Constructor for AbstractAutoCompleter stores words in a list
     * @see AutoCompleter
     * @param words list of words
     */
    public AbstractAutoCompleter(List<String> words) {
        this.words = words;
    }

    /**
     * Load a file in the fill words list
     * @param fileName name of dictionary file
     * @throws IOException if something goes wrong
     */
    @Override
    public void initialize(String fileName) throws IOException {
        words.clear();
        long startTime = System.nanoTime();
        long endTime;
        Path path = Paths.get(fileName);
        File file = new File(path.toString());
        int dotIndex = path.toString().lastIndexOf(".");
        String ext = path.toString().substring(dotIndex);
        try(Scanner fileIn = new Scanner(file)) {
            if (ext.equals(".csv")) {
                while (fileIn.hasNextLine()) {
                    String line = fileIn.nextLine();
                    String[] fields = line.split(",");
                    // add the second field in the csv file, which should be the domain name
                    words.add(fields[1]);
                }
            } else if (ext.equals(".txt")) {
                while (fileIn.hasNextLine()) {
                    words.add(fileIn.nextLine());
                }
            } else {
                throw new IOException("wrong file here");
            }
        } finally {
            endTime = System.nanoTime();
            lastOpTime = endTime - startTime;
        }
    }

    @Override
    public long getLastOperationTime() {
        return lastOpTime;
    }
}
