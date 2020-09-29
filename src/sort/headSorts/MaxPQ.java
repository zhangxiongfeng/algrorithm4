package sort.headSorts;

public class MaxPQ <Key extends Comparable<Key>>{
    private Key[] pq;
    private int N=0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 打印
     */
    public void show() {
        for (int i = 1; i < N; i++) {
            System.out.print(pq[i] + " ");
        }
        System.out.println();
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    /**
     * 上浮插入的元素
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            // 节点上浮
            k = k / 2;
        }
    }

    /**
     * 下沉因删除操作而移动到根节点的最后一个节点
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            // 注意： 此处需要j<N,防止K=N时候的边界溢出
            if (j < N && less(j, j + 1)) {
                j++;
            }
            // 如果父节点大于子节点则break
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            // 节点下沉
            k = j;
        }
    }

    /**
     * 插入操作，需上浮插入的元素
     * @param v
     */
    protected void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    protected Key delMax() {
        Key max = pq[1];
        exch(1, N);

        // 游离并删除交换后的元素
        N--;
        pq[N + 1] = null;

        // 下沉交换后的根节点
        sink(1);
        return max;
    }
}
