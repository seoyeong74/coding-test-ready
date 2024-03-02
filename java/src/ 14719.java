import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main_14719
{
    // tip: arguments are passed via the field below this editor
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] heightList = new int[W];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i< W; i++){
            heightList[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for(int h = 1; h <= H; h++){
            boolean isLeftBolck = false;
            int tmp = 0;
            for(int w= 0; w< W; w++){
                int maxHeight = heightList[w];

                if (isLeftBolck && (h > maxHeight))
                    tmp++;
                else if (isLeftBolck && (h <= maxHeight)){
                    result += tmp;
                    tmp = 0;
                }
                
                if (h <= maxHeight){
                    isLeftBolck = true;
                }
            }
        }

        br.close();
        System.out.println(result);
    }
}