package packages;
import java.util.Arrays;
public class GenericBubbleSort {
	public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    // Swap array[j] and array[j+1]
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] intArray = {5, 3, 8, 1, 9, 2};
        System.out.println("Original Integer array: " + Arrays.toString(intArray));
        bubbleSort(intArray);
        System.out.println("Sorted Integer array: " + Arrays.toString(intArray));

        // Test with Double array
        Double[] doubleArray = {5.5, 3.3, 8.8, 1.1, 9.9, 2.2};
        System.out.println("\nOriginal Double array: " + Arrays.toString(doubleArray));
        bubbleSort(doubleArray);
        System.out.println("Sorted Double array: " + Arrays.toString(doubleArray));

        // Test with String array
        String[] stringArray = {"banana", "apple", "pear", "orange", "grape"};
        System.out.println("\nOriginal String array: " + Arrays.toString(stringArray));
        bubbleSort(stringArray);
        System.out.println("Sorted String array: " + Arrays.toString(stringArray));
	}

}
