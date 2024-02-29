import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.*;

class Main_14888{
    static int totalNum = 0;
    static int[] numList = new int[11];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        totalNum = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < totalNum; i++){
            numList[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        
        int[] operatorList = new int[4];
        for(int i = 0; i< 4; i++){
            operatorList[i] = Integer.parseInt(st.nextToken());
        }

        Stack<CalInfo> stack = new Stack<>();

        long maxNum = Long.MIN_VALUE;
        long minNum = Long.MAX_VALUE;

        CalInfo first = new CalInfo(numList[0], 1, operatorList);
        pushStack(first, stack);

        while(!stack.isEmpty()){
            CalInfo currElement = stack.pop();
            if (currElement.toOperateIdx > totalNum - 1){
                maxNum = Math.max(maxNum, currElement.currNum);
                minNum = Math.min(minNum, currElement.currNum);

                continue;
            }
            
            pushStack(currElement, stack);
        }

        bw.write(maxNum + "\n" + minNum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    
    public static void pushStack(CalInfo currElement, Stack<CalInfo> stack) {
        int[] operatorList = currElement.ableOperatorList;
        long prevResult = currElement.currNum;
        int currIdx = currElement.toOperateIdx;
        int currNum = numList[currIdx];

        for (int i = 0; i < 4; i++) {
            if (operatorList[i] > 0){
                long calcResult = calByOperator(i, prevResult, currNum);
                int[] newOperList = Arrays.copyOf(operatorList, 4);
                newOperList[i]--;
                stack.push(new CalInfo(calcResult, currIdx + 1, newOperList));
            }
        }
    }

    public static long calByOperator(int operator, long prevResult, int currNum){
        long result;
        switch(operator){
            case 0:
                result = prevResult + currNum;
                break;
            case 1:
                result = prevResult - currNum;
                break;
            case 2:
                result = prevResult * currNum;
                break;
            case 3:
                result = prevResult / currNum;
                break;
            default: 
                throw new IllegalArgumentException("Invalid operator");
        }

        return result;
    }
}

class CalInfo{
    long currNum;
    int toOperateIdx;
    int[] ableOperatorList;

    public CalInfo(long curr, int idx, int[] list){
        this.currNum = curr;
        this.toOperateIdx = idx;
        this.ableOperatorList = list;
    }
}