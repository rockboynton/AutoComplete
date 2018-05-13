/*
 * CS2852 - 021
 * Spring 2018
 * Lab 9 - AutoCompleter Revisited
 * Name: Rock Boynton
 * Created: 5/10/2018
 */

package boyntonrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores the words in a sorted ArrayList and uses it to find all the words beginning with a
 * prefix by binary searching.
 */
public class ArrayListBinarySearchAutoCompleter extends AbstractAutoCompleter implements
        AutoCompleter {

    /**
     * Constructor for an ArrayListBinarySearchAutoCompleter
     * @see AbstractAutoCompleter
     * @param words list of words
     */
    public ArrayListBinarySearchAutoCompleter(List<String> words) {
        super(words);
    }

    /**
     * Returns a list of all prefix matches in the dictionary, a.k.a., all entries in the
     * dictionary that begin with prefix. Utilizes binary searching methods.
     * @param prefix prefix to look for
     * @return list containing words in dictionary that match the prefix
     */
    @Override
    public List<String> allThatBeginWith(String prefix) {
        long startTime = System.nanoTime();
        long endTime;
        List<String> foundWords = new ArrayList<>();
        int startIndex;
        boolean beginsWith = true;
        if (prefix.length() > 0) {
            startIndex = Collections.binarySearch(words, prefix);
            if (startIndex < 0) {
                startIndex = -startIndex - 1;
            }
            for (int i = startIndex; beginsWith && i < words.size(); i++) {
                if (words.get(i).length() >= prefix.length() && words.get(i).substring(
                        0, prefix.length()).equals(prefix)) {
                    foundWords.add(words.get(i));
                } else {
                    beginsWith = false;
                }
            }
        }
        endTime = System.nanoTime();
        lastOpTime = endTime - startTime;
        return foundWords;
    }

    @Override
    public void initialize(String fileName) throws IOException {
        super.initialize(fileName);
        words.sort(null); // sorts the strings by their natural ordering
    }
}
