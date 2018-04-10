package boyntonrl;

import java.util.LinkedList;
import java.util.List;

public class IteratorAutoCompleter extends AbstractAutoCompleter implements AutoCompleter{

    public IteratorAutoCompleter(List<String> words) {
        super(words);
    }

    @Override
    public List<String> allThatBeginWith(String prefix) {
        long startTime = System.nanoTime();
        long endTime;
        // LinkedList should be faster to add
        List<String> foundWords = new LinkedList<>();
        int prefixLength = prefix.length();
        for (String word : words) {
            if (word.length() >= prefixLength) {
                String wordPrefix = word.substring(0, prefixLength);
                if (wordPrefix.equals(prefix)) {
                    foundWords.add(word);
                }
            }
        }
        endTime = System.nanoTime();
        lastOpTime = endTime - startTime;
        return foundWords;
    }
}
