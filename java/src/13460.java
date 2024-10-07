//13460 18:37
import java.util.*;
public class 13460 {
    static int N, M;
    //0은 빈칸 1은 벽 2가 구멍
    static int[][] map;

    static class Info{
        int rx, ry;
        int bx, by;

        int num;

        Info(int rx, int ry, int bx, int by, int num){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.num = num;
        }
    }

    static int ans = Integer.MAX_VALUE;

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];

        int init_bx = -1, init_by = -1;
        int init_rx = -1, init_ry = -1;

        for(int i = 0; i< N; i++){
            String string_map = sc.next();
            for(int j = 0; j < M; j++){
                char cur = string_map.charAt(j);

                //빈칸
                if(cur == '.') continue;
                //벽
                if (cur == '#') map[i][j] = 1;
                //구멍
                if(cur== 'O') map[i][j] = 2;

                //파란 공
                if(cur == 'B'){
                    init_bx = i;
                    init_by = j;
                }

                //빨간 공
                if(cur == 'R'){
                    init_rx = i;
                    init_ry = j;
                }
            }
        }

        // System.out.println(init_bx + " " + init_by + " " + init_rx + " " + init_ry);

        // for(int i = 0; i< N; i++){
        //     for(int j = 0; j < M; j++){
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        Deque<Info> q = new ArrayDeque<>();

        q.addLast(new Info(init_rx, init_ry, init_bx, init_by, 1));

        while(!q.isEmpty()){
            Info cur_info = q.pollFirst();
            // System.out.println(cur_info.rx + " " + cur_info.ry + " " + cur_info.bx + " " + cur_info.by + " " + cur_info.num);

            //10회 이상이면 그만 두어야 함
            if (cur_info.num > 10) break;

            for(int i = 0; i < 4; i++){
                int next_rx = cur_info.rx, next_ry = cur_info.ry;
                int next_bx = cur_info.bx, next_by = cur_info.by;

                //빨간 공 멈출 때까지 감
                for(int j = 0; j < M; j++){
                    // System.out.println("pre: " + next_rx + " " + next_ry);
                    next_rx += dx[i];
                    next_ry += dy[i];
                    // System.out.println(next_rx + " " + next_ry);
                    //구멍에 빠졌을 때
                    if (map[next_rx][next_ry] == 2){
                        break;
                    }

                    //벽에 일 때
                    if (map[next_rx][next_ry] == 1){
                        next_rx -= dx[i];
                        next_ry -= dy[i];
                        break;
                    }
                }

                for(int j = 1; j < M; j++){
                    next_bx += dx[i];
                    next_by += dy[i];

                    //구멍에 빠졌을 때
                    if (map[next_bx][next_by] == 2){
                        break;
                    }

                    //벽에 일 때
                    if (map[next_bx][next_by] == 1){
                        next_bx -= dx[i];
                        next_by -= dy[i];
                        break;
                    }
                }

                //파란 공이 구멍에 빠졌을 경우
                if (map[next_bx][next_by] == 2){
                    continue;
                }

                //빨간 공이 구멍에 빠졌을 경우
                if (map[next_rx][next_ry] == 2){
                    // ans = Math.min(ans, cur_info.num + 1);
                    System.out.println(cur_info.num);
                    return;
                }

                //구멍에 빠진 것이 아닌데 둘이 겹쳐 있을 때 위치 재조정
                if (next_rx == next_bx && next_ry == next_by){
                    //상
                    if (i == 0){
                        //빨간 공이 더 아래에 있으면
                        if (cur_info.rx > cur_info.bx) next_rx++;
                        else next_bx++;
                    }
                    //하
                    else if (i == 1){
                        //빨간 공이 더 아래에 있으면
                        if (cur_info.rx > cur_info.bx) next_bx--;
                        else next_rx--;
                    }
                    //좌
                    else if (i == 2){
                        //빨간 공이 더 오른쪽에 있으면
                        if(cur_info.ry > cur_info.by) next_ry++;
                        else next_by++;
                    }
                    //우
                    else if (i == 3){
                        //빨간 공이 더 오른쪽에 있으면
                        if(cur_info.ry > cur_info.by) next_by--;
                        else next_ry--;
                    }
                }

                //이동이 완료됨
                q.addLast(new Info(next_rx, next_ry, next_bx, next_by, cur_info.num + 1));
            }
        }

        System.out.println(-1);
    }

    //상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

}