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
        for (int i = 31; i >= 0; i--){
//            System.out.print((num & (1 << i)) == 1 ? "1" : "0");//不能判断等于1，因为在进行与运算以后除非结果为000000000000000000000000000000001与1进行二目运算能为true其余皆为false
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int num = 4;
        printBinary(num);
    }

}
