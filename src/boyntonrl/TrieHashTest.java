package boyntonrl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieHashTest {

    TrieHash trie;

    @Before
    public void setUp() throws Exception {
        trie = new TrieHash();
        trie.put("to");
        trie.put("a");
        trie.put("tea");
        trie.put("ted");
        trie.put("in");
        trie.put("i");
        trie.put("inn");
        trie.put("inning");
        trie.put("ten");
        trie.put("a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.0.id.ctoid.net");
        trie.put("a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.0.id.ctoid.net");
    }

    @After
    public void tearDown() throws Exception {
        trie = null;
    }

    @Test
    public void put() {
    }

    @Test
    public void search() {
        assertTrue(trie.search("i"));
        assertFalse(trie.search("inni"));
    }

    @Test
    public void beginsWith() {
        assertTrue(trie.beginsWith("inni"));
    }

    @Test
    public void allThatBeginsWith() {
        assertEquals("[tea, ted, ten, to]", trie.allThatBeginsWith("t").toString());
        assertEquals("[i, in, inn, inning]", trie.allThatBeginsWith("i").toString());
        assertNotEquals("[bla]", trie.allThatBeginsWith("b"));
        System.out.println(trie.allThatBeginsWith("a"));
    }

    @Test
    public void clear() {
    }
}