import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

//투포인터 알고리즘으로 풀어본다. 투포인터 알고리즘이란,
//1. 시작점과 끝점이 첫번째 원소의 인덱스를 가리키도록 한다.
//2. 현재 부분 합이 M과 같다면 카운트한다.
//3. 현재 부분 합이 M보다 작다면 end를 1 증가시킨다.
//4. 현재 부분 합이 M보다 크거나 같다면 start를 1 증가시킨다.
//5. 모든 경우를 확인할 때까지 2-4번 과정을 반복한다.

class Main_1806_2
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

        int endIdx = 0, sum = 0;
        int minLenght = Integer.MAX_VALUE;
        for(int startIdx = 0; startIdx < N; startIdx++){
            // end만큼 이동시키기
            while (sum < S && endIdx < N){
                sum += numList[endIdx];
                endIdx++;
            }

            // 부분합이 m일 때 카운트 증가
            if (sum >= S) {
                minLenght = Math.min(minLenght, (endIdx - startIdx));
            }
            sum -= numList[startIdx];
        }

        System.out.println(minLenght);

        br.close();
    }
}