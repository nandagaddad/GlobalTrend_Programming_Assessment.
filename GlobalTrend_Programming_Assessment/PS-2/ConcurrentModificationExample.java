import java.util.*;

public class ConcurrentModificationExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> myList = new ArrayList<>();
        myList.add("one");
        myList.add("two");
        myList.add("three");

        // Demonstrating ConcurrentModificationException
        try {
            for (String item : myList) {
                if (item.equals("three")) {
                    myList.remove(item);  // This will cause ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException e) {
        	e.printStackTrace();
            System.out.println("ConcurrentModificationException caught!");
        }
        
        // Proper way to remove elements using iterator
        Iterator<String> iterator = myList.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equals("three")) {
                iterator.remove();  // This is the correct way to remove elements
            }
        }
        System.out.println("List after modification: " + myList);
	}
}
