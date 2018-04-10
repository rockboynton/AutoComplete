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
 * Stores the words in an ArrayList and uses index methods (e.g., get()) to navigate the list. for
 * looking up words that begin with the specified prefix.
 */
public class IndexAutoCompleter extends AbstractAutoCompleter implements AutoCompleter {

    /**
     * Constructor for an IndexAutoCompleter
     * @see AbstractAutoCompleter
     * @param words list of words
     */
    public IndexAutoCompleter(List<String> words) {
        super(words);
    }

    /**
     * Returns a list of all prefix matches in the dictionary, a.k.a., all entries in the
     * dictionary that begin with prefix. Utilizes index-based methods.
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
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
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
