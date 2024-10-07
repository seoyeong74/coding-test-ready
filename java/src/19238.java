//19237 17:08-18:11
import java.util.*;

public class 19237 {
    static int N, M, k;

    //x 위 아래 왼 오
    static int[] direc_x = {0, -1, 1, 0, 0};
    static int[] direc_y = {0, 0, 0, -1, 1};

    static int[][] smell_map;
    static int[][] count_map;

    static class Shark{
        int x, y;
        int d;

        Shark(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    static Shark[] shark_list;
    static int shark_leave;

    static int[][][] shark_level; //[상어 번호][바라보는 방향][우선순위]

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        k = sc.nextInt();

        smell_map = new int[N][N];
        count_map = new int[N][N];

        shark_list = new Shark[M + 1];
        shark_leave = M;

        shark_level = new int[M + 1][5][4];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int shark = sc.nextInt();
                smell_map[i][j] = shark;
                if (shark > 0)
                    count_map[i][j] = k;

                shark_list[shark] = new Shark(i, j, 0);
            }
        }

        for(int i = 1; i<=M; i++){
            shark_list[i].d = sc.nextInt();
        }

        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= 4; j++){
                for(int k = 0; k < 4; k++){
                    shark_level[i][j][k] = sc.nextInt();
                }
            }
        }

        int time = 1;

        while (time <= 1000){
            
            move();

            if (shark_leave == 1){
                System.out.println(time);
                return;
            }
            
            // System.out.println();
            // for (int i = 0; i < N; i++){
            //     for(int j=0 ; j< N; j++){
            //         System.out.print(smell_map[i][j] + ",");
            //         System.out.print(count_map[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            

            time++;
        }

        System.out.println(-1);

    }

    public static void move(){
        int[][] new_smell = new int[N][N];
        boolean[][] no_count = new boolean[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                new_smell[i][j] = smell_map[i][j];
            }
        }

        for(int shark_num = 1; shark_num <= M ; shark_num++){
            if (shark_list[shark_num] == null) continue;

            Shark shark = shark_list[shark_num];
            int[] cur_level = shark_level[shark_num][shark.d];

            int next_x = -1, next_y = -1;
            int next_d = -1;

            //빈칸 찾기
            for (int i = 0; i < 4; i++){
                int to_go_direction = cur_level[i];
                int a_x = shark.x + direc_x[to_go_direction];
                int a_y = shark.y + direc_y[to_go_direction];

                //격자를 벗어나면
                if (a_x < 0 || a_y < 0 || a_x >= N || a_y >= N) continue;

                //빈칸이라면
                if (smell_map[a_x][a_y] == 0){
                    next_x = a_x;
                    next_y = a_y;

                    next_d = to_go_direction;

                    //우선 순위가 높은 방향을 찾으면 끝냄
                    break;
                }
            }

            //빈칸이 없다면 같은 냄새 찾기
            if (next_x == -1 && next_y == -1){
                for (int i = 0; i < 4; i++){
                    int to_go_direction = cur_level[i];
                    int a_x = shark.x + direc_x[to_go_direction];
                    int a_y = shark.y + direc_y[to_go_direction];

                    //격자를 벗어나면
                    if (a_x < 0 || a_y < 0 || a_x >= N || a_y >= N) continue;
    
                    //빈칸이라면
                    if (smell_map[a_x][a_y] == shark_num){
                        next_x = a_x;
                        next_y = a_y;

                        next_d = to_go_direction;
    
                        //우선 순위가 높은 방향을 찾으면 끝냄
                        break;
                    }
                }
            }

            //해당 칸에 자신보다 번호가 낮은 상어가 먼저 갔다면
            if (new_smell[next_x][next_y] != 0 
            && new_smell[next_x][next_y] < shark_num){
                //상어가 쫓겨남
                shark_list[shark_num] = null;
                shark_leave--;
            }
            else{
                //상어가 이동한다.
                shark.x = next_x;
                shark.y = next_y;
                shark.d = next_d;

                new_smell[next_x][next_y] = shark_num;
                no_count[next_x][next_y] = true;
                count_map[next_x][next_y] = k;
            }
        }

        smell_map = new_smell;

        //상어 냄새--
        for (int i = 0; i < N; i++){
            for(int j= 0; j < N; j++){
                //이번에 새로 추가된 냄새
                if (no_count[i][j]) continue;
                //냄새가 없는 곳
                if (smell_map[i][j] == 0) continue;

                //count 빼주기
                if (count_map[i][j] > 1){
                    count_map[i][j]--;
                }
                //냄새가 사라졌을 때
                else{
                    count_map[i][j]--;
                    smell_map[i][j] = 0;
                }
            }
        }

    }
}
