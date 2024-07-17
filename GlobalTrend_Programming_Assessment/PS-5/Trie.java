public class Trie {

	    // Trie Node definition
	    class TrieNode {
	        // Each node can have 26 children, one for each lowercase English letter
	        private TrieNode[] children;
	        // Flag to check if this node marks the end of a word
	        private boolean isEndOfWord;

	        public TrieNode() {
	            children = new TrieNode[26];
	            isEndOfWord = false;
	        }
	    }

	    // Root of the Trie
	    private TrieNode root;

	    /** Initialize your data structure here. */
	    public Trie() {
	        root = new TrieNode();
	    }

	    /** Inserts a word into the trie. */
	    public void insert(String word) {
	        TrieNode node = root;
	        for (char c : word.toCharArray()) {
	            int index = c - 'a';
	            if (node.children[index] == null) {
	                node.children[index] = new TrieNode();
	            }
	            node = node.children[index];
	        }
	        node.isEndOfWord = true;
	    }

	    /** Returns if the word is in the trie. */
	    public boolean search(String word) {
	        TrieNode node = searchPrefix(word);
	        return node != null && node.isEndOfWord;
	    }

	    /** Returns if there is any word in the trie that starts with the given prefix. */
	    public boolean startsWith(String prefix) {
	        return searchPrefix(prefix) != null;
	    }

	    /** Helper method to search for a prefix or whole word in the trie. */
	    private TrieNode searchPrefix(String word) {
	        TrieNode node = root;
	        for (char c : word.toCharArray()) {
	            int index = c - 'a';
	            if (node.children[index] == null) {
	                return null;
	            }
	            node = node.children[index];
	        }
	        return node;
	    }
	}
