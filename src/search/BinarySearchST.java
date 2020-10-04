package search;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }
    public boolean isEmpty() {
        return N == 0;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return lo;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public void put(Key key, Value val) {
        // 查找键，如果找到则直接更新
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            vals[i] = val;
            return;
        }
        // 没找到则往后移动
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public static void main(String[] args) {
        int[] nums={11,1,15,4,2,16,8,13,7,3,10,9,5,12,6,14};
        BinarySearchST st = new BinarySearchST(20);
        for (int i = 0; i < nums.length; i++) {
            st.put(i, nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(" " + st.get(i));
        }
    }
}
