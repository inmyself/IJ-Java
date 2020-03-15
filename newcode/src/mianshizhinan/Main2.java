package mianshizhinan;

import java.util.Scanner;

public class Main2 {
    /*
    给定排序数组arr和整数k，不重复打印arr中所有相加和为k的不降序二元组
例如, arr = [-8, -4, -3, 0, 1, 2, 4, 5, 8, 9], k = 10，打印结果为：
1, 9
2, 8
[要求]
时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)
输入描述:
第一行有两个整数n, k
接下来一行有n个整数表示数组内的元素
输出描述:
输出若干行，每行两个整数表示答案
按二元组从小到大的顺序输出(二元组大小比较方式为每个依次比较二元组内每个数)
     */
    public static void main(String[] args){

        //使用双指针，分别从头部，尾部遍历
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i <  n; i++){
            arr[i] = sc.nextInt();
        }

        int pre = 0;
        int end = n - 1;
        while (end > pre){
            if (arr[pre] + arr[end] > k)//说明k小于数组的中位数
                end --;
            if (arr[pre] + arr[end] < k)
                pre ++;
            if (arr[pre] + arr[end] == k && end != pre){
                //不重复输出
                if (pre-1 >= 0 && arr[pre] == arr[pre-1])
                    pre ++;
                else if (end + 1 < n && arr[end] == arr[end+1])
                    end --;
                else {
                    System.out.println(arr[pre] + " " + arr[end]);
                    end --;
                    pre ++;
                }
            }
        }
    }
}
