package com.example.basis.class01;

/**
 * @Project: algorithm
 * @Package: com.example.basis.class01
 * @ClassName: PrintBinary
 * @Author: huxy
 * @Date: 2020/12/10 16:33
 * @Version: 1.0
 * @Description: 题目：输入一个整数，输出其32位二进制的值
 */
public class PrintBinary {

    public static void printBinary(int num){
        for (int i = 0; i <= 31; i++){
            System.out.print((num & (1 << i)) == 1 ? "1" : "0");
        }
    }


}
