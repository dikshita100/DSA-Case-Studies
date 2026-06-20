import java.util.Arrays;

public class HospitalPatientSorting {

    // Counting Sort
    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        int[] count = new int[max + 1];

        for (int num : arr) {
            count[num]++;
        }

        int index = 0;

        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }

    // Radix Sort Helper
    public static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // Radix Sort
    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, exp);
    }

    public static void main(String[] args) {

        int[] patientIds = {245, 103, 512, 89, 320, 156, 401};

        System.out.println("Original Patient IDs:");
        System.out.println(Arrays.toString(patientIds));

        int[] radixArray = patientIds.clone();
        radixSort(radixArray);

        System.out.println("\nSorted Patient IDs using Radix Sort:");
        System.out.println(Arrays.toString(radixArray));

        int[] countingArray = {45, 23, 12, 9, 30, 15, 40};

        countingSort(countingArray);

        System.out.println("\nSorted IDs using Counting Sort:");
        System.out.println(Arrays.toString(countingArray));
    }
}