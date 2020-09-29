package sort.headSorts;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class testHeadSort {
    public static void main(String[] args) {
        int[] nums={0,11,1,15,4,2,16,8,13,7,3,10,9,5,12,6,14};
        
        // 原始类型的数组转换为包装类型的数组
        //先将int数组转换为数值流
        IntStream stream = Arrays.stream(nums);
        //流中的元素全部装箱，转换为流 ---->int转为Integer
        Stream<Integer> integerStream = stream.boxed();
        //将流转换为数组
        Integer[] integers = integerStream.toArray(Integer[]::new);

        headSort.sort(integers);
    }
}
