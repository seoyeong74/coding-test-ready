//14502 18:59-19:57
import java.util.*;
import java.io.*;

class Point{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_14502 {
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        // System.out.println(N + " " + M);
        Vector<Point> empty = new Vector<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j< M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0){
                    empty.add(new Point(i, j));
                }
            }
        }

        int max = 0;

        for (int i = 0; i < empty.size(); i++){
            for (int j = i + 1; j< empty.size(); j++){
                for(int k= j + 1; k < empty.size(); k ++){

                    // System.out.println(i + " " + j + " "+ k + " ");
                    //add wall
                    map[empty.get(i).x][empty.get(i).y] = 1;
                    map[empty.get(j).x][empty.get(j).y] = 1;
                    map[empty.get(k).x][empty.get(k).y] = 1;

                    max = Math.max(max, play(map));

                    if (max == 32){
                        
                    }

                    //remove wall
                    map[empty.get(i).x][empty.get(i).y] = 0;
                    map[empty.get(j).x][empty.get(j).y] = 0;
                    map[empty.get(k).x][empty.get(k).y] = 0;
                }
            }
        }

        System.out.println(max);

    }

    public static int play(int[][] map){
        int safe = 0;
        int[][] copy_map = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                copy_map[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (map[i][j] != 2){
                    continue;
                }

                boolean[][] visited = new boolean[map.length][map[0].length];
                Deque<Point> q = new ArrayDeque<>();
                q.add(new Point(i, j));

                while(!q.isEmpty()){
                    Point cur = q.poll();

                    if(visited[cur.x][cur.y])
                        continue;

                    visited[cur.x][cur.y] = true;

                    if (copy_map[cur.x][cur.y] == 0){
                        copy_map[cur.x][cur.y] = 2;
                    }

                    if ((cur.x + 1 < copy_map.length) && copy_map[cur.x + 1][cur.y] == 0){
                        q.add(new Point(cur.x + 1, cur.y));
                    }
                    if ((cur.y + 1 < copy_map[0].length) && copy_map[cur.x][cur.y + 1] == 0){
                        q.add(new Point(cur.x, cur.y + 1));
                    }
                    if ((cur.x - 1 > -1) && copy_map[cur.x - 1][cur.y] == 0){
                        q.add(new Point(cur.x - 1, cur.y));
                    }
                    if ((cur.y - 1 > -1) && copy_map[cur.x][cur.y - 1] == 0){
                        q.add(new Point(cur.x, cur.y - 1));
                    }
                }

            }
        }

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (copy_map[i][j] == 0){
                    safe++;
                }
            }
            
        }

        return safe;
    }
    
}
