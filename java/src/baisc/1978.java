import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class Main_1978{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int resultNum = 0;

        while(st.hasMoreTokens()){
            int curr = Integer.parseInt(st.nextToken());

            boolean isDecimal = true;

            if (curr < 2) continue;
            for (int j = 2; j < curr; j++){
                if (curr % j == 0){
                    isDecimal = false;
                    break;
                }
            }

            if (isDecimal) resultNum++;
        }

        bw.write(resultNum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}