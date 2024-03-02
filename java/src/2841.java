import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main_2841{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());

        ArrayList<Stack<Integer>> stackList = new ArrayList<>(N);

        for(int i= 0; i< N; i++){
            Stack<Integer> newStack = new Stack<Integer>();
            stackList.add(newStack);
        }

        long result = 0;
        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int currN = Integer.parseInt(st.nextToken()), currP = Integer.parseInt(st.nextToken());
            Stack<Integer> currStack = stackList.get(currN);

            if (currStack.isEmpty()){
                result++;
                currStack.push(currP);
                continue;
            }

            while(!currStack.isEmpty() && currStack.peek() > currP){
                currStack.pop();
                result++;
            }

            if(currStack.isEmpty() || currStack.peek() != currP){
                currStack.push(currP);
                result++;
            }
        }

        br.close();

        System.out.println(result);
    }
}