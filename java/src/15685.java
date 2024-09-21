//15685 09:42 - 10:33
import java.util.*;
import java.io.*;

public class 15685 {
    static int[][] direction = { // y, x
        {0,1},
        {-1,0},
        {0,-1},
        {1,0}
    };
    static boolean[][] map = new boolean[101][101];
    static int x, y, finish_g;
    static ArrayList<Integer> move_driection = new ArrayList<>();

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++){
            move_driection.clear();

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            finish_g= Integer.parseInt(st.nextToken());
            // System.out.println("-------------");

            move_driection.add(d);
            map[y][x] = true;
            curve(0, 0);
        }

        int num = 0;

        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]){
                    num++;
                }
            }
        }

        System.out.println(num);

    }

    public static void curve(int cur_g, int start){
        // System.out.println(cur_g);

        //move
        for (int i = start; i < move_driection.size(); i++){
            int to_move_idx = move_driection.get(i);
            x += direction[to_move_idx][1];
            y += direction[to_move_idx][0];

            // System.out.println("move: " + to_move_idx);
            // System.out.println("y: " + y + " x: " + x);
            if (0 > x || x > 100 || 0 > y || y > 100){
                continue;
            }

            map[y][x] = true;
        }

        if (cur_g == finish_g){
            return;
        }

        ArrayList<Integer> add_dir = new ArrayList<Integer>();
        //next move dir
        for (int i = move_driection.size() - 1; i >=0; i--){
            int to_move_idx = move_driection.get(i);
            add_dir.add((to_move_idx + 1) % 4);
        }
        move_driection.addAll(add_dir);

        // for (int i: move_driection){
        //     System.out.print(i + " ");
        // }
        // System.out.println();

        if (start == 0){
            curve(cur_g + 1, 1);
        }
        else{
            curve(cur_g + 1, start * 2);
        }
    }
}