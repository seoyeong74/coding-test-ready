import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.*;

class Move_Info {
    int time;
    String direction;

    Move_Info(int time, String direc){
        this.time = time;
        this.direction = direc;
    }
}

class Point {
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Snake {
    Deque<Point> body;
    Point to_D, to_L, to_Front;

    Snake(Deque<Point> body, Point to_D,Point to_L,Point to_Front){
        this.body = body;
        this.to_D = to_D;
        this.to_L = to_L;
        this.to_Front = to_Front;
    }
}

class Main_3190{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()), K = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];

        for (int i = 0; i < K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }

        int L = Integer.parseInt(br.readLine());
        Deque<Move_Info> to_move = new ArrayDeque<>();
        
        for (int i = 0; i < L; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            to_move.addLast(new Move_Info(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        int time = play(map, N, to_move);

        bw.write(time + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int play(boolean[][] map, int N, Deque<Move_Info> to_move){
        Snake snake = new Snake(new ArrayDeque<Point>(), new Point(1, 0), new Point(-1, 0), new Point(0,1));
        boolean[][] snake_map = new boolean[map.length][map.length];

        snake.body.addFirst(new Point(0, 0));
        snake_map[0][0] = true;

        int time = 1;
        while (time < N * N + 1) {
            Move_Info nevigate = to_move.peekFirst();

            Point new_head = new Point(snake.body.peekFirst().x, snake.body.peekFirst().y);
            if (!to_move.isEmpty() && nevigate.time < time){
                to_move.pollFirst();

                // System.out.println(nevigate.direction);
                if (nevigate.direction.equals("D")){
                    // System.out.println("to D " + snake.to_D.x + " "+ snake.to_D.y);
                    new_head.x += snake.to_D.x;
                    new_head.y += snake.to_D.y;

                    Point tmp = snake.to_Front;
                    snake.to_L = snake.to_Front;
                    snake.to_Front = snake.to_D;
                    snake.to_D = new Point(-tmp.x, -tmp.y);
                }
                else{
                    // System.out.println("to L " + snake.to_L.x + " "+ snake.to_L.y);
                    new_head.x += snake.to_L.x;
                    new_head.y += snake.to_L.y;

                    Point tmp = snake.to_Front;
                    snake.to_D = snake.to_Front;
                    snake.to_Front = snake.to_L;
                    snake.to_L = new Point(-tmp.x, -tmp.y);
                }
            }
            else {
                new_head.x += snake.to_Front.x;
                new_head.y += snake.to_Front.y;
            }

            // System.out.println("end move " + new_head.x + " "+ new_head.y);

            if (is_Out(new_head, N, snake_map))
                break;

            snake.body.addFirst(new_head);
            snake_map[new_head.x][new_head.y] = true;
            if (!map[new_head.x][new_head.y]){
                Point remove = snake.body.pollLast();
                snake_map[remove.x][remove.y] = false;
            }
            else {
                map[new_head.x][new_head.y] = false;
            }

            time++;   
        }

        return time;
    }

    public static boolean is_Out(Point new_head, int N, boolean[][] snake_map){
        if (new_head.x < 0 || new_head.y < 0){
            // System.out.println("  1");
            return true;
        }
        if (new_head.x >= N || new_head.y >= N){
            // System.out.println("  2");
            return true;
        }

        if (snake_map[new_head.x][new_head.y])
            return true;

        return false;
    }
    
}