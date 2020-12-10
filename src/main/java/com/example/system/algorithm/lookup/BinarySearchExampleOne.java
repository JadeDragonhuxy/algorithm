package com.example.system.algorithm.lookup;

import java.util.Arrays;

/**
 * @Project: algorithm
 * @Package: com.example.demo.algorithm.lookup
 * @ClassName: BinarySearchExampleOne
 * @Author: huxy
 * @Date: 2020/11/24 9:14
 * @Version: 1.0
 * @Description: 二分查找例题一
 *               题目：在一个有序数组中，找>=某个数最左侧的位置
 *               解题思路：使用二分法进行查找，每次找到待查找区域中间位置，中间位置数值与目标数值相比较，如果>=就将中间位置索引记下，继续二分中间位置数组左边，
 *                       如果不大于则不更新记下的位置值，并在二分位置右边继续二分，重复操作直到数组二分不再有值为止。
 *               举例：（1）查找出数组[1,2,2,2,3,3,3,3,3,4,4,4,4,5,5,5,6]中>=3的最左侧位置
 *                    （2）第一次二分，找到（0+16）/2=8，找到8位置值为3，因为3=3，
 *                        满足条件记下位置tar=8，继续在数组左边二分；
 *                    （3）第二次二分，找到（0+7）/2=3，找到3位置值为2，因为2<3，
 *                        不满足条件，不更新tar,继续在3位置右边二分；
 *                    （4）第三次二分，找到（4+7）/2=5，找到5位置值为3，因为3=3，
 *                        满足条件更新位置tar=5，继续在5位置左边二分；
 *                    （5）第四次二分，找到（4+5）/2=4，找到4位置值为3，因为3=3，
 *                        满足条件更新位置tar=4，5位置左边4位置右边没数了，二分到此为止；
 *                    （6）找到>=3的最左位置为tar=4位置。
 *               时间复杂度：必须O（logN）,因为必须二分完全，直到不能二分为止
 *               空间复杂度：必须O（1），因为只使用有限变量记录位置
 *
 *               学习日期：2020年11月24日，22:38（进度有点慢哦，坚持学习，加油）
 */
public class BinarySearchExampleOne {

    public static int LeftmostPositionSearch(int[] arr , int targetValue){
        if (arr == null || arr.length == 0){
            return  -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr [mid] >= targetValue){
                index = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static void logarithm(){
        int testTime = 1000000;
        int maxSize = 10000;
        int maxValue = 100000;
        Boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize , maxValue);
            Arrays.sort(arr);
            int value = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
            if (comparator(arr , value) != LeftmostPositionSearch(arr , value)){
                succeed = false;
                printArray(arr);
                System.out.println("暴力查找：" + comparator(arr , value));
                System.out.println("二分法：" + LeftmostPositionSearch(arr , value));
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    public static void printArray(int[] arr) {
        if (arr == null){
            return;
        }
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[1] + ',');
        }
        System.out.print(" ]");
        System.out.println();
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int [] arr = new int [(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr [i] = (int) ((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    public static int comparator(int[] arr , int targetValue){
        if (arr == null || arr.length == 0){
            return  -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= targetValue){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        logarithm();
    }

}
