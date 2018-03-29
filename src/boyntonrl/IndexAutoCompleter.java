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
import java.util.*;
import java.util.stream.Collectors;

/**
 * Stores the words in an ArrayList and uses index methods (e.g., get()) to navigate the list. for
 * looking up words that begin with the specified prefix.
 */
public class IndexAutoCompleter extends AbstractAutoCompleter implements AutoCompleter {

    public IndexAutoCompleter (List<String> words) {
        super(words);
    }

    public IndexAutoCompleter() {
        super();
    }

    @Override
    public List<String> allThatBeginWith(String prefix) {
        // LinkedList should be faster to add
        List<String> foundWords = new LinkedList<>();
        int prefixLength = prefix.length();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String wordPrefix = word.substring(0,prefixLength);
            if (wordPrefix.equals(prefix)) {
                foundWords.add(word);
            }
        }
        return foundWords;
    }

    @Override
    public double getLastOperationTime() {
        return 0;
    }
}
