package com.example.demo.algorithm.sort;

import java.util.Arrays;

/**
 * @Project: algorithm
 * @Package: com.example.demo.algorithm.logarithm
 * @ClassName: SelectionSort
 * @Author: huxy
 * @Date: 2020/11/19 09:56
 * @Version: 1.0
 * @Description: 选择排序算法
 * 排序思想：  首先，将0位置当做基准，在1位置到n-1位置之间找出最小的数，将最小数与0位置数交换，0位置数有序
 *           其次，将1位置当做基准，在2位置到n-1位置之间找出最小的数，将最小数与1位置数交换，0位置到1位置数有序
 *           重复此操作，直到0位置到n-1位置数有序
 *  举例：(1)要排序数组:[10,1,35,61,89,36,55]
 *       (2)将10当做基准，在【1,35,61,89,36,55】中找到最小值为1
 *          比较10与最小值1，10>1交换
 *          交换后数组为：[1,10,35,61,89,36,55]
 *       (3)将10当做基准，在【35,61,89,36,55】中找到最小值为35
 *          比较10与最小值35，10<35不交换
 *          交换后数组为：[1,10,35,61,89,36,55]
 *       (4)将35当做基准，在【61,89,36,55】中找到最小值为36
 *          比较35与最小值36，35>36不交换
 *          交换后数组为：[1,10,35,61,89,36,55]
 *       (5)将61当做基准，在【89,36,55】中找到最小值为36
 *          比较61与最小值36，61>36交换
 *          交换后数组为：[1,10,35,36,89,61,55]
 *       (6)重复此操作，直到全部有序
 *  时间复杂度： 0到n-1位置，n 次看+比较，1次交换
 *             1到n-1位置，n-1 次看+比较，1次交换
 *             2到n-1位置，n-2 次看+比较，1次交换
 *             。。。。
 *             n-1到n-1位置，1 次看+比较，1次交换
 *             时间复杂度计算为：n+(n-1)+(n-2)+(n-3)+...+2+1 = a*n^2+b*n+c （等差数列）
 *             ====================================================================
 *             因此：时间复杂度为：O(n^2) （时间复杂度忽略低阶项和常数，只取最高阶项）
 *  空间复杂度： 算法中只使用了基本数据类型变量，因而空间复杂度为O(1)
 *
 *  学习日期：2020年11月19日，9:56（坚持学习，加油，你比昨天强了，明天会更棒）
 */
public class SelectionSort {

    //排序算法
    public static void selectSort(int[] arr){

        //首先，当待排数组为空，或长度小于2时，直接结束，不需排序
        if (arr == null || arr.length < 2){
            return;
        }

        //遍历整个待排序区间
        for (int i = 0 ; i < arr.length; i++ ){
            int minIndex = i;//将当前待排序区域第一个值当做基准
            for (int j = i+1; j < arr.length; j++) {//遍历待排序区间中1位置到区间结束的所有值
                minIndex = arr [j] < arr [minIndex] ? j : minIndex; //将待排序区间1位置到n-1位置的数,依次与当前最小数比较，找出待排序区间最小值
            }
            swap(arr, i, minIndex); //将最小值与基准位置数交换
        }
    }

    //交换两个数
    private static void swap(int[] arr, int i, int minIndex) {
        int temp = arr [i];
        arr [i] = arr [minIndex];
        arr [minIndex] = temp;
    }

    public static void main(String[] args){
        int [] arr = {
            1,5,2,4,6,2,5,7,99,34,54
        };
        System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
