import java.io.*;
import java.util.*;

public class 17142 {
    static int N, M;
    static int[][] map;
    static int target_num = 0;
    static ArrayList<Virus> virus_list;
    static Virus[] selected;
    static int ans = Integer.MAX_VALUE;
    
    static class Virus{
        int x, y, t;
        Virus(int x, int y){
            this.x = x;
            this.y = y;
            this.t = 0;
        }

        Virus(int x, int y, int t){
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virus_list = new ArrayList<Virus>();
        selected = new Virus[M];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j= 0; j< N; j++){
                int input = Integer.parseInt(st.nextToken());
                if (input == 0){
                    target_num++;
                }
                else if (input == 2){
                    virus_list.add(new Virus(i, j));
                }
                map[i][j] = input;
            }
        }

        if (target_num == 0)
            {System.out.println(0);
                return;
            }

        dfs(0, 0);

        if (ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(ans);
        }

    }

    //success
    public static void dfs(int start, int count){
        if (count == M){
            bfs(target_num);
            return;
        }

        for(int i = start; i < virus_list.size(); i++){
            selected[count] = virus_list.get(i);

            dfs(i + 1, count + 1);
        }

    }

    static int[][] direction = {
        {0, 1}, {0, -1}, 
        {1, 0}, {-1, 0}
    };

    public static void bfs(int target){
        //먼저 도착하는 바이러스가 q에 들어갈 테니 각 칸 별 시간을 작성할 이유가 없음
        boolean[][] infected = new boolean[N][N];

        Deque<Virus> q = new ArrayDeque<>();
        for (Virus v: selected){
            infected[v.x][v.y] = true;
            q.add(v);
        }

        while (!q.isEmpty()){
            //감염됨
            Virus cur_v = q.poll();

            //감염
            for (int i = 0; i < 4; i++){
                int next_x = cur_v.x + direction[i][0];
                int next_y = cur_v.y + direction[i][1];

                //map을 벗어나는지
                if (next_x < 0 || next_y < 0 || next_x >= N || next_y >= N){
                    continue;
                }
                //벽인지
                if (map[next_x][next_y] == 1 || infected[next_x][next_y]){
                    continue;
                }

                if (map[next_x][next_y] == 0){
                    target--;
                }
                
                //종료 조건 확인
                if (target == 0){
                    ans = Math.min(ans, cur_v.t + 1);
                    return;
                }

                infected[next_x][next_y] = true;
                q.add(new Virus(next_x, next_y, cur_v.t + 1));
            }
        }


    }
}
