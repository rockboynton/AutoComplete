package boyntonrl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {

    @Before
    public void setUp() throws Exception {
        Trie tree = new Trie();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void put() {

    }

    @Test
    public void search() {
    }

    @Test
    public void beginsWith() {
    }

    @Test
    public void allThatBeginsWith() {
        Trie trie = new Trie();
        trie.put("to");
        trie.put("a");
        trie.put("tea");
        trie.put("ted");
        trie.put("in");
        trie.put("i");
        trie.put("inn");
        trie.put("inning");

        trie.put("ten");
        assertEquals("to", trie.allThatBeginsWith("t").get(0));
        assertEquals(3, trie.allThatBeginsWith("i").size());
        assertNotEquals("te", trie.allThatBeginsWith("t").get(1));
        assertEquals("tea", trie.allThatBeginsWith("t").get(1));
    }

    @Test
    public void clear() {
    }
}