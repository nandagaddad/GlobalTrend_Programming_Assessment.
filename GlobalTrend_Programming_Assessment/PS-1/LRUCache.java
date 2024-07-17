import java.util.HashMap;
import java.util.Scanner;

public class LRUCache {
	
	 // Node class representing an element in the doubly linked list
	private class Node {
        int key;
        int value;
	    Node prev;
        Node next;
        
        Node(int key, int value) {
	        this.key = key;
            this.value = value;
	    }
	}
	
	private int capacity;
	private HashMap<Integer, Node> map; // HashMap to store key-node pairs
    private Node head;
    private Node tail;
    
    // Constructor to initialize the LRU cache with a given capacity
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    // Get the value of the key if it exists, otherwise return -1
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);  // Move the accessed node to the front
            insert(node);  // as it is the most recently used
            return node.value;
        } else {
            return -1;
        }
    }
    
    // Put the key-value pair into the cache
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);  // Update the node's position to the front
            insert(node);
        } else {
            if (map.size() == capacity) {
                map.remove(tail.prev.key); // Remove the least recently used item
                remove(tail.prev);
            }
            Node node = new Node(key, value);
            map.put(key, node);  // Add new node to the HashMap
            insert(node);  // Insert new node to the front
        }
    }
    
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert a node at the front (right after the head)
    private void insert(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);
	        
	     System.out.println("Enter the capacity of the LRU Cache:");
	     int capacity = scanner.nextInt();
	     LRUCache cache = new LRUCache(capacity);

	     while (true) {
	        System.out.println("Enter operation: (get <key> / put <key> <value> / exit):");
	        String operation = scanner.next();

	        if (operation.equals("get")) {
	            int key = scanner.nextInt();
	            int result = cache.get(key);
	            System.out.println("Get(" + key + ") = " + result);
	        } else if (operation.equals("put")) {
	            int key = scanner.nextInt();
	            int value = scanner.nextInt();
	            cache.put(key, value);
	            System.out.println("Put(" + key + ", " + value + ")");
	        } else if (operation.equals("exit")) {
	            break;
            } else {
	            System.out.println("Invalid operation. Please enter 'get', 'put', or 'exit'.");
	        }
	    }

	    scanner.close();
	}
}
