package boyntonrl;

import java.io.IOException;
import java.util.List;

public class IteratorAutoCompleter extends AbstractAutoCompleter implements AutoCompleter{

    public IteratorAutoCompleter(List<String> words) {
        super(words);
    }

    public IteratorAutoCompleter(){
        super();
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
