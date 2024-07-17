public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true

        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
	}

}
