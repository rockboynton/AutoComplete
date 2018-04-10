/*
 * CS2852 - 021
 * Spring 2018
 * Lab 4 - AutoCompleter
 * Name: Rock Boynton
 * Created: 3/28/2018
 */

package boyntonrl;

import java.io.IOException;
import java.util.List;

/**
 * Defines the methods that all strategies of auto-completing must implement
 */
public interface AutoCompleter {

    /**
     * Loads the dictionary file.
     * @param fileName name of dictionary file
     * @throws IOException if there's an error with the file
     */
    void initialize(String fileName) throws IOException;

    /**
     * Returns a list of all prefix matches in the dictionary, a.k.a., all entries in the
     * dictionary that begin with prefix.
     * @param prefix prefix to look for
     * @return list containing words in dictionary that match the prefix
     */
    List<String> allThatBeginWith(String prefix);

    /**
     * Returns the number of nanoseconds required by the last call to initialize() or
     * allThatBeginsWith()
     * @return number of nanoseconds
     */
    long getLastOperationTime();
}
