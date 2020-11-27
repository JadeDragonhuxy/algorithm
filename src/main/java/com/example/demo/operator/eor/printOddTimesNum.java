package com.example.demo.operator.eor;

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

    public static void printOddTimesNum(int[] arr){
        if (arr == null || arr.length == 0){
            return;
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
        System.out.println("其中一个奇数个数为：" + one);
        System.out.println("另外一个奇数个数为：" + otherOne);
    }

    public static int[] randomArray(int maxKind , int range, int k, int m){
        return null;
    }

    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }


    public static void main(String[] args){
        
    }

}
