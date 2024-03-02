import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Stack;

class Main_1874 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int maxN = Integer.parseInt(br.readLine());

        boolean isWrong = false;

        Stack<Integer> stack = new Stack<>();
        int currN = 2;
        stack.push(1);
        StringBuilder result = new StringBuilder();
        result.append("+\n");
        
        for(int j = 0; j < maxN; j++){
            int i = Integer.parseInt(br.readLine());
            while(stack.isEmpty() || stack.peek() < i){
                if (currN > maxN){
                    isWrong = true;
                    break;
                }

                stack.push(currN++);
                result.append("+\n");
            }

            int popInt = stack.pop();
            if (popInt != i){
                isWrong = true;
            }

            if(isWrong)
                break;

            result.append("-\n");
        }

        if(isWrong){
            System.out.println("NO");
        }
        else{
            System.out.println(result);
        }
        
        br.close();
    }
    
}
