//14890
import java.util.*;
import java.io.*;

public class 14890 {
    static int N, L;
    static int[][] map;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j= 0; j< N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int i = 0 ; i< N; i++){
            if (checkRow(i)) {
                // System.out.println("row: " + i);
                ans++;
            }
            if (checkCol(i)) {
                // System.out.println("col: " + i);
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static boolean checkRow(int x){
        boolean[] incline = new boolean[N];
        for (int y = 0 ; y < N - 1; y++) {
            int diff = map[x][y + 1] - map[x][y];

            if (Math.abs(diff) > 1) return false;

            //높은 곳에서 낮은 곳으로
            if (diff == 1){
                for (int k = 0; k < L; k++){
                    if(y - k < 0 || incline[y - k]) return false;
                    if(map[x][y] != map[x][y - k]) return false;
                    incline[y - k] = true;
                }
            }
            //낮은 곳에서 높은곳으로
            else if (diff == -1){
                for (int k = 0; k < L; k++){
                    if(y + 1 + k >= N || incline[y + 1 + k]) return false;
                    if(map[x][y + 1] != map[x][y + 1 + k]) return false;
                    incline[y + 1 + k] = true;
                }
            }
            else continue;
        }

        return true;
    }

    public static boolean checkCol(int y){
        boolean[] incline = new boolean[N];
        for (int x = 0 ; x < N - 1; x++) {
            int diff = map[x][y] - map[x + 1][y];

            if (Math.abs(diff) > 1) return false;

            //높은 곳에서 낮은 곳으로
            if (diff == -1){
                for (int k = 0; k < L; k++){
                    if(x - k < 0 || incline[x - k]) return false;
                    if(map[x][y] != map[x - k][y]) return false;
                    incline[x - k] = true;
                }
            }
            //낮은 곳에서 높은곳으로
            else if (diff == 1){
                for (int k = 0; k < L; k++){
                    if(x + 1 + k >= N || incline[x + 1 + k]) return false;
                    if(map[x + 1][y] != map[x + 1 + k][y]) return false;
                    incline[x + 1 + k] = true;
                }
            }
            else continue;
        }

        return true;
    }
    
}
