import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.*;

class Main_14499{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int roll_x = Integer.parseInt(st.nextToken()), roll_y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[20][20];

        for(int x = 0; x < N; x++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int y = 0; y< M; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");

        int[] roll_num = new int[6];
        int[] side_roll_idx = {1, 6, 3, 4, 2, 5}; //위 아래 동 서 북 남

        //move
        for(int i = 0; i < K; i++){
            int to_move = Integer.parseInt(st.nextToken());

            int[][] move_dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

            if (roll_x + move_dir[to_move - 1][0] < 0 || roll_x + move_dir[to_move - 1][0] >= N || 
                roll_y + move_dir[to_move - 1][1] < 0 || roll_y + move_dir[to_move - 1][1] >= M 
                ){                
                // bw.write("test ? " + tmp_x + " " + tmp_y + "\n");
                continue;
            }

            roll_x += move_dir[to_move - 1][0];
            roll_y += move_dir[to_move - 1][1];

            //동
            if (to_move == 1){ 
                int tmp = side_roll_idx[0];
                side_roll_idx[0] = side_roll_idx[3];
                side_roll_idx[3] = side_roll_idx[1];
                side_roll_idx[1] = side_roll_idx[2];
                side_roll_idx[2] = tmp;
            }
            //서
            else if (to_move == 2){
                int tmp = side_roll_idx[0]; //위 아래 동 서 북 남
                side_roll_idx[0] = side_roll_idx[2];
                side_roll_idx[2] = side_roll_idx[1];
                side_roll_idx[1] = side_roll_idx[3];
                side_roll_idx[3] = tmp;
            }
            //북
            else if (to_move == 3){
                int tmp = side_roll_idx[0]; //위 아래 동 서 북 남
                side_roll_idx[0] = side_roll_idx[5];
                side_roll_idx[5] = side_roll_idx[1];
                side_roll_idx[1] = side_roll_idx[4];
                side_roll_idx[4] = tmp;
            }
            //남
            else if (to_move == 4){
                int tmp = side_roll_idx[0]; //위 아래 동 서 북 남
                side_roll_idx[0] = side_roll_idx[4];
                side_roll_idx[4] = side_roll_idx[1];
                side_roll_idx[1] = side_roll_idx[5];
                side_roll_idx[5] = tmp;
            }

            bw.write(roll_num[side_roll_idx[0] - 1] + "\n");
            // bw.write("test : "+ Arrays.toString(side_roll_idx) + "\n");

            if (map[roll_x][roll_y] == 0){ //이동한 바닥면이 0이라면
                map[roll_x][roll_y] = roll_num[side_roll_idx[1] - 1]; //주사위 -> 바닥 복사
            }
            else {
                roll_num[side_roll_idx[1] - 1] = map[roll_x][roll_y];
                map[roll_x][roll_y] = 0;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    
    
}