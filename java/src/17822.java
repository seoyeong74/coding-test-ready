//17822 20:33-21:13 21:15-21:41
import java.util.*;

public class 17822 {
    static int N, M, T;
    static ArrayList<Integer>[] plane;
    static int test = 0;

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        T = sc.nextInt();

        plane = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++){
            plane[i] = new ArrayList<>();
        }

        for (int i = 1; i< N+1; i++){
            for (int j = 0; j < M; j++){
                plane[i].add(sc.nextInt());
            }
        }

        for (test = 0; test< T; test++){
            int x = sc.nextInt();
            int d = sc.nextInt();
            int k = sc.nextInt();

            roll(x, d, k);
            // if (test == 2){
                // System.out.println("roll");
                // for(int i = 1; i < N + 1; i++){
                //     for (int j = 0; j < M; j++){
                //         System.out.print(plane[i].get(j) + " ");
                //     }
                //     System.out.println();
                // }
            // }
            remove();
            // if (test == 2){
                // System.out.println("remove");
                // for(int i = 1; i < N + 1; i++){
                //     for (int j = 0; j < M; j++){
                //         System.out.print(plane[i].get(j) + " ");
                //     }
                //     System.out.println();
                // }
            // }
        }

        int ans = 0;
        for(int i = 1; i < N + 1; i++){
            for (int j = 0; j < M; j++){
                ans += plane[i].get(j);
            }
        }

        System.out.println(ans);
    }

    public static void roll(int x, int d, int k){
        //배수인 원판 들
        for(int i = x; i < N + 1; i += x){
            ArrayList<Integer> cur = plane[i];

            //시계 방향
            //뒤의 k개가 앞으로 
            if (d == 0){
                List<Integer> back = cur.subList(0, M - k);
                List<Integer> front = cur.subList(M - k, cur.size());
    
                front.addAll(back);
                // for (int test: front){
                //     System.out.print(test + " ");
                // }
                // System.out.println();
                
                plane[i] = new ArrayList<Integer>(front);

            }
            //반시계 방향
            //얖의 k개가 뒤로
            if (d == 1){
                List<Integer> back = cur.subList(0, k);
                List<Integer> front = cur.subList(k, cur.size());
    
                front.addAll(back);
                // for (int test: front){
                //     System.out.print(test + " ");
                // }
                // System.out.println();
                
                plane[i] = new ArrayList<Integer>(front);

            }
        }
    }

    static int[][] direction = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    //제일 안쪽과 바깥쪽은 살펴볼 이유가 없음
    public static void remove(){
        boolean neighbor = false;
        int sum = 0;
        int num = 0;

        boolean[][] is_remove = new boolean[N+1][M];

        for(int i = 1; i <= N; i++){
            for (int j = 0; j < M; j++){
                //지워진 수
                if (plane[i].get(j) == 0) continue;

                sum += plane[i].get(j);
                boolean in_neighbor = false;
                num++;

                for (int d = 0; d < 4; d++){
                    int a_i = i + direction[d][0];
                    int a_j = (j + direction[d][1] + M) % M;

                    if (a_i > N || a_i < 1) continue;

                    if (plane[a_i].get(a_j).equals(plane[i].get(j))){
                        //인접한 수 지움
                        // plane[a_i].set(a_j, 0);
                        is_remove[a_i][a_j] = true;
                        neighbor = true;
                        in_neighbor = true;
                    }
                }

                if (in_neighbor){
                    // plane[i].set(j, 0);
                    is_remove[i][j] = true;
                }

                // System.out.println();
                // for(int i_ = 1; i_ < N + 1; i_++){
                //     for (int j_ = 0; j_ < M; j_++){
                //         System.out.print(plane[i_].get(j_) + " ");
                //     }
                //     System.out.println();
                // }
            }
        }

        //인접한 것이 있다면 그만
        if (neighbor){
            for (int i = 1; i < N + 1; i++){
                for (int j= 0; j< M; j++){
                    if (is_remove[i][j]){
                        plane[i].set(j, 0);
                    }
                }
            }
            return;
        }

        //인접한 것이 없음
        float mean = sum / (float)num;
        // System.out.println("mean: " + mean);
        // System.out.println(sum + " " + num);

        for (int i = 1; i < N + 1; i++){
            for (int j= 0; j< M; j++){
                //지워진 수
                if (plane[i].get(j) == 0) continue;

                if (plane[i].get(j) > mean){
                    plane[i].set(j, plane[i].get(j) - 1);
                }
                else if(plane[i].get(j) < mean){
                    plane[i].set(j, plane[i].get(j) + 1);
                }
            }
        }
    }
}
