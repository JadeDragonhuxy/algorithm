package com.example.system.operator.eor;

import java.util.*;

/**
 * @Project: algorithm
 * @Package: com.example.demo.operator.eor
 * @ClassName: printOddTimesNum
 * @Author: huxy
 * @Date: 2020/11/27 9:18
 * @Version: 1.0
 * @Description: 题目四：一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 *               解题思路：第一步，遍历数组，对整个数组进行^运算，得到的结果就是这两种奇数次数相^的值，（原理同求数组中一个奇数次数）
 *                       第二步，找出^运算后值的最右侧的1，那么这两个奇数必然一个在做最右侧1位置也为1，一个必然不为1
 *                              （理由，两个数^运算，同为1或同为0结果都为0，只有不一样才为1，所以哪两个奇数的^在最右侧1位置，必然一个为0，一个为1）
 *                       第三步，遍历数组，将数组中所有数分别与最优位置1进行&，结果不等于0的所有数^运算，就能得到其中一个奇数；
 *                       第四步，将第一步得到的数组^运算值与第三步^运算值进行^运算的结果就是另外一个奇数。
 *               时间复杂度：O(N) 只遍历了两次数组
 *               空间复杂度：O(1) 没有用到可变长度的变量
 */
public class printOddTimesNum {

    public static int[] printOddTimesNum(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr [i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int rightOne = eor & (-eor); // 提取出最右的1
        int one = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr [i] & rightOne) != 0){
                one ^= arr [i];
            }
        }
        int otherOne = eor ^ one;
        int[] result = new int [2];
        result[0] = one;
        result[1] = otherOne;
        return result;
        /*System.out.println("其中一个奇数个数为：" + one);
        System.out.println("另外一个奇数个数为：" + otherOne);*/
    }

    public static int[] comparator(int[] arr ){
        if (arr == null || arr.length == 0){
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int [] result = new int [2];
        int i = 0;
        for (int num : map.keySet()) {
            if ((map.get(num) % 2) != 0) {
                result [i++] = num;
            }
        }
        return result;
    }

    public static void logarithm(){
        int testTime = 10000;
        int maxSize = 10000;
        int range = 10000;
        int maxKind = 100;
        Boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxKind , range , maxSize);
            int[] ans1 = printOddTimesNum(arr);
            int[] ans2 = comparator(arr);
            Arrays.sort(ans1);
            Arrays.sort(ans2);
            if (!Arrays.equals(ans1 , ans2)){
                succeed = false;
                printArray(arr);
                printArray(ans1);
                printArray(ans2);
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


    public static int[] randomArray(int maxKind , int range, int maxSize){
        //数组中包含多少种数
        int arrayKind = 0;
        do {
            arrayKind = (int) ((Math.random() * maxKind)+1);
        }while (arrayKind < 3);
        List<Integer> integers = new ArrayList<> ();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= 2; i++){
            int num = 0;
            do {
                num = randomNumber(range);
            }while (set.contains(num));
            set.add(num);
            int size = (((int)(Math.random() * maxSize)) * 2) + 1;
            for (int j = 0; j < size; j++){
                integers.add(num);
            }
        }
        for (int i = 0; i < arrayKind - 2; i++){
            int num = 0;
            do {
                num = randomNumber(range);
            }while (set.contains(num));
            set.add(num);
            int size = ((int)(Math.random() * maxSize)) * 2;
            for (int j = 0; j < size; j++){
                integers.add(num);
            }
        }
        int[] arr = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            arr[i] = integers.get(i);
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    private static void printArray(int[] arr) {
        if (arr == null){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
        System.out.println();
    }

    public static void main(String[] args){
//        int[] arr = randomArray(5,100,20);
//        printArray(arr);
//        int[] arr2 = comparator(arr);
//        printArray(arr2);
        logarithm();
    }

}
