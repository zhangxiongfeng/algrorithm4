package sort.headSorts;

public class headSort<Key extends Comparable<Key>> {
    public static void sort(Comparable[] a) {
        int N = a.length - 1;
        // 先进行堆构造
        for (int k = N/2; k >0; k--) {
            sink(a, k, N);
        }

        // 再进行遍历整个数组的下沉操作
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
        show(a);
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2*k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void show(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
