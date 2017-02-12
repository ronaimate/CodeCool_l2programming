package sort;

public class Sorter {
    static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    static void swap(int[] array, int k, int l) {
        int temp = array[k];
        array[k] = array[l];
        array[l] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 6, 5, 123, 9, 56754, 3, 123, 1, 345677, 7};
        System.out.print("Array before sort: ");
        printer(array);
        sort(array);
        System.out.print("Array after sort: ");
        printer(array);
    }

    private static void printer(int[] array) {
        for (int item : array) {
            System.out.print(item + ", ");
        }
    }

}
