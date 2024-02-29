import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Stack;

class Main_2504_1{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputString = br.readLine();
        Stack<Character> stack = new Stack<>();
        Stack<Pair> sumAndDepth = new Stack<>();
        
        boolean isNotCorrect = false;

        int depth = 0;
        boolean isPrevClose = false;
        for(char i : inputString.toCharArray()){
            if (isNotCorrect) {
                break;    
            }

            if (i == '(' || i == '['){
                stack.push(i);
                depth++;
                isPrevClose = false;
                continue;
            }

            if (stack.isEmpty()) {
                isNotCorrect = true;
                break;
            }
            char lastChar = stack.pop();
            
            if (!((lastChar == '(' && i == ')') || (lastChar == '[' && i == ']'))) {
                isNotCorrect = true;
                break;
            }

            depth--;
            
            if (isPrevClose) {
                long multiple = 0;
            
                while (!sumAndDepth.empty()) {
                    if (sumAndDepth.peek().depth > depth) {
                        multiple += sumAndDepth.pop().sum;
                        continue;
                    }
                    break;
                }
                
                sumAndDepth.push(new Pair(convert(lastChar) * multiple, depth));
            }
            else
                sumAndDepth.push(new Pair(convert(lastChar), depth));

            isPrevClose = true;
        }

        if (!stack.isEmpty()) {
            isNotCorrect = true;
        }

        long result = 0;
        if(!isNotCorrect){
            while (!sumAndDepth.empty()) {
                result += sumAndDepth.pop().sum;
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();

        br.close();
    }

    public static int convert(char input) {
        if (input == '(' || input == ')' ) {
            return 2;
        } 
        else return 3;
    }
}

class Pair{
    long sum;
    int depth;

    public Pair(long sum, int depth){
        this.sum = sum;
        this.depth = depth;
    }
}