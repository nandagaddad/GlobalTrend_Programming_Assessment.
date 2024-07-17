import java.util.Random;
import java.util.Scanner;
public class KthLargestElement {
	
    // Method to find the kth largest element in the array
    public static int findKthLargest(int[] nums, int k) {
        // Adjust k to find the correct position in zero-based index
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    // Quickselect method to partition the array and find the kth largest element
    private static int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left]; // If the array has only one element
        }

        // Choose a random pivot index
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);

        // Partition the array around the pivot
        pivotIndex = partition(nums, left, right, pivotIndex);

        if (k == pivotIndex) {
            return nums[k]; // Found the kth largest element
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k); // Search in the left part
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k); // Search in the right part
        }
    }
    
    // Partition method to rearrange the array elements
    private static int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex]; // Pivot value
        swap(nums, pivotIndex, right); // Move pivot to end
        int storeIndex = left; // Store index

        // Move elements less than pivot to the left
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        // Move pivot to its final place
        swap(nums, right, storeIndex);
        return storeIndex;
    }
    
    // Helper method to swap two elements in the array
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);

	        // Read array size
	        System.out.print("Enter the size of the array: ");
	        int n = scanner.nextInt();

	        // Initialize array
	        int[] nums = new int[n];

	        // Read array elements
	        System.out.println("Enter the elements of the array:");
	        for (int i = 0; i < n; i++) {
	            nums[i] = scanner.nextInt();
	        }

	        // Read the value of k
	        System.out.print("Enter the value of k: ");
	        int k = scanner.nextInt();

	        // Close the scanner
	        scanner.close();

	        // Find and print the kth largest element
	        System.out.println("The " + k + "th largest element is: " + findKthLargest(nums, k));
	}
}
