//17140 21:31-
import java.io.*;
import java.util.*;

public class 17140 {
    static int r, c, k;
    static int[][] map = new int[101][101];
    static int max_x = 3, max_y = 3;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        for(int i = 1; i <= 3; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int t = 0;

        while (t <= 100){
            if (map[r][c] == k){
                break;
            }

            if (max_x >= max_y){
                cal_r();
            }
            else{
                cal_c();
            }

            t++;
        }

        if (t > 100){
            System.out.println(-1);
        }
        else{
            System.out.println(t);
        }
        
    }

    public static void cal_r(){
        int new_max_y = 0;

        for (int i = 1; i <= max_x; i++){
            Map<Integer, Integer> num_map = new HashMap<>();
            for (int j = 1; j <= max_y; j++){
                if (map[i][j] == 0)
                    continue;
                if (num_map.containsKey(map[i][j])){
                    num_map.put(map[i][j], num_map.get(map[i][j]) + 1);
                }
                else{
                    num_map.put(map[i][j], 1);
                }
            }

            ArrayList<Integer> sortKeys = sortKey(num_map);
            new_max_y = Math.max(new_max_y, sortKeys.size() * 2);
            int idx = 1;
            for (int key: sortKeys){
                map[i][idx++] = key;
                map[i][idx++] = num_map.get(key);

            }

            if (idx <= max_y){
                for(int j = idx; j <= max_y; j++){
                    map[i][j] = 0;
                }
            }
        }

        max_y = calMax(new_max_y);
    }

    public static void cal_c(){
        int new_max_x = 0;
        for (int j = 1; j <= max_y; j++){
            Map<Integer, Integer> num_map = new HashMap<>();
            for (int i = 1; i <= max_x; i++){
                if (map[i][j] == 0)
                    continue;

                if (num_map.containsKey(map[i][j])){
                    num_map.put(map[i][j], num_map.get(map[i][j]) + 1);
                }
                else{
                    num_map.put(map[i][j], 1);
                }
            }

            ArrayList<Integer> sortKeys = sortKey(num_map);
            new_max_x = Math.max(new_max_x, sortKeys.size() * 2);

            int idx = 1;
            for (int key: sortKeys){
                map[idx++][j] = key;
                map[idx++][j] = num_map.get(key);

            }

            if (idx <= max_x){
                for(int i = idx; i <= max_x; i++){
                    map[i][j] = 0;
                }
            }
        }

        max_x = calMax(new_max_x);
    }

    public static ArrayList<Integer> sortKey(Map<Integer, Integer> num_map){
        ArrayList<Integer> key = new ArrayList<>(num_map.keySet());
        key.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2){
                if(num_map.get(o2) == num_map.get(o1)){
                    return o1 - o2;
                }
                else {
                    return num_map.get(o1) - num_map.get(o2);
                }
            }
        });

        return key;
    }

    public static int calMax(int size){
        if(size > 100){
            return 100;
        }
        return size;
    }
    
}
