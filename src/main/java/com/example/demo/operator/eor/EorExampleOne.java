package com.example.demo.operator.eor;

/**
 * @Project: algorithm
 * @Package: com.example.demo.operator.eor
 * @ClassName: EorExampleOne
 * @Author: huxy
 * @Date: 2020/11/26 9:01
 * @Version: 1.0
 * @Description: 异或运算符的基本用法与特点
 *                  运算规则：异或运算符：相同为0，不同为1（方便记忆：异或运算即，无进为相加）
 *                          同或运算符：相同为1，不同为0
 *                  特性：（1）0^N = N ; N^N = 0
 *                       （2）异或运算满足交换律和结合律。
 *
 */
public class EorExampleOne {

    /**
     *@参数:  arr 待交换数组,
     *        i 待交换位置一,
     *        j 待交换位置二
     *@返回值:  void
     *@创建人:  huxyd
     *@创建时间:  2020/11/26 9:09
     *@修改人和其它信息:
     *@描述: 题目一：如何不用额外变量交换两个数
     */
    public static void swap(int[] arr , int i , int j ){
        arr [i] = arr [i] ^ arr [j]; //执行完此步后得到：arr [i] = arr [i] ^ arr [j]
        arr [j] = arr [i] ^ arr [j]; //arr [j] = arr [i] ^ arr [j]在第一步的前提下可转换为：arr [j] = arr [i] ^ arr [j] ^ arr [j],由于异或运算满足结合律，且N^N=0,0^N=N，所以arr[j] = arr[i];
        arr [i] = arr [i] ^ arr [j]; //arr [i] = arr [i] ^ arr [j];在前二步的结果上可转换为：arr [i] = arr [i] ^ arr [j] ^ arr[i],由于异或运算满足结合律，且N^N=0,0^N=N，所以arr[i] = arr[j].
        /**
         * 使用如上三行代码即可不使用额外空间的前提下，完成两个值交换,但是。。。
         * 但是若要以上交换变量生效且正确，必须满足要交换的两个变量在内存中是两个不同的内存区域，如果两个变量指向的存储区域相同，则最后会将值刷成0
         * 例如：在此方法中i=j,最后数组此位置的值会变成0,
         * 原因：如果在两个内存区域中，第一步两个相同值异或为0存在其中一个区域，另一个区域仍为原值，后两步0异或那个原值，仍为那个值，完成交换；
         *      如果在一个内存区域中，第一步两个相同值异或为0，就将这个值改为0，后两次计算都是0^0=0,所以最后结果刷成0
         */
    }

    /**
     *@参数:  arr 待查找数组
     *@返回值:  void
     *@创建人:  huxyd
     *@创建时间:  2020/11/26 10:19
     *@修改人和其它信息:
     *@描述: 题目二：一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     */
    public static int FindOddTimesNumber(int[] arr){
        /**
         * 解题思路：根据异或运算的特性：0^N=N，N^N=0,那么偶数个相同的数^，结果坑定为0，
         * 而在奇数个数时，前偶数个数^为0，在与最后一个数^，还为那个数，同时^满足交换律和结合律，
         * 因而将所有数全部异或运算后的结果就是那种奇数
         */
        int eor = 0;
        for (int i = 0; i < arr.length ; i++) {
            eor ^= arr [i];
        }
        return eor;
    }


    public static void main(String[] args){
        /*int [] arr = {3,2,5,4,6};
        System.out.println("交换前" + Arrays.toString(arr));
        swap(arr , 2,4);
        System.out.println("交换后" + Arrays.toString(arr));*/

        int [] arr = {1,2,3,3,2,1,2,2,3,2,3,2,1};
        int num = FindOddTimesNumber(arr);
        System.out.println(num);

    }

}
