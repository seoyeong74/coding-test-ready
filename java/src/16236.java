//16236 21:56-22:52
import java.io.*;
import java.util.*;

public class 16236 {
    static int N;
    static int[][] map;
    static int shark_x, shark_y;
    static int shark_size = 2;
    static int time = 0;
    static int eat_num = 0;

    static class Point{
        int x, y, d;
        Point(int x, int y){
            this.x = x;
            this.y = y;
            this.d = 0;
        }
        Point(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int fish_num = 0;

        for(int i = 0; i< N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j= 0; j< N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9){
                    shark_x = i;
                    shark_y = j;
                }
                else if(map[i][j] != 0){
                    fish_num++;
                }
            }
        }
        
        //무한 루프 방지
        for (int i_ = 0; i_ < fish_num; i_++){
            // System.out.println();
            if(!find_fish()){
                break;
            }

            // for(int i = 0; i< N; i++){
            //     for(int j= 0; j< N; j++){
            //         System.out.print(map[i][j] + " ");
            //     }
            //     System.out.println();
            // }

            // System.out.println("time: " + time);
            // System.out.println("shark size: " + shark_size + " eat: " + eat_num);
            // System.out.println("shark location: " + shark_x + " " + shark_y);

        }

        System.out.println(time);
    }

    static int[][] direction = {
        {0,1},
        {1,0},
        {0,-1},
        {-1,0}
    };

    public static boolean find_fish(){
        // System.out.println("here");
        Deque<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Point(shark_x, shark_y));
        visited[shark_x][shark_y] = true;
        Point select_fish = null;
        int min_dis = -1;

        while(!q.isEmpty()){
            Point cur = q.poll();
            // System.out.println(cur.x + " " + cur.y+ " " + cur.d);

            //물고기 최소거리보다 더 먼 거리는 보지 않음
            if (min_dis != -1 && min_dis < cur.d){
                // System.out.println("break");
                break;
            }

            //먹을 수 있는 물고기가 있음
            if (map[cur.x][cur.y] > 0 && map[cur.x][cur.y] < 7 && map[cur.x][cur.y] < shark_size){
                // System.out.println("find!");
                // System.out.println(cur.x + " " + cur.y+ " " + cur.d);
                if (select_fish == null){
                    select_fish = cur;
                    min_dis = cur.d;
                }
                //거리가 더 가까운 물고기
                else if(select_fish.d > cur.d){
                    select_fish = cur;
                    min_dis = cur.d;
                }
                //거리는 같으며, 가장 위에 있는 물고기
                else if ((select_fish.d == cur.d) && (select_fish.x > cur.x)){
                    select_fish = cur;
                    min_dis = cur.d;
                }
                //거리는 같으며, 가장 위에 있으며, 가장 왼쪽에 있는 물고기
                else if ((select_fish.d == cur.d) && (select_fish.x == cur.x) && (select_fish.y > cur.y)){
                    select_fish = cur;
                    min_dis = cur.d;
                }
            }

            for (int i= 0; i < 4; i++){
                int a_x = cur.x + direction[i][0];
                int a_y = cur.y + direction[i][1];

                if (a_x <0 || a_y < 0|| a_x >= N || a_y >= N) continue;
                if (visited[a_x][a_y] || map[a_x][a_y] > shark_size) continue;

                visited[a_x][a_y] = true;
                q.add(new Point(a_x, a_y, cur.d + 1));
            }
        }

        if (select_fish == null){
            return false;
        }
        else {
            //물고기 먹음
            map[select_fish.x][select_fish.y] = 0;
            eat_num++;
            //시간 더함
            time += select_fish.d;
            //상어 위치 이동
            map[shark_x][shark_y] = 0;
            shark_x = select_fish.x;
            shark_y = select_fish.y;
            map[shark_x][shark_y] = 9;
            //상어 크기 확인
            if (eat_num == shark_size){
                eat_num = 0;
                shark_size++;
            }
            return true;
        }
    }
}