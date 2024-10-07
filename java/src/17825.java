//17825 17:38-
import java.util.*;

public class 17825 {
    static int[][] map = {
        //빨간 색
        {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
        //10 파란색
        {10, 13, 16, 19},
        //20 파란색
        {20, 22, 24},
        //30 파란색
        {30, 28, 27, 26},

        {25, 30, 35}
    };

    static boolean[][] hourse_exist = new boolean[5][25];

    static class Hourse{
        boolean done = false;

        int map_num;
        int idx;

        Hourse(int map_num, int idx){
            this.map_num = map_num;
            this.idx = idx;
        }
        Hourse(Hourse pre){
            this.map_num = pre.map_num;
            this.idx = pre.idx;
            this.done = pre.done;
        }
    }
    static Hourse[] hourse_list = new Hourse[4];

    static int[] roll = new int[10];

    static int ans = 0;

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 10; i++){
            roll[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++){
            hourse_list[i] = new Hourse(0, 0);
        }

        hourse_exist[0][0] = true;

        
        for(int i = 0; i < 4; i++){
            test[i] = new ArrayDeque<>();
        }
        dfs(0, 0);

        System.out.println(ans);
    }

    static Deque<Integer>[] test = new Deque[4]; 

    public static void dfs(int cur, int sum){
        if(cur == 10) {
            System.out.println(sum);
            ans = Math.max(ans, sum);

            for(int i = 0; i < 4; i++){
                for(int k: test[i])
                    {System.out.print(k + " ");}
                System.out.println();
            }

            return;
        }

        int to_move_num = roll[cur];

        for(int i = 0; i < 4; i++){
            Hourse hourse = hourse_list[i];
            Hourse remember = new Hourse(hourse);

            //도착한 말은 선택하지 않는다.
            if (hourse.done) continue;

            //도착칸에 왔을 때
            if ((hourse.idx + to_move_num >= map[hourse.map_num].length) 
                && hourse.map_num == 0 
                || (hourse.map_num == 4 && (hourse.idx + to_move_num > map[hourse.map_num].length))
                )
             {
                //말 이동
                hourse_exist[remember.map_num][remember.idx] = false;
                hourse.done = true;
                //도착칸이면 점수 획득을 하지 않는다.

                // System.out.println("hourse num: " + i + " " + map[remember.map_num][remember.idx] +  " dice: " + cur + " " + to_move_num + " done");

                test[i].addLast(-1);
                dfs(cur + 1, sum);
            }
            //칸 이동
            else {
                int next_map_num = hourse.map_num;
                int next_idx = hourse.idx + to_move_num;
                //10 파란칸에 멈출 경우
                if (hourse.map_num == 0 && map[0][next_idx] == 10){
                    next_map_num = 1;
                    next_idx = 0;
                }
                //20 파란칸에 멈출 경우
                else if (hourse.map_num == 0 && map[0][next_idx] == 20){
                    next_map_num = 2;
                    next_idx = 0;
                }
                //30 파란칸에 멈출 경우
                else if (hourse.map_num == 0 && map[0][next_idx] == 30){
                    next_map_num = 3;
                    next_idx = 0;
                }
                //25부터 map으로 이동해야하는 경우
                else if (
                    hourse.map_num < 4 && 
                    hourse.map_num > 0 && 
                    next_idx >= map[hourse.map_num].length 
                ){
                    next_map_num = 4;
                    next_idx -= map[hourse.map_num].length - 1; 
                }
                //40으로 
                if (
                    hourse.map_num == 4 && 
                    next_idx == map[hourse.map_num].length 
                ){
                    next_map_num = 0;

                }

                //옮기려는 곳에 말이 존재하면 선택할 수 없다.
                if(hourse_exist[next_map_num][next_idx]){
                    hourse_exist[hourse.map_num][hourse.idx] = false;
                    hourse_list[i] = remember;
                    // System.out.println(remember.map_num + " " + remember.idx);
                    // System.out.println();
                    hourse_exist[remember.map_num][remember.idx] = true;
                    continue;
                }

                //기존 말 제거
                hourse_exist[hourse.map_num][hourse.idx] = false;

                //말 옮기기
                hourse.map_num = next_map_num;
                hourse.idx = next_idx;

                //말 놓기
                hourse_exist[hourse.map_num][hourse.idx] = true;

                test[i].addLast(map[hourse.map_num][hourse.idx]);

                System.out.println("hourse num: " + i + " " + map[remember.map_num][remember.idx] + " dice: " + cur + " " + to_move_num + " " + map[hourse.map_num][hourse.idx]);
                //다음 주사위 돌리기
                dfs(cur + 1, sum + map[next_map_num][next_idx]);
            }

            //되돌려 놓기
            // System.out.println(hourse.map_num + " " + hourse.idx);
            hourse_exist[hourse.map_num][hourse.idx] = false;
            hourse_list[i] = remember;
            // System.out.println(remember.map_num + " " + remember.idx);
            // System.out.println();
            hourse_exist[remember.map_num][remember.idx] = true;

            test[i].pollLast();

            System.out.println("return");
        }
    }
}
