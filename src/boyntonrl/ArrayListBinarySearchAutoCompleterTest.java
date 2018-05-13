package boyntonrl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayListBinarySearchAutoCompleterTest {

    List<String> words = new ArrayList<>();
    ArrayListBinarySearchAutoCompleter autoCompleter = new ArrayListBinarySearchAutoCompleter(words);

    @Before
    public void setUp() throws Exception {
        autoCompleter.initialize("wordstest.txt");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void allThatBeginWith() {
        assertEquals("[tea, ted, ten, to]", autoCompleter.allThatBeginWith("t").toString());
        assertEquals("[i, in, inn, inning]", autoCompleter.allThatBeginWith("i").toString());
        assertNotEquals("[bla]", autoCompleter.allThatBeginWith("b"));
        System.out.println(autoCompleter.allThatBeginWith("a"));
    }
}