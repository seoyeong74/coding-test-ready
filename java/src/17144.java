//17144 12:58 - 12:52
import java.io.*;
import java.util.*;

public class Main_17144 {
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int R = Integer.parseInt(st.nextToken()), 
        C = Integer.parseInt(st.nextToken()), 
        T = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        int[][] machine = new int[2][2];
        int machine_idx = 0;

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j< C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1){
                    machine[machine_idx][0] = i;
                    machine[machine_idx++][1] = j;
                }
            }
        }

        
        for (int i = 0; i < T; i++){
            map = spread(map);
            // System.out.println();
            // for(int i_ = 0; i_ < R; i_++){
            //     for(int j = 0; j< C; j++){
            //         System.out.print(map[i_][j] + " ");
            //     }
            //     System.out.println();
            // }

            remove(map, machine);
        }

        int sum = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j< C; j++){
                sum += map[i][j];
            }
        }

        sum += 2;

        System.out.println(sum);
    }

    static int[][] spread_direction = {{0,1}, {1, 0}, {-1, 0}, {0, -1}};

    public static int[][] spread(int[][] map){
        int R = map.length, C = map[0].length;

        int[][] new_map = new int[R][C];

        for (int i = 0; i < R; i++){
            for (int j= 0; j< C; j++){
                if (map[i][j] == 0){
                    continue;
                }

                int leave_a = map[i][j];
                int spread_a = map[i][j] / 5;

                for(int k = 0; k < spread_direction.length; k++){
                    int new_x = i + spread_direction[k][0];
                    int new_y = j + spread_direction[k][1];

                    if (new_x < 0 || new_x >= R){
                        continue;
                    }
                    if (new_y < 0 || new_y >= C){
                        continue;
                    }

                    if(map[new_x][new_y] == -1)
                        continue;

                    new_map[new_x][new_y] += spread_a;
                    leave_a -= spread_a;
                }

                new_map[i][j] += leave_a;
            }
        }

        return new_map;
    }

    static int[][][] remove_direction = {
        {{0,1}, {-1, 0}, {0, -1}, {1, 0}},
        {{0,1}, {1, 0}, {0, -1}, {-1, 0}}
    };

    public static void remove(int map[][], int[][] machine){
        int R = map.length, C = map[0].length;

        for (int i = 0; i < 2; i++){
            int machine_x = machine[i][0], machine_y = machine[i][1];
            int cur_x = machine_x, cur_y = machine_y;

            int prev = 0;
            for (int j = 0; j < 4; j++){
                int plus_x = remove_direction[i][j][0];
                int plus_y = remove_direction[i][j][1];

                cur_x += plus_x;
                cur_y += plus_y;

                while (cur_x >= 0 && cur_x <= R - 1 && cur_y >= 0 && cur_y <= C - 1){
                    int tmp = map[cur_x][cur_y];
                    map[cur_x][cur_y] = prev;
                    prev = tmp;

                    if (prev < 0)
                        prev++;

                    // System.out.println(cur_x + " " + cur_y + " " + prev);

                    if (cur_x == machine_x && cur_y == machine_y)
                        {break;}
                    
                    cur_x += plus_x;
                    cur_y += plus_y;
                }

                cur_x -= plus_x;
                cur_y -= plus_y;
            }

            map[machine_x][machine_y] = -1;
        }
    }
}
