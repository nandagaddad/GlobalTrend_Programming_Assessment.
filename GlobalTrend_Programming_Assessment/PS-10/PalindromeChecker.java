import java.util.Scanner;
public class PalindromeChecker {

	 // Method to check if a given string is a palindrome
    public static boolean isPalindrome(String str) {
        // Remove all non-alphanumeric characters and convert the string to lowercase
        String cleanedStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Initialize two pointers, one at the start and one at the end of the cleaned string
        int left = 0;
        int right = cleanedStr.length() - 1;

        // Loop until the pointers meet in the middle
        while (left < right) {
            // Compare the characters at the left and right pointers
            if (cleanedStr.charAt(left) != cleanedStr.charAt(right)) {
                // If characters do not match, return false
                return false;
            }
            // Move the pointers towards the center
            left++;
            right--;
        }
        
        // If all character comparisons are valid, return true
        return true;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);
	        
	        // Prompt the user to enter a string
	        System.out.println("Enter a string to check if it is a palindrome:");
	        
	        // Read the input string
	        String input = scanner.nextLine();
	        
	        // Close the scanner to prevent resource leaks
	        scanner.close();
	        
	        // Check if the input string is a palindrome and print the result
	        if (isPalindrome(input)) {
	            System.out.println("The string is a palindrome.");
	        } else {
	            System.out.println("The string is not a palindrome.");
	        }
	}

}
