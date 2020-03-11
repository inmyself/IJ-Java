package arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //股神
    public static int getPrice(int day){
        int price = 1;
        if (day == 1)
            return 1;
        int begin = 2;
        int down = 3;//下降的日期
        int time = 3;//下跌的间隔
        while (begin <= day){
            if(begin == down){
                price--;
                down += time;
                time++;
            }else
                price++;
            begin++;

        }
        return price;
    }
    public static void main(String[] args){
        //int[] a = new int[1000000000];
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int day = sc.nextInt();
            System.out.println(getPrice(day));
        }

        List<Integer> list = new ArrayList<>();

    }
}
