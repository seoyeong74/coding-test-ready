import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Main_1405
{
    static double[] probabilityList = new double[4];
    static int N;
    static double result = 0.0;

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < 4; i++){
            probabilityList[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }

        boolean[][] visited = new boolean[29][29];

        int initX = 14, initY = 14;
        visited[initX][initY] = true;
        bfs(0, 1.0, initX, initY, visited);

        System.out.println(result);

        br.close();
    }

    static void bfs(int moveNum, double prob, int currX, int currY, boolean[][] visited){

        if(moveNum >= N){
            result += prob;
            return;
        }

        for(int i = 0; i < 4; i++){
            int nextX = currX, nextY = currY;
            if(i == 0){
                nextX++;
            }
            else if(i== 1){
                nextX--;
            }
            else if(i== 2){
                nextY--;
            }
            else if(i==3){
                nextY++;
            }
            double nextPorb = prob * probabilityList[i];

            if (!visited[nextX][nextY]){
                visited[nextX][nextY] = true;
                bfs(moveNum + 1, nextPorb, nextX, nextY, visited);
                visited[nextX][nextY] = false;
            }
        }
    }
}