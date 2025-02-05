package packages;

public class GenericMinMaxTest {
    // Step 1: Create a Generic Interface
    interface MinMax<T extends Comparable<T>> {
        T min(T[] array);
        T max(T[] array);
    }

    // Step 2: Implement the Generic Interface (This class must be static)
    static class MinMaxImpl<T extends Comparable<T>> implements MinMax<T> {

        @Override
        public T min(T[] array) {
            T min = array[0];
            for (T element : array) {
                if (element.compareTo(min) < 0) {
                    min = element;
                }
            }
            return min;
        }

        @Override
        public T max(T[] array) {
            T max = array[0];
            for (T element : array) {
                if (element.compareTo(max) > 0) {
                    max = element;
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {3, 5, 7, 2, 8};
        String[] strArray = {"apple", "orange", "banana", "pear"};
        Character[] charArray = {'A', 'X', 'B', 'Z'};
        Float[] floatArray = {3.5f, 5.7f, 2.1f, 8.3f};

        // Integer MinMax
        MinMax<Integer> intMinMax = new MinMaxImpl<>();
        System.out.println("Integer Array - Min: " + intMinMax.min(intArray) + ", Max: " + intMinMax.max(intArray));

        // String MinMax
        MinMax<String> strMinMax = new MinMaxImpl<>();
        System.out.println("String Array - Min: " + strMinMax.min(strArray) + ", Max: " + strMinMax.max(strArray));

        // Character MinMax
        MinMax<Character> charMinMax = new MinMaxImpl<>();
        System.out.println("Character Array - Min: " + charMinMax.min(charArray) + ", Max: " + charMinMax.max(charArray));

        // Float MinMax
        MinMax<Float> floatMinMax = new MinMaxImpl<>();
        System.out.println("Float Array - Min: " + floatMinMax.min(floatArray) + ", Max: " + floatMinMax.max(floatArray));
    }
}
