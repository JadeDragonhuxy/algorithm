package com.example.system.algorithm.lookup;

import java.util.Arrays;

/**
 * @Project: algorithm
 * @Package: com.example.demo.algorithm.lookup
 * @ClassName: BinarySearchExampleTwo
 * @Author: huxy
 * @Date: 2020/11/25 11:05
 * @Version: 1.0
 * @Description: 二分查找例题一
 *               题目：在一个有序数组中，找<=某个数最右侧的位置
 *               解题思路：使用二分法进行查找，每次找到待查找区域中间位置，中间位置数值与目标数值相比较，如果<=就将中间位置索引记下，继续二分中间位置数组右边，
 *                       如果不小于则不更新记下的位置值，并在二分位置左边继续二分，重复操作直到数组二分不再有值为止。
 *               举例：（1）查找出数组[1,2,2,2,3,3,3,3,3,4,4,4,4,5,5,5,6]中<=3的最右侧位置
 *                    （2）第一次二分，找到（0+16）/2=8，找到8位置值为3，因为3=3，
 *                        满足条件记下位置tar=8，继续在数组右边二分；
 *                    （3）第二次二分，找到（9+16）/2=12，找到12位置值为4，因为4>3，
 *                        不满足条件，不更新tar,继续在12位置左边二分；
 *                    （4）第三次二分，找到（9+11）/2=10，找到10位置值为4，因为4>3，
 *                        不满足条件，不更新tar,继续在10位置左边二分；
 *                    （5）第四次二分，找到（9+9）/2=9，找到9位置值为4，因为4>3，
 *                        不满足条件，不更新tar,9位置左边9位置右边没数了，二分到此为止；
 *                    （6）找到<=3的最左位置为tar=8位置。
 *               时间复杂度：必须O（logN）,因为必须二分完全，直到不能二分为止
 *               空间复杂度：必须O（1），因为只使用有限变量记录位置
 *
 *               学习日期：2020年11月25日，11:08（坚持学习，加油）
 */
public class BinarySearchExampleTwo {

    public static int rightmostPositionSearch(int[] arr , int targetValue){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if (arr [mid] <= targetValue) {
                index = mid;
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return index;
    }

    public static int comparator(int[] arr , int targetValue){
        if (arr == null || arr.length == 0){
            return  -1;
        }
        for (int i = arr.length - 1; i >= 0 ; i--) {
            if (arr[i] <= targetValue){
                return i;
            }
        }
        return -1;
    }

    public static int[] generateRandomArray(int maxSize , int maxValue){
        int [] arr = new int [(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++){
            arr [i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
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

    public static void logarithm(){
        int testTime = 1000000;
        int maxSize = 1000;
        int maxValue = 10000;
        Boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize , maxValue);
            Arrays.sort(arr);
            int value = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
            if (comparator(arr , value) != rightmostPositionSearch(arr , value)){
                succeed = false;
                printArray(arr);
                System.out.println("暴力查找：" + comparator(arr , value));
                System.out.println("二分法：" + rightmostPositionSearch(arr , value));
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    public static void main(String[] args){
        logarithm();
    }

}
