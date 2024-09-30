//15684 18:12 -
//풀이 봄
import java.util.*;
import java.io.*;

public class 15684 {
    static int N, M, H;
    static boolean[][] map;
    static ArrayList<Point> possible_bridge = new ArrayList<>();
    static int[] idx_list = new int[3];
    static int ans = -1;

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
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = true;
        }

        // System.out.println();

        for (int i = 1; i <= H; i++){
            for (int j = 1; j < N; j++){
                if (!map[i][j] && !map[i][j + 1] && !map[i][j - 1]){
                    possible_bridge.add(new Point(i, j));
                }
            }
        }

        if (play()){
            System.out.println(0);
            return;
        }


        for (int i = 1; i <= 3; i++){
            if (ans != -1){
                break;
            }
            dfs(0, 0, i);
        }

        // for (Point p: possible_bridge){
        //     System.out.println(p.x + " " + p.y);
        // }

        System.out.println(ans);
    }

    public static boolean play(){
        for (int i = 1; i <= N; i++){
            int y = i;
            for (int x = 1; x <= H; x++){
                //오른쪽으로 다리 연결
                if (map[x][y]) {
                    y++;
                }
                //왼쪽으로 다리 연결
                else if (y - 1 > 0 && map[x][y-1]) {
                    y--;
                }
            }

            if (y != i) return false;
        }
        return true;
    }

    public static void dfs(int start, int num, int max){
        if (ans != -1){
            return;
        }

        if (num == max){
            for (int i = 0 ; i < max; i++){
                map[possible_bridge.get(idx_list[i]).x][possible_bridge.get(idx_list[i]).y] = true;
            }
            if (play()){
                ans = max;
            }
            for (int i = 0 ; i < max; i++){
                map[possible_bridge.get(idx_list[i]).x][possible_bridge.get(idx_list[i]).y] = false;
            }
            return;
        }

        for (int i = start; i < possible_bridge.size(); i++){
            idx_list[num] = i;
            dfs(i + 1, num + 1, max);
        }
    }
    
}
