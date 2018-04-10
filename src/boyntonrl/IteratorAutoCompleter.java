/*
 * CS2852 - 021
 * Spring 2018
 * Lab 4 - AutoCompleter
 * Name: Rock Boynton
 * Created: 3/28/2018
 */

package boyntonrl;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of AutoCompleter that uses an iterator in the allThatBeginsWith() method
 */
public class IteratorAutoCompleter extends AbstractAutoCompleter implements AutoCompleter {

    /**
     * Constructor for an IteratorAutoCompleter
     * @see AbstractAutoCompleter
     * @param words list of words
     */
    public IteratorAutoCompleter(List<String> words) {
        super(words);
    }

    /**
     * Returns a list of all prefix matches in the dictionary, a.k.a., all entries in the
     * dictionary that begin with prefix. Utilizes an iterator.
     * @param prefix prefix to look for
     * @return list containing words in dictionary that match the prefix
     */
    @Override
    public List<String> allThatBeginWith(String prefix) {
        long startTime = System.nanoTime();
        long endTime;
        // LinkedList should be faster to add
        List<String> foundWords = new LinkedList<>();
        int prefixLength = prefix.length();
        if (prefixLength > 0) {
            for (String word : words) {
                if (word.length() >= prefixLength) {
                    String wordPrefix = word.substring(0, prefixLength);
                    if (wordPrefix.equals(prefix)) {
                        foundWords.add(word);
                    }
                }
            }
        }
        endTime = System.nanoTime();
        lastOpTime = endTime - startTime;
        return foundWords;
    }
}
