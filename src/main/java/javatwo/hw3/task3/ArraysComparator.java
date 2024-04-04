package javatwo.hw3.task3;

public class ArraysComparator {

    public static <T, V> boolean compareArrays(T[] arr1, V[] arr2) {
        return arr1.getClass().equals(arr2.getClass()) && arr1.length == arr2.length;
    }

    public static void main(String[] args) {
        System.out.println(compareArrays(new Byte[]{1, 2, 3},
                new Integer[]{4, 5, 6})); //false

        System.out.println(compareArrays(new Long[]{1L, 2L, 3L},
                new Long[]{4L, 5L, 6L, 7L})); //false

        System.out.println(compareArrays(new Double[]{1.0, 2.5, 3.8},
                new Double[]{4.12, 5.0, 7.95})); //true

        System.out.println(compareArrays(new String[]{"1.0", "2.5", "3.8"},
                new String[]{"4.12", "5.0", "7.95"})); //true
    }
}
