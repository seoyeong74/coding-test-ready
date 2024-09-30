//17779 22:57-23:06 stop 23:22-
//풀이 봤음
import java.util.*;
import java.io.*;

public class 17779 {
    static int N;
    static int[][] map;
    static int min_diff = Integer.MAX_VALUE;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j<= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 1; x <= N; x++){
            for (int y = 1; y <= N; y++){
                for (int d1 = 1; d1 <= N; d1++){
                    for (int d2 = 1; d2 <= N; d2++){
                        if (!((d1 + d2 + x <= N) && (1 <= y - d1) && (y - d1 <= N) && (y+d2 <=N))){
                            // System.out.println("hrer");
                            break;
                        }
                        cal_people(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(min_diff);
    }

    static public void cal_people(int x, int y, int d1, int d2){
        boolean[][] is_5 = new boolean[N + 1][N + 1];
        //꼭짓점
        is_5[x][y] = is_5[x + d1][y - d1] = is_5[x + d2][y + d2] = is_5[x +d2 + d1][y + d2 - d1] = true;

        //경계선
        for (int i = 0; i <= d1; i++){
            is_5[x + i][y - i] = true;
            is_5[x + d2 + i][y + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++){
            is_5[x + i][y + i] = true;
            is_5[x + d1 + i][y - d1 + i] = true;
        }

        //내부 채우기
        for(int i = x + 1; i < x + d1 + d2; i++){
            int true_num = 0;
            for (int j = 1; j<= N; j++){
                if (is_5[i][j]){
                    true_num++;
                }

                if (true_num == 1){
                    is_5[i][j] = true;
                }
            }
            // System.out.println();
        }

        // if (x == 2 && y == 3 && d1 == 2 && d2 ==2){
        //     for(int i = 1; i <= N; i++){
        //         for (int j = 1; j<= N; j++){
        //             System.out.print(is_5[i][j] + " ");
        //         }
        //         System.out.println();
        //     }
        // }

        int[] people_sum = new int[5];
        // System.out.println();

        for(int i = 1; i <= N; i++){
            for (int j = 1; j<= N; j++){
                if(is_5[i][j]){
                    people_sum[4] += map[i][j];
                    // if (x == 2 && y == 3 && d1 == 2 && d2 ==2){
                    //     System.out.print(5 + " ");
                    // }
                }
                //1번
                else if(1 <= i && i < x + d1 && 1 <= j && j <= y){
                    people_sum[0] += map[i][j];
                    // if (x == 2 && y == 3 && d1 == 2 && d2 ==2){
                    //     System.out.print(1 + " ");
                    // }
                }
                //2
                else if(1 <= i && i <= x + d2 && y < j && j <= N){
                    people_sum[1]+= map[i][j];
                    // if (x == 2 && y == 3 && d1 == 2 && d2 ==2){
                        // System.out.print(2 + " ");
                    // }
                }
                //3
                else if(x + d1 <= i && i <= N && 1 <= j && j < y - d1 + d2){
                    people_sum[2]+= map[i][j];
                    // if (x == 2 && y == 3 && d1 == 2 && d2 ==2){
                    //     System.out.print(3 + " ");
                    // }
                }
                //4
                else if(x + d2 < i && i <= N && y - d1 + d2 <= j && j <= N){
                    people_sum[3] += map[i][j];
                    // if (x == 2 && y == 3 && d1 == 2 && d2 ==2){
                    //     System.out.print(4 + " ");
                    // }
                }
                //5
                // else{
                //     people_sum[4] += map[i][j];
                //     if (x == 2 && y == 3 && d1 == 2 && d2 ==2){
                //         System.out.print(5 + " ");
                //     }
                // }
            }
            // if (x == 2 && y == 3 && d1 == 2 && d2 ==2)
            // System.out.println();
        }

        Arrays.sort(people_sum);

        int diff = Math.abs(people_sum[0] - people_sum[4]);
        // for(int i: people_sum){
        //     System.out.println(i);
        // }

        min_diff = Math.min(diff, min_diff);

        // if (min_diff == 38){
        //     System.out.println(x + " " + y + " " + d1 + " " + d2);
        // }

    }
    
}
