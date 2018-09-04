package sort.quickSort;

public class QuickSort {
    public void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    public int partition(int[] arr, int low, int high) {
        int privot = arr[low];

        while (low < high) {
            while (low < high && arr[high] >= privot) {
                high --;
            }
            swap(arr, low, high);
            while (low < high && arr[low] <= privot) {
                low ++;
            }
            swap(arr, low, high);
        }

        return low;
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int privot = partition(arr, low, high);
            quickSort(arr, low, privot - 1);
            quickSort(arr, privot + 1, high);
        }
    }
}
