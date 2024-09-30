//20057 17:40-18:30
import java.util.*;

public class 20057 {
    static int N;
    static int[][] map;
    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        int init_sand = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j< N; j++){
                map[i][j] = sc.nextInt();
                init_sand += map[i][j];
            }
        }

        move();

        int sum = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j< N; j++){
                sum += map[i][j];
            }
        }

        System.out.println(init_sand - sum);
    }

    static int[][] direction = {
        {0, -1},
        {1, 0},
        {0, 1},
        {-1, 0}
    };

    static int[] dir_num = {1, 1, 2, 2};

    public static void move(){
        int x = N / 2, y = N / 2;
        int iter = 0;
        // int[][] test = new int[N][N];
        // int test_num = 0;
        // test[x][y] = test_num++;
        while(true){
            for(int i = 0; i < 4; i++){
                if (x == 0 && y == 0) break;
                for (int j = 0; j < dir_num[i] + iter; j++){
                    x += direction[i][0];
                    y += direction[i][1];
                    // System.out.println(x + " " + y);
                    // test[x][y] = test_num++;

                    //토네이도
                    //d, x, y
                    play(i, x, y);

                    if (x == 0 && y == 0) break;
                }
            }

            if (x == 0 && y == 0) break;

            iter += 2;
        }

        // System.out.println();

        // for (int i = 0; i < N; i++){
        //     for (int j = 0; j< N; j++){
        //         System.out.print(test[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }

    static int[][][] wind = {
        //0
        //y에 적혀있는 것 기준
        //x, y, 비율
        {
            {-1, 1, 1},
            {1, 1, 1},

            {-1, 0, 7},
            {1, 0, 7},

            {-2, 0, 2},
            {2, 0, 2},

            {-1, -1, 10},
            {1, -1, 10},

            {0, -2, 5},
            {0, -1, 99}
        },
        //1
        {
            {-1, 1, 1},
            {-1, -1, 1},

            {0, 1, 7},
            {0, -1, 7},

            {0, 2, 2},
            {0, -2, 2},

            {1, -1, 10},
            {1, 1, 10},

            {2, 0, 5},
            {1, 0, 99}
        },
        //2
        {
            {-1, -1, 1},
            {1, -1, 1},

            {-1, 0, 7},
            {1, 0, 7},

            {-2, 0, 2},
            {2, 0, 2},

            {-1, 1, 10},
            {1, 1, 10},

            {0, 2, 5},
            {0, 1, 99}
        },
        //3
        {
            {1, -1, 1},
            {1, 1, 1},

            {0, 1, 7},
            {0, -1, 7},

            {0, 2, 2},
            {0, -2, 2},

            {-1, -1, 10},
            {-1, 1, 10},

            {-2, 0, 5},
            {-1, 0, 99}
        }
    };

    public static void play(int d, int x, int y){
        int sand_init = map[x][y], sand = map[x][y];

        for (int i = 0; i < 9; i++){
            int a_x = x + wind[d][i][0];
            int a_y = y + wind[d][i][1];

            int cal = sand_init * wind[d][i][2] / 100;
            sand -= cal;

            if (a_x < 0 || a_y < 0 || a_x >= N || a_y >= N){
                continue;
            }

            map[a_x][a_y] += cal;
        }

        map[x][y] = 0;

        int alpha_x = x + wind[d][9][0], alpha_y = y + wind[d][9][1];

        if (alpha_x < 0 || alpha_y < 0 || alpha_x >= N || alpha_y >= N){
            return;
        }

        map[alpha_x][alpha_y] += sand;
    }
}