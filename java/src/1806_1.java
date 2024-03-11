import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main_1806_1
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), S = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine(), " ");
        int[] numList = new int[N];

        long totalSum = 0;
        for(int i = 0; i< N; i++){
            numList[i] = Integer.parseInt(st.nextToken());
            totalSum += numList[i];
            if (numList[i] >= S) {
                System.out.println(1);
                return;
            }
        }

        if(totalSum < S){
            System.out.println(0);
            return;
        }
        
        int minLenght = Integer.MAX_VALUE;
        long subListTotalSum = totalSum;
        for(int i = 0; i < N; i++){
            int sum = 0;
            for(int j = i; j < N; j++){
                sum += numList[j];
                if (sum >= S){
                    minLenght = Math.min(minLenght, (j - i) + 1);
                    break;
                }
            }

            subListTotalSum -= numList[i];

            if(subListTotalSum < S)
                break;
            
            if (minLenght == 1)
                break;
        }

        System.out.println(minLenght);

        br.close();
    }
}