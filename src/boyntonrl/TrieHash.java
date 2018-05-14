/*
 * CS2852 - 021
 * Spring 2018
 * Lab 9 - AutoCompleter Revisited
 * Name: Rock Boynton
 * Created: 5/10/2018
 */

package boyntonrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements a trie(prefix tree) data structure
 */
public class TrieHash {

    private static class Node {
        char c;
        HashMap<Character, Node> kids;
        boolean isWord;

        Node(char c) {
            this.c = c;
            kids = new HashMap<>();
            isWord = false;
        }

        Node() {
            kids = new HashMap<>();
        }
    }

    private Node root; // dummy root node

    /**
     * Construct a new Trie with a root Node containing an empty string
     */
    public TrieHash() {
        this.root = new Node();
    }

    /**
     * Adds an entry to this Trie
     * @param word word to be added to the Trie. Must be non empty.
     */
    public void put(String word) {
        HashMap<Character, Node> kids = root.kids;
        char c;
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (kids.containsKey(c)) {
                node = kids.get(c);
            } else {
                node = new Node(c);
                kids.put(c, node);
            }
            kids = node.kids;
        }
        node.isWord = true; // will not work if string is empty
    }

    /**
     * Searches the Trie to see if the word is in the Trie
     * @param word word to search for
     * @return true if the word was found in the tree
     */
    public boolean search(String word) {
        Node node = searchNode(word);
        return node != null && node.isWord;
    }

    /**
     * Searches the Trie to see if there's any word in the tree that starts with the given prefix
     * @param prefix prefix to search for
     * @return true if there's any word in the tree that starts with the given prefix
     */
    public boolean beginsWith(String prefix) {
        Node node = searchNode(prefix);
        return node != null;
    }

    // RECURSIVE VERSION... NOT WORKING
    /**
     * Finds and returns all words that begin with the given prefix in the Trie
     * @param prefix to search for words that begin with
     * @return
     */
    public List<String> allThatBeginsWith(String prefix) {
        Node root = searchNode(prefix);
        return allThatBeginsWith(root, new StringBuilder(), new ArrayList<>());
    }

    private List<String> allThatBeginsWith(Node root, StringBuilder sb, List<String> foundWords) {
//        List<String> foundWords = new ArrayList<>();
        if (root != null) {
            StringBuilder localSb;
            if (root.isWord) {
                localSb = new StringBuilder(sb);
                localSb.append(root.c);
                foundWords.add(localSb.toString());
            }
            for (Node node : root.kids.values()) {
                localSb = new StringBuilder(sb);
                localSb.append(root.c);
                allThatBeginsWith(node, localSb, foundWords); }
        }
        return foundWords;
    }
//
//    public List<String> getKids() {
//        return getKids(root);
//    }
//
//    private List<String> getKids(Node root) {
//        List<String> kids = new ArrayList<>();
//        char c;
//        for (int i = 0; i < root.kids.length; i++) {
//            if (root.kids[i] != null) {
//                kids.add(root.kids[i].letters);
//            }
//        }
//        return kids;
//    }

    /**
     * Clears the Trie
     */
    public void clear() {
        root.kids = new HashMap<>();
    }

    private Node searchNode(String word) {
        Map<Character, Node> kids = root.kids;
        Node node = root;
        char c;
        for (int i = 0; node != null && i < word.length(); i++) {
            c = word.charAt(i);
            node = kids.getOrDefault(c, null);
            kids = node != null ? node.kids : null;
        }
        return node == root ? null : node;
    }
}
