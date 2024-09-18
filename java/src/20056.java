//20056 16:00 
import java.util.*;
import java.io.*;

public class Main_20056 {
    static class Fireball{
        int x, y, m, s, d;
        Fireball(int x, int y, int m, int s, int d){
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static ArrayList<Fireball>[][] board;
    static ArrayList<Fireball> fire_list = new ArrayList<>();

    public static void main(String args[]) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()),
        M = Integer.parseInt(st.nextToken()),
        K = Integer.parseInt(st.nextToken());

        board = new ArrayList[N][N];
        for (int i = 0; i< N; i++){
            for(int j = 0 ; j< N; j++){
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            fire_list.add(
                new Fireball(
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
                )
            );
        }

        int play_num = 0;
        // System.out.println();
        while (play_num++ < K){
            move(N);

            // System.out.println("move---");
            // for(Fireball fire: fire_list){
            //     System.out.println(fire.x + " " + fire.y + " " + fire.m + " "+ fire.s + " " + fire.d);
            // }
            // System.out.println();

            // for(int i = 0; i < N; i++){
            //     for(int j= 0 ; j< N; j++){
            //         System.out.print(board[i][j].size() + " ");
            //     }
            //     System.out.println();
            // }

            // System.out.println("combien---");
            combine(N);

            // for(Fireball fire: fire_list){
            //     System.out.println(fire.x + " " + fire.y + " " + fire.m + " "+ fire.s + " " + fire.d);
            // }
            // System.out.println();

        }

        int sum = 0;
        for(Fireball fire: fire_list){
            sum += fire.m;
        }

        System.out.println(sum);

    }

    static int[][] direction = {
        {-1, 0},
        {-1, 1},
        {0, 1},
        {1, 1},

        {1, 0},
        {1, -1},
        {0, -1},
        {-1, -1}
    };

    public static void move(int N){
        for (int i =0 ; i < fire_list.size(); i++){
            Fireball fire = fire_list.get(i);

            fire.x = (N + fire.x + (fire.s % N) * direction[fire.d][0]) % N;
            fire.y = (N + fire.y + (fire.s % N) * direction[fire.d][1]) % N;

            board[fire.x][fire.y].add(fire);
        }
    }

    static int[][] combine_dir = {
        {0, 2, 4, 6},
        {1, 3, 5, 7}
    };

    public static void combine(int N){
        for(int i = 0; i < N; i++){
            for (int j= 0; j< N; j++){
                if (board[i][j].size() < 2){
                    board[i][j].clear();
                    continue;
                }

                int m_sum = 0, s_sum = 0, divide_sum = 0;

                for(Fireball fire: board[i][j]){
                    m_sum += fire.m;
                    s_sum += fire.s;
                    divide_sum += fire.d % 2;
                    fire_list.remove(fire);
                }

                int combine_f_num = board[i][j].size();

                if (divide_sum == 0 || divide_sum == board[i][j].size()){
                    divide_sum = 0;
                }
                else {
                    divide_sum = 1;
                }

                board[i][j].clear();

                if (m_sum / 5 == 0)
                    continue;

                for (int dir: combine_dir[divide_sum]){
                    Fireball new_fire = new Fireball(
                        i, j, 
                        m_sum / 5, 
                        s_sum /combine_f_num, 
                        dir
                    );

                    fire_list.add(new_fire);
                }

            }
        }
    }
    
}

