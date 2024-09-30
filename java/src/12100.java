//12100 16:20-17:30
import java.util.*;

public class 12100 {
    static int N;
    static int[][] init_map;
    static int[][] map;

    //상 하 좌 우
    //0 1 2 3
    static int[] move_list = new int[5];
    static int ans = 0;

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        init_map = new int[N][N];
        map = new int[N][N];

        for (int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                init_map[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= 5; i++){
            move(0, i);
        }

        System.out.println(ans);

    }

    public static void move(int cur, int max){
        if (cur == max){

            for (int i = 0 ; i < N; i++){
                for(int j = 0; j < N; j++){
                    map[i][j] = init_map[i][j];
                }
            }

            // System.out.println("move!");
            for (int i_= 0 ; i_ < max; i_++){
                // System.out.println(move_list[i_]);
                if (move_list[i_] == 0){
                    up();
                }
                else if (move_list[i_] == 1){
                    down();
                }
                else if (move_list[i_] == 2){
                    left();
                }
                else if (move_list[i_] == 3){
                    right();
                }
            }
            for (int i = 0 ; i < N; i++){
                for(int j = 0; j < N; j++){
                    ans = Math.max(ans, map[i][j]);
                    // System.out.print(map[i][j] + " ");
                }
                // System.out.println();
            }
            
            return;
        }

        for (int i = 0; i < 4; i++){
            move_list[cur] = i;
            move(cur + 1, max);
        }
    }

    public static void up(){
        // System.out.println("here!");
        boolean[][] merged = new boolean[N][N];

        for (int j = 0; j < N; j++){
            for (int i = 1; i< N; i++){
                if (map[i][j] == 0) continue;

                int cur;
                //블럭이 있는 곳을 찾아낸다.
                for (cur = i - 1; cur >= 0; cur--){
                    if (map[cur][j] == 0) continue;
                    break;
                }

                if (cur == -1) cur++;

                //비어있는 칸이라면
                if (map[cur][j] == 0){
                    map[cur][j] = map[i][j];
                    map[i][j] = 0;
                }
                //블럭을 합칠 수 있으면
                else if (map[cur][j] == map[i][j] && merged[cur][j] == false){
                    map[cur][j] += map[i][j];
                    map[i][j] = 0;
                    merged[cur][j] = true;
                }
                //블럭이 바로 앞에 붙어있는 경우가 아니라면
                else if(cur != i - 1){
                    map[cur + 1][j] = map[i][j];
                    map[i][j] = 0;
                }
                //가만히 있는다.
                else{}
            }
        }
    }

    public static void down(){
        // System.out.println("here!");
        boolean[][] merged = new boolean[N][N];
        for (int j = 0; j < N; j++){
            for (int i = N - 2; i >= 0; i--){
                if (map[i][j] == 0) continue;

                int cur;
                //블럭이 있는 곳을 찾아낸다.
                for (cur = i + 1; cur < N; cur++){
                    if (map[cur][j] == 0) continue;
                    break;
                }

                if (cur == N) cur--;

                //비어있는 칸이라면
                if (map[cur][j] == 0){
                    map[cur][j] = map[i][j];
                    map[i][j] = 0;
                }
                //블럭을 합칠 수 있으면
                else if (map[cur][j] == map[i][j] && merged[cur][j] == false){
                    map[cur][j] += map[i][j];
                    map[i][j] = 0;
                    merged[cur][j] = true;
                }
                //블럭이 바로 앞에 붙어있는 경우가 아니라면
                else if(cur != i + 1){
                    map[cur - 1][j] = map[i][j];
                    map[i][j] = 0;
                }
                //가만히 있는다.
                else{}
            }
        }
    }

    public static void left(){
        boolean[][] merged = new boolean[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 1; j< N; j++){
                if (map[i][j] == 0) continue;

                int cur;
                //블럭이 있는 곳을 찾아낸다.
                for (cur = j - 1; cur >= 0; cur--){
                    if (map[i][cur] == 0) continue;
                    break;
                }

                if (cur == -1) cur++;

                //비어있는 칸이라면
                if (map[i][cur] == 0){
                    map[i][cur] = map[i][j];
                    map[i][j] = 0;
                }
                //블럭을 합칠 수 있으면
                else if (map[i][cur] == map[i][j] && merged[i][cur] == false){
                    map[i][cur] += map[i][j];
                    map[i][j] = 0;
                    merged[i][cur] = true;
                }
                //블럭이 바로 앞에 붙어있는 경우가 아니라면
                else if(cur != j - 1){
                    map[i][cur + 1] = map[i][j];
                    map[i][j] = 0;
                }
                //가만히 있는다.
                else{}
            }
        }

    }

    public static void right(){
        // System.out.println("here!");
        boolean[][] merged = new boolean[N][N];
        for (int i = 0; i < N; i++){
            for (int j = N - 2; j >= 0; j--){
                if (map[i][j] == 0) continue;

                int cur;
                //블럭이 있는 곳을 찾아낸다.
                for (cur = j + 1; cur < N; cur++){
                    if (map[i][cur] == 0) continue;
                    break;
                }

                if (cur == N) cur--;

                //비어있는 칸이라면
                if (map[i][cur] == 0){
                    map[i][cur] = map[i][j];
                    map[i][j] = 0;
                }
                //블럭을 합칠 수 있으면
                else if (map[i][cur] == map[i][j] && merged[i][cur] == false){
                    map[i][cur] += map[i][j];
                    map[i][j] = 0;
                    merged[i][cur] = true;
                }
                //블럭이 바로 앞에 붙어있는 경우가 아니라면
                else if(cur != j + 1){
                    map[i][cur - 1] = map[i][j];
                    map[i][j] = 0;
                }
                //가만히 있는다.
                else{}
            }
        }
    }
}
