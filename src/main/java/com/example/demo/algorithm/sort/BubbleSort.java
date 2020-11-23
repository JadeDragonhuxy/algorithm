package com.example.demo.algorithm.sort;

import com.example.demo.algorithm.logarithm.SortLogarithm;

/**
 * @Project: algorithm
 * @Package: com.example.demo.algorithm.logarithm
 * @ClassName: BubbleSort
 * @Author: huxy
 * @Date: 2020/11/19 15:38
 * @Version: 1.0
 * @Description: 冒泡排序算法
 * 排序思想：  首先，从0位置出发到n-1位置为止，相邻两个数作比较，将前一个比后一个大则交换位置，反之不交换，结束后最大数将会在n-1位置
 *           其次，从0位置出发到n-2位置为止，相邻两个数作比较，将前一个比后一个大则交换位置，反之不交换，结束后第二大数将会在n-2位置
 *           重复此操作，直到0位位置出发到0位置为止
 *  举例：(1)要排序数组:[10,1,35,61,89,36,55]
 *       (2)第一趟排序：
 * 　　　　　　第一次排序：10和1比较，10大于1，交换位置 　　 　[1,10,35,61,89,36,55]
 * 　　　　　　第二趟排序：10和35比较，10小于35，不交换位置　　[1,10,35,61,89,36,55]
 * 　　　　　　第三趟排序：35和61比较，35小于61，不交换位置　　[1,10,35,61,89,36,55]
 * 　　　　　　第四趟排序：61和89比较，61小于89，不交换位置　　[1,10,35,61,89,36,55]
 *  　　　　　 第五趟排序：89和36比较，89大于36，交换位置　　　[1,10,35,61,36,89,55]
 * 　　　　　　第六趟排序：89和55比较，89大于55，交换位置　　　[1,10,35,61,36,55,89]
 * 　　　　　　第一趟总共进行了六次比较，排序结果：[1,10,35,61,36,55,89]
 *      (3)第二趟排序：
 * 　　　　　　第一次排序：1和10比较，1小于10，不交换位置　　1,10,35,61,36,55,89
 * 　　　　　　第二次排序：10和35比较，10小于35，不交换位置    1,10,35,61,36,55,89
 * 　　　　　　第三次排序：35和61比较，35小于61，不交换位置     1,10,35,61,36,55,89
 * 　　　　　　第四次排序：61和36比较，61大于36，交换位置　　　1,10,35,36,61,55,89
 * 　　　　　　第五次排序：61和55比较，61大于55，交换位置　　　1,10,35,36,55,61,89
 * 　　　　　　第二趟总共进行了5次比较，排序结果：1,10,35,36,55,61,89
 * 　　　　(4)第三趟排序：
 * 　　　　　　1和10比较，1小于10，不交换位置　　1,10,35,36,55,61,89
 * 　　　　　　第二次排序：10和35比较，10小于35，不交换位置    1,10,35,36,55,61,89
 * 　　　　　　第三次排序：35和36比较，35小于36，不交换位置     1,10,35,36,55,61,89
 * 　　　　　　第四次排序：36和61比较，36小于61，不交换位置　　　1,10,35,36,55,61,89
 * 　　　　　　第三趟总共进行了4次比较，排序结果：1,10,35,36,55,61,89
 *       （5）重复此操作直到全部有序
 *  时间复杂度： 时间复杂度最好的的情况是：数据本身就是正序，只需要遍历比较1次，因我最优的最理想情况下时间复杂度为：O(n)；
 *             但如果数据本来就是逆序：
 *             0到n-1位置，n-1 比较交换
 *             0到n-2位置，n-2 比较次交换
 *             。。。。
 *             0到1位置，1 比较交换
 *             0到0位置，0 比较交换
 *             最坏情况下时间复杂度计算为：n+(n-1)+(n-2)+(n-3)+...+2+1+0 = a*n^2+b*n+c （等差数列）
 *             最坏情况下时间复杂度为：O(n^2)
 *             ==============================================================
 *             因此：时间复杂度为：O(n^2) （一般时间复杂度只考虑最坏情况下）
 *  空间复杂度： 算法中只使用了基本数据类型变量，因而空间复杂度为O(1)
 *
 *  学习日期：2020年11月19日，15:38（你进步了哦，坚持学习，加油，你会更棒的）
 *
 */
public class BubbleSort {

    //排序方法写法一
    public static void bubbleSort1(int[] arr){

        //首先，当待排数组为空，或长度小于2时，直接结束，不需排序
        if (arr == null || arr.length < 2){
            return;
        }
        //遍历整个数组
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {//冒泡排序是将大的数字通过每次比较安排放到右边，因而循环i次则靠右的i个数值有序，所以每次待排序区间为0位置到arr.length-i-1位置，循环该区间
                if (arr [j] > arr [j+1]){//将当前位置与当前位置加1位置的比较，当前大则交换
                    swap(arr , j , j+1);//交换数据位置
                }
            }
        }
    }

    //排序方法写法二
    public static void bubbleSort2(int[] arr){

        //首先，当待排数组为空，或长度小于2时，直接结束，不需排序
        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = arr.length-1; i > 0 ; i--) {//从数组arr.length-1位置开始倒序遍历数组
            for (int j = 0; j < i; j++) {//因为每循环一次，都将一个待排序区间的最大数放到待排序区间最后位置，因而外层循环i次，则从arr.length-1位置开始的i个数据有序，所以待排序区间始终为0位置到i-1位置
                if (arr [j] > arr [j+1]){//将当前位置与当前位置加1位置的比较，当前大则交换
                    swap(arr , j , j+1);//交换数据位置
                }
            }
        }
    }

    //交换两个数位置
    private static void swap(int[] arr, int j, int i) {
        //^运算符，相同为0,不同为1。(不进位相加)（^运算满足交换律和结合律）
        //i= 3（00000011） j=4(00000100)
        arr[j] = arr[j] ^ arr[i]; //第一次^运算：（3）00000011^（4）00000100 = 00000111（7）
        arr[i] = arr[j] ^ arr[i]; //第二次^运算：（7）00000111^（3）00000011 = 00000100（4）
        arr[j] = arr[j] ^ arr[i]; //第三次^运算：（7）00000111^（4）00000100 = 00000011（3）

    }

    public static void main(String[] args){
        /*int [] arr = {
                1,5,2,4,6,2,5,7,99,34,54
        };
        System.out.println("排序前：" + Arrays.toString(arr));
        bubbleSort2(arr);
        System.out.println("排序后：" + Arrays.toString(arr));*/
        SortLogarithm.LogarithmTest(BubbleSort.class , "bubbleSort1" , "bubbleSort2" , 1000000 , 1000 , 10000 );
    }

}
