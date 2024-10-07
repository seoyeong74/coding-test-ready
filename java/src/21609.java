//21609 22:15-23:38
import java.util.*;

public class 21609{
    static int N, M;
    static int[][] map;

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];

        for(int i = 0; i< N; i++){
            for(int j = 0; j< N; j++){
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) 
                    map[i][j] = M + 1;
            }
        }

        // System.out.println("rainbow: " + (M +1));

        int ans = 0;

        while(true){
            int score = find_big_group();

            if (score == -1) {
                break;
            }

            // System.out.println();
            // System.out.println("score" + score);

            // for(int i = 0; i< N; i++){
            //     for(int j = 0; j< N; j++){
            //         System.out.print(map[i][j] + " ");
            //     }
            //     System.out.println();
            // }

            ans += score * score;
            
            gravity();
    
            turn();
    
            gravity();

            // System.out.println("after game");
            // for(int i = 0; i< N; i++){
            //     for(int j = 0; j< N; j++){
            //         System.out.print(map[i][j] + " ");
            //     }
            //     System.out.println();
            // }
        }


        System.out.println(ans);
    }

    static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[] direc_x = {-1, 1, 0, 0};
    static int[] direc_y = { 0, 0, -1, 1,};

    public static int find_big_group(){
        int max_num = 0;
        int max_rainbow = 0;
        Point max_standard = new Point(0, 0);
        boolean[][] visited = new boolean[N][N];
        boolean[][] max_to_remove = new boolean[N][N];;
        
        for(int i = 0; i < N; i++){
            for(int j= 0; j < N; j++){
                if (visited[i][j]) continue;

                //일반 블록이 하나가 무조건 들어가 있어야하므로
                if (map[i][j] == -1 || map[i][j] == M + 1) continue;

                //빈칸이면 pass
                if (map[i][j] == 0) continue;
                
                //아직 방문하지 않은 일반 블록인 경우 bfs로 그룹을 구함
                Deque<Point> q = new ArrayDeque<>();

                q.addLast(new Point(i, j));
                boolean[][] to_remove = new boolean[N][N];
                visited[i][j] = true;
                to_remove[i][j] = true;

                int color = map[i][j];
                int count = 1;
                int rainbow = 0;

                Point standard = new Point(i, j);

                // System.out.println("check");
                while(!q.isEmpty()){
                    Point cur = q.pollFirst();
                    // System.out.println(cur.x + " " + cur.y);

                    for(int k = 0; k < 4; k++){
                        int a_x = cur.x + direc_x[k];
                        int a_y = cur.y + direc_y[k];

                        
                        //범위를 벗어난 경우
                        if (a_x < 0 || a_y < 0 || a_x >= N|| a_y >= N) continue;

                        // System.out.println(a_x + " " + a_y + " " + map[a_x][a_y]);

                        //이번에 방문했으면 continue
                        if (to_remove[a_x][a_y]) continue;
                        //검은 색은 갈 수 없다.
                        if (map[a_x][a_y] == -1 || map[a_x][a_y] == 0) continue;
                        //다른 색이면 갈 수 없다.
                        if (map[a_x][a_y] != M + 1 && map[a_x][a_y] != color) continue;

                        visited[a_x][a_y] = true;
                        to_remove[a_x][a_y] = true;
                        count++;

                        //무지개 블록 확인
                        if (map[a_x][a_y] == M + 1) rainbow++;

                        q.addLast(new Point(a_x, a_y));

                        //기준 블록 체크
                        if (
                            //무지개 블록 아니면서
                            map[a_x][a_y] != M + 1 &&
                            (   
                                //행의 번호가 가장 작은 것
                                (standard.x > a_x) ||
                                //행의 번호가 같다면 열의 번호가 가장 작은 것
                                ((standard.x == a_x) && standard.y > a_y)
                            )
                        ){
                            standard = new Point(a_x, a_y);
                        }
                    }
                }

                if (count < 2) continue;

                if (count > max_num){
                    // System.out.println("coutn " + count);
                    max_num = count;
                    max_rainbow = rainbow;
                    max_standard = standard;
                    max_to_remove = to_remove;
                }
                else if (count == max_num && rainbow > max_rainbow){
                    // System.out.println("secont" + count+ " " + rainbow);
                    max_num = count;
                    max_rainbow = rainbow;
                    max_standard = standard;
                    max_to_remove = to_remove;
                }
                //행이 가장 큰 것
                else if (
                    count == max_num && 
                    rainbow == max_rainbow &&
                    max_standard.x < standard.x
                ){
                    max_num = count;
                    max_rainbow = rainbow;
                    max_standard = standard;
                    max_to_remove = to_remove;
                }
                //열이 가장 큰 것
                else if (
                    count == max_num && 
                    rainbow == max_rainbow &&
                    max_standard.x == standard.x &&
                    max_standard.y < standard.y
                ){
                    max_num = count;
                    max_rainbow = rainbow;
                    max_standard = standard;
                    max_to_remove = to_remove;
                }
            }
        }

        //더 이상 그룹이 없을 경우
        if (max_num < 2) return -1;

        //블록 제거
        for (int i = 0; i< N; i++){
            for(int j = 0; j< N; j++){
                if(max_to_remove[i][j])
                    map[i][j] = 0;
            }
        }

        return max_num;
    }

    public static void gravity(){
        // System.out.println("gravity");
        for (int j = 0; j < N; j++){
            for (int i = N-2; i >= 0; i--){
                //빈칸이거나 검은 색
                if (map[i][j] == 0 || map[i][j] == -1) continue;

                int to_go;
                for (to_go = i + 1; to_go < N; to_go++){
                    //빈칸이 아니면 
                    if (map[to_go][j] != 0) break;
                }

                to_go--;
                if (to_go == i) continue;

                map[to_go][j] = map[i][j];
                map[i][j] = 0;

                // System.out.println();
                // for(int i_ = 0; i_< N; i_++){
                //     for(int j_ = 0; j_< N; j_++){
                //         System.out.print(map[i_][j_] + " ");
                //     }
                //     System.out.println();
                // }
            }
        }
    }

    public static void turn(){
        int[][] new_map = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                new_map[N - 1 - j][i] = map[i][j];
            }
        }

        map = new_map;
    }
}