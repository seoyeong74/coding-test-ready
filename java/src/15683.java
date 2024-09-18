//15683 10:03 - 10:55
import java.io.*;
import java.util.*;

public class Main_15683 {
    static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<Point> cameras = new ArrayList<>();
    static int[][] map;
    static int N, M;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j< M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if (isCamera(i, j)){
                    cameras.add(new Point(i, j));
                }
            }
        }

        int ans = bfs();

        System.out.println(ans);
    }

    static Point[][][] camera_direcs = {
        { //1
            {new Point(0, 1)}, 
            {new Point(1, 0)},
            {new Point(0, -1)}, 
            {new Point(-1, 0)}
        },
        { //2
            {new Point(0, -1), new Point(0, 1)}, 
            {new Point(-1, 0), new Point(1, 0)}
        },
        { //3
            {new Point(-1, 0), new Point(0, 1)}, 
            {new Point(0, 1), new Point(1, 0)},
            {new Point(1, 0), new Point(0, -1)}, 
            {new Point(0, -1), new Point(-1, 0)}
        },
        { //4
            {new Point(0, -1), new Point(-1, 0), new Point(0, 1)}, 
            {new Point(-1, 0), new Point(0, 1), new Point(1, 0)},
            {new Point(0, 1), new Point(1, 0), new Point(0, -1)}, 
            {new Point(1, 0), new Point(0, -1), new Point(-1, 0)}
        },
        { //5
            {new Point(1, 0), new Point(0, -1), new Point(-1, 0), new Point(0, 1)}, 
        }
    };

    public static int bfs(){
        Deque<ArrayList<Integer>> q = new ArrayDeque<>();
        q.add(new ArrayList<Integer>());

        int min = N * M + 1;

        while(!q.isEmpty()){
            ArrayList<Integer> camera_di = q.poll();
            if (camera_di.size() == cameras.size()){
                min = Math.min(min, cal_safe(camera_di));
                continue;
            }

            int cur_camera_idx = camera_di.size();
            Point carmea_point = cameras.get(cur_camera_idx);

            int camera = map[carmea_point.x][carmea_point.y];

            for (int i = 0; i < camera_direcs[camera - 1].length; i++){
                ArrayList<Integer> new_dir = new ArrayList<Integer>(camera_di);
                new_dir.add(i);
                q.add(new_dir);
            }
        }

        return min;
    }

    public static int cal_safe(ArrayList<Integer> camera_dis){
        // System.out.println("here");
        int[][] map_copy = new int[N][M];

        for(int i = 0; i< N; i++){
            for(int j= 0; j< M; j++){
                map_copy[i][j] = map[i][j];
            }
        }

        for(int i = 0; i < camera_dis.size(); i++){
            int camera_di = camera_dis.get(i);
            Point camera_point = cameras.get(i);
            int camera = map_copy[camera_point.x][camera_point.y];

            for (int j = 0; j < camera_direcs[camera - 1][camera_di].length; j++){
                int cur_x = camera_point.x, cur_y = camera_point.y;

                while (cur_x > -1 && cur_y > -1 && cur_x < N && cur_y < M){
                    if (isCamera(cur_x, cur_y)){
                        cur_x += camera_direcs[camera - 1][camera_di][j].x;
                        cur_y += camera_direcs[camera - 1][camera_di][j].y;
                        continue;
                    }

                    if (isWall(cur_x, cur_y)){
                        break;
                    }

                    map_copy[cur_x][cur_y] = -1;
                    cur_x += camera_direcs[camera - 1][camera_di][j].x;
                    cur_y += camera_direcs[camera - 1][camera_di][j].y;
                }
            }

        }

        int sum = 0;

        for(int i = 0; i< N; i++){
            for(int j= 0; j< M; j++){
                if (map_copy[i][j] == 0)
                    sum++;
            }
        }

        return sum++;
    }

    public static boolean isCamera(int x, int y){
        if (0 < map[x][y] && map[x][y] < 6){
            return true;
        }
        return false;
    }

    public static boolean isWall(int x, int y){
        if (map[x][y ]== 6){
            return true;
        }
        return false;
    }
    
}
