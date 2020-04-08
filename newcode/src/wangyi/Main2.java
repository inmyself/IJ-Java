package wangyi;

import java.util.*;

//牛牛找工作
public class Main2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++){
            int Di = sc.nextInt();
            int Pi = sc.nextInt();
            map.put(Pi, Di);
        }

        for (int i = 0; i < M; i++){
            int Ai = sc.nextInt();
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            int res = 0;
            while (iterator.hasNext()){
                Map.Entry<Integer, Integer> entry = iterator.next();
                if (Ai >= entry.getValue())
                    res = entry.getKey() > res ? entry.getKey() : res;
            }
            System.out.println(res);
        }


    }
}
