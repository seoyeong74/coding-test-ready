//17143 17:41-
import java.util.*;

public class 17143 {
    static int R, C, M;
    static int[][] map;

    static class Shark{
        int r, c;
        //속력
        int s;
        //이동 방향 1 상 2 하 3 우 4 좌
        int d;
        //크기
        int z;

        Shark(int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
    static Shark[] shark_list;
    static int remain_shark;

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        M = sc.nextInt();

        map = new int[R + 1][C + 1];
        shark_list = new Shark[M + 1];
        remain_shark = M;

        for (int i = 1; i <= M; i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();
            int z = sc.nextInt();

            shark_list[i] = new Shark(r, c, s, d, z);
            map[r][c] = i;
        }

        int ans = 0;

        for (int j_ = 1; j_ <= C; j_++){
            ans += catch_shark(j_);
            shark_move();

            
            // System.out.println(ans);

            // for(int i = 1; i <=R; i++){
            //     for(int j = 1; j <= C; j++){
            //         System.out.print(map[i][j] + " ");
            //     }
            //     System.out.println();
            // }

            if (remain_shark == 0) break;
        }

        System.out.println(ans);
    }

    public static int catch_shark(int j){
        for(int i = 1; i <= R; i++){
            if (map[i][j] == 0) continue;
            //상어가 존재
            else {
                int size = shark_list[map[i][j]].z;
                shark_list[map[i][j]] = null;
                remain_shark--;

                return size;
            }
        }

        return 0;
    }

    static int[] dx = {0, -1, 1, 0,0};
    static int[] dy = {0, 0, 0, 1, -1};

    public static void shark_move(){
        int[][] new_map = new int[R+1][C+1];

        for(int shark_num = 1; shark_num <= M; shark_num++){
            if(shark_list[shark_num] == null) continue;

            Shark shark = shark_list[shark_num];

            int move_num;
            //실질적으로 이동하는 칸을 구한다.
            //위 아래로 움직일 때
            if (shark.d == 1 || shark.d == 2){
                move_num = shark.s % ((R - 1) * 2);
            }
            // 좌 우로 움직일 때 
            else {
                move_num = shark.s % ((C - 1) * 2);
            }

            // System.out.println(" irst: " + shark.r + " " + shark.c);
            // System.out.println(move_num);
            // System.out.println(shark.d);

            //실제로 움직임
            boolean change = false;
            for (int i = 0; i < move_num; i++){
                if (shark.r == 1 && shark.d == 1) {
                    change = !change;
                    if (shark.d == 1) shark.d = 2;
                    else if (shark.d == 2) shark.d = 1;
                    else if (shark.d == 3) shark.d = 4;
                    else if (shark.d == 4) shark.d = 3;
                }
                else if (shark.c == 1 && shark.d == 4) {
                    change = !change;
                    if (shark.d == 1) shark.d = 2;
                    else if (shark.d == 2) shark.d = 1;
                    else if (shark.d == 3) shark.d = 4;
                    else if (shark.d == 4) shark.d = 3;
                }
                else if (shark.r == R && shark.d == 2) {
                    change = !change;
                    if (shark.d == 1) shark.d = 2;
                    else if (shark.d == 2) shark.d = 1;
                    else if (shark.d == 3) shark.d = 4;
                    else if (shark.d == 4) shark.d = 3;
                }
                else if (shark.c == C && shark.d == 3) {
                    change = !change;
                    if (shark.d == 1) shark.d = 2;
                    else if (shark.d == 2) shark.d = 1;
                    else if (shark.d == 3) shark.d = 4;
                    else if (shark.d == 4) shark.d = 3;
                }

                shark.r += dx[shark.d];
                shark.c += dy[shark.d];

                // System.out.println(change + " " + shark.r + " " + shark.c);
            }

            // System.out.println("last: " + shark.r + " " + shark.c);

            //이미 상어가 존재할 떄
            if (new_map[shark.r][shark.c] != 0){
                int exist_shark_num = new_map[shark.r][shark.c];
                //이미 있는 상어보다 현재 상어가 더 클 떄 
                if (shark_list[exist_shark_num].z < shark.z){
                    //잡아먹힘
                    shark_list[exist_shark_num] = null;
                    new_map[shark.r][shark.c] = shark_num;
                    remain_shark--;
                }
                //반대의 경우 잡아먹힘
                else{
                    shark_list[shark_num] = null;
                    remain_shark--;
                    continue;
                }
            }
            //상어가 없을 때
            else{
                new_map[shark.r][shark.c] = shark_num;
            }

            map = new_map ;
        }

    }

}
