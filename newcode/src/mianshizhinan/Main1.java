package mianshizhinan;

import java.util.HashSet;
import java.util.Scanner;

public class Main1 {
    /*
先给出可整合数组的定义：如果一个数组在排序之后，每相邻两个数的差的绝对值都为1，或者该数组长度为1，则该数组为可整合数组。
例如，[5, 3, 4, 6, 2]排序后为[2, 3, 4, 5, 6]，符合每相邻两个数差的绝对值都为1，所以这个数组为可整合数组
给定一个数组arr, 请返回其中最大可整合子数组的长度。例如，[5, 5, 3, 2, 6, 4, 3]的最大可整合子数组为[5, 3, 2, 6, 4]，
所以请返回5
[要求]
时间复杂度为O(n^2)，空间复杂度为O(n)
输入描述:
第一行一个整数N，表示数组长度
第二行N个整数，分别表示数组内的元素
输出描述:
输出一个整数，表示最大可整合子数组的长度
     */
    public static void main(String[] args){
        //数据输入
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int arr[] = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        int maxCount = 1;
        HashSet<Integer> set = new HashSet<>();//用来存放将要比较的序列
        for (int i = 0; i < n; i++){
            if (n - maxCount < i)
                break;//继续往后找，也没有比当前子序列长的
            set.add(arr[i]);
            int min = arr[i];
            int max = arr[i];
            for (int j = i+1; j < n; j++){
                if (set.contains(arr[j]))
                    break;
                set.add(arr[j]);

                if (arr[j] < min)
                    min = arr[j];
                if (arr[j] > max)
                    max = arr[j];
                if (max - min == j - i)//如果最大值和最小值的差等于set长度，则该子序列数值是连续的
                    maxCount = (j - i + 1) <= maxCount ? maxCount : j-i+1;
            }
            set.clear();
        }
       System.out.println(maxCount);
    }
}
