//20058 11:25-11:40 stop 11:44- 12:34
import java.util.*;
import java.io.*;

public class 20058 {
    static int N, Q;
    static int L, L2 = 1;
    static int[][] map;
    static int[] square = {
        1, 2, 4, 8, 16, 32, 64
        //0, 1, 2, 3, 4, 5, 6
    };
    static boolean[][] ice;

    static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[square[N]][square[N]];
        ice = new boolean[square[N]][square[N]];

        for (int i = 0; i < square[N]; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j< square[N]; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0){
                    ice[i][j] = true;
                }
                else{
                    ice[i][j] = false;
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i_ = 0; i_ < Q; i_++){
            L = Integer.parseInt(st.nextToken());
            L2 = square[L];
            rotation();
            melt();
        }

        boolean[][] visited = new boolean[square[N]][square[N]];
        int ice_sum = 0;
        int max_ice = 0;
        
        for (int i = 0; i < square[N]; i++){
            for(int j = 0; j< square[N]; j++){
                if (visited[i][j])
                    continue;
                if (map[i][j] == 0){
                    visited[i][j] = true;
                    continue;
                }

                visited[i][j] = true;
                ice_sum += map[i][j];
                int ice_num = 1;
                Deque<Point> q= new ArrayDeque<>();
                q.push(new Point(i, j));

                while(!q.isEmpty()){
                    Point cur = q.poll();

                    for (int k = 0; k< arround.length; k++){
                        int a_x = cur.x + arround[k][0];
                        int a_y = cur.y + arround[k][1];
            
                        if (a_x < 0 || a_y < 0 || a_x >= square[N] || a_y >= square[N]){
                            continue;
                        }
            
                        if (visited[a_x][a_y] || map[a_x][a_y] == 0)
                            continue;

                        visited[a_x][a_y] = true;
                        ice_sum += map[a_x][a_y];
                        ice_num++;
                        q.push(new Point(a_x, a_y));
                    }
                }

                max_ice = Math.max(max_ice, ice_num);
            }
        }

        System.out.println(ice_sum);
        System.out.println(max_ice);
    }

    public static void rotation(){
        int[][] new_map = new int[square[N]][square[N]];

        for (int start_x = 0; start_x < square[N]; start_x += L2){
            for (int start_y = 0; start_y < square[N]; start_y += L2){
                for (int i = 0; i < L2; i++){
                    for(int j= 0; j< L2; j++){
                        new_map[start_x + j][start_y + (L2 - 1 - i)] = map[start_x + i][start_y+j];
                        if (new_map[start_x + j][start_y + (L2 - 1 - i)] > 0){
                            ice[start_x + j][start_y + (L2 - 1 - i)] = true;
                        }
                        else{
                            ice[start_x + j][start_y + (L2 - 1 - i)] = false;
                        }
                    }
                }
            }
        }

        map = new_map;
    }

    public static void melt(){
        for (int i = 0; i < square[N]; i++){
            for(int j = 0; j< square[N]; j++){
                if (map[i][j] == 0)
                    continue;

                int ice_num = cal_ice(i, j);
                if (ice_num >= 3)
                    continue;
                else
                    map[i][j]--;
            }
        }

    }

    static int[][] arround = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    public static int cal_ice(int x, int y){
        int sum = 0;
        for (int i = 0; i< arround.length; i++){
            int a_x = x + arround[i][0];
            int a_y = y + arround[i][1];

            if (a_x < 0 || a_y < 0 || a_x >= square[N] || a_y >= square[N]){
                continue;
            }

            if (ice[a_x][a_y])
                sum++;
        }

        return sum;

    }
    
}
