package sort.headSorts;

public class testPQ {
    public static void main(String[] args) {
        int[] nums={11,1,15,4,2,16,8,13,7,3,10,9,5,12,6,14};
        MaxPQ pq = new MaxPQ(16);
        for (int i = 0; i < 16; i++) {
            pq.insert(nums[i]);
        }
        pq.show();

        Comparable v = pq.delMax();

        System.out.println(v);
    }
}
