package com.example.demo.algorithm.lookup;

import java.util.Arrays;

/**
 * @Project: algorithm
 * @Package: com.example.demo.algorithm.lookup
 * @ClassName: SimpleBinarySearch
 * @Author: huxy
 * @Date: 2020/11/23 18:54
 * @Version: 1.0
 * @Description: 简单经典二分查找
 *               题目：在一个有序数组中，找某个数是否存在
 *               实现原理：对于一个有序数组，先通过（0+（n-1））/2找到数组中间位置的索引，取出该位置的值与目标值比较，如果数组索引位置值大于目标值，则目标值必在索引位置前边，如果数组索引位置值小于目标值，则目标值必在数组后半边；
 *                       在目标值所在的半边重复此操作，直到找到目标值或者划分后待查找区域为空。
 *               举例：（1）待查找数组[0,1,2,3,4,5,6,7,8,9,10],目标值为：3
 *                    （2）第一次找到中间位置索引：（0+10）/2=5；找到5位置数值为：5；5>3
 *                        因而目标值必在5位置的前边；
 *                    （3）第二次找到中间位置索引：（0+4）/2=2；找到2位置数值为：2；2<3
 *                        因而目标值必在2位置的后边；
 *                    （4）第三次找到中间位置索引：（3+4）/2=3（系统中向下取整，不要问为啥向下取整，就说int类型就没有存储小数部分的存储空间，系统又不会四舍五入，难道不是直接舍弃小数部分，不是向下取整吗）
 *                        找到3位置数值为：3，3=3找到目标值，结束返回。
 *               时间复杂度：在理想的情况下，第一次划分就找到目标，最优情况O（1）；
 *                         但是在最坏的的情况下，直到划分到最后才找到；对于二分次数必定满足：2^k=n（k指划分次数，n为数组长度）（例如：长度为16的数组，8位置，3位置，1位置，直到0位置最后一次，最多划分4次，2^4=16）
 *                         对于长度n处于2^k<n<2^j，也只需二分k次（不信你试），因而时间负责度为：O（logn）
 *                         综述：二分查找的时间复杂度为：O（logn）（时间负责度取最坏情况下）
 *               空间复杂度：只使用了有限个数的常量，因而空间复杂度为：O(1)
 *
 *               注意：使用二分法必须数组有序吗？不是，在特定的题目下是不需要数组有序也能二分的，详情例题请见局部最小值问题。
 *
 *               学习日期：2020年11月23日，22:38（what are you 弄啥嘞！进度这么慢，坚持学习，加油）
 */
public class SimpleBinarySearch {

    /*
      二分查找方法，arr待查找数组，num 目标值
     */
    public static boolean simpleBinarySearch(int[] arr , int num){
        if (arr == null || arr.length == 0){ //当数组为null或者数组长度为0时，肯定不存在目标值
            return false;
        }
        int L = 0;  //查找区域左边界，初始时在数组开头
        int R = arr.length - 1;  //查找区域右边界，初始时在数组最后位置
        int mid = 0;  //中间位置索引值
        while(L <= R){
            mid = (L + R)/2; //计算中间位置索引，此公式存在缺陷：当数组特别长，L和R都十分巨大时，相加会发生越界，优化写法：L+(R-L)/2,((L +R)/2)必在L和R中间，就等于L到R的个数加上L的位置；又因为/2就等同于带符号右移1位，原式就等于：L+((R-L)>>1)
            // mid = L+((R-L)>>1);
            if (arr[mid] == num){ //相等则当前就说目标值
                return true;
            }else if(arr[mid] > num){ //大于目标值，则目标值在前半边，R前移
                R = mid - 1;
            }else { //小于目标值，则目标值在后半边，L后移
                L = mid + 1;
            }
        }
        return arr[mid] == num;
    }

    /*
     二分查找方法，arr待查找数组，num 目标值
    */
    public static boolean simpleBinarySearch1(int[] arr , int num){
        if (arr == null || arr.length == 0){ //当数组为null或者数组长度为0时，肯定不存在目标值
            return false;
        }
        int L = 0;  //查找区域左边界，初始时在数组开头
        int R = arr.length - 1;  //查找区域右边界，初始时在数组最后位置
        int mid = 0;  //中间位置索引值
        while(L < R){
            mid = (L + R)/2; //计算中间位置索引，此公式存在缺陷：当数组特别长，L和R都十分巨大时，相加会发生越界，优化写法：L+(R-L)/2,((L +R)/2)必在L和R中间，就等于L到R的个数加上L的位置；又因为/2就等同于带符号右移1位，原式就等于：L+((R-L)>>1)
            // mid = L+((R-L)>>1);
            if (arr[mid] == num){ //相等则当前就说目标值
                return true;
            }else if(arr[mid] > num){ //大于目标值，则目标值在前半边，R前移
                R = mid - 1;
            }else { //小于目标值，则目标值在后半边，L后移
                L = mid + 1;
            }
        }
        return arr[L] == num;
    }

    //对数器
    public static void logarithm(){
        int testTime = 100000;
        int maxSize = 10000;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0 ; i < testTime ; i++){
            int arr[] = generateRandomArray(maxSize , maxValue);
            Arrays.sort(arr);
            int value = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random()); //生成随机目标值
            if (comparator(arr , value) != simpleBinarySearch1(arr , value)){
                succeed = false;
                System.out.println(Arrays.toString(arr));
                System.out.println(value);
                System.out.println("对比方法结果：" +comparator(arr , value));
                System.out.println("二分法结果：" +simpleBinarySearch(arr , value));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    //比较查找方法
    private static boolean comparator(int[] arr, int value) {
        for (int i = 0 ; i < arr.length ; i++){
            if (arr[i] == value){
                return true;
            }
        }
        return false;
    }

    //随机数组生成方法
    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i = 0 ; i < arr.length ; i++ ){
            arr[i] = (int)((maxValue +  1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args){
        logarithm();
//        int [] arr = new int []{-66, -59, -47, -17, -1, 4, 7, 65, 86};
//        simpleBinarySearch(arr , 4);
    }

}
