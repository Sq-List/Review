package binary;

public class BinarySearch {
    /**
     * 基本的二分查找
     * @param arr
     * @param key
     * @return
     */
    public static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (arr[middle] == key) {
                return middle;
            } else if (arr[middle] < key) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }

    // -------------- 变种 --------------

    /**
     * 查找 第一个 与key相等 的元素
     * @param arr
     * @param key
     * @return
     */
    public static int findFirstEqual(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            // 尽量使右边界往左边移
            if (arr[middle] >= key) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        if (left < arr.length && arr[left] == key) {
            return left;
        }

        return -1;
    }

    /**
     * 查找 最后一个 与key相等 的元素
     * @param arr
     * @param key
     * @return
     */
    public static int findLastEqual(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            // 尽量使左边界往右边移
            if (arr[middle] <= key) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        if (right >= 0 && arr[right] == key) {
            return right;
        }

        return -1;
    }

    /**
     * 查找 最后一个 小于等于key 的元素
     * @param arr
     * @param key
     * @return
     */
    public static int findLastSmallerOrEqual(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (arr[middle] <= key) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return right;
    }

    /**
     * 查找 最后一个 小于key 的元素
     * @param arr
     * @param key
     * @return
     */
    public static int findLastSmaller(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (arr[middle] < key) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return right;
    }

    /**
     * 查找 第一个 大于等于key 的元素
     * @param arr
     * @param key
     * @return
     */
    public static int findFirstLargerOrEqual(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (arr[middle] >= key) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }

    /**
     * 查找 第一个 大于key 的元素
     * @param arr
     * @param key
     * @return
     */
    public static int findFirstLarger(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (arr[middle] > key) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
//        int[] arr = {0, 1, 2, 4, 4, 4, 6, 7, 8, 9};
        int[] arr = {2, 5};
//        System.out.println(binarySearch(arr, 4));
//        System.out.println(findFirstEqual(arr, 4));
//        System.out.println(findLastEqual(arr, 4));
//        System.out.println(findLastSmallerOrEqual(arr, 4));
//        System.out.println(findLastSmaller(arr, 4));
        System.out.println(findFirstLargerOrEqual(arr, 3));
//        System.out.println(findFirstLarger(arr, 4));
    }
}
