package com.example.demo.algorithm.logarithm;

import java.lang.reflect.Method;

/**
 * @Project: algorithm
 * @Package: com.example.demo.algorithm.logarithm
 * @ClassName: SortLogarithm
 * @Author: huxy
 * @Date: 2020/11/23 15:52
 * @Version: 1.0
 * @Description: 对数器，通用验证排序方法
 */
public class SortLogarithm {

    /**
     * @参数: clazz 调用类,
     *       verificationMethod 需验证排序方法,
     *       controlMethod 对照方法,
     *       testTime 验证次数,
     *       maxSize 数组最大长度,
     *       maxValue 数组最大值
     * @返回值: void
     * @创建人: huxyd
     * @创建时间: 2020/11/23 16:15
     * @修改人和其它信息:
     * @描述:
     */
    public static void LogarithmTest(Class clazz, String verificationMethod, String controlMethod, int testTime, int maxSize, int maxValue) {
        Boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            try {
                Method vMethod = clazz.getMethod(verificationMethod, int[].class);
                vMethod.invoke(null, arr1);
                Method cMethod = clazz.getMethod(controlMethod, int[].class);
                cMethod.invoke(null, arr2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(verificationMethod,arr1);
                printArray(controlMethod,arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    private static void printArray(String methodName, int[] arr) {
        if (arr == null){
            return;
        }
        System.out.print("方法名为“"+methodName+"”的排序方法排序后结果为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
        System.out.println();
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1 [i] != arr2 [i]){
                return false;
            }
        }
        return true;
    }

    private static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
}
