//17837 20:22-21:13
import java.util.*;

public class 17837 {
    static int N, K;
    static ArrayList<Integer>[][] move_map;
    static int[][] map;
    static Info[] hourse;
    static boolean end = false;

    static class Info{
        int x, y;
        int d;
        Info(int x, int y, int d){
            this.x=  x;
            this.y= y;
            this.d = d;
        }
    }

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        move_map = new ArrayList[N][N];
        map = new int[N][N];

        hourse = new Info[K];

        for(int i = 0 ; i< N; i++){
            for(int j = 0; j< N; j++){
                move_map[i][j] = new ArrayList<>();
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < K; i++){
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            int d = sc.nextInt() - 1;

            move_map[x][y].add(i);
            hourse[i] = new Info(x, y, d);
        }

        // for (int i = 0; i < N; i++){
        //     for(int j = 0; j < N; j++){
        //         if (move_map[i][j].size() == 0) System.out.print("0 ");
        //         else{
        //             for(int k: move_map[i][j]) System.out.print(k + ",");
        //             System.out.print(" ");
        //         }
        //     }
        //     System.out.println();
        // }

        int turn = 1;

        while(turn <= 1000){
            // System.out.println(turn);
            move();
            // System.out.println();
            // for (int i = 0; i < N; i++){
            //     for(int j = 0; j < N; j++){
            //         if (move_map[i][j].size() == 0) System.out.print("0 ");
            //         else{
            //             for(int k: move_map[i][j]) {System.out.print((k + 1) + ",");}
            //             System.out.print(" ");
            //         }
            //     }
            //     System.out.println();
            // }

            if (end) {
                System.out.println(turn);
                return;
            }
            turn++;
        }

        System.out.println(-1);
    }

    public static int[][] direction = {
        {0,1},
        {0,-1},
        {-1, 0},
        {1, 0}
    };

    public static void move(){
        for(int i = 0; i < K; i++){
            Info cur = hourse[i];

            int next_x = cur.x + direction[cur.d][0];
            int next_y = cur.y + direction[cur.d][1];

            if (next_x < 0 || next_y < 0 || next_x >= N || next_y >= N){
                next_x = cur.x + direction[reflect(cur.d)][0];
                next_y = cur.y + direction[reflect(cur.d)][1];
                cur.d = reflect(cur.d);
            }
            else if (map[next_x][next_y] == 2){
                next_x = cur.x + direction[reflect(cur.d)][0];
                next_y = cur.y + direction[reflect(cur.d)][1];
                cur.d = reflect(cur.d);

                if (next_x < 0 || next_y < 0 || next_x >= N || next_y >= N) continue;
            }
            
            if (map[next_x][next_y] == 0){
                white(next_x, next_y, cur, i);
            }
            else if (map[next_x][next_y] == 1){
                red(next_x, next_y, cur, i);
            }
            else if (map[next_x][next_y] == 2){
            }
        }
    }

    public static int reflect(int d){
        if (d == 0) return 1;
        else if (d == 1) return 0;
        else if (d == 2) return 3;
        else return 2;
    }

    public static void white(int to_x, int to_y, Info cur, int num){
        // System.out.println("white! "+ to_x + " " + to_y);
        ArrayList<Integer> list = move_map[cur.x][cur.y];

        int i;
        for (i = 0; i < list.size(); i++){
            if (list.get(i) == num) break;
        }
        
        List<Integer> to_move = list.subList(i, list.size());
        // System.out.println(to_move.size());

        int last_x = cur.x;
        int last_y = cur.y;

        //말 옮기기
        for(int k: to_move){
            hourse[k].x = to_x;
            hourse[k].y = to_y;
        }
        move_map[to_x][to_y].addAll(to_move);
        // System.out.println(move_map[to_x][to_y].size());

        //말 제거
        move_map[last_x][last_y].removeAll(to_move);

        if (move_map[to_x][to_y].size() >= 4) end = true;
    }

    public static void red(int to_x, int to_y, Info cur, int num){
        ArrayList<Integer> list = move_map[cur.x][cur.y];

        int i;
        for (i = 0; i < list.size(); i++){
            if (list.get(i) == num) break;
        }

        int last_x = cur.x;
        int last_y = cur.y;
        List<Integer> to_move = list.subList(i, list.size());

        //말 옮기기
        for(int j = to_move.size() - 1; j >=0; j--){
            int k = to_move.get(j);
            hourse[k].x = to_x;
            hourse[k].y = to_y;

            move_map[to_x][to_y].add(k);
        }

        //말 제거
        move_map[last_x][last_y].removeAll(to_move);

        if (move_map[to_x][to_y].size() >= 4) end = true;
    }
}
