/*
 * CS2852 - 021
 * Spring 2018
 * Lab 9 - AutoCompleter Revisited
 * Name: Rock Boynton
 * Created: 5/10/2018
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
 * Stores the words in a Trie and uses it to find all the words beginning with a prefix.
 */
public class TrieMapAutoCompleter implements AutoCompleter {

    private Trie words;
    private long lastOpTime;

    /**
     * Constructor for an TrieMapAutoCompleter
     * @see AbstractAutoCompleter
     * @param words list of words
     */
    public TrieMapAutoCompleter(Trie words) {
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
        int addCount = 0;
        try(Scanner fileIn = new Scanner(file)) {
            switch (ext) {
                case ".csv":
                    while (fileIn.hasNextLine()) {
                        String line = fileIn.nextLine();
                        String[] fields = line.split(",");
                        // add the second field in the csv file, which should be the domain name
                        words.put(fields[1]);
                        addCount++;
                        System.out.println(addCount);
                    }
                    break;
                case ".txt":
                    while (fileIn.hasNextLine()) {
                        words.put(fileIn.nextLine());
                        addCount++;
                        System.out.println(addCount);
                    }
                    break;
                default:
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

    /**
     * Returns a list of all prefix matches in the dictionary, a.k.a., all entries in the
     * dictionary that begin with prefix. Utilizes a trie for fast querying.
     * @param prefix prefix to look for
     * @return list containing words in dictionary that match the prefix
     */
    @Override
    public List<String> allThatBeginWith(String prefix) {
        long startTime = System.nanoTime();
        long endTime;
        List<String> foundWords = new ArrayList<>();
        if (prefix.length() > 0) {
            foundWords = words.allThatBeginsWith(prefix);
        }
        endTime = System.nanoTime();
        lastOpTime = endTime - startTime;
        return foundWords;
    }
}
