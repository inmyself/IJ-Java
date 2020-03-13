package wangyi;

import java.util.Scanner;

public class Main1 {
    //被三整除
    /*
    小Q得到一个神奇的数列: 1, 12, 123,...12345678910,1234567891011...。

    并且小Q对于能否被3整除这个性质很感兴趣。

    小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
     */
    //找到如下规律：xooxoo。。。 三个为一组，第一个不可整除，后两个可以
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int r = sc.nextInt();
        sc.close();

        if (l % 3 == 0 || l % 3 == 2)
            System.out.println(num(r) - num(l) + 1);
        else
            System.out.println(num(r) - num(l));

    }

    public static int num(int x){
        if (x % 3 == 2)
            return (x / 3) * 2 + 1;
        else
            return (x / 3) * 2;
    }

}
