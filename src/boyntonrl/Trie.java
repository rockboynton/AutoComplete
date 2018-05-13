/*
 * CS2852 - 021
 * Spring 2018
 * Lab 9 - AutoCompleter Revisited
 * Name: Rock Boynton
 * Created: 5/10/2018
 */

package boyntonrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a trie(prefix tree) data structure
 */
public class Trie {

    /**
     * Number of possible characters each node can have as children:
     * alphabetical letters(26), numbers(10), dots, dashes, and slashes
     */
    public static final int NUM_POSSIBLE_CHARACTERS = 39;
    /**
     * Offset between the ASCII value 'a' and ''
     */
    public static final int ASCII_OFFSET = 52;
    /**
     * Number of digits 0-10, and dots, dashes and slashes
     */
    public static final int NUMBER_OF_EXTRA_CHARACTERS = 13;

    private static class Node {
        Node[] kids;
        String letters;
        boolean isWord;

        Node(String letters) {
            kids = new Node[NUM_POSSIBLE_CHARACTERS];
            this.letters = letters;
            isWord = false;
        }
    }

    private Node root; // dummy root node

    /**
     * Construct a new Trie with a root Node containing an empty string
     */
    public Trie() {
        this.root = new Node("");
    }

    /**
     * Adds an entry to this Trie
     * @param word word to be added to the Trie
     */
    public void put(String word) {
        Node node = this.root;
        char c;
        int index;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (Character.isDigit(c) || c == '-' || c == '.' || c == '/') {
                index = c - 'a' + ASCII_OFFSET;
            } else if (Character.isAlphabetic(c)) {
                index = c - 'a' + NUMBER_OF_EXTRA_CHARACTERS;
            } else {
                index = 0;
            }
            if (node.kids[index] == null) {
                Node temp = new Node(word.substring(0, i + 1));
                node.kids[index] = temp;
                node = temp;
            } else {
                node = node.kids[index];
            }
        }
        node.isWord = true;
//        System.out.println("Free memory: " + Runtime.getRuntime().freeMemory() + ", Total Memory:" +
//                    Runtime.getRuntime().totalMemory());
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
        return allThatBeginsWith(root);
    }

    private List<String> allThatBeginsWith(Node root) {
        List<String> foundWords = new ArrayList<>();
        if (root != null) {
            if (root.isWord) {
                foundWords.add(root.letters);
            }
            for (Node node : root.kids) {
                if (node != null) {
                    foundWords.addAll(allThatBeginsWith(node));
                }
            }
        }
        return foundWords;
    }
    // NON RECURSIVE NOT WORKING
//    /**
//     * Finds and returns all words that begin with the given prefix in the Trie
//     * @param prefix to search for words that begin with
//     * @return
//     */
//    public List<String> allThatBeginsWith(String prefix) {
//        List<String> words = new ArrayList<>();
//        Node root = searchNode(prefix);
//        for (Node node : root.kids) {
//            Node temp = node;
//            while (temp.isWord) {
//                words.add(temp.letters);
//            }
//        }
//        return words;
//    }


    public List<String> getKids() {
        return getKids(root);
    }

    private List<String> getKids(Node root) {
        List<String> kids = new ArrayList<>();
        char c;
        for (int i = 0; i < root.kids.length; i++) {
            if (root.kids[i] != null) {
                kids.add(root.kids[i].letters);
            }
        }
        return kids; // FIXME
    }

    /**
     * Clears the Trie
     */
    public void clear() {
        root.kids = new Node[NUM_POSSIBLE_CHARACTERS];
    }

    private Node searchNode(String word) {
        Node node = root;
        char c;
        int index;
        for (int i = 0; node != null && i < word.length(); i++) {
            c = word.charAt(i);
            if (Character.isDigit(c) || c == '-' || c == '.' || c == '/') {
                index = c - 'a' + ASCII_OFFSET;
            } else {
                index = c - 'a' + NUMBER_OF_EXTRA_CHARACTERS;
            }
            if (node.kids[index] != null) {
                node = node.kids[index];
            } else {
                node = null;
            }
        }
        return node == root ? null : node;
    }
}
