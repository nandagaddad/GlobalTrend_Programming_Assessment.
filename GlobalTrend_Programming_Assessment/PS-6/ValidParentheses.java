import java.util.Scanner;
import java.util.Stack;
public class ValidParentheses {
	public boolean isValid(String s) {
        // Stack to keep track of opening brackets
        Stack<Character> stack = new Stack<>();

        // Traverse through each character in the string
        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push it onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // If it's a closing bracket, check if it matches the top of the stack
            else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } 
            // If it doesn't match or the stack is empty (unmatched closing bracket), return false
            else {
                return false;
            }
        }
        // If the stack is empty, all brackets were matched correctly
        return stack.isEmpty();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string containing only '(', ')', '{', '}', '[', ']':");
        String input = scanner.nextLine();
        
        ValidParentheses vp = new ValidParentheses();
        boolean result = vp.isValid(input);
        
        if (result) {
            System.out.println("The input string is valid.");
        } else {
            System.out.println("The input string is not valid.");
        }
        scanner.close();
	}
}
