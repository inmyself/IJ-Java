package wangyi;

import java.util.Scanner;
/*
牛牛以前在老师那里得到了一个正整数数对(x, y), 牛牛忘记他们具体是多少了。
但是牛牛记得老师告诉过他x和y均不大于n, 并且x除以y的余数大于等于k。
牛牛希望你能帮他计算一共有多少个可能的数对。
 */
public class Main3 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int res = 0;
        int m = 1;
        for (int i = 1; i <= n; i++){
            if (i % m >= k)
            for (int j = 1; j <= n - i; j++){

            }
        }
    }
}
