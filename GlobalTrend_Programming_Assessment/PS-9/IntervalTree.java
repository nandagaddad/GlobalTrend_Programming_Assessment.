import java.util.ArrayList;
import java.util.List;
public class IntervalTree {
    // Inner class to represent an interval
    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // Inner class to represent a node in the interval tree
    class Node {
        Interval interval;
        int maxEnd;
        Node left, right;

        Node(Interval interval) {
            this.interval = interval;
            this.maxEnd = interval.end;
        }
    }

    private Node root;

    // Insert a new interval into the interval tree
    public void insertInterval(int start, int end) {
        Interval newInterval = new Interval(start, end);
        root = insert(root, newInterval);
    }

    // Helper method to insert a new interval into the tree
    private Node insert(Node node, Interval interval) {
        if (node == null) {
            return new Node(interval);
        }

        if (interval.start < node.interval.start) {
            node.left = insert(node.left, interval);
        } else {
            node.right = insert(node.right, interval);
        }

        node.maxEnd = Math.max(node.maxEnd, interval.end);
        return node;
    }

    // Delete an interval from the interval tree
    public void deleteInterval(int start, int end) {
        Interval targetInterval = new Interval(start, end);
        root = delete(root, targetInterval);
    }

    // Helper method to delete an interval from the tree
    private Node delete(Node node, Interval interval) {
        if (node == null) {
            return null;
        }

        if (interval.start < node.interval.start) {
            node.left = delete(node.left, interval);
        } else if (interval.start > node.interval.start) {
            node.right = delete(node.right, interval);
        } else if (interval.end == node.interval.end) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node minNode = findMin(node.right);
            node.interval = minNode.interval;
            node.right = delete(node.right, minNode.interval);
        }

        if (node != null) {
            node.maxEnd = Math.max(node.interval.end, 
                        Math.max(getMaxEnd(node.left), getMaxEnd(node.right)));
        }
        return node;
    }

    // Helper method to find the minimum node in the tree
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Helper method to get the maxEnd value of a node
    private int getMaxEnd(Node node) {
        return node == null ? Integer.MIN_VALUE : node.maxEnd;
    }

    // Find all intervals that overlap with the given interval
    public List<Interval> findOverlappingIntervals(int start, int end) {
        Interval targetInterval = new Interval(start, end);
        List<Interval> result = new ArrayList<>();
        findOverlappingIntervals(root, targetInterval, result);
        return result;
    }

    // Helper method to find overlapping intervals
    private void findOverlappingIntervals(Node node, Interval interval, List<Interval> result) {
        if (node == null) {
            return;
        }

        if (isOverlapping(node.interval, interval)) {
            result.add(node.interval);
        }

        if (node.left != null && node.left.maxEnd >= interval.start) {
            findOverlappingIntervals(node.left, interval, result);
        }

        findOverlappingIntervals(node.right, interval, result);
    }

    // Helper method to check if two intervals overlap
    private boolean isOverlapping(Interval interval1, Interval interval2) {
        return interval1.start <= interval2.end && interval2.start <= interval1.end;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 IntervalTree intervalTree = new IntervalTree();
	        // Inserting intervals into the tree
	        intervalTree.insertInterval(15, 20);
	        intervalTree.insertInterval(10, 30);
	        intervalTree.insertInterval(17, 19);
	        intervalTree.insertInterval(5, 20);
	        intervalTree.insertInterval(12, 15);
	        intervalTree.insertInterval(30, 40);

	        System.out.println("Overlapping intervals with [14, 16]: ");
	        // Finding overlapping intervals with [14, 16]
	        List<Interval> overlappingIntervals = intervalTree.findOverlappingIntervals(14, 16);
	        for (Interval interval : overlappingIntervals) {
	            System.out.println("[" + interval.start + ", " + interval.end + "]");
	        }

	        // Deleting interval [10, 30] from the tree
	        intervalTree.deleteInterval(10, 30);
	        System.out.println("Overlapping intervals with [14, 16] after deleting [10, 30]: ");
	        // Finding overlapping intervals with [14, 16] after deletion
	        overlappingIntervals = intervalTree.findOverlappingIntervals(14, 16);
	        for (Interval interval : overlappingIntervals) {
	            System.out.println("[" + interval.start + ", " + interval.end + "]");
	        }
	}

}
