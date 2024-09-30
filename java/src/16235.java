//16235 16:47-17:44
import java.io.*;
import java.util.*;

public class 16235 {
    static int N, M, K;
    static int[][] add_map;
    static int[][] map;
    static ArrayList<Integer>[][] tree_map;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        add_map = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1];
        tree_map = new ArrayList[N +1][N + 1];

        //map 입력
        for(int i = 1; i<= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<= N; j++){
                add_map[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
                tree_map[i][j] = new ArrayList<>();
            }
        }
        
        //나무 입력
        for (int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            tree_map[x][y].add(z);
        }

        //k년 재테크
        for (int i_ = 0; i_ < K; i_++){
            spring_and_summer();
            fall_and_winter();
        }

        //나무 세기
        int sum = 0;
        for(int i = 1; i<= N; i++){
            for(int j = 1; j<= N; j++){
                sum += tree_map[i][j].size();
            }
        }

        System.out.println(sum);
    }

    static public void spring_and_summer(){
        for(int i = 1; i<= N; i++){
            for(int j = 1; j<= N; j++){
                //여름에 더해질 양분
                int to_add = 0;
                int min_dead_idx = tree_map[i][j].size();
                for (int k = 0; k< tree_map[i][j].size(); k++){
                    int tree_age = tree_map[i][j].get(k);

                    //죽은 나무
                    if (map[i][j] < tree_age){
                        to_add += tree_age / 2;
                        min_dead_idx = Math.min(min_dead_idx, k);
                        // System.out.println("here!: " + min_dead_idx);
                    }
                    //나무 나이 먹음
                    else {
                        map[i][j] -= tree_age;
                        tree_map[i][j].set(k, tree_age + 1);
                    } 
                }

                //죽은 나무 제거
                if (min_dead_idx != tree_map[i][j].size()){
                    tree_map[i][j].subList(min_dead_idx, tree_map[i][j].size()).clear();
                }

                //죽은 나무 양분 더하기
                map[i][j] += to_add;
            }
        }
    }

    static int[][] direction = {
        {-1, -1},
        {-1, 0},
        {-1, 1},
        {0, -1},

        {0, 1},
        {1, -1},
        {1, 0},
        {1, 1}
    };

    static public void fall_and_winter() {
        for(int i = 1; i<= N; i++){
            for(int j = 1; j<= N; j++){
                //가을
                for (int k = tree_map[i][j].size() - 1; k >= 0 ; k--){
                    int tree_age = tree_map[i][j].get(k);
                    if (tree_age < 5){
                        break;
                    }
                    if (tree_age % 5 != 0){
                        continue;
                    }

                    //번식
                    for(int l = 0; l < direction.length; l++){
                        int a_x = i + direction[l][0];
                        int a_y = j + direction[l][1];

                        if (a_x < 1 || a_y < 1 || a_x > N || a_y > N){
                            continue;
                        }
                        //어린 나무 번식 됨
                        tree_map[a_x][a_y].add(0, 1);
                    }
                }

                //겨울
                map[i][j] += add_map[i][j];
            }
        }
    }
    
}
