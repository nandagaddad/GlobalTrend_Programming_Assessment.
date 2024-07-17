public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointers to try and find a larger area
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int[] height = {1,8,6,2,5,4,8,3,7};
	     System.out.println("Max water container area: " + maxArea(height));
	}
}
