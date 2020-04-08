package wangyi;

import java.util.Scanner;
//英雄与最近怪物的距离
public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < m; j ++){
                arr[i][j] = sc.nextInt();
            }
        }

        int[] res = {n*m};
        boolean[][] flag = new boolean[n][m];
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < m; j ++){
                if (arr[i][j] == 0){
                    System.out.print(0);
                }else {

                    countDistance(arr, flag, i, j, n, m, 0, res);
                    System.out.print(res[0]);
                }
                if (j == m-1) {
                    System.out.println();
                }
                else {
                    System.out.print(" ");
                }
            }
        }
    }

    public static void countDistance(int[][] arr, boolean[][] flag, int i, int j, int n, int m, int count, int[] res){
        if (i < 0 || i >= n || j < 0 || j >= m || flag[i][j]) {
            return;
        }
        if (arr[i][j] == 0){
            res[0] = res[0] < count ? res[0] : count;
        }
        flag[i][j] = true;
        countDistance(arr, flag, i+1, j, n, m, count+1, res);
        countDistance(arr, flag, i-1, j, n, m, count+1, res);
        countDistance(arr, flag, i, j+1, n, m, count+1, res);
        countDistance(arr, flag, i, j-1, n, m, count+1, res);
        flag[i][j] = false;
    }
}
