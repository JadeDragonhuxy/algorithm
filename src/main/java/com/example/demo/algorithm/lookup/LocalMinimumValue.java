package com.example.demo.algorithm.lookup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: algorithm
 * @Package: com.example.demo.algorithm.lookup
 * @ClassName: LocalMinimumValue
 * @Author: huxy
 * @Date: 2020/11/25 13:42
 * @Version: 1.0
 * @Description: 最小值问题
 *               题目：局部最小值问题（局部最小值：如果0位置数小于1位置数则0位置就是局部最小值；
 *                    如果n-1位置小于n-2位置数则n-1位置就是局部最小值；
 *                    否则，左右两边都比其大，其就是局部最小值）
 *               解题思路：虽然数组无序，但是可以采用二分渐进的方式找到一个局部最小值（原理是若存在局部最小值，除了最小值就在数组头尾，
 *                       若在数组中间必然会出现最小值左边必出现下降趋势，最小值右边必出现上升趋势，而若要0位置不是局部最小值需要下降趋势，
 *                       而n-1位置不是最小值需要上升趋势，所以两点之间必出现拐点，局部最小值，利用此原理通过移动终点比较逼近中间的局部最小值）
 *               例题：(1)在数组[3,2,1,0,4,5,6,7,6,5,4,5,7]中求局部最小值；
 *                    (2)首先判断0位置和12位置均不是局部最小值，但两者之间必存在局部最小值，因为数组趋势不唯一；
 *                    (3)第一次二分，(0+12)/2=6,6位置值为6,6>5,右标移到5位置；
 *                    (4)第二次二分，(0+5)/2 =2,2位置值为1,1>0,左标移到3位置；
 *                    (5)第三次二分，(3+5)/2 =4,4位置值为4,4>0,右标移到4位置；
 *                    (6)第四次二分，(3+4)/2 =3,3位置值为0,0既不大于1也不大于4,0就是局部最小值，直接返回
 *               时间复杂度：最坏是二分法的时间复杂度：O(logN)
 *               空间复杂度：没有引入可变存储变量，O(1)
 *
 *               重点来了：数组及时无序，在特殊的情况下也能二分，二分法不一定非要数组有序。
 *
 *               学习日期：2020年11月25日，18:45（坚持学习，加油）
 *
 *
 */
public class LocalMinimumValue {

    //存在问题，当数组所有数值相同时？？？？？
    //二分法查找
    public static int LocalMinimumValue(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        if(arr.length == 1 || arr [0] < arr [1]){
            return 0;
        }
        if(arr[arr.length - 2] > arr [arr.length - 1]){
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while (L < R){
            mid = (L+R)/2;
            if (arr [mid] > arr[mid - 1]){
                R = mid - 1;
            }else if (arr [mid] > arr [mid + 1]){
                L = mid + 1;
            }else {
                return mid;
            }
        }
        return L;
    }

    //对比方法
    public static List<Integer> comparator(int[] arr){
        List<Integer> list = new ArrayList<>();
        if (arr == null || arr.length == 0){
            list.add(-1);
            return list;
        }
        if(arr.length == 1 || arr [0] < arr [1]){
            list.add(0);
            return list;
        }
        if(arr[arr.length - 2] > arr [arr.length - 1]){
            list.add(arr.length - 1);
            return list;
        }

        for (int i = 1; i < arr.length -1 ; i++) {
            if (arr[i] < arr[i-1] && arr[i] < arr[i+1]){
                list.add(i);
            }
        }
        return list;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int [] arr = new int [(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr [i] = (int) ((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    public static void logarithm(){
        int testTime = 100;
        int maxSize = 100;
        int maxValue = 100;
        Boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize , maxValue);
 //           int[] arr = {-72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72 , -72};
            List<Integer> list = comparator(arr);
            int k = LocalMinimumValue(arr);
            if (!list.contains(k)){
                succeed = false;
                printArray(arr);
                System.out.println("暴力查找：" + list.toString());
                System.out.println("二分法：" + k);
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
            System.out.print(arr[1] + " , ");
        }
        System.out.print(" ]");
        System.out.println();
    }

    public static void main(String[] args){
        logarithm();
//        int [] arr = {
//                -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16 , -16
//        };
//        List list = comparator(arr);
//        System.out.println(list.toString());
//        int [] arr = {
//
//        }
    }


}
