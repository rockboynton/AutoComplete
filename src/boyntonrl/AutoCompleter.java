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
/*
 * DISCUSSION:
 *      To compare the performance of the different solutions. I tried testing them all using the
 * .csv file, as I thought having the larger data set assured the greatest difference in search
 * time/efficiency revealing the asymptotic time complexity differences between using
 * ArrayList/LinkedList and indexing/iterators.
 *      However, i found that using the .csv file with LinkedList indexing made the program
 * unresponsive even after waiting several minutes, as it took so much longer to search than the
 * other methods. I then tried it with the second-largest data set, the words.txt file and while
 * all of the other strategies took about the same time on average to complete, the LinkedList
 * index was by far the slowest (by about 4 orders of magnitude).
 */

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
