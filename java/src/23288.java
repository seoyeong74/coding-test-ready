//23288 19:36-20:22
import java.util.*;
public class 23288 {
    static int N, M, K;
    static int[][] map;
    //동 서 남 북
    static int[][] direction_map = {
        {0,1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        map = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++){
            for (int j= 1; j < M + 1; j++){
                map[i][j] = sc.nextInt();
            }
        }

        int x = 1, y = 1;
        int direction = 0;
        int score = 0;

        for (int i = 0; i < K; i++){
            int add_x = direction_map[direction][0], add_y = direction_map[direction][1];
            //이동
            if (!is_out(x + add_x, y + add_y)){
                x += add_x;
                y += add_y;
                roll(direction);
            }
            //맵 밖으로 나가면 반대 방향으로
            else {
                x -= add_x;
                y -= add_y;
                if (direction % 2 == 0){
                    direction++;
                }
                else{
                    direction--;
                }

                roll(direction);
            }

            //점수 계산
            score += cal_score(x, y);

            int A = dice[1];
            int B = map[x][y];
            if (A > B){
                //동 -> 남
                if (direction == 0) direction = 2;
                //서 -> 북
                else if (direction == 1) direction = 3;
                //남 -> 서
                else if (direction == 2) direction = 1;
                //북 -> 동
                else if (direction == 3) direction = 0;
            }
            else if (A < B){
                //동 -> 북
                if (direction == 0) direction = 3;
                //서 -> 남
                else if (direction == 1) direction = 2;
                //남 -> 동
                else if (direction == 2) direction = 0;
                //북 -> 서
                else if (direction == 3) direction = 1;
            }

        }

        System.out.println(score);
    }

    //위 아래 동 서 남 북
    static int[] dice = {1, 6, 3, 4, 5, 2}; 

    public static void roll(int direction){
        //동
        if (direction == 0){
            int tmp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[2];
            dice[2] = tmp;
        }
        //서
        else if (direction == 1){
            int tmp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[3];
            dice[3] = tmp;
        }
        //남
        if (direction == 2){
            int tmp = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[4];
            dice[4] = tmp;
        }
        //북
        if (direction == 3){
            int tmp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[5];
            dice[5] = tmp;
        }
    }

    public static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[][] direction = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1},
    };

    public static int cal_score(int x, int y){
        Deque<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M + 1];

        visited[x][y] = true;
        q.addLast(new Point(x, y));

        int num = 1;
        while(!q.isEmpty()){
            Point cur = q.pollFirst();

            for(int i = 0; i < 4; i++){
                int a_x = cur.x + direction[i][0];
                int a_y = cur.y + direction[i][1];

                if (is_out(a_x, a_y)) continue;
                if (visited[a_x][a_y]) continue;
                if (map[a_x][a_y] != map[x][y]) continue;

                // System.out.println(a_x + " " + a_y);
                visited[a_x][a_y] = true;
                num++;
                q.addLast(new Point(a_x, a_y));
            }
        }

        return num * map[x][y];
    }

    public static boolean is_out(int x, int y){
        if (x < 1 || y < 1 || x > N || y > M) return true;
        else return false;
    }
    
}
