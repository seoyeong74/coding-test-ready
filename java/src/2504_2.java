import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.*;

class Main_2504_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputString = br.readLine();
        int tmp = 1, result = 0;
        boolean isNotCorrect = false;
        Stack<Character> stack = new Stack<>();

        char prevChar = ' ';
        for(char i: inputString.toCharArray()){
            switch(i){
                case '(':
                case '[':
                    tmp *= convert(i);
                    stack.push(i);
                    break;
                case ')':
                case ']':
                    if (stack.isEmpty()){
                        isNotCorrect = true;
                        break;
                    }
                    char lastOpen = stack.pop();

                    if (!((lastOpen == '(' && i == ')') || (lastOpen == '[' && i == ']'))){
                        isNotCorrect = true;
                        break;
                    }

                    if (prevChar == '(' || prevChar == '['){
                        result += tmp;
                    }

                    tmp /= convert(i);
                    break;
                default:
                    break;
            }
             
            prevChar = i;
        }

        if (!stack.isEmpty())
            isNotCorrect = true;

        if(isNotCorrect)
            result = 0;

        bw.write(result + "");
        bw.flush();
        bw.close();

        br.close();
    }

    public static int convert(Character input){
        if (input == '(' || input == ')') return 2;
        else return 3;
    }
    
}
