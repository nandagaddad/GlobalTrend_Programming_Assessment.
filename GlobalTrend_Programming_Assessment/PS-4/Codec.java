import java.util.*;
public class Codec {
	
	 // Definition for a binary tree node.
	public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
	
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // If the root is null, return the string "null"
        if (root == null) {
            return "null";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                // Append "null" for null nodes
                sb.append("null,");
            } else {
                // Append the value of the node
                sb.append(node.val).append(",");
                // Add left and right children to the queue
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        // Remove the trailing comma
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // If the data is "null", return null
        if (data.equals("null")) {
            return null;
        }

        // Split the data by commas to get node values
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!values[i].equals("null")) {
                // Create the left child and add it to the queue
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.add(node.left);
            }
            i++;
            if (!values[i].equals("null")) {
                // Create the right child and add it to the queue
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.add(node.right);
            }
            i++;
        }

        return root;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Serialize the tree
        String serialized = codec.serialize(root);
        System.out.println("Serialized tree: " + serialized);

        // Deserialize the string back to the tree
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized tree root value: " + deserialized.val);
	}

}
